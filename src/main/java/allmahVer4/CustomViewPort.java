package allmahVer4;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JViewport;
public class CustomViewPort  extends JViewport{

	public CustomViewPort() {
		// TODO Auto-generated constructor stub
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(200, 200, 200, 200);
		}
}
