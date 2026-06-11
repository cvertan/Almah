package allmahVer4;
public class ExplicitJoinPath {

    HieroglyphenBlock block;
    NumTranslit1 n1;
    NumTranslit2 n2;
    GraphTranslit1 g1;
    GraphTranslit2 g2;

    ExplicitJoinPath(
            HieroglyphenBlock block,
            NumTranslit1 n1,
            NumTranslit2 n2,
            GraphTranslit1 g1,
            GraphTranslit2 g2
    ) {
        this.block = block;
        this.n1 = n1;
        this.n2 = n2;
        this.g1 = g1;
        this.g2 = g2;
    }
}
