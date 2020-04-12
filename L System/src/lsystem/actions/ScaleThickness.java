package lsystem.actions;

import lsystem.LSystem;

public class ScaleThickness implements Action
{
	public float amount;
	
	public ScaleThickness(float amount)
	{
		this.amount = amount;
	}

	@Override
	public void perform(LSystem system)
	{
		system.thickness *= amount;
	}
}

