package lsystem.gui.animations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import lsystem.LSystem;
import lsystem.actions.Action;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

public class AnimationPanel extends JPanel
{
	private static final long serialVersionUID = -923820469380884849L;
	
	private static final int RADIUS = 4;
	private static final int LENGTH = 75;
	
	private static final Color POINTA = Color.RED;
	private static final Color POINTB = Color.BLUE;
	private static final Color VECTOR = new Color(0xFF7F50);
	
	private Point pos;
	private Vector dir;
	
	private BorderLayout layout;
	private JLayeredPane layers;
	
	private MovementPanel movementPanel;
	private LinePanel linePanel;
	
	public AnimationPanel(LSystem system, Dimension size, int shiftX, int shiftY)
	{
		layout = new BorderLayout();
		this.setLayout(layout);
		
		layers = new JLayeredPane();
		layers.setPreferredSize(size);
		layers.setLayout(new BorderLayout());
		
		System.out.println(layers.getPreferredSize());
		
		Point origin = new Point(0, 0);
		Vector direction = new Vector(1, 0);
		
		Color foreground = Color.GREEN;
		Color background = Color.BLACK;
		
		movementPanel = new MovementPanel(RADIUS, LENGTH, shiftX, shiftY, POINTA, POINTB, VECTOR, foreground, origin, direction);
		movementPanel.setPreferredSize(size);
		
		linePanel = new LinePanel(foreground, background, shiftX, shiftY);
		linePanel.setPreferredSize(size);
//		linePanel.setSize(size);
//		
//		this.add(linePanel);
		this.add(layers, BorderLayout.CENTER);
		
		layers.add(linePanel);
		layers.add(movementPanel);
		
		System.out.println(layers.getPreferredSize());
		
//		layers.add(linePanel, new Integer(0));
//		layers.add(movementPanel, new Integer(1));
//		layers.add(movementPanel, 1);
	}
	
	private void perform(LinkedList<Action> actions)
	{
		
	}
	
	private void perform(Action action)
	{
		
	}
}
