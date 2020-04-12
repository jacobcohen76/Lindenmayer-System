package lsystem;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import lsystem.actions.Action;
import lsystem.actions.MoveForward;
import lsystem.actions.RotateCCW;
import lsystem.actions.RotateCW;
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
	
	public Point origin;
	public Vector initial;
	
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
		if(finalColor == null)
			finalColor = new Color(startingColor.getRed(), startingColor.getGreen(), startingColor.getBlue(), startingColor.getAlpha());
		perform(getReplacement());
	}
	
	private void generate(Symbol symbol, int depth)
	{
		if(grammar.constants.contains(symbol.toString()))
			perform(symbol, depth);
		else if(depth == 0)
			perform(symbol, depth);
		else
		{
			LinkedList<Symbol> replacement = grammar.getReplacement(symbol);
			for(Symbol s : replacement)
				generate(s, depth - 1);
		}
	}
	
	private void generate2(Symbol symbol, int depth)
	{
		if(depth == 0)
			perform(symbol);
		else
		{
			LinkedList<Symbol> replacement = grammar.getReplacement(symbol);
			for(Symbol s : replacement)
				generate(s, depth - 1);
		}
	}
	
	public void generate(int depth)
	{
		if(finalColor == null)
			finalColor = new Color(startingColor.getRed(), startingColor.getGreen(), startingColor.getBlue(), startingColor.getAlpha());
		if(depthbasedcolors)
		{
			n = depth;
			for(Symbol symbol : axiom)
				generate(symbol, depth);
		}
		else
			for(Symbol symbol : axiom)
				generate2(symbol, depth);
		
		RotateCCW.direction = 1.0;
		RotateCW.direction = 1.0;
		
		MoveForward.scale_factor = 1.0;
		RotateCCW.increment = 0.0;
		RotateCW.increment = 0.0;
		RotateCCW.scale_factor = 1.0;
		RotateCW.scale_factor = 1.0;
	}
	
	public LinkedList<Symbol> getReplacement()
	{
		LinkedList<Symbol> production = axiom;
		for(int i = 0; i < n; i++)
		{
			production = grammar.getReplacement(production);
		}
		return production;
	}
	
	private AnimationFrame animationFrame = null;
	
	public void animate(Color foreground, Color background, WindowListener closureEvent)
	{
		reset();
		if(finalColor == null)
			finalColor = new Color(startingColor.getRed(), startingColor.getGreen(), startingColor.getBlue(), startingColor.getAlpha());
		
		currentLineColor = startingColor;
		
		LinkedList<Symbol> replacement = getReplacement();
		perform(replacement);
		
		int width = getWidth();
		int height = getHeight();
		int xShift = getXShift();
		int yShift = getYShift();
		int numLines = lines.size();
		
		LinkedList<LineSegment> lines = null;
		
		if(depthbasedcolors == false)
		{
			lines = this.lines;

			Iterator<LineSegment> itr = lines.iterator();
			if(finalColor != null)
			{
				finalColor = finalColor == null ? startingColor : finalColor;
				for(int i = 0; itr.hasNext(); i++)
					itr.next().color = getColor(startingColor, finalColor, i, this.lines.size());
	
			}
		}
		else
		{
			this.lines.clear();
			generate(n);
			lines = this.lines;
		}
		
		AnimationPanel animationPanel = new AnimationPanel(origin.clone(), initial.clone(), foreground, background, width, height, xShift, yShift, finalColor, numLines, thickness, lines, depthbasedcolors);
		animationFrame = new AnimationFrame(animationPanel, this, replacement, Color.BLACK, Color.WHITE, Color.YELLOW);
		animationFrame.addWindowListener(closureEvent);
		animationFrame.play();
	}
	
	public void stopAnimation()
	{
		if(animationFrame != null)
			animationFrame.stop();
	}
	
	private void perform(LinkedList<Symbol> axiom)
	{
		for(Symbol symbol : axiom)
		{
			perform(symbol);
		}
		
		RotateCCW.direction = 1.0;
		RotateCW.direction = 1.0;
		
		MoveForward.scale_factor = 1.0;
		RotateCCW.increment = 0.0;
		RotateCW.increment = 0.0;
		RotateCCW.scale_factor = 1.0;
		RotateCW.scale_factor = 1.0;
	}
	
	private Color currentLineColor = null;
	
	private void perform(Symbol symbol, int depth)
	{
		lineGeneratorNumber = depth;
		currentLineColor = getColor(startingColor, finalColor, n - depth, n);
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
		{
			action.perform(this);
		}
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
	
	public boolean depthbasedcolors = true;
	
	public Color startingColor = null;
	public float thickness = 1.0f;
	private int lineGeneratorNumber = 0;
	
	public void drawLine()
	{
		lines.add(new LineSegment(prev, current, new Color(currentLineColor.getRed(), currentLineColor.getGreen(), currentLineColor.getBlue(), currentLineColor.getAlpha()), thickness, lineGeneratorNumber));
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
		float mostThick = 0;
		for(LineSegment line : lines)
		{
			min = min(min, line.a.x, line.b.x);
			max = max(max, line.a.x, line.b.x);
			mostThick = Float.max(mostThick, line.thickness);
		}
		return (int) (Math.round(max - min) + 2 * mostThick);
	}
	
	private int getHeight()
	{
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		float mostThick = 0;
		for(LineSegment line : lines)
		{
			min = min(min, line.a.y, line.b.y);
			max = max(max, line.a.y, line.b.y);
			mostThick = Float.max(mostThick, line.thickness);
		}
		return (int) (Math.round(max - min) + 2 * mostThick);
	}
	
	private int getXShift()
	{
		double min = Double.POSITIVE_INFINITY;
		float mostThick = 0;
		for(LineSegment line : lines)
		{
			min = min(min, line.a.x, line.b.x);
			mostThick = Float.max(mostThick, line.thickness);
		}	
		return (int) Math.ceil(min - mostThick);
	}
	
	private int getYShift()
	{
		double max = Double.NEGATIVE_INFINITY;
		float mostThick = 0;
		for(LineSegment line : lines)
		{
			max = max(max, line.a.y, line.b.y);
			mostThick = Float.max(mostThick, line.thickness);
		}
		return (int) Math.ceil(max + mostThick);
	}
	
	private void render(BufferedImage image, int xShift, int yShift, float thickness, Color foreground, Color background)
	{
		Graphics2D g = image.createGraphics();
		g.setColor(background);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		if(depthbasedcolors == false)
		{
			Iterator<LineSegment> itr = lines.iterator();
			if(finalColor != null)
			{
				finalColor = finalColor == null ? startingColor : finalColor;
				for(int i = 0; itr.hasNext(); i++)
					itr.next().color = getColor(startingColor, finalColor, i, lines.size() - 1);
	
			}
		}
		if(smallestGensFirst == true)
		{
			for(int i = n - 1; i >= 0; i--)
			{
				Iterator<LineSegment> itr = lines.iterator();
				while(itr.hasNext())
				{
					LineSegment line = itr.next();
					if(line.n == i)
					{
						line.render(g, xShift, yShift);
						itr.remove();
					}
				}
			}
		}
		else
			for(LineSegment line : lines)
				line.render(g, xShift, yShift);
	}
	
	public int padY = 4;
	public int padX = 4;
	
	public BufferedImage getImage(float thickness, Color foreground, Color background)
	{
		BufferedImage image = new BufferedImage(getWidth() + 2 * padX, getHeight() + 2 * padY, BufferedImage.TYPE_INT_ARGB);
		render(image, getXShift() - padX, getYShift() + padY, thickness, foreground, background);
		reset();
		return image;
	}
	
	public BufferedImage getImage(float thicknesss, Color foreground, Color background, int width, int height, int padX, int padY)
	{
		this.padX = padX;
		this.padY = padY;
		BufferedImage image = new BufferedImage(width + padX * 2, height + padY * 2, BufferedImage.TYPE_INT_ARGB);
		render(image, -(width + 2 * padX) / 2, height - padY, thickness, foreground, background);
		reset();
		return image;
	}
	
	private Color getColor(Color origin, Color destination, double percent)
	{
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
	
	public boolean smallestGensFirst = true;
	public boolean roundedPercentages = false;
	
	private Color getColor(Color origin, Color destination, int i, int m)
	{
		i = roundedPercentages ? i / n : i;
		m = roundedPercentages ? m / n : m;
		return getColor(origin, destination, (double) i / (double) m);
	}
	
	private Color finalColor = null;
	
	public void setFinalColor(Color color)
	{
		finalColor = color;
		if(finalColor == null)
			finalColor = startingColor;
	}
	
	public void reset()
	{
		lines.clear();
		current = origin.clone();
		direction = initial.clone();
		positionStack.clear();
		directionStack.clear();
		
		RotateCCW.direction = 1.0;
		RotateCW.direction = 1.0;
		
		MoveForward.scale_factor = 1.0;
		RotateCCW.increment = 0.0;
		RotateCW.increment = 0.0;
		RotateCCW.scale_factor = 1.0;
		RotateCW.scale_factor = 1.0;
	}

	public void setN(int numGenerations)
	{
		n = numGenerations;
	}
}
