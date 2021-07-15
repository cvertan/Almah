package allmahVer4;

import java.util.ArrayList;
import java.awt.Color;
public class GraphNT2Node extends GlyphNode{
	private ArrayList<GlyphNode> children;
	private NT2Element nt2;
	private Color c;
	public GraphNT2Node(NT2Element n2, GlyphNode p) {
		super(n2.getLabel(),p,"nt2");
		this.nt2=n2;
		children=new ArrayList<GlyphNode>();
	}
	public GraphNT2Node(Color c) {
		super("Component",null,"complex");
		this.c=c;
		this.nt2=null;
		children=new ArrayList<GlyphNode>();
	}
	
		
	public String toString() {
		return this.getLabel();
	}
	public NT2Element getNT2Element() {
		return nt2;
	}
	public void setNT1Element(NT2Element el) {
		nt2=el;
	}
	public ArrayList<GlyphNode> getChildrenNodes(){
		return children;
	}
	
	public void setChildren(ArrayList<GlyphNode> g) {
		for (int i=0;i<g.size();i++)
			children.add(g.get(i));
	}

 public Color getColor() {
	 return c;
 }

}
