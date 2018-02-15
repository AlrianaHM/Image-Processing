/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingpalmbunches;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Alriana
 */
public class HSVBinarization {
    BufferedImage image;
    File output, input;
    int w,h;
    
    public HSVBinarization(String in, String out){
        try{
            
            System.out.print(".");
            input = new File(in);
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            int dh=10*h/100;
            int uh=70*h/100;
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed());
                    int green = (int)(c.getGreen());
                    int blue = (int)(c.getBlue());
                    float[] hsv = new float[3];
                    Color.RGBtoHSB(red, green, blue, hsv);
                    float hue=  hsv[0]*360;
                    float sat= hsv[1]*100;
                    float val= hsv[2]*100;
                    if(((hue>=340 && hue<=360 )|| (hue>=0 && hue<=30)) && sat >= 30 && val >=30 && val <90) {
                        if(i>=dh && i<=uh) {
                            Color newColor = new Color(255,255,255);
                            image.setRGB(j, i, newColor.getRGB());
                        } else {
                            Color newColor = new Color(0,0,0);
                            image.setRGB(j, i, newColor.getRGB());
                        }
                    }
                    else {
                        Color newColor = new Color(0,0,0);
                        image.setRGB(j, i, newColor.getRGB());
                    }
                    
                }
            }
            output = new File(out);
            ImageIO.write(image, "jpg", output);
            System.out.print(".");
        } catch (Exception e) {System.out.print(e);}
    }
}
