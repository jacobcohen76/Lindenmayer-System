package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

public class StackPanels extends JPanel
{
	private static final long serialVersionUID = -5920591648928108141L;
	
	private StackPanel<Point> pointStackPanel;
	private StackPanel<Vector> vectorStackPanel;
	
	public StackPanels(int n, Font font, Color textColor, Color borderColor, Color backgroundColor)
	{
		this.setOpaque(false);
		
		pointStackPanel = new StackPanel<Point>(n, font, textColor, borderColor, backgroundColor);
		vectorStackPanel = new StackPanel<Vector>(n, font, textColor, borderColor, backgroundColor);
		
		pointStackPanel.setPreferredSize(new Dimension(100, 401));
		vectorStackPanel.setPreferredSize(new Dimension(100, 401));
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vectorStackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pointStackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pointStackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vectorStackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	}
	
	public void pushPosition(Point pos)
	{
		pointStackPanel.add(pos);
	}
	
	public Point popPosition()
	{
		return pointStackPanel.pop();
	}
	
	public void pushDirection(Vector direction)
	{
		vectorStackPanel.add(direction);
	}
	
	public Vector popDirection()
	{
		return vectorStackPanel.pop();
	}
}
