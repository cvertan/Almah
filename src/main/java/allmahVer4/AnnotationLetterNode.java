package allmahVer4;
import javax.swing.tree.*;
import java.util.ArrayList;
import java.util.Map;
public class AnnotationLetterNode extends DefaultMutableTreeNode{
  private boolean explored=false;
  public AnnotationLetterNode() {
	  
  }
	public AnnotationLetterNode(MorphoTranscrNode o) {
		 // System.out.println("Node "+o.calculateLabel());
		 
     		setUserObject(o);
	}
	public MorphoTranscrNode getMyLetterTreeNode() {
		return (MorphoTranscrNode) getUserObject();
	}
	
	public boolean isDirectory() {
		LetterTreeNode t=getMyLetterTreeNode();
		if ((t.getLevel()<2)&&(!t.calculateLabel().equals("??"))) return true;
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
			MorphoTranscrNode t1=getMyLetterTreeNode();
			ArrayList<LetterTreeNode> children=t1.listNodes();
			for(int i=0;i<children.size();++i) {
				// System.out.println("Child "+children.get(i).calculateLabel() );
				AnnotationLetterNode a=new  AnnotationLetterNode((MorphoTranscrNode)children.get(i));
				a.explore();
				 add( a);
				
			}
			explored=true;
		}
	}
}

