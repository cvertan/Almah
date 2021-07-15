package allmahVer4;

import java.util.ArrayList;
import java.awt.Color;
public class GraphGT1Node extends GlyphNode{
	private ArrayList<GlyphNode> children;
	private GT1Element gt1;
	public GraphGT1Node(GT1Element g1, GlyphNode p) {
		super(g1.getLabel(),p,"gt1");
		this.gt1=g1;
		children=new ArrayList<GlyphNode>();
	
	}
	
	public String toString() {
		return this.getLabel();
	}
	public GT1Element getGT1Element() {
		return gt1;
	}
	public void setGT1Element(GT1Element el) {
		gt1=el;
	}
	public ArrayList<GlyphNode> getChildrenNodes(){
		return children;
	}
	
	public void setChildren(ArrayList<GlyphNode> g) {
		for (int i=0;i<g.size();i++)
			children.add(g.get(i));
	}


 }



