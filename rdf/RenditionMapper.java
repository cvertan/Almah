package allmahVer4.rdf;

public final class RenditionMapper {

    private RenditionMapper() {}

    public static String toRenditionIndividual(String rend) {
        if (rend == null || rend.trim().isEmpty()) {
            return "allmah:UnknownRendition";
        }

        switch (rend) {
            case "above": return "allmah:Above";
            case "beneath": return "allmah:Beneath";
            case "attached_at_top_left": return "allmah:AttachedAtTopLeft";
            case "attached_at_top_right": return "allmah:AttachedAtTopRight";
            case "is_adjoined_to": return "allmah:IsAdjoinedTo";
            case "encloses": return "allmah:Encloses";
            case "infixed_in": return "allmah:InfixedIn";
            case "left_beside": return "allmah:LeftBeside";
            case "right_beside": return "allmah:RightBeside";
            case "conflated": return "allmah:Conflated";
            case "overlaps-with": return "allmah:OverlapsWith";
            case "inserted_in_bottom_left_corner": return "allmah:InsertedInBottomLeftCorner";
            case "is_bent_around": return "allmah:BentAround";
            default: return "allmah:UnknownRendition";
        }
    }
}