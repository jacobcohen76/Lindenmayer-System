package lsystem;

import java.util.LinkedList;

public class ProductionRule
{
	public Symbol variable;
	public LinkedList<Symbol> sequence;
	
	public ProductionRule(Symbol variable, LinkedList<Symbol> sequence)
	{
		this.variable = variable;
		this.sequence = sequence;
	}
	
	public ProductionRule()
	{
		this(null, null);
	}
	
	private String getSequenceString()
	{
		String str = "";
		for(Symbol s : sequence)
			str += s + " ";
		if(str.length() > 1)
			str = str.substring(0, str.length() - 1);
		return str;
	}
	
	public String toString()
	{
		return variable + " = " + getSequenceString();
	}
}
