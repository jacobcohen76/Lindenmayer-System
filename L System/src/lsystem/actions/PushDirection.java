package lsystem.actions;

import lsystem.LSystem;

public class PushDirection implements Action
{
	public void perform(LSystem system)
	{
		system.pushDirection();
	}
	
	public String toString()
	{
		return "PUSHDIR";
	}
}
