package lsystem;
import java.util.HashMap;
import java.util.LinkedList;

import lsystem.actions.Action;
import lsystem.actions.AddPoint;
import lsystem.actions.ClosePolygon;
import lsystem.actions.DecrementAngle;
import lsystem.actions.DrawLine;
import lsystem.actions.IncrementAngle;
import lsystem.actions.IncrementThickness;
import lsystem.actions.MoveForward;
import lsystem.actions.OpenPolygon;
import lsystem.actions.PopDirection;
import lsystem.actions.PopPosition;
import lsystem.actions.PushDirection;
import lsystem.actions.PushPosition;
import lsystem.actions.RotateCCW;
import lsystem.actions.RotateCW;
import lsystem.actions.ScaleLine;
import lsystem.actions.ScaleThickness;
import lsystem.actions.SwapAngles;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;
import lsystem.parser.lexicalanalysis.Dictionary;
import lsystem.parser.lexicalanalysis.LexicalAnalyzer;
import lsystem.parser.lexicalanalysis.Token;
import lsystem.parser.lexicalanalysis.TokenType;

public class Parser
{
	private static final Dictionary<String> KEYWORDS = new Dictionary<String>();
	public static boolean DEGREEMODE = false;
	
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
		KEYWORDS.add("INCANGLE");
		KEYWORDS.add("DECANGLE");
		KEYWORDS.add("SCALE");
		KEYWORDS.add("SWAP");
		KEYWORDS.add("INCTHICKNESS");
		KEYWORDS.add("SCLTHICK");
		KEYWORDS.add("OPENPOLY");
		KEYWORDS.add("CLOSEPOLY");
		KEYWORDS.add("ADDPOINT");
	}
	
	private LexicalAnalyzer lexer;
	private Dictionary<String> constants;
	private Dictionary<String> variables;
	private Dictionary<String> keyWords;
	
	private String rulesInput;
	private String constantsInput;
	private String variablesInput;
	private String axiomInput;
	
	private HashMap<Symbol, LinkedList<Action>> actionMap;
	
	private String currentlyParsing;
	
	public Parser(String rulesInput, String constantsInput, String variablesInput, String axiomInput)
	{
		this.rulesInput = rulesInput;
		this.constantsInput = constantsInput;
		this.variablesInput = variablesInput;
		this.axiomInput = axiomInput;
		this.keyWords = KEYWORDS;
		constants = new Dictionary<String>();
		variables = new Dictionary<String>();
		actionMap = new HashMap<Symbol, LinkedList<Action>>();
	}
	
	//axiom = constant axiom
	//axiom = variable axiom
	//axiom = constant
	//axiom = variable
	private LinkedList<Symbol> parseAxiom()
	{
		Token t = lexer.getToken();
		LinkedList<Symbol> axiom = new LinkedList<Symbol>();
		if(t.type == TokenType.CONSTANT || t.type == TokenType.VARIABLE)
		{
			axiom.add(new Symbol(t.lexeme));
			t = lexer.peek();
			if(t.type == TokenType.CONSTANT || t.type == TokenType.VARIABLE)
				axiom.addAll(parseAxiom());
		}
		else
			throw new Error("Error, expected token of type 'CONSTANT',"
					+ " or 'VARIABLE', got '" + t.type + "' with lexeme of '" + t.lexeme + "'.");
		return axiom;
	}
	
	//constants = constant COMMA constants
	//constants = constant
	//constants = 
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
		actionMap.put(new Symbol(t.lexeme), actions);
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
			throw new Error("Error, the variable"
					+ " '" + t.lexeme + "' is declared more than once.");
		variables.add(t.lexeme);
		actionMap.put(new Symbol(t.lexeme), actions);
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
	
	private double angle(double num)
	{
		return DEGREEMODE ? (num * Math.PI / 180.0) : num;
	}
	
	//Action = KEYWORD NUM NUM PERCENT
	//Action = KEYWORD NUM
	//Action = KEYWORD
	private Action parseAction()
	{
		Token t = lexer.expect(TokenType.KEYWORD);
		switch(t.lexeme)
		{
		case "RCCW":
			RotateCCW rccw = new RotateCCW(angle(parseNum()));
			if(lexer.peek().type == TokenType.NUM)
			{
				rccw.variation = parseNum() / 100.0;
				lexer.expect(TokenType.PERCENT);
			}
			return rccw;
		case "RCW":
			RotateCW rcw = new RotateCW(angle(parseNum()));
			if(lexer.peek().type == TokenType.NUM)
			{
				rcw.variation = parseNum() / 100.0;
				lexer.expect(TokenType.PERCENT);
			}
			return rcw;
		case "INCANGLE":
			return new IncrementAngle(angle(parseNum()));
		case "DECANGLE":
			return new DecrementAngle(angle(parseNum()));
		case "MVFWD":
			MoveForward mvfwd = new MoveForward(parseNum());
			if(lexer.peek().type == TokenType.NUM)
			{
				mvfwd.variation = parseNum() / 100.0;
				lexer.expect(TokenType.PERCENT);
			}
			return mvfwd;
		case "SCALE":
			return new ScaleLine(parseNum());
		case "SWAP":
			return new SwapAngles();
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
		case "INCTHICKNESS":
			return new IncrementThickness((float) parseNum());
		case "SCLTHICK":
			return new ScaleThickness((float) parseNum());
		case "OPENPOLY":
			return new OpenPolygon();
		case "CLOSEPOLY":
			return new ClosePolygon();
		case "ADDPOINT":
			return new AddPoint();
		}
		throw new Error("Error, invalid action keyword at '" + t.lexeme + "', expected a valid action keyword.");
	}
	
	private double parseNum()
	{
		
		Token t = lexer.getToken();
		if(t.lexeme.length() > 0 && t.lexeme.charAt(0) == '-')
		{
			
		}
		else if(t.type != TokenType.NUM)
		{
			lexer.ungetToken(t);
			t = lexer.expect(TokenType.NUM);
		}
		return Double.valueOf(t.lexeme);
	}
	
	public LSystem parseLSystem(int n, Point origin, Vector initial)
	{
		if(constantsInput.equals("") && variablesInput.equals(""))
		{
			throw new Error("Error, no constants or variables are declared in this L-System. Declare variables and constants in the corresponding input fields."
					+ "\n\nFor examples of how to do this, click File -> Load Presets, and then select one of the predefined L-Systems from this list."
					+ "\n\nThe predefined L-Systems in this list come with"
					+ " an example of properly defined syntax, and show how to use 'Actions' to produce meaningful and fascinating images as outputs.");
		}
		
		try
		{
			currentlyParsing = "Constants";
			lexer = new LexicalAnalyzer(constantsInput, keyWords);
			parseConstants();
			
			currentlyParsing = "Variables";
			lexer = new LexicalAnalyzer(variablesInput, keyWords);
			parseVariables();
			
			currentlyParsing = "Rules";
			lexer = new LexicalAnalyzer(rulesInput, constants, variables, keyWords);
			Grammar grammar = parseGrammar();
			
			if(grammar.definedAll(variables) == false)
				throw new Error("Error, not all variables were defined. Define the replacement rule for each non-terminal symbol.");
			
			currentlyParsing = "Axiom";
			lexer = new LexicalAnalyzer(axiomInput, constants, variables, keyWords);
			LinkedList<Symbol> axiom = parseAxiom();
			
			return new LSystem(grammar, axiom, n, origin, initial);
		}
		catch(Exception ex)
		{
			throw new Error("An Error occurred in the " + currentlyParsing + " input field.\n\n" + ex.getMessage());
		}
		catch(Error e)
		{
			throw new Error("An Error occurred in the " + currentlyParsing + " input field.\n\n" + e.getMessage());
		}
	}
	
	//Grammar = RuleList
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
		if(lexer.peek().type != TokenType.EOI)
		{
			RuleList.add(parseProductionRule());
			Token t = lexer.peek();
			if(t.type == TokenType.CONSTANT || t.type == TokenType.VARIABLE)
				RuleList.addAll(parseRuleList());
		}
		return RuleList;
	}
	
	//ProductionRule = VARIABLE EQUALS RightHandSide SEMICOLON
	private ProductionRule parseProductionRule()
	{
		Token t = lexer.expect(TokenType.VARIABLE);
		ProductionRule rule = new ProductionRule();
		rule.variable = new Symbol(t.lexeme);
		lexer.expect(TokenType.EQUALS);
		rule.sequence = parseRightHandSide();
		lexer.expect(TokenType.SEMICOLON);
		return rule;
	}
	
	//RightHandSide = CONSTANT RightHandSide
	//RightHandSide = VARIABLE RightHandSide
	//RightHandSide = CONSTANT
	//RightHandSide = VARIABLE
	private LinkedList<Symbol> parseRightHandSide()
	{
		Token t = lexer.getToken();
		LinkedList<Symbol> RHS = new LinkedList<Symbol>();
		if(t.type == TokenType.SEMICOLON)
		{
			lexer.ungetToken(t);
		}
		else if(t.type == TokenType.CONSTANT || t.type == TokenType.VARIABLE)
		{
			RHS.add(new Symbol(t.lexeme));
			t = lexer.peek();
			if(t.type == TokenType.CONSTANT || t.type == TokenType.VARIABLE)
				RHS.addAll(parseRightHandSide());
		}
		else
			throw new Error("expected "
					+ "a token of type CONSTANT, or VARIABLE, instead got a token of type " + t.type
					+ " with lexeme of '" + t.lexeme + "' at line " + t.line_no + ".");
		return RHS;
	}
}
