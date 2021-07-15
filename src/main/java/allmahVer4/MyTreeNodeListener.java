package allmahVer4;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
class MyTreeModelListener implements TreeModelListener {

    @Override
    public void treeNodesChanged(TreeModelEvent e) {
      SubElementNode node = (SubElementNode) (e.getTreePath()
          .getLastPathComponent()); 
      int index = e.getChildIndices()[0];
      node = (SubElementNode) (node.getChildAt(index));
      System.out.println("New value NodesChanged: " + node.getUserObject());
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {
      SubElementNode node = (SubElementNode) (e.getTreePath()
          .getLastPathComponent()); 
      int index = e.getChildIndices()[0];
      node = (SubElementNode) (node.getChildAt(index));
      System.out.println("New value NodesInserted : " + node.getUserObject());
    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
      SubElementNode node = (SubElementNode) (e.getTreePath()
          .getLastPathComponent()); 
      int index = e.getChildIndices()[0];
      node = (SubElementNode) (node.getChildAt(index));
      System.out.println("New value NodesRemoved : " + node.getUserObject());
    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {
      SubElementNode node = (SubElementNode) (e.getTreePath()
          .getLastPathComponent());
      int index = e.getChildIndices()[0];
      node = (SubElementNode) (node.getChildAt(index));
      System.out.println("New value StructureChanged : " + node.getUserObject());
    }
  }
