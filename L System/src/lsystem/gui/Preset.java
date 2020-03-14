package lsystem.gui;

import java.io.Serializable;

public class Preset implements Serializable
{
	private static final long serialVersionUID = -8973315093181612777L;
	
	public String title, thickness, i, j, n, constants, variables, axiom, rules;
	
	public Preset(String title, String thickness, String i, String j, String n, String constants, String variables, String axiom, String rules)
	{
		this.title = title;
		this.thickness = thickness;
		this.i = i;
		this.j = j;
		this.n = n;
		this.constants = constants;
		this.variables = variables;
		this.axiom = axiom;
		this.rules = rules;
	}
	
	public Preset()
	{
		this("", "", "", "", "", "", "", "", "");
	}

}
