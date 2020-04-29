package lsystem.scripting;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lsystem.LSystem;
import lsystem.cartesian2d.Vector;

public class GenerateGenerationalImages
{
	static String directory = "E:\\L-Systems\\Generations\\TITLE\\frames\\";
	
	public static void run(String title, Color startingColor, Color finalColor, LSystem system, int n, int width, int height, int padX, int padY, Color text)
	{
		String dir = directory.replace("TITLE", title);
		
		int dr = 0, dg = 0, db = 0, da = 0;
		if(finalColor != null)
		{
			dr = finalColor.getRed() - startingColor.getRed();
			dg = finalColor.getGreen() - startingColor.getGreen();
			db = finalColor.getBlue() - startingColor.getBlue();
			da = finalColor.getAlpha() - startingColor.getAlpha();
		}
		float thick = system.thickness;
		for(int i = 0; i <= n; i++)
		{
			system.thickness = thick;
			double percent = (double) (i) / (double) n;
			Color tempColor = new Color(startingColor.getRed() + (int)(dr * percent), startingColor.getGreen() + (int)(dg * percent), startingColor.getBlue() + (int)(db * percent), startingColor.getAlpha() + (int)(da * percent));
			render(system, tempColor, tempColor, title, dir, i, width, height, padX, padY, text);
		}
	}
	
	public static void render(LSystem system, Color startingColor, Color finalColor, String title, String dir, int i, int width, int height, int padX, int padY, Color text)
	{
		system.startingColor = startingColor;
		system.finalColor = finalColor;
		system.setN(i);
		system.generate();
		BufferedImage image = system.getImage(system.startingColor, new Color(0, 0, 0, 0), width, height, padX, padY);
		Graphics2D g2D = image.createGraphics();
		g2D.setColor(text);
		g2D.setFont(new Font("Dialog", Font.BOLD, 150));
		
		FontMetrics fm = g2D.getFontMetrics();
		int y = fm.getHeight();
		
		g2D.drawString(title, 0, y);
		g2D.drawString("n = " + i, 0, 2 * y);
		
		try {
			File f = new File(dir + title + " n = " + i + ".png");
			if(f.getParentFile().exists() == false)
				f.getParentFile().mkdirs();
			ImageIO.write(image, "png", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Color getColor(Color startingColor, Color finalColor, int i, int n)
	{
		int dr = 0, dg = 0, db = 0, da = 0;
		if(finalColor != null)
		{
			dr = finalColor.getRed() - startingColor.getRed();
			dg = finalColor.getGreen() - startingColor.getGreen();
			db = finalColor.getBlue() - startingColor.getBlue();
			da = finalColor.getAlpha() - startingColor.getAlpha();
		}
		System.out.println(dr + " " + dg + " " + db + " " + da);
		double percent = (double)i / (double)n;
		return new Color(startingColor.getRed() + (int)(dr * percent), startingColor.getGreen() + (int)(dg * percent), startingColor.getBlue() + (int)(db * percent), startingColor.getAlpha() + (int)(da * percent));
	}
	
	public static void backwardsGeneration(LSystem system, Color startingColor, Color finalColor, int n, String title, Vector initial, int fontsize, Color background)
	{
		int i = n;
		int width, height;
		float thick = system.thickness;
		system.initial = initial.clone();
		system.finalColor = getColor(startingColor, finalColor, i, n);
		system.startingColor = startingColor;
		system.setN(i);
		system.generate(i);
		
		BufferedImage image = system.getImage(1.0f, startingColor, background);
		
		width = image.getWidth();
		height = image.getHeight();
		int xShift = system.lastXShift;
		int yShift = system.lastYShift;
		
		String dir = directory.replace("TITLE", title);
		
		Graphics2D g2D = image.createGraphics();
		g2D.setColor(Color.BLACK);
		g2D.setFont(new Font("Dialog", Font.BOLD, fontsize));
		
		FontMetrics fm = g2D.getFontMetrics();
		int y = fm.getHeight();
		
		g2D.drawString(title, 0, y);
		g2D.drawString("n = " + i, 0, 2 * y);
		
		try {
			File f = new File(dir + title + " n = " + i + ".png");
			if(f.getParentFile().exists() == false)
				f.getParentFile().mkdirs();
			ImageIO.write(image, "png", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(i = 0; i < n; i++)
		{
			system.reset();
			system.thickness = thick;
			system.finalColor = getColor(startingColor, finalColor, i, n);
			system.startingColor = startingColor;

			system.setN(i);
			system.generate(i);
			image = system.getImage(width, height, xShift, yShift, background);
			
			g2D = image.createGraphics();
			g2D.setColor(Color.BLACK);
			g2D.setFont(new Font("Dialog", Font.BOLD, fontsize));
			
			fm = g2D.getFontMetrics();
			y = fm.getHeight();
			
			g2D.drawString(title, 0, y);
			g2D.drawString("n = " + i, 0, 2 * y);
			
			try {
				File f = new File(dir + title + " n = " + i + ".png");
				if(f.getParentFile().exists() == false)
					f.getParentFile().mkdirs();
				ImageIO.write(image, "png", f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
