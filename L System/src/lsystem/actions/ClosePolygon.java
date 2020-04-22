package lsystem.actions;

import lsystem.LSystem;

public class ClosePolygon implements Action
{
	public void perform(LSystem system)
	{
		system.closePolygon();
	}
	
	public String toString()
	{
		return "CLOSEPOLY";
	}
}
