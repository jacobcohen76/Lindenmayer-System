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

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

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
        colorsMenu = new javax.swing.JMenu();
        forgeroundMenuItem = new javax.swing.JMenuItem();
        backgroundMenuItem = new javax.swing.JMenuItem();

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
        statusArea.setRows(5);
        statusArea.setFocusable(false);
        jScrollPane5.setViewportView(statusArea);

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
                    .addComponent(variablesLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(constantsLabel)
                            .addComponent(axiomLabel)
                            .addComponent(rulesLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(generationsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(generationsInput))
                            .addComponent(generationProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buildButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(constantsLabel)
                    .addComponent(generationsLabel)
                    .addComponent(generationsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buildButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(variablesLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(generationProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(axiomLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rulesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>                        

    private Color foregroundColor = Color.BLUE;
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
    private void saveAsActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    	if(image == null)
    	{
    		display("Error, you must generate an image before you can save it as a file.", Color.RED);
    	}
    	else
    	{
        	JFileChooser chooser = new JFileChooser();
    		chooser.showOpenDialog(null);
    		chooser.setFileHidingEnabled(true);
    		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", new String[] {"png"}));
    		File selected = chooser.getSelectedFile();
    		
    		try {
				ImageIO.write(image , "png", selected);
				display("Successfuly saved to '" + selected.getPath() + "'.", Color.GREEN);
			} catch (IOException e) {
				display(e.getMessage(), Color.RED);
			}
    	}
    }                                      

    private void generationsInputActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void dragonCurveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                    
    	generationsInput.setText("20");
    	constantsInputArea.setText("F(MVFWD 3, DRAWLINE),+(RCCW 1.57079632679),-(RCW 1.57079632679)");
    	variablesInputArea.setText("X,Y");
    	axiomInputArea.setText("FX");
    	rulesInputArea.setText("X=X+YF+;\nY=-FX-Y;");
    }                                                   

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void kochCurveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                  
    	generationsInput.setText("4");
    	constantsInputArea.setText("+(RCCW 1.5707963268), -(RCW 1.5707963268)");
    	variablesInputArea.setText("F(MVFWD 5, DRAWLINE)");
    	axiomInputArea.setText("F");
    	rulesInputArea.setText("F=F+F-F-F+F;");
    }                                                 

    private void fractalBinaryTreeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {    	
    	generationsInput.setText("5");
    	constantsInputArea.setText("[(PUSHPOS, PUSHDIR, RCCW 0.7853981634), ](POPPOS, POPDIR, RCW 0.7853981634)");
    	variablesInputArea.setText("A(MVFWD 2, DRAWLINE), B(MVFWD 5, DRAWLINE)");
    	axiomInputArea.setText("A");
    	rulesInputArea.setText("B=BB;\nA=B[A]A;");
    }                                                         

    private void sierpinskiTriangleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                           
    	generationsInput.setText("8");
    	constantsInputArea.setText("+(RCCW 2.0943951024), -(RCW 2.0943951024)");
    	variablesInputArea.setText("F(MVFWD 5, DRAWLINE), G(MVFWD 5, DRAWLINE)");
    	axiomInputArea.setText("F-G-G");
    	rulesInputArea.setText("F=F-G+F+G-F;\nG=GG;");
    }                                                          

    private void sierpinskiArrowHeadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                            
    	generationsInput.setText("5");
    	constantsInputArea.setText("+(RCCW 1.0471975512),-(RCW 1.0471975512),[(PUSHPOS, PUSHDIR, RCCW 0.7853982),](POPPOS, POPDIR, RCW 0.7853982)");
    	variablesInputArea.setText("A(MVFWD 5, DRAWLINE), B(MVFWD 5, DRAWLINE)");
    	axiomInputArea.setText("A");
    	rulesInputArea.setText("A=B-A-B;\nB=A+B+A;");
    }                                                           

    private void fractalPlantMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                         	
    	generationsInput.setText("5");
    	constantsInputArea.setText("[(PUSHPOS, PUSHDIR, RCCW 0.7853981634), ](POPPOS, POPDIR, RCW 0.7853981634)");
    	variablesInputArea.setText("A(MVFWD 2, DRAWLINE), B(MVFWD 5, DRAWLINE)");
    	axiomInputArea.setText("A");
    	rulesInputArea.setText("B=BB;\nA=B[A]A;");
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
    		throw new Error("Error, expected a non-zero positive integer in the input field 'Number of Generations, n = '.");
    	}
    	scan.close();
    	if(numGenerations < 1)
    	{
    		throw new Error("Error, expected a non-zero positive integer in the input field 'Number of Generations, n = '.");
    	}
    	return numGenerations;
    }
    
    private float thickness = 1.0f;
    private Point origin = new Point(0, 0);
    private Vector direction = new Vector(1, 0);
    private LSystem system = null;
    
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
    		system = parser.parseLSystem(getNumGenerations(), origin, direction);
    		system.generate();
    		
    		if(foregroundColor == null)
    			foregroundColor = Color.BLUE;
    		if(backgroundColor == null)
    			backgroundColor = Color.BLACK;
    		
    		image = system.getImage(thickness, foregroundColor, backgroundColor);
    	}
    	catch(Exception ex)
    	{
    		display(ex.getMessage(), Color.RED);
    	}
    	catch(Error error)
    	{
    		display(error.getMessage(), Color.RED);
    	}
    	finally
    	{
    		
    	}
    }                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextArea axiomInputArea;
    private javax.swing.JLabel axiomLabel;
    private javax.swing.JMenuItem backgroundMenuItem;
    private javax.swing.JButton buildButton;
    private javax.swing.JMenu colorsMenu;
    private javax.swing.JTextArea constantsInputArea;
    private javax.swing.JLabel constantsLabel;
    private javax.swing.JMenuItem dragonCurveMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem forgeroundMenuItem;
    private javax.swing.JMenuItem fractalBinaryTreeMenuItem;
    private javax.swing.JMenuItem fractalPlantMenuItem;
    private javax.swing.JProgressBar generationProgress;
    private javax.swing.JTextField generationsInput;
    private javax.swing.JLabel generationsLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
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
    // End of variables declaration                   
}
