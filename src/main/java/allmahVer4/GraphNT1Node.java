package allmahVer4;

import java.util.ArrayList;

public class GraphNT1Node extends GlyphNode{
	private ArrayList<GlyphNode> children;
	private NT1Element nt1;
	public GraphNT1Node(NT1Element n1, GlyphNode p) {
		super(n1.getLabel(),p,"nt1");
		this.nt1=n1;
		children=new ArrayList<GlyphNode>();
	}
	
		
	public String toString() {
		return this.getLabel();
	}
	public NT1Element getNT1Element() {
		return nt1;
	}
	public void setNT1Element(NT1Element el) {
		nt1=el;
	}
	public ArrayList<GlyphNode> getChildrenNodes(){
		return children;
	}
	
	public void setChildren(ArrayList<GlyphNode> g) {
		for (int i=0;i<g.size();i++)
			children.add(g.get(i));
	}

 
}
