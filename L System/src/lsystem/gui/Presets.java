package lsystem.gui;

public class Presets
{
	public static final Preset KOCH_SNOWFLAKE;
	public static final Preset KOCH_ANTI_SNOWFLAKE;
	public static final Preset GOSPER_CURVE;
	public static final Preset ICY_FRACTAL;
	public static final Preset FRACTAL_TREE;
	public static final Preset PENTADENDRITE;
	public static final Preset ROUND_STAR;
	public static final Preset ISLAND_CURVE;
	public static final Preset PENROSE_TILING;
	
	public static final Preset SQUARE_SNOWFLAKE;
	public static final Preset FRACTAL_TILES;
	
	public static final Preset BOURKE_CROSS_1;
	public static final Preset BOURKE_CROSS_2;
	public static final Preset PENTAPLEXITY;
	public static final Preset FRACTAL_RINGS;
	public static final Preset HEXAGONAL_GOSPER;
	public static final Preset LEVY_CURVE;
	public static final Preset SIERPINSKI_CURVE;
	public static final Preset KRISHNA_ANKLETS;
	public static final Preset MANGO_LEAF;
	public static final Preset KOLAM;
	
	public static final Preset LEAF;
	public static final Preset TUMBLE_WEED;
	public static final Preset BINARY_BUSH;
	public static final Preset TRINARY_BUSH;
	public static final Preset FERN;
	public static final Preset BROCCOLI;
	
	static
	{
		String title = "",
			   thickness = "",
			   i = "",
			   j = "",
			   n = "",
			   constants = "",
			   variables = "",
			   axiom = "",
			   rules = "";
		
		title = "Broccoli";
		thickness = "1.0";
		i = "0.0";
		j = "1.0";
		n = "12";
		constants = "[(PUSHPOS, PUSHDIR, RCCW 0.6981317008),\r\n"
				  + "](POPPOS, POPDIR, RCW 0.6981317008)";
		variables = "A(MVFWD 5, DRAWLINE),\r\n"
				  + "B(MVFWD 7, DRAWLINE)";
		axiom = "A";
		rules = "B=BB;\nA=B[A]A;";
		BROCCOLI = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Leaf";
		thickness = "1.0";
		i = "0.0";
		j = "1.0";
		n = "12";
		constants = "+ (RCCW 0.7853981634),\r\n"
				  + "- (RCW 0.7853981634),\r\n"
				  + "[(PUSHPOS, PUSHDIR),\r\n"
				  + "](POPPOS, POPDIR), \r\n"
				  + "> (SCALE 1.36),\r\n"
				  + "< (SCALE 0.73529411764)";
		variables = "F (MVFWD 4, DRAWLINE),\r\n"
				  + "a, b, x, y";
		axiom = "a";
		rules = "F = >F<;\r\n"
			  + "a = F[+x]Fb;\r\n"
			  + "b = F[-y]Fa;\r\n"
			  + "x = a;\r\n"
			  + "y = b;";
		LEAF = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		
		title = "Tumble Weed";
		thickness = "1.0";
		i = "0.0";
		j = "1.0";
		n = "4";
		constants = "+ (RCCW 0.3926990817),\r\n"
				  + "- (RCW 0.3926990817),\r\n"
				  + "[(PUSHPOS, PUSHDIR),\r\n"
				  + "](POPPOS, POPDIR)";
		variables = "F (MVFWD 15, DRAWLINE)";
		axiom = "F";
		rules = "F = FF+[+F-F-F]-[-F+F+F];";
		TUMBLE_WEED = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Binary Bush";
		thickness = "1.0";
		i = "0.0";
		j = "1.0";
		n = "6";
		constants = "+ (RCCW 0.44854961776),\r\n"
				  + "- (RCW 0.44854961776),\r\n"
				  + "[(PUSHPOS, PUSHDIR),\r\n"
				  + "](POPPOS, POPDIR), \r\n"
				  + "F (MVFWD 8, DRAWLINE)";
		variables = "X, Y";
		axiom = "Y";
		rules = "X = X[-FFF][+FFF]FX;\n"
			  + "Y = YFX[+Y][-Y];";
		BINARY_BUSH = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Trinary Bush";
		thickness = "1.0";
		i = "0.0";
		j = "1.0";
		n = "4";
		constants = "+ (RCCW 0.6108652382),\r\n"
				  + "- (RCW 0.6108652382),\r\n"
				  + "[(PUSHPOS, PUSHDIR),\r\n"
				  + "](POPPOS, POPDIR)";
		variables = "F (MVFWD 8, DRAWLINE)";
		axiom = "F";
		rules = "F = F[+FF][-FF]F[-F][+F]F;";
		TRINARY_BUSH = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		

		title = "Fern";
		thickness = "1.0";
		i = "0.0";
		j = "1.0";
		n = "10";
		constants = "+ (RCCW 0.3490658504),\r\n"
				  + "- (RCW 0.3490658504),\r\n"
				  + "[(PUSHPOS, PUSHDIR),\r\n"
				  + "](POPPOS, POPDIR),\r\n"
				  + "F (MVFWD 15, DRAWLINE)";
		variables = "V, W, X, Y, Z";
		axiom = "VZFFF";
		rules = "V = [+++W][---W]YV;\r\n"
			  + "W = +X[-W]Z;\r\n"
			  + "X = -W[+X]Z;\r\n" + 
			  "Y = YZ;\r\n" + 
			  "Z = [-FFF][+FFF]F;";
		FERN = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Koch Snowflake";
		thickness = "1.0";
		i = "1.0";
		j = "0.0";
		n = "5";
		constants = "+ (RCCW 1.0471975512),\r\n"
	  			  + "- (RCW 1.0471975512)";
		variables = "F (MVFWD 10, DRAWLINE)";
		axiom = "F + + F + + F";
		rules = "F = F - F + + F - F;";
		KOCH_SNOWFLAKE = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		
		title = "Bourke Cross 1";
		thickness = "1.0";
		i = "1.0";
		j = "0.0";
		n = "4";
		constants = "+ (RCCW 1.57079632679),\r\n"
	              + "- (RCW 1.57079632679)";
		variables = "F (MVFWD 10, DRAWLINE)";
		axiom = "F + F + F + F";
		rules = "F = F + F F + + F + F;";
		BOURKE_CROSS_1 = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Bourke Cross 2";
		thickness = "1.0";
		i = "1.0";
		j = "0.0";
		n = "4";
		constants = "+ (RCCW 1.57079632679),\r\n"
	              + "- (RCW 1.57079632679)";
		variables = "F (MVFWD 10, DRAWLINE)";
		axiom = "F + F + F + F";
		rules = "F = F + F - F + F + F;";
		BOURKE_CROSS_2 = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		 
		title = "Pentaplexity";
		thickness = "";
		thickness = "1.0";
		i = "1.0";
		j = "0.0";
		n = "4";
		constants = "+ (RCCW 1.57079632679),\r\n"
	              + "- (RCW 1.57079632679),\r\n"
				  + "| (RCCW 3.1415926536)";
		variables = "F (MVFWD 10, DRAWLINE)";
		axiom = "F + + F + + F + + F + + F";
		rules = "F = F + + F + + F | F - F + + F;";
		PENTAPLEXITY = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Fractal Rings";
		thickness = "1.0";
		i = "1.0";
		j = "0";
		n = "3";
		constants = "+ (RCCW 1.57079632679),\r\n"
				  + "- (RCW 1.57079632679)";
		variables = "F (MVFWD 10, DRAWLINE)";
		axiom = "F + F + F + F";
		rules = "F = F F + F + F + F + F + F - F";
		FRACTAL_RINGS = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Hexagonal Gosper";
		thickness = "1.0";
		i = "1.0";
		j = "0";
		n = "3";
		constants = "+ (RCCW 1.0471975512),\r\n"
	  			  + "- (RCW 1.0471975512),\r\n"
	  			  + "F (MVFWD 10, DRAWLINE)";
		variables = "X, Y";
		axiom = "X F";
		rules = "X = X + Y F + + Y F _ F X _ _ F X F X - Y F +;\r\n"
			  + "Y = - F X + Y F Y F + + Y F + F X - - F X - Y;";
		HEXAGONAL_GOSPER = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Levy Curve";
		thickness = "1.0";
		i = "1.0";
		j = "0";
		n = "3";
		constants = "+ (RCCW 0.7853981634),\r\n"
				  + "- (RCW 0.7853981634)";
		variables = "F (MVFWD 10, DRAWLINE)";
		axiom = "F";
		rules = "F = - F + + F -";
		LEVY_CURVE = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Sierpinski Curve";
		thickness = "1.0";
		i = "1.0";
		j = "0";
		n = "3";
		constants = "+ (RCCW 0.7853981634),\r\n"
				  + "- (RCW 0.7853981634),\r\n"
				  + "F (MVFWD 10, DRAWLINE)";
		variables = "X";
		axiom = "F - - X F - - F - - X F";
		rules = "X = X F + F + X F - - F - - X F + F + X;";
		SIERPINSKI_CURVE = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Krishna Anklets";
		thickness = "1.0";
		i = "1.0";
		j = "0";
		n = "3";
		constants = "+ (RCCW 0.7853981634),\r\n"
				  + "- (RCW 0.7853981634),\r\n"
				  + "F (MVFWD 10, DRAWLINE)";
		variables = "X";
		axiom = "- X - - X";
		rules = "X = X F X - - X F X;";
		KRISHNA_ANKLETS = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		title = "Mango Leaf";
		thickness = "1.0";
		i = "1.0";
		j = "0";
		n = "3";
		constants = "+ (RCCW 1.0471975512),\r\n"
				  + "- (RCW 1.0471975512),\r\n,"
				  + "[(PUSHPOS, PUSHDIR),\r\n"
	   			  + "](POPPOS, POPDIR), \r\n"
				  + "F (MVFWD 10, DRAWLINE),\r\n"
				  + "f (MVFWD 10)";
		variables = "X, Y";
		axiom = "Y - - - Y";
		rules = "X={F-F}{F-F}--[--X]{F-F}{F-F}--{F-F}{F-F}--;\r\n"
			  + "Y=f-F+X+F-fY;";
		MANGO_LEAF = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);
		
		
		title = "Kolam";
		thickness = "1.0";
		i = "1.0";
		j = "0";
		n = "3";
		constants = "+ (RCCW 0.7853981634),\r\n"
				  + "- (RCW 0.7853981634),\r\n"
				  + "F (MVFWD 10, DRAWLINE),\r\n"
				  + "( (INCANGLE 0.1),\r\n"
				  + "( (DECANGLE 0.1)";
		variables = "A, B, C, D";
		axiom = "( - D - - D )";
		rules = "A = F++FFFF--F--FFFF++F++FFFF--F;\r\n" + 
				"B = F--FFFF++F++FFFF--F--FFFF++F;\r\n" + 
				"C = BFA--BFA;\r\n" + 
				"D = CFC--CFC;";
		KOLAM = new Preset(title, thickness, i, j, n, constants, variables, axiom, rules);

		
		KOCH_ANTI_SNOWFLAKE = new Preset();
		KOCH_ANTI_SNOWFLAKE.constants = "+ (RCCW 1.0471975512),\r\n"
									  + "- (RCW 1.0471975512)";
		KOCH_ANTI_SNOWFLAKE.variables = "F (MVFWD 10, DRAWLINE)";
		KOCH_ANTI_SNOWFLAKE.axiom = "F + + F + + F";
		KOCH_ANTI_SNOWFLAKE.rules = "F = F + F - - F + F;";
		
		GOSPER_CURVE = new Preset();
		GOSPER_CURVE.i = "1";
		GOSPER_CURVE.j = "0";
		GOSPER_CURVE.n = "4";
		GOSPER_CURVE.constants = "+ (RCCW 1.0471975512),\r\n"
				  			   + "- (RCW 1.0471975512)";
		GOSPER_CURVE.variables = "A (MVFWD 10, DRAWLINE),\r\n"
							   + "B (MVFWD 10, DRAWLINE)";
		GOSPER_CURVE.axiom = "A";
		GOSPER_CURVE.rules = "A = A - B - - B + A + + A A + B -;\r\n"
						   + "B = + A - B B - - B - A + + A + B;";
		
		ICY_FRACTAL = new Preset();
		ICY_FRACTAL.i = "0";
		ICY_FRACTAL.j = "1";
		ICY_FRACTAL.n = "4";
		ICY_FRACTAL.constants = "+ (RCCW 1.57079632679),\r\n"
				              + "- (RCW 1.57079632679)";
		ICY_FRACTAL.variables = "F (MVFWD 10, DRAWLINE)";
		ICY_FRACTAL.axiom = "F + F + F + F";
		ICY_FRACTAL.rules = "F = F F + F + + F + F;";
		
		FRACTAL_TREE = new Preset();
		FRACTAL_TREE.i = "0";
		FRACTAL_TREE.j = "1";
		FRACTAL_TREE.n = "3";
		FRACTAL_TREE.constants = "+(RCCW 0.62831853071),\r\n"
							   + "-(RCW 0.62831853071),\r\n"
							   + "[(PUSHPOS, PUSHDIR),\r\n"
							   + "](POPPOS, POPDIR)";
		FRACTAL_TREE.variables = "F (MVFWD 10, DRAWLINE)";
		FRACTAL_TREE.axiom = "F";
		FRACTAL_TREE.rules = "F = F [ + F F ] [ - F F ] F [ - F ] [ + F ] F;";
		
		PENTADENDRITE = new Preset();
		PENTADENDRITE.i = "0";
		PENTADENDRITE.j = "1";
		PENTADENDRITE.n = "4";
		PENTADENDRITE.constants = "+(RCCW 1.25663706144),\r\n"
				   				+ "-(RCW 1.25663706144)";
		PENTADENDRITE.variables = "F (MVFWD 10, DRAWLINE)";
		PENTADENDRITE.axiom = "F - F - F - F - F";
		PENTADENDRITE.rules = "F = F - F - F + + F + F - F;";
		
		ROUND_STAR = new Preset();
		ROUND_STAR.i = "0";
		ROUND_STAR.j = "1";
		ROUND_STAR.n = "7";
		ROUND_STAR.constants = "+ (RCCW 1.34390352404)";
		ROUND_STAR.variables = "F (MVFWD 800, DRAWLINE)";
		ROUND_STAR.axiom = "F";
		ROUND_STAR.rules = "F = F + + F;";
		
		ISLAND_CURVE = new Preset();
		ISLAND_CURVE.i = "0";
		ISLAND_CURVE.j = "1";
		ISLAND_CURVE.n = "2";
		ISLAND_CURVE.constants = "+ (RCCW 1.57079632679),\r\n"
							   + "- (RCW 1.57079632679)";
		ISLAND_CURVE.variables = "F (MVFWD 10, DRAWLINE),\r\n"
							   + "b";
		ISLAND_CURVE.axiom = "F - F - F - F";
		ISLAND_CURVE.rules = "F=F-b+FF-F-FF-Fb-FF+b-FF+F+FF+Fb+FFF;\r\n"
						   + "b=bbbbbb;";
		
		PENROSE_TILING = new Preset();
		PENROSE_TILING.i = "0";
		PENROSE_TILING.j = "1";
		PENROSE_TILING.n = "5";
		PENROSE_TILING.constants = "+(RCCW 0.62831853071),\r\n"
				   				 + "-(RCW 0.62831853071),\r\n"
				   				 + "[(PUSHPOS, PUSHDIR),\r\n"
				   				 + "](POPPOS, POPDIR), \r\n"
				   				 + "E";
		PENROSE_TILING.variables = "A (MVFWD 10, DRAWLINE),\r\n"
								 + "B (MVFWD 10, DRAWLINE),\r\n"
								 + "C (MVFWD 10, DRAWLINE),\r\n"
								 + "D (MVFWD 10, DRAWLINE)";
		PENROSE_TILING.axiom = "[ B ] + + [ B ] + + [ B ] + + [ B ] + + [ B ]";
		PENROSE_TILING.rules = "A = D E + + D E - - - - B E [ - C E - - - - A E ] + +;\r\n"
							 + "B = + C E - - D E [ - - - A E - - B E ] +;\r\n"
							 + "C = - A E + + B E [ + + + C E + + D E ] -;\r\n"
							 + "D = - - C E + + + + A E [ + D E + + + + B E ] - - B E;";
		
		SQUARE_SNOWFLAKE = new Preset();
		SQUARE_SNOWFLAKE.i = "1";
		SQUARE_SNOWFLAKE.j = "0";
		SQUARE_SNOWFLAKE.n = "2";
		SQUARE_SNOWFLAKE.constants = "+(RCCW 1.5707963268),\r\n"
  				 				   + "-(RCW 1.5707963268)";
		SQUARE_SNOWFLAKE.variables = "F (MVFWD 10, DRAWLINE)";
		SQUARE_SNOWFLAKE.axiom = "F + F + F + F";
		SQUARE_SNOWFLAKE.rules = "F = F + F - F - F F + F + F - F;";
		
		FRACTAL_TILES= new Preset();
		FRACTAL_TILES.i = "1";
		FRACTAL_TILES.j = "0";
		FRACTAL_TILES.n = "3";
		FRACTAL_TILES.constants = "+(RCCW 1.5707963268),\r\n"
				   					   + "-(RCW 1.5707963268)";
		FRACTAL_TILES.variables = "F (MVFWD 10, DRAWLINE)";
		FRACTAL_TILES.axiom = "F + F + F + F";
		FRACTAL_TILES.rules = "F = F F + F - F + F + F F;";
		
	}
}
