package countingpalmbunches;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alriana
 */
public class Corrector {
    BufferedImage imageH,imageB;
    File output, inputA, inputB;
    int w,h;
    
    public Corrector(String inH, String inB, String out){
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
                    if(red>127){
                        red=255;
                    } else red =0;
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
