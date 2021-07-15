package allmahVer4;

import java.util.ArrayList;

public class SegmentNode extends GlyphNode{

	private String label;
	private GlyphElementNode parent;
	private ArrayList<GlyphNode> children;
		public SegmentNode(String s, GlyphNode p,String t) {
			super(s,p,t);
			children=new ArrayList<GlyphNode>();
		}
		
	public String toString() {
		return this.getLabel();
	}
	public ArrayList<GlyphNode> getChildrenNodes(){
		return children;
	}
	
	public void setChildren(ArrayList<GlyphNode> g) {
		for (int i=0;i<g.size();i++)
			children.add(g.get(i));
	}

}
