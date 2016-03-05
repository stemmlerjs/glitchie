/*
 * Project: Glitch-Pond
 * Author: Khalil Stemmler
 * Date Modified: Oct 10th, 2015
 * 
 * The GuiAndListener class is the entry point to the Glitch-Pond project. It creates an instance of the Glitch backend
 * and relies on the backend to perform glitches reqested from the UI.
 */

package gui;

import glitchart.Glitch;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import components.FileChooserDemo2;

public class GuiAndListener extends JFrame {
	
	//Instance Variables
	FileChooserDemo2 fileChooser;
	public File picFromPrompt;
	JLabel picDisplay;
	Glitch glitchProgram;
	String fileName;
	
	public GuiAndListener(){
		//Create an instance of the Glitch Backend
		glitchProgram = new Glitch();
		
		//STARTUP THE GUI
		initialize();
	}
	
	/** This method sets up all of the GUI elements to the screen and boots all of the ActionListeners
     **/
		
	private void initialize() {
		//SETUP THE FORM
		setTitle("Glitchie");
		
		//Set Layout
		GroupLayout groupLayout = new GroupLayout(getContentPane());
        
        //Set Size and Default stuff
		getContentPane().setBackground(new Color(0, 158, 214));
		setSize(600, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Undo Button
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(glitchProgram.imageIndex > 0){
					System.out.println("Undo");
					if(glitchProgram.fileBroken) {
						glitchProgram.fileBroken = false;
					}
					BufferedImage temp = glitchProgram.arrayRemove();
					updatePicture(temp);
				} else {
					System.out.println("Can't undo");
				}
			}
		});
		
		//Redo Button
		JButton redoButton = new JButton("Redo");
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!glitchProgram.nextIndexNull()){
					System.out.println("Redo");
					BufferedImage temp = glitchProgram.redoNext();
					updatePicture(temp);
				} else {
					System.out.println("Can't Redo");
				}
			}
		});
		
		//Logo
		ImageIcon logodesign = new ImageIcon("src/gui/glitchie3.jpg");
		JLabel logo = new JLabel("");
		logo.setBounds(200, 0, 426, 50);
		logo.setIcon(logodesign);
		
		
		//PIC DISPLAY
		picDisplay = new JLabel("To get started, go to File > Open");
		
		//GLITCH RATE Text Field
		final JTextPane glitchRate = new JTextPane();
		
		//GLITCH BUTTON
		JButton glitchButton = new JButton("Perform Glitch");
		glitchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!glitchProgram.fileBroken){
				int rate;
				try{
					rate =  Integer.parseInt(glitchRate.getText());
						System.out.println("Do a glitch");
						glitchProgram.cleanRedoHistory();				   //cleanup redo history, all subsequent glitches in history should be set to null
						glitchProgram.repGlitch(rate);					   //perform glitch
						glitchProgram.arrayAdd(glitchProgram.toPicture()); //add to memory
							if(glitchProgram.fileBroken){				   		//if we break the picture, inform the user to click Undo and use a lesser glitch rate
								//Inform user that the file broke, reset fileBroken to false after
								System.out.println("Displaying error image");
								ImageIcon errorIcon = new ImageIcon("src/gui/broke.jpg");
								picDisplay.setIcon(errorIcon);
							} else {
						updatePicture(glitchProgram.toPicture());		   //update picture on screen
							}
					} catch (Exception e){
						System.out.println("Bad input");
						}
						
					}	
				}
			});
		
		//GLITCH LABEL
		JLabel lblSpecifyGlitchRate = new JLabel("Specify glitch rate:");
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(picDisplay)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblSpecifyGlitchRate)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(glitchRate, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
											.addComponent(undoButton))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(redoButton)
											.addComponent(glitchButton)))))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(81)
								.addComponent(logo)))
						.addContainerGap(158, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(logo)
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(glitchButton)
							.addComponent(lblSpecifyGlitchRate)
							.addComponent(glitchRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(21)
						.addComponent(picDisplay)
						.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(redoButton)
							.addComponent(undoButton))
						.addContainerGap())
			);
		
		//MENU - MAIN
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//MENU - File
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		//MENU - Open a Picture
		JMenuItem file_open = new JMenuItem("Open");
		file_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				picDisplay.setText(null);
				System.out.println("Open new picture");
				fileChooser = new FileChooserDemo2();  	//The process will stop here until completion
				if(fileChooser.getfile() != null){	   	//If we read in a picture, we will put it to the GUI so we can pass it to the Glitch class
					picFromPrompt = fileChooser.getfile();
					fileName = picFromPrompt.getName();
					System.out.println("File " + picFromPrompt.getName() + " successfully transferred to MAIN.");
					
					//Convert to BufferedImage and then update Picture
					Image image = null;
					try {
						image = ImageIO.read(picFromPrompt);
					} catch (IOException e2) {
						System.out.println("Something went wrong in updatePicture() in GuiAndListener");
					}
					glitchProgram.resetArray();
					glitchProgram.arrayAdd((BufferedImage) image); //add it to the memory for redos and undos
					updatePicture((BufferedImage) image); //update the image shown on screen
					
					//Send the picture to our backend so we can process it and glitch it
					try {
						glitchProgram.setupGlitchImage(ImageIO.read(picFromPrompt)); //send this to our glitch backend so we can process and glitch it
					} catch (IOException e1) {
					};
				}
			}
		});
		file.add(file_open);	
		
		//FILE - Save 
		JMenuItem file_save = new JMenuItem("Save");
		file_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FileChooserDemo2(glitchProgram.toPicture(), fileName); //Alternate Constructor
				
			}	
		});
		file.add(file_save);
				
		//NOW SET VISIBLE
		getContentPane().setLayout(groupLayout);
		setVisible(true);	
	}
	
	/* Update the canvas with the new image  */

	protected void updatePicture(BufferedImage image) {
		image = resize((BufferedImage) image);
		ImageIcon icon = new ImageIcon(image); 
		picDisplay.setIcon(icon);
	}
	
	/* Resize the picture before it goes onto the UI. 
	 * EDIT: this method needs additional logic  */
	
	public static BufferedImage resize(BufferedImage image) {
		int height = image.getHeight();
		int width = image.getWidth();
		System.out.println("The height: " + height + " -- and the width: " + width);
		
		// Resize picture to fit the UI nicely
		if((height > 400) || (width > 570)){
			if(height > width){
				System.out.println("resize 1");
				double rate = height / 400;
				height = 400;
				width = (int) Math.round(width / rate);	
			} else if (width > height) { // width greater than or equal to height
				
				// TODO: Use BigDecimal to solve this problem!!
				System.out.println("resize 2 - " + width + " / " + 570 + " = rate");
				double rate = (double) width / 570.0;
				width = 570;
				System.out.println("rate " + rate);
				height = (int) Math.round(height / rate);
			} else {
				System.out.println("resize 3");
				height = 400;
				width = 400;
			}
		}
		
		System.out.println("The new height: " + height + " -- and the new width: " + width);
		
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	/* Main entry-point to the Glitch-Pond project */
	
	public static void main (String [] args){
		new GuiAndListener();
	}

}
