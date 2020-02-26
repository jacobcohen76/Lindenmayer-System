/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import lsystem.LSystem;
import lsystem.Parser;
import lsystem.cartesian2d.Point;
import lsystem.cartesian2d.Vector;

/**
*
* @author Jacob Cohen
*/
public class GUI extends javax.swing.JFrame {
	private static final long serialVersionUID = 2856215708576999022L;
	
	/**
    * Creates new form GUI
    */
   public GUI() {
       initComponents();
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
   private void initComponents() {
	   this.setTitle("L-System Generator, Created by Jacob Cohen");
       grammarPopupFrame = new javax.swing.JFrame();
       jTextArea1 = new javax.swing.JTextArea();
       actionsHelpFrame = new javax.swing.JFrame();
       actionsHelpArea = new javax.swing.JTextArea();
       constantsLabel = new javax.swing.JLabel();
       jScrollPane1 = new javax.swing.JScrollPane();
       constantsInputArea = new javax.swing.JTextArea();
       variablesLabel = new javax.swing.JLabel();
       jScrollPane2 = new javax.swing.JScrollPane();
       variablesInputArea = new javax.swing.JTextArea();
       axiomLabel = new javax.swing.JLabel();
       jScrollPane3 = new javax.swing.JScrollPane();
       axiomInputArea = new javax.swing.JTextArea();
       rulesLabel = new javax.swing.JLabel();
       jScrollPane4 = new javax.swing.JScrollPane();
       rulesInputArea = new javax.swing.JTextArea();
       generationsLabel = new javax.swing.JLabel();
       generationsInput = new javax.swing.JTextField();
       buildButton = new javax.swing.JButton();
       generationProgress = new javax.swing.JProgressBar();
       jScrollPane5 = new javax.swing.JScrollPane();
       statusArea = new javax.swing.JTextArea();
       jLabel1 = new javax.swing.JLabel();
       vectorJField = new javax.swing.JTextField();
       jLabel2 = new javax.swing.JLabel();
       vectorIField = new javax.swing.JTextField();
       jLabel3 = new javax.swing.JLabel();
       mainMenu = new javax.swing.JMenuBar();
       fileMenu = new javax.swing.JMenu();
       saveAs = new javax.swing.JMenuItem();
       presetsMenu = new javax.swing.JMenu();
       kochCurveMenuItem = new javax.swing.JMenuItem();
       fractalBinaryTreeMenuItem = new javax.swing.JMenuItem();
       sierpinskiTriangleMenuItem = new javax.swing.JMenuItem();
       sierpinskiArrowHeadMenuItem = new javax.swing.JMenuItem();
       dragonCurveMenuItem = new javax.swing.JMenuItem();
       fractalPlantMenuItem = new javax.swing.JMenuItem();
       resetMenuItem = new javax.swing.JMenuItem();
       helpMenu = new javax.swing.JMenu();
       displayActionsMenuItem = new javax.swing.JMenuItem();
       colorsMenu = new javax.swing.JMenu();
       forgeroundMenuItem = new javax.swing.JMenuItem();
       backgroundMenuItem = new javax.swing.JMenuItem();

       grammarPopupFrame.setTitle("Grammar");

       jTextArea1.setColumns(20);
       jTextArea1.setRows(5);
       jTextArea1.setText("<Action> ::= KEYWORD NUM\n<Action> ::= KEYWORD\n\n<ActionList> ::= <Action> , <ActionList>\n<ActionList> ::= <Action>\n\n<Constant> ::= ID ( <ActionList> )\n<Constant> ::= ID\n\n<Variable> ::= ID ( <ActionList> )\n<Variable> ::= ID\n\n<Constants> ::= <Constant> , <Constants>\n<Constants> ::= <Constant>\n<Constants> ::= \n\n<Variables> ::= <Variable> , <Variables>\n<Variables> ::= <Variable>\n<Variables> ::= \n\n<Axiom> ::= <Constant> <Axiom>\n<Axiom> ::= <Constant>\n\n<Axiom> ::= <Variable> <Axiom>\n<Axiom> ::= <Variable>\n\n<Rules> ::= <Rule> <Rules>\n<Rules> ::= <Rule>\n\n<Rule> ::= <Variable> = <RightHandSide> ;\n\n<RightHandSide> ::= <Constant> <RightHandSide>");

       javax.swing.GroupLayout grammarPopupFrameLayout = new javax.swing.GroupLayout(grammarPopupFrame.getContentPane());
       grammarPopupFrame.getContentPane().setLayout(grammarPopupFrameLayout);
       grammarPopupFrameLayout.setHorizontalGroup(
           grammarPopupFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
       );
       grammarPopupFrameLayout.setVerticalGroup(
           grammarPopupFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
       );

       actionsHelpArea.setEditable(false);
       actionsHelpArea.setColumns(20);
       actionsHelpArea.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
       actionsHelpArea.setRows(5);
       actionsHelpArea.setText("Actions\n\n\tKEYWORD:\t\tPARAMETER:\t\tDESCRIPTION:\n\t\t\t\t\t\t\t\n\tRCCW\t\t\tNUM\t\t\tRotates direction parameter is in radians\n\tRCW\t\t\tNUM\t\t\tRotates direction parameter is in radians\n\tMVFWD\t\t\tNUM\t\t\tMoves position forward by some amount\n\n\tDRAWLINE\t\tNONE\t\t\tDraws a line from prev to current position\n\tPUSHDIR\t\t\tNONE\t\t\tPushes current direction onto the stack\n\tPUSHPOS\t\t\tNONE\t\t\tPushes current position onto the stack\n\tPOPDIR\t\t\tNONE\t\t\tPops last direction from the stack\n\tPOPPOS\t\t\tNONE\t\t\tPops last position from the stack\n\n\n\t");
       actionsHelpArea.setFocusable(false);

       javax.swing.GroupLayout actionsHelpFrameLayout = new javax.swing.GroupLayout(actionsHelpFrame.getContentPane());
       actionsHelpFrame.getContentPane().setLayout(actionsHelpFrameLayout);
       actionsHelpFrameLayout.setHorizontalGroup(
           actionsHelpFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(actionsHelpArea, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
       );
       actionsHelpFrameLayout.setVerticalGroup(
           actionsHelpFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(actionsHelpArea, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
       );

       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       setResizable(false);

       constantsLabel.setText("Constants");
       constantsLabel.setFocusable(false);

       constantsInputArea.setColumns(20);
       constantsInputArea.setRows(2);
       jScrollPane1.setViewportView(constantsInputArea);

       variablesLabel.setText("Variables");
       variablesLabel.setFocusable(false);

       variablesInputArea.setColumns(20);
       variablesInputArea.setRows(2);
       jScrollPane2.setViewportView(variablesInputArea);

       axiomLabel.setText("Axiom");
       axiomLabel.setFocusable(false);

       axiomInputArea.setColumns(20);
       axiomInputArea.setRows(2);
       jScrollPane3.setViewportView(axiomInputArea);

       rulesLabel.setText("Rules");
       rulesLabel.setFocusable(false);

       rulesInputArea.setColumns(20);
       rulesInputArea.setRows(2);
       jScrollPane4.setViewportView(rulesInputArea);

       generationsLabel.setText("Number of Generations, n =");
       generationsLabel.setFocusable(false);

       generationsInput.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               generationsInputActionPerformed(evt);
           }
       });

       buildButton.setText("BUILD");
       buildButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               buildButtonActionPerformed(evt);
           }
       });

       generationProgress.setFocusable(false);

       statusArea.setEditable(false);
       statusArea.setColumns(20);
       statusArea.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
       statusArea.setRows(5);
       statusArea.setFocusable(false);
       jScrollPane5.setViewportView(statusArea);

       jLabel1.setText(">");

       vectorJField.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               vectorJFieldActionPerformed(evt);
           }
       });

       jLabel2.setText(",");

       vectorIField.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               vectorIFieldActionPerformed(evt);
           }
       });

       jLabel3.setText("Initial Direction = <");

       fileMenu.setText("File");
       fileMenu.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               fileMenuActionPerformed(evt);
           }
       });

       saveAs.setText("Save As");
       saveAs.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               saveAsActionPerformed(evt);
           }
       });
       fileMenu.add(saveAs);

       presetsMenu.setText("Load Presets");

       kochCurveMenuItem.setText("Koch Curve");
       kochCurveMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               kochCurveMenuItemActionPerformed(evt);
           }
       });
       presetsMenu.add(kochCurveMenuItem);

       fractalBinaryTreeMenuItem.setText("Fractal Binary Tree");
       fractalBinaryTreeMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               fractalBinaryTreeMenuItemActionPerformed(evt);
           }
       });
       presetsMenu.add(fractalBinaryTreeMenuItem);

       sierpinskiTriangleMenuItem.setText("Sierpinski Triangle");
       sierpinskiTriangleMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               sierpinskiTriangleMenuItemActionPerformed(evt);
           }
       });
       presetsMenu.add(sierpinskiTriangleMenuItem);

       sierpinskiArrowHeadMenuItem.setText("Sierpinski Arrowhead");
       sierpinskiArrowHeadMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               sierpinskiArrowHeadMenuItemActionPerformed(evt);
           }
       });
       presetsMenu.add(sierpinskiArrowHeadMenuItem);

       dragonCurveMenuItem.setText("Dragon Curve");
       dragonCurveMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               dragonCurveMenuItemActionPerformed(evt);
           }
       });
       presetsMenu.add(dragonCurveMenuItem);

       fractalPlantMenuItem.setText("Fractal Plant");
       fractalPlantMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               fractalPlantMenuItemActionPerformed(evt);
           }
       });
       presetsMenu.add(fractalPlantMenuItem);

       fileMenu.add(presetsMenu);

       resetMenuItem.setText("Reset");
       resetMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               resetMenuItemActionPerformed(evt);
           }
       });
       fileMenu.add(resetMenuItem);

       mainMenu.add(fileMenu);

       helpMenu.setText("Help");

       displayActionsMenuItem.setText("Display Actions");
       displayActionsMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               displayActionsMenuItemActionPerformed(evt);
           }
       });
       helpMenu.add(displayActionsMenuItem);

       mainMenu.add(helpMenu);

       colorsMenu.setText("Colors");

       forgeroundMenuItem.setText("Foreground");
       forgeroundMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               forgeroundMenuItemActionPerformed(evt);
           }
       });
       colorsMenu.add(forgeroundMenuItem);

       backgroundMenuItem.setText("Background");
       backgroundMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               backgroundMenuItemActionPerformed(evt);
           }
       });
       colorsMenu.add(backgroundMenuItem);

       mainMenu.add(colorsMenu);

       setJMenuBar(mainMenu);

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(layout.createSequentialGroup()
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(axiomLabel)
                                   .addComponent(rulesLabel))
                               .addGap(0, 0, Short.MAX_VALUE))
                           .addGroup(layout.createSequentialGroup()
                               .addGap(10, 10, 10)
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jScrollPane3)
                                   .addComponent(jScrollPane2)
                                   .addComponent(jScrollPane1)
                                   .addComponent(jScrollPane4)))
                           .addGroup(layout.createSequentialGroup()
                               .addComponent(constantsLabel)
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                               .addComponent(jLabel3)
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                               .addComponent(vectorIField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                               .addComponent(jLabel2)
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                               .addComponent(vectorJField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                               .addComponent(jLabel1)))
                       .addGap(18, 18, 18))
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(variablesLabel)
                       .addGap(475, 475, 475)))
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addComponent(generationProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                       .addComponent(generationsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(generationsInput, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                   .addComponent(buildButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(jScrollPane5))
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(generationsLabel)
                           .addComponent(generationsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                       .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(jLabel2)
                           .addComponent(vectorIField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addComponent(jLabel3)
                           .addComponent(vectorJField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addComponent(jLabel1)))
                   .addComponent(constantsLabel, javax.swing.GroupLayout.Alignment.TRAILING))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                   .addComponent(buildButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(variablesLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                   .addComponent(generationProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(axiomLabel)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(rulesLabel)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                   .addComponent(jScrollPane5))
               .addContainerGap())
       );

       pack();
   }// </editor-fold>             
                      

    private Color foregroundColor = Color.GREEN;
    private void forgeroundMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	foregroundColor = JColorChooser.showDialog(this,  "Select a foreground color.", foregroundColor);
    }
    
    private Color backgroundColor = Color.BLACK;
    private void backgroundMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	backgroundColor = JColorChooser.showDialog(this,  "Select a background color.", backgroundColor);
    } 
    
    private void display(String statusMessage, Color color)
    {
    	statusArea.setLineWrap(true);
    	statusArea.setWrapStyleWord(true);
    	statusArea.setForeground(color);
    	statusArea.setText(statusMessage);
    }
    
    private BufferedImage image = null;
    private ExecutorService service = Executors.newFixedThreadPool(1);
    private void saveAsActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    	if(system == null)
    	{
    		display("Error, you must generate an LSystem before you can render an image.", FAILURE);
    	}
    	else
    	{
        	JFileChooser chooser = new JFileChooser();
    		chooser.showOpenDialog(null);
    		chooser.setFileHidingEnabled(true);
    		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", new String[] {"png"}));
    		File selected = chooser.getSelectedFile();
    		
    		if(selected != null)
    		{
	    		Thread thread = new SaveImage(selected);
//	    		thread.setPriority(Thread.);
//	    		thread.setPriority(Thread.MAX_PRIORITY);
	    		service.execute(thread);
    		}
    	}
    }
    
    private class SaveImage extends Thread
    {
    	private File selected;
    	
    	public SaveImage(File selected)
    	{
    		this.selected = selected;
    	}
    	
    	public void run()
    	{
    		try {
    			system.setN(getNumGenerations());
    			system.generate();
    			image = system.getImage(thickness, foregroundColor, backgroundColor);
				ImageIO.write(image , "png", selected);
				display("Successfuly saved to '" + selected.getPath() + "'.", SUCCESS);
			} catch (IOException e) {
				display(e.getMessage(), FAILURE);
			}
    		catch (Error e)
    		{
    			display(e.getMessage(), FAILURE);
    		}
    	}
    }

    private void generationsInputActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void dragonCurveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
    	vectorIField.setText("1");
    	vectorJField.setText("0");
    	generationsInput.setText("5");
    	constantsInputArea.setText("F(MVFWD 3, DRAWLINE),+(RCCW 1.57079632679),-(RCW 1.57079632679)");
    	variablesInputArea.setText("X,Y");
    	axiomInputArea.setText("FX");
    	rulesInputArea.setText("X=X+YF+;\nY=-FX-Y;");
    }                                                   

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void kochCurveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
    	vectorIField.setText("1");
    	vectorJField.setText("0");
    	generationsInput.setText("5");
    	constantsInputArea.setText("+(RCCW 1.5707963268), -(RCW 1.5707963268)");
    	variablesInputArea.setText("F(MVFWD 5, DRAWLINE)");
    	axiomInputArea.setText("F");
    	rulesInputArea.setText("F=F+F-F-F+F;");
    }                                                 

    private void fractalBinaryTreeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
    	vectorIField.setText("0");
    	vectorJField.setText("1");
    	generationsInput.setText("5");
    	constantsInputArea.setText("[(PUSHPOS, PUSHDIR, RCCW 0.7853981634), ](POPPOS, POPDIR, RCW 0.7853981634)");
    	variablesInputArea.setText("A(MVFWD 2, DRAWLINE), B(MVFWD 5, DRAWLINE)");
    	axiomInputArea.setText("A");
    	rulesInputArea.setText("B=BB;\nA=B[A]A;");
    }                                                         

    private void sierpinskiTriangleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
    	vectorIField.setText("0");
    	vectorJField.setText("-1");
    	generationsInput.setText("5");
    	constantsInputArea.setText("+(RCCW 2.0943951024), -(RCW 2.0943951024)");
    	variablesInputArea.setText("F(MVFWD 5, DRAWLINE), G(MVFWD 5, DRAWLINE)");
    	axiomInputArea.setText("F-G-G");
    	rulesInputArea.setText("F=F-G+F+G-F;\nG=GG;");
    }                                                          

    private void sierpinskiArrowHeadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {          
    	vectorIField.setText("1");
    	vectorJField.setText("0");
    	generationsInput.setText("5");
    	constantsInputArea.setText("+(RCCW 1.0471975512),-(RCW 1.0471975512),[(PUSHPOS, PUSHDIR, RCCW 0.7853982),](POPPOS, POPDIR, RCW 0.7853982)");
    	variablesInputArea.setText("A(MVFWD 5, DRAWLINE), B(MVFWD 5, DRAWLINE)");
    	axiomInputArea.setText("A");
    	rulesInputArea.setText("A=B-A-B;\nB=A+B+A;");
    }                                                           

    private void fractalPlantMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
    	vectorIField.setText("0");
    	vectorJField.setText("1");
    	generationsInput.setText("5");
    	constantsInputArea.setText("+(RCCW 0.436332313),-(RCW 0.436332313),[(PUSHPOS, PUSHDIR), ](POPPOS, POPDIR)");
    	variablesInputArea.setText("X,F(MVFWD 3, DRAWLINE)");
    	axiomInputArea.setText("X");
    	rulesInputArea.setText("X=F+[[X]-X]-F[-FX]+X;F=FF;");
    }
    
    private void resetInputFields()
    {
    	generationsInput.setText("");
    	constantsInputArea.setText("");
    	variablesInputArea.setText("");
    	axiomInputArea.setText("");
    	rulesInputArea.setText("");
    	statusArea.setText("");
    	
    	generationProgress.setValue(0);
    	image = null;
    }

    private void resetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    	resetInputFields();
    }                                                
    
    private int getNumGenerations()
    {
    	Scanner scan = new Scanner(generationsInput.getText());
    	int numGenerations = -1;
    	try
    	{
    		numGenerations = scan.nextInt();
    	}
    	catch(Exception ex)
    	{
    		scan.close();
    		throw new Error("Error, expected a non-zero positive integer in the input field labeled 'Number of Generations, n ='.");
    	}
    	scan.close();
    	if(numGenerations < 1)
    	{
    		throw new Error("Error, expected a non-zero positive integer in the input field labeled 'Number of Generations, n ='.");
    	}
    	return numGenerations;
    }
    
    private float thickness = 1.0f;
    private Point origin = new Point(0, 0);
    private LSystem system = null;
    
    private Color SUCCESS = new Color(0x006400);
    private Color FAILURE = new Color(0x800000);
    
    private void buildButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    	try
    	{
    		String constantsInput = constantsInputArea.getText(); 
    		String variablesInput = variablesInputArea.getText();
    		String axiomInput = axiomInputArea.getText();
    		String rulesInput = rulesInputArea.getText();
    		
    		System.out.println(constantsInput);
    		System.out.println(variablesInput);
    		
    		Parser parser = new Parser(rulesInput, constantsInput, variablesInput, axiomInput);
    		system = parser.parseLSystem(5, origin, getVectorFromInput());
    		display("Success, your LSystem has been created, now generate an image by going to File -> Save As, and then saving your image to your desired location.", SUCCESS);
       	}
    	catch(Exception ex)
    	{
    		display(ex.getMessage(), FAILURE);
    	}
    	catch(Error error)
    	{
    		display(error.getMessage(), FAILURE);
    	}
    	finally
    	{
    		
    	}
    }
    
    private Vector getVectorFromInput()
    {
    	Scanner scan;
    	double i;
    	double j;
    	
    	try
    	{
	    	scan = new Scanner(vectorIField.getText());
	    	i = scan.nextDouble();
	    	scan.close();
    	}
    	catch(Exception ex)
    	{
    		throw new Error("Error, expected a number (double or int) in the i component of the initial direction.");
    	}
    	
    	try
    	{
	    	scan = new Scanner(vectorJField.getText());
	    	j = scan.nextDouble();
	    	scan.close();
    	}
    	catch(Exception ex)
    	{
    		throw new Error("Error, expected a number (double or int) in the j component of the initial direction.");
    	}
    	
    	return new Vector(i, j);
    }

    private void vectorJFieldActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void vectorIFieldActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            


    private void displayActionsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        // TODO add your handling code here:
        actionsHelpFrame.setVisible(true);
        actionsHelpFrame.pack();
        actionsHelpFrame.setResizable(false);
    }                                                      

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextArea actionsHelpArea;
    private javax.swing.JFrame actionsHelpFrame;
    private javax.swing.JTextArea axiomInputArea;
    private javax.swing.JLabel axiomLabel;
    private javax.swing.JMenuItem backgroundMenuItem;
    private javax.swing.JButton buildButton;
    private javax.swing.JMenu colorsMenu;
    private javax.swing.JTextArea constantsInputArea;
    private javax.swing.JLabel constantsLabel;
    private javax.swing.JMenuItem displayActionsMenuItem;
    private javax.swing.JMenuItem dragonCurveMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem forgeroundMenuItem;
    private javax.swing.JMenuItem fractalBinaryTreeMenuItem;
    private javax.swing.JMenuItem fractalPlantMenuItem;
    private javax.swing.JProgressBar generationProgress;
    private javax.swing.JTextField generationsInput;
    private javax.swing.JLabel generationsLabel;
    private javax.swing.JFrame grammarPopupFrame;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem kochCurveMenuItem;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenu presetsMenu;
    private javax.swing.JMenuItem resetMenuItem;
    private javax.swing.JTextArea rulesInputArea;
    private javax.swing.JLabel rulesLabel;
    private javax.swing.JMenuItem saveAs;
    private javax.swing.JMenuItem sierpinskiArrowHeadMenuItem;
    private javax.swing.JMenuItem sierpinskiTriangleMenuItem;
    private javax.swing.JTextArea statusArea;
    private javax.swing.JTextArea variablesInputArea;
    private javax.swing.JLabel variablesLabel;
    private javax.swing.JTextField vectorIField;
    private javax.swing.JTextField vectorJField;
    // End of variables declaration                   
}