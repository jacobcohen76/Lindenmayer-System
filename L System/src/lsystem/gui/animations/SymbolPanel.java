package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lsystem.Symbol;

public class SymbolPanel extends JPanel
{
	private static final long serialVersionUID = 1468787329221383401L;
	
	private HashMap<Symbol, JLabel> labelMap;
	private JLabel highlighted;
	
	public SymbolPanel(Object[] objects, Font font)
	{
		this.setOpaque(false);
		
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		labelMap = new HashMap<Symbol, JLabel>();
		highlighted = null;
		
		for(Object object : objects)
		{
			JLabel label = new JLabel();
			label.setText(object.toString());
			label.setFont(font);
			labels.add(label);
			labelMap.put((Symbol)object, label);
		}
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        
        ParallelGroup parallel = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING);
        for(JLabel label : labels)
        	parallel = parallel.addComponent(label);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(parallel)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        SequentialGroup sequential = layout.createSequentialGroup();
        for(JLabel label : labels)
        	sequential = sequential.addComponent(label).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        sequential = sequential.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sequential)
        );
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
	
	public void highlight(Symbol symbol, Color color)
	{
		removeHighlight();
		highlighted = labelMap.get(symbol);
		highlighted.setOpaque(true);
		highlighted.setBackground(color);
		highlighted.repaint();
	}
}
