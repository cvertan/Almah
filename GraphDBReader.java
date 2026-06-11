package allmahVer4.graphdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
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
import java.util.List;

/**
 * Reads ALLMAH documents from GraphDB.
 *
 * Design rule: GraphDBReader does not reconstruct ALLMAH objects itself.
 * It lists available documents and downloads the selected named graph as a
 * temporary Turtle file. The existing AllmahRdfImporter then performs the
 * TTL -> GUI reconstruction.
 */
public class GraphDBReader {

    private static final String ALLMAH =
            "https://classicmayan.org/allmah/ontology#";

    private final GraphDBConfig config;

    public GraphDBReader(GraphDBConfig config) {
        this.config = config;
    }

    public List<GraphDBDocumentInfo> listDocuments() throws IOException {
        StringBuilder query = new StringBuilder();
        query.append("PREFIX allmah: <").append(ALLMAH).append(">\n");
        query.append("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n");
        query.append("SELECT DISTINCT ?g ?doc ?title ?label WHERE {\n");
        query.append("  GRAPH ?g {\n");
        query.append("    ?doc a allmah:Document .\n");
        query.append("    OPTIONAL { ?doc allmah:title ?title . }\n");
        query.append("    OPTIONAL { ?doc rdfs:label ?label . }\n");
        query.append("  }\n");
        query.append("}\n");
        query.append("ORDER BY ?title ?label ?doc\n");

        String tsv = postSparqlSelectTsv(query.toString());
        return parseDocumentListTsv(tsv);
    }

    public File loadDocumentGraphAsTempTurtle(GraphDBDocumentInfo document)
            throws IOException {
        if (document == null || document.getGraphUri() == null
                || document.getGraphUri().trim().isEmpty()) {
            throw new IOException("No GraphDB document graph selected.");
        }

        String endpoint = config.getRdfGraphsServiceEndpoint()
                + "?graph=" + URLEncoder.encode(document.getGraphUri(), "UTF-8");

        HttpURLConnection conn = openConnection(endpoint, "GET", null);
        conn.setRequestProperty("Accept", "text/turtle, application/x-turtle, */*");

        int code = conn.getResponseCode();
        if (code < 200 || code >= 300) {
            throw new IOException("Could not read GraphDB named graph: HTTP "
                    + code + " " + readResponse(conn));
        }

        File temp = File.createTempFile("allmah-graphdb-open-", ".ttl");
        temp.deleteOnExit();

        try (InputStream in = conn.getInputStream();
             OutputStream out = new FileOutputStream(temp)) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) >= 0) {
                out.write(buffer, 0, read);
            }
        }
        conn.disconnect();
        return temp;
    }

    private String postSparqlSelectTsv(String query) throws IOException {
        HttpURLConnection conn = openConnection(
                config.getRepositoryEndpoint(),
                "POST",
                "application/sparql-query"
        );
        conn.setRequestProperty("Accept", "text/tab-separated-values");
        conn.setDoOutput(true);
        try (OutputStream out = conn.getOutputStream()) {
            out.write(query.getBytes(StandardCharsets.UTF_8));
        }

        int code = conn.getResponseCode();
        if (code < 200 || code >= 300) {
            throw new IOException("GraphDB document list failed: HTTP "
                    + code + " " + readResponse(conn));
        }
        String response = readResponse(conn);
        conn.disconnect();
        return response;
    }

    private List<GraphDBDocumentInfo> parseDocumentListTsv(String tsv) {
        List<GraphDBDocumentInfo> result = new ArrayList<GraphDBDocumentInfo>();
        if (tsv == null || tsv.trim().isEmpty()) {
            return result;
        }

        String[] lines = tsv.split("\\r?\\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String[] cols = line.split("\\t", -1);
            String graphUri = cols.length > 0 ? cleanTsvValue(cols[0]) : "";
            String documentUri = cols.length > 1 ? cleanTsvValue(cols[1]) : "";
            String title = cols.length > 2 ? cleanTsvValue(cols[2]) : "";
            String label = cols.length > 3 ? cleanTsvValue(cols[3]) : "";

            if (title == null || title.trim().isEmpty()) {
                title = label;
            }
            if (title == null || title.trim().isEmpty()) {
                title = documentUri;
            }
            if (graphUri != null && !graphUri.trim().isEmpty()
                    && documentUri != null && !documentUri.trim().isEmpty()) {
                result.add(new GraphDBDocumentInfo(graphUri, documentUri, title));
            }
        }
        return result;
    }

    private String cleanTsvValue(String value) {
        if (value == null) {
            return "";
        }
        String s = value.trim();
        if (s.startsWith("<") && s.endsWith(">") && s.length() > 1) {
            return s.substring(1, s.length() - 1);
        }
        if (s.startsWith("\"") && s.length() >= 2) {
            int end = s.lastIndexOf('"');
            if (end > 0) {
                s = s.substring(1, end);
            }
        }
        return s.replace("\\t", "\t")
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\\"", "\"");
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
        conn.setRequestProperty("Accept", "text/plain, */*");
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

    public static class GraphDBDocumentInfo {
        private final String graphUri;
        private final String documentUri;
        private final String title;

        public GraphDBDocumentInfo(String graphUri, String documentUri, String title) {
            this.graphUri = graphUri;
            this.documentUri = documentUri;
            this.title = title;
        }

        public String getGraphUri() {
            return graphUri;
        }

        public String getDocumentUri() {
            return documentUri;
        }

        public String getTitle() {
            return title;
        }

        public String toString() {
            if (title == null || title.trim().isEmpty()) {
                return documentUri;
            }
            return title;
        }
    }
}