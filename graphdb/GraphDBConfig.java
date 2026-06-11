package allmahVer4.graphdb;

/**
 * Minimal GraphDB connection configuration for ALLMAH.
 * The test repository is pre-filled to reduce steps during development.
 */
public class GraphDBConfig {

    private String serverUrl;
    private String repositoryId;
    private String username;
    private String password;
    private String documentGraphPrefix;

    public GraphDBConfig() {
        this.serverUrl = "https://classicmayan.org/graphdb";
        this.repositoryId = "allmah-test";
        this.documentGraphPrefix = "https://classicmayan.org/allmah/document/";
    }

    public static GraphDBConfig defaultTestConfig() {
        return new GraphDBConfig();
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        if (serverUrl == null || serverUrl.trim().isEmpty()) {
            return;
        }
        this.serverUrl = stripTrailingSlash(serverUrl.trim());
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        if (repositoryId == null || repositoryId.trim().isEmpty()) {
            return;
        }
        this.repositoryId = repositoryId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocumentGraphPrefix() {
        return documentGraphPrefix;
    }

    public void setDocumentGraphPrefix(String documentGraphPrefix) {
        if (documentGraphPrefix == null || documentGraphPrefix.trim().isEmpty()) {
            return;
        }
        this.documentGraphPrefix = documentGraphPrefix.trim();
    }

    public String getRepositoryEndpoint() {
        return stripTrailingSlash(serverUrl) + "/repositories/" + repositoryId;
    }

    public String getRdfGraphsServiceEndpoint() {
        return getRepositoryEndpoint() + "/rdf-graphs/service";
    }

    public String documentGraphUri(String documentId) {
        String safe = sanitizeForUri(documentId);
        return documentGraphPrefix + safe;
    }

    public boolean hasCredentials() {
        return username != null && !username.trim().isEmpty()
                && password != null && !password.isEmpty();
    }

    private static String stripTrailingSlash(String value) {
        if (value == null) {
            return "";
        }
        while (value.endsWith("/")) {
            value = value.substring(0, value.length() - 1);
        }
        return value;
    }

    public static String sanitizeForUri(String value) {
        if (value == null || value.trim().isEmpty()) {
            return "unnamed-document";
        }
        String safe = value.trim()
                .replace('\u00A0', ' ')
                .replaceAll("\\s+", "_")
                .replaceAll("[^A-Za-z0-9._-]", "-");
        while (safe.contains("--")) {
            safe = safe.replace("--", "-");
        }
        return safe;
    }
}