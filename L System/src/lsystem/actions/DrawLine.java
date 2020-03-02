package lsystem.actions;

import lsystem.LSystem;

public class DrawLine implements Action
{
	public void perform(LSystem system)
	{
		system.drawLine();
	}
	
	public String toString()
	{
		return "DRAWLINE";
	}
}
