package lsystem.actions;

import lsystem.LSystem;

public class IncrementThickness implements Action
{
	public float amount;
	
	public IncrementThickness(float amount)
	{
		this.amount = amount;
	}

	@Override
	public void perform(LSystem system)
	{
		system.thickness += amount;
	}
}
