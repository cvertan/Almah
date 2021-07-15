package allmahVer4;
import java.util.ArrayList;
public abstract class GlyphNode {
private String label;
private GlyphNode parent;
private String typ;


	public GlyphNode(String s, GlyphNode p, String t) {
		label=s;
        parent=p;
        typ=t;
 
	}

	 public abstract String toString() ;
	 
	

	 public GlyphNode getParent() {
	return parent;
}

public void setParent(GlyphNode p) {
	parent=p;
}
public String getLabel() {
	return label;
}
public String getTyp() {
	return typ;
}
public void setTyp(String t) {
	typ=t;
}
public void setLabel(String s) {
	label=s;
}

public int noChild() {
	  int n=0;
	  if(((SegmentNode)this.getParent()!=null)) {
	  boolean found=false;
	  while((!found)&&( (n<((SegmentNode)this.getParent()).getChildrenNodes().size()))) {
		  if(( (SegmentNode)this.getParent()).getChildrenNodes().get(n).equals(this))
			   found=true;
		  else n=n+1;
	  }
	  if( found) return n+1;
	  else return n;
	  }
	  else return( (SegmentNode)this).getChildrenNodes().size();
}
}


