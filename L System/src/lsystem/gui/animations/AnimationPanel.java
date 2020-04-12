package lsystem.gui.animations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import lsystem.cartesian2d.LineSegment;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

public class AnimationPanel extends JPanel
{
	private static final long serialVersionUID = -923820469380884849L;
	
	private static final int RADIUS = 3;
	private static final int LENGTH = 75;
	
	private static final Color POINTA = Color.RED;
	private static final Color POINTB = Color.BLUE;
	private static final Color VECTOR = new Color(0xFF7F50);
	
	private BorderLayout layout;
	private JLayeredPane layers;
	
	private MovementPanel movementPanel;
	
	public AnimationPanel(Point origin, Vector direction, Color foreground, Color background, int width, int height, int shiftX, int shiftY, Color finalColor, int numLines, float thickness, LinkedList<LineSegment> lines, boolean depthbasedcolors)
	{
		layout = new BorderLayout();
		this.setLayout(layout);
		
		Dimension size = new Dimension(width, height);
		
		layers = new JLayeredPane();
		layers.setPreferredSize(size);
		layers.setLayout(new BorderLayout());
		
		movementPanel = new MovementPanel(RADIUS, LENGTH, shiftX, shiftY, POINTA, POINTB, VECTOR, foreground, background, origin, direction, finalColor, numLines, thickness, lines, depthbasedcolors);
		movementPanel.setPreferredSize(size);
		
		layers.add(movementPanel, BorderLayout.CENTER, 0);
		
		this.add(layers, BorderLayout.CENTER);
	}
	
	public void moveTo(Point pos, int millis)
	{
		movementPanel.moveTo(pos, millis);
	}
	
	public void rotateTo(double radians, Vector transformation, int millis)
	{
		movementPanel.rotateTo(radians, transformation, millis);
	}
	
	public void rotateTo(Vector transformation, int millis)
	{
		movementPanel.rotateTo(transformation, millis);
	}
	
	public void drawLine(int millis)
	{
		movementPanel.drawLine(millis);
		movementPanel.draw(movementPanel.getLine());
	}
}
