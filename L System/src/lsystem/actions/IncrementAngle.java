package lsystem.actions;

import lsystem.LSystem;

public class IncrementAngle implements Action
{
	public double amount;
	
	public IncrementAngle(double amount)
	{
		this.amount = amount;
	}

	@Override
	public void perform(LSystem system)
	{
		RotateCCW.increment += amount;
		RotateCW.increment += amount;
	}
}
