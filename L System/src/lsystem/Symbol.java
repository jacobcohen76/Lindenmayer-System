package lsystem;

public class Symbol
{
	private String lexeme;
	public int generation = -1;
	
	public Symbol(String lexeme)
	{
		this.lexeme = lexeme;
	}
	
	public int hashCode()
	{
		return lexeme.hashCode();
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof Symbol)
		{
			Symbol objsymbol = (Symbol) obj;
			return lexeme.equals(objsymbol.lexeme);
		}
		else 
		{
			return false;
		}
	}
	
	public String toString()
	{
		return lexeme;
	}
}
