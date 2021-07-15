package allmahVer4;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.WindowConstants;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.JPanel;
 import javax.swing.JScrollPane;
 import javax.swing.JButton;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.ListCellRenderer;
 import java.awt.*;
 import javax.swing.DefaultListCellRenderer;
 import java.awt.Component;
 import javax.swing.BorderFactory;
 import javax.swing.JDesktopPane;
 import java.awt.event.*;
 import edu.mit.jwi.*;
 import edu.mit.jwi.item.*;
import javax.swing.*;
 import java.awt.*;
 import edu.mit.jwi.*;
 import edu.mit.jwi.item.*;
 import java.util.*;
 import javax.swing.text.*;
 import javax.swing.text.html.HTMLDocument;
 import javax.swing.text.html.HTMLEditorKit;
 import xmleditorkit.XMLEditorKit;
 import javax.swing.event.*;
 import java.awt.event.*;
 import javax.swing.JToggleButton.ToggleButtonModel;
public class ElemSemanticsPanel{
private boolean status;
JPanel  p;
IDictionary dict;
int posx;
int posy;
String label;
boolean noSem;
JList semList;
	public ElemSemanticsPanel(String s, IDictionary dict, AllmahGUI interf,int fx,int fy) {
		this.dict=dict;
		noSem=false;
		label=s;
		  p=new JPanel(new GridBagLayout());
		GridBagConstraints g2=new GridBagConstraints();
		status=false;
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
    	g2.gridwidth=2;
    	JMenuItem jmi1, jmi2, jmi3, jmi4;
    	   JButton val=new JButton("Validate");
	       JButton edit=new JButton("Edit");
    	JCheckBox enableSem=new JCheckBox("No Semantics");
    	enableSem.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ex) {
    			if (enableSem.isSelected()) {
    				status=true; noSem=true;
    				semList.setEnabled(false);
    				val.setEnabled(false);
    			}
    			else {
    				status=false; noSem=false;
    				semList.setEnabled(true);
    				val.setEnabled(true);
    			}
    		}
    	});
    	JCheckBox unknownSem=new JCheckBox("Unknown Meaning");
    	    unknownSem.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ex) {
    			if (unknownSem.isSelected()) {
    				status=true; 
    			   (  (DefaultListModel) semList.getModel()).insertElementAt( label+"?",0);
    				semList.setEnabled(false); val.setEnabled(false);
    			}
    			else {
    				status=false; 
    				semList.setEnabled(true);
    			}
    		}
    	});
		JLabel heading=new JLabel("Semantics for "+label);
		p.add(heading,g2);
		g2.gridy=1;
		p.add(enableSem,g2);
		g2.gridy=2;
		p.add(unknownSem,g2);
		g2.anchor=GridBagConstraints.NORTHWEST;
		g2.gridy=3;
		 DefaultListModel<String> listModel = new DefaultListModel<>();
		 for(int i=0;i<20;i++)
			 listModel.addElement("                             ");
		 
	        //create the list
	       semList = new JList<>(listModel);
	//       semList.addListSelectionListener(this);
	        JPopupMenu popupMenu = new JPopupMenu();
	        popupMenu.add(jmi1= new JMenuItem("Add"));
	        popupMenu.add(new JPopupMenu.Separator());
	        popupMenu.add(jmi2 = new JMenuItem("Delete"));
	        popupMenu.add(new JPopupMenu.Separator());
	        popupMenu.add(jmi3 = new JMenuItem("Unclear Meaning"));
	        popupMenu.add(jmi4 = new JMenuItem("Clear Meaning"));
	        semList.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	              // if right mouse button clicked (or me.isPopupTrigger())
	              if (SwingUtilities.isRightMouseButton(me)
	                  && !semList.isSelectionEmpty()
	                  && semList.locationToIndex(me.getPoint())
	                     == semList.getSelectedIndex()) {
	                      popupMenu.show(semList, me.getX(), me.getY());
	                      posx=me.getX();
	                      posy=me.getY();
	                      System.out.println("Clicked at "+ posx+ "  "+posy);
	                      }
	                  }
	               }
	            );
	        
	        jmi3.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		DefaultListModel dlm =
	        			       (DefaultListModel) semList.getModel();
	        		String s=""+dlm.getElementAt(semList.getSelectedIndex());
	        		if ((!s.equals("                             "))&&(!s.endsWith("?"))) {
	        			     dlm.insertElementAt( s+"?",semList.getSelectedIndex());
	        			     dlm.remove(semList.getSelectedIndex()+1);
	        		}
	        		
	        	}
	        });
	        jmi4.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		DefaultListModel dlm =
	        			       (DefaultListModel) semList.getModel();
	        		String s=""+dlm.getElementAt(semList.getSelectedIndex());
	        		if ((!s.equals("                             "))&&(s.endsWith("?"))) {
	        		       s=s.substring(0,s.length()-2);
	        			     dlm.insertElementAt( s,semList.getSelectedIndex());
	        			     dlm.remove(semList.getSelectedIndex()+1);
	        		}
	        	}
	        });
	        jmi1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		wordNetPanel wnp=new wordNetPanel(dict);
	        		JPanel WN=wnp.getWNPanel();
	        		ChildFrame meaningFrame= new ChildFrame("Select Meaning ",Color.BLUE,WindowConstants.DISPOSE_ON_CLOSE);
	        		meaningFrame.setSize(500, 700);
                    Container cont=meaningFrame.getContentPane();
                    cont.setLayout(new BorderLayout());
                    JButton selMeaning=new JButton("Select Meaning");
                    WN.setSize(500, 250);
            		   cont.add(WN,BorderLayout.CENTER);
            		   cont.add(selMeaning,BorderLayout.SOUTH);
            		   //
            		   selMeaning.addActionListener(new ActionListener() {
               			  public void actionPerformed(ActionEvent x) {
               				  //
               				 HTMLDocument doc = (HTMLDocument)wnp.getWNEditor().getDocument();
               				 String refMeaning=wnp.getMeaning();
               				 String sf="";
							        ElementIterator it = new ElementIterator(doc);
							        Element element;
							        int k=0;  int nr=0;
                            ArrayList<String> selectW=new ArrayList<String>();
							        while ( (element = it.next()) != null )
							        {
							            //System.out.println("*** "+element.getName()+"  "+element.getClass()+" **");
                                  if(element.getName().equals("input")){
                                	  k++; 
                                	  //System.out.println("Checkbox "+k);
							            AttributeSet as = element.getAttributes();
							            Enumeration enumm = as.getAttributeNames();
                                    boolean issel=false;
                                    String idWort="";
							            while( enumm.hasMoreElements() )
							            {
							                Object name = enumm.nextElement();
							                Object value = as.getAttribute( name );
							         
							               //System.out.println( "\t" + name + " : " + value );
                                       if((""+name).equals("id")) {
                                     	  //System.out.println("$$$"+value);
                                       idWort=""+value;
                                       System.out.println("IDLemma "+idWort);
                                       //System.out.println("\t Word"+getWord(idWort).getTranslitLabel(typScript));
                                       }
                                     
                                      if (value instanceof ToggleButtonModel)
							                {
							                	ToggleButtonModel model = (ToggleButtonModel)value;
							                	//if(global.isSelected()) model.setSelected(true);
							                	
							                	if( model.isSelected()) {
							                		//System.out.println("!!!!!"+ idWort+" "+getWord(idWort).getTranslitLabel(typScript));
							                	   nr=nr+1;
							                		int posC=idWort.indexOf("checked");
							                	    if (posC>=0) idWort=idWort.substring(0,posC);
							                	    selectW.add(idWort);
							                	    System.out.println("Selected Lemma"+ idWort);
							                	    if (nr<=1)
							                	    refMeaning=idWort;
							                	    else refMeaning=refMeaning+" OR "+idWort;
							                	//    fMeaning=resultMeaning+":"+idWort.substring(idWort.lastIndexOf("-")+1);
							                		issel=true;
							                	
							                	}
							                	
							                }
							                    
							                }
							           
                                  }
							            }
               				  
							        if(nr>1) {
						                 String ts=refMeaning;
						               refMeaning = JOptionPane.showInputDialog(meaningFrame, "Select just one meaning",ts);
								            }
							        // if (refMeaning!=null)
							        else if (nr==1)
               				      sf=refMeaning;
               				  else 
               					  //if( wnp.getSearchField().getText()!=null)
               					      sf= wnp.getSearchField().getText();
               				DefaultListModel dlm =
     	        			       (DefaultListModel) semList.getModel();
     	        			     dlm.insertElementAt( sf,semList.getSelectedIndex());
     	        			     if (dlm.get(semList.getSelectedIndex()+1).equals("                             "))
     	        			    	 dlm.remove(semList.getSelectedIndex()+1);
               				  meaningFrame.doDefaultCloseAction();
               				
               			  }
               		  });
                
            		   
            		   
            		  //  meaningFrame.pack();  
                 		meaningFrame.setVisible(true); 
            		   interf.addChild(meaningFrame,fx+posx+20,fy+posy+20,500, 700);		
                  		// meaningFrame.pack();  
                  		meaningFrame.setVisible(true); 
                  		  meaningFrame.moveToFront();
	        		
	        	/*	DefaultListModel dlm =
	        			       (DefaultListModel) semList.getModel();
	        			     dlm.insertElementAt( semList.getSelectedIndex()+"Inserted",semList.getSelectedIndex());
	        			     if (dlm.get(semList.getSelectedIndex()+1).equals("                             "))
	        			    	 dlm.remove(semList.getSelectedIndex()+1);*/
	        	}
	        	
	        });
	        jmi2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		DefaultListModel dlm =
	        			       (DefaultListModel) semList.getModel();
	        		     dlm.remove(semList.getSelectedIndex());
	        			   
	        			  
	        	}
	        	
	        });
	        semList.setCellRenderer(getRenderer());
	      p. setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	       p.add(new JScrollPane(semList),g2);
	       g2.anchor=GridBagConstraints.CENTER;
	       g2.gridwidth=1;
	       g2.gridy=4;
	    
	       p.add(val,g2); g2.gridx=1;
	       p.add(edit,g2);
	       
	       val.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent e) {
	    		   status=true;
	    		   semList.setEnabled(false);
	    		   val.setEnabled(false);
	    		   edit.setEnabled(true);
	    	   }
	       });
	       
	      edit.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent e) {
	    		   status=false;
	    		   semList.setEnabled(true);
	    		   val.setEnabled(true);
	    		   edit.setEnabled(false);
	    	   }
	       });
	       
	}
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean b) {
		status=b;
	}
	public JPanel getPanel() {
		return p;
	}
	public JList getList() {
		return semList;
	}
	public boolean hasNoSemantics() {
		return noSem;
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
