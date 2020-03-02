package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lsystem.Symbol;

public class ReplacementStringPanel extends JPanel
{
	private static final long serialVersionUID = -7299249429292546704L;
	
	private static final int n = 256;
	
	private JLabel current;
	private JLabel remainder;
	
	private String replacementString;
	
	public ReplacementStringPanel(LinkedList<Symbol> replacement, Color fontColor, Color highlight, Font font)
	{
		this.setOpaque(false);
		
		replacementString = "";
		for(Symbol symbol : replacement)
			replacementString += symbol;
		
		current = new JLabel();
		current.setOpaque(true);
		current.setFont(font);
		current.setForeground(fontColor);
		current.setBackground(highlight);
		current.setText(" ");
		
		remainder = new JLabel();
		remainder.setFont(font);
		remainder.setForeground(fontColor);
		if(replacementString.length() > n)
			remainder.setText(replacementString.substring(0, n));
		else
			remainder.setText(replacementString);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(current)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remainder))
//                .addGap(0, 265, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(current)
                    .addComponent(remainder)))
//                .addGap(0, 286, Short.MAX_VALUE))
        );        
	}
	
	public void emptyCurrent()
	{
		current.setText(" ");
	}
	
	public void shift()
	{
		if(replacementString.length() > 0)
		{
			current.setText(replacementString.substring(0, 1));
			replacementString = replacementString.substring(1);
			if(replacementString.length() > n)
				remainder.setText(replacementString.substring(0, n));
			else
				remainder.setText(replacementString);
		}
		else
		{
			emptyCurrent();
		}
	}
}
