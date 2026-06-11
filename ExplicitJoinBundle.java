package allmahVer4;
import java.util.ArrayList;
public class ExplicitJoinBundle {

    HieroglyphenBlock block;
    NumTranslit1 n1;
    NumTranslit2 n2;

    ArrayList<ExplicitGTVariant> gtVariants =
            new ArrayList<ExplicitGTVariant>();

    ExplicitJoinBundle(
            HieroglyphenBlock block,
            NumTranslit1 n1,
            NumTranslit2 n2
    ) {
        this.block = block;
        this.n1 = n1;
        this.n2 = n2;
    }
}