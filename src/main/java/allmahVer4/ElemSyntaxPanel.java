package allmahVer4;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.util.*;
import java.awt.*;
public class ElemSyntaxPanel {
private MorphoSyntGlossing msg;
private JPanel p;
private JTree  treePOS;
private JTable selectedTable;
private JMenuItem[] jmi1, jmi2;
ArrayList<MorphoSyntGlossing> msgg;
int posx, posy;
boolean select;
int selind;
int sellist;
//private JList syntList;
int x;
private JButton[] val,edit;
private int status1;
private JButton generate;
private JList[] synList;
ArrayList<ArrayList<GlossingVariant>> gset;
JPanel right;
String nodeValue;
SyntPanel[] parts3;
JPopupMenu[] popupMenu ;
//DefaultListModel<String>[] listModel;
private TreePoS trpos;
private ChildFrame syntaxFrame;
DefaultMutableTreeNode tModel;
LinguisticTables t;
JList selList;
JScrollPane scrollFeat, scrollPos;
Map<String, SyntacticFeature> msyn=new HashMap<String,SyntacticFeature>();
AllmahGUI interf;
SyntacticAnnotation sa;
ArrayList<SyntacticAnnotation> sal;
ChildFrame win;
String id;
private boolean status;
	public ElemSyntaxPanel(MorphoSyntGlossing msg, TreePoS trposf, AllmahGUI interf, ChildFrame morphoGlossFrame) {
		this.msg=msg;
		trpos=trposf;
		select=true;
		win=morphoGlossFrame;
		this.interf=interf;
		status=true;
		t=new LinguisticTables();
		sal=new ArrayList<SyntacticAnnotation>();
		id=msg.getId();
		System.out.println("ID after Semantics "+id);
		 tModel=trpos.createNodes();
		 String[][] rec = {
				   { "  ", "  ", "  " },
				   { "  ", "  ", "  " },
				   { "  ", "  ", "  " },
				   { "  ", "  ", "  " },
				   { "  ", "  ", "  " },
				   { "  ", "  ", "  " },
				};
		 selectedTable=new JTable(rec, t.colPoSValues);
		 treePOS=new JTree(tModel);
		    treePOS.expandPath(new TreePath(treePOS.getModel().getRoot()));
		    treePOS.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		    //
		   
		  
		  //  val=new JButton[msg.getElements().size()]; 
		    //edit=new JButton[msg.getElements().size()]; 
		    //
	//	synList=new JList[msg.getElements().size()];
		    parts3=new SyntPanel[msg.getElements().size()];
		status1=0;
		
		generate =new JButton("Generate Syntax Variants");
		//popupMenu=new JPopupMenu[msg.getElements().size()];
        //  JMenuItem jmi2=new JMenuItem[msg.getElements().size()];
        //  jmi1=new JMenuItem[msg.getElements().size()];
       // listModel= new DefaultListModel[msg.getElements().size()];
		  p=new JPanel(new GridBagLayout());
		  p. setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
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
	   	g2.gridwidth=msg.getElements().size();
	   // 	g2.gridwidth=1;
	    	JCheckBox sel=new JCheckBox("Dismiss Alternative");
	    
			p.add(sel,g2);
	    		
	    	   JPanel parts1=new JPanel(new GridLayout(1,msg.getElements().size()));
	    	   parts1. setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
	    	   JPanel cp=new JPanel();
	    	for(int i=0;i<msg.getElements().size();i++) {
	    		JLabel part1=new JLabel(msg.getElements().get(i).getParent().calculateLabel1());
	    		System.out.println("Label "+ part1);
	    		parts1.add(part1);
	    	}
	    	g2.gridy=1;
	    	p.add(parts1,g2);
	    	 JPanel parts2=new JPanel(new GridLayout(1,msg.getElements().size()));
	    	for(int i=0;i<msg.getElements().size();i++) {
	         
	    		JLabel part2=new JLabel(msg.getElements().get(i).getSemantics().getMeaning());
	    		parts2.add(part2);
	    	
	    	}
	    	g2.gridy=2;
	    	p.add(parts2,g2);
	    	
	    	g2.gridy=3;
	    	g2.gridwidth=1;
	    	 //JPanel parts3=new JPanel(new GridLayout(1,msg.getElements().size()));
	    	for(int i=0;i<msg.getElements().size();i++) {
	    		parts3[i]=new SyntPanel(msg.getElements().get(i).getSemantics().getMeaning(),trpos,interf,win );
	             g2.gridx=i;
	    	p.add(parts3[i].getPaneSynt(),g2);
	    	}
	    	
	    
	    	g2.gridy=4;
	    	g2.gridwidth=msg.getElements().size();
	    	g2.gridx=0;
	    	g2.fill=GridBagConstraints.HORIZONTAL;
	    	p.add(generate,g2);
	    	sel.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			if (sel.isSelected()){
	    				for(int i=0;i<msg.getElements().size();i++)
	    					parts3[i].makeDisable(true);
	    				generate.setEnabled(false);
	    				status=false;
	    			}
	    			else{
	    				for(int i=0;i<msg.getElements().size();i++)
	    					parts3[i].makeDisable(false);
	    				generate.setEnabled(true);
	    				status=true;
	    			}
	    		}
	    	});
	    	
	    	 gset=new ArrayList<ArrayList<GlossingVariant>>();
	    	generate.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent w) {
	    			// generate for one sem all synt values

        				boolean isvalid=true; 
        				for(int i=0;i<parts3.length;i++)
        					isvalid=isvalid && parts3[i].getStatus();
        				if(isvalid){
        					//currentEl.get(i);
        					gset.clear();
        					for (int i=0;i<parts3.length;i++) {
        						ArrayList<GlossingVariant> gvar=new ArrayList<GlossingVariant>();     					
        						for (int j=0;j<parts3[i].getSynList().getModel().getSize();j++) {
        							
        								if(!parts3[i].getSynList().getModel().getElementAt(j).equals("        ")) {
        								//String s=""+parts3[i].getSynList().getModel().getElementAt(j);
        								//SyntacticAnnotation synA= sa=new SyntacticAnnotation(s);
        								SyntacticAnnotation synA= sa=parts3[i].getAnnot(j);
        									
        							GlossingVariant gnode=new GlossingVariant(msg.getElements().get(i).getParent(),synA,msg.getElements().get(i).getSemantics());
    							        gvar.add(gnode);	
        								}
        								
        						
        						
        					}
        						
        					if(gvar.size()>0)
        						gset.add(gvar);
        					
        				}
        			//
        				
        					 msgg=new ArrayList<MorphoSyntGlossing>();
        					int nr=0;
        					MorphoSyntGlossing msg1 = new MorphoSyntGlossing(id,interf);
        					msgg.add(msg1);
        					for (int i=0;i<gset.size();i++) {
        						
        						if (gset.get(i).size()==1) {
        							for (int j=0;j<msgg.size();j++) {
        								
        							msgg.get(j).getElements().add(gset.get(i).get(0));
        							}
        						}
        						else {
        							ArrayList<GlossingVariant> elx=new ArrayList<GlossingVariant>();
        							for (int j=0;j<gset.get(i).size();j++) {
        								GlossingVariant nt1aux=gset.get(i).get(j);
        								
        							    elx.add(nt1aux);
        							}  
        							ArrayList<MorphoSyntGlossing> msgaux=new ArrayList<MorphoSyntGlossing>();
        							
        					        for (int k=0;k<msgg.size();k++) {
        					       
        						        msgg.get(k).getElements().add(elx.get(0));
        						        
        					        	
        					        }
        					        for(int l=1;l<elx.size(); l++) {
        					        
        					        	for(int k=0;k<msgg.size();k++) {
        					        		nr=nr+1;
        					        		MorphoSyntGlossing nx=msgg.get(k).copy();
        					        		nx.setId(id+nr);
        					        		nx.getElements().remove(nx.getElements().size()-1);
        					        		nx.getElements().add(elx.get(l));
        					        	//System.out.println("Intermediate"+ nx.calculateLabel());
        					        		msgaux.add(nx);
        					        	}
        					        }
        					        
        					        if(!msgaux.isEmpty()) {
        					         for (int k=0;k<msgaux.size();k++) {
        					        	 msgg.add(msgaux.get(k));
        					         }     
        					         msgaux.clear();
        					        }
        						}
        					}
        					for (int i=0;i<msgg.size();i++) {
        						//ntr1.add(nt1.get(i).getId());
        			//		System.out.println("MSG "+ msgg.get(i).calculateLabel());
        					//	interf.nt1.add(nt1.get(i));
        						//interf.mnt1.put(nt1.get(i).getId(),new Integer(interf.nt1.size()-1));
        					
        				
        					}
        					/*g2.weighty=1.0;
                    		g2.gridwidth=1;
                    		g2.gridy=3;*/
        					//JPanel content2=new JPanel(new GridLayout(1,msgg.size()));
        					g2.gridy=5;
        					DefaultListModel<String>	 lm = new DefaultListModel<String>();
        					
        				        //create the list
        				 JList endVariants = new JList<>(lm);
                    		for(int i=0;i<msgg.size();i++) {
                    		     String s=msgg.get(i).calculateLabel();
                    			lm.add(i,msgg.get(i).calculateLabel());
                    		}
                    		
                    		p.add(new JScrollPane(endVariants),g2);
                    		p.revalidate();
                    		p.repaint();
        			//		
        			}
        			}
        		
	    			
	    			
	    		//	
	    		
	    	});
	}
public boolean isSelect() {
	return select;
}
public void setSelect(boolean b) {
	select=b;
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
	public SyntacticAnnotation  getSyntAnnot() {
		return sa;
	}
public JList[] getList() {
		return synList;
	}
public ArrayList<MorphoSyntGlossing> getMorphoGlossing(){
	/*for (int i=0;i<msgg.size();i++) {
		System.out.println("ID Variant "+msg.getId() );
		String s=msg.getId().substring(0,msg.getId().lastIndexOf("_")+1);
		msgg.get(i).setId(s+i);
	}*/
	return msgg;
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
