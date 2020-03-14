package lsystem.parser.lexicalanalysis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Dictionary<T> implements Iterable<T>
{
	public HashSet<T> elements;
	
	public Dictionary(HashSet<T> elements)
	{
		this.elements = elements;
	}
	
	public Dictionary()
	{
		this(new HashSet<T>());
	}
	
	public Dictionary(T[] elements)
	{
		this();
		for(T e : elements)
			this.elements.add(e);
	}
	
	public boolean contains(T e)
	{
		return elements.contains(e);
	}
	
	public boolean add(T e)
	{
		return elements.add(e);
	}
	
	public boolean addAll(Collection<T> c)
	{
		return elements.addAll(c);
	}
	
	public boolean addAll(Dictionary<T> a)
	{
		return elements.addAll(a.elements);
	}
	
	public boolean remove(T e)
	{
		return elements.remove(e);
	}
	
	public boolean removeAll(Collection<T> c)
	{
		return elements.removeAll(c);
	}
	
	public boolean removeAll(Dictionary<T> a)
	{
		return elements.removeAll(a.elements);
	}
	
	public boolean isEmpty()
	{
		return elements.isEmpty();
	}
	
	public String toString()
	{
		String str = "{ ";
		for(T e : elements)
			str += e + ", ";
		if(str.length() > 3)
			str = str.substring(0, str.length() - 2);
		else
			str += " ";
		str += " }";
		return str;
	}
	
	public Object[] toArray()
	{
		return elements.toArray();
	}

	public Iterator<T> iterator()
	{
		return elements.iterator();
	}
}
