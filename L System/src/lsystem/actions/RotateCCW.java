package lsystem.actions;

import lsystem.LSystem;

public class RotateCCW implements Action
{
	public double radians;
	
	public RotateCCW(double radians)
	{
		this.radians = radians;
	}
	
	public void perform(LSystem system)
	{
		system.rotateCCW(radians);
	}
}
