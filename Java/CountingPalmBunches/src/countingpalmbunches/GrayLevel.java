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
    int w,h;
    
    public GrayLevel(){
        try{
            
            System.out.println("Gray Level Start");
            File input = new File("./Dataset/6.jpg");
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed() * 0.823);
                    int green = (int)(c.getGreen() * 0.134);
                    int blue = (int)(c.getBlue() * 0.043);
                    
                    Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            File output = new File("./Dataset/6gs.jpg");
            ImageIO.write(image, "jpg", output);
            System.out.println("Gray Level End");
        } catch (Exception e) {}
    }
}
