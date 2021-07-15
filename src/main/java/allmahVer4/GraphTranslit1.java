package allmahVer4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.StringTokenizer;
import org.jsoup.*;
import com.google.common.base.Functions;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.algorithms.shortestpath.MinimumSpanningForest2;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Tree;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.util.Iterator;
import java.util.HashMap;

public class GraphTranslit1 extends TreeNode {
	String id;
	ArrayList<GT1Element> elem;
	ArrayList<String> compon;
	ArrayList<String> gt2;
	String pathImage;
	String confidence;
	String confidenceGT1;
//	private Graph<GlyphNode,OperatorLink> g ;
	private Graph<GlyphNode,OperatorLink> t; 
	AllmahGUI tag;
	public GraphTranslit1(String id,AllmahGUI tag) {
		super(3);
		this.id=id;
		pathImage="";
		confidence="";
		confidenceGT1="";
		elem=new ArrayList<GT1Element>();
		compon=new ArrayList<String>();
		gt2=new ArrayList<String>();
		this.tag=tag;
	}
	public void setPathImage(String s) {
		pathImage=s;
	}
	public String getPathImage() {
		return pathImage;
	}
	public void setConfidence(String s) {
		confidence=s;
	}
	public void calculateConfidence() {
	confidence="";
		for (int i=0;i<elem.size();i++) {
			
			
				
				confidence=confidence+"<"+Jsoup.parse(elem.get(i).getLabel()).text()+"    "+elem.get(i).getConfidence()+">";
		}
		
	}
	public String getConfidence() {
		calculateConfidence();
		return confidence;
	}
	public void setConfidenceGT1(String s) {
		confidenceGT1=s;
	}
	public String getConfidenceGT1() {
		return confidenceGT1;
	}
	
	public String calculateLabel() {
		String aux=""; int n=0;
		NumTranslit2 n2=tag.nt2.get((tag.mnt2.get(this.getParent())).intValue());
		NumTranslit1 n1=tag.nt1.get((tag.mnt1.get(n2.getParent())).intValue());
		ArrayList<Object> o=tag.hb.get((tag.mhb.get(n1.getParent())).intValue()).getTeile();
		System.out.println("Parts "+o.size());
		System.out.println("GT1 Elem "+elem.size());
		for (int i=0;i<o.size();i++) {
			
			if(o.get(i).getClass().getName().contains("Glyph")) {
				System.out.println("Glyph "+n+" "+ elem.get(n).getLabel());
				aux=aux+elem.get(n).getLabel();
				confidence=confidence+"<"+elem.get(n).getConfidence()+">";
				n=n+1;
			}
			else if(o.get(i).getClass().getName().contains("Operator")) {
				aux=aux+((OperatorLink)o.get(i)).toString();
				confidence=confidence+((OperatorLink)o.get(i)).toString();
			}
			else aux=aux+o.get(i).toString();
		}
		return aux;
	}
	
	public ArrayList<String> getCompon(){
		return compon;
	}
	public GraphTranslit1 copy() {
		GraphTranslit1 aux=new GraphTranslit1(id,tag);
		for (int i=0;i<this.elem.size();i++) {
			aux.getElements().add(elem.get(i));
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
	public ArrayList<String> getGraphTranslit2(){
		return gt2;
	}

	public  ArrayList<TreeNode> listNodes(){
		 ArrayList <TreeNode> t=new ArrayList<TreeNode>();
		 if(gt2.size()>0) {
			 for(int i=0;i<gt2.size();i++) {
				 t.add(tag.gt2.get((tag.mgt2.get(gt2.get(i)).intValue())));
			 }
			 }
		 return t;
	 }
	public ArrayList<GT1Element> getElements(){
		return elem;
	}
	public void setGT1Elements(ArrayList<GT1Element>n2) {
		for (int i=0;i<n2.size();i++ ) {
			elem.add(n2.get(i));
		}
	}
	
	public void createTranslit2() {
		GraphTranslit2 gt2=new GraphTranslit2(this.id+">GT2_0","main",tag);
	
		createGraphStructure();
	
		MinimumSpanningForest2<GlyphNode,OperatorLink> prim = 
              	new MinimumSpanningForest2<GlyphNode,OperatorLink>(t,
              		new DelegateForest<GlyphNode, OperatorLink>(), DelegateTree.<GlyphNode,OperatorLink>getFactory(),
              		Functions.<Double>constant(8.0));
         Forest<GlyphNode,OperatorLink> trees = prim.getForest();
     	System.out.println("Created GT2 "+trees.toString());
      Iterator<Tree<GlyphNode,OperatorLink>> it=trees.getTrees().iterator();
      Tree<GlyphNode,OperatorLink> tt=it.next();
		System.out.println("Root "+ tt.getRoot().getLabel()+" "+"has "+ tt.getRoot().noChild()+" children");
		GlyphNode prev=null;
	 ArrayList<ComponentElement> selComp=new ArrayList<ComponentElement>();
		for (int i=0;i<tt.getRoot().noChild();i++) {
			
			GlyphNode gnode=((SegmentNode)tt.getRoot()).getChildrenNodes().get(i);
			System.out.println("Process Child GT2 "+gnode.getLabel());
			DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> temp= processNode(gnode);
			
			Iterator<GraphGT2Node> it1=temp.getVertices().iterator();;
			GraphGT2Node glast;
			if (gt2.getGraphGT2()!=null) {
			      glast=lastGT2Node(gt2.getGraphGT2());
			}
			else glast=null;
			GraphGT2Node gfirst=firstGT2Node(temp);
			GraphGT2Node gcurent=firstGT2Node(temp);
			System.out.println("Created ROOT GT2 "+gfirst.getGT2Element().getLabel() );
			if(temp.getOutEdges(gcurent).size()>0) {
			while(temp.getOutEdges(gcurent).size()>0) {
				if (!gt2.getGraphGT2().containsVertex(gcurent)) {
				gt2.getGraphGT2().addVertex(gcurent);
				gt2.getElements().add(gcurent);
				}
				if (temp.getOutEdges(gcurent).size()>0) {
				OperatorLink o=(OperatorLink)temp.getOutEdges(gcurent).toArray()[0];
				GraphGT2Node gp=temp.getDest(o);
				if (gt2.getGraphGT2().containsVertex(gp))
				          gt2.getGraphGT2().addEdge(o,gcurent,gp);
				else {
					gt2.getGraphGT2().addVertex(gp);
					gt2.getElements().add(gp);
					gt2.getGraphGT2().addEdge(o,gcurent,gp);
				}
				gcurent=gp;
				}
				
			}
			}
			else { 
				if (!gt2.getGraphGT2().containsVertex(gcurent)) {
				gt2.getGraphGT2().addVertex(gcurent); gt2.getElements().add(gcurent);
				}
				}
		//	gt2.getGraphGT2().addVertex(gcurent);
			
			
			/* for (OperatorLink e :temp.getEdges())
			        gt2.getGraphGT2().addEdge(e, temp.getIncidentVertices(e));*/
			if (prev !=null) {
				OperatorLink o;
				System.out.println("Previous " +prev.getClass().toString());
				if (prev.getClass().toString().equals("class allmahVer4.GraphGT1Node")) {
				              o=new OperatorLink("-",5);
				}
				else {
					if (gnode.getClass().toString().equals("class allmahVer4.GraphGT1Node")) {
					o=new OperatorLink("-",5);
					}
					else o=new OperatorLink(" ",5);
				}
				gt2.getGraphGT2().addEdge(o, glast, gfirst);
				System.out.println("Added Link "+ o.toString());
				glast=lastGT2Node(gt2.getGraphGT2());
			}
			prev=gnode;
		
			
		}
		//ArrayList<GraphGT2Node> duplicates=new ArrayList<GraphGT2Node>();
		ArrayList<GraphGT2Node> firstNode=new  ArrayList<GraphGT2Node>();
		ArrayList<ArrayList<GraphGT2Node>> duplicates=new  ArrayList<ArrayList<GraphGT2Node>>();
		for (int i=0;i<gt2.getElements().size();i++) {
			if(gt2.getElements().get(i).getGT2Element().getParentGT1().getParent().getComponentElement()!=null) {
				System.out.println("Component "+gt2.getElements().get(i).getGT2Element().getParentGT1().getParent().getComponentElement().getLabel());
				if (!find(gt2.getElements().get(i).getGT2Element().getParentGT1().getParent().getComponentElement(),selComp)) {
					selComp.add(gt2.getElements().get(i).getGT2Element().getParentGT1().getParent().getComponentElement());
					firstNode.add(gt2.getElements().get(i));
					String s=Jsoup.parse(gt2.getElements().get(i).getLabel()).text();
					ArrayList<GraphGT2Node> furtherNodes=new ArrayList<GraphGT2Node>();
					int j=i+1;
					while (j<gt2.getElements().size()) {
						if (find(gt2.getElements().get(j).getGT2Element().getParentGT1().getParent().getComponentElement(),selComp)) {
							furtherNodes.add(gt2.getElements().get(j));
							if(gt2.getElements().get(j).getGT2Element().getParentGT1().getParent().getComponentElement()!=null)
							    if(gt2.getElements().get(i).getGT2Element().getParentGT1().getParent().getComponentElement().getLabel().equals(gt2.getElements().get(j).getGT2Element().getParentGT1().getParent().getComponentElement().getLabel()))
							                        s=s+";"+Jsoup.parse(gt2.getElements().get(j).getLabel()).text();
						}
						
						j=j+1;
					}
					if(gt2.getElements().get(i).getGT2Element().getParentGT1().getParent().getComponentElement().getLabel1().equals("CGlyph~"))
					    gt2.getElements().get(i).getGT2Element().getParentGT1().getParent().getComponentElement().setLabel2(s);
					duplicates.add(furtherNodes);
				}
		}		
			 //selComp.clear();
	}
		ArrayList<GraphGT2Node> eliminatedNodes=new ArrayList<GraphGT2Node>();
		
		for (int i=0;i<firstNode.size();i++) {
			ArrayList<GraphGT2Node> nodes= duplicates.get(i);
		 
			for (int j=0;j<nodes.size();j++) {
				boolean eliminate=true;
				if(!eliminatedNodes.isEmpty())
					if(eliminatedNodes.contains(nodes.get(j))) eliminate=false;
				if(eliminate) {
				OperatorLink oin=null;
				System.out.println("Node to eliminate "+ nodes.get(j).getLabel());
				if(gt2.getGraphGT2().containsVertex(nodes.get(j)) ) System.out.println("Node is in graph");
				if (!gt2.getGraphGT2().getInEdges(nodes.get(j)).isEmpty())	
			       oin=(OperatorLink)gt2.getGraphGT2().getInEdges(nodes.get(j)).toArray()[0];
				OperatorLink oout=null;
				if(!gt2.getGraphGT2().getOutEdges(nodes.get(j)).isEmpty())
		         oout=(OperatorLink)gt2.getGraphGT2().getOutEdges(nodes.get(j)).toArray()[0];
			if(oout==null) {
				gt2.getGraphGT2().removeEdge(oin);
				gt2.getGraphGT2().removeVertex(nodes.get(j));
			}
			else {
				GraphGT2Node ziel=gt2.getGraphGT2().getDest(oout);
				GraphGT2Node source=gt2.getGraphGT2().getSource(oin);
				OperatorLink oin1=oin.copy();
				gt2.getGraphGT2().removeEdge(oin);
				gt2.getGraphGT2().removeEdge(oout);
				gt2.getGraphGT2().removeVertex(nodes.get(j));
				gt2.getGraphGT2().addEdge(oin1, source,ziel);
			}
			gt2.getElements().remove(nodes.get(j));
			eliminatedNodes.add(nodes.get(j));
			}
			}
		}

    Iterator<GraphGT2Node> it2= gt2.getGraphGT2().getVertices().iterator();
  /*  while(it2.hasNext()) {
    	gt2.getElements().add(it2.next().getGT2Element());
    }*/
	this.gt2.add(gt2.getId());
	//gt1.get(i).generateGT2();
	tag.gt2.add(gt2);
	tag.mgt2.put(gt2.getId(),new Integer(tag.gt2.size()-1));
		//return gt2;
	}
	public GraphGT2Node lastGT2Node(DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> g) {
		GraphGT2Node g2=null;
		Collection<GraphGT2Node> nodes=g.getVertices();
		Iterator<GraphGT2Node> it=nodes.iterator();
		boolean found=false;
		while(it.hasNext() &&(!found)) {
			g2=it.next();
			if( g.getOutEdges(g2).size()==0) found =true;
		}
		return g2;
	}
	public GraphGT2Node firstGT2Node(DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> g) {
		GraphGT2Node g2=null;
		Collection<GraphGT2Node> nodes=g.getVertices();
		Iterator<GraphGT2Node> it=nodes.iterator();
		boolean found=false;
		while(it.hasNext() &&(!found)) {
			g2=it.next();
			if( g.getInEdges(g2).size()==0) found =true;
		}
		return g2;
	}
	public boolean find(ComponentElement c, ArrayList<ComponentElement> components) {
		boolean f=false;
		int i=0;
		if(c!=null) {
		if (!components.isEmpty()) {
		     while((i< components.size())&&(!f)) {
		    	 if (c.getLabel().equals(components.get(i).getLabel()))
		    			 f=true;
		    	 else i=i+1;
		     }
		   
		}
	}
		return f;
	}
	public DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink>  processNode(GlyphNode node) {
		DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> graph=new DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink>();
	
		if (node.getClass().toString().equals("class allmahVer4.GraphGT1Node")) {
			GT1Element gt1=((GraphGT1Node)node).getGT1Element();
			GT2Element gt2=null;
			//if (gt1.getParent().getComponentElement()!=null) {
				//if (!find(gt1.getParent().getComponentElement(),selComp)) {
				//gt2=new GT2Element(gt1.getParent().getComponentElement());
				//selComp.add(gt1.getParent().getComponentElement());
				//}
				//else gt2=new GT2Element(gt1.getParent().getComponentElement());
			//}
			//else 
			gt2=new GT2Element(gt1);
			if(gt2!=null) {
			GraphGT2Node g2=new GraphGT2Node(gt2, node);
			graph.addVertex(g2);
			}
			return graph;
		}
		else {
			GlyphNode prev=null;
				for (int i=0;i<((SegmentNode)node).getChildrenNodes().size();i++) {
			
			GlyphNode gnode=((SegmentNode)node).getChildrenNodes().get(i);
			System.out.println("Process Child GT2 "+Jsoup.parse(gnode.getLabel()).text());
			DirectedOrderedSparseMultigraph<GraphGT2Node,OperatorLink> temp= processNode(gnode);
			if(temp!=null) {
			Iterator<GraphGT2Node> it1=temp.getVertices().iterator();
			GraphGT2Node glast;
			if(graph!=null)
			  glast=lastGT2Node(graph);
			else glast=null;
			GraphGT2Node gfirst=firstGT2Node(temp);
			if (gfirst.getGT2Element().componentParent!=null) {
				
			}
			GraphGT2Node gcurent=firstGT2Node(temp);
		//	System.out.println("Created GT2 "+gfirst.getGT2Element().getLabel() );
			if(temp.getOutEdges(gcurent).size()>0) {
			while(temp.getOutEdges(gcurent).size()>0) {
				if (!graph.containsVertex(gcurent)) {
				graph.addVertex(gcurent);
				}
				if (temp.getOutEdges(gcurent).size()>0) {
				OperatorLink o=(OperatorLink)temp.getOutEdges(gcurent).toArray()[0];
				GraphGT2Node gp=temp.getDest(o);
				if (graph.containsVertex(gp))
				          graph.addEdge(o,gcurent,gp);
				else {
					graph.addVertex(gp);
					graph.addEdge(o,gcurent,gp);
				}
				gcurent=gp;
				}
				
			}
			}
			else 	{
				if (!graph.containsVertex(gcurent))
				graph.addVertex(gcurent);
			}
		//graph.addVertex(gcurent);
			/*for (OperatorLink e :temp.getEdges())
		        graph.addEdge(e, temp.getIncidentVertices(e));*/
			if (prev !=null) {
				OperatorLink o;
				if (prev.getClass().toString().equals("class allmahVer4.GraphGT1Node")) {
				              o=new OperatorLink("-",5);
				}
				else {
					if (gnode.getClass().toString().equals("class allmahVer4.GraphGT1Node")) {
					o=new OperatorLink("-",5);
					}
					else o=new OperatorLink(" ",5);
				}
				System.out.println("LAST "+ glast.getLabel());
				System.out.println("FIRST "+ gfirst.getLabel());
				graph.addEdge(o, glast, gfirst);
				glast=lastGT2Node(graph);
				
			}
			  
			prev=gnode;
				}
			
			//
		}
			
		}
		return graph;
	}

	public void createGraphStructure() {
		String label=calculateLabel();
		label=Jsoup.parse(label).text();
		StringTokenizer st=new StringTokenizer(label,SpecialSymbols.Operators_L13,true);
		String elem="";
	//	g = new DirectedSparseGraph<GlyphNode,OperatorLink>();
		 t = new SparseGraph<GlyphNode,OperatorLink>();
		 ArrayList <GraphGT1Node>elemNodes=new ArrayList<GraphGT1Node>();
		ArrayList <OperatorLink> operators=new ArrayList<OperatorLink>();
		String currentOp="";
		GlyphNode root=new SegmentNode(label,null,"root");
		GlyphNode currentParent=root;
		t.addVertex(currentParent);
		GlyphNode prevNode=null;
		GlyphNode node;
		int noalt=0; int nnt2=0;
		while (st.hasMoreTokens()) {
		   elem=st.nextToken();
		   if ( Arrays.stream(SpecialSymbols.OperatorsSet_L13).anyMatch(elem::equals))
			{System.out.println("Operator "+elem); operators.add(new OperatorLink(elem,1));}
		   else { System.out.println("Glyph "+elem);
		               if ((elem.indexOf("[")<0)&&(elem.indexOf("]")<0)){
		       
		            	
		            		node=new GraphGT1Node(this.elem.get(nnt2),currentParent);   
		            		elemNodes.add((GraphGT1Node)node);    	
		            		nnt2=nnt2+1;
		            	
		           
		            ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
		          //     g.addEdge(new OperatorLink("  ",0), currentParent, node, EdgeType.DIRECTED); 
		              t.addEdge(new OperatorLink("  ",0),currentParent,node, EdgeType.DIRECTED); 
		              
		               if (prevNode !=null) {
			                     //    g.addEdge(operators.get(operators.size()-1),prevNode, node, EdgeType.DIRECTED); 
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
		            			//   g.addEdge(operators.get(operators.size()-1),prevNode, node, EdgeType.DIRECTED); 
		            			  
		            		   }
		            		   prevNode=node;
		            		  // g.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            	  t.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            		
		            		  
		            		   currentParent=node;
		            		   aux=aux.substring(1);n=n+1;
		               }
		            	  
		            		   node=new GraphGT1Node(this.elem.get(nnt2),currentParent);   
		            		   elemNodes.add((GraphGT1Node)node);    	
			            		nnt2=nnt2+1;
		      	    ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
		            	    prevNode=node;
		            	   // g.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            	 t.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            	   	   
		            	 
		               }
		               else if(elem.indexOf("]")>=0) {
		            	   String aux=elem;
		            	  
		            		   node=new GraphGT1Node(this.elem.get(nnt2),currentParent);   elemNodes.add((GraphGT1Node)node);    	
			            		nnt2=nnt2+1;
		            	   ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
		            	//   g.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED);
		             t.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED);
		           	            	 
		            	//   g.addEdge(operators.get(operators.size()-1),prevNode, node, EdgeType.DIRECTED); 
		            	   
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
	/*	public Graph<GlyphNode,OperatorLink> getEntireGraphNT2(){
			
			return g;
		}*/
		public Graph<GlyphNode,OperatorLink> getTreeGraphNT2(){
			return t;
		}
}

