package allmahVer4;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import edu.mit.jwi.*;
import edu.mit.jwi.item.*;
import java.awt.event.*;
import java.awt.GridBagLayout;
public class TranslationPanel {
private JPanel panel;
private JList listSynonims;
private JTextField newVal;
private JTextField text;
private JCheckBox dictionaryEntry;
private JCheckBox partMU;
private JLabel orig;
	public TranslationPanel(GlossingVariant p, IDictionary dict, JTextArea fin1, JTextArea fin2) {
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		orig= new JLabel(p.getParent().calculateLabel2());
		JCheckBox dictionaryEntry=new JCheckBox("Entry in Dictionary");
		if (orig.getText().equals("") )dictionaryEntry.setSelected(false);
		else dictionaryEntry.setSelected(true);
		JLabel syntax=new JLabel(p.getSyntax().getSyntFeature().getAbbreviation());
		partMU=new JCheckBox("part of");partMU.setSelected(false);
		DefaultListModel<String> listModel = new DefaultListModel<>();
		
		String meaningID="";
		JTextArea explanation=null;
		JLabel pos=null;
		if (!p.getSemantics().getLinkWN().isEmpty()) {
			meaningID=p.getSemantics().getLinkWN()+"-"+p.getSemantics().getMeaning();
			System.out.println("Meaning Id "+ meaningID);
			IWordID wordId=WordID.parseWordID(meaningID);
			  IWord word = dict.getWord(wordId) ;
			  explanation=new JTextArea(10,7);
			  explanation.setLineWrap(true);
			 explanation.setWrapStyleWord(true);
			  explanation.setText(word . getSynset () . getGloss ()  );
			  explanation.setEditable(false);
			  pos=new JLabel(""+word.getPOS());
			  ISynset synset = word . getSynset () ;
				
				  for( IWord w : synset . getWords () )
					  listModel.addElement(w.getLemma());
			        //create the list
			      
			    
		}  
		else listModel.addElement(p.getSemantics().getMeaning());
		 listSynonims = new JList<>(listModel);
		   listSynonims.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	              // if right mouse button clicked (or me.isPopupTrigger())
	            	 if (me.getClickCount() == 2) {
	                     newVal.setText(""+listSynonims.getSelectedValue());
	                  }
	                  }
	               }
	            );
			       JButton editB=new JButton("Edit");
			       newVal=new JTextField(25);
			       newVal.setEditable(false);
			       editB.addActionListener(new ActionListener() {
			    	   public void actionPerformed(ActionEvent e) {
			    		   newVal.setEditable(true);
			    	   }
			       });
			    
		
			       JButton useB=new JButton("Use");
			       useB.addActionListener(new ActionListener() {
			    	   public void actionPerformed(ActionEvent e) {
			    		   String s=fin1.getText();
			    		   if(s.isEmpty())fin1.setText(orig.getText());
			    		   else fin1.setText(s+" "+orig.getText());
			    		   s=fin2.getText();
			    		   if(s.isEmpty())fin2.setText(newVal.getText());
			    		   else fin2.setText(s+" "+newVal.getText());
			    	   }
			    	   
			       });
			       GridBagConstraints g2=new GridBagConstraints();
					g2.gridx=0;
			    	g2.gridy=0;
			    	g2.weightx=0.0;
			    	g2.weighty=0.0;
			    	g2.anchor=GridBagConstraints.CENTER;
			    	g2.fill=GridBagConstraints.BOTH;
			    	g2.insets.top=5;
			    	g2.insets.bottom=5;
			    	g2.insets.left=5;
			    	g2.insets.right=5;
			 
			    	g2.gridwidth=1;
			    	panel.add(orig,g2); g2.gridx=1; panel.add(dictionaryEntry,g2);
			    	g2.gridx=0;
			    	g2.gridwidth=2;
			    	g2.gridy=1;
			    	panel.add(syntax,g2);
			    	if(!meaningID.isEmpty()) {
			    		g2.gridy=2;
			    		panel.add(new JScrollPane(explanation),g2);
			    		g2.gridy=3;
			    		panel.add(listSynonims,g2);
			    		g2.gridy=4;
			    		panel.add(pos,g2);
			    		g2.gridy=5;g2.gridwidth=1;
			    		g2.fill=GridBagConstraints.BOTH;
			    		panel.add(editB,g2);
			    		g2.fill=GridBagConstraints.BOTH;
			    		g2.gridx=1;panel.add(newVal,g2);
			    		g2.gridwidth=2; g2.gridx=0; g2.gridy=6;
			    		g2.fill=GridBagConstraints.BOTH;
			    		panel.add(useB,g2);
			    		g2.gridy=7;
			    		panel.add(partMU,g2);
			    	}
			    	else {
			    		g2.gridy=2;
			    		panel.add(listSynonims,g2);
			    		g2.gridy=3;g2.gridwidth=1;
			    		g2.fill=GridBagConstraints.BOTH;
			    		panel.add(editB,g2); g2.gridx=1;g2.fill=GridBagConstraints.BOTH;
			    		panel.add(newVal,g2);
			    		g2.gridwidth=4; g2.gridx=0; g2.gridy=4;g2.fill=GridBagConstraints.BOTH;g2.anchor=GridBagConstraints.CENTER;
			    		panel.add(useB,g2);
			    		g2.gridy=5;
			    		panel.add(partMU,g2);
			    	}
			    	
		//}
	}
public JPanel getPanelTr() {
	return panel;
}
public void setTextZone(JTextField t) {
	text=t;

}
public String getTextZone() {
	return text.getText();
}
public String getTranslation() {
	return newVal.getText();
}
public boolean getPartMUState() {
	if (partMU.isSelected()) return true;
	else return false;
	
}
public void setPartMU(boolean b) {
	partMU.setSelected(b);
}
public String getOrig() {
	return orig.getText();
}
public String getTr() {
	return newVal.getText();
}
}
