package lsystem.actions;

import java.util.Random;

public class Randomizer
{
	private static final Random rand = new Random(System.currentTimeMillis());
	
	public static double getVariation(double value, double percent)
	{
		return rand.nextDouble() * percent * value;
	}
}
