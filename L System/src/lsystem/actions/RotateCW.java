package lsystem.actions;

import lsystem.LSystem;

public class RotateCW implements Action
{
	public static double increment = 0.0;
	public static double direction = 1.0;
	
	public double radians;
	
	public RotateCW(double radians)
	{
		this.radians = radians;
	}
	
	public void perform(LSystem system)
	{
		system.rotateCW((radians + increment) * direction);
	}
	
	public String toString()
	{
		return "RCW " + radians;
	}
}
