package allmahVer4;
import java.util.ArrayList;
import java.awt.Color;
import org.jsoup.*;
public class ComponentElement {
	
	Color c;
	ArrayList<GlyphNode> elem;
	String label="CGlyph:";
	String label1="CGlyph~";
	
	public ComponentElement(Color c, ArrayList<GlyphNode> e) {
		
		this.c=c;
	
		elem=new ArrayList<GlyphNode>();
		for(int i=0;i<e.size();i++) {
			elem.add(e.get(i));
			label=label+Jsoup.parse(e.get(i).getLabel()).text()+";";
		}
		label=label.substring(0,label.length()-1);
	}
public ComponentElement(Color c) {
		
		this.c=c;
		elem=new ArrayList<GlyphNode>();
		
		
	}

	public Color getcolor() {
		return c;
	}
	public String getLabel() {
		
		return label;
	}
	public String getLabel1() {
		
		return label1;
	}
public void setLabel2(String s) {
		
		label1="CGlyph~"+s;
	}
public void setLabel1(String s) {
	
	label1=s;
}
	
	public void setLabel() {
		label="CGlyph~";
			for(int i=0;i<elem.size();i++) {
				
				label=label+Jsoup.parse(elem.get(i).getLabel()).text()+";";
			}
			label=label.substring(0,label.length()-1);
		
	}
	public ArrayList<GlyphNode> getElements(){
		return elem;
	}
	/*public String encodeComponentColor() {
		String hex=String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
		
		return this.getId()+"*"+hex;
	}*/

}
