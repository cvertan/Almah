package allmahVer4;

import java.util.ArrayList;
import org.jsoup.*;
public class PhonemNode extends GlyphNode {

	private ArrayList<GlyphNode> children;
	private String label;
	public PhonemNode(GraphGT2Node g2) {
		super(Jsoup.parse(g2.getLabel1()).text(),g2,"phon");
		children=new ArrayList<GlyphNode>();
	if(	((GraphGT2Node)(this.getParent())).getGT2Element().getParentGT1().getParent().getComponentElement()!=null)	  
			label=((GraphGT2Node)(this.getParent())).getGT2Element().getParentGT1().getParent().getComponentElement().getLabel1();
	
	else
		  label=g2.getGT2Element().getLabel();
			
	}
	
	public String toString() {
	           return label;
	}
	public void setLabel(String s) {
		label=s;
	}
	public String getLabel() {
	return label;
	}
	public PhonemNode copy() {
		PhonemNode p=new PhonemNode((GraphGT2Node)this.getParent());
		p.setLabel(this.label);
		return p;
	}
}
