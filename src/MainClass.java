import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainClass {

	public static void main(String[] args) {
		
		SourceFile sFile1 = new SourceFile();
		sFile1.setName();
		long startTime = System.nanoTime(); //start timing
		sFile1.createSrcFile();
		sFile1.createImage();
		float estimatedTime = (float)(System.nanoTime() - startTime)/1000000000; //stop+calculate est time
		System.out.println("Source file created in: " + estimatedTime + " s"); //print est time
		DestinationFile dFile2 = new DestinationFile();
		
		//start timing for processing
		startTime = System.nanoTime();
		
		//image processing alg
		//getting image width and height
		int x = sFile1.getImage().getWidth();
        int y = sFile1.getImage().getHeight();

        //int maxGval = 0;
        int[][] edgeColors = new int[x][y];
        int maxGradient = -1;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                int val00 = getGrayScale(sFile1.getImage().getRGB(i - 1, j - 1));
                int val01 = getGrayScale(sFile1.getImage().getRGB(i - 1, j));
                int val02 = getGrayScale(sFile1.getImage().getRGB(i - 1, j + 1));

                int val10 = getGrayScale(sFile1.getImage().getRGB(i, j - 1));
                int val11 = getGrayScale(sFile1.getImage().getRGB(i, j));
                int val12 = getGrayScale(sFile1.getImage().getRGB(i, j + 1));

                int val20 = getGrayScale(sFile1.getImage().getRGB(i + 1, j - 1));
                int val21 = getGrayScale(sFile1.getImage().getRGB(i + 1, j));
                int val22 = getGrayScale(sFile1.getImage().getRGB(i + 1, j + 1));

                int gx =  ((-1 * val00) + (0 * val01) + (1 * val02)) 
                        + ((-2 * val10) + (0 * val11) + (2 * val12))
                        + ((-1 * val20) + (0 * val21) + (1 * val22));

                int gy =  ((-1 * val00) + (-2 * val01) + (-1 * val02))
                        + ((0 * val10) + (0 * val11) + (0 * val12))
                        + ((1 * val20) + (2 * val21) + (1 * val22));

                double gval = Math.sqrt((gx * gx) + (gy * gy));
                int g = (int) gval;

                if(maxGradient < g) {
                    maxGradient = g;
                }

                edgeColors[i][j] = g;
            }
        }

        double scale = 255.0 / maxGradient;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                int edgeColor = edgeColors[i][j];
                edgeColor = (int)(edgeColor * scale);
                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

                sFile1.getImage().setRGB(i, j, edgeColor);
            }
        }
        
        estimatedTime = (float)(System.nanoTime() - startTime)/1000000000; //stop+calculate timing for processing
		System.out.println("Image processed in: " + estimatedTime + " s");
        
		
		dFile2.setName();
        dFile2.createDestImage(sFile1.getImage());
        System.out.println("Finished");
	
	}
	
	public static int getGrayScale(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;

        //from https://en.wikipedia.org/wiki/Grayscale, calculating luminance
        int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
        //int gray = (r + g + b) / 3;

        return gray;
    }

}
