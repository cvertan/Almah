package allmahVer4;

import javax.swing.tree.*;
import java.util.ArrayList;
import java.util.Map;
public class SubElementNode extends DefaultMutableTreeNode{
  private boolean explored=false;

	public SubElementNode(LetterTreeNode o) {
		 // System.out.println("Node "+o.calculateLabel());
		 
     		setUserObject(o);
	}
public SubElementNode(SubElementNode x) {
	     LetterTreeNode o=x.getMyLetterTreeNode();
	     setUserObject(o);
}
	public LetterTreeNode getMyLetterTreeNode() {
		return (LetterTreeNode) getUserObject();
	}
	
	public boolean isDirectory() {
		LetterTreeNode t=getMyLetterTreeNode();
		if ((t.getLevel()==0)|| (t.getLevel()==1)) return true;
	//	if(!t.listNodes().isEmpty())return true;
		else return false;
	}
	public boolean isLeaf() {return !isDirectory();}
	public boolean getAllowsChildren() {return isDirectory();}
	public boolean isExplored() {return explored;}
	public String toString() {
		LetterTreeNode t=(LetterTreeNode)getUserObject();
		String nodeName= t.getNodeLabel();
		return nodeName;
	}
	public void setExplored(boolean b) {
		explored=b;
	}
	public void explore() {
		if( !isExplored()) {
			LetterTreeNode t1=getMyLetterTreeNode();
			ArrayList<LetterTreeNode> children=t1.listNodes();
			for(int i=0;i<children.size();++i) {
				// System.out.println("Child "+children.get(i).calculateLabel() );
				SubElementNode a=new  SubElementNode(children.get(i));
				a.explore();
				 add( a);
				
			}
			explored=true;
		}
	}
}

