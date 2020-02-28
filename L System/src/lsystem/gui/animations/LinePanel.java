package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import lsystem.cartesian2d.LineSegment;

public class LinePanel extends JPanel
{
	private static final long serialVersionUID = 3691384352908929401L;
	
	private Color foreground;
	private Color background;
	
	private int shiftX;
	private int shiftY;
	
	private boolean backgroundPainted;
	private LineSegment toDraw;
	
	
	public LinePanel(Color foreground, Color background, int shiftX, int shiftY)
	{
		this.foreground = foreground;
		this.background = background;
		
		this.shiftX = shiftX;
		this.shiftY = shiftY;
		
		backgroundPainted = false;
		toDraw = null;
	}
	
	public void draw(LineSegment line)
	{
		toDraw = line;
		repaint();
	}
	
	public void paint(Graphics g)
	{
		if(backgroundPainted == false)
		{
			g.setColor(background);
			g.fillRect(0, 0, getWidth(), getHeight());
			backgroundPainted = true;
		}
		
		if(toDraw != null)
		{
			g.setColor(foreground);
			toDraw.render((Graphics2D) g, shiftX, shiftY);
			toDraw = null;
		}
	}
}
