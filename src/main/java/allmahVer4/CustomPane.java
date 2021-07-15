package allmahVer4;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
class CustomPane extends JPanel {
int x1;
int y1;
int x2;
int y2;
public CustomPane(LayoutManager lm) {
super(lm);
x1=0;
y1=0;
x2=0;
y2=0;
}

@Override
public void paintComponent(Graphics g) {
g.setColor(Color.red);
g.drawRect(200, 200, 200, 200);
g.drawLine(x1,y1,x2,x2);
}

}
