package allmahVer4;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.tree.DefaultMutableTreeNode;
class MyRenderer extends DefaultTreeCellRenderer {
   
	 private JLabel label;
	 private JButton callender;
	 private JButton onomastics;;
	 JPanel panel;

     MyRenderer() {
         label = new JLabel();
        
     }

    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) {

        super.getTreeCellRendererComponent(
                        tree, value, sel,
                        expanded, leaf, row,
                        hasFocus);

       setFont(new Font("Sans Serif",Font.PLAIN,16));
        if ( isDrawable(value)) {
           
        	setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } 
        else {
        	setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }
        Object o = ((DefaultMutableTreeNode) value).getUserObject();
        if (o instanceof allmahVer4.HieroglyphenBlock) {
           HieroglyphenBlock block = (HieroglyphenBlock) o;
          //  URL imageUrl = getClass().getResource(country.getFlagIcon());
           setToolTipText("");
          /* String imageUrl=block.getPathImage();
            if (imageUrl != null) {
                this.setIcon(new ImageIcon(imageUrl));
            }*/
                      //System.out.println("BLOCK "+block.getLabel()+ " "+block.getNumLabel());
                      this.setIcon(new ImageIcon(block.getImage()));
       if (!block.getAnnotations().isEmpty()) this.setBackground(Color.YELLOW);
          
        } 
        else if (o instanceof allmahVer4.GraphTranslit1) {
        	  GraphTranslit1 gt1 = (GraphTranslit1) o;
        	  setToolTipText(gt1.getConfidence());
        	  setBackgroundSelectionColor(Color.ORANGE);
        	  
               //  URL imageUrl = getClass().getResource(country.getFlagIcon());
                String imageUrl=gt1.getPathImage();
                if (imageUrl != null) {
                    this.setIcon(new ImageIcon(imageUrl));
                     label.setText(gt1.getNodeLabel());
                 }
        }
        else if (o instanceof allmahVer4.GraphTranslit2) {
      	  GraphTranslit2 gt2 = (GraphTranslit2) o;
     // 	setToolTipText("GT2" + row);
          setToolTipText("");
             //  URL imageUrl = getClass().getResource(country.getFlagIcon());
              String imageUrl=gt2.getPathImage();
               if (imageUrl != null) {
                   this.setIcon(new ImageIcon(imageUrl));
                   label.setText(gt2.getNodeLabel());
               }
      }
        else if (o instanceof allmahVer4.PhonemTranslit) {
      	  PhonemTranslit phon = (PhonemTranslit) o;
             //  URL imageUrl = getClass().getResource(country.getFlagIcon());
      	    //setToolTipText("Phonem" + row);
          setToolTipText("");
              String imageUrl=phon.getPathImage();
               if (imageUrl != null) {
                   this.setIcon(new ImageIcon(imageUrl));
                   label.setText(phon.getNodeLabel());
               }
      }
        else {
            setToolTipText("");
            setBackground(Color.WHITE);
        }
        return this;
    }

    protected boolean isDrawable(Object value) {
        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode)value;
        TreeNode nodeInfo =
                (TreeNode)(node.getUserObject());
       String label=""+nodeInfo.getClass();
        if (label.equals("class allmahVer4.HieroglyphenBlock")) {
            return true;
        }
        else
        return false;
    }
 
}
