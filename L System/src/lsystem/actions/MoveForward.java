package lsystem.actions;

import lsystem.LSystem;

public class MoveForward implements Action
{
	public static double scale_factor = 1.0;
	
	public double amount;
	
	public MoveForward(double amount)
	{
		this.amount = amount;
	}
	
	public void perform(LSystem system)
	{
		system.moveForward(amount * scale_factor);
	}
	
	public String toString()
	{
		return "MVFWD " + amount;
	}
}
