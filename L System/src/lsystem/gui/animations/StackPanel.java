package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JPanel;

public class StackPanel<T> extends JPanel
{
	private static final long serialVersionUID = 4648864358756471134L;
	
	private ArrayList<T> dataStack;
	private Font font;
	private Color textColor;
	private Color background;
	private Color borderColor;
	private int n;
	
	public StackPanel(Collection<T> stack, int n, Font font, Color textColor, Color borderColor, Color background)
	{
		this.n = n;
		this.font = font;
		this.textColor = textColor;
		this.background = background;
		this.borderColor = borderColor;
		dataStack = new ArrayList<T>();
		dataStack.addAll(stack);
	}
	
	public StackPanel(int n, Font font, Color textColor, Color borderColor, Color background)
	{
		this(new ArrayList<T>(), n, font, textColor, borderColor, background);
	}
	
	public void add(T element)
	{
		dataStack.add(element);		
		repaint();
	}
	
	public void add(Collection<T> collection)
	{
		dataStack.addAll(collection);
		repaint();
	}
	
	public T pop()
	{
		if(dataStack.size() != 0)
		{
			T popped = dataStack.remove(dataStack.size() - 1);
			repaint();
			return popped;
		}
		else
			return null;
	}
	
	public void paint(Graphics g)
	{
		drawBackground(g, background);
		
		Rectangle rect;
		
		int depth = dataStack.size() % n + 1;
		int i = 0;
		
		int rectHeight = getHeight() / n;
		
		int start_x = 1;
		int prev_y = 0;
		
		for(i = 0; i < (n - depth); i++)
		{
			rect = new Rectangle(start_x, prev_y, getWidth() - 2, rectHeight);
			drawRect(g, rect, borderColor);
			prev_y += rectHeight;
		}
		
		i = 0;
		while(i < depth && i < dataStack.size())
		{
			rect = new Rectangle(start_x, prev_y, getWidth() - 2, rectHeight);
			drawRect(g, rect, borderColor);
			String text = dataStack.get(dataStack.size() - 1 - i).toString();
			drawString(g, text, rect, font, textColor);
			prev_y += rectHeight;
			i++;
		}
		
		if(dataStack.size() < n)
		{
			rect = new Rectangle(start_x, prev_y, getWidth() - 2, rectHeight);
			drawRect(g, rect, borderColor);
			drawString(g, "$", rect, font, textColor);
		}
	}
	
	private void drawBackground(Graphics g, Color background)
	{
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	private void drawString(Graphics g, String text, Rectangle rect, Font font, Color foreground)
	{
		FontMetrics metrics = g.getFontMetrics(font);
		
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		
		g.setFont(font);
		g.setColor(foreground);
		g.drawString(text, x, y);
	}
	
	private void drawRect(Graphics g, Rectangle rect, Color color)
	{
		g.setColor(color);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}
}
