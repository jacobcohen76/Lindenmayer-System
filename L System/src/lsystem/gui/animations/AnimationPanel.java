package lsystem.gui.animations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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
	private LinePanel linePanel;
	
	public AnimationPanel(Point origin, Vector direction, Color foreground, Color background, int width, int height, int shiftX, int shiftY)
	{
		layout = new BorderLayout();
		this.setLayout(layout);
		
		Dimension size = new Dimension(width, height);
		
		layers = new JLayeredPane();
		layers.setPreferredSize(size);
		layers.setLayout(new BorderLayout());
		
		movementPanel = new MovementPanel(RADIUS, LENGTH, shiftX, shiftY, POINTA, POINTB, VECTOR, foreground, origin, direction);
		movementPanel.setPreferredSize(size);
		
		linePanel = new LinePanel(foreground, background, shiftX, shiftY);
		linePanel.setPreferredSize(size);
		linePanel.setSize(size);
		
		layers.add(linePanel, BorderLayout.CENTER, 0);
		layers.add(movementPanel, BorderLayout.CENTER, 1);
		
		this.add(layers, BorderLayout.CENTER);
	}
	
	public void moveTo(Point pos)
	{
		movementPanel.moveTo(pos);
	}
	
	public void rotateTo(double radians, Vector transformation)
	{
		movementPanel.rotateTo(radians, transformation);
	}
	
	public void rotateTo(Vector transformation)
	{
		movementPanel.rotateTo(transformation);
	}
	
	public void drawLine()
	{
		movementPanel.drawLine();
		linePanel.draw(movementPanel.getLine());
	}
}
