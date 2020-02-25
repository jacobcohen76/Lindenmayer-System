package lsystem;

import java.util.LinkedList;

public class ProductionRule
{
	public String variable;
	public LinkedList<String> sequence;
	
	public ProductionRule(String variable, LinkedList<String> sequence)
	{
		this.variable = variable;
		this.sequence = sequence;
	}
	
	public ProductionRule()
	{
		this("", null);
	}
	
	private String getSequenceString()
	{
		String str = "";
		for(String s : sequence)
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
