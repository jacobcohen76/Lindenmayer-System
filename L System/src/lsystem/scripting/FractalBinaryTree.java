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

public class FractalBinaryTree
{
	static int n = 14;
	static float thickness = 15f;
	static Vector initial = new Vector(0.0, 1.0);
	static Point origin = new Point(0.0, 0.0);
	static Color startingColor = Color.BLACK;
	static Color finalColor = Color.GREEN;
	static Color background = new Color(0, 0, 0, 0);
	
	static int imagewidth = 1750;
	static int imageheight = 1750;
	static int padX = 125;
	static int padY = 125;
	
	static String directory= "E:\\L-Systems\\Fractal Binary Trees\\frames\\";
	
	public static void run(double F, double K1, double K2, double A, double B, double inc_amount)
	{
		for(double increment = 0; increment <= 360.0; increment += inc_amount)
			FractalBinaryTreeGenerate(F, K1, K2, A + increment, B, directory + "CCW\\");
		for(double increment = 0; increment <= 360.0; increment += inc_amount)
			FractalBinaryTreeGenerate(F, K1, K2, A, B + increment, directory + "CW\\");
	}
	
	public static void FractalBinaryTreeGenerate(double F, double K1, double K2, double A, double B, String dir)
	{
		String constants =
				"F(MVFWD FLENGTH, DRAWLINE),\r\n" + 
				">K1(SCALE SCALEDOWNK1, INCTHICKNESS -1), <K1(SCALE SCALEUPK1, INCTHICKNESS 1),\r\n" + 
				">K2(SCALE SCALEDOWNK2, INCTHICKNESS -1), <K2(SCALE SCALEUPK2, INCTHICKNESS 1),\r\n" + 
				"+(RCCW ROTATEA), -(RCW ROTATEB), [(PUSHPOS, PUSHDIR), ](POPPOS, POPDIR)";
		
		constants = constants.replace("FLENGTH", new BigDecimal(F).toPlainString());
		constants = constants.replace("SCALEDOWNK1", new BigDecimal(K1).toPlainString());
		constants = constants.replace("SCALEUPK1", new BigDecimal(1.0 / K1).toPlainString());
		constants = constants.replace("SCALEDOWNK2", new BigDecimal(K2).toPlainString());
		constants = constants.replace("SCALEUPK2", new BigDecimal(1.0 / K2).toPlainString());
		constants = constants.replace("ROTATEA", new BigDecimal(A).toPlainString());
		constants = constants.replace("ROTATEB", new BigDecimal(B).toPlainString());
		
		String variables = "X";
		String axiom = "X";
		String rules = "X = F [ + >K1 X <K1 ] [ - >K2 X <K2 ];";
		
		Parser parser = new Parser(rules, constants, variables, axiom);
		Parser.DEGREEMODE = true;
		
		LSystem system = parser.parseLSystem(n, origin, initial);
		
		system.biggestGensFirst = false;
		system.smallestGensFirst = true;
		system.depthbasedcolors = true;
		system.thickness = thickness;
		system.startingColor = startingColor;
		system.setFinalColor(finalColor);
		
		system.generate(n);
		BufferedImage image = system.getImage(startingColor, background, imagewidth, imageheight, padX, padY);
		Graphics2D g2D = image.createGraphics();
		g2D.setColor(Color.BLACK);
		g2D.setFont(new Font("Dialog", Font.BOLD, 50));
		
		String part1 = "K1 = " + String.format("%.3f", K1);
		String part2 = "K2 = " + String.format("%.3f", K2);
		String part3 = "A = " + String.format("%.1f", A) + "°"; 
		String part4 = "B = " + String.format("%.1f", B) + "°";
		
		FontMetrics fm = g2D.getFontMetrics();
        int y = fm.getHeight();
        
		g2D.drawString(part1, 0, y);
		g2D.drawString(part2, 0, 2 * y);
		g2D.drawString(part3, 0, 3 *y);
		g2D.drawString(part4, 0, 4 * y);
		
		try {
			File f = new File(dir + "Fractal Binary Tree - A = " + String.format("%.1f", A) + " B = " + (B < 10.0 ? "0" : "") + (B < 1.0 ? "0" : "") + String.format("%.1f", B) + " n = " + n + ".png");
			if(f.getParentFile().exists() == false)
				f.getParentFile().mkdirs();
			ImageIO.write(image, "png", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
