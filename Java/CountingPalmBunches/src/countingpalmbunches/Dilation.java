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
public class Dilation {
    BufferedImage image;
    int w,h,picsize;
    
    public Dilation(){
        try{
            System.out.println("Dilation Start");
            File input = new File("./Dataset/Kacang3.jpg");
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            picsize = w*h;
            int[] piksel = new int[picsize];
            int foreground = 255;
            int reverse = 0;
            
            for(int i=0;i<h;i++){
                for(int j=0; j<w;j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = c.getRed();
                    if(red == foreground){
                        boolean flag = false;
                        for(int y= i-1; y <= i+1 && flag==false; y++){
                            for(int x= j-1; x <= j+1 && flag==false; x++){
                                
                                if(y >= 0 && y < h && x>=0 && x < w){
                                    c = new Color(image.getRGB(x, y));
                                    red = c.getRed();
                                    if(red != foreground){
                                        flag = true;
                                        piksel[j+i*w] = reverse;
                                    }
                                }
                            }
                        }if(flag == false){
                        piksel [j+i*w] = foreground;
                        }
                    } else{
                        piksel[j+i*w] = reverse;
                    }
                }
            }
            
            File output = new File("./Dataset/Kacang5.jpg");
            for(int y = 0; y < h; y++){
                for(int x = 0; x < w; x++){
                    int v = piksel[x+y*w];
                    Color newColor = new Color(v,v,v);
                    image.setRGB(x, y, newColor.getRGB());
                    
                }
           }
           ImageIO.write(image, "jpg", output);
           System.out.println("Dilation End");

        }catch(Exception e){
            
        }
    }
}
