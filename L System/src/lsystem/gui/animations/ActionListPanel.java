package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lsystem.actions.Action;

public class ActionListPanel extends JPanel
{
	private static final long serialVersionUID = 7734245934321812207L;
	
	private HashMap<Action, JLabel> labelMap;
	private JLabel highlighted;
	
	public ActionListPanel(Object[] actionLists, Font font, Color fontColor)
	{
		this.setOpaque(false);
		
		ArrayList<LinkedList<JLabel>> labelLists = new ArrayList<LinkedList<JLabel>>();
		labelMap = new HashMap<Action, JLabel>();
		
		for(Object object : actionLists)
		{
			@SuppressWarnings("unchecked")
			LinkedList<Action> actionList = (LinkedList<Action>) object;
			labelLists.add(new LinkedList<JLabel>());
			
			JLabel arrow = new JLabel();
			arrow.setText("->");
			arrow.setFont(font);
			arrow.setForeground(fontColor);
			
			labelLists.get(labelLists.size() - 1).add(arrow);
			
			for(Action action : actionList)
			{
				JLabel label = new JLabel();
				label.setText(action.toString());
				label.setFont(font);
				label.setForeground(fontColor);
				labelLists.get(labelLists.size() - 1).add(label);
				labelMap.put(action, label);
			}
		}
		
		highlighted = null;
		
		int max = -1;
		
		for(LinkedList<JLabel> labelList : labelLists)
			if(labelList.size() > max)
				max = labelList.size();
		
		ActionPanel[] actionPanels = new ActionPanel[max];
		
		for(int i = 0; i < max; i++)
		{
			JLabel[] labels = new JLabel[labelLists.size()];
			int j = 0;
			for(LinkedList<JLabel> labelList : labelLists)
			{
				if(labelList.size() > i)
					labels[j] = labelList.get(i);
				else
				{
					labels[j] = new JLabel("   ");
					labels[j].setFont(font);
				}
				j++;
			}
			actionPanels[i] = new ActionPanel(labels);
		}
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        
        SequentialGroup sequential = layout.createSequentialGroup();
        for(ActionPanel actionPanel : actionPanels)
        	sequential = sequential.addComponent(actionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(sequential));
        
        ParallelGroup parallel = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        
        for(ActionPanel actionPanel : actionPanels)
        	parallel.addComponent(actionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        
        layout.setVerticalGroup(parallel);
	}
	
	public void removeHighlight()
	{
		if(highlighted != null)
		{
			highlighted.setOpaque(false);
			highlighted.repaint();
		}
		highlighted = null;
	}
	
	public void highlight(Action action, Color color)
	{
		removeHighlight();
		highlighted = labelMap.get(action);
		highlighted.setOpaque(true);
		highlighted.setBackground(color);
		highlighted.repaint();
	}
}
