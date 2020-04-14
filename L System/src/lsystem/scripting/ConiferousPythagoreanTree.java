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

public class ConiferousPythagoreanTree
{
	static int n = 15;
	static float thickness = 6f;
	static Vector initial = new Vector(1.0, 0.0);
	static Point origin = new Point(0.0, 0.0);
	
	static Color startingColor = new Color(0x9059ff);
	static Color finalColor = new Color(0xffca59);
	static Color background = new Color(0, 0, 0, 0);
	
	static Color text = Color.BLACK;
	
	static int imagewidth = 3700;
	static int imageheight = 3700;
	
	static int padX = 150;
	static int padY = 150;
	
	static String directory= "E:\\L-Systems\\frames\\";
	static boolean degreemode = true;
	
	public static void main(String args[])
	{
		double width = 120.0;
		double height = 194.0;
		double inc_amount = 0.5;
		pythagorean_tree(0.001, 90-0.001, width, height);
		for(double A = inc_amount; A < 90.0; A += inc_amount)
			pythagorean_tree(A, 90.0 - A, width, height);
		pythagorean_tree(90-0.001, 0.001, width, height);
	}
	
	public static void pythagorean_tree(double A, double B, double width, double height)
	{
		String constants =
				"F (MVFWD WIDTH, DRAWLINE), H (MVFWD HEIGHT, DRAWLINE),\r\n" + 
						"+90 (RCCW 90), -90 (RCW 90),\r\n" + 
						"+a (RCCW ANGLE_A), +b (RCCW ANGLE_B),\r\n" +
						"-a (RCW ANGLE_A), -b (RCW ANGLE_B),\r\n" + 
						"[ (PUSHPOS, PUSHDIR), ] (POPPOS, POPDIR),\r\n" + 
						"> (SCALE DOWNSCALE_A, SCLTHICK THICKDOWNA), < (SCALE UPSCALE_A, SCLTHICK THICKUPA),\r\n" + 
						"} (SCALE DOWNSCALE_B, SCLTHICK THICKDOWNB), { (SCALE UPSCALE_B, SCLTHICK THICKUPB)";
		
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
		
		String variables = "X1, X2, Y1, Y2, U1, U2, S1, S2;";
		String axiom = "X1";
		String rules = 
				"X1 = +90 H -90 [ +a > X2 < ] F [ +90 +90 -b } Y2 { ] -90 H -90 F;\r\n" + 
				"X2 = +90 H -90 [ +a > U1 < ] F [ +90 +90 -b } S1 { ] -90 H -90 F;\r\n" + 
				"Y1 = F -90 H -90 [ +a > X2 < ] F [ +90 +90 -b } Y2 { ] -90 H;\r\n" + 
				"Y2 = F -90 H -90 [ +a > U1 < ] F [ +90 +90 -b } S1 { ] -90 H;\r\n" + 
				"U1 = +90 H -90 [ +b } U2 { ] F [ +90 +90 -a > S2 < ] -90 H -90 F;\r\n" + 
				"U2 = +90 H -90 [ +b } X1 { ] F [ +90 +90 -a > Y1 < ] -90 H -90 F;\r\n" + 
				"S1 = F -90 H -90 [ +b } U2 { ] F [ +90 +90 -a > S2 < ] -90 H;\r\n" + 
				"S2 = F -90 H -90 [ +b } X1 { ] F [ +90 +90 -a > Y1 < ] -90 H;";
		
		Parser parser = new Parser(rules, constants, variables, axiom);
		Parser.DEGREEMODE = degreemode;
		LSystem system = parser.parseLSystem(n, origin, initial);
		
		system.biggestGensFirst = true;
		system.smallestGensFirst = false;
		
		system.thickness = thickness;
		system.startingColor = startingColor;
		system.setFinalColor(finalColor);
		system.generate(n);
		BufferedImage image = system.getImage(thickness, startingColor, background, imagewidth, imageheight, padX, padY);
		Graphics2D g2D = image.createGraphics();
		g2D.setColor(text);
		g2D.setFont(new Font("Dialog", Font.BOLD, 150));
		A *= 180 / Math.PI;
		B *= 180 / Math.PI;
        FontMetrics fm = g2D.getFontMetrics();
        String title = "Pythagorean Tree, n = " + n;
        String angles = "A = " + String.format("%.1f", A)  + "° " + "B = " + String.format("%.1f", B) + "°";
        int y = fm.getHeight();
        int x = (fm.stringWidth(title) - fm.stringWidth(angles)) / 2;
		g2D.drawString(title, 0, y);
		g2D.drawString(angles, x, 2 * y);
		try {
			ImageIO.write(image, "png", new File(directory + "Coniferous Pythagorean Tree - A = " + String.format("%.2f", A) + " B = " + String.format("%.2f", B) + " n = " + n + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
