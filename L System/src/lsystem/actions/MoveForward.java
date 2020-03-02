package lsystem.actions;

import lsystem.LSystem;

public class MoveForward implements Action
{
	public double amount;
	
	public MoveForward(double amount)
	{
		this.amount = amount;
	}
	
	public void perform(LSystem system)
	{
		system.moveForward(amount);
	}
	
	public String toString()
	{
		return "MVFWD " + amount;
	}
}
