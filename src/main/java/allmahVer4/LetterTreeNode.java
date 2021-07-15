package allmahVer4;

import java.util.ArrayList;
import java.util.Map;
import org.jsoup.*;
public abstract class LetterTreeNode {
    private String label;
    private int level;
    private boolean finish;
    private boolean del;
   private String fin;
   boolean modif;
  
	public LetterTreeNode(int n, boolean b) {
		level=n;
		finish=b;
		del=false;
	modif=false;
	}
	//1 - wörter 2-Leters 3 -eingefügte Letters 4-Eingefügte Wörter
	
    public String  getNodeLabel() {
  //  if (!modif) {
    	String s=calculateLabel();
    	
    	 if( level==3) return  "<html><font color=\"red\"> <b>"+s+ "</b></font></html>";
    	else if( level==4) return  "<html><font color=\"blue\"> <b>"+s+" </b> </font></html>";
    	else if (level ==5) {
    		if (del) return  "<html><font color=\"#d3d3d3\"> <b><strike>"+s+" </strike></b> </font></html>";
    		else return s;
    	}
    	else if(level==-1)
    		return  "<html><span style=\"background-color: yellow;\"> "+s+" </span></html>";
    		//style="background-color: steelblue;"
    	
    	else return s;
    	
    	
   // }
   // else return label;
    
    }
    public void setLabel() {
    	label=calculateLabel();
    }
    public void setLabel(String s) {
    	label=s;
    }
    public void setFinish(boolean b) {
    	finish=b;

    }
    public void setModif(boolean b) {
    	modif=true;
    }
    public boolean getFinish() {
    	return finish;
    }
    public int getLevel() {
    	return level;
    }
    public void setLevel(int n) {
    	level=n;
    }
    public boolean getDel() {
    	return del;
    }
    public void setDel(boolean b) {
    	del=b;
    }
  // abstract LetterTreeNode copy();
    public abstract String calculateLabel();
	 public  abstract ArrayList<LetterTreeNode> listNodes();
	public abstract LetterTreeNode getParent();
}

