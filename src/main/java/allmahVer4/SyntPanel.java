package allmahVer4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTable;
import javax.swing.tree.*;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import java.awt.GridBagLayout;
public class SyntPanel {
JList synList;
JPanel parts3;
int selind;
LinguisticTables t;
int posx,posy;
ChildFrame syntaxFrame;
JPanel right,left;
String label;
JTable selectedTable;
JScrollPane scrollFeat;
JTree treePOS;
ArrayList<SyntacticAnnotation>  listSyntFeatures;
String nodeValue;
DefaultMutableTreeNode tModel;
JButton val, edit;
SyntacticAnnotation sa;
	public SyntPanel(String s,TreePoS trposf, AllmahGUI interf, ChildFrame win) {
			label=s;
			
			//treePOS=pos;
			// treePOS=new JTree(tModel);
			 tModel=trposf.createNodes();
			 treePOS=new JTree(tModel);
			 listSyntFeatures=new ArrayList<SyntacticAnnotation>();
			 treePOS.expandPath(new TreePath(treePOS.getModel().getRoot()));
			    treePOS.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			t=new LinguisticTables();
			 String[][] rec = {
					   { "  ", "  ", "  " },
					   { "  ", "  ", "  " },
					   { "  ", "  ", "  " },
					   { "  ", "  ", "  " },
					   { "  ", "  ", "  " },
					   { "  ", "  ", "  " },
					};
			 selectedTable=new JTable(rec, t.colPoSValues);
			GridBagConstraints g2=new GridBagConstraints();
			
			g2.gridx=0;
	    	g2.gridy=0;
	    	g2.weightx=1.0;
	    	g2.weighty=1.0;
	    	g2.anchor=GridBagConstraints.CENTER;
	    	g2.fill=GridBagConstraints.BOTH;
	    	g2.insets.top=5;
	    	g2.insets.bottom=5;
	    	g2.insets.left=5;
	    	g2.insets.right=5;
	    //	g2.gridwidth=msg.getElements().size();
	    	g2.gridwidth=2;
		DefaultListModel<String>	 listModel = new DefaultListModel<String>();
			 for(int j=0;j<10;j++)
				 listModel.addElement("        ");
			 parts3=new JPanel();
			 parts3.setLayout(new GridBagLayout());
		        //create the list
		  synList= new JList<>(listModel);
		       parts3.add(new JScrollPane(synList), g2);
		//       semList.addListSelectionListener(this);
		      JPopupMenu popupMenu= new JPopupMenu();
		     JMenuItem  jmi1= new JMenuItem("Add");
		    JMenuItem jmi2 = new JMenuItem("Delete");
		        popupMenu.add(jmi1);
		        popupMenu.add(new JPopupMenu.Separator());
		        popupMenu.add(jmi2);
		       
		        synList.addListSelectionListener(new ListSelectionListener() {
		        	 public void valueChanged(ListSelectionEvent listSelectionEvent) {
		        		
		        		selind=synList.getSelectedIndex();
		        	 }
		        });
		        
		        synList.addMouseListener(new MouseAdapter() {
		        	
		        	public void mousePressed(MouseEvent e)  {check(e);}
		        //	public void mouseReleased(MouseEvent e) {check(e);}
                       
		        	public void check(MouseEvent e) {
		        	    if (e.isPopupTrigger()) { //if the event shows the menu
		        	     
		        	     
		        	    	//synList[x].setSelectedIndex(synList[x].locationToIndex(e.getPoint())); //select the item
		        	    	//synList.setSelectedIndex(synList.locationToIndex(e.getPoint()));
		        	    	//selind=selList.locationToIndex(e.getPoint());
		        	      //  sellist=x;
		        	    	if(synList.isEnabled()) {
		        	    	synList.setSelectedIndex(selind);
		        	        popupMenu.show(synList, e.getX(), e.getY()); //and show the menu
		        	        posx=e.getX();posy=e.getY();;
		        	    	}
		        	    }
		        	}
		          
		       });

		       
		        if(synList.isEnabled()) {
		        synList.setComponentPopupMenu(popupMenu);
		
		       
		        jmi1.addActionListener(new ActionListener() {
		        	 
		        	public void actionPerformed(ActionEvent e) {
		        	syntaxFrame= new ChildFrame("Morphological Glossing for "+label,Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
             		   syntaxFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             		   Container content=syntaxFrame.getContentPane();
             		  // content.setLayout();
             	
             		  // selList=synList[x];
             		  scrollFeat=new JScrollPane(selectedTable);
     				  JScrollPane scrollPos=new JScrollPane(treePOS);
             		   left =new JPanel();
             		   left.add(scrollPos);
             		  right= new JPanel();
		        		right.add(scrollFeat);
		        		JButton assign=new JButton("Assign");
		        		assign.addActionListener(new ActionListener() {
		        			public void actionPerformed(ActionEvent e1) {
		        			String sp=treePOS.getSelectionPath().getLastPathComponent().toString();
		        				String spp=treePOS.getSelectionPath().getParentPath().getLastPathComponent().toString();
		        				String sf="";
		        				ArrayList<SyntacticAnnotation> synfeat=new ArrayList<SyntacticAnnotation>();
		        				int row= selectedTable.getSelectedRow();
    		                    int col= selectedTable.getSelectedColumn();
    		                   String key=""+selectedTable.getValueAt(row, 0)+selectedTable.getValueAt(row, 1)+selectedTable.getValueAt(row, 2);
    		                   System.out.println("KEY "+key);
    		                   System.out.println("POS "+sp);
    		                   System.out.println("POS "+spp);
    		                 SyntacsValues sv=new SyntacsValues();
    		                    SyntacticFeature sff=null;
    		                 
    		            if (nodeValue.indexOf("Absolutive")>=0) sff=sv.ABSOLUTIVE_CASE.get(key);
    		  				   else  if (nodeValue.indexOf("Ergative")>=0)sff=sv.ERGATIVE_CASE.get(key);
    		  				   else  if (nodeValue.indexOf("Demonstrative")>=0) sff=sv.INDEPENDENT_OR_DEMONSTRATIVE_PRONOUN.get(key);
    		  				   else  if (nodeValue.indexOf("Thematic")>=0) {	sff=sv.THEMATIC_SUFFIXES.get(key);			                    					   
    		  					                  				 }
    		  				   else   if (nodeValue.indexOf("Verbal Clitics")>=0) {sff=sv.VERBAL_CLITICS.get(key);}
    		  				   else   if (nodeValue.indexOf("Other Particle")>=0) {sff=sv.PARTICLE.get(key);}
    		  				   else  if (nodeValue.indexOf("Valency")>=0) {sff=sv.VALENCY_DECREASING_INCREASING_SUFFIXES.get(key);}
    		  				   else if (nodeValue.indexOf("Verb Lexical")>=0) {sff=sv.VERB_LEXICAL.get(key);}
    		  				   else if (nodeValue.indexOf("Verbal Derivation")>=0) {sff=sv.VERBAL_DERIVATION.get(key);}
    		  				   else  if (nodeValue.indexOf("Adjective Lexical")>=0) {sff=sv.ADJECTIVE_LEXICAL.get(key);}
    		  				   else  if (nodeValue.indexOf("Adjectival Derivation")>=0) {sff=sv.ADJECTIVAL_DERIVATION.get(key);}
    		  				   else  if (nodeValue.indexOf("Adverb Lexical")>=0) {sff=sv.ADVERBS_LEXICAL.get(key);}
    		  				   else  if (nodeValue.indexOf("Adverb Bound")>=0) {sff=sv.ADVERBS_BOUND.get(key);}
    		  				   else  if (nodeValue.indexOf("Numeral Lexical")>=0) sff=sv.NUMERAL_LEXICAL.get(key);
    		  				   else  if (nodeValue.indexOf("Numeral Bound")>=0) sff=sv.NUMERALS_BOUND.get(key);
    		  				   else if (nodeValue.indexOf("Noun Lexical")>=0) sff=sv.NOUN_LEXICAL.get(key);
    		  				   else if (nodeValue.indexOf("Noun Inflection")>=0) sff=sv.NOUN_INFLECTION.get(key);
    		  				   else  if (nodeValue.indexOf("Noun Derivation")>=0) sff=sv.NOUN_DERIVATION.get(key);
    		  				   else  if (nodeValue.indexOf("Preposition")>=0)sff=sv.PREPOSITION.get(key);   
    		         //   System.out.println("Abbrev "+ sff.getAbbreviation());
    		                     sa=new SyntacticAnnotation(spp,sp,sff);     
    		               //   sa=new SyntacticAnnotation(""+selectedTable.getValueAt(row, 1));
    		                syntaxFrame.doDefaultCloseAction();
    		              DefaultListModel<String> dlm = (DefaultListModel) synList.getModel();
    		              dlm.insertElementAt(sa.getSyntFeature().getAbbreviation(),selind );
    		              listSyntFeatures.add(sa);
    		              synList.clearSelection();
                           // dlm.add(synList[x].getSelectedIndex(), sa.getSyntFeature().getAbbreviation());
                        // dlm.addElement(sa.getSyntFeature().getAbbreviation());
                          synList.revalidate();synList.repaint();
		        			}
		        		});
		        		content.add(left, BorderLayout.WEST);
		        		content.add(right, BorderLayout.CENTER);
		        		content.add(assign, BorderLayout.SOUTH);
		        		syntaxFrame.setVisible(true); 
	            		   interf.addChild(syntaxFrame,posx+20,posy+20,700, 500);		
	                  		// meaningFrame.pack();  
	                  		syntaxFrame.setVisible(true); 
	                  		 syntaxFrame.moveToFront();
		        		
		        	}
		        });
		      
		        jmi2.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		DefaultListModel dlm =
		        			       (DefaultListModel) synList.getModel();
		        		     dlm.remove(synList.getSelectedIndex());
		        	}
		        });
		        
		        synList.setCellRenderer(getRenderer());
	    	}
		        treePOS.addTreeSelectionListener(new TreeSelectionListener() {
		 			   public void valueChanged(TreeSelectionEvent e) {
		 				   DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		 	                       treePOS.getLastSelectedPathComponent();
		 				   nodeValue=node.toString();
		 				   System.out.println("Selected "+nodeValue);
		 				   if (nodeValue.indexOf("Absolutive")>=0) selectedTable=t.absolutiveCase;
		 				   else  if (nodeValue.indexOf("Ergative")>=0) selectedTable=t.ergativeCase;
		 				   else  if (nodeValue.indexOf("Demonstrative")>=0) selectedTable=t.indepEmphPronouns;
		 				   else  if (nodeValue.indexOf("Thematic")>=0) {				                    					   
		 					   selectedTable=t.thematicSuffixes;				                    				
		 				   }
		 				   else   if (nodeValue.indexOf("Verbal Clitics")>=0) selectedTable=t.verbalClitics;
		 				   else   if (nodeValue.indexOf("Other Particle")>=0) selectedTable=t.otherParticle;
		 				   else  if (nodeValue.indexOf("Valency")>=0) selectedTable=t.valencySuffixes;
		 				   else if (nodeValue.indexOf("Verb Lexical")>=0) selectedTable=t.verbLexical;
		 				   else if (nodeValue.indexOf("Verbal Derivation")>=0) selectedTable=t.verbalDerivation;
		 				   else  if (nodeValue.indexOf("Adjective Lexical")>=0) selectedTable=t.adjectiveLexical;
		 				   else  if (nodeValue.indexOf("Adjectival Derivation")>=0) selectedTable=t.adjectivalDerivation;
		 				   else  if (nodeValue.indexOf("Adverb Lexical")>=0) selectedTable=t.adverbLexical;
		 				   else  if (nodeValue.indexOf("Adverb Bound")>=0) selectedTable=t.adverbBound;
		 				   else  if (nodeValue.indexOf("Numeral Lexical")>=0) selectedTable=t.numeralLexical;
		 				   else  if (nodeValue.indexOf("Numeral Bound")>=0) selectedTable=t.numeralBound;
		 				   else if (nodeValue.indexOf("Noun Lexical")>=0) selectedTable=t.nounLexical;
		 				   else if (nodeValue.indexOf("Noun Inflection")>=0) selectedTable=t.nounInflection;
		 				   else  if (nodeValue.indexOf("Noun Derivation")>=0) selectedTable=t.nounDerivation;
		 				   else  if (nodeValue.indexOf("Preposition")>=0) selectedTable=t.preposition;
		 				  scrollFeat=new JScrollPane(selectedTable);
		 				 scrollFeat.revalidate();scrollFeat.repaint();
		 				 right.removeAll();
		 				right.add(scrollFeat);
		 				right.revalidate();right.repaint();
		 				   syntaxFrame.revalidate(); syntaxFrame.repaint();
		 				 win.revalidate(); win.pack();win.repaint();
		 			   }
				    });

		 //       JPanel buttons=new JPanel();
			    
			      // p.add(new JScrollPane(syntList),g2);
			      val=new JButton("Validate");
			       edit=new JButton("Edit");
			 //   buttons.add(val); buttons.add(edit);
			      g2.gridwidth=1; g2.gridy=1;
			       parts3.add(val, g2);
			       g2.gridx=1;
			       parts3.add(edit, g2);
			       val.addActionListener(new ActionListener(){
			    	   public void actionPerformed(ActionEvent e) {
			    		
			    	synList.setEnabled(false);
			    	edit.setEnabled(true);
			    	val.setEnabled(false);
			    	   }  
			       });
			       
			      edit.addActionListener(new ActionListener(){
			    	   public void actionPerformed(ActionEvent e) {
			    		   
			    		   synList.setEnabled(true);
			    		   val.setEnabled(true);
			    		   edit.setEnabled(false);
			    	   }
			       });
		        
	    	
	}
	public boolean getStatus() {
		if (!val.isEnabled()) return true;
		else return true;
	}
	public SyntacticAnnotation getAnnot(int j) {
		return  listSyntFeatures.get(j);
	}
	public void makeDisable(boolean b) {
		if (b) {
			synList.setEnabled(false);
			val.setEnabled(false);
			edit.setEnabled(false);
		}
		else {
			synList.setEnabled(true);
			val.setEnabled(true);
			edit.setEnabled(true);
		}
	}
	public JList getSynList() {
		return synList;
	}
	public JPanel getPaneSynt() {
		return parts3;
	}
	  private ListCellRenderer<? super String> getRenderer() {
		    return new DefaultListCellRenderer() {
		      @Override
		      public Component getListCellRendererComponent(JList<?> list,
		          Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        JLabel listCellRendererComponent = (JLabel) super
		            .getListCellRendererComponent(list, value, index, isSelected,
		                cellHasFocus);
		        listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0,
		            0, 1, 0, Color.GRAY));
		        return listCellRendererComponent;
		      }
		    };
		  }
}
