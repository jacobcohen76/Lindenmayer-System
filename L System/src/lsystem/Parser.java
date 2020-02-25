package lsystem;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import lsystem.actions.Action;
import lsystem.actions.DrawLine;
import lsystem.actions.MoveForward;
import lsystem.actions.PopDirection;
import lsystem.actions.PopPosition;
import lsystem.actions.PushDirection;
import lsystem.actions.PushPosition;
import lsystem.actions.RotateCCW;
import lsystem.actions.RotateCW;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;
import lsystem.parser.lexicalanalysis.Dictionary;
import lsystem.parser.lexicalanalysis.LexicalAnalyzer;
import lsystem.parser.lexicalanalysis.Token;
import lsystem.parser.lexicalanalysis.TokenType;

public class Parser
{
	private static final Dictionary<String> KEYWORDS = new Dictionary<String>();
	
	static
	{
		KEYWORDS.add("RCCW");
		KEYWORDS.add("RCW");
		KEYWORDS.add("MVFWD");
		KEYWORDS.add("POPDIR");
		KEYWORDS.add("DRAWLINE");
		KEYWORDS.add("PUSHDIR");
		KEYWORDS.add("PUSHPOS");
		KEYWORDS.add("POPPOS");
	}
	
	private LexicalAnalyzer lexer;
	private Dictionary<String> constants;
	private Dictionary<String> variables;
	private Dictionary<String> keyWords;
	
	private String rulesInput;
	private String constantsInput;
	private String variablesInput;
	private String axiomInput;
	
	private HashMap<String, LinkedList<Action>> actionMap;
	
	public Parser(String rulesInput, String constantsInput, String variablesInput, String axiomInput)
	{
		this.rulesInput = rulesInput;
		this.constantsInput = constantsInput;
		this.variablesInput = variablesInput;
		this.axiomInput = axiomInput;
		this.keyWords = KEYWORDS;
		constants = new Dictionary<String>();
		variables = new Dictionary<String>();
		actionMap = new HashMap<String, LinkedList<Action>>();
	}
	
	//axiom = constant axiom
	//axiom = variable axiom
	//axiom = constant
	//axiom = variable
	private LinkedList<String> parseAxiom()
	{
		Token t = lexer.getToken();
		LinkedList<String> axiom = new LinkedList<String>();
		if(t.type == TokenType.CONSTANT || t.type == TokenType.VARIABLE)
		{
			axiom.add(t.lexeme);
			t = lexer.peek();
			if(t.type == TokenType.CONSTANT || t.type == TokenType.VARIABLE)
				axiom.addAll(parseAxiom());
		}
		else
			throw new Error("Error, expected token of type 'CONSTANT', or 'VARIABLE', got '" + t.type + "' with lexeme of '" + t.lexeme + "'.");
		return axiom;
	}
	
	//constants = constant COMMA constants
	//constants = constant
	//constants = EOI
	private void parseConstants()
	{
		if(lexer.peek().type != TokenType.EOI)
		{
			parseConstant();
			if(lexer.peek().type == TokenType.COMMA)
			{
				lexer.expect(TokenType.COMMA);
				parseConstants();
			}
		}
	}
	
	//constant = ID LPAREN ActionList RPAREN
	//constant = ID
	private void parseConstant()
	{
		Token t = lexer.expect(TokenType.ID);
		LinkedList<Action> actions = new LinkedList<Action>();
		if(lexer.peek().type == TokenType.LPAREN)
		{
			lexer.expect(TokenType.LPAREN);
			actions.addAll(parseActionList());
			lexer.expect(TokenType.RPAREN);
		}
		if(constants.contains(t.lexeme))
			throw new Error("Error, the constant '" + t.lexeme + "' is declared more than once.");
		constants.add(t.lexeme);
		actionMap.put(t.lexeme, actions);
	}
	
	//variables = variable COMMA variables
	//variables = variable
	//variables = EOI
	private void parseVariables()
	{
		if(lexer.peek().type != TokenType.EOI)
		{
			parseVariable();
			if(lexer.peek().type == TokenType.COMMA)
			{
				lexer.expect(TokenType.COMMA);
				parseVariables();
			}
		}
	}
	
	//variable = ID LPAREN ActionList RPAREN
	//variable = ID
	private void parseVariable()
	{
		Token t = lexer.expect(TokenType.ID);
		LinkedList<Action> actions = new LinkedList<Action>();
		if(lexer.peek().type == TokenType.LPAREN)
		{
			lexer.expect(TokenType.LPAREN);
			actions.addAll(parseActionList());
			lexer.expect(TokenType.RPAREN);
		}
		if(variables.contains(t.lexeme))
			throw new Error("Error, the variable '" + t.lexeme + "' is declared more than once.");
		variables.add(t.lexeme);
		actionMap.put(t.lexeme, actions);
	}
	
	//ActionList = Action, ActionList
	//ActionList = Action
	private LinkedList<Action> parseActionList()
	{
		LinkedList<Action> ActionList = new LinkedList<Action>();
		ActionList.add(parseAction());
		if(lexer.peek().type == TokenType.COMMA)
		{
			lexer.getToken();
			ActionList.addAll(parseActionList());
		}
		return ActionList;
	}
	
	//Action = KEYWORD NUM
	//Action = KEYWORD
	private Action parseAction()
	{
		Token t = lexer.expect(TokenType.KEYWORD);
		switch(t.lexeme)
		{
		case "RCCW":
			return new RotateCCW(parseNum());
		case "RCW":
			return new RotateCW(parseNum());
		case "MVFWD":
			return new MoveForward(parseNum());
		case "POPDIR":
			return new PopDirection();
		case "DRAWLINE":
			return new DrawLine();
		case "POPPOS":
			return new PopPosition();
		case "PUSHDIR":
			return new PushDirection();
		case "PUSHPOS":
			return new PushPosition();
		}
		throw new Error("Error, invalid action keyword at '" + t.lexeme + "', expected a valid action keyword.");
	}
	
	private double parseNum()
	{
		Token t = lexer.expect(TokenType.NUM);
		return Double.valueOf(t.lexeme);
	}
	
	public LSystem parseLSystem(int n, Point origin, Vector initial)
	{
		lexer = new LexicalAnalyzer(constantsInput, keyWords);
		parseConstants();
		lexer = new LexicalAnalyzer(variablesInput, keyWords);
		parseVariables();
		lexer = new LexicalAnalyzer(rulesInput, constants, variables, keyWords);
		Grammar grammar = parseGrammar();
		lexer = new LexicalAnalyzer(axiomInput, constants, variables, keyWords);
		LinkedList<String> axiom = parseAxiom();	
		
		Set<String> keySet = actionMap.keySet();
		for(String key : keySet)
			System.out.println(key + "\t\t" + actionMap.get(key));
		
		return new LSystem(grammar, axiom, n, origin, initial);
	}
	
	//Grammar = RuleList EOI
	private Grammar parseGrammar()
	{		
		LinkedList<ProductionRule> RuleList = parseRuleList();
		lexer.expect(TokenType.EOI);
		return new Grammar(RuleList, actionMap, constants, variables);
	}
	
	//RuleList = ProductionRule RuleList
	//RuleList = ProductionRule
	private LinkedList<ProductionRule> parseRuleList()
	{
		LinkedList<ProductionRule> RuleList = new LinkedList<ProductionRule>();
		RuleList.add(parseProductionRule());
		Token t = lexer.peek();
		if(t.type == TokenType.CONSTANT || t.type == TokenType.VARIABLE)
			RuleList.addAll(parseRuleList());
		return RuleList;
	}
	
	//ProductionRule = VARIABLE EQUALS RightHandSide SEMICOLON
	private ProductionRule parseProductionRule()
	{
		Token t = lexer.expect(TokenType.VARIABLE);
		ProductionRule rule = new ProductionRule();
		rule.variable = t.lexeme;
		lexer.expect(TokenType.EQUALS);
		rule.sequence = parseRightHandSide();
		lexer.expect(TokenType.SEMICOLON);
		return rule;
	}
	
	//RightHandSide = CONSTANT RightHandSide
	//RightHandSide = VARIABLE RightHandSide
	//RightHandSide = CONSTANT
	//RightHandSide = VARIABLE
	private LinkedList<String> parseRightHandSide()
	{
		Token t = lexer.getToken();
		LinkedList<String> RHS = new LinkedList<String>();
		if(t.type == TokenType.CONSTANT || t.type == TokenType.VARIABLE)
		{
			RHS.add(t.lexeme);
			t = lexer.peek();
			if(t.type == TokenType.CONSTANT || t.type == TokenType.VARIABLE)
				RHS.addAll(parseRightHandSide());
		}
		else
			throw new Error("Syntax Error");
		return RHS;
	}
}
