package allmahVer4;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JLabel;
public class GlyphElementNode extends GlyphNode {
private String label;
private String position;
private String damage;
private ArrayList<Reading> readings;
private JLabel uri;
private Map<String, ArrayList<Reading>> alternatives;
private Map<String, ArrayList<JLabel>> alternativesUri;
private ArrayList<GlyphElementNode> children;
private ArrayList<NT1Element> nt1el;
	public GlyphElementNode(String s, GlyphNode p) {
		super(s,p,"glyph");
		children=new ArrayList<GlyphElementNode>();
		readings=new ArrayList<Reading>();
		uri=new JLabel();
		alternatives=new HashMap<String,ArrayList<Reading>>();
		alternativesUri=new HashMap<String,ArrayList<JLabel>>();
	nt1el=new ArrayList<NT1Element>();
	}
	public GlyphElementNode(String s, GlyphNode p, ArrayList<Reading> r) {
		super(s,p,"glyph");
		children=new ArrayList<GlyphElementNode>();
		readings=new ArrayList<Reading>();
		uri=new JLabel();
		if(r!=null) {
	    for (int i=0;i<r.size();i++) {
	    	readings.add(new Reading(r.get(i).getReading(),r.get(i).getConfidence(),r.get(i).getTyp(),r.get(i).getState()));
	    }
	    }
	    alternatives=new HashMap<String,ArrayList<Reading>>();
	    alternativesUri=new HashMap<String,ArrayList<JLabel>>();
	    nt1el=new ArrayList<NT1Element>();
	}
	public JLabel getURI() {
		return uri;
	}
public void setURI(JLabel s) {
	uri=s;
}
public String toString() {
	if (alternatives.isEmpty()){
	return this.getLabel();
	}
	else {
		String complabel=this.getLabel()+"\n";
		  for (Map.Entry<String, ArrayList<Reading>> entry : alternatives.entrySet()) {
	            complabel= complabel +entry.getKey()+"; ";
	        }
		  return complabel.substring(0,complabel.length()-2);
	}
}
	public Map<String, ArrayList<Reading>> getAlternatives(){
		return alternatives;
	}
	public Map<String, ArrayList<JLabel>> getAlternativesURI(){
		return alternativesUri;
	}
public ArrayList<Reading> getReadings(){
	return readings;
}
public ArrayList<Reading> getReadings(String s){
	return alternatives.get(s);
}
public ArrayList<JLabel> getAlternativeURI(JLabel s){
	return alternativesUri.get(s);
}
	
	public ArrayList<NT1Element>  getNT1Elements(){
		return nt1el;
	}
}
