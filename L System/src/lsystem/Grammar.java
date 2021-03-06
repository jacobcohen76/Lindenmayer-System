package lsystem;

import java.util.HashMap;
import java.util.LinkedList;

import lsystem.actions.Action;
import lsystem.parser.lexicalanalysis.Dictionary;

public class Grammar
{
	public HashMap<Symbol, LinkedList<Action>> actionMap;
	private HashMap<Symbol, LinkedList<Symbol>> replacementMap;
	public Dictionary<String> constants;
	public Dictionary<String> variables;
	
	public Grammar(LinkedList<ProductionRule> RuleList, HashMap<Symbol, LinkedList<Action>> actionMap, Dictionary<String> constants, Dictionary<String> variables)
	{
		this.actionMap = actionMap;
		replacementMap = new HashMap<Symbol, LinkedList<Symbol>>();
		
		this.constants = constants;
		this.variables = variables;
		
		for(ProductionRule rule : RuleList)
			if(replacementMap.containsKey(rule.variable))
				throw new Error("Semantic Error, you can only have each variable mapped to a single rule, "
						+ "the variable '" + rule.variable + "' is mapped to more than one rule.");
			else
				replacementMap.put(rule.variable, rule.sequence);
		
		for(String constant : constants)
		{
			LinkedList<Symbol> replacement = new LinkedList<Symbol>();
			replacement.add(new Symbol(constant));
			replacementMap.put(replacement.peek(), replacement);
		}
	}
	
	public LinkedList<Action> getActions(Symbol symbol)
	{
		return actionMap.get(symbol);
	}
	
	public LinkedList<Symbol> getReplacement(LinkedList<Symbol> axiom)
	{
		LinkedList<Symbol> replacement = new LinkedList<Symbol>();
		for(Symbol symbol : axiom)
			replacement.addAll(getReplacement(symbol));
		return replacement;
	}
	
	public LinkedList<Symbol> getReplacement(Symbol symbol)
	{
		return replacementMap.get(symbol);
	}
	
	public boolean definedAll(Dictionary<String> variables)
	{
		for(String variable : variables)
			if(replacementMap.containsKey(new Symbol(variable)) == false)
				return false;
		return true;
	}
}
