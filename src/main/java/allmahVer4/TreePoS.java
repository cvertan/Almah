package allmahVer4;

import javax.swing.tree.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * builds the tree of PoS for the Search panel as well as for the linguistic annotation
 * @author Cristina Vertan
 * @see TagGUI
 *
 */
 public class TreePoS  {
	 String[] PoSValues= {"Pronominal","Verb","Adjective","Adverb","Noun","Numeral", "Particle", "Preposition","Expletive, Exclamation"};
	String[] Pronominal={"Absolutive Case","Ergative Case", "Independent or Demonstrative Pronoun"};
	String[] Verb={"Verb Lexical", "Thematic Suffixes", "Valency Decreasing / Increasing Suffixes", "Verbal Derivation"};
	String[] Adjective= {"Adjective Lexical","Adjectival Derivation"};
	String[] Adverb= {"Adverb Lexical","Adverb Bound"};
	String[] Noun= {"Noun Lexical","Noun Inflection","Noun Derivation"};
	String[] Numeral= {"Numeral Lexical","Numeral Bound"};
	String[] Particle = {"Verbal Clitics", "Other Particle"};

	

	public DefaultMutableTreeNode createNodes() {
	
	    DefaultMutableTreeNode category = null;
	    DefaultMutableTreeNode category1 = null;
	    DefaultMutableTreeNode category2 = null;
	    DefaultMutableTreeNode category3 = null;
	    DefaultMutableTreeNode category4 = null;
	    DefaultMutableTreeNode category5 = null;
	    DefaultMutableTreeNode category6 = null;
	    DefaultMutableTreeNode category7 = null;
	  /*  DefaultMutableTreeNode category8 = null;
	    DefaultMutableTreeNode category9 = null;*/
	    DefaultMutableTreeNode top = null;
	    DefaultMutableTreeNode POS = null;
	    /*
	     1.Person Marker and Pronouns
 
	     */
	  
	   top = new DefaultMutableTreeNode("POS");
	    category1 = new DefaultMutableTreeNode("Person Marker and Pronouns");
	    category2 = new DefaultMutableTreeNode("Verb");
	    category3 = new DefaultMutableTreeNode("Adjective");
	    category4 = new DefaultMutableTreeNode("Adverb");
	    category5 = new DefaultMutableTreeNode("Noun");
	    category6 = new DefaultMutableTreeNode("Numeral");
	    category7 = new DefaultMutableTreeNode("Particle");
	    int k=0;
	    for (int i=0;i<Pronominal.length;i++){
	    //original Tutorial
	       POS = new DefaultMutableTreeNode(Pronominal[i]);
	     category1.add(POS);
	    }
	  top.add(category1);
	   for (int i=0;i<Verb.length;i++){
		    //original Tutorial
		       POS = new DefaultMutableTreeNode(Verb[i]);
		     category2.add(POS);
		    }
		  top.add(category2);
		   for (int i=0;i<Adjective.length;i++){
			    //original Tutorial
			       POS = new DefaultMutableTreeNode(Adjective[i]);
			     category3.add(POS);
			    }
			
			  top.add(category3);
			   for (int i=0;i<Adverb.length;i++){
				    //original Tutorial
				       POS = new DefaultMutableTreeNode(Pronominal[i]);
				     category4.add(POS);
				    }
				
				  top.add(category4);
				   for (int i=0;i<Noun.length;i++){
					    //original Tutorial
					       POS = new DefaultMutableTreeNode(Noun[i]);
					     category5.add(POS);
					    }
					
					  top.add(category5);
					   for (int i=0;i<Numeral.length;i++){
						    //original Tutorial
						       POS = new DefaultMutableTreeNode(Numeral[i]);
						     category6.add(POS);
						    }
						
						  top.add(category6);
						   for (int i=0;i<Particle.length;i++){
							    //original Tutorial
							       POS = new DefaultMutableTreeNode(Particle[i]);
							     category7.add(POS);
							    }
							
							  top.add(category7);

	    category = new DefaultMutableTreeNode("Preposition");
	    top.add(category);	  
   
	    return top;
	    
}
	
}

