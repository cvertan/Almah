package allmahVer4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class NumTranslit2 extends TreeNode {
	String id;
	ArrayList<NT2Element> elem;
	private Graph<GlyphNode,OperatorLink> g ;
	private Graph<GlyphNode,OperatorLink> t; 
	ArrayList<String> gt1;
	AllmahGUI tag;
	String typ;
	public NumTranslit2(String id,AllmahGUI tag, String t) {
		super(2);
		this.id=id;
		this.typ=t;
		elem=new ArrayList<NT2Element>();
		
		gt1=new ArrayList<String>();
		this.tag=tag;
	}
	public String getTyp() {
		return typ;
	}
	
	public void setTyp(String t) {
		typ=t;
	}
	public NumTranslit2 generateVariant() {
		NumTranslit2 n2=this.copy();
		this.setTyp("var");
		int x=tag.nt1.get((tag.mnt1.get(this.id)).intValue()).getNT2().size();
		n2.setId(id.substring(0,id.indexOf("_"))+x);
		return n2;
	}
	public String calculateLabel() {
		String aux=""; int n=0;
		NumTranslit1 n1=tag.nt1.get((tag.mnt1.get(this.getParent())).intValue());
		ArrayList<Object> o=tag.hb.get((tag.mhb.get(n1.getParent())).intValue()).getTeile();
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
	

	public NumTranslit2 copy() {
		NumTranslit2 aux=new NumTranslit2(id,tag,typ);
		
		for (int i=0;i<this.elem.size();i++) {
			aux.getElements().add(elem.get(i).copy());
		}
		aux.createGraphStructure();
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
	

	public  ArrayList<TreeNode> listNodes(){
		 ArrayList <TreeNode> t=new ArrayList<TreeNode>();
		 if(gt1.size()>0) {
		 for(int i=0;i<gt1.size();i++) {
			 t.add(tag.gt1.get((tag.mgt1.get(gt1.get(i)).intValue())));
		 }
		 }
		 return t;
	 }
	public ArrayList<NT2Element> getElements(){
		return elem;
	}
	public void setNT2Elements(ArrayList<NT2Element>n2) {
		for (int i=0;i<n2.size();i++ ) {
			elem.add(n2.get(i));
		}
	}
	//
	public void generateGT1s(String s) {
		

		ArrayList<GraphTranslit1> gt1=new ArrayList<GraphTranslit1>();
		int nr=0;
	     GraphTranslit1 n1 = new GraphTranslit1(id+">"+"GT1_0",tag);
		gt1.add(n1);
		String nt1=(
				tag.nt1.get((tag.mnt1.get(this.getParent())).intValue())).getParent();
		NumTranslit1 nt1e=tag.nt1.get((tag.mnt1.get(this.getParent())).intValue());
		HieroglyphenBlock b= tag.hb.get((tag.mhb.get(nt1)).intValue());
		ArrayList<ArrayList<Reading>> reads=new ArrayList<ArrayList<Reading>>();
		for(int i=0;i<elem.size();i++) {
			/*if (b.getElements().get(i).getReadings().isEmpty()) 
				if(!b.getElements().get(i).getAlternatives().isEmpty()) reads.add(b.getElements().get(i).getAlternatives().get(nt1e.getElements().get(i).getLabel()));
				else {
					ArrayList<Reading> rempty=new ArrayList<Reading>();
					rempty.add(new Reading(nt1e.getElements().get(i).getLabel(),0,""));
					reads.add(rempty);
				}
			else reads.add(b.getElements().get(i).getReadings());*/
			if (!elem.get(i).getReadings(s).isEmpty()) {
				
			reads.add(elem.get(i).getReadings(s));
			}
			else {
				System.out.println("!!!!!!1 " + elem.get(i).getLabel()+ " with parent " + elem.get(i).getParent().getLabel()+ " has  no reading");
				ArrayList<Reading> fakeRead= new ArrayList<Reading>();
				fakeRead.add(new Reading(elem.get(i).getLabel()));
				reads.add(fakeRead);
			}
		}
		for (int i=0;i<reads.size();i++) {
			int na=0;
				ArrayList<GT1Element> elx=new ArrayList<GT1Element>();
				for (int j=0;j<reads.get(i).size();j++) 
				    elx.add(new GT1Element(elem.get(i),reads.get(i).get(j)));
				ArrayList<GraphTranslit1> gt1aux=new ArrayList<GraphTranslit1>();
		        for(int l=0;l<elx.size(); l++) {
		        	//System.out.println("NT1 Elem "+ elx.get(l).getLabel());
		        	if(gt1.isEmpty()) {
		        		GraphTranslit1 nx=new GraphTranslit1(id+">"+"GT1_0",tag);
		        		nx.getElements().add(elx.get(l));
		        	//	System.out.println(nx.calculateLabel());
		        		gt1aux.add(nx);
		        	}
		        	else {
		        	for(int k=0;k<gt1.size();k++) {
		        		nr=nr+1;
		        		GraphTranslit1 nx=gt1.get(k).copy();
		        		nx.setId(id+">"+"NT1_"+nr);
		        		nx.getElements().add(elx.get(l));
		        	//	System.out.println(nx.calculateLabel());
		        		gt1aux.add(nx);
		        	}
		        }
		        }
		        
		         gt1.clear();
		         for (int k=0;k<gt1aux.size();k++) {
		        	 gt1.add(gt1aux.get(k));
		         }     
		       
		
		}
		for (int i=0;i<gt1.size();i++) {
			gt1.get(i).createTranslit2();
			this.gt1.add(gt1.get(i).getId());
			
			tag.gt1.add(gt1.get(i));
			tag.mgt1.put(gt1.get(i).getId(),new Integer(tag.gt1.size()-1));
		}
	
	}
	
	
	//
	
	
	
	public void generateGT1s() {
		

		ArrayList<GraphTranslit1> gt1=new ArrayList<GraphTranslit1>();
		int nr=0;
	     GraphTranslit1 n1 = new GraphTranslit1(id+">"+"GT1_0",tag);
		gt1.add(n1);
		String nt1=(tag.nt1.get((tag.mnt1.get(this.getParent())).intValue())).getParent();
		NumTranslit1 nt1e=tag.nt1.get((tag.mnt1.get(this.getParent())).intValue());
		HieroglyphenBlock b= tag.hb.get((tag.mhb.get(nt1)).intValue());
		ArrayList<ArrayList<Reading>> reads=new ArrayList<ArrayList<Reading>>();
		for(int i=0;i<elem.size();i++) {
			/*if (b.getElements().get(i).getReadings().isEmpty()) 
				if(!b.getElements().get(i).getAlternatives().isEmpty()) reads.add(b.getElements().get(i).getAlternatives().get(nt1e.getElements().get(i).getLabel()));
				else {
					ArrayList<Reading> rempty=new ArrayList<Reading>();
					rempty.add(new Reading(nt1e.getElements().get(i).getLabel(),0,""));
					reads.add(rempty);
				}
			else reads.add(b.getElements().get(i).getReadings());*/
			if (!elem.get(i).getReadings().isEmpty())
			reads.add(elem.get(i).getReadings());
			else {
				System.out.println("!!!!!!2 " + elem.get(i).getLabel()+ " with parent " + elem.get(i).getParent().getLabel()+ " has  no reading");
				ArrayList<Reading> fakeRead= new ArrayList<Reading>();
				fakeRead.add(new Reading(elem.get(i).getLabel()));
				reads.add(fakeRead);
			}
		}
		for (int i=0;i<reads.size();i++) {
			int na=0;
				ArrayList<GT1Element> elx=new ArrayList<GT1Element>();
				for (int j=0;j<reads.get(i).size();j++) 
				    elx.add(new GT1Element(elem.get(i),reads.get(i).get(j)));
				ArrayList<GraphTranslit1> gt1aux=new ArrayList<GraphTranslit1>();
		        for(int l=0;l<elx.size(); l++) {
		        	//System.out.println("NT1 Elem "+ elx.get(l).getLabel());
		        	if(gt1.isEmpty()) {
		        		GraphTranslit1 nx=new GraphTranslit1(id+">"+"GT1_0",tag);
		        		nx.getElements().add(elx.get(l));
		        	//	System.out.println(nx.calculateLabel());
		        		gt1aux.add(nx);
		        	}
		        	else {
		        	for(int k=0;k<gt1.size();k++) {
		        		nr=nr+1;
		        		GraphTranslit1 nx=gt1.get(k).copy();
		        		nx.setId(id+">"+"NT1_"+nr);
		        		nx.getElements().add(elx.get(l));
		        	//	System.out.println(nx.calculateLabel());
		        		gt1aux.add(nx);
		        	}
		        }
		        }
		        
		         gt1.clear();
		         for (int k=0;k<gt1aux.size();k++) {
		        	 gt1.add(gt1aux.get(k));
		         }     
		       
		
		}
		for (int i=0;i<gt1.size();i++) {
			gt1.get(i).createTranslit2();
			this.gt1.add(gt1.get(i).getId());
		    //gt1.get(i).generateGT2();
			tag.gt1.add(gt1.get(i));
			tag.mgt1.put(gt1.get(i).getId(),new Integer(tag.gt1.size()-1));
		}
	
	}
public ArrayList<String> getGT1(){
	return gt1;
}
public void createGraphStructure() {
String label=calculateLabel();
StringTokenizer st=new StringTokenizer(label,SpecialSymbols.Operators_L13,true);
String elem="";
g = new DirectedOrderedSparseMultigraph<GlyphNode,OperatorLink>();
 t = new DirectedOrderedSparseMultigraph<GlyphNode,OperatorLink>();
 ArrayList <GraphNT2Node>elemNodes=new ArrayList<GraphNT2Node>();
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
       
            	
            		node=new GraphNT2Node(this.elem.get(nnt1),currentParent);   elemNodes.add((GraphNT2Node)node);    	
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
            	  
            		   node=new GraphNT2Node(this.elem.get(nnt1),currentParent);   elemNodes.add((GraphNT2Node)node);    	
	            		nnt1=nnt1+1;
      	    ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
            	    prevNode=node;
            	    g.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
            	 t.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
            	   	   
            	 
               }
               else if(elem.indexOf("]")>=0) {
            	   String aux=elem;
            	  
            		   node=new GraphNT2Node(this.elem.get(nnt1),currentParent);   elemNodes.add((GraphNT2Node)node);    	
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
public Graph<GlyphNode,OperatorLink> getEntireGraphNT2(){
	
	return g;
}
public Graph<GlyphNode,OperatorLink> getTreeGraphNT2(){
	return t;
}
}
