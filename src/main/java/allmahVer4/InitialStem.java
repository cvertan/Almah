package allmahVer4;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class InitialStem {
	JPanel stem;
	JRadioButton consonantButton;
	JRadioButton vowelButton;
	JRadioButton none;
	ButtonGroup groupStem;
	public InitialStem(String s){
		stem = new JPanel(new GridBagLayout());
    	stem.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	//number.setPreferredSize(new Dimension(300,100));
    	 GridBagConstraints g1=new GridBagConstraints();
    	 JLabel numberLabel=new JLabel("Initial Stem");
    	// numberLabel.setForeground(Color.BLUE);
    	  consonantButton = new JRadioButton("Consonant");
    	 vowelButton = new JRadioButton("Vowel");
    	  none=new JRadioButton("None");
    	  if (s.equalsIgnoreCase("Consonant")) consonantButton.setSelected(true);
    	  else if(s.equalsIgnoreCase("Vowel")) vowelButton.setSelected(true);
    	  else none.setSelected(true);
    	 groupStem = new ButtonGroup();
    	 groupStem.add(consonantButton);
    	 groupStem.add(vowelButton);
    	 groupStem.add(none);
 
    	 g1.gridx=0;
	    	g1.gridy=0;
	    	g1.weightx=0.0;
	    	g1.weighty=0.0;
	    	g1.anchor=GridBagConstraints.NORTHWEST;
	    	g1.fill=GridBagConstraints.BOTH;
	    	g1.insets.top=5;
	    	g1.insets.bottom=2;
	    	g1.insets.left=5;
	    	g1.insets.right=5;
	    	g1.gridwidth=1;
	    	stem.add(numberLabel,g1);
	    	g1.gridwidth=1;
	    	g1.gridy=1;
	    	stem.add(consonantButton,g1);
	    	g1.gridy=2;
	    	stem.add(vowelButton,g1);
	    	
	}
	public JPanel getStemPanel(){
		return stem;
	}
	public String getNumberValue(){
		String val="";
		if(consonantButton.isSelected()) val="Consonant";
		else if (vowelButton.isSelected()){
			val="Vowel";
		}
		else val="";
		return val;
	}
  public ButtonGroup getNumberGrp(){
	  return groupStem;
  }
  public void clearValues(){
	  none.setSelected(true);
  }
  public JRadioButton getNumber1(){
	  return consonantButton;
  }
  public JRadioButton getNumber2(){
	  return vowelButton;
  }
  public JRadioButton getNumberVoid(){
	  return none;
  }
}


