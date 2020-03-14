package lsystem.actions;

import lsystem.LSystem;

public class DecrementAngle implements Action
{
	public double amount;
	
	public DecrementAngle(double amount)
	{
		this.amount = amount;
	}

	@Override
	public void perform(LSystem system)
	{
		RotateCCW.increment -= amount;
		RotateCW.increment -= amount;
	}
}
