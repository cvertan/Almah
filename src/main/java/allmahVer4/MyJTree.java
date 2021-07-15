package allmahVer4;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseEvent;
import java.awt.Point;
import org.jsoup.*;
import javax.swing.tree.TreePath;
public class MyJTree extends JTree {

	public MyJTree(DefaultTreeModel m) {
		super(m);
	}
	
	public String getToolTipText(MouseEvent evt) {
		  // Let the renderer supply the tooltip
		  String tip = super.getToolTipText(evt); 

		  // If it did not, return the tree's tip
		  return tip != null ? tip : getToolTipText();
		}
	
	public Point getToolTipLocation(MouseEvent e) {
        TreePath path = getPathForLocation(e.getX(), e.getY());
    
       
        if(path == null) {
            return null;
        }
        else {
        	 String pa=Jsoup.parse(""+path).text();
             int count = ( pa.split("Graphical", -1).length ) - 1;
             System.out.println("PATH "+pa+ "COUNT "+count );
             if(count!=1) return null;
             else  return new Point(e.getX() + 15, e.getY());
        }
   
	
}

}
