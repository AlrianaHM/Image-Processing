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
public class Erosion {
    BufferedImage image;
    int w,h,picsize;
    
    public Erosion(String in, String out){
        try{
            System.out.println("Erosion Start");
            File input = new File(in);
            
                        System.out.println("pass");
            image = ImageIO.read(input);
            
            w = image.getWidth();
            h = image.getHeight();
            picsize = w*h;
            
            int[] piksel = new int[picsize];
            int[] piksel2 = new int[picsize];

            int foreground = 255;
            int reverse = 0;
            
            for(int i=0;i<h;i++){
                for(int j=0; j<w;j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = c.getRed();
                    if(red>127){
                        red=255;
                    } else red =0;
                    //System.out.println(i +" "+j+" = "+red);
                    if(red == foreground){
                        boolean flag = false;
                        int cx=j,cy=i;
                        int rad=5;
                        for(int y= i-rad; y <= i+rad && flag==false; y++){
                            for(int x= j-rad; x <= j+rad && flag==false; x++){
                                int r2 = (x-cx)*(x-cx) + (y-cy)*(x-cx);
                                if(y >= 0 && y < h && x>=0 && x < w  && r2<=rad*rad ){
                                    c = new Color(image.getRGB(x, y));
                                    red = c.getRed();
                                    if(red>127){
                                        red=255;
                                    } else red =0;
                                    //System.out.println(y +" "+x+" = "+red);
                                    if(red != foreground){
                                        flag = true;
                                        piksel[j+i*w] = reverse;
                                    }
                                    
                                }
                            }
                        }
                        if(flag == false){
                        piksel [j+i*w] = foreground;
                        }
                    } else{
                        piksel[j+i*w] = reverse;
                    }
                }
            }
            
            File output = new File(out);
            for(int y = 0; y < h; y++){
                for(int x = 0; x < w; x++){
                    int v = piksel[x+y*w];
                    Color newColor = new Color(v,v,v);
                    image.setRGB(x, y, newColor.getRGB());
                    
                }
           }
           ImageIO.write(image, "jpg", output);
           System.out.println("Erosion End");

        }catch(Exception e){
            
                        System.out.println(e);
        }
    }
}
