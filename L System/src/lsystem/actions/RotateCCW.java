package lsystem.actions;

import lsystem.LSystem;

public class RotateCCW implements Action
{
	public static double increment = 0.0;
	public static double direction = 1.0;
	public static double scale_factor = 1.0;
	
	public double radians;
	public double variation;
	
	public RotateCCW(double radians)
	{
		this.radians = radians;
		this.variation = 0.0;
	}
	
	public void perform(LSystem system)
	{
		system.rotateCCW((radians + increment) * direction * scale_factor + Randomizer.getVariation((radians + increment) * scale_factor, variation));
	}
	
	public String toString()
	{
		return "RCCW " + radians + (variation == 0.0 ? "" : variation + "%");
	}
}
