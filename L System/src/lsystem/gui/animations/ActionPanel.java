package lsystem.gui.animations;

import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ActionPanel extends JPanel
{
	private static final long serialVersionUID = -4564848835983715948L;

	public ActionPanel(JLabel[] labels)
	{
		this.setOpaque(false);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
                
        ParallelGroup parallel = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
        
        for(JLabel label : labels)
        	parallel = parallel.addComponent(label);
        
        layout.setHorizontalGroup(
        		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(parallel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        SequentialGroup sequential = layout.createSequentialGroup();
        for(JLabel label : labels)
        	sequential = sequential.addComponent(label).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
        
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(sequential)
        );
	}
}
