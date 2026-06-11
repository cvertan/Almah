package allmahVer4.graphdb;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.Lang;

/**
 * GraphDB upload helper.
 *
 * Important design rule: this class does not change the ALLMAH model and does
 * not rewrite the Turtle export. The local TTL remains the full, consistent
 * representation. GraphDB is only an additional persistence target.
 */
public class GraphDBWriter {

    private final GraphDBConfig config;

    public GraphDBWriter(GraphDBConfig config) {
        this.config = config;
    }

    public void testConnection() throws IOException {
        String endpoint = config.getRepositoryEndpoint() + "/size";
        HttpURLConnection conn = openConnection(endpoint, "GET", null);
        int code = conn.getResponseCode();
        if (code < 200 || code >= 300) {
            throw new IOException("GraphDB connection failed: HTTP "
                    + code + " " + readResponse(conn));
        }
        conn.disconnect();
    }

    /**
     * Uploads a complete Turtle file into one named graph.
     * The graph is replaced atomically from the GUI perspective: first clear,
     * then PUT the current complete TTL.
     */
    public UploadReport uploadTurtleFile(File turtleFile, String graphUri)
            throws IOException {

        if (turtleFile == null || !turtleFile.isFile()) {
            throw new IOException("Turtle file not found: " + turtleFile);
        }

        UploadReport report = inspectGlobalResources(turtleFile);

        try {
            report.setExistingReadingUris(findExistingUris(report.getReadingUris()));
            report.setExistingGlyphUris(findExistingUris(report.getGlyphUris()));
        } catch (Exception ex) {
            report.setInspectionWarning(
                    appendWarning(report.getInspectionWarning(),
                            "Could not check existing readings in GraphDB: " + ex.getMessage())
            );
        }

        clearNamedGraph(graphUri);
        putNamedGraph(turtleFile, graphUri);

        report.setGraphUri(graphUri);
        return report;
    }

    private void clearNamedGraph(String graphUri) throws IOException {
        String endpoint = config.getRdfGraphsServiceEndpoint()
                + "?graph=" + URLEncoder.encode(graphUri, "UTF-8");
        HttpURLConnection conn = openConnection(endpoint, "DELETE", null);
        int code = conn.getResponseCode();
        // DELETE may return 204, 200, or 404 when the graph was not present.
        if (code != 200 && code != 202 && code != 204 && code != 404) {
            throw new IOException("Could not clear named graph: HTTP "
                    + code + " " + readResponse(conn));
        }
        conn.disconnect();
    }

    private void putNamedGraph(File turtleFile, String graphUri)
            throws IOException {
        String endpoint = config.getRdfGraphsServiceEndpoint()
                + "?graph=" + URLEncoder.encode(graphUri, "UTF-8");
        HttpURLConnection conn = openConnection(endpoint, "PUT", "text/turtle");
        conn.setDoOutput(true);

        try (OutputStream out = conn.getOutputStream();
             InputStream in = new FileInputStream(turtleFile)) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) >= 0) {
                out.write(buffer, 0, read);
            }
        }

        int code = conn.getResponseCode();
        if (code < 200 || code >= 300) {
            throw new IOException("Could not upload named graph: HTTP "
                    + code + " " + readResponse(conn));
        }
        conn.disconnect();
    }

    /**
     * Preflight only: collects globally reused Reading URIs and possible Glyph
     * resources from the TTL. This does not alter the TTL. It is deliberately
     * conservative because the current export still keeps GlyphElement nodes as
     * document-local structures, while Readings already have global URIs.
     */
    private UploadReport inspectGlobalResources(File turtleFile) {
        UploadReport report = new UploadReport();
        try {
            Model model = RDFDataMgr.loadModel(
                    turtleFile.getAbsolutePath(),
                    Lang.TURTLE
            );

            Set<String> readingUris = new LinkedHashSet<String>();
            Set<String> glyphUris = new LinkedHashSet<String>();
            ResIterator subjects = model.listSubjects();
            while (subjects.hasNext()) {
                Resource r = subjects.nextResource();
                if (r.isURIResource()) {
                    String uri = r.getURI();
                    if (uri.contains("/reading-")
                            || uri.contains("#reading-")) {
                        readingUris.add(uri);
                    }
                    if (uri.contains("/glyph-")
                            || uri.contains("#glyph-")) {
                        glyphUris.add(uri);
                    }
                }
            }
            subjects.close();

            report.setReadingUris(new ArrayList<String>(readingUris));
            report.setGlyphUris(new ArrayList<String>(glyphUris));
        } catch (Exception ex) {
            report.setInspectionWarning(ex.getMessage());
        }
        return report;
    }


    private List<String> findExistingUris(List<String> uris) throws IOException {
        List<String> existing = new ArrayList<String>();
        if (uris == null || uris.isEmpty()) {
            return existing;
        }

        int chunkSize = 80;
        for (int start = 0; start < uris.size(); start += chunkSize) {
            int end = Math.min(start + chunkSize, uris.size());
            StringBuilder query = new StringBuilder();
            query.append("SELECT DISTINCT ?uri WHERE {\n");
            query.append("  VALUES ?uri {\n");
            for (int i = start; i < end; i++) {
                query.append("    <").append(uris.get(i)).append(">\n");
            }
            query.append("  }\n");
            query.append("  GRAPH ?g { ?uri ?p ?o . }\n");
            query.append("}");

            String response = postSparqlSelect(query.toString());
            for (int i = start; i < end; i++) {
                String uri = uris.get(i);
                if (response.contains(uri)) {
                    existing.add(uri);
                }
            }
        }
        return existing;
    }

    private String postSparqlSelect(String query) throws IOException {
        HttpURLConnection conn = openConnection(
                config.getRepositoryEndpoint(),
                "POST",
                "application/sparql-query"
        );
        conn.setRequestProperty("Accept", "application/sparql-results+json");
        conn.setDoOutput(true);
        try (OutputStream out = conn.getOutputStream()) {
            out.write(query.getBytes(StandardCharsets.UTF_8));
        }
        int code = conn.getResponseCode();
        if (code < 200 || code >= 300) {
            throw new IOException("SPARQL check failed: HTTP "
                    + code + " " + readResponse(conn));
        }
        String response = readResponse(conn);
        conn.disconnect();
        return response;
    }

    private String appendWarning(String oldWarning, String newWarning) {
        if (oldWarning == null || oldWarning.isEmpty()) {
            return newWarning;
        }
        return oldWarning + "; " + newWarning;
    }

    private HttpURLConnection openConnection(
            String endpoint,
            String method,
            String contentType
    ) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setConnectTimeout(20000);
        conn.setReadTimeout(60000);
        conn.setRequestProperty("Accept", "application/sparql-results+json, text/plain, */*");
        if (contentType != null) {
            conn.setRequestProperty("Content-Type", contentType + "; charset=UTF-8");
        }
        if (config.hasCredentials()) {
            String token = config.getUsername() + ":" + config.getPassword();
            String encoded = Base64.getEncoder().encodeToString(
                    token.getBytes(StandardCharsets.UTF_8)
            );
            conn.setRequestProperty("Authorization", "Basic " + encoded);
        }
        return conn;
    }

    private String readResponse(HttpURLConnection conn) {
        InputStream stream;
        try {
            stream = conn.getErrorStream();
            if (stream == null) {
                stream = conn.getInputStream();
            }
            if (stream == null) {
                return "";
            }
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8)
            );
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            return sb.toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static class UploadReport {
        private String graphUri;
        private List<String> readingUris = new ArrayList<String>();
        private List<String> existingReadingUris = new ArrayList<String>();
        private List<String> glyphUris = new ArrayList<String>();
        private List<String> existingGlyphUris = new ArrayList<String>();
        private String inspectionWarning;

        public String getGraphUri() {
            return graphUri;
        }

        public void setGraphUri(String graphUri) {
            this.graphUri = graphUri;
        }

        public List<String> getReadingUris() {
            return readingUris;
        }

        public void setReadingUris(List<String> readingUris) {
            this.readingUris = readingUris;
        }

        public List<String> getExistingReadingUris() {
            return existingReadingUris;
        }

        public void setExistingReadingUris(List<String> existingReadingUris) {
            this.existingReadingUris = existingReadingUris;
        }

        public List<String> getGlyphUris() {
            return glyphUris;
        }

        public void setGlyphUris(List<String> glyphUris) {
            this.glyphUris = glyphUris;
        }

        public List<String> getExistingGlyphUris() {
            return existingGlyphUris;
        }

        public void setExistingGlyphUris(List<String> existingGlyphUris) {
            this.existingGlyphUris = existingGlyphUris;
        }

        public int getMissingReadingCount() {
            if (readingUris == null) {
                return 0;
            }
            if (existingReadingUris == null) {
                return readingUris.size();
            }
            return readingUris.size() - existingReadingUris.size();
        }

        public int getMissingGlyphCount() {
            if (glyphUris == null) {
                return 0;
            }
            if (existingGlyphUris == null) {
                return glyphUris.size();
            }
            return glyphUris.size() - existingGlyphUris.size();
        }

        public String getInspectionWarning() {
            return inspectionWarning;
        }

        public void setInspectionWarning(String inspectionWarning) {
            this.inspectionWarning = inspectionWarning;
        }
    }
}