import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class InputField extends JPanel
{
	private static final long serialVersionUID = -5621017370103773045L;
	
	private static final Font DEFAULTFONT = new Font("Dialog.bold", Font.PLAIN, 12);
	
	private JLabel label;
	private JTextField input;
	private SpringLayout layout;
	
	public InputField(String labelText, Font labelFont, int pad, String inputText, Font inputFont, int numInputColumns)
	{		
		layout = new SpringLayout();
		this.setLayout(layout);
		
		label = new JLabel();
		label.setText(labelText);
		label.setFont(labelFont);
		
		input = new JTextField();
		input.setText(inputText);
		input.setFont(inputFont);
		input.setColumns(numInputColumns);
		
		this.add(label);
		this.add(input);
		
		layout.putConstraint(SpringLayout.WEST, input, pad, SpringLayout.EAST, label);
	}
	
	public InputField(String labelText, int pad, String inputText, int numInputColumns)
	{
		this(labelText, DEFAULTFONT, pad, inputText, DEFAULTFONT, numInputColumns);
	}
	
	public InputField(String labelText, int pad, int numInputColumns)
	{
		this(labelText, pad, "", numInputColumns);
	}
	
	public String getInputText()
	{
		return input.getText();
	}
}
