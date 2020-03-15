package lsystem.gui.animations;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JFrame;

import lsystem.LSystem;
import lsystem.Symbol;
import lsystem.actions.Action;

public class AnimationFrame extends JFrame
{
	private static final long serialVersionUID = -2538849839603934312L;
	
	private static Font font = new Font("Consolas", Font.PLAIN, 12);
	
	private LSystem system;
	private LinkedList<Symbol> replacement;
	
	private AnimationPanel animationPanel;
	private ActionMapPanel actionMapPanel;
	private ReplacementStringPanel replacementStringPanel;
	private StackPanels stackPanels;
	private TimerSettingsPanel timerSettingsPanel;
	private boolean stop;
	
	public AnimationFrame(AnimationPanel aPanel, LSystem system, LinkedList<Symbol> replacement, Color fontColor, Color background, Color highlightColor)
	{
		this.setSize(1000, 1000);
		
		this.system = system;
		this.replacement = replacement;
		
		animationPanel = aPanel;
		actionMapPanel = new ActionMapPanel(system.grammar.actionMap, font, fontColor, highlightColor);
		replacementStringPanel = new ReplacementStringPanel(replacement, fontColor, highlightColor, font);
		stackPanels = new StackPanels(10, font, fontColor, Color.BLACK, background);
		timerSettingsPanel = new TimerSettingsPanel(font, fontColor);
		timerSettingsPanel.setPreferredSize(actionMapPanel.getPreferredSize());
		
		Center animationPanelContainer = new Center(animationPanel);
		Center actionMapPanelContainer = new Center(actionMapPanel);
		Center replacementStringPanelContainer = new Center(replacementStringPanel);
		Center stackPanelsContainer = new Center(stackPanels);
		Center timerSettingsPanelContainer = new Center(timerSettingsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timerSettingsPanelContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actionMapPanelContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(animationPanelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stackPanelsContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(replacementStringPanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(replacementStringPanelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(animationPanelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stackPanelsContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(actionMapPanelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timerSettingsPanelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        this.getContentPane().setBackground(background);
        pack();
        
		this.setVisible(true);
	}
	
	private int getDelayTime()
	{
		return timerSettingsPanel.getDelayTime();
	}
	
	private int getActionSpeed()
	{
		return timerSettingsPanel.getActionSpeed();
	}
	
	private void shift()
	{
		replacementStringPanel.emptyCurrent();
		pause(getDelayTime());
		replacementStringPanel.shift();
		pause(getDelayTime());
	}
	
	public void play()
	{
		system.reset();
		stop = false;
		for(Symbol symbol : replacement)
		{
			shift();
			actionMapPanel.highlight(symbol);
			pause(getDelayTime());
			perform(system.getActions(symbol));
			actionMapPanel.removeSymbolHighlight();
			if(stop == true)
				break;
		}
	}
	
	public void stop()
	{
		stop = true;
	}
	
	private void perform(LinkedList<Action> actions)
	{
		for(Action action : actions)
		{
			actionMapPanel.highlight(action);
			pause(getDelayTime());			
			perform(action);
			actionMapPanel.removeActionHighlight();
			pause(getDelayTime());
		}
	}
	
	private void pause(long millis)
	{
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void perform(Action action)
	{
		action.perform(system);
		
		switch(action.getClass().getName())
		{
		case "lsystem.actions.MoveForward":
			animationPanel.moveTo(system.current.clone(), getActionSpeed());
			break;
		case "lsystem.actions.RotateCCW":
			animationPanel.rotateTo(((lsystem.actions.RotateCCW)action).radians, system.direction, getActionSpeed());
			break;
		case "lsystem.actions.RotateCW":
			animationPanel.rotateTo(((lsystem.actions.RotateCW)action).radians * -1, system.direction, getActionSpeed());
			break;
		case "lsystem.actions.DrawLine":
			animationPanel.drawLine(getActionSpeed());
		case "lsystem.actions.PopDirection":
			stackPanels.popDirection();
			animationPanel.rotateTo(system.direction, getActionSpeed());
			break;
		case "lsystem.actions.PopPosition":
			stackPanels.popPosition();
			animationPanel.moveTo(system.current, getActionSpeed());
			break;
		case "lsystem.actions.PushDirection":
			stackPanels.pushDirection(system.direction);
			break;
		case "lsystem.actions.PushPosition":
			stackPanels.pushPosition(system.current);
			break;
		}
	}
}
