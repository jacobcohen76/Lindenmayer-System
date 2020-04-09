package lsystem.actions;

import lsystem.LSystem;

public class ScaleLine implements Action
{
	public double amount;
	
	public ScaleLine(double amount)
	{
		this.amount = amount;
	}

	@Override
	public void perform(LSystem system)
	{
		MoveForward.scale_factor *= amount;
	}
	
	public String toString()
	{
		return "SCALE " + amount;
	}
}
