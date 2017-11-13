/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingpalmbunches;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

/**
 *
 * @author Alriana
 */
public class ArffCreator {
    
    BufferedImage image;
    File input;
    PrintWriter output; 
    int w,h;
    
    public ArffCreator(String in, String out) throws IOException{
        System.out.print(".");
        input = new File(in);
        image = ImageIO.read(input);
        w = image.getWidth();
        h = image.getHeight();
        
        output = new PrintWriter(new FileWriter(out));
        output.println("@relation palmbunch\n");
        output.println("@attribute x real");
        output.println("@attribute y real");
        output.println("\n@data");
        
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                Color c = new Color(image.getRGB(j, i));
                int red = (int)(c.getRed());

                if(red>127){
                    red=255;
                } else red =0;
                
                if (red==255){
                    output.println(i+","+j);
                }
            }
        }
        output.close();
    }
    
}
