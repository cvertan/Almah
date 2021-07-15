package allmahVer4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;

public class PhonemTranslit extends TreeNode{

	private String id;
	private DirectedOrderedSparseMultigraph<PhonemNode,OperatorLink> g;
	private ArrayList<PhonemNode> elem;
	private ArrayList<String> morphoTranscr;
	private AllmahGUI tag;
	private String typ;
	int novariants;
	private String pathImage;
	//Graph<GlyphNode,OperatorLink> g;
	private String label;
	public PhonemTranslit(String id,String t,AllmahGUI tag) {
		super(5);
		this.id=id;
		elem=new ArrayList<PhonemNode>();
		morphoTranscr=new ArrayList<String>();
		this.tag=tag;
		typ=t;
		pathImage="";
		g=new DirectedOrderedSparseMultigraph<PhonemNode,OperatorLink>();
		
		label="";
		novariants=0;
	}
	
	public void setPathImage(String s) {
		pathImage=s;
	}
	public String getPathImage() {
		return pathImage;
	}
	public void calculateGraph() {
		String parentid=getParent();
		GraphTranslit2 gt2=tag.gt2.get(tag.mgt2.get(parentid).intValue());
		for(int i=0;i<gt2.getElements().size();i++) {
			elem.add(new PhonemNode(gt2.getElements().get(i)));
		}
		for (int i=0;i<elem.size();i++)
			g.addVertex(elem.get(i));
		for (int i=0;i<elem.size()-1;i++) {
			OperatorLink o=gt2.getGraphGT2().findEdge((GraphGT2Node)elem.get(i).getParent(),(GraphGT2Node)elem.get(i+1).getParent());
			if(o!=null) {
				g.addEdge(new OperatorLink("E",6), elem.get(i),elem.get(i+1));
			}
		}
	
		
	}
	public String getTyp() {
		return typ;
	}
	public int getNoVariants() {
		return novariants;
	}
	public void setNoVariants(int n) {
		novariants=n;
	}
	public void setElements(ArrayList<PhonemNode> a) {
		elem=new ArrayList<PhonemNode>();
		for(int i=0;i<a.size();i++)
			elem.add(a.get(i));
	}
	public PhonemTranslit copy(String t) {
		PhonemTranslit  copyTranslit=new PhonemTranslit(this.getId(),t,tag);
		copyTranslit.setLabel(this.label);
		ArrayList<PhonemNode> newelem=new ArrayList<PhonemNode>();
		for(int i=0;i<elem.size();i++) 
			newelem.add(elem.get(i).copy());
 
			copyTranslit.setElements(newelem);
		for (int i=0;i<elem.size();i++)
			copyTranslit.getGraphPhonem().addVertex(newelem.get(i));
		for (int i=0;i<elem.size()-1;i++) {
			OperatorLink o=g.findEdge(elem.get(i),elem.get(i+1));
			if(o!=null)
				copyTranslit.getGraphPhonem().addEdge(o.copy(), newelem.get(i),newelem.get(i+1));
		}
	
		      
		return copyTranslit;
		
	}
	public void setGraph(DirectedOrderedSparseMultigraph<PhonemNode,OperatorLink> g) {
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
	public ArrayList<PhonemNode> getElements(){
		return elem;
	}
	
public String calculateLabel() {
    label="";
   
     for(int i=0;i<elem.size();i++) {

    	 PhonemNode gaux=elem.get(i);
    	if(((GraphGT2Node)gaux.getParent()).getGT2Element().getParentGT1().getParent().getComponentElement()!=null) {
    		Color cg=((GraphGT2Node)gaux.getParent()).getGT2Element().getParentGT1().getParent().getComponentElement().getcolor();
			String hex=String.format("#%02x%02x%02x", cg.getRed(), cg.getGreen(), cg.getBlue());
			//label=label+"<font color=\""+hex+"\">"+gaux.getGT2Element().getLabel()+"</font>"; 
			label=label+"<font color=\""+hex+"\">"+elem.get(i).toString()+"</font>"; 
    	}
    	else label=label+elem.get(i).toString();
    
    	if  (g.getOutEdges(gaux).size()>0) {
    		
    		Iterator<OperatorLink> ito=g.getOutEdges(gaux).iterator();
    		while(ito.hasNext()) {
    			
    			
    		     label=label+ito.next().toString();
    		}
    	}
    
    }
	return label;
}
	public String getId() {
		return id;
	}
	public DirectedOrderedSparseMultigraph<PhonemNode,OperatorLink> getGraphPhonem(){
		return g;
	}
	

	public AllmahGUI getTagInterf() {
		return tag;
	}
	public  ArrayList<TreeNode> listNodes(){
		 ArrayList <TreeNode> t=new ArrayList<TreeNode>();
		 if(morphoTranscr.size()>0) {
		 for(int i=0;i<morphoTranscr.size();i++) {
		//	 t.add(tag.num2.get(Integer.parseInt(tag.mapnum2.get(nt2.get(i)))));
		 }
		 }
		 return t;
	 }
	public ArrayList<String> getMorphoTranscr(){
		return morphoTranscr;
	}
}
