package lsystem.actions;

import lsystem.LSystem;

public class PopDirection implements Action
{
	public void perform(LSystem system)
	{
		system.popDirection();
	}
}
