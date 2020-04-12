package lsystem.gui.animations;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import lsystem.cartesian2d.LineSegment;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

public class MovementPanel extends JPanel
{
	private static final long serialVersionUID = -408764710346055893L;
	private static double FPMS = 60.0 / 1000.0;
	
	private Point current;
	private Point prev;
	private Vector direction;
	
	private Color A;
	private Color B;
	private Color C;
	private Color D;
	private Color background;
	private int radius;
	private int length;
	private int shiftX;
	private int shiftY;
	
	private int numLines;
	private Color finalColor;
	
	private ArrayList<LineSegment> lines;
	private ArrayList<Color> colors;
	private ArrayList<Float> thicknesses;
	
	public float thickness = 1.0f;
	
	public MovementPanel(int radius, int length, int shiftX, int shiftY, Color A, Color B, Color C, Color D, Color background, Point origin, Vector direction, Color finalColor, int numLines, float thickness, LinkedList<LineSegment> lines, boolean depthbasedcolors)
	{
		this.depthbasedcolors = depthbasedcolors;
		this.setOpaque(false);
		this.thickness = thickness;
		this.radius = radius;
		this.length = length;
		this.shiftX = shiftX;
		this.shiftY = shiftY;
		this.A = A;
		this.B = B;
		this.C = C;
		this.D = D;
		this.background = background;
		this.direction = direction.normalize();
		current = origin;
		prev = origin.clone();
		
		this.numLines = numLines;
		this.finalColor = finalColor == null ? D : finalColor;
		this.lines = new ArrayList<LineSegment>();
		colors = new ArrayList<Color>();
		thicknesses = new ArrayList<Float>();
		
		for(LineSegment line : lines)
		{
			colors.add(line.color);
			thicknesses.add(line.thickness);
		}
	}
	
	private Color getColor(Color origin, Color destination)
	{
		double percent = (double) lines.size() / (double) numLines; 
		int dr = destination.getRed() - origin.getRed();
		int dg = destination.getGreen() - origin.getGreen();
		int db = destination.getBlue() - origin.getBlue();
		int da = destination.getAlpha() - origin.getAlpha();
		dr *= percent;
		dg *= percent;
		db *= percent;
		da *= percent;
		return new Color(origin.getRed() + dr, origin.getGreen() + dg, origin.getBlue() + db, origin.getAlpha() + da);
	}
	
	private boolean renderingLine = false;
	private Point increment;
	
	public void paint(Graphics g)
	{
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for(LineSegment line : lines)
			render(line, g);
		
		if(renderingLine)
			render(g, prev, increment, depthbasedcolors == true ? getColor() : getColor(D, finalColor), shiftX, shiftY, thickness);
		render(g, prev, B, shiftX, shiftY, radius);
		render(g, current, A, shiftX, shiftY, radius);
		render(g, current, direction, C, shiftX, shiftY, length);
	}
	
	public void moveTo(Point pos, int millis)
	{
		int numFrames = (int) Math.round(FPMS * millis);
		if(numFrames <= 0)
			numFrames = 1;
		int interval = (int) Math.round((double) millis / (double) numFrames);
		move(prev, current, pos, numFrames, interval);
	}
	
	public void drawLine(int millis)
	{
		int numFrames = (int) Math.round(FPMS * millis);
		if(numFrames <= 0)
			numFrames = 1;
		int interval = (int) Math.round((double) millis / (double) numFrames);
		animateLine(numFrames, interval);
	}
	
	private void animateLine(int frames, int interval)
	{
		Point currentFrame = new Point((current.x - prev.x) / frames, (current.y - prev.y) / frames);
		increment = prev.clone();
		
		renderingLine = true;
		
		int frame = 0;
		while(true)
		{
			increment.x += currentFrame.x;
			increment.y += currentFrame.y;
			repaint();
			
			if(frame < frames)
				frame++;
			else
			{
				renderingLine = false;
				repaint();
				break;
			}
			try { Thread.sleep(interval); } catch(Exception ex) { }
		}
	}
	
	public void rotateTo(double radians, Vector transformation, int millis)
	{
		int numFrames = (int) Math.round(FPMS * millis);
		if(numFrames <= 0)
			numFrames = 1;
		int interval = (int) Math.round((double) millis / (double) numFrames);
		transformation = transformation.normalize();
		rotate(radians, direction, transformation, numFrames, interval);
	}
	
	public void rotateTo(Vector transformation, int millis)
	{
		int numFrames = (int) Math.round(FPMS * millis);
		if(numFrames <= 0)
			numFrames = 1;
		int interval = (int) Math.round((double) millis / (double) numFrames);
		transformation = transformation.normalize();
		double radians = Vector.getAngleBetween(direction, transformation);
		rotate(radians, direction, transformation, numFrames, interval);
	}
	
	private void rotate(double radians, Vector current, Vector transformation, int frames, int interval)
	{
		double currentFrame = radians / frames;
		
		int frame = 0;
		while(true)
		{
			Vector temp = current.rotateCCW(currentFrame);
			current.i = temp.i;
			current.j = temp.j;
			repaint();
			
			if(frame < frames)
				frame++;
			else
			{
				current.i = transformation.i;
				current.j = transformation.j;
				break;
			}
			try { Thread.sleep(interval); } catch(Exception ex) { }
		}
	}
	
	int j = 0;
	private Color getColor()
	{
		return colors.get(j);
	}
	
	private float getThickness()
	{
		return thicknesses.get(j);
	}
	
	public boolean depthbasedcolors = false;
	public LineSegment getLine()
	{
		Color c = depthbasedcolors == true ? getColor() : getColor(D, finalColor);
		float thickness = getThickness();
		j++;
		return new LineSegment(prev.clone(), current.clone(), c, thickness);
	}
	
	private void move(Point prev, Point current, Point destination, int frames, int interval)
	{		
		Point currentFrame = new Point((destination.x - current.x) / frames, (destination.y - current.y) / frames);
		Point prevFrame = new Point((current.x - prev.x) / frames, (current.y - prev.y) / frames);
		Point temp = current.clone();
		
		int frame = 0;
		while(true)
		{
			current.x += currentFrame.x;
			current.y += currentFrame.y;
			
			prev.x += prevFrame.x;
			prev.y += prevFrame.y;
			
			repaint();
			
			if(frame < frames)
				frame++;
			else
			{
				current.x = destination.x;
				current.y = destination.y;
				
				prev.x = temp.x;
				prev.y = temp.y;
				break;
			}
			try { Thread.sleep(interval); } catch(Exception ex) { }
		}
	}
	
	private void render(Graphics g, Point pos, Color c, int shiftX, int shiftY, int radius)
	{
		int x = (int) Math.round(pos.x - shiftX - radius);
		int y = (int) Math.round(shiftY - radius - pos.y);
		g.setColor(c);
		g.fillOval(x, y, 2 * radius, 2 * radius);
	}
	
	private void render(Graphics g, Point origin, Vector v, Color c, int shiftX, int shiftY, int length)
	{
		v = Vector.mult(length, v);
		
		int x1 = (int) Math.round(origin.x - shiftX);
		int y1 = (int) Math.round(shiftY - origin.y);
		int x2 = (int) Math.round(origin.x + v.i - shiftX);
		int y2 = (int) Math.round(shiftY - (origin.y + v.j));
		
		Graphics2D g2D = (Graphics2D) g;
		BasicStroke stroke = new BasicStroke(1.0f);
		g2D.setStroke(stroke);
		g2D.setColor(c);
		g2D.drawLine(x1, y1, x2, y2);
	}
	
	private void render(Graphics g, Point origin, Point destination, Color c, int shiftX, int shiftY, float thickness)
	{
		int x1 = (int) Math.round(origin.x - shiftX);
		int y1 = (int) Math.round(shiftY - origin.y);
		int x2 = (int) Math.round(destination.x - shiftX);
		int y2 = (int) Math.round(shiftY - destination.y);
		
		Graphics2D g2D = (Graphics2D) g;
		BasicStroke stroke = new BasicStroke(thickness);
		g2D.setStroke(stroke);
		g2D.setColor(c);
		g2D.drawLine(x1, y1, x2, y2);
	}
	
	public void draw(LineSegment line)
	{
		lines.add(line);
		repaint();
	}
	
	private void render(LineSegment line, Graphics g)
	{
		line.render((Graphics2D) g, shiftX, shiftY);
	}
}
