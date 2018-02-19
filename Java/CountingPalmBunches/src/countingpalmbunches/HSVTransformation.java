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
    int[] hg;
    public HSVTransformation(String in, String out){
        try{
            System.out.print(".");
            //System.out.println();
            input = new File(in);
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            float[] hsv = new float[3];
            hg = new int[36];
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
                    //System.out.println(hsv[0]);
                    int rgb = Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]);
                    image.setRGB(j, i, rgb);
                    //System.out.println(hue +", "+sat+", "+val);
                    int idx =(int) hue/10;
                    if(hue>0 && sat >0 && val>0) hg[idx]++;
                    //if(idx>=10) System.out.println(sat);
                    //image.setRGB(j, i, newColor.getRGB());
                }
            }
            //*
            System.out.println("------------");
            for(int i=0;i<36;i++){
                System.out.println(hg[i]);
            }//*/
            
            output = new File(out);
            ImageIO.write(image, "jpg", output);
            System.out.print(".");
        } catch(Exception e){
            
        }
    }
    
}
