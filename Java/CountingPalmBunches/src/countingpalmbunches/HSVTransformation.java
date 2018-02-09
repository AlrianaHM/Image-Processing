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
public class HSVTransformation {
    
    BufferedImage image;
    File output, input;
    int w,h;
    float hue, sat, val;

    public HSVTransformation(String in, String out){
        try{
            System.out.print(".");
            input = new File(in);
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            float[] hsv = new float[3];
            //System.out.print(w+" "+h+"\n");
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed());
                    int green = (int)(c.getGreen());
                    int blue = (int)(c.getBlue());
                    //System.out.println(i +" x "+j+": "+red+green+blue);
                    
                    //Color.getHSBColor(hue, sat, val);
                    Color.RGBtoHSB(red, green, blue, hsv);
                    hue= hsv[0]*360;
                    sat = hsv[1]*100;
                    val=hsv[2]*100;
                    System.out.println();
                    System.out.println(hue +" | "+sat+" | "+val);
                    //image.setRGB(j, i, newColor.getRGB());
                }
            }/*
            Color.RGBtoHSB(252, 81, 227, hsv);
            Color.getHSBColor(hue, sat, val);
            hue= hsv[0]*360;
            sat = hsv[1]*100;
            val=hsv[2]*100;
            System.out.println();
            System.out.println(hue +" | "+sat+" | "+val);*/
            output = new File(out);
            ImageIO.write(image, "jpg", output);
            System.out.print(".");
        } catch(Exception e){
            
        }
    }
    
}
