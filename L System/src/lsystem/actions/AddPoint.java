package lsystem.actions;

import lsystem.LSystem;

public class AddPoint implements Action
{
	public void perform(LSystem system)
	{
		system.addPoint();
	}
	
	public String toString()
	{
		return "ADDPOINT";
	}
}
