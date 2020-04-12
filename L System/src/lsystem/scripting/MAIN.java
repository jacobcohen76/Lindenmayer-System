package lsystem.scripting;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;

import lsystem.LSystem;
import lsystem.Parser;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

public class MAIN
{
	static int n = 20;
	static float thickness = 8.0f;
	static Vector initial = new Vector(1.0, 0.0);
	static Point origin = new Point(0.0, 0.0);
	
	static Color startingColor = new Color(0x000000);
//	static Color finalColor = new Color(0x725faf);
	static Color finalColor = new Color(0xFFFFFF);
	static Color background = new Color(0xD3DAF2);
	
	static int imagewidth = 8000;
	static int imageheight = 8000;
	
	static int padX = 500;
	static int padY = 500;
	
	static String directory= "E:\\L-Systems\\";
	static boolean degreemode = true;
	
	public static void main(String args[])
	{
		double width = 200.0;
		double height = 800.0;
		double inc_amount = 0.1;
		for(double A = 60; A <= (90.0 - inc_amount); A += inc_amount)
			pythagorean_tree(A, 90.0 - A, width, height);
	}
	
	public static void pythagorean_tree(double A, double B, double width, double height)
	{
		String constants =
				"F (MVFWD WIDTH, DRAWLINE), G (MVFWD HEIGHT, DRAWLINE),\r\n" + 
				"+ (RCCW 90), - (RCW 90),\r\n" + 
				"a (RCCW ANGLE_A), b (RCW ANGLE_B),\r\n" + 
				"[ (PUSHPOS, PUSHDIR), ] (POPPOS, POPDIR),\r\n" + 
				">A (SCALE DOWNSCALE_A), A< (SCALE UPSCALE_A),\r\n" + 
				">B (SCALE DOWNSCALE_B), B< (SCALE UPSCALE_B)";
		
		constants = constants.replace("WIDTH", String.valueOf(width));
		constants = constants.replace("HEIGHT", String.valueOf(height));
		constants = constants.replace("ANGLE_A", new BigDecimal(A).toPlainString());
		constants = constants.replace("ANGLE_B", new BigDecimal(B).toPlainString());
		
		A *= Math.PI / 180;
		B *= Math.PI / 180;
		
		constants = constants.replace("DOWNSCALE_A", new BigDecimal(Math.sin(B)).toPlainString());
		constants = constants.replace("DOWNSCALE_B", new BigDecimal(Math.sin(A)).toPlainString());
		constants = constants.replace("UPSCALE_A", new BigDecimal(1.0 / Math.sin(B)).toPlainString());
		constants = constants.replace("UPSCALE_B", new BigDecimal(1.0 / Math.sin(A)).toPlainString());

		String variables = "X, Y;";
		String axiom = "X";
		String rules = 
				"X = + G - [ a >A X A< ] F [ + + b >B Y B< ] - G - F;\r\n" + 
				"Y = F - G - [ a >A X A< ] F [ + + b >B Y B< ] - G;";
		
		Parser parser = new Parser(rules, constants, variables, axiom);
		Parser.DEGREEMODE = degreemode;
		LSystem system = parser.parseLSystem(n, origin, initial);
		system.thickness = thickness;
		system.startingColor = startingColor;
		system.setFinalColor(finalColor);
		system.generate(n);
		BufferedImage image = system.getImage(thickness, startingColor, background, imagewidth, imageheight, padX, padY);
		
		A *= 180 / Math.PI;
		B *= 180 / Math.PI;
		
		try {
			ImageIO.write(image, "png", new File(directory + "PythagoreanTree, A = " + String.format("%.2f", A) + ", B = " + String.format("%.2f", B) + ", n = " + n + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
