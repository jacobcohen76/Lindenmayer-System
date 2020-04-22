/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsystem.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
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
   
   private static String actionsHelpString = 
		   "INSTRUCTIONS\r\n" + 
		   "\r\n" + 
		   "	KEYWORD			PARAMETER(S)\r\n" + 
		   "	\r\n" + 
		   "	RCCW			angle	: NUM, (optional) % randomization : PERCENTAGE\r\n" + 
		   "	Rotates the current angle of the turtle graphics by some angle counter-clockwise, if the input parameter is\r\n" + 
		   "	in radians or degrees is determined by if the program is currently in radian mode, or degree mode. This can\r\n" + 
		   "	be modified by selecting an option from the 'Angle Type' menu label. If a percentage is included after the\r\n" + 
		   "	angle parameter every time this action is performed, the angle that the turtle will rotate will vary by\r\n" + 
		   "	the specifed amount.\r\n" + 
		   "	\r\n" + 
		   "	RCW			angle	: NUM, (optional) % randomization : PERCENTAGE\r\n" + 
		   "	Rotates the current angle of the turtle graphics by some angle clockwise, if the input parameter is\r\n" + 
		   "	in radians or degrees is determined by if the program is currently in radian mode, or degree mode. This can\r\n" + 
		   "	be modified by selecting an option from the 'Angle Type' menu label. If a percentage is included after the\r\n" + 
		   "	angle parameter every time this action is performed, the angle that the turtle will rotate will vary by\r\n" + 
		   "	the specifed amount.\r\n" + 
		   "	\r\n" + 
		   "	MVFWD			amount	: NUM, (optional) % randomization : PERCENTAGE\r\n" + 
		   "	Moves the position of the turtle in the turtle graphics forwards by the amount of the input parameter.\r\n" + 
		   "	If a percentage is included after the amount parameter every time this action is performed, the distance\r\n" + 
		   "	the turtle will move will vary by the specifed amount.\r\n" + 
		   "	\r\n" + 
		   "	PUSHPOS			NONE\r\n" + 
		   "	Pushes the current position of the turtle to the position stack.\r\n" + 
		   "	\r\n" + 
		   "	PUSHDIR			NONE\r\n" + 
		   "	Pushes the current direction of the turtle in to the direction stack.\r\n" + 
		   "	\r\n" + 
		   "	POPPOS			NONE\r\n" + 
		   "	Pops a position from the position stack, and moves the turtle there.\r\n" + 
		   "	\r\n" + 
		   "	POPDIR			NONE\r\n" + 
		   "	Pops a direction from the direction stack, and rotates the direction of the turtle to it.\r\n" + 
		   "	\r\n" + 
		   "	INCANGLE		amount : NUM\r\n" + 
		   "	Each time this instruction is called, all other calls of the RCCW and RCW commands will increase by\r\n" + 
		   "	the specified amount.\r\n" + 
		   "	\r\n" + 
		   "	DECANGLE		amount : NUM\r\n" + 
		   "	Each time this instruction is called, all other calls of the RCCW and RCW commands will decrease by\r\n" + 
		   "	the specified amount. This is actually pointless because you can input negative numbers to INCANGLE, so\r\n" + 
		   "	I don't know why you would every use this. I should probably remove it. Edit: it turns out\r\n" + 
		   "	that you cannot use negative numbers as parameters, I should probably update the parser to allow for this behavior.\r\n" + 
		   "	\r\n" + 
		   "	SCALE			scale_factor : NUM\r\n" + 
		   "	Multipies the amount by which all other calls of the MVFWD command will move by a factor.\r\n" + 
		   "	This command is particularly useful for generating equivalent fractals in the IFS format because it\r\n" + 
		   "	allows for more variations of ratios between the drawn lines than provided in traditional L-Systems.\r\n" + 
		   "	\r\n" + 
		   "	SWAP			NONE\r\n" + 
		   "	Multiplies all of the angles in the RCCW and RCW commands by -1. This effectively swaps the direction\r\n" + 
		   "	clockwise and counter-clockwise rotations. To undo this effect, simply add another Symbol that\r\n" + 
		   "	calls this instruction when you want this behavior to stop.\r\n" + 
		   "	\r\n" + 
		   "	INCTHICKNESS		amount : NUM\r\n" + 
		   "	Each time this instruction is called, the thickness of lines drawn will increase by the specifed amount.\r\n" + 
		   "	To decrease the thickness of lines, simply make the amount negative.\r\n" + 
		   "	\r\n" + 
		   "	SCLTHICK		amount : NUM\r\n" + 
		   "	Multiplies the current thickness of lines drawn by a factor.  To remove this effect, simply perform \r\n" + 
		   "	this instruction with a parameter equal to (1 / amount).";
   
   private void addItem(JMenu menu, Preset preset)
   {
	   JMenuItem item = new JMenuItem();
       item.setText(preset.title);
       item.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               load(preset);
           }
       });
       menu.add(item);
   }
   
   private void loadFromPresetsResource()
   {
	   File f = new File("resources\\presets");
	   addPresets(Arrays.asList(f.listFiles()), presetsMenu);
   }
   
   public void addPresets(List<File> files, JMenu base)
   {
	   Collections.sort(files);
	   for(File file : files)
	   {
		   if(file.isDirectory())
		   {
			   String name = file.getName();
			   if(name.length() > 1 && Character.isDigit(name.charAt(0)))
					   name = name.substring(1);
			   JMenu submenu = new JMenu(name);
			   addPresets(Arrays.asList(file.listFiles()), submenu);
			   base.add(submenu);
		   }
	   }
	   for(File file : files)
	   {
		   if(file.isDirectory() == false)
		   {
			   add(file, base);
		   }
	   }
   }
   
   private void add(File preset, JMenu folder)
   {
	   JMenuItem item = new JMenuItem();
	   try
	   {
		   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(preset));
		   Preset imported = (Preset) ois.readObject();
		   ois.close();
		   item.setText(imported.title);
		   item.addActionListener(new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
				   load(imported);
			   }
		   });
		   folder.add(item);
	   }
	   catch(Exception ex)
	   {
		   ex.printStackTrace();
	   } 
   }
   
   private JMenuItem importItem;
   private JMenuItem exportItem;
   
   private JRadioButtonMenuItem biggestGenerationsFirst;
   private JRadioButtonMenuItem degreeMode;
   private JRadioButtonMenuItem radianMode;
   private JRadioButtonMenuItem depthBasedColorsOption;
   private JRadioButtonMenuItem percentageBasedLines;
   private JRadioButtonMenuItem smallestGenerationsFirst;
   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
   @SuppressWarnings("deprecation")
   private void initComponents() {
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
       animateButton = new javax.swing.JButton();
       thicknessLabel = new javax.swing.JLabel();
       thicknessField = new javax.swing.JTextField();
       mainMenu = new javax.swing.JMenuBar();
       fileMenu = new javax.swing.JMenu();
       saveAs = new javax.swing.JMenuItem();
       presetsMenu = new javax.swing.JMenu();
       resetMenuItem = new javax.swing.JMenuItem();
       helpMenu = new javax.swing.JMenu();
       displayActionsMenuItem = new javax.swing.JMenuItem();
       colorsMenu = new javax.swing.JMenu();
       forgeroundMenuItem = new javax.swing.JMenuItem();
       backgroundMenuItem = new javax.swing.JMenuItem();
       importItem = new JMenuItem();
       exportItem = new JMenuItem();
       
       plantsMenu = new javax.swing.JMenu();

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
       
       JScrollPane actionsHelp = new JScrollPane();
       actionsHelp.setViewportView(actionsHelpArea);
       
       //TODO
       actionsHelpArea.setText(actionsHelpString);
       actionsHelpArea.setFocusable(false);

       BorderLayout actionsHelpFrameLayout = new BorderLayout();
       actionsHelpFrame.getContentPane().setLayout(actionsHelpFrameLayout);
       
       actionsHelpFrame.getContentPane().add(actionsHelpArea, BorderLayout.CENTER);
       actionsHelpFrame.setResizable(true);

       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       setResizable(true);

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

       rulesInputArea.setColumns(100);
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

       animateButton.setText("ANIMATE");
       animateButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               animateButtonActionPerformed(evt);
           }
       });
       animateButton.setEnabled(false);

       thicknessLabel.setText("Thickness =");

       fileMenu.setText("File");
       fileMenu.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               fileMenuActionPerformed(evt);
           }
       });

       saveAs.setText("Render");
       saveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
       saveAs.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               saveAsActionPerformed(evt);
           }
       });
       fileMenu.add(saveAs);
       
       importItem.setText("Import");
       exportItem.setText("Export");
       
       importItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
       exportItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
       
       importItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               importItemActionPerformed(evt);
           }
       });
       exportItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               exportItemActionPerformed(evt);
           }
       });
       
       fileMenu.add(importItem);
       fileMenu.add(exportItem);
       
       presetsMenu.setText("Load Presets");
       
       //TODO
       loadFromPresetsResource();
       
       plantsMenu.setText("Plants");
       presetsMenu.add(plantsMenu);
       
       addItem(plantsMenu, Presets.LEAF);
       addItem(plantsMenu, Presets.TUMBLE_WEED);
       addItem(plantsMenu, Presets.BINARY_BUSH);
       addItem(plantsMenu, Presets.TRINARY_BUSH);
       addItem(plantsMenu, Presets.FERN);
       addItem(plantsMenu, Presets.BROCCOLI);
       addItem(plantsMenu, Presets.ALGAE_1);
       addItem(plantsMenu, Presets.ALGAE_2);
       
       JMenu kochMenu = new JMenu();
       kochMenu.setText("Koch");
       presetsMenu.add(kochMenu);
       addItem(kochMenu, Presets.KOCH_CURVE);
       addItem(kochMenu, Presets.KOCH_CURVE_ALT);
       addItem(kochMenu, Presets.QUADRATIC_KOCH_ISLAND_1);
       addItem(kochMenu, Presets.QUADRATIC_KOCH_ISLAND_2);
       addItem(kochMenu, Presets.QUADRATIC_KOCH_ISLAND_3);
       addItem(kochMenu, Presets.KOCH_SNOWFLAKE);
       addItem(kochMenu, Presets.KOCH_ANTI_SNOWFLAKE);
       addItem(kochMenu, Presets.HONEY_COMB);
       addItem(kochMenu, Presets.BOARD);
       addItem(kochMenu, Presets.ICY_FRACTAL);
       
       JMenu sierpinskiMenu = new JMenu();
       sierpinskiMenu.setText("Sierpinski");
       presetsMenu.add(sierpinskiMenu);
       addItem(sierpinskiMenu, Presets.SIERPINSKI_CURVE);
       addItem(sierpinskiMenu, Presets.SIERPINSKI_SQUARE);
       addItem(sierpinskiMenu, Presets.SIERPINSKI_ARROWHEAD);
       addItem(sierpinskiMenu, Presets.SIERPINSKI_TRIANGLE);
       
       JMenu curvesMenu = new JMenu();
       curvesMenu.setText("Curves");
       presetsMenu.add(curvesMenu);
       addItem(curvesMenu, Presets.DRAGON_CURVE);
       addItem(curvesMenu, Presets.LEVY_CURVE);
       addItem(curvesMenu, Presets.CROSS_1);
       addItem(curvesMenu, Presets.CROSS_2);
       addItem(curvesMenu, Presets.PENTAPLEXITY);
       addItem(curvesMenu, Presets.PENTADENDRITE);
       addItem(curvesMenu, Presets.HEXAGONAL_GOSPER);

       resetMenuItem.setText("Reset");
       resetMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
       resetMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               resetMenuItemActionPerformed(evt);
           }
       });
       fileMenu.add(resetMenuItem);
       

       mainMenu.add(fileMenu);
       
       mainMenu.add(presetsMenu);

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

       forgeroundMenuItem.setText("Initial Color");
       forgeroundMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               forgeroundMenuItemActionPerformed(evt);
           }
       });
       colorsMenu.add(forgeroundMenuItem);
       
       JMenuItem finalColorMenuItem = new JMenuItem();
       finalColorMenuItem.setText("Destination Color");
       finalColorMenuItem.addActionListener(new java.awt.event.ActionListener() {
    	   public void actionPerformed(java.awt.event.ActionEvent evt) {
    		   destinationMenuItemActionPerformed(evt);
    	   }
       });
       colorsMenu.add(finalColorMenuItem);

       backgroundMenuItem.setText("Background");
       backgroundMenuItem.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               backgroundMenuItemActionPerformed(evt);
           }
       });
       colorsMenu.add(backgroundMenuItem);
       
       mainMenu.add(colorsMenu);
       
       JMenu angleMode = new JMenu("Angle Type");
       mainMenu.add(angleMode);
       
       degreeMode = new JRadioButtonMenuItem("Degrees", false);
       radianMode = new JRadioButtonMenuItem("Radians", true);
       
       angleMode.add(radianMode);
       angleMode.add(degreeMode);
       
       radianMode.addActionListener(new java.awt.event.ActionListener() {
    	   public void actionPerformed(java.awt.event.ActionEvent evt) {
    		   radianModeActionPerformed(evt);
    	   }
       });
       
       degreeMode.addActionListener(new java.awt.event.ActionListener() {
    	   public void actionPerformed(java.awt.event.ActionEvent evt) {
    		   degreeModeActionPerformed(evt);
    	   }
       });
       
       JMenu renderingOptions = new JMenu("Rendering Options");
       
       smallestGenerationsFirst = new JRadioButtonMenuItem("Smallest Generations on Bottom", false);
       smallestGenerationsFirst.addActionListener(new java.awt.event.ActionListener() {
    	   public void actionPerformed(java.awt.event.ActionEvent evt) {
    		   smallestGenerationsActionPerformed(evt);
    	   }
       });       
       
       biggestGenerationsFirst = new JRadioButtonMenuItem("Biggest Generations on Bottom", true);
       biggestGenerationsFirst.addActionListener(new java.awt.event.ActionListener() {
    	   public void actionPerformed(java.awt.event.ActionEvent evt) {
    		   biggestGenerationsActionPerformed(evt);
    	   }
       });
       renderingOptions.add(biggestGenerationsFirst);
       renderingOptions.add(smallestGenerationsFirst);
       depthBasedColorsOption = new JRadioButtonMenuItem("Depth Based Colors");
       renderingOptions.add(depthBasedColorsOption);
       depthBasedColorsOption.addActionListener(new java.awt.event.ActionListener() {
    	   public void actionPerformed(java.awt.event.ActionEvent evt) {
    		   depthBasedColorsActionPerformed(evt);
    	   }
       });
       
       percentageBasedLines = new JRadioButtonMenuItem("Rounded Line Percentages");
       percentageBasedLines.addActionListener(new java.awt.event.ActionListener() {
    	   public void actionPerformed(java.awt.event.ActionEvent evt) {
    		   percentageBasedLinesActonPerformed(evt);
    	   }
       });
       renderingOptions.add(percentageBasedLines);
       
       mainMenu.add(renderingOptions);
       
       JMenu settingsMenu = new JMenu();
       settingsMenu.setText("Settings");
       mainMenu.add(settingsMenu);

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
//                               .addGap(10, 10, 10)
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jScrollPane3)
                                   .addComponent(jScrollPane2)
                                   .addComponent(jScrollPane1)
                                   .addComponent(jScrollPane4)))
                           .addGroup(layout.createSequentialGroup()
                               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(axiomLabel)
                                   .addComponent(rulesLabel)
                                   .addGroup(layout.createSequentialGroup()
                                       .addComponent(constantsLabel)
                                       .addGap(0, 0, Short.MAX_VALUE)
                                       .addComponent(thicknessLabel)
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(thicknessField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(jLabel3)
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(vectorIField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(jLabel2)
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(vectorJField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                       .addComponent(jLabel1)))
                               .addGap(0, 0, 0)))
                       .addGap(18, 18, 18))
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(variablesLabel)
                       .addGap(475, 475, 475)))
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(generationProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                       .addComponent(generationsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(generationsInput, javax.swing.GroupLayout.PREFERRED_SIZE, 161, Short.MAX_VALUE))
                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                       .addComponent(buildButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(animateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE))
                   .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
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
                           .addComponent(jLabel1)
                           .addComponent(thicknessLabel)
                           .addComponent(thicknessField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                   .addComponent(axiomLabel, javax.swing.GroupLayout.Alignment.TRAILING))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                   .addComponent(buildButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(animateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(constantsLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                   .addComponent(generationProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(variablesLabel)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(rulesLabel)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                   .addComponent(jScrollPane5))
               .addContainerGap())
       );

       pack();
       actionsHelpFrame.setLocationRelativeTo(null);
       this.setLocationRelativeTo(null);
   }// </editor-fold>          
                      

    private Color foregroundColor = Color.GREEN;
    private void forgeroundMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	Color selected = JColorChooser.showDialog(this,  "Select a foreground color.", foregroundColor);
    	if(selected != null)
    		foregroundColor = selected;
    }
    
    private Color backgroundColor = Color.BLACK;
    private void backgroundMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	Color selected = JColorChooser.showDialog(this,  "Select a background color.", backgroundColor);
    	if(selected != null)
    		backgroundColor = selected;
    }
    
    private Color destinationColor = null;
    private void destinationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
    	Color selected = JColorChooser.showDialog(this,  "Select a background color.", destinationColor == null ? foregroundColor : destinationColor);
    	if(selected != null)
    		destinationColor = selected;
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
    	if(system == null)
    	{
    		display("Error, you must generate an LSystem before you can render an image.", FAILURE);
    	}
    	else
    	{
    		buildButton.setEnabled(false);
    		try
    		{
	        	JFileChooser chooser = new JFileChooser();
	    		chooser.setCurrentDirectory(mostRecentDirectory);
	        	chooser.setFileFilter(new FileNameExtensionFilter("Image Files (*.png,*.jpg,*.jpeg,*.jpe,*.jfif)", ImageIO.getReaderFileSuffixes()));        	
	    		chooser.showOpenDialog(null);
	    		chooser.setFileHidingEnabled(true);
	    		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", new String[] {"png"}));
	    		File selected = chooser.getSelectedFile();
	    		
	    		if(selected != null)
	    		{
		    			Thread thread = new SaveImage(selected);
		    			service.execute(thread);
		    			mostRecentDirectory = selected.getParentFile();
	    		}
	    		else
	    		{
	    			buildButton.setEnabled(true);
	    		}
    		}
    		catch(Exception ex)
    		{
    			display("An unexpected error occured while trying to save your image, " + ex.getMessage(), FAILURE);
    			buildButton.setEnabled(true);
    		}
    	}
    }
    
    private float getThickness()
    {
    	try
    	{
    		float f = Float.valueOf(thicknessField.getText());
    		return f;
    	}
    	catch(Exception ex)
    	{
    		throw new Error("Error, expected a float in the thickness field, instead got '" + thicknessField.getText() + "'");
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
    			system.reset();
    			system.biggestGensFirst = biggestGenerationsFirst.isSelected();
        		system.smallestGensFirst = smallestGenerationsFirst.isSelected();
        		system.depthbasedcolors = depthBasedColorsOption.isSelected();
        		system.roundedPercentages = percentageBasedLines.isSelected();
    			system.setFinalColor(destinationColor);
    			system.setN(getNumGenerations());
    			system.startingColor = new Color(foregroundColor.getRed(), foregroundColor.getGreen(), foregroundColor.getBlue(), foregroundColor.getAlpha());
    			system.thickness = getThickness();
    			system.origin = new Point(0, 0);
    			system.initial = getVectorFromInput().normalize();
    			display("Generating Replacement String...", Color.BLACK);
    			system.generate(getNumGenerations());
    			display("Rendering Image...", Color.BLACK);
    			image = system.getImage(thickness, foregroundColor, backgroundColor);
				ImageIO.write(image , "png", selected);
				display("Successfuly saved to '" + selected.getPath() + "'.", SUCCESS);
				buildButton.setEnabled(true);
			} catch (IOException e) {
				display("An error occured while rendering your image, " + e.getMessage(), FAILURE);
				buildButton.setEnabled(true);
			}
    		catch (Error e)
    		{
    			display("An error occured while rendering your image, " + e.getMessage(), FAILURE);
				buildButton.setEnabled(true);
    		}
    		catch (Exception ex)
    		{
    			display("An error occured while rendering your image, " + ex.getMessage(), FAILURE);
				buildButton.setEnabled(true);
    		}
    	}
    }

    private void generationsInputActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    }                                                                                            

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {                                         
    }
    
    
    
    public void smallestGenerationsActionPerformed(java.awt.event.ActionEvent evt)
    {
    	this.biggestGenerationsFirst.setSelected(false);
    	if(system != null)
    		system.smallestGensFirst = this.smallestGenerationsFirst.isSelected();
    }
    
    public void biggestGenerationsActionPerformed(java.awt.event.ActionEvent evt)
    {
    	this.smallestGenerationsFirst.setSelected(false);
    	if(system != null)
    		system.biggestGensFirst = this.biggestGenerationsFirst.isSelected();
    }
    
    public void depthBasedColorsActionPerformed(java.awt.event.ActionEvent evt)
    {
    	if(system != null)
    		system.depthbasedcolors = this.depthBasedColorsOption.isSelected();
    }
    
    public void percentageBasedLinesActonPerformed(java.awt.event.ActionEvent evt)
    {
    	if(system != null)
    		system.roundedPercentages = this.percentageBasedLines.isSelected();
    }
    
    private void resetInputFields()
    {
    	vectorIField.setText("");
    	vectorJField.setText("");
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
    		throw new Error("Error, expected zero or a positive integer in the input field labeled 'Number of Generations, n ='.");
    	}
    	scan.close();
    	if(numGenerations < 0)
    	{
    		throw new Error("Error, expected zero or a positive integer in the input field labeled 'Number of Generations, n ='.");
    	}
    	return numGenerations;
    }
    
    private float thickness = 1.0f;
    private Point origin = new Point(0, 0);
    private LSystem system = null;
    
    private Color SUCCESS = new Color(0x006400);
    private Color FAILURE = new Color(0x800000);
    
    private void buildButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
    	try
    	{
    		String constantsInput = constantsInputArea.getText(); 
    		String variablesInput = variablesInputArea.getText();
    		String axiomInput = axiomInputArea.getText();
    		String rulesInput = rulesInputArea.getText();
    		
    		Parser parser = new Parser(rulesInput, constantsInput, variablesInput, axiomInput);
    		system = parser.parseLSystem(getNumGenerations(), origin.clone(), getVectorFromInput());
    		
    		system.biggestGensFirst = this.biggestGenerationsFirst.isSelected();
    		system.smallestGensFirst = this.smallestGenerationsFirst.isSelected();
    		system.depthbasedcolors = this.depthBasedColorsOption.isSelected();
    		system.roundedPercentages = this.percentageBasedLines.isSelected();

    		display("Success, your LSystem has been created, now generate an image by going to File -> Save As, and then saving your image to your desired location.", SUCCESS);
    		animateButton.setEnabled(true);
       	}
    	catch(Exception ex)
    	{
    		display(ex.getMessage(), FAILURE);
    		animateButton.setEnabled(true);
    	}
    	catch(Error error)
    	{
    		display(error.getMessage(), FAILURE);
    		animateButton.setEnabled(true);
    	}
    	finally
    	{
    		
    	}
    }
    
    private class Animate extends Thread
    {
    	private LSystem system;
    	
    	public Animate(LSystem system)
    	{
    		this.system = system;
    	}
    	
    	public void run()
    	{
    		try
    		{
    			system.biggestGensFirst = biggestGenerationsFirst.isSelected();
        		system.smallestGensFirst = smallestGenerationsFirst.isSelected();
        		system.depthbasedcolors = depthBasedColorsOption.isSelected();
        		system.roundedPercentages = percentageBasedLines.isSelected();
	        	system.thickness = getThickness();
	        	system.setN(getNumGenerations());
    			system.startingColor = new Color(foregroundColor.getRed(), foregroundColor.getGreen(), foregroundColor.getBlue(), foregroundColor.getAlpha());
    			system.setFinalColor(destinationColor);
	        	buildButton.setEnabled(false);
	        	animateButton.setEnabled(false);
	    		system.animate(new Color(foregroundColor.getRed(), foregroundColor.getGreen(), foregroundColor.getBlue(), foregroundColor.getAlpha()), backgroundColor, animationClosureEvent);
	    		buildButton.setEnabled(true);
	        	animateButton.setEnabled(true);
    		}
    		catch(Exception ex)
    		{
        		display(ex.getMessage(), FAILURE);
        		buildButton.setEnabled(true);
            	animateButton.setEnabled(true);
            	system.stopAnimation();
    		}
    		catch(Error e)
    		{
    			display(e.getMessage(), FAILURE);
    			buildButton.setEnabled(true);
            	animateButton.setEnabled(true);
            	system.stopAnimation();
    		}
    	}
    }
    
    public void load(Preset preset)
    {
    	thicknessField.setText(preset.thickness);
    	vectorIField.setText(preset.i);
    	vectorJField.setText(preset.j);
    	generationsInput.setText(preset.n);
    	constantsInputArea.setText(preset.constants);
    	variablesInputArea.setText(preset.variables);
    	axiomInputArea.setText(preset.axiom);
    	rulesInputArea.setText(preset.rules);
    	foregroundColor = preset.foreground;
    	backgroundColor = preset.background;
    	destinationColor = preset.destinationColor;
    	degreeMode.setSelected(preset.degreemode);
    	radianMode.setSelected(!preset.degreemode);
    	biggestGenerationsFirst.setSelected(preset.biggestFirst);
    	smallestGenerationsFirst.setSelected(preset.smallestFirst);
    	depthBasedColorsOption.setSelected(preset.depthbasedColors);
    	percentageBasedLines.setSelected(preset.roundedPercentages);
    	Parser.DEGREEMODE = preset.degreemode;
    }
    
    private java.awt.event.WindowListener animationClosureEvent = new java.awt.event.WindowAdapter()
    {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent)
        {
            try
            {
            	buildButton.setEnabled(true);
            	animateButton.setEnabled(true);
            	system.stopAnimation();
            }
            catch(Exception ex)
            {
            	
            }
        }
    };
    
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
    }                                            

    private void vectorIFieldActionPerformed(java.awt.event.ActionEvent evt) {                                             
    }                                            


    private void displayActionsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        actionsHelpFrame.setVisible(true);
        actionsHelpFrame.setPreferredSize(new Dimension(500, 500));
    }
    
    private void animateButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	service.execute(new Animate(system));
    }
    
	private void importItemActionPerformed(ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(mostRecentDirectory);
    	chooser.setFileFilter(new FileNameExtensionFilter("L-System Ruleset (*.lsrs)", new String[] { "lsrs" }));        	
		chooser.showOpenDialog(null);
		chooser.setFileHidingEnabled(true);
		
		File selected = chooser.getSelectedFile();
		
		if(selected != null)
		{
			load(selected);
		}
	}
	
	private void exportItemActionPerformed(ActionEvent evt) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(mostRecentDirectory);
    	chooser.setFileFilter(new FileNameExtensionFilter("L-System Ruleset (*.lsrs)", new String[] { "lsrs" }));        	
		chooser.showOpenDialog(null);
		chooser.setFileHidingEnabled(true);
		
		File selected = chooser.getSelectedFile();
		
		if(selected != null)
		{
			export(selected);
		}
	}
	
	private File mostRecentDirectory = null;
	
	private void export(File selected)
	{
		try {
			String name = selected.getName();
			if(name.contains(".lsrs") == false)
				name += ".lsrs";
			selected = new File(selected.getParent() + "\\" + name);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selected));
			Preset preset = getCurrentSettings();
			preset.title = selected.getName().substring(0, selected.getName().lastIndexOf('.'));
			oos.writeObject(preset);
			oos.close();
			display("The current program state successfully saved to " + selected, SUCCESS);
			mostRecentDirectory = selected.getParentFile();
		} catch (IOException e) {
			display("An unexpected error occured when trying to save the current program state to " + selected, FAILURE);
		}
	}
	
	private Preset getCurrentSettings()
	{
		Preset preset = new Preset();
		preset.i = vectorIField.getText();
		preset.j = vectorJField.getText();
		preset.thickness = thicknessField.getText();
		preset.n = generationsInput.getText();
		preset.constants = constantsInputArea.getText();
		preset.variables = variablesInputArea.getText();
		preset.axiom = axiomInputArea.getText();
		preset.rules = rulesInputArea.getText();
		preset.foreground = foregroundColor;
		preset.background = backgroundColor;
		preset.destinationColor = destinationColor;
		preset.biggestFirst = biggestGenerationsFirst.isSelected();
		preset.smallestFirst = smallestGenerationsFirst.isSelected();
		preset.depthbasedColors = depthBasedColorsOption.isSelected();
		preset.roundedPercentages = percentageBasedLines.isSelected();
		preset.degreemode = Parser.DEGREEMODE;
		return preset;
	}
	
	private void load(File selected)
	{
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selected));
			Preset preset = (Preset) ois.readObject();
			load(preset);
			ois.close();
			display("Program state successfully loaded from " + selected, SUCCESS);
			mostRecentDirectory = selected;
		} catch (Exception e)
		{
			display("An unexpected error occured when trying to load the program state from " + selected, FAILURE);
		}
	}
	
	private void radianModeActionPerformed(ActionEvent evt) {
		   degreeMode.setSelected(false);
		   radianMode.setSelected(true);
		   Parser.DEGREEMODE = false;
	}
	
	private void degreeModeActionPerformed(ActionEvent evt) {
		   degreeMode.setSelected(true);
		   radianMode.setSelected(false);
		   Parser.DEGREEMODE = true;
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
                if ("Windows".equals(info.getName())) {
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
    private javax.swing.JTextArea actionsHelpArea;
    private javax.swing.JFrame actionsHelpFrame;
    private javax.swing.JButton animateButton;
    private javax.swing.JTextArea axiomInputArea;
    private javax.swing.JLabel axiomLabel;
    private javax.swing.JMenuItem backgroundMenuItem;
    private javax.swing.JButton buildButton;
    private javax.swing.JMenu colorsMenu;
    private javax.swing.JTextArea constantsInputArea;
    private javax.swing.JLabel constantsLabel;
    private javax.swing.JMenuItem displayActionsMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem forgeroundMenuItem;
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
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenu presetsMenu;
    private javax.swing.JMenuItem resetMenuItem;
    private javax.swing.JTextArea rulesInputArea;
    private javax.swing.JLabel rulesLabel;
    private javax.swing.JMenuItem saveAs;
    private javax.swing.JTextArea statusArea;
    private javax.swing.JTextField thicknessField;
    private javax.swing.JLabel thicknessLabel;
    private javax.swing.JTextArea variablesInputArea;
    private javax.swing.JLabel variablesLabel;
    private javax.swing.JTextField vectorIField;
    private javax.swing.JTextField vectorJField;
    private javax.swing.JMenu plantsMenu;
    // End of variables declaration
}