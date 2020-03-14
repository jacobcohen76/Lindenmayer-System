package lsystem.actions;

import lsystem.LSystem;

public class SwapAngles implements Action
{
	@Override
	public void perform(LSystem system)
	{
		RotateCCW.direction *= -1;
		RotateCW.direction *= -1;
	}
}
