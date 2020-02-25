package lsystem.actions;

import lsystem.LSystem;

public class PopPosition implements Action
{
	public void perform(LSystem system)
	{
		system.popPosition();
	}
}
