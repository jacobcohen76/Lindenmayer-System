package lsystem.parser.lexicalanalysis;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Tokenizer
{
	private int line_no;
	private String input;
	private Dictionary<Character> alphabet;
	private Dictionary<String> constants;
	private Dictionary<String> variables;
	private Dictionary<String> keyWords;
	
	public static LinkedList<Token> tokenize(String input, Dictionary<String> constants, Dictionary<String> variables, Dictionary<String> keyWords)
	{
		Tokenizer tokenizer = new Tokenizer(input, constants, variables, keyWords);
		return tokenizer.tokenize();
	}
	
	public static LinkedList<Token> tokenize(String input, Dictionary<String> keyWords)
	{
		Tokenizer tokenizer = new Tokenizer(input, keyWords);
		return tokenizer.tokenize();
	}
	
	private Tokenizer(String input, Dictionary<String> keyWords)
	{
		this(input, new Dictionary<String>(), new Dictionary<String>(), keyWords);
	}
	
	private Tokenizer(String input, Dictionary<String> constants, Dictionary<String> variables, Dictionary<String> keyWords)
	{
		this.line_no = 0;
		this.input = input + "$";
		
		this.constants = constants;
		this.variables = variables;
		this.keyWords = keyWords;
		
		alphabet = new Dictionary<Character>();
		for(String s : constants)
			for(int i = 0; i < s.length(); i++)
				alphabet.add(s.charAt(i));
		for(String s : variables)
			for(int i = 0; i < s.length(); i++)
				alphabet.add(s.charAt(i));
		
		if(alphabet.contains('$'))
			throw new InvalidParameterException("'$' cannot be a constant nor a character contained in a variable.");
		else if(alphabet.contains('='))
			throw new InvalidParameterException("'=' cannot be a constant nor a character contained in a variable.");
		else if(alphabet.contains(' ') || alphabet.contains('\t') || alphabet.contains('\n'))
			throw new InvalidParameterException("White space cannot be a constant nor a character contained in a variable.");
		
		checkForSubstrings();
		
		alphabet.add('$');
		alphabet.add('=');
		alphabet.add(',');
		alphabet.add(';');
		alphabet.add('(');
		alphabet.add(')');
		alphabet.add(' ');
		alphabet.add('\t');
		alphabet.add('\n');
		
		if(constants.isEmpty() == false || variables.isEmpty() == false)
			for(int i = 0; i < input.length(); i++)
				if(alphabet.contains(input.charAt(i)) == false)
					throw new InvalidParameterException("Unexpected Character at '" + input.charAt(i) + "'.");
	}
	
	private void checkForSubstrings()
	{
		Object[] vars = variables.toArray();
		Object[] cons = constants.toArray();
		Object[] append = new Object[vars.length + cons.length];
		for(int i = 0; i < vars.length; i++)
			append[i] = vars[i];
		for(int i = 0; i < cons.length; i++)
			append[i + vars.length] = cons[i];
		vars = append;
		
//		cons = keyWords.toArray();
//		append = new Object[vars.length + cons.length];
//		for(int i = 0; i < vars.length; i++)
//			append[i] = vars[i];
//		for(int i = 0; i < cons.length; i++)
//			append[i + vars.length] = cons[i];
//		vars = append;
//		
		ArrayList<String> substrPairs = new ArrayList<String>();
		
		for(int i = 0; i < vars.length; i++)
			for(int j = i + 1; j < vars.length; j++)
				if(((String) vars[i]).contains((CharSequence) vars[j]))
					substrPairs.add(vars[i] + "\t" + vars[j]);
				else if(((String) vars[j]).contains((CharSequence) vars[i]))
					substrPairs.add(vars[j] + "\t" + vars[i]);
		
		if(substrPairs.size() > 0)
		{
			String message = "\n"
					+ "Error, for each variable, none can be a substring of another variable.\n"
					+ "The following pairs are variables that have are substrings, and are preventing\n"
					+ "your program from compiling.\n";
			for(String pair : substrPairs)
				message += pair + "\n";
			throw new Error(message);
		}
	}
	
	private LinkedList<Token> tokenize()
	{
		LinkedList<Token> tokenStream = new LinkedList<Token>();
		
		while(input.length() > 0)
		{
			skipWhiteSpace();
			tokenStream.add(getToken());
		}
		
		return tokenStream;
	}
	
	private void skipWhiteSpace()
	{
		int i = 0;
		
		while(i < input.length() && isWhiteSpace(input.charAt(i)))
		{
			if(isNewLine(input.charAt(i)))
				line_no += 1;
			i++;
		}
		
		input = input.substring(i);
	}
	
	private Token getToken()
	{
		String lexeme = "";
		int i = 0;
		
		while(isDelimeter(input.charAt(i)) == false && keyWords.contains(lexeme) == false && variables.contains(lexeme) == false && constants.contains(lexeme) == false)
		{
			lexeme += input.charAt(i);
			i++;
		}
		
		if(lexeme.equals("") && isDelimeter(input.charAt(i)))
		{
			lexeme += input.charAt(i);
			i++;
		}
		
		input = input.substring(i);
		return new Token(line_no, getTokenType(lexeme), lexeme);
	}
	
	private TokenType getTokenType(String lexeme)
	{
		TokenType type = null;
		if(constants.contains(lexeme))
			type = TokenType.CONSTANT;
		else if(variables.contains(lexeme))
			type = TokenType.VARIABLE;
		else if(keyWords.contains(lexeme))
			type = TokenType.KEYWORD;
		else if(lexeme.equals("="))
			type = TokenType.EQUALS;
		else if(lexeme.equals("$"))
			type = TokenType.EOI;
		else if(lexeme.equals(","))
			type = TokenType.COMMA;
		else if(lexeme.equals("("))
			type = TokenType.LPAREN;
		else if(lexeme.equals(")"))
			type = TokenType.RPAREN;
		else if(lexeme.equals(";"))
			type = TokenType.SEMICOLON;
		else if(isNumeric(lexeme))
			type = TokenType.NUM;
		else if(isID(lexeme))
			type = TokenType.ID;
		else
			throw new InvalidParameterException("Unidentifiable Token at '" + lexeme + "'.");
		return type;
	}
	
	private boolean isNumeric(String lexeme)
	{
		boolean isNumber = lexeme.length() > 0;
		for(int i = 0; i < lexeme.length(); i++)
			isNumber &= lexeme.charAt(i) <= '9' && lexeme.charAt(i) >= '0' || lexeme.charAt(i) == '.';
		isNumber &= lexeme.indexOf('.') == lexeme.lastIndexOf('.');
		return isNumber;
	}
	
	private boolean isID(String lexeme)
	{
		boolean isID = lexeme.length() > 0;
		isID &= constants.isEmpty();
		isID &= variables.isEmpty();
		return isID;
	}
	
	private boolean isDelimeter(char c)
	{
		return isEquals(c) || isWhiteSpace(c) || isEOI(c) || isComma(c) || isSemiColon(c) || c == '(' || c == ')';
	}
	
	private boolean isSemiColon(char c)
	{
		return c == ';';
	}
	
	private boolean isComma(char c)
	{
		return c == ',';
	}
	
	private boolean isEOI(char c)
	{
		return c == '$';
	}
	
	private boolean isEquals(char c)
	{
		return c == '=';
	}
	
	private boolean isWhiteSpace(char c)
	{
		return c == ' ' || c == '\t' || c == '\n';
	}
	
	private boolean isNewLine(char c)
	{
		return c == '\n';
	}
}
