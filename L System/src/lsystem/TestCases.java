package lsystem;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

public class TestCases
{
	public static int NUMGENERATIONS = 24;
	public static Point ORIGIN = new Point(0, 0);
	public static Vector INITIAL = new Vector(0, 1);
	
	public static float THICKNESS = 1.0f;
	public static Color FOREGROUND = Color.GREEN;
	public static Color BACKGROUND = Color.BLACK;
	
	public static void main(String args[]) throws IOException
	{
		LSystem system = DragonCurve();
		system.generate();
		File output = new File("D:\\output.png");
		BufferedImage image = system.getImage(THICKNESS, FOREGROUND, BACKGROUND);
		ImageIO.write(image , "png", output);
	}
	
	public static LSystem FractalPlant()
	{
		String constantInputs = "+(RCCW 0.436332313),-(RCW 0.436332313),[(PUSHPOS, PUSHDIR), ](POPPOS, POPDIR)";
		String variableInputs = "X,F(MVFWD 3, DRAWLINE)";
		String axiomInput = "X";
		String ruleInput = "X=F+[[X]-X]-F[-FX]+X;F=FF;";
		
		int n = NUMGENERATIONS;
		Point origin = ORIGIN;
		Vector initial = INITIAL;
		
		Parser parser = new Parser(ruleInput, constantInputs, variableInputs, axiomInput);
		return parser.parseLSystem(n, origin, initial);
	}
	
	public static LSystem DragonCurve()
	{
		String constantInputs = "F(MVFWD 3, DRAWLINE),+(RCCW 1.57079632679),-(RCW 1.57079632679)";
		String variableInputs = "X,Y";
		String axiomInput = "FX";
		String ruleInput = "X=X+YF+;Y=-FX-Y;";
		
		int n = NUMGENERATIONS;
		Point origin = ORIGIN;
		Vector initial = INITIAL;
		
		Parser parser = new Parser(ruleInput, constantInputs, variableInputs, axiomInput);
		return parser.parseLSystem(n, origin, initial);
	}
	
	public static LSystem SierpinskiArrowhead()
	{
		String constantInputs = "+(RCCW 1.0471975512),-(RCW 1.0471975512),[(PUSHPOS, PUSHDIR, RCCW 0.7853982),](POPPOS, POPDIR, RCW 0.7853982)";
		String variableInputs = "A(MVFWD 5, DRAWLINE), B(MVFWD 5, DRAWLINE)";
		String axiomInput = "A";
		String ruleInput = "A=B-A-B;B=A+B+A;";
		
		int n = NUMGENERATIONS;
		Point origin = ORIGIN;
		Vector initial = INITIAL;
		
		Parser parser = new Parser(ruleInput, constantInputs, variableInputs, axiomInput);
		return parser.parseLSystem(n, origin, initial);
	}
	
	public static LSystem SierpinskiTriangle()
	{
		String constantInputs = "+(RCCW 2.0943951024), -(RCW 2.0943951024)";
		String variableInputs = "F(MVFWD 5, DRAWLINE), G(MVFWD 5, DRAWLINE)";
		String axiomInput = "F-G-G";
		String ruleInput = "F=F-G+F+G-F;G=GG;";
		
		int n = NUMGENERATIONS;
		Point origin = ORIGIN;
		Vector initial = INITIAL;
		
		Parser parser = new Parser(ruleInput, constantInputs, variableInputs, axiomInput);
		return parser.parseLSystem(n, origin, initial);
	}
	
	public static LSystem FractalBinaryTree()
	{
		String constantInputs = "[(PUSHPOS, PUSHDIR, RCCW 0.7853981634), ](POPPOS, POPDIR, RCW 0.7853981634)";
		String variableInputs = "A(MVFWD 2, DRAWLINE), B(MVFWD 5, DRAWLINE)";
		String axiomInput = "A";
		String ruleInput = "B=BB;A=B[A]A;";
		
		int n = NUMGENERATIONS;
		Point origin = ORIGIN;
		Vector initial = INITIAL;
		
		Parser parser = new Parser(ruleInput, constantInputs, variableInputs, axiomInput);
		return parser.parseLSystem(n, origin, initial);
	}
	
	public static LSystem KochCurve()
	{
		String constantInputs = "+(RCCW 1.5707963268), -(RCW 1.5707963268)";
		String variableInputs = "F(MVFWD 5, DRAWLINE)";
		String axiomInput = "F";
		String ruleInput = "F=F+F-F-F+F;";
		
		int n = NUMGENERATIONS;
		Point origin = ORIGIN;
		Vector initial = INITIAL;
		
		Parser parser = new Parser(ruleInput, constantInputs, variableInputs, axiomInput);
		return parser.parseLSystem(n, origin, initial);
	}
}
