package lsystem.parser.lexicalanalysis;

import java.util.LinkedList;

public class LexicalAnalyzer
{
	private LinkedList<Token> tokenStream;
	
	public LexicalAnalyzer(String input, Dictionary<String> constants, Dictionary<String> variables, Dictionary<String> keyWords)
	{
		tokenStream = Tokenizer.tokenize(input, constants, variables, keyWords);
	}
	
	public LexicalAnalyzer(String input, Dictionary<String> keyWords)
	{
		tokenStream = Tokenizer.tokenize(input, keyWords);
	}
	
	public Token getToken()
	{
		return tokenStream.poll();
	}
	
	public void ungetToken(Token t)
	{
		tokenStream.push(t);
	}
	
	public Token expect(TokenType type)
	{
		Token t = getToken();
		if(t.type != type)
			throw ExpectedError(t, type);
		return t;
	}
	
	public Token peek()
	{
		Token t = getToken();
		ungetToken(t);
		return t;
	}
	
	private Error ExpectedError(Token t, TokenType type)
	{
		String message = "Syntax Error, expected a Token of type '" + type + "', instead got"
				+ " a Token of type '" + t.type + "' with lexeme of '" + t.lexeme + "' at line " + t.line_no;
		return new Error(message);
	}
}
