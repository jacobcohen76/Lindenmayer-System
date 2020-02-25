import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class GUI extends JFrame
{
	private static final long serialVersionUID = 6411499808530678723L;
	
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	
	private BorderLayout layout;
	private InputPanel inputPanel;
	
	public GUI()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		
		layout = new BorderLayout();
		this.setLayout(layout);
		
		inputPanel = new InputPanel();
//		inputPanel.setPreferredSize(new Dimension(1000, 1000));
//		inputPanel.setSize(1000, 1000);
		
//		JPanel panel = new JPanel();
//		panel.setLayout(new BorderLayout());
//		panel.add(inputPanel, BorderLayout.WEST);
		
		this.add(inputPanel, BorderLayout.CENTER);
		
//		this.add(inputPanel, BorderLayout.WEST);
		
//		layout.putConstraint(SpringLayout.WEST, this, 0, SpringLayout.WEST, inputPanel);
//		layout.putConstraint(SpringLayout.VERTICAL_CENTER, getContentPane(), 0, SpringLayout.VERTICAL_CENTER, inputPanel);
		
		
		
		this.setVisible(true);
	}
	
	public static void main(String args[])
	{
		GUI display = new GUI();
	}
}
