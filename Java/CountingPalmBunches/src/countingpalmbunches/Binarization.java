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
public class Binarization {
    BufferedImage image;
    int w,h;
    
    public Binarization(){
        try{
            
            System.out.println("Binarization Start");
            File input = new File("./Dataset/6he.jpg");
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            int[] arr = new int[h*w];
            int x=0;
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed());
                    arr[x] = red;
                    x++;
                }
            }
            int median;
            Arrays.sort(arr);
            if (arr.length % 2 == 0)
                median = (arr[arr.length/2] + arr[arr.length/2 - 1])/2;
            else
                median = arr[arr.length/2];
            System.out.println(median);

            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed());

                    if(red<median) {
                        Color newColor = new Color(0,0,0);
                        image.setRGB(j, i, newColor.getRGB());
                    }
                    else {
                        Color newColor = new Color(255,255,255);
                        image.setRGB(j, i, newColor.getRGB());
                    }
                    
                }
            }
            File output = new File("./Dataset/6ib.jpg");
            ImageIO.write(image, "jpg", output);
            System.out.println("Binarization End");
            } catch (Exception e) {}
    }
}
