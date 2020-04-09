package lsystem.actions;

import lsystem.LSystem;

public class ScaleAngle implements Action
{
	public double amount;
	
	public ScaleAngle(double amount)
	{
		this.amount = amount;
	}

	@Override
	public void perform(LSystem system)
	{
		RotateCCW.scale_factor *= amount;
		RotateCW.scale_factor *= amount;
	}

}
