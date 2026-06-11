package allmahVer4.rdf;

public final class TurtleEscaper {

    private TurtleEscaper() {}

    public static String literal(String value) {
        if (value == null) return "\"\"";

        String escaped = value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");

        return "\"" + escaped + "\"";
    }
}