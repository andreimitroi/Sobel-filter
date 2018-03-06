import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class DestinationFile extends FileClass { //extends FileClass
	Scanner keyboard = new Scanner(System.in); //used to get input from keyboard
	File destFile = null;

	
	public void setName() {
		//gets the name for the destination file and sets it using setter
		long startTime = System.nanoTime();
		System.out.println("Enter destination name:");
		this.setFileName(keyboard.nextLine());
		System.out.println(this.getFileName() + ".bmp");
		destFile = new File(this.getFileName() + ".bmp");
		float estimatedTime = (float)(System.nanoTime() - startTime)/1000000000;
		System.out.println("Destination name read completed in: " + estimatedTime + " s");
	}

	public void createDestImage(BufferedImage img) {
		//saves image to new file
		try {
			ImageIO.write(img, "bmp", destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public void setFileName(String s) {
		fileName = s;
	}
}
