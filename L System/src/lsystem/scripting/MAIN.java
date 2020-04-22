package lsystem.scripting;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import lsystem.LSystem;
import lsystem.Parser;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;
import lsystem.gui.Preset;

public class MAIN 
{
	static String directory = "C:\\Users\\Jacob Cohen\\Desktop\\Lines\\Fractal Binary Trees\\K1=0.7 K2=0.65 A=45 B=90\\CCW";
	
	public static void main(String args[]) throws IOException
	{
		try {
			generations("E:\\Finalized L-System Project\\Pythagorean Trees\\Semi-Coniferous Pythagorean Tree\\Semi-Coniferous Pythagorean Tree with Polygons.lsrs");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		FractalBinaryTree.run(400.0, Math.sqrt(2.0)/2.0, Math.sqrt(2.0) / 2.0, 90, 345, 1.0);
//		dragon();
//		cptreeGenerations();
//		PythagoreanTree.run();
//		ConiferousPythagoreanTree.run();
//		SemiConiferousPythagoreanTree.run();
		
//		File f = new File(directory);
//		String[] files = f.list();
//		
//		Comparator<String> comparator = new Comparator<String>()
//		{
//			@Override
//			public int compare(String o1, String o2) {
//				if(o1.length() < o2.length())
//					return -1;
//				else if(o1.length() > o2.length())
//					return +1;
//				else
//					return o1.compareTo(o2);
//			}
//		};
//		
//		FileOutputStream fos = new FileOutputStream(new File(directory + "\\" + "compiled.gif"));
//		Arrays.parallelSort(files, comparator);
//		AnimatedGifEncoder e = new AnimatedGifEncoder();
//		e.start(fos);
//		e.setDelay(1);
//		e.setTransparent(new Color(0, 0, 0, 0));
//		
//		for(int i = 0; i < files.length; i++)
//		{
//			BufferedImage image;
//			image = ImageIO.read(new File(directory + "\\" + files[i]));
//			e.addFrame(image);
//			if(i == 30)
//				break;
//		}
//		e.setQuality(1);
//		e.finish();
	}
	
//	public static void cptreeGenerations()
//	{
//		Point origin = new Point(0, 0);
//		Vector initial = new Vector(1, 0);
//		
//		String constants =
//				"F (MVFWD 300, DRAWLINE), H (MVFWD 500, DRAWLINE),\r\n" + 
//				"+90 (RCCW 90), -90 (RCW 90),\r\n" + 
//				"+a (RCCW 60), +b (RCCW 30),\r\n" + 
//				"-a (RCW 60), -b (RCW 30),\r\n" + 
//				"[ (PUSHPOS, PUSHDIR), ] (POPPOS, POPDIR),\r\n" + 
//				"> (SCALE 0.5), < (SCALE 2),\r\n" + 
//				"} (SCALE 0.86602540378), { (SCALE 1.15470053838)";
//		String variables = "X1, X2, Y1, Y2, U1, U2, S1, S2;";
//		String axiom = "X1";
//		String rules = 
//				"X1 = +90 H -90 [ +a > X2 < ] F [ +90 +90 -b } Y2 { ] -90 H -90 F;\r\n" + 
//				"X2 = +90 H -90 [ +a > U1 < ] F [ +90 +90 -b } S1 { ] -90 H -90 F;\r\n" + 
//				"Y1 = F -90 H -90 [ +a > X2 < ] F [ +90 +90 -b } Y2 { ] -90 H;\r\n" + 
//				"Y2 = F -90 H -90 [ +a > U1 < ] F [ +90 +90 -b } S1 { ] -90 H;\r\n" + 
//				"U1 = +90 H -90 [ +b } U2 { ] F [ +a +a > S2 < ] -90 H -90 F;\r\n" + 
//				"U2 = +90 H -90 [ +b } X1 { ] F [ +a +a > Y1 < ] -90 H -90 F;\r\n" + 
//				"S1 = F -90 H -90 [ +b } U2 { ] F [ +a +a > S2 < ] -90 H;\r\n" + 
//				"S2 = F -90 H -90 [ +b } X1 { ] F [ +a +a > Y1 < ] -90 H;";
		
//		String constants =
//				"F (MVFWD 300, ADDPOINT), H (MVFWD 500, ADDPOINT),\r\n" + 
//				"+90 (RCCW 90), -90 (RCW 90),\r\n" + 
//				"+a (RCCW 60), +b (RCCW 30),\r\n" + 
//				"-a (RCW 60), -b (RCW 30),\r\n" + 
//				"[ (PUSHPOS, PUSHDIR), ] (POPPOS, POPDIR),\r\n" + 
//				"> (SCALE 0.5), < (SCALE 2),\r\n" + 
//				"} (SCALE 0.86602540378), { (SCALE 1.15470053838),\r\n" +
//				"O (OPENPOLY), C (CLOSEPOLY)";
//		String variables = "X, Y, U, V;";
//		String axiom = "X";
//		String rules = 
//				"X = O+90 H -90 [ +a > U < ] F [ +90 +90 -b } V { ] -90 H -90 FC;\r\n" + 
//				"Y = OF -90 H -90 [ +a > U < ] F [ +90 +90 -b } V { ] -90 HC;\r\n" + 
//				"U = O+90 H -90 [ +b } X { ] F [ +90 +90 -a > Y < ] -90 H -90 FC;\r\n" + 
//				"V = OF -90 H -90 [ +b } X { ] F [ +90 +90 -a > Y < ] -90 HC;";
//		
//		int n = 15;
//		
//		Parser parser = new Parser(rules, constants, variables, axiom);
//		Parser.DEGREEMODE = true;
//		LSystem system = parser.parseLSystem(n, origin, initial);
//		system.depthbasedcolors = true;
//		system.roundedPercentages = false;
//		system.biggestGensFirst = false;
//		system.smallestGensFirst = true;
//		
//		system.setFinalColor(Color.RED);
//		system.startingColor = Color.BLUE;
//		system.thickness = 3f;
//		system.setN(n);
////		system.thickness = 16.0f;
////		GenerateGenerationalImages.run("Pythagorean Tree", Color.RED, Color.BLUE, system, 15, 1827, 1323, 30, 30, Color.black);
//		GenerateGenerationalImages.backwardsGeneration(system, new Color(0, 255, 0), new Color(255 , 204, 0), n, "Coniferous Pythagorean Tree", initial, 80);
//
//	}
	
	public static void generations(String fileLocation) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		File f = new File(fileLocation);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		Preset preset = (Preset) ois.readObject();
		ois.close();
		Parser parser = new Parser(preset.rules, preset.constants, preset.variables, preset.axiom);
		Parser.DEGREEMODE = preset.degreemode;
		LSystem system = parser.parseLSystem(3, new Point(0, 0), new Vector(1, 0));
		system.depthbasedcolors = preset.depthbasedColors;
		system.initial = new Vector(Double.valueOf(preset.i), Double.valueOf(preset.j));
		int n = Integer.valueOf(preset.n);
		system.setN(n);
		system.biggestGensFirst = preset.biggestFirst;
		system.smallestGensFirst = preset.smallestFirst;
		system.roundedPercentages = preset.roundedPercentages;
		system.thickness = Float.valueOf(preset.thickness);
		Color finalColor = preset.destinationColor;
		Color startColor = preset.foreground;
		Color background = preset.background;
		String title = preset.title;

		GenerateGenerationalImages.backwardsGeneration(system, startColor, finalColor, n, title, new Vector(Double.valueOf(preset.i), Double.valueOf(preset.j)), 30, background);
	}
	
//	public static void ptreeGenerations()
//	{
//		Point origin = new Point(0, 0);
//		Vector initial = new Vector(1, 0);
//		
////		String constants =
////				"F (MVFWD 200, DRAWLINE), G (MVFWD 300, DRAWLINE),\r\n" + 
////				"+ (RCCW 90), - (RCW 90),\r\n" + 
////				"a (RCCW 60), b (RCW 30),\r\n" + 
////				"[ (PUSHPOS, PUSHDIR), ] (POPPOS, POPDIR),\r\n" + 
////				">A (SCALE 0.5), A< (SCALE 2),\r\n" + 
////				">B (SCALE 0.86602540378), B< (SCALE 1.15470053838)";
////		String variables = "X, Y;";
////		String axiom = "X";
////		String rules = 
////				"X = + G - [ a >A X A< ] F [ + + b >B Y B< ] - G - F;\r\n" + 
////				"Y = F - G - [ a >A X A< ] F [ + + b >B Y B< ] - G;";
//		
//		String constants =
//				"F (MVFWD 200, ADDPOINT), G (MVFWD 300, ADDPOINT),\r\n" + 
//				"+ (RCCW 90), - (RCW 90),\r\n" + 
//				"a (RCCW 60), b (RCW 30),\r\n" + 
//				"[ (PUSHPOS, PUSHDIR), ] (POPPOS, POPDIR),\r\n" + 
//				">A (SCALE 0.5, INCTHICKNESS -1), A< (SCALE 2, INCTHICKNESS 1),\r\n" + 
//				">B (SCALE 0.86602540378, INCTHICKNESS -1), B< (SCALE 1.15470053838, INCTHICKNESS 1),\r\n" +
//				"O (OPENPOLY), C (CLOSEPOLY)";
//		String variables = "X, Y;";
//		String axiom = "X";
//		String rules = 
//				"X = O + G - [ a >A X A< ] F [ + + b >B Y B< ] - G - F C;\r\n" + 
//				"Y = O F - G - [ a >A X A< ] F [ + + b >B Y B< ] - G C;";
//		
//		int n = 15;
//		
//		Parser parser = new Parser(rules, constants, variables, axiom);
//		Parser.DEGREEMODE = true;
//		LSystem system = parser.parseLSystem(n, origin, initial);
//		system.depthbasedcolors = true;
//		system.roundedPercentages = false;
//		system.biggestGensFirst = false;
//		system.smallestGensFirst = true;
//		
//		system.setFinalColor(Color.RED);
//		system.startingColor = Color.BLUE;
//		system.thickness = 3f;
//		system.setN(n);
////		system.thickness = 16.0f;
////		GenerateGenerationalImages.run("Pythagorean Tree", Color.RED, Color.BLUE, system, 15, 1827, 1323, 30, 30, Color.black);
//		GenerateGenerationalImages.backwardsGeneration(system, new Color(0, 255, 0), new Color(255 , 204, 0), n, "Pythagorean Tree", initial, 30);
//
//	}
//	
//	public static void dragon()
//	{
//		Point origin = new Point(0, 12000);
//		Vector initial = new Vector(1, 0);
//		int n = 15;
//		String constants = "+ (RCCW 1.57079632679),\r\n" + 
//				"- (RCW 1.57079632679),\r\n" + 
//				"F (MVFWD 8, DRAWLINE)";
//		String variables = "X, Y";
//		String axiom = "FX";
//		
//		String rules = "X = X+YF+;\r\n" + 
//				"Y = -FX-Y;";
//		
//		Parser parser = new Parser(rules, constants, variables, axiom);
//		LSystem system = parser.parseLSystem(n, origin, initial);
//		
//		system.thickness = 4f;
//		
//		system.startingColor = Color.GREEN;
//		system.finalColor = Color.RED;
////		system.setFinalColor(Color.RED);
////		system.depthbasedcolors = true;
////		system.biggestGensFirst = false;
////		system.roundedPercentages = false;
////		system.smallestGensFirst = true;
//		GenerateGenerationalImages.backwardsGeneration(system, system.startingColor, system.finalColor, n, "Dragon Curve", new Vector(1.0, 0.0), 60);
////		GenerateGenerationalImages.run("Dragon Curve", Color.green, Color.red, system, 23, 16000, 16000, 150, 150, Color.BLACK);
//	}
}
