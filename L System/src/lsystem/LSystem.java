package lsystem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Stack;

import lsystem.actions.Action;
import lsystem.cartesian2d.LineSegment;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;
import lsystem.gui.animations.AnimationFrame;
import lsystem.gui.animations.AnimationPanel;

public class LSystem
{
	public Grammar grammar;
	private LinkedList<Symbol> axiom;
	private int n;
	
	private Stack<Point> positionStack;
	private Stack<Vector> directionStack;
	
	private Point origin;
	private Vector initial;
	
	private Point prev;
	public Point current;
	public Vector direction;
	
	private LinkedList<LineSegment> lines;
	
	public LSystem(Grammar grammar, LinkedList<Symbol> axiom, int n, Point origin, Vector initial)
	{
		this.grammar = grammar;
		this.axiom = axiom;
		this.n = n;
		
		positionStack = new Stack<Point>();
		directionStack = new Stack<Vector>();
		
		prev = null;
		
		this.origin = origin;
		this.initial = initial.normalize();
		
		current = this.origin.clone();
		direction = this.initial.clone();
		
		lines = new LinkedList<LineSegment>();
	}
	
	public void generate()
	{
		perform(getReplacement());
	}
	
	public LinkedList<Symbol> getReplacement()
	{
		LinkedList<Symbol> production = axiom;
		for(int i = 0; i < n; i++)
			production = grammar.getReplacement(production);
		return production;
	}
	
	public void animate(Color foreground, Color background)
	{
		LinkedList<Symbol> replacement = getReplacement();
		perform(replacement);
		
		int width = getWidth();
		int height = getHeight();
		int xShift = getXShift();
		int yShift = getYShift();
		
		AnimationPanel animationPanel = new AnimationPanel(origin, initial, foreground, background, width, height, xShift, yShift);
		AnimationFrame animationFrame = new AnimationFrame(animationPanel, this, replacement, Color.BLACK, Color.WHITE, Color.YELLOW);
		
		animationFrame.play();
	}
	
	private void perform(LinkedList<Symbol> axiom)
	{
		for(Symbol symbol : axiom)
			perform(symbol);
	}
	
	public LinkedList<Action> getActions(Symbol symbol)
	{
		return grammar.getActions(symbol);
	}
	
	private void perform(Symbol symbol)
	{
		LinkedList<Action> actions = grammar.getActions(symbol);
		for(Action action : actions)
			action.perform(this);
	}
	
	public void setPosition(Point pos)
	{
		prev = current;
		current = pos;
	}
	
	public void moveForward(double amount)
	{
		setPosition(Point.add(current, Vector.mult(amount, direction)));
	}
	
	public void pushPosition()
	{
		positionStack.push(current);
	}
	
	public void popPosition()
	{
		setPosition(positionStack.pop());
	}
	
	public void pushDirection()
	{
		directionStack.push(direction);
	}
	
	public void popDirection()
	{
		direction = directionStack.pop();
	}
	
	public void rotateCCW(double radians)
	{
		direction = direction.rotateCCW(radians);
	}
	
	public void rotateCW(double radians)
	{
		rotateCCW(-radians);
	}
	
	public void drawLine()
	{
		lines.add(new LineSegment(prev, current));
	}
	
	private double min(double a, double b)
	{
		return (a < b) ? a : b;
	}
	
	private double min(double a, double b, double c)
	{
		return (min(a, b) < c) ? min(a, b) : c;
	}
	
	private double max(double a, double b)
	{
		return (a > b) ? a : b;
	}
	
	private double max(double a, double b, double c)
	{
		return (max(a, b) > c) ? max(a, b) : c;
	}
	
	private int getWidth()
	{
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		for(LineSegment line : lines)
		{
			min = min(min, line.a.x, line.b.x);
			max = max(max, line.a.x, line.b.x);
		}
		return (int) (max - min) + 6;
	}
	
	private int getHeight()
	{
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		for(LineSegment line : lines)
		{
			min = min(min, line.a.y, line.b.y);
			max = max(max, line.a.y, line.b.y);
		}
		return (int) (max - min) + 6;
	}
	
	private int getXShift()
	{
		double min = Double.POSITIVE_INFINITY;
		for(LineSegment line : lines)
			min = min(min, line.a.x, line.b.x);
		return (int) min - 3;
	}
	
	private int getYShift()
	{
		double max = Double.NEGATIVE_INFINITY;
		for(LineSegment line : lines)
			max = max(max, line.a.y, line.b.y);
		return (int) max + 3;
	}
	
	private void render(BufferedImage image, int xShift, int yShift, float thickness, Color foreground, Color background)
	{
		Graphics2D g = image.createGraphics();
		g.setColor(background);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.setColor(foreground);
		g.setStroke(new BasicStroke(thickness));
		for(LineSegment line : lines)
			line.render(g, xShift, yShift);
	}
	
	public BufferedImage getImage(float thickness, Color foreground, Color background)
	{
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		render(image, getXShift(), getYShift(), thickness, foreground, background);
		reset();
		return image;
	}
	
	public void reset()
	{
		lines.clear();
		current = origin.clone();
		direction = initial.clone();
		positionStack.clear();
		directionStack.clear();
	}

	public void setN(int numGenerations)
	{
		n = numGenerations;
	}
}
