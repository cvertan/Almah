package allmahVer4.rdf;

public final class OperatorMapper {

    private OperatorMapper() {}

    public static String toOperatorIndividual(String symbol) {
        if (symbol == null || symbol.trim().isEmpty()) {
            return "allmah:EmptyOperator";
        }

        switch (symbol) {
            case ":": return "allmah:ColonOperator";
            case ".": return "allmah:DotOperator";
            case "+": return "allmah:PlusOperator";
            case "°": return "allmah:DegreeOperator";
            case "┌": return "allmah:TopLeftOperator";
            case "┐": return "allmah:TopRightOperator";
            case "╝": return "allmah:BottomRightOperator";
            case "╚": return "allmah:BottomLeftOperator";
            case "╗": return "allmah:UpperRightCornerOperator";
            case "╔": return "allmah:UpperLeftCornerOperator";
            case "-": return "allmah:HyphenOperator";
            case "=": return "allmah:EqualsOperator";
            case "↑": return "allmah:UpArrowOperator";
            case "~": return "allmah:TildeOperator";
            case "[ ]": return "allmah:SquareBracketOperator";
            case "{ }": return "allmah:CurlyBracketOperator";
            case "| |": return "allmah:PipeOperator";
            case "<": return "allmah:LessThanOperator";
            case ">": return "allmah:GreaterThanOperator";
            case "C": return "allmah:ConsonantOperator";
            case "V": return "allmah:VowelOperator";
            case "H": return "allmah:HOperator";
            case "Ø": return "allmah:ZeroOperator";
            case "^": return "allmah:CaretOperator";
            default: return null;
        }
    }
}