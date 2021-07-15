package allmahVer4;

import java.util.ArrayList;
import java.awt.Color;
public class GraphGT2Node extends GlyphNode{
	private ArrayList<GlyphNode> children;
	private GT2Element gt2;
	public GraphGT2Node(GT2Element g2, GlyphNode p) {
		super(g2.getLabel(),p,"gt2");
		this.gt2=g2;
		children=new ArrayList<GlyphNode>();
	
	}
	public GraphGT2Node() {
		super("NEW",null,"gt2");
		this.gt2=new GT2Element();
		children=new ArrayList<GlyphNode>();
	}
	public GraphGT2Node copy() {
		GT2Element gt2copy=this.getGT2Element().copy(this.getGT2Element().getLabel());
		GraphGT2Node graph2=new GraphGT2Node(gt2copy, this.getParent());
		return graph2;
	}
	public String toString() {
		String label1="";
		if(this.getGT2Element().getParentGT1()!=null) {
		if(this.getGT2Element().getParentGT1().getParent().getComponentElement()!=null) {
    		
			
			//label=label+"<font color=\""+hex+"\">"+gaux.getGT2Element().getLabel()+"</font>"; 
			label1=label1+this.getGT2Element().getParentGT1().getParent().getComponentElement().getLabel1(); 
		}
    	else label1=label1+this.getGT2Element().getLabel();
		}
		else label1=this.getLabel();
		return label1;
	}
	
	public String getLabel1() {
		String label1="";
		if(this.getGT2Element().getParentGT1().getParent().getComponentElement()!=null) {
    		//Color cg=it2.next().getGT2Element().getParenComponent().getcolor();
    		Color cg=this.getGT2Element().getParentGT1().getParent().getComponentElement().getcolor();
			String hex=String.format("#%02x%02x%02x", cg.getRed(), cg.getGreen(), cg.getBlue());
			//label=label+"<font color=\""+hex+"\">"+gaux.getGT2Element().getLabel()+"</font>"; 
			label1=label1+"<font color=\""+hex+"\">"+this.getGT2Element().getParentGT1().getParent().getComponentElement().getLabel1()+"</font>"; 
    	}
    	else label1=label1+this.getGT2Element().getLabel();
		return label1;
	}
	public GT2Element getGT2Element() {
		return gt2;
	}
	public void setGT2Element(GT2Element el) {
		gt2=el;
	}
	public ArrayList<GlyphNode> getChildrenNodes(){
		return children;
	}
	
	public void setChildren(ArrayList<GlyphNode> g) {
		for (int i=0;i<g.size();i++)
			children.add(g.get(i));
	}


 }


