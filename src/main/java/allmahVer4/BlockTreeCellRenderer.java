package allmahVer4;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
class BlockTreeCellRenderer implements TreeCellRenderer {
    private JLabel label;

    BlockTreeCellRenderer() {
        label = new JLabel();
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        Object o = ((DefaultMutableTreeNode) value).getUserObject();
        if (o instanceof allmahVer4.HieroglyphenBlock) {
           HieroglyphenBlock block = (HieroglyphenBlock) o;
          //  URL imageUrl = getClass().getResource(country.getFlagIcon());
           String imageUrl=block.getPathImage();
            if (imageUrl != null) {
                label.setIcon(new ImageIcon(imageUrl));
            }
           // label.setText(country.getName());
        } else {
            label.setIcon(null);
            label.setText("" + value);
        }
        return label;
    }
}
