package lsystem.actions;

import lsystem.LSystem;

public class RotateCW implements Action
{
	public double radians;
	
	public RotateCW(double radians)
	{
		this.radians = radians;
	}
	
	public void perform(LSystem system)
	{
		system.rotateCW(radians);
	}
	
	public String toString()
	{
		return "RCW " + radians;
	}
}
