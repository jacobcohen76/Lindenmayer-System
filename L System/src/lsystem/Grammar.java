package lsystem;

import java.util.HashMap;
import java.util.LinkedList;

import lsystem.actions.Action;
import lsystem.parser.lexicalanalysis.Dictionary;

public class Grammar
{
	private HashMap<String, LinkedList<Action>> actionMap;
	private HashMap<String, LinkedList<String>> replacementMap;
	
	public Grammar(LinkedList<ProductionRule> RuleList, HashMap<String, LinkedList<Action>> actionMap, Dictionary<String> constants, Dictionary<String> variables)
	{
		this.actionMap = actionMap;
		replacementMap = new HashMap<String, LinkedList<String>>();
		
		for(ProductionRule rule : RuleList)
			if(replacementMap.containsKey(rule.variable))
				throw new Error("Semantic Error, you can only have each variable mapped to a single rule, "
						+ "the variable '" + rule.variable + "' is mapped to more than one rule.");
			else
				replacementMap.put(rule.variable, rule.sequence);
		
		for(String constant : constants)
		{
			LinkedList<String> replacement = new LinkedList<String>();
			replacement.add(constant);
			replacementMap.put(constant, replacement);
		}
	}
	
	public LinkedList<Action> getActions(String symbol)
	{
		return actionMap.get(symbol);
	}
	
	public LinkedList<String> getReplacement(LinkedList<String> axiom)
	{
		LinkedList<String> replacement = new LinkedList<String>();
		for(String symbol : axiom)
			replacement.addAll(getReplacement(symbol));
		return replacement;
	}
	
	private LinkedList<String> getReplacement(String symbol)
	{
		return replacementMap.get(symbol);
	}
}
