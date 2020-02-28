package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

public class TestFrame extends JFrame
{
	private MovementPanel movementPanel;
	
	public TestFrame()
	{
		this.setSize(500, 500);
		this.setPreferredSize(new Dimension(500, 500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int radius = 4;
		int length = 75;
		int shiftX = 0;
		int shiftY = getContentPane().getHeight();
		
		Color A = Color.RED;
		Color B = Color.BLUE;
		Color C = new Color(0xFF7F50);
		Color D = Color.GREEN;
		
		Point origin = new Point(0, 0);
		Vector direction = new Vector(1, 0);
		
//		movementPanel = new MovementPanel(radius, length, shiftX, shiftY, A, B, C, D, origin, direction);
		
		
//		this.add(new LinePanel(D, Color.BLACK, shiftX, shiftY));
		this.setVisible(true);
		this.add(new AnimationPanel(null, this.getContentPane().getSize(), shiftX, shiftY));
	}
	
	public static void main(String args[])
	{
		TestFrame testFrame = new TestFrame();
	}
}
