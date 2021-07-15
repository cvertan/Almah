package allmahVer4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import java.util.*;
public class GraphTranslit2 extends TreeNode {

	String id;
	DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> g;
	ArrayList<GraphGT2Node> elem;
	ArrayList<String> phonTr;
	AllmahGUI tag;
	String typ;
	String pathImage;
	int novariants;
	//Graph<GlyphNode,OperatorLink> g;
	private String label;
	public GraphTranslit2(String id,String t,AllmahGUI tag) {
		super(4);
		this.id=id;
		elem=new ArrayList<GraphGT2Node>();
		phonTr=new ArrayList<String>();
		this.tag=tag;
		pathImage="";
		typ=t;
		g=new DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink>();
		label="";
		novariants=0;
	}
	public String getTyp() {
		return typ;
	}
	public int getNoVariants() {
		return novariants;
	}
	public void setPathImage(String s) {
		pathImage=s;
	}
	public String getPathImage() {
		return pathImage;
	}
	public void setNoVariants(int n) {
		novariants=n;
	}
	public void setElements(ArrayList<GraphGT2Node> a) {
		elem.clear();
		for(int i=0;i<a.size();i++)
			elem.add(a.get(i));
	}
	public GraphTranslit2 copy(String t) {
		GraphTranslit2  copyTranslit2=new GraphTranslit2(this.getId(),t,tag);
		copyTranslit2.setLabel(this.label);
		ArrayList<GraphGT2Node> newelem=new ArrayList<GraphGT2Node>();
		for(int i=0;i<elem.size();i++) 
			newelem.add(elem.get(i).copy());
 
			copyTranslit2.setElements(newelem);
		for (int i=0;i<elem.size();i++)
			copyTranslit2.getGraphGT2().addVertex(newelem.get(i));
		for (int i=0;i<elem.size()-1;i++) {
			OperatorLink o=g.findEdge(elem.get(i),elem.get(i+1));
			if(o!=null)
				copyTranslit2.getGraphGT2().addEdge(o.copy(), newelem.get(i),newelem.get(i+1));
		}
	
		      
		return copyTranslit2;
		
	}
	public void setGraph(DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> g) {
		this.g=g;
	}
	public void setLabel(String s) {
		this.label=s;
	}
	public String getParent() {
		String s=id.substring(0,id.lastIndexOf(">"));
		return s ;
		}
	
	public void setId(String s) {
		this.id=s;
	}
	public ArrayList<GraphGT2Node> getElements(){
		return elem;
	}
	
public String calculateLabel() {
    label="";
    //Iterator<GraphGT2Node> it2= g.getVertices().iterator();
   //System.out.println("Graph Translit2 "+g.getVertices().size() );
    //while(it2.hasNext()) {
     for(int i=0;i<elem.size();i++) {
    	//GraphGT2Node gaux=it2.next();
    	 GraphGT2Node gaux=elem.get(i);
    	System.out.println("GT2 Element added "+ gaux.getGT2Element().getLabel());
    	if(gaux.getGT2Element().getParentGT1().getParent().getComponentElement()!=null) {
    		//Color cg=it2.next().getGT2Element().getParenComponent().getcolor();
    		Color cg=gaux.getGT2Element().getParentGT1().getParent().getComponentElement().getcolor();
			String hex=String.format("#%02x%02x%02x", cg.getRed(), cg.getGreen(), cg.getBlue());
			//label=label+"<font color=\""+hex+"\">"+gaux.getGT2Element().getLabel()+"</font>"; 
			label=label+"<font color=\""+hex+"\">"+gaux.getGT2Element().getParentGT1().getParent().getComponentElement().getLabel1()+"</font>"; 
    	}
    	else label=label+gaux.getGT2Element().getLabel();
    	if  (g.getOutEdges(gaux).size()>0) {
    		System.out.println("Number of out Edges for " +gaux.getGT2Element().getLabel()+" "+g.getOutEdges(gaux).size() );
    		Iterator<OperatorLink> ito=g.getOutEdges(gaux).iterator();
    		while(ito.hasNext()) {
    			
    			
    		     label=label+ito.next().toString();
    		}
    	}
    	
    }
    System.out.println("Graph Translit 2 "+ label);
	return label;
}
	public String getId() {
		return id;
	}
	public DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> getGraphGT2(){
		return g;
	}
	
public ArrayList<String> getPhonemicId(){
	return phonTr;
}
	public AllmahGUI getTagInterf() {
		return tag;
	}
	public  ArrayList<TreeNode> listNodes(){
		 ArrayList <TreeNode> t=new ArrayList<TreeNode>();
		 if(phonTr.size()>0) {
			 for(int i=0;i<phonTr.size();i++) {
				 t.add(tag.pt.get(tag.mpt.get(phonTr.get(i)).intValue()));
			 }
			 }
		 return t;
	 }
}
