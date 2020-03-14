package lsystem.actions;

import lsystem.LSystem;

public class RotateCCW implements Action
{
	public static double increment = 0.0;
	public static double direction = 1.0;
	
	public double radians;
	
	public RotateCCW(double radians)
	{
		this.radians = radians;
	}
	
	public void perform(LSystem system)
	{
		system.rotateCCW((radians + increment) * direction);
	}
	
	public String toString()
	{
		return "RCCW " + radians;
	}
}
