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
class MyLetterRenderer extends DefaultTreeCellRenderer {
   
	 private JLabel label;
	 private JButton callender;
	 private JButton onomastics;;
	 JPanel panel;

     MyLetterRenderer() {
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

        Object o = ((SubElementNode) value).getUserObject();
          
          MorphoTranscrNode letter = (MorphoTranscrNode) o;
          label.setText(letter.getNodeLabel());
          if (letter.getLevel()==3) setBackground(Color.YELLOW);
          else if (letter.getLevel()==4) setBackground(Color.LIGHT_GRAY);
          else  setBackground(Color.WHITE);
    /*      System.out.println("LABEL "+letter.calculateLabel());
          for (int i=0;i<letter.getChildren().size();i++) {
        	  System.out.println("i="+i+" child"+letter.getChildren().get(i).getLabel());
          }*/
    
        return this;
    }


 
}

