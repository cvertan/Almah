package allmahVer4;
import java.util.ArrayList;
import java.awt.Color;
import org.jsoup.*;
public class GT1Element {
private String label;
private NT2Element parent;
private Reading reading;
private String confidence;

	public GT1Element(NT2Element n, Reading r) {
		parent=n;
		reading=r;
		//if (r!=null)
		   //  label=r.getReading();		
		//else 
			label=r.getReading();
			confidence=r.getConfidence();
	}
	
	public GT1Element copy() {
		GT1Element x= new GT1Element(parent, reading);
		return x;
	}
	public String getConfidence() {
		return confidence;
	}
public NT2Element getParent() {
	return parent;
}
	public void setLabel(String s) {
		label=s;
		
	}
	public String getSimpleLabel() {
		String s=label;
		if( label.indexOf("<html>")==0) s=Jsoup.parse(label).text();
		return s;
	}
	
	public String getLabel() {
		if( parent.getComponentElement()!=null) {
			Color cg=parent.getComponentElement().getcolor();
			String hex=String.format("#%02x%02x%02x", cg.getRed(), cg.getGreen(), cg.getBlue());
			return "<html><font color=\""+hex+"\">"+label+"</font><html>"; 
		}
		return label;
	}
}
