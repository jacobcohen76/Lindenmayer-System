package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
	private ArrayList<LineSegment> toDraw;
	
	
	public LinePanel(Color foreground, Color background, int shiftX, int shiftY)
	{
		this.foreground = foreground;
		this.background = background;
		
		this.shiftX = shiftX;
		this.shiftY = shiftY;
		
		backgroundPainted = false;
		toDraw = new ArrayList<LineSegment>();
	}
	
	public void draw(LineSegment line)
	{
		toDraw.add(line);
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
		
		for(LineSegment line : toDraw)
			render(line, g);
	}
	
	private void render(LineSegment line, Graphics g)
	{
		g.setColor(foreground);
		int x1 = (int) Math.round(line.a.x - shiftX);
		int y1 = (int) Math.round(shiftY - line.a.y);
		int x2 = (int) Math.round(line.b.x - shiftX);
		int y2 = (int) Math.round(shiftY - line.b.y);
		g.drawLine(x1, y1, x2, y2);
	}
}
