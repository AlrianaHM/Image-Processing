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
public class ImageSuperimpose {
    BufferedImage imageH,imageB;
    File output, inputA, inputB;
    int w,h;
    
    public ImageSuperimpose(String inB, String inH, String out){
        try{
            
            System.out.print(".");
            inputA = new File(inB);
            imageB = ImageIO.read(inputA);
            w = imageB.getWidth();
            h = imageB.getHeight();
            
            inputB = new File(inH);
            imageH = ImageIO.read(inputB);
            
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    Color c = new Color(imageB.getRGB(j, i));
                    int red = (int)c.getRed();
                    if(red <= 128){
                        red =0;
                    } else {
                        red =255;
                    }
                    
                    if(red == 0){
                        Color a = new Color(imageH.getRGB(j, i));
                        int redH = (int)a.getRed();
                        if(redH <= 128){
                            redH =0;
                        } else {
                            redH =255;
                        }
                        red =red+redH;
                        //System.out.println(red);
                        
                    }
                    Color newColor = new Color(red,red,red);
                    imageH.setRGB(j, i, newColor.getRGB());
                    
                }
            }
            output = new File(out);
            ImageIO.write(imageH, "jpg", output);
            System.out.print(".");
        } catch (Exception e) {System.out.print(e);}
    }
}
