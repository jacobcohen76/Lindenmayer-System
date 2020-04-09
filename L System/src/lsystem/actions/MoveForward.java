package lsystem.actions;


import lsystem.LSystem;

public class MoveForward implements Action
{
	public static double scale_factor = 1.0;
	
	public double amount;
	public double variation;
	
	public MoveForward(double amount)
	{
		this.amount = amount;
		this.variation = 0.0;
	}
	
	public void perform(LSystem system)
	{
		system.moveForward(amount * scale_factor + Randomizer.getVariation(amount * scale_factor, variation));
	}
	
	public String toString()
	{
		return "MVFWD " + amount + (variation == 0.0 ? "" : variation + "%");
	}
}
