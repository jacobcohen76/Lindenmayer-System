package lsystem.actions;

import lsystem.LSystem;

public class PushPosition implements Action
{
	public void perform(LSystem system)
	{
		system.pushPosition();
	}
}
