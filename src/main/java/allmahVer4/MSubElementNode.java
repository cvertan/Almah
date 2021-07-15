package allmahVer4;

import javax.swing.tree.*;
import java.util.ArrayList;
import java.util.Map;
public class MSubElementNode extends DefaultMutableTreeNode{
  private boolean explored=false;

	public MSubElementNode(MLetterTreeNode o) {
		 // System.out.println("Node "+o.calculateLabel());
		 
     		setUserObject(o);
	}
public MSubElementNode(MSubElementNode x) {
	     MLetterTreeNode o=x.getMyLetterTreeNode();
	     setUserObject(o);
}
	public MLetterTreeNode getMyLetterTreeNode() {
		return (MLetterTreeNode) getUserObject();
	}
	
	public boolean isDirectory() {
		MLetterTreeNode t=getMyLetterTreeNode();
		if ((t.getLevel()==0)|| (t.getLevel()==1)) return true;
	//	if(!t.listNodes().isEmpty())return true;
		else return false;
	}
	public boolean isLeaf() {return !isDirectory();}
	public boolean getAllowsChildren() {return isDirectory();}
	public boolean isExplored() {return explored;}
	public String toString() {
		MLetterTreeNode t=(MLetterTreeNode)getUserObject();
		String nodeName= t.getNodeLabel();
		return nodeName;
	}
	public void setExplored(boolean b) {
		explored=b;
	}
	public void explore() {
		if( !isExplored()) {
			MLetterTreeNode t1=getMyLetterTreeNode();
			ArrayList<MLetterTreeNode> children=t1.listNodes();
			for(int i=0;i<children.size();++i) {
				// System.out.println("Child "+children.get(i).calculateLabel() );
				MSubElementNode a=new  MSubElementNode(children.get(i));
				a.explore();
				 add( a);
				
			}
			explored=true;
		}
	}
}


