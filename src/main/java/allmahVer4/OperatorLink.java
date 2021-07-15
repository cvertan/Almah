package allmahVer4;
import java.awt.Color;
public class OperatorLink {
private String label;
private int typ;
private Color c;
	public OperatorLink(String s,int t) {
		label=s;
		typ=t;
	}
	public OperatorLink(String s,int t,Color c) {
		label=s;
		this.c=c;
		typ=t;
	}
	public OperatorLink copy() {
		return new OperatorLink(label,typ,c);
		
	}
	public Color getColor() {
		return c;
	}
  public String toString() {
	  return label;
  }
  public String getLabel() {
	  return label;
  }
  public void  setLabel(String s) {
	   label=s;
  }
  public int getTyp() {
	  return typ;
  }
}
