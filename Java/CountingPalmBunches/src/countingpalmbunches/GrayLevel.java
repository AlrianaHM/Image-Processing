/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingpalmbunches;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author Alriana
 */
public class GrayLevel {
    BufferedImage image;
    File output, input;
    int w,h;
    
    public GrayLevel(String in, String out){
        try{
            
            System.out.println("Gray Level Start");
            input = new File(in);
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed()*0.25);
                    int green = (int)(c.getGreen()*0.75);
                    int blue = (int)(c.getBlue()*0.25);
                    
                    Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            output = new File(out);
            ImageIO.write(image, "jpg", output);
            System.out.println("Gray Level End");
        } catch (Exception e) {}
    }
}
