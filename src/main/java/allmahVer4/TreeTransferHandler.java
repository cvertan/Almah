package allmahVer4;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.*;
class TreeTransferHandler extends TransferHandler {
    DataFlavor nodesFlavor;
    DataFlavor[] flavors = new DataFlavor[1];
    SubElementNode[] nodesToRemove;

    public TreeTransferHandler() {
        try {
            String mimeType = DataFlavor.javaJVMLocalObjectMimeType +
                              ";class=\"" +
                javax.swing.tree.DefaultMutableTreeNode[].class.getName() +
                              "\"";
            nodesFlavor = new DataFlavor(mimeType);
            flavors[0] = nodesFlavor;
        } catch(ClassNotFoundException e) {
            System.out.println("ClassNotFound: " + e.getMessage());
        }
    }
    
    public boolean canImport(TransferHandler.TransferSupport support) {
        if(!support.isDrop()) {
            return false;
        }
        support.setShowDropLocation(true);
        if(!support.isDataFlavorSupported(nodesFlavor)) {
            return false;
        }
        // Do not allow a drop on the drag source selections.
        JTree.DropLocation dl =
                (JTree.DropLocation)support.getDropLocation();
        JTree tree = (JTree)support.getComponent();
        int dropRow = tree.getRowForPath(dl.getPath());
        int[] selRows = tree.getSelectionRows();
        for(int i = 0; i < selRows.length; i++) {
            if(selRows[i] == dropRow) {
                return false;
            }
        }
        // Do not allow MOVE-action drops if a non-leaf node is
        // selected unless all of its children are also selected.
        int action = support.getDropAction();
        if(action == MOVE) {
            return haveCompleteNode(tree);
        }
        // Do not allow a non-leaf node to be copied to a level
        // which is less than its source level.
        TreePath dest = dl.getPath();
        SubElementNode target =
            (SubElementNode)dest.getLastPathComponent();
        TreePath path = tree.getPathForRow(selRows[0]);
        SubElementNode firstNode =
            (SubElementNode)path.getLastPathComponent();
        if(firstNode.getChildCount() > 0 &&
               target.getLevel() < firstNode.getLevel()) {
            return false;
        }
        return true;
    }

    private boolean haveCompleteNode(JTree tree) {
        int[] selRows = tree.getSelectionRows();
        TreePath path = tree.getPathForRow(selRows[0]);
        SubElementNode first =
            (SubElementNode)path.getLastPathComponent();
        int childCount = first.getChildCount();
        // first has children and no children are selected.
        if(childCount > 0 && selRows.length == 1)
            return false;
        // first may have children.
        for(int i = 1; i < selRows.length; i++) {
            path = tree.getPathForRow(selRows[i]);
            SubElementNode next =
                (SubElementNode)path.getLastPathComponent();
            if(first.isNodeChild(next)) {
                // Found a child of first.
                if(childCount > selRows.length-1) {
                    // Not all children of first are selected.
                    return false;
                }
            }
        }
        
        return true;
    }

    protected Transferable createTransferable(JComponent c) {
        JTree tree = (JTree)c;
        TreePath[] paths = tree.getSelectionPaths();
        if(paths != null) {
            // Make up a node array of copies for transfer and
            // another for/of the nodes that will be removed in
            // exportDone after a successful drop.
            List<SubElementNode> copies =
                new ArrayList<SubElementNode>();
            List<SubElementNode> toRemove =
                new ArrayList<SubElementNode>();
            SubElementNode node =
                (SubElementNode)paths[0].getLastPathComponent();
            SubElementNode copy = copy(node);
            copies.add(copy);
            toRemove.add(node);
            for(int i = 1; i < paths.length; i++) {
                SubElementNode next =
                    (SubElementNode)paths[i].getLastPathComponent();
                // Do not allow higher level nodes to be added to list.
                if(next.getLevel() < node.getLevel()) {
                    break;
                } else if(next.getLevel() > node.getLevel()) {  // child node
                    copy.add(copy(next));
                    // node already contains child
                } else {                                        // sibling
                    copies.add(copy(next));
                    toRemove.add(next);
                }
            }
            SubElementNode[] nodes =
                copies.toArray(new SubElementNode[copies.size()]);
            nodesToRemove =
                toRemove.toArray(new SubElementNode[toRemove.size()]);
            return new NodesTransferable(nodes);
        }
        return null;
    }

    /** Defensive copy used in createTransferable. */
    private SubElementNode copy(SubElementNode node) {
       // return new DefaultMutableTreeNode(node);
        return new SubElementNode(node);
    }

    protected void exportDone(JComponent source, Transferable data, int action) {
    	try {
        if((action & MOVE) == MOVE) {
            JTree tree = (JTree)source;
            DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
            // Remove nodes saved in nodesToRemove in createTransferable.
            for(int i = 0; i < nodesToRemove.length; i++) {
                model.removeNodeFromParent(nodesToRemove[i]);
            }
           
        }
    	}
        catch(Exception e) {JOptionPane.showMessageDialog(null, "Illegal Move");}
    }

    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    public boolean importData(TransferHandler.TransferSupport support) {
        if(!canImport(support)) {
            return false;
        }
        // Extract transfer data.
        SubElementNode[] nodes = null;
        try {
            Transferable t = support.getTransferable();
            nodes = (SubElementNode[])t.getTransferData(nodesFlavor);
        } catch(UnsupportedFlavorException ufe) {
            System.out.println("UnsupportedFlavor: " + ufe.getMessage());
        } catch(java.io.IOException ioe) {
            System.out.println("I/O error: " + ioe.getMessage());
        }
        // Get drop location info.
        JTree.DropLocation dl =
                (JTree.DropLocation)support.getDropLocation();
        int childIndex = dl.getChildIndex();
        TreePath dest = dl.getPath();
        SubElementNode parent =
            (SubElementNode)dest.getLastPathComponent();
        JTree tree = (JTree)support.getComponent();
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        // Configure for drop mode.
        int index = childIndex;    // DropMode.INSERT
        if(childIndex == -1) {     // DropMode.ON
            index = parent.getChildCount();
        }
        // Add data to model.
        for(int i = 0; i < nodes.length; i++) {
            model.insertNodeInto(nodes[i], parent, index++);
        }
        
      
        return true;
    }

    public String toString() {
        return getClass().getName();
    }

    public class NodesTransferable implements Transferable {
        //DefaultMutableTreeNode[] nodes;
    	SubElementNode[] nodes;

      //  public NodesTransferable(DefaultMutableTreeNode[] nodes) {
    	public NodesTransferable(SubElementNode[] nodes) {
            this.nodes = nodes;
         }

        public Object getTransferData(DataFlavor flavor)
                                 throws UnsupportedFlavorException {
            if(!isDataFlavorSupported(flavor))
                throw new UnsupportedFlavorException(flavor);
            return nodes;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return flavors;
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return nodesFlavor.equals(flavor);
        }
    }
}
