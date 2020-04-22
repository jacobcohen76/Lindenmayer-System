package lsystem.actions;

import lsystem.LSystem;

public class OpenPolygon implements Action
{
	public void perform(LSystem system)
	{
		system.openPolygon();
	}
	
	public String toString()
	{
		return "OPENPOLY";
	}
}
