import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class InputPanel extends JPanel
{
	private static final long serialVersionUID = 8993778411941960466L;
	
	private static Dimension CONSTANT_DIMENSIONS;
	private static Dimension VARIABLE_DIMENSIONS;
	private static Dimension AXIOM_DIMENSIONS;
	private static Dimension RULES_DIMENSIONS;
	
	static
	{
		CONSTANT_DIMENSIONS = new Dimension(500, 100);
		VARIABLE_DIMENSIONS = new Dimension(500, 100);
		AXIOM_DIMENSIONS = new Dimension(500, 100);
		RULES_DIMENSIONS = new Dimension(500, 100);
	}
	
	private SpringLayout layout;
	
	private JLabel constantLabel;
	private JLabel variableLabel;
	private JLabel axiomLabel;
	private JLabel rulesLabel;
	
	private JTextArea constantInput;
	private JTextArea variableInput;
	private JTextArea axiomInput;
	private JTextArea rulesInput;
	
	private JScrollPane constantScrollPane;
	private JScrollPane variableScrollPane;
	private JScrollPane axiomScrollPane;
	private JScrollPane rulesScrollPane;
	
	public InputPanel()
	{
		layout = new SpringLayout();
		this.setLayout(layout);
		
		constantLabel = new JLabel("Constants ");
		variableLabel = new JLabel("Variables ");
		axiomLabel = new JLabel("Axiom ");
		rulesLabel = new JLabel("Rules ");
		
		constantInput = new JTextArea();
		variableInput = new JTextArea();
		axiomInput = new JTextArea();
		rulesInput = new JTextArea();
		
		constantScrollPane = new JScrollPane(constantInput);
		variableScrollPane = new JScrollPane(variableInput);
		axiomScrollPane = new JScrollPane(axiomInput);
		rulesScrollPane = new JScrollPane(rulesInput);
		
		constantScrollPane.setPreferredSize(CONSTANT_DIMENSIONS);
		variableScrollPane.setPreferredSize(VARIABLE_DIMENSIONS);
		axiomScrollPane.setPreferredSize(AXIOM_DIMENSIONS);
		rulesScrollPane.setPreferredSize(RULES_DIMENSIONS);
		
		this.add(constantLabel);
		this.add(variableLabel);
		this.add(axiomLabel);
		this.add(rulesLabel);
		
		this.add(constantScrollPane);
		this.add(variableScrollPane);
		this.add(axiomScrollPane);
		this.add(rulesScrollPane);
		
		layout.putConstraint(SpringLayout.WEST, constantLabel, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, constantLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, variableLabel, 0, SpringLayout.EAST, constantLabel);
		layout.putConstraint(SpringLayout.EAST, axiomLabel, 0, SpringLayout.EAST, constantLabel);
		layout.putConstraint(SpringLayout.EAST, rulesLabel, 0, SpringLayout.EAST, constantLabel);
		
		layout.putConstraint(SpringLayout.WEST, constantScrollPane, 0, SpringLayout.EAST, constantLabel);
		layout.putConstraint(SpringLayout.WEST, variableScrollPane, 0, SpringLayout.EAST, variableLabel);
		layout.putConstraint(SpringLayout.WEST, axiomScrollPane, 0, SpringLayout.EAST, axiomLabel);
		layout.putConstraint(SpringLayout.WEST, rulesScrollPane, 0, SpringLayout.EAST, rulesLabel);
		
		layout.putConstraint(SpringLayout.NORTH, constantScrollPane, 5, SpringLayout.NORTH, constantLabel);
		layout.putConstraint(SpringLayout.NORTH, variableLabel, 2, SpringLayout.SOUTH, constantScrollPane);
		layout.putConstraint(SpringLayout.NORTH, variableScrollPane, 5, SpringLayout.SOUTH, constantScrollPane);
		layout.putConstraint(SpringLayout.NORTH, axiomLabel, 2, SpringLayout.SOUTH, variableScrollPane);
		layout.putConstraint(SpringLayout.NORTH, axiomScrollPane, 5, SpringLayout.SOUTH, variableScrollPane);
		layout.putConstraint(SpringLayout.NORTH, rulesLabel, 2, SpringLayout.SOUTH, axiomScrollPane);
		layout.putConstraint(SpringLayout.NORTH, rulesScrollPane, 5, SpringLayout.SOUTH, axiomScrollPane);
	}	 
}
