package allmahVer4.rdf;

public final class RdfUriFactory {

    public static final String ALLMAH =
            "https://classicmayan.org/allmah/ontology#";

    public static final String DATA =
            "https://classicmayan.org/allmah/data/";

    private RdfUriFactory() {}

    public static String allmah(String localName) {
        return "allmah:" + localName;
    }

    public static String ex(String localName) {
        return "ex:" + sanitize(localName);
    }

    public static String sanitize(String value) {
        if (value == null || value.isBlank()) {
            return "unknown";
        }

        return value
                .trim()
                .replaceAll("[^A-Za-z0-9_-]", "-")
                .replaceAll("-+", "-");
    }
    
    public static String fromInternalId(String internalId) {

        if (internalId == null || internalId.isBlank()) {
            return ex("unknown");
        }

        return ex(
                internalId
                    .replace(">", "-")
                    .replace(" ", "_")
            );
    }
}