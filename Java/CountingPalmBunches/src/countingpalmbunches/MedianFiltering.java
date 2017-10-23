/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingpalmbunches;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author Alriana
 */
public class MedianFiltering {
    BufferedImage image;
    int w,h;
    
    public MedianFiltering(String in, String out){
        try{
            System.out.println("MedianFiltering Start");
            File input = new File(in);
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            Color[] p= new Color[9];
            int[] val= new int[9];
            
            for(int i=1; i<w-1; i++){    
                for(int j=1; j<h-1; j++){
                    p[0] = new Color(image.getRGB(i-1, j-1));
                    p[1] = new Color(image.getRGB(i-1, j));
                    p[2] = new Color(image.getRGB(i-1, j+1));
                    p[3] = new Color(image.getRGB(i, j-1));
                    p[4] = new Color(image.getRGB(i, j));
                    p[5] = new Color(image.getRGB(i, j+1));
                    p[6] = new Color(image.getRGB(i+1, j-1));
                    p[7] = new Color(image.getRGB(i+1, j));
                    p[8] = new Color(image.getRGB(i+1, j+1));
                    
                    for(int k=0; k<9; k++){
                        val[k]=p[k].getRed();
                    }
                    
                    Arrays.sort(val);
                    
                    image.setRGB(i, j, new Color(val[4],val[4],val[4]).getRGB());
                    
                }
                
            }
            
            File output = new File(out);
            ImageIO.write(image, "jpg", output);
            System.out.println("MedianFiltering End");
        }catch(Exception e){
        }
    }
    
}
