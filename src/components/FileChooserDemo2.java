/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package components;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.*;

/*
 * FileChooserDemo2.java requires these files:
 *   ImageFileView.java
 *   ImageFilter.java
 *   ImagePreview.java
 *   Utils.java
 *   images/jpgIcon.gif (required by ImageFileView.java)
 *   images/gifIcon.gif (required by ImageFileView.java)
 *   images/tiffIcon.gif (required by ImageFileView.java)
 *   images/pngIcon.png (required by ImageFileView.java)
 */
public class FileChooserDemo2 extends JPanel {
    static private String newline = "\n";
    public JFileChooser fc;
    public File pictureToGlitch;

    public FileChooserDemo2() {
    	prompt();        
    }
    
    public FileChooserDemo2(BufferedImage imageToSave, String fileName){
    	save(imageToSave, fileName);
    }
    
    public File getfile(){
		return pictureToGlitch;
    }
    
    public void save(BufferedImage imageToSave, String fileName){
    	File outputfile = new File(fileName);
    	try{
    		
    		System.out.println("Preparing to Save");
    		
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setDialogTitle("Save File");   
    		 
    		int userSelection = fileChooser.showSaveDialog(null);
    		 
    		if (userSelection == JFileChooser.APPROVE_OPTION) {
    		    String destination = fileChooser.getSelectedFile().getAbsolutePath();
    		    //ImageIO.write(imageToSave, fileName.substring(fileName.lastIndexOf(".") + 1), outputfile);
    		    ImageIO.write(imageToSave, fileName.substring(fileName.lastIndexOf(".") + 1), new File(destination +  fileName.substring(fileName.lastIndexOf("."))));
    		}
    	} catch (Exception e){
    		
    	}
    }
    
    private void prompt(){
    	if (fc == null) {
            fc = new JFileChooser();

	    //Add a custom file filter and disable the default
	    //(Accept All) file filter.
            fc.addChoosableFileFilter(new ImageFilter());
            fc.setAcceptAllFileFilterUsed(false);

	    //Add custom icons for file types.
            fc.setFileView(new ImageFileView());

	    //Add the preview pane.
            fc.setAccessory(new ImagePreview(fc));
        }

        //Make the file chooser visible. At this point we wait for user input and the response that we will receive will be through the
        //the Open or Cancel button (returning a 0 or a 1).
        int returnVal = fc.showDialog(FileChooserDemo2.this, "Open");

        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) { //if the user hit Open
        	//We can access the selected File by using the .getSelectedFile() method on the JFileChooser object
            pictureToGlitch = fc.getSelectedFile();
            System.out.println("Opening file: " + pictureToGlitch.getName() + "." + newline);
        } else {										//if the user hit Cancel
        	pictureToGlitch = null;
        	System.out.println("Open process cancelled by user." + newline);
        }

        //Reset the file chooser for the next time it's shown.
        fc.setSelectedFile(null);
    }

    public void start() {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
                //SET THE LOOK AND FEEL TO NIMBUS
            	try {
            	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            	        if ("Nimbus".equals(info.getName())) {
            	            UIManager.setLookAndFeel(info.getClassName());
            	            break;
            	        }
            	    }
            	} catch (Exception e) {
            	    // If Nimbus is not available, fall back to cross-platform
            	    try {
            	        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            	    } catch (Exception ex) {
            	        // not worth my time
            	    }
            	}
            	//END OF SETTING THE LOOK AND FEEL TO NIMBUS
            	
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                //createAndShowGUI();
            }
        });
    }
}
