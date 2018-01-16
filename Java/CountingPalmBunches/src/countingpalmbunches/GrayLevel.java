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
            
            System.out.print(".");
            input = new File(in);
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            System.out.print(w+" "+h+"\n");
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed()*0.2);
                    int green = (int)(c.getGreen()*0.75);
                    int blue = (int)(c.getBlue()*0.05);
                    //System.out.println(i +" x "+j+": "+red+green+blue);
                    Color newColor = new Color((red+green+blue),(red+green+blue),(red+green+blue));
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            output = new File(out);
            ImageIO.write(image, "jpg", output);
            System.out.print(".");
        } catch (Exception e) {System.out.print(e);}
    }
}
