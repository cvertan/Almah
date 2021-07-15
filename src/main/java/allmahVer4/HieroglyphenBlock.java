package allmahVer4;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import java.awt.image.BufferedImage;
import org.apache.commons.lang3.tuple.Pair;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import org.classicmayan.tools.*;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.OElement;
public class HieroglyphenBlock extends TreeNode{
	private String id ;
	private String label;
	private String numerical;
	
	private boolean state;
	private ArrayList<GlyphElementNode> elemNodes;
	private Graph<GlyphNode,OperatorLink> g ;
	private Graph<GlyphNode,OperatorLink> t; 
	 private String parent;
    private ArrayList<String> ntr1;
    private AllmahGUI interf;
    private String imageURL;
    private ArrayList<Object> teile;
    private ArrayList<String> annotations;
   private JPanel pane;
   private BufferedImage img;
	public HieroglyphenBlock(String readingID, String num, String l, boolean state, AllmahGUI in)  {
		super(0);
		id=readingID+">"+l;
		numerical=num;
		teile=new ArrayList<Object>();
		this.label=l;	
		img=null;
		this.state=state;
		pane=new JPanel();
		
	
		parent=readingID;
		annotations=new ArrayList<String>();
		elemNodes= new ArrayList<GlyphElementNode>();
		ntr1=new ArrayList<String>();
		this.interf=in;
	}
	public OElement toDB(OElement el) {
		ODatabaseSession db =  this.interf.getDatabase();
		 el.setProperty("id",this.id, OType.STRING);
		 el.setProperty("state",this.state,OType.BOOLEAN);
	     List<OElement> listHBlocks=new ArrayList<OElement>();
	     return el;
	} 
	public boolean getState() {
		return state;
	}
	public void setElemNodes1(ArrayList<GlyphElementNode> e) {
		for (int i=0;i<e.size();i++)
			elemNodes.add(e.get(i));
	}
	public void calculateImage(Map<String,JLabel> pictures) {
		Map<String, ArrayList<JLabel> >imgs1=new HashMap<String,ArrayList<JLabel>>();
		int nochoice=0;
		
		
		if(numerical.equals("#")) { 
			pane.setSize(50,50);pane.add(pictures.get("#"));
		}
		else if(numerical.equals("\u2026")) { 
			pane.setSize(50,50);pane.add(pictures.get("\u2026"));
		}
		else if(!elemNodes.isEmpty()) {
		pane.setSize(150,150);
		for (int i=0;i<elemNodes.size();i++) {
		//	if(elemNodes.get(i).getClass().equals("class allmahVer4.GlyphElementNode")) {
		 if( elemNodes.get(i).getLabel().equals ("?")){
			   
		      
		        	
		        JPanel paux=new JPanel();JLabel lx=new JLabel("?"+(i+1)+":");
		        for (Map.Entry<String, ArrayList<Reading>> entry : elemNodes.get(i).getAlternatives().entrySet()) {
		        
		           paux.add(pictures.get(entry.getKey()));
		        }
		       pane.add(lx);pane.add(paux);
			}
			else {
				String temp=elemNodes.get(i).getLabel();
				if(!temp.equals("??")) {
				  if(temp.indexOf("?")==0) temp=temp.substring(1);
				  if(temp.lastIndexOf("?")==temp.length()-1) temp=temp.substring(0, temp.length()-1);
				}
				
					  if(temp.indexOf("*")==0) temp=temp.substring(1);
					  if(temp.lastIndexOf("*")==temp.length()-1) temp=temp.substring(0, temp.length()-1);
					
			pane.add(pictures.get(temp));
		
		}
		//}
		}
		}
		else{
			pane.setSize(50,50);
			pane.add(new JLabel(" "));
	}
		
		
	
		 int w = pane.getWidth();
		    int h = pane.getHeight();
		    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g = bi.createGraphics();	 
		   layoutComponent(pane);
		    pane.print(g);
		    g.dispose();
		    img= bi;
	}
	public BufferedImage getImage() {
		return img;
	}
	public String getPathImage() {
		return imageURL;
	}
	public void setPathImage(String s) {
		imageURL=s;
	}
	public ArrayList<String> getAnnotations() {
		return annotations;
	}
	public String getNumerical() {
		return numerical;
	}
	public String getParent() {
		return parent;
	}
	public String getId() {
		return id;
	}
	public void setState(boolean b) {
		state=b;
	}
	public String calculateLabel() {
		String s="";
		if (state)
		s= "<b>" +label +"</b>"+ "<br/>"+numerical;
		else if (numerical.equals("#")) s= "<font color=\"#c4c3d0\"><b>" +label +"</b>"+ "<br/>"+numerical+"</font>";
		else s= "<font color=\"#c4aead\"><b>" +label +"</b>"+ "<br/>"+numerical+"<br/>ERROR</font>";
		if(! annotations.isEmpty()) {
		for (int i=0;i<annotations.size();i++) {
			int position=(interf.mbla.get(annotations.get(i))).intValue();
			BlockAnnotation a=interf.blockAnn.get(position);
			int j=0; int countC=0; int countO=0;
			while(j<position) {
				if (interf.blockAnn.get(j).getTyp().equals("calender")) countC=countC+1;
				if (interf.blockAnn.get(j).getTyp().equals("onomastic")) countO=countO+1;
				j=j+1;
			}
			countC=countC+1;
			countO=countO+1;
			Color c=Color.BLACK;
			String abr="";
			if (a.getTyp().equals("calender")) {
				c=interf.colC; abr="C"+countC+" ";
			}
			if (a.getTyp().equals("onomastic")) {
				c=interf.colO; abr="O"+countO+" ";
			}
			
		         s=s+"<br/><font color=\""+encodeAnnColor(c)+"\"><b>"+abr+"</b> "+a.getSubTyp()+"</font>";
		}
		}
		s="<html>"+s+"</html>";
	return s;
	}
	public String getBlockID() {
		return id;
	}
	public String encodeAnnColor(Color c) {
		String hex=String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
		return hex;
	}
	 public  ArrayList<TreeNode> listNodes(){
		 ArrayList <TreeNode> t=new ArrayList<TreeNode>();
		 for(int i=0;i<ntr1.size();i++) {
			t.add(interf.nt1.get((interf.mnt1.get(ntr1.get(i))).intValue()));
		 }
		 return t;
	 }	
	 public String getNumLabel() {
		return numerical;
	}
	public String getLabel() {
		return label;
	}
	public ArrayList<GlyphElementNode> getElements(){
		return elemNodes;
	}
	public Graph<GlyphNode,OperatorLink> getEntireGraphHB(){
		return g;
	}
	public Graph<GlyphNode,OperatorLink> getTreeGraphHB(){
		return t;
	}
	//

	
	
	//
	public void createGraphStructure(String s, Map<String,ArrayList< Reading>> elements, Map <String, ArrayList<String>>choiceList) {
		
		String label=s;
		StringTokenizer st=new StringTokenizer(label,SpecialSymbols.Operators_L13,true);
		String elem="";
		g = new SparseGraph<GlyphNode,OperatorLink>();
		 t = new SparseGraph<GlyphNode,OperatorLink>();
		
		ArrayList <GlyphElementNode> glyphElements=new ArrayList<GlyphElementNode>();
		ArrayList <OperatorLink> operators=new ArrayList<OperatorLink>();
		String currentOp="";
		GlyphNode root=new SegmentNode(s,null,"root");
		GlyphNode currentParent=root;
		t.addVertex(currentParent);
		GlyphNode prevNode=null;
		GlyphNode node;
		int noalt=0;
		while (st.hasMoreTokens()) {
		   elem=st.nextToken();
		   if ( Arrays.stream(SpecialSymbols.OperatorsSet_L13).anyMatch(elem::equals))
			{System.out.println("Operator "+elem); operators.add(new OperatorLink(elem,1)); teile.add(operators.get(operators.size()-1));}
		   else { System.out.println("Glyph "+elem);
		               if ((elem.indexOf("[")<0)&&(elem.indexOf("]")<0)){
		            	if(elem.equals("??")) {
		            	
		            		node=new GlyphElementNode(elem,currentParent);  
		            //	((GlyphElementNode)node).setURI(pictures.get(elem));
		            		elemNodes.add((GlyphElementNode)node);    	
		            		teile.add(node);
		            	}
		            	else if(elem.equals("?")) {
		            		node=new GlyphElementNode(elem,currentParent); 
		            		System.out.println("ELEM "+ elem+ " "+noalt);
		            		ArrayList<String> alt=choiceList.get("?"+noalt);
		            		
		            		for (int j=0;j<alt.size();j++) {
		           
		            		((GlyphElementNode)node).getAlternatives().put(alt.get(j),elements.get(alt.get(j)) );
		            		
		            		}
		            		noalt=noalt+1;elemNodes.add((GlyphElementNode)node);   
		            		teile.add(node);
		            	}
		            	else {
		            		System.out.println("Kernel= "+kernel(elem));
		            		node=new GlyphElementNode(elem,currentParent, elements.get(kernel(elem)));
		            	
		            		elemNodes.add((GlyphElementNode)node);    	teile.add(node);   
		            	}
		           
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
		            		    node=new SegmentNode("[     ]",currentParent,"segm");teile.add("[");
		            		    ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
		            		   if((prevNode!=null))  {
		            			   if(n==1)
		            			   g.addEdge(operators.get(operators.size()-1),prevNode, node, EdgeType.DIRECTED); 
		            			  
		            		   }
		            		   prevNode=node;
		            		   g.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            	  t.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            		
		            		  
		            		   currentParent=node;
		            		   aux=aux.substring(1);n=n+1;
		               }
		            	   if(aux.equals("??")) {
				            	
			            		node=new GlyphElementNode(aux,currentParent);  
			       
			            		elemNodes.add((GlyphElementNode)node);    teile.add(node);	                
			            	}
		            	   else if(aux.equals("?")) {
			            		node=new GlyphElementNode(aux,currentParent); teile.add(node);
			            		System.out.println("ELEM "+ elem+ " "+noalt);
			            		ArrayList<String> alt=choiceList.get("?"+noalt);
			            	
			            		for (int j=0;j<alt.size();j++) {
			            		((GlyphElementNode)node).getAlternatives().put(alt.get(j),elements.get(alt.get(j)) );
			            	
			            		}
			            		elemNodes.add((GlyphElementNode)node);    	   
			            		noalt=noalt+1;
			            	}
		            	   else  {node=new GlyphElementNode(aux,currentParent,elements.get(kernel(aux))); 
		          
		            	   teile.add(node); elemNodes.add((GlyphElementNode)node);  }
		            	    ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
		            	    prevNode=node;
		            	    g.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            	 t.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED); 
		            	   	   
		            	 
		               }
		               else if(elem.indexOf("]")>=0) {
		            	   String aux=elem;
		            	   if(elem.substring(0,elem.indexOf("]")).equals("??")) {
				            	
			            		node=new GlyphElementNode(elem.substring(0,elem.indexOf("]")),currentParent);       	   elemNodes.add((GlyphElementNode)node);  
			            		teile.add(node);
			            	}
		            	   else if(elem.substring(0,elem.indexOf("]")).equals("?")) {
			            		node=new GlyphElementNode(elem.substring(0,elem.indexOf("]")),currentParent); 
			            		System.out.println("ELEM "+ elem+ " "+noalt);
			            		ArrayList<String> alt=choiceList.get("?"+noalt);
			            	
			            		for (int j=0;j<alt.size();j++) {
			            		((GlyphElementNode)node).getAlternatives().put(alt.get(j),elements.get(alt.get(j)) );
			         
			            		}
			            		noalt=noalt+1;elemNodes.add((GlyphElementNode)node);    	  teile.add(node); 
			            		/*
			            		 *	node=new GlyphElementNode(elem,currentParent); 
		            		System.out.println("ELEM "+ elem+ " "+noalt);
		            		ArrayList<String> alt=choiceList.get("?"+noalt);
		            		for (int j=0;j<alt.size();j++)
		            		((GlyphElementNode)node).getAlternatives().put(alt.get(j),elements.get(alt.get(j)) );
		            		noalt=noalt+1;elemNodes.add((GlyphElementNode)node);   
		            		teile.add(node);
			            		 */
			            	}
		            	   else {node=new GlyphElementNode(elem.substring(0,elem.indexOf("]")),currentParent,elements.get(kernel(elem.substring(0,elem.indexOf("]")))));
		            
		            	   elemNodes.add((GlyphElementNode)node); teile.add(node);
		            	   }
		            	   ( (SegmentNode)  currentParent).getChildrenNodes().add(node);
		            	   g.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED);
		             t.addEdge(new OperatorLink("  ",0),currentParent, node, EdgeType.DIRECTED);
		           
		            	 
		            	   g.addEdge(operators.get(operators.size()-1),prevNode, node, EdgeType.DIRECTED); 
		            	   
		            	   while(aux.indexOf("]")>0) {
		            		   prevNode=currentParent;  node=currentParent;;currentParent=node.getParent();
		            		  teile.add("]");
		            		  if(aux.substring(aux.indexOf("]")).length()>1) {
		            		  aux=aux.substring(0,aux.indexOf("]"))+aux.substring(aux.indexOf("]")+1);
		            		   
		            		  }
		            		  else  aux=aux.substring(0,aux.indexOf("]"));
		            	   }
		               }	
		   }
		}
	}
	public ArrayList<Object> getTeile(){
		return teile;
	}
	private String kernel(String s) {
		String aux=s;
		  if( s.equals("??")) return"";
		  else if (s.equals("?")) return "choice";
		  else {
			  
			  aux=s.replace("*","");
			  System.out.println("AUX "+aux);
			  aux=aux.replace("?","");
		
			  System.out.println("AUX "+aux);
			  return aux;
		  }

	}
	public ArrayList<String> getNumTranslit1() {
		return ntr1;
	}
	public void generateNT1() {
		ArrayList<NumTranslit1> nt1=new ArrayList<NumTranslit1>();
		int nr=0;
		NumTranslit1 n1 = new NumTranslit1(id+">"+"NT1_0",interf);
		nt1.add(n1);
		for (int i=0;i<elemNodes.size();i++) {
			int na=0;
			if (elemNodes.get(i).getAlternatives().isEmpty()) {
				for (int j=0;j<nt1.size();j++) {
					
					NT1Element x=new NT1Element(elemNodes.get(i));
					elemNodes.get(i).getNT1Elements().add(x);
				nt1.get(j).getElements().add(x);
				}
			}
			else {
				ArrayList<NT1Element> elx=new ArrayList<NT1Element>();
				for (Map.Entry<String, ArrayList<Reading>> entry : elemNodes.get(i).getAlternatives().entrySet()) {
					NT1Element nt1aux=new NT1Element(elemNodes.get(i),entry.getKey());
					System.out.println("ADDED "+entry.getKey()+"  "+entry.getValue()+ "  "+ entry.getValue().size()+" readings");
				   nt1aux.setSelectedReadings(entry.getValue());
				     nt1aux.setInitialReadings(entry.getValue());
				  
				    elx.add(nt1aux);
				}  
				ArrayList<NumTranslit1> nt1aux=new ArrayList<NumTranslit1>();
				System.out.println("1 NT1 Elem "+ elx.get(0).getLabel());
		        for (int k=0;k<nt1.size();k++) {
		        //	NumTranslit1 nx=nt1.get(k).copy();
		        //	nx.setId(id+">"+"NT1_0");
		        	//nx.getElements().add(elx.get(0));
		        	//nt1aux.add(nx);
		        	//NumTranslit1 nx=nt1.get(k).copy();
			        //	nx.setId(id+">"+"NT1_0");
			        nt1.get(k).getElements().add(elx.get(0));
			        //	nt1aux.add(nx);
		        	
		        }
		        for(int l=1;l<elx.size(); l++) {
		        	System.out.println("NT1 Elem "+ elx.get(l).getLabel());
		        	for(int k=0;k<nt1.size();k++) {
		        		nr=nr+1;
		        		NumTranslit1 nx=nt1.get(k).copy();
		        		nx.setId(id+">"+"NT1_"+nr);
		        		nx.getElements().remove(nx.getElements().size()-1);
		        		nx.getElements().add(elx.get(l));
		        	//	System.out.println(nx.calculateLabel());
		        		nt1aux.add(nx);
		        	}
		        }
		        
		        if(!nt1aux.isEmpty()) {
		         for (int k=0;k<nt1aux.size();k++) {
		        	 nt1.add(nt1aux.get(k));
		         }     
		         nt1aux.clear();
		        }
			}
		}
		for (int i=0;i<nt1.size();i++) {
			ntr1.add(nt1.get(i).getId());
		
			interf.nt1.add(nt1.get(i));
			interf.mnt1.put(nt1.get(i).getId(),new Integer(interf.nt1.size()-1));
			if (!nt1.get(i).multipleReadings())
		        nt1.get(i).generateNT2();
	
		}
	}
/*	public void setNtr1(String s) {
		ntr1=s;
	}*/
	private void layoutComponent(Component component) {
	    synchronized (component.getTreeLock()) {
	        component.doLayout();

	        if (component instanceof Container) {
	            for (Component child : ((Container) component).getComponents()) {
	                layoutComponent(child);
	            }
	        }
	    }
	}
}
