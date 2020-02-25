package lsystem.parser.lexicalanalysis;

public class Token
{
	public int line_no;
	public TokenType type;
	public String lexeme;
	
	public Token(int line_no, TokenType type, String lexeme)
	{
		this.line_no = line_no;
		this.type = type;
		this.lexeme = lexeme;
	}
	
	public Token()
	{
		this(-1, TokenType.DEFAULT, "");
	}
	
	public String toString()
	{
		return line_no + " - " + type + " - " + lexeme;
	}
}
