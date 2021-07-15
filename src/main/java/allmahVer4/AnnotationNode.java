package allmahVer4;
import javax.swing.tree.*;
import java.util.ArrayList;
import java.util.Map;
public class AnnotationNode extends DefaultMutableTreeNode{
  private boolean explored=false;
  public AnnotationNode() {
	  
  }
	public AnnotationNode(TreeNode o) {
		 // System.out.println("Node "+o.calculateLabel());
		 
     		setUserObject(o);
	}
	public TreeNode getMyTreeNode() {
		return (TreeNode) getUserObject();
	}
	
	public boolean isDirectory() {
		TreeNode t=getMyTreeNode();
		if ((t.getLevel()<9) || t.getFinish()) return true;
		else return false;
	}
	public boolean isLeaf() {return !isDirectory();}
	public boolean getAllowsChildren() {return isDirectory();}
	public boolean isExplored() {return explored;}
	public String toString() {
		TreeNode t=(TreeNode)getUserObject();
		String nodeName= t.getNodeLabel();
		return nodeName;
	}
	public void setExplored(boolean b) {
		explored=b;
	}
	public void explore() {
		if( !isExplored()) {
			TreeNode t1=getMyTreeNode();
			ArrayList<TreeNode> children=t1.listNodes();
			for(int i=0;i<children.size();++i) {
				// System.out.println("Child "+children.get(i).calculateLabel() );
				AnnotationNode a=new  AnnotationNode(children.get(i));
				a.explore();
				 add( a);
				
			}
			explored=true;
		}
	}
}
