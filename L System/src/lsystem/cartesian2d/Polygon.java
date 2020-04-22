package lsystem.cartesian2d;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class Polygon
{
	private LinkedList<Point> points;
	public int n;
	public Color color;
	
	public Polygon()
	{
		points = new LinkedList<Point>();
		n = -1;
	}
	
	public void addPoint(Point p)
	{
		points.add(p);
	}
	
	public double minX()
	{
		double minX = Double.POSITIVE_INFINITY;
		for(Point point : points)
			minX = Double.min(minX, point.x);
		return minX;
	}
	
	public double minY()
	{
		double minY = Double.POSITIVE_INFINITY;
		for(Point point : points)
			minY = Double.min(minY, point.y);
		return minY;
	}
	
	public double maxX()
	{
		double maxX = Double.NEGATIVE_INFINITY;
		for(Point point : points)
			maxX = Double.max(maxX, point.x);
		return maxX;
	}
	
	public double maxY()
	{
		double maxY = Double.NEGATIVE_INFINITY;
		for(Point point : points)
			maxY = Double.max(maxY, point.y);
		return maxY;
	}
	
	private java.awt.Polygon getConversion(int shiftX, int shiftY)
	{
		java.awt.Polygon conversion = new java.awt.Polygon();
		for(Point point : points)
			conversion.addPoint((int)Math.round(point.x - shiftX), (int)Math.round(shiftY - point.y));
		return conversion;
	}
	
	public void render(Graphics2D g2D, int shiftX, int shiftY)
	{
		Color temp = g2D.getColor();
		g2D.setColor(color);
		(g2D).fillPolygon(getConversion(shiftX, shiftY));
		g2D.setColor(temp);
	}
}
