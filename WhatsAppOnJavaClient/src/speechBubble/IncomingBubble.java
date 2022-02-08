package speechBubble;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

import theme.Theme;

public class IncomingBubble extends JPanel {
   private static final long serialVersionUID = -5389178141802153305L;
   private int radius = 10;
   private int arrowSize = 12;
   private int strokeThickness = 3;
   private int padding = strokeThickness / 2;
   
   IncomingBubble()
   {
	   //setBackground(new Color(245, 245, 220));
   }
   @Override   
   protected void paintComponent(final Graphics g) {
      final Graphics2D g2d = (Graphics2D) g;
     // setBackground(Color.LIGHT_GRAY);
    //  g2d.setColor(Color.WHITE);
      g2d.setColor(Theme.getIncomingBubble());
      int x = padding + strokeThickness + arrowSize;
      int width = getWidth() - arrowSize - (strokeThickness * 2);
      int bottomLineY = getHeight() - strokeThickness;
      g2d.fillRect(x, padding, width, bottomLineY);
      g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,   RenderingHints.VALUE_ANTIALIAS_ON));
      g2d.setStroke(new BasicStroke(strokeThickness));
      RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, padding, width, bottomLineY, radius, radius);
      Polygon arrow = new Polygon();
      arrow.addPoint(20, 8);
      arrow.addPoint(0, 10);
      arrow.addPoint(20, 12);
      Area area = new Area(rect);
      area.add(new Area(arrow));
      g2d.draw(area);
   }
}