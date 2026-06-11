package allmahVer4.rdf;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GlyphTypeMapper {

    private static final Pattern SUFFIX =
            Pattern.compile(".*?([a-zA-Z]+)$");

    private GlyphTypeMapper() {}

    public static String toGlyphTypeIndividual(String catalogNumber) {
        if (catalogNumber == null || catalogNumber.isBlank()) {
            return null;
        }

        Matcher matcher = SUFFIX.matcher(catalogNumber.trim());
        if (!matcher.matches()) {
            return null;
        }

        String suffix = matcher.group(1).toLowerCase(Locale.ROOT);

        switch (suffix) {
            case "st": return "allmah:ST";
            case "bt": return "allmah:BT";
            case "bh": return "allmah:BH";
            case "bb": return "allmah:BB";
            case "tf": return "allmah:TF";
            case "vc": return "allmah:VC";
            case "bv": return "allmah:BV";
            default: return null;
        }
    }
}