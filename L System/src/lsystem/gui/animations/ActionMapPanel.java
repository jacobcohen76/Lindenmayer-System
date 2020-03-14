package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JPanel;

import lsystem.Symbol;
import lsystem.actions.Action;

public class ActionMapPanel extends JPanel
{
	private static final long serialVersionUID = -3830366902137376593L;
	
	private SymbolPanel symbolPanel;
	private ActionListPanel actionListPanel;
	private Color highlightColor;
	
	public ActionMapPanel(HashMap<Symbol, LinkedList<Action>> actionMap, Font font, Color fontColor, Color highlightColor)
	{
		this.setOpaque(false);
		this.highlightColor = highlightColor;
		Object[] keys = actionMap.keySet().toArray();
		Object[] actionList = new Object[actionMap.size()];
		
		for(int i = 0; i < actionMap.size(); i++)
			actionList[i] = actionMap.get((Symbol)keys[i]);
		
		symbolPanel = new SymbolPanel(keys, font);
		actionListPanel = new ActionListPanel(actionList, font, fontColor);
		
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(symbolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actionListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(actionListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(symbolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
	}
	
	public void highlight(Symbol symbol)
	{
		symbolPanel.highlight(symbol, highlightColor);
	}
	
	public void highlight(Action action)
	{
		actionListPanel.highlight(action, highlightColor);
	}
	
	public void removeSymbolHighlight()
	{
		symbolPanel.removeHighlight();
	}
	
	public void removeActionHighlight()
	{
		actionListPanel.removeHighlight();
	}
}
