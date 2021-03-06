package lsystem.scripting;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;

import lsystem.LSystem;
import lsystem.Parser;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

public class PythagoreanTree
{
	static int n = 15;
	static float thickness = 6f;
	static Vector initial = new Vector(1.0, 0.0);
	static Point origin = new Point(0.0, 0.0);
	
	static Color startingColor = new Color(0x9059ff);
	static Color finalColor = new Color(0xffca59);
	static Color background = new Color(0, 0, 0, 0);
	
	static Color text = Color.BLACK;
	
	static int imagewidth = 1400;
	static int imageheight = 1400;
	
	static int padX = 75;
	static int padY = 75;
	
	static String directory= "E:\\L-Systems\\Pythagorean Tree\\frames\\";
	static boolean degreemode = true;
	
	public static void main(String args[])
	{
		double width = 36;
		double height = 60;
		double inc_amount = 0.5;
		pythagorean_tree(0.001, 90-0.001, width, height);
		for(double A = inc_amount; A < 90.0; A += inc_amount)
			pythagorean_tree(A, 90.0 - A, width, height);
		pythagorean_tree(90-0.001, 0.001, width, height);
	}
	
	public static void run()
	{
		double width = 45.0;
		double height = 74.0;
		double inc_amount = 0.5;
		pythagorean_tree(0.001, 90-0.001, width, height);
		for(double A = inc_amount; A < 90.0; A += inc_amount)
			pythagorean_tree(A, 90.0 - A, width, height);
	}
	
	public static void pythagorean_tree(double A, double B, double width, double height)
	{
		origin = new Point(0.0 - width / 2.0, 0.0);
		String constants =
				"F (MVFWD WIDTH, DRAWLINE), G (MVFWD HEIGHT, DRAWLINE),\r\n" + 
				"+ (RCCW 90), - (RCW 90),\r\n" + 
				"a (RCCW ANGLE_A), b (RCW ANGLE_B),\r\n" + 
				"[ (PUSHPOS, PUSHDIR), ] (POPPOS, POPDIR),\r\n" + 
				">A (SCALE DOWNSCALE_A, SCLTHICK THICKDOWNA), A< (SCALE UPSCALE_A, SCLTHICK THICKUPA),\r\n" + 
				">B (SCALE DOWNSCALE_B, SCLTHICK THICKDOWNB), B< (SCALE UPSCALE_B, SCLTHICK THICKUPB)";
				//"O (OPENPOLY), C (CLOSEPOLY)";
		
		constants = constants.replace("WIDTH", String.valueOf(width));
		constants = constants.replace("HEIGHT", String.valueOf(height));
		constants = constants.replace("ANGLE_A", new BigDecimal(A).toPlainString());
		constants = constants.replace("ANGLE_B", new BigDecimal(B).toPlainString());
		
		A *= Math.PI / 180;
		B *= Math.PI / 180;
		
		double scalefactor = 0.95;
		constants = constants.replace("THICKDOWNA", new BigDecimal(scalefactor).toPlainString());
		constants = constants.replace("THICKDOWNB", new BigDecimal(scalefactor).toPlainString());
		constants = constants.replace("THICKUPA", new BigDecimal(1.0 / scalefactor).toPlainString());
		constants = constants.replace("THICKUPB", new BigDecimal(1.0 / scalefactor).toPlainString());
		
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
		system.biggestGensFirst = true;
		system.smallestGensFirst = false;
		system.thickness = thickness;
		system.startingColor = startingColor;
		system.setFinalColor(finalColor);
		system.generate(n);
		BufferedImage image = system.getImage(startingColor, background, imagewidth, imageheight, padX, padY);
		Graphics2D g2D = image.createGraphics();
		g2D.setColor(text);
		g2D.setFont(new Font("Dialog", Font.BOLD, 40));
		
		A *= 180 / Math.PI;
		B *= 180 / Math.PI;
		
		 FontMetrics fm = g2D.getFontMetrics();
	        String title = "Pythagorean Tree";
	        String angles = "n = " + n + " " + "A = " + String.format("%.1f", A)  + "� " + "B = " + String.format("%.1f", B) + "�";
	        int y = fm.getHeight();
	        
 		g2D.drawString(title, 30, y);
 		g2D.drawString(angles, 30, 2 * y);
		try {
			File f =  new File(directory + "PythagoreanTree - A = " + String.format("%.1f", A) + " B = " + String.format("%.1f", B) + " n = " + n + ".png");
			f.mkdirs();
			ImageIO.write(image, "png",f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
