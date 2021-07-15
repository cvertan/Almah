package allmahVer4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class NumTranslit1 extends TreeNode {
	String id;
	ArrayList<NT1Element> elem;
	ArrayList<String> nt2;
	AllmahGUI tag;
	private Graph<GlyphNode,OperatorLink> g ;
	private Graph<GlyphNode,OperatorLink> t; 
	public NumTranslit1(String id,AllmahGUI tag) {
		super(1);
		this.id=id;
		elem=new ArrayList<NT1Element>();
		nt2=new ArrayList<String>();
		this.tag=tag;
	}
	public boolean multipleReadings() {
		boolean found=false;
		int i=0;
		while((i<elem.size())&&(!found)) {
			if (elem.get(i).getSelectedReadings().size()>1) found=true;
			i=i+1;
		}
		return found;
	}
	public String calculateLabel() {
		String aux=""; int n=0;
		ArrayList<Object> o=tag.hb.get((tag.mhb.get(this.getParent())).intValue()).getTeile();
		for (int i=0;i<o.size();i++) {
			//System.out.println("Object class Name "+ o.getClass().getName());
			if(o.get(i).getClass().getName().contains("Glyph")) {
				aux=aux+elem.get(n).getLabel();n=n+1;
			}
			else if(o.get(i).getClass().getName().contains("Operator")) {
				aux=aux+((OperatorLink)o.get(i)).toString();
			}
			else aux=aux+o.get(i).toString();
		}
		return aux;
	}
	

	public void generateNT2() {
		ArrayList<NT2Element> n2=new ArrayList<NT2Element>();
		for (int i=0;i<elem.size();i++) {
			n2.add(new NT2Element(elem.get(i)));
		}
		NumTranslit2 nt2=new NumTranslit2(id+">"+"NT2_0",tag,"main");
		nt2.setNT2Elements(n2);
		
		tag.nt2.add(nt2);
		
		tag.mnt2.put(nt2.getId(),new Integer(tag.nt2.size()-1));
		this.nt2.add(nt2.getId());
		nt2.generateGT1s();
	}
	public NumTranslit1 copy() {
		NumTranslit1 aux=new NumTranslit1(id,tag);
		for (int i=0;i<this.elem.size();i++) {
			aux.getElements().add(elem.get(i).copy());
		}
		return aux;
	}
	public String getParent() {
		return id.substring(0,id.lastIndexOf(">"));
	}
	public void setId(String s) {
		this.id=s;
	}

	
	public String getId() {
		return id;
	}
	public AllmahGUI getTagInterf() {
		return tag;
	}
	public ArrayList<String> getNT2(){
		return nt2;
	}

	public  ArrayList<TreeNode> listNodes(){
		 ArrayList <TreeNode> t=new ArrayList<TreeNode>();
		 if(nt2.size()>0) {
		 for(int i=0;i<nt2.size();i++) {
		       t.add(tag.nt2.get((tag.mnt2.get(nt2.get(i)).intValue())));
		 }
		 }
		 return t;
	 }
	public ArrayList<NT1Element> getElements(){
		return elem;
	}
	public Graph<GlyphNode,OperatorLink> getEntireGraphNT1(){
		
		return g;
	}
	public Graph<GlyphNode,OperatorLink> getTreeGraphNT1(){
		return t;
	}
	public void createGraphStructure() {
		String label=calculateLabel();
		StringTokenizer st=new StringTokenizer(label,SpecialSymbols.Operators_L13,true);
		String elem="";
		g = new SparseGraph<GlyphNode,OperatorLink>();
		 t = new SparseGraph<GlyphNode,OperatorLink>();
		 ArrayList <GraphNT1Node>elemNodes=new ArrayList<GraphNT1Node>();
		ArrayList <OperatorLink> operators=new ArrayList<OperatorLink>();
		String currentOp="";
		GlyphNode root=new SegmentNode(label,null,"root");
		GlyphNode currentParent=root;
		t.addVertex(currentParent);
		GlyphNode prevNode=null;
		GlyphNode node;
		int noalt=0; int nnt1=0;
		while (st.hasMoreTokens()) {
		   elem=st.nextToken();
		   if ( Arrays.stream(SpecialSymbols.OperatorsSet_L13).anyMatch(elem::equals))
			{System.out.println("Operator "+elem); operators.add(new OperatorLink(elem,1));}
		   else { System.out.println("Glyph "+elem);
		               if ((elem.indexOf("[")<0)&&(elem.indexOf("]")<0)){
		       
		            	
		            		node=new GraphNT1Node(this.elem.get(nnt1),currentParent);   elemNodes.add((GraphNT1Node)node);    	
		            		nnt1=nnt1+1;
		            	
		           
		            ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
		               g.addEdge(new OperatorLink("  ",0), currentParent, node, EdgeType.DIRECTED); 
		              t.addEdge(new OperatorLink("  ",0),currentParent,node, EdgeType.DIRECTED); 
		              
		               if (prevNode !=null) {
			                         g.addEdge(operators.get(operators.size()-1),prevNode, node, EdgeType.DIRECTED); 
			                        // System.out.println("Added " + glyphElements.get(glyphElements.size()-1).toString()+currentOp+ elem );
		               }
			                         prevNode=node;
		                   
		               }
		               //
		               else if (elem.indexOf("[")>=0) {
		            	   String aux=elem; int n=1;
		            	   while(aux.indexOf("[")==0) {
		            		    node=new SegmentNode("[     ]",currentParent,"segm");;
		            		    ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
		            		   if((prevNode!=null)&&(n==1))  {
		            			   g.addEdge(operators.get(operators.size()-1),prevNode, node, EdgeType.DIRECTED); 
		            			  
		            		   }
		            		   prevNode=node;
		            		   g.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            	  t.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            		
		            		  
		            		   currentParent=node;
		            		   aux=aux.substring(1);n=n+1;
		               }
		            	  
		            		   node=new GraphNT1Node(this.elem.get(nnt1),currentParent);   elemNodes.add((GraphNT1Node)node);    	
			            		nnt1=nnt1+1;
	          	    ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
		            	    prevNode=node;
		            	    g.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            	 t.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            	   	   
		            	 
		               }
		               else if(elem.indexOf("]")>=0) {
		            	   String aux=elem;
		            	  
		            		   node=new GraphNT1Node(this.elem.get(nnt1),currentParent);   elemNodes.add((GraphNT1Node)node);    	
			            		nnt1=nnt1+1;
		            	   ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
		            	   g.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED);
		             t.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED);
		           	            	 
		            	   g.addEdge(operators.get(operators.size()-1),prevNode, node, EdgeType.DIRECTED); 
		            	   
		            	   while(aux.indexOf("]")>0) {
		            		   prevNode=currentParent;  node=currentParent;;currentParent=node.getParent();
		            		  
		            		  if(aux.substring(aux.indexOf("]")).length()>1) {
		            		  aux=aux.substring(0,aux.indexOf("]"))+aux.substring(aux.indexOf("]")+1);
		            		   
		            		  }
		            		  else  aux=aux.substring(0,aux.indexOf("]"));
		            	   }
		               }	
		   }
		}
   
	}

}
