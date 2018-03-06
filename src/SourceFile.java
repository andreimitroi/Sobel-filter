import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class SourceFile extends FileClass { //extends FileClass
	Scanner keyboard = new Scanner(System.in); //used to get input from keyboard
	File srcFile = null;
	BufferedImage image = null;
	
	public void setName() {
		//reads the name of the source file and sets it in fileName using setter
		//also calculates the est time
		long startTime = System.nanoTime();
		System.out.println("Enter source name:");
		this.setFileName(keyboard.nextLine());
		float estimatedTime = (float)(System.nanoTime() - startTime)/1000000000;
		System.out.println("Source name read completed in: " + estimatedTime + " s");
	}
	
	public void createSrcFile() {
		//creates the source file using keyboard given string
		srcFile = new File(fileName);
		//System.out.println(srcFile.exists());
		
	}
	
	public void createImage() {
		//reads image from file if possible
		//else prints error
		try {
			image = ImageIO.read(srcFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public BufferedImage getImage() {
		//getter for image atribut
		return image;
	}

	//constructor
	public SourceFile() {
		super();
	}
	
	//implementing abstract methods
	@Override
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String s) {
		fileName = s;
	}
	
}
