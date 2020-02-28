package lsystem.gui.animations;

import java.awt.Color;

import javax.swing.JFrame;

import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

public class TestFrame extends JFrame
{
	private PointPanel pointPanel;
	
	public TestFrame()
	{
		this.setSize(500, 500);
		this.setVisible(true);
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
		
		pointPanel = new PointPanel(radius, length, shiftX, shiftY, A, B, C, D, origin, direction);
		this.getContentPane().add(pointPanel);
	}
	
	public static void main(String args[])
	{
		TestFrame testFrame = new TestFrame();
		testFrame.pointPanel.moveTo(new Point(80, 80));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testFrame.pointPanel.moveTo(new Point(40, 160));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testFrame.pointPanel.moveTo(new Point(180, 180));
		testFrame.pointPanel.rotateTo(new Vector(1, -1));
		testFrame.pointPanel.drawLine();
	}
}
