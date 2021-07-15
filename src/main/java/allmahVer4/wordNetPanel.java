package allmahVer4;
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
public class wordNetPanel {
 String refMeaning="";
 JPanel WN;
 JEditorPane     wordnet;
 JTextField searchMean;
 int nrsel=0;
	public wordNetPanel(IDictionary dict) {
		Font typFont1=new Font("Sans Serif",Font.PLAIN,16);
		JButton assign=new JButton("Assign");
		   JButton cancel=new JButton("Cancel");
		   JPanel buttons=new JPanel();
		  searchMean=new JTextField();
		      String Text="";
		 wordnet= new JEditorPane("text/html",Text);
		   wordnet.setSize(300, 200);
		         wordnet.setFont(typFont1);
		         String bodyRule = "body { font-family: " + typFont1.getFamily() + "; " +
		                 "font-size: " + typFont1.getSize() + "pt; }";
		         ((HTMLDocument)wordnet.getDocument()).getStyleSheet().addRule(bodyRule);
		         wordnet.setCaretPosition(0);
		         wordnet.setSize(500, 250);
		         MyLinkController controller = new MyLinkController();
			        wordnet.addMouseListener(controller);
// s=s+words.get(posTok-1).getTranslitLabel(typScript)+" 
//<a href=\"https://"+ indexIdWord.get(haveAnnot.get(i)).intValue()+"\">"+words.get(posTok).getTranslitLabel(typScript)+"</a>" +"<input type='checkbox' id='"+haveAnnot.get(i)+"'> "+words.get(posTok+1).getTranslitLabel(typScript)+"<br/>";
 				  
		   JButton searchwn=new JButton("Wordnet Synset");
		  
		 	wordnet.addHyperlinkListener(new HyperlinkListener() {
			    public void hyperlinkUpdate(HyperlinkEvent e) {
			        if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			        	String s=e.getURL().toString().substring(7);
			        System.out.println("Lemma selected "+s);
			        refMeaning=s; nrsel=nrsel+1;
			        if (nrsel>1) JOptionPane.showInputDialog("Please select just one item");
			         //  System.out.println("Event on "+ s);
			        }
			        else nrsel=nrsel-1; 
			    }
			});
		assign.addActionListener(new ActionListener(){
		   		
		   		public void actionPerformed(ActionEvent e){
		   			
		   		 HTMLDocument doc = (HTMLDocument)wordnet.getDocument();
			        ElementIterator it = new ElementIterator(doc);
			        Element element;
			        int k=0; int nrsel=0;
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
			                	    int posC=idWort.indexOf("checked");
			                	    if (posC>=0) idWort=idWort.substring(0,posC);
			                	    selectW.add(idWort);
			                	    System.out.println("Selected Lemma"+ idWort);
			                	//    fMeaning=resultMeaning+":"+idWort.substring(idWort.lastIndexOf("-")+1);
			                		issel=true;
			                	}
			                	
			                }
			                    
			                }
                  }
			            }
		//   			showTokenised.doDefaultCloseAction();
		   		}
		   		
		   	});
		   searchwn.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   wordnet.setText("");
				   try {
				
			 String meanings="";
				    IIndexWord idxWord_N = dict . getIndexWord (searchMean.getText(), POS.NOUN) ;
				    IIndexWord idxWord_A = dict . getIndexWord (searchMean.getText(), POS.ADJECTIVE) ;
				    IIndexWord idxWord_Ad = dict . getIndexWord (searchMean.getText(), POS.ADVERB) ;
				    IIndexWord idxWord_V = dict . getIndexWord (searchMean.getText(), POS.VERB) ;
				    if(idxWord_N!=null) {
				    	meanings="<b>NOUN</b>";
				System.out.println(searchMean.getText() +" has " +idxWord_N.getWordIDs().size()+ " senses" );
				    for (int j=0;j< idxWord_N.getWordIDs().size();j++) {
				   IWordID wordID = idxWord_N . getWordIDs().get(j) ;
				  IWord word = dict.getWord(wordID) ;
				 String ids="Id = " + wordID ;
				 String lemma= " Lemma = " + word.getLemma () ;
				  String gloss=" Gloss = " + word . getSynset () . getGloss ()  ;
				 meanings=meanings+"<br/><input type='checkbox' id='"+wordID+"'> <br/>"+ " <a href=\"https://"+lemma+"\">"+lemma+"</a><br/>"+gloss;
				 //"<input type='checkbox' id='"+wordID+"'> <br/>"+
				 ISynset synset = word . getSynset () ;
				 			       String synonyms="<br/><i> Synonyms</i><br/>" ;            		
				   for( IWord w : synset . getWords () )
				             synonyms=synonyms+  w.getLemma() + ";"+"  " ;
				   meanings=meanings+synonyms;
				   wordnet.setText(meanings);
				    }
				}
				    if(idxWord_A!=null) {
				    	meanings=meanings+"<br/><b>ADJECTIVE</b>";
				System.out.println(searchMean.getText() +" has " +idxWord_A.getWordIDs().size()+ " senses" );
				    for (int j=0;j< idxWord_A.getWordIDs().size();j++) {
				   IWordID wordID = idxWord_A . getWordIDs().get(j) ;
				  IWord word = dict.getWord(wordID) ;
				 String ids="Id = " + wordID ;
				 String lemma= " Lemma = " + word.getLemma () ;
				  String gloss=" Gloss = " + word . getSynset () . getGloss ()  ;
				 meanings=meanings+"<br/><input type='checkbox' id='"+wordID+"'> <br/>"+ " <a href=\"https://"+lemma+"\">"+lemma+"</a><br/>"+gloss;
				 //"<input type='checkbox' id='"+wordID+"'> <br/>"+
				 ISynset synset = word . getSynset () ;
				 			       String synonyms="<br/><i> Synonyms</i><br/>" ;            		
				   for( IWord w : synset . getWords () )
				             synonyms=synonyms+  w.getLemma() + ";"+"  " ;
				   meanings=meanings+synonyms;
				   wordnet.setText(meanings);
				    }
				}
				    if(idxWord_Ad!=null) {
				    	meanings=meanings+"<br/><b>ADVERB</b>";
				System.out.println(searchMean.getText() +" has " +idxWord_Ad.getWordIDs().size()+ " senses" );
				    for (int j=0;j< idxWord_Ad.getWordIDs().size();j++) {
				   IWordID wordID = idxWord_Ad . getWordIDs().get(j) ;
				  IWord word = dict.getWord(wordID) ;
				 String ids="Id = " + wordID ;
				 String lemma= " Lemma = " + word.getLemma () ;
				  String gloss=" Gloss = " + word . getSynset () . getGloss ()  ;
				 meanings=meanings+"<br/><input type='checkbox' id='"+wordID+"'> <br/>"+ " <a href=\"https://"+lemma+"\">"+lemma+"</a><br/>"+gloss;
				 //"<input type='checkbox' id='"+wordID+"'> <br/>"+
				 ISynset synset = word . getSynset () ;
				 			       String synonyms="<br/><i> Synonyms</i><br/>" ;            		
				   for( IWord w : synset . getWords () )
				             synonyms=synonyms+  w.getLemma() + ";"+"  " ;
				   meanings=meanings+synonyms;
				   wordnet.setText(meanings);
				    }
				}
				    if(idxWord_V!=null) {
				    	meanings=meanings+"<br/><b>VERB</b>";
				System.out.println(searchMean.getText() +" has " +idxWord_V.getWordIDs().size()+ " senses" );
				    for (int j=0;j< idxWord_V.getWordIDs().size();j++) {
				   IWordID wordID = idxWord_V . getWordIDs().get(j) ;
				  IWord word = dict.getWord(wordID) ;
				 String ids="Id = " + wordID ;
				 String lemma= " Lemma = " + word.getLemma () ;
				  String gloss=" Gloss = " + word . getSynset () . getGloss ()  ;
				 meanings=meanings+"<br/><input type='checkbox' id='"+wordID+"'> <br/>"+ " <a href=\"https://"+lemma+"\">"+lemma+"</a><br/>"+gloss;
				 //"<input type='checkbox' id='"+wordID+"'> <br/>"+
				 ISynset synset = word . getSynset () ;
				 			       String synonyms="<br/><i> Synonyms</i><br/>" ;            		
				   for( IWord w : synset . getWords () )
				             synonyms=synonyms+  w.getLemma() + ";"+"  " ;
				   meanings=meanings+synonyms;
				   wordnet.setText(meanings);
				    }
				}
				   } catch(Exception ex) {System.out.println("Error wordnet");}
			   }
		   });
		
		
		
		
		
		
		
	 WN=new JPanel();
		WN.setLayout(new BorderLayout());
		   WN.add(searchMean, BorderLayout.NORTH);
		   WN.add(new JScrollPane(wordnet), BorderLayout.CENTER);
		   WN.add(searchwn, BorderLayout.SOUTH);
	}
public JPanel getWNPanel() {
	return WN;
}
public JTextField getSearchField() {
	return searchMean;
}
public JEditorPane getWNEditor() {
	return wordnet;
}
public String getMeaning() {
	return refMeaning;
}
}
