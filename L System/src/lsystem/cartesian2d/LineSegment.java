package lsystem.cartesian2d;

import java.awt.Graphics2D;

public class LineSegment extends Line
{
	public Point a;
	public Point b;
	
	public LineSegment(Point a, Point b)
	{
		super(new Vector(a, b), new Vector(a.x, a.y));
		this.a = a;
		this.b = b;
	}
	
	public Point getMidPoint()
	{
		return Point.mid(a, b);
	}
	
	private double computeT(Point p)
	{
		return -Vector.dot(Vector.sub(a, p), v) / Vector.dot(v, v);
	}
	
	public double distanceFrom(Point p)
	{
		return Point.distance(p, getPointClosestTo(p));
	}
	
	public Point getPointClosestTo(Point pos)
	{
		double t = computeT(pos);
		Point closest;
		if(0 <= t && t <= 1.0)
		{
			closest = super.getPointClosestTo(pos);
		}
		else
		{
			double d1 = Point.distance(a, pos);
			double d2 = Point.distance(b, pos);
			closest = (d1 < d2) ? a : b;
		}
		return closest;
	}
	
	public void render(Graphics2D g, int xShift, int yShift)
	{
		int x1 = (int) (a.x - xShift);
		int y1 = (int) (yShift - a.y);
		int x2 = (int) (b.x - xShift);
		int y2 = (int) (yShift - b.y);
		g.drawLine(x1, y1, x2, y2);
	}

}
