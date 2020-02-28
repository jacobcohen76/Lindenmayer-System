package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

import lsystem.LSystem;
import lsystem.actions.Action;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

public class AnimationPanel extends JPanel
{
	private static final long serialVersionUID = -923820469380884849L;
	
	private Point pos;
	private Vector dir;
	
	public AnimationPanel(LSystem system)
	{
		
	}
	
	private void perform(LinkedList<Action> actions)
	{
		
	}
	
	private void perform(Action action)
	{
		
	}
	
	private void renderPoint(Graphics g, Point pos, int radius, int shiftX, int shiftY, Color c)
	{
		int x = (int) Math.round(pos.x - shiftX - radius);
		int y = (int) Math.round(shiftY - radius - pos.y);
		g.fillOval(x, y, 2 * radius, 2 * radius);
	}
	
	private void moveTo(Point destination)
	{
		
	}
	
	public static void main(String args[])
	{
		
	}
}
