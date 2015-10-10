package glitchart;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import components.FileChooserDemo2;

public class Glitch {
	byte[] fileContent = null;
	Scanner in;
	String userInput;
	int iterations;
	byte[] imageInByte;
	LinkStack memory;
	BufferedImage [] memoryImage;
	public int imageIndex;
	public boolean fileBroken;
	
	String fileName = "123";
	String fileType = "jpg";
	String partFilePath;
	String partNewFilePath;
	FileChooserDemo2 filech;
	File image;
	
	public void setupGlitchImage(BufferedImage img){
		//Convert Image to Byte Array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
		ImageIO.write(img, fileType, baos);
		baos.flush();
		imageInByte = baos.toByteArray();
		baos.close();
		} catch (IOException e){
			System.out.println("Error in the setupGlitchImage() method in the Glitch Backend");
		}
	}
	
	public Glitch(){	
		//SETUP IMAGE/SEND TO BYTE ARRAY
		memoryImage = new BufferedImage[50];
		imageIndex = -1;
	} 
	
	public void resetArray(){
		memoryImage = null;
		memoryImage = new BufferedImage[50];
		imageIndex = -1;
	}
	
	public BufferedImage redoNext(){
		imageIndex++;
		System.out.println("Array index - " + imageIndex);
		setupGlitchImage(memoryImage[imageIndex]);
		return memoryImage[imageIndex];
	}
	
	public void arrayAdd(BufferedImage img){
		System.out.println("Array index - " + imageIndex);
		imageIndex++;
		memoryImage[imageIndex] = img;
	}
	
	public BufferedImage arrayRemove(){
		imageIndex--;
		System.out.println("Array index - " + imageIndex);
		setupGlitchImage(memoryImage[imageIndex]);
		return memoryImage[imageIndex];
	}

	public void replace(byte[] temp) {
		for(int i = 0; i < temp.length; i++){
			imageInByte[i] = temp[i];
		}
		if (Arrays.equals(imageInByte, temp)){
		    System.out.println("Yup, they're the same!");
		}
	}

	/** This method performs a replacement glitch on the byte array by switching two bytes within the array's length. This is iterated
	 ** a number of times based on the iteration variable.
     ** @param iter      		the amount of iterations to be performed
     **/
	
	public BufferedImage toPicture(){
		// convert byte array back to BufferedImage
		InputStream in = new ByteArrayInputStream(imageInByte);
		BufferedImage bImageFromConvert = null;
		try {
			bImageFromConvert = ImageIO.read(in);
		} catch (IOException e) {
			System.out.println("Something went wrong in toPicture() in Glitch");
			fileBroken = true;
		}
		return bImageFromConvert;
	}
	
	public void repGlitch(int iter){
		for(int i = 0; i <= iter; i++){
			imageInByte[(int) Math.floor(Math.random() * imageInByte.length)] = imageInByte[(int) Math.floor(Math.random() * imageInByte.length)];
		}
	}
	
	public void readInPicture(){
		System.out.println("Showing File Chooser");
		filech = new FileChooserDemo2();
		filech.start();

		image = filech.getfile();
		System.out.println(image.getAbsolutePath());
	}
	
	public static void main(String args[]){
		new Glitch();
	}

	public boolean nextIndexNull() {
		if(memoryImage[imageIndex + 1] == null){
			return true;
		} else {
			return false;
		}
	}

	public void cleanRedoHistory() {
		int indexTemp = imageIndex + 1;
		while(memoryImage[indexTemp] != null){
			memoryImage[indexTemp] = null;
			indexTemp++;
		}
		
	}

}
