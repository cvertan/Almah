package allmahVer4;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.PickableEdgePaintTransformer;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import  java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import edu.uci.ics.jung.visualization.control.*;
import java.awt.geom.Point2D;
import org.apache.commons.collections4.Transformer;

import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import edu.uci.ics.jung.visualization.*;
import java.util.StringTokenizer;
import java.util.UUID;
import org.w3c.dom.Document;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.algorithms.shortestpath.MinimumSpanningForest2;
import edu.uci.ics.jung.algorithms.layout.*;
import org.apache.commons.lang3.tuple.Pair;
import org.classicmayan.tools.IDIOMTextGridObject;
import org.classicmayan.tools.TripleStoreQuery;
import org.jdom2.Content;
import org.jdom2.filter.ElementFilter;
import org.jdom2.util.IteratorIterable;

import com.google.common.base.Function;
import com.google.common.base.Functions;

import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Color;
import java.awt.Container;
import java.awt.image.BufferedImage;
import edu.uci.ics.jung.graph.util.EdgeType;
public class MayaDocumentParser  implements SpecialSymbols{
private File fileRoot;
private Font font;
private Namespace ns,ns1;
private String labelDocument;
private AllmahGUI interf;
private org.jdom2.Document doc;
private String sid;
private Map<String, JLabel> pictures=new HashMap<String,JLabel>();
protected ArrayList<HieroglyphenBlock> blocks=new ArrayList<HieroglyphenBlock>();
protected String docID="";
Element  teiroot;
	public MayaDocumentParser(AllmahGUI interf,String sid,File f, Font ft) {
		fileRoot=f;
		font=ft;
		this.interf=interf;
		this.sid=sid;
		pictures.put("#",new JLabel("#"));
		pictures.put("??",new JLabel("??"));
		pictures.put("\u2026", new JLabel("\u2026"));
		labelDocument="";
		Utils.setLastDir(fileRoot);
 		 docID =fileRoot.getName(); 
 		String selectedDocument="";
 			try{
 				
 			//	InputStream stream = new ByteArrayInputStream(selectedDocument.getBytes("UTF-8"));
 			
			org.jdom2.input.SAXBuilder saxBuilder = new SAXBuilder();
			saxBuilder.setIgnoringBoundaryWhitespace(true);
			saxBuilder.setIgnoringElementContentWhitespace(true);
			
			 doc = (org.jdom2.Document) saxBuilder.build(fileRoot);
		 teiroot=doc.getRootElement();
			System.out.println(teiroot);
		 ns =teiroot.getNamespace();
		 ns1 =teiroot.getNamespace("xml");
		
		
		//	Element  el1=doc.getRootElement().getChild("text",ns);
		  //el=el1.getChild("body",ns);
 			}catch(Exception ex1){
					JOptionPane mess=new JOptionPane();
					mess.setFont(font);
					JFrame tempW=new JFrame();
					tempW.setFont(font);
					mess.showMessageDialog(tempW, "<html><p><font size=+1 face="+font.getName() +" >Could not open file. Wrong Format</font></p></html>", "Dialog",
	    		        JOptionPane.ERROR_MESSAGE); 
					//System.out.println("Could not open file.Problem 1 in " +lineNr+" with word "+fidal);
					System.exit(0);}	
	}
	
	//
	
	public MayaDocumentParser(AllmahGUI interf,String sid,org.jdom2.Document doc, String docId,Font ft) {
	//	fileRoot=f;
		font=ft;
		this.interf=interf;
		this.doc=doc;
		this.sid=sid;
		pictures.put("#",new JLabel("#"));
		pictures.put("??",new JLabel("??"));
		pictures.put("\u2026", new JLabel("\u2026"));
		labelDocument="";
		//Utils.setLastDir(fileRoot);
 		 //docID =fileRoot.getName(); 
		this.docID=docId;
 		String selectedDocument="";
 			try{
 				
 			//	InputStream stream = new ByteArrayInputStream(selectedDocument.getBytes("UTF-8"));
 			
			org.jdom2.input.SAXBuilder saxBuilder = new SAXBuilder();
			saxBuilder.setIgnoringBoundaryWhitespace(true);
			saxBuilder.setIgnoringElementContentWhitespace(true);
			
			// doc = (org.jdom2.Document) saxBuilder.build(fileRoot);
		 teiroot=doc.getRootElement();
			System.out.println(teiroot);
		 ns =teiroot.getNamespace();
		 ns1 =teiroot.getNamespace("xml");
		
		
		//	Element  el1=doc.getRootElement().getChild("text",ns);
		  //el=el1.getChild("body",ns);
 			}catch(Exception ex1){
					JOptionPane mess=new JOptionPane();
					mess.setFont(font);
					JFrame tempW=new JFrame();
					tempW.setFont(font);
					mess.showMessageDialog(tempW, "<html><p><font size=+1 face="+font.getName() +" >Could not open file. Wrong Format</font></p></html>", "Dialog",
	    		        JOptionPane.ERROR_MESSAGE); 
					//System.out.println("Could not open file.Problem 1 in " +lineNr+" with word "+fidal);
					System.exit(0);}	
	}
	//
	public String getMayaDocumentTitle() {
		Element titleEl=teiroot.getChild("teiHeader",ns).getChild("fileDesc",ns).getChild("titleStmt",ns).getChild("title",ns);
		System.out.println(titleEl.getText());
 		return titleEl.getText();
	}
	
	public ArrayList<HieroglyphenBlock> getMayaDocumentParts(){
		Element bodyEl=teiroot.getChild("text",ns).getChild("body",ns);
		ArrayList<String> docParts=new ArrayList<String>();
		List<Element> divEls= bodyEl.getChildren("div",ns);
		Random randomGenerator=new Random();
		if (!divEls.isEmpty()) {
		for (Element divElement: divEls) {
			List<Element> abEls= divElement.getChildren("ab",ns);
			if (!abEls.isEmpty()) {
			for (Element abElement: abEls) {
				System.out.println("** "+ abElement.getAttributeValue("n") );
				
				int nochoice=0;
				labelDocument=abElement.getAttributeValue("n");
				if( (!labelDocument.equals(SpecialSymbols.GAP))&& (abElement.getChildren("error",ns).isEmpty())){
				System.out.println(abElement.getAttributeValue("id", ns1)+ " " +abElement.getAttributeValue("n"));
				blocks.add(new HieroglyphenBlock(docID,abElement.getAttributeValue("n"),abElement.getAttributeValue("id", ns1),true,interf));
				 Map <String, ArrayList<String>>choiceList = new HashMap<String, ArrayList<String>>();
			
				 Map <String, ArrayList<Reading>> elements = new HashMap<String, ArrayList<Reading>>();
				
				IteratorIterable<Element> abChildren= abElement.getDescendants(new ElementFilter());
				TripleStoreQuery queryimg=new TripleStoreQuery();
				while(abChildren.hasNext()) {
					
					Element c=abChildren.next();
					if( c.getName().equals("g")){
						  ArrayList<Reading> re= new ArrayList<Reading>();
						if(c.getAttributeValue("ref")!=null) {
						if (c.getAttributeValue("ref").indexOf("textgrid")>=0) {
						 System.out.println(c.getAttributeValue("ref")+ " " +c.getAttributeValue("n"));
						 String uripict="";
						 String s="";
							try {
						 s=queryimg.getImageOfGraph(c.getAttributeValue("ref")) ;
							}
							catch(Exception e) {System.out.println ("Error reading URI");};
							if(!s.isEmpty()) {
							IDIOMTextGridObject ob=new IDIOMTextGridObject();
						//	BufferedImage picture=null;
						//	BufferedImage pictureScaled=null;
							BufferedImage picture=null;
							BufferedImage pictureScaled=null;
						try {
							// picture=ob.getTextGridObjectByUri(s);
							pictureScaled=ob.getTextGridObjectByUri(s, sid, 40) ;
							//162d11e0MCAS09We57yQYs2CiDuOc7o2LwJqRCWd9d49IsNE7RmujQRZTfDL0zUiJJw4PF51607425705101344
							//System.out.println("Size of picture "+ picture.getWidth()+" "+ picture.getHeight());
							// pictureScaled=new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
							 System.out.println("Size of picture "+ pictureScaled.getWidth()+" "+ pictureScaled.getHeight());
							 Graphics2D g = pictureScaled.createGraphics();	 
							    //layoutComponent(pane);
							 g.drawImage(picture, 0, 0, 30, 30, null);
							 g.dispose();
						}
						catch(Exception e1) {}		
						 if (pictureScaled!=null) {
							 JLabel l =new JLabel(c.getAttributeValue("n"));
					            l.setIcon(new ImageIcon(pictureScaled));
					            pictures.put(c.getAttributeValue("n"),l);
					          }
						 else { 
							 System.out.println("Picture from " +s +"is null");
							 pictures.put(c.getAttributeValue("n"),new JLabel(s));
						 }
							}
							else 	pictures.put(c.getAttributeValue("n"),new JLabel(c.getAttributeValue("ref")));
						 TripleStoreQuery query1=new TripleStoreQuery();
		            		java.util.List<Pair> reading1=query1.getLogographicReadingTransliterationValueWithLevel(c.getAttributeValue("ref"));
		            		for (int i=0; i<reading1.size();i++) {
		            			re.add(new Reading((String)reading1.get(i).getLeft(),(String)reading1.get(i).getRight(),"lr"));
		            		}
		            		System.out.println("Logographic reading "+reading1);	       
		            		TripleStoreQuery query2=new TripleStoreQuery();
		            		java.util.List<Pair> reading2=query2.getDiacriticFunctionTransliterationValueWithLevel(c.getAttributeValue("ref"));
		            		for (int i=0; i<reading2.size();i++) {
		            			re.add(new Reading((String)reading2.get(i).getLeft(),(String)reading2.get(i).getRight(),"df"));
		            		}
		            		System.out.println("Diacritic Function "+reading2);
		            		TripleStoreQuery query3=new TripleStoreQuery();
		            		java.util.List<Pair>reading3=query3.getLogographicMeaningTransliterationValueWithLevel(c.getAttributeValue("ref"));
		            		for (int i=0; i<reading3.size();i++) {
		            			re.add(new Reading((String)reading3.get(i).getLeft(),(String)reading3.get(i).getRight(),"lm"));
		            		}
		            		System.out.println("Logographic Meaning "+reading3);
		            		TripleStoreQuery query4=new TripleStoreQuery();
		            		java.util.List<Pair>reading4=query4.getNumericFunctionTransliterationValueWithLevel(c.getAttributeValue("ref"));
		            		for (int i=0; i<reading4.size();i++) {
		            			re.add(new Reading((String)reading4.get(i).getLeft(),(String)reading4.get(i).getRight(),"nf"));
		            		}
		            		System.out.println("Numeric Function "+reading4);
		            		TripleStoreQuery query5=new TripleStoreQuery();
		            		java.util.List<Pair>reading5=query5.getSyllabicReadingTransliterationValueWithLevel(c.getAttributeValue("ref"));
		            		for (int i=0; i<reading5.size();i++) {
		            			re.add(new Reading((String)reading5.get(i).getLeft(),(String)reading5.get(i).getRight(),"sr"));
		            		}
		            		System.out.println("Syllabic reading "+reading5);
		            	if (!elements.containsKey(c.getAttributeValue("n")))
		            		elements.put(c.getAttributeValue("n"),re);
		            	 					}
						else pictures.put(c.getAttributeValue("n"),new JLabel(c.getAttributeValue("n")));
					}
						else pictures.put(c.getAttributeValue("n"),new JLabel(c.getAttributeValue("n")));
				}
					//
					else if(c.getName().equals("choice")) {
						IteratorIterable<Element> choiceChildren= c.getDescendants(new ElementFilter());
						ArrayList<String> choiceEl=new ArrayList<String>();
						ArrayList<String> choiceEluri=new ArrayList<String>();
						while(choiceChildren.hasNext()) {
							Element c1=choiceChildren.next();
							if( c1.getName().equals("g")){
								choiceEl.add(c1.getAttributeValue("n"));
								choiceEluri.add(c1.getAttributeValue("ref"));
							}
					}
						choiceList.put("?"+nochoice,choiceEl);
					
						nochoice=nochoice+1;
				}
					//
				}
				//createGraphStructure(labelDocument,elements);
			
		      blocks.get(blocks.size()-1).createGraphStructure(abElement.getAttributeValue("n"), elements, choiceList);
		      blocks.get(blocks.size()-1).calculateImage(pictures);
		     int imgno=randomGenerator.nextInt(5);
		      //TripleStoreQuery queryimg=new TripleStoreQuery();
		      //String s=queryimg.getImageOfGraph(String textgridURI) ;
		      blocks.get(blocks.size()-1).setPathImage(SpecialSymbols.path+SpecialSymbols.imageNames[imgno]);
		      
			}
				else {
					blocks.add(new HieroglyphenBlock(docID,abElement.getAttributeValue("n"),abElement.getAttributeValue("id", ns1),false,interf));
					  blocks.get(blocks.size()-1).calculateImage(pictures);
					int imgno=randomGenerator.nextInt(5);
					  blocks.get(blocks.size()-1).setPathImage(SpecialSymbols.path+SpecialSymbols.imageNames[imgno]);
					System.out.println("!!!!! "+abElement.getAttributeValue("n")+ "    " + abElement.getAttributeValue("id", ns1));
				}
				//}
				
			}
			}
		}
		}
		return blocks;
	}
	
 

}
