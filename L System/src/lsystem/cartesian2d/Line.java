package lsystem.cartesian2d;

public class Line
{
	public Vector v;
	public Vector r0;
	
	public Line(Vector v, Vector r0)
	{
		this.v = v;
		this.r0 = r0;
	}
	
	public Point r(double t)
	{
		return Point.add(r0, Vector.mult(t, v));
	}
	
	public Vector getNormal()
	{
		return Vector.getNormalOf(v);
	}
	
	public double distanceFrom(Point p)
	{
		return Point.distance(p, getPointClosestTo(p));
	}
	
	public Point getPointClosestTo(Point p)
	{
		double ri = v.i * (r0.i - p.x);
		double rj = v.j * (r0.j - p.y);
		double nvi = v.i * v.i;
		double nvj = v.j * v.j;
		double t = 0 - (ri + rj) / (nvi + nvj);	
		return r(t);
	}
	
	public static float thickness = 1.0f;
	
	public String toString()
	{
		return "r(t) = " + v + " * t + " + r0;
	}
}
