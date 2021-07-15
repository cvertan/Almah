package allmahVer4;
import java.awt.Color;
import java.util.ArrayList;
public class ComplexGlyph {
 private String label;
 private String numLabel;
 private Color c;
 private ArrayList<NT2Element> glyphs;
	public ComplexGlyph(ArrayList<NT2Element> n2, Color c) {
		for (int i=0;i<n2.size();i++) {
		//	n2.get(i).setComplexGlyph(this);
			this.c=c;
			numLabel=n2.get(i).getLabel()+";";
			n2.get(i).setLabel("<html><font color=\""+encodeComponentColor()+"\">"+n2.get(i).getLabel()+"</font></html>");
			
		}
		numLabel=numLabel.substring(0,numLabel.length()-1);
		                                                        
	}
public Color getColor() {
return c;
}
public String encodeComponentColor() {
	String hex=String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
	return hex;
}
public void setColor(Color c) {
	this.c=c;
}
}
