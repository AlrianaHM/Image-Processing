/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingpalmbunches;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author Alriana
 */
public class Binarization {
    BufferedImage image;
    int w,h, picsize;
    int[] histogram;
    public Binarization(String in, String out){
        try{
            
            System.out.print(".");
            File input = new File(in);
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            picsize = h*w;
            int[] arr = new int[picsize];
            histogram = new int[256];
            int x=0;
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed());
                    arr[x] = red;
                    histogram[red]++;
                    x++;
                }
            }
            int median;
            Arrays.sort(arr);
            if (arr.length % 2 == 0)
                median = (arr[arr.length/2] + arr[arr.length/2 - 1])/2;
            else
                median = arr[arr.length/2];
            //System.out.println(median);
            
            int th = getOtsu();
            //System.out.println(th);
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed());

                    if(red>90) {
                        Color newColor = new Color(0,0,0);
                        image.setRGB(j, i, newColor.getRGB());
                    }
                    else {
                        Color newColor = new Color(255,255,255);
                        image.setRGB(j, i, newColor.getRGB());
                    }
                    
                }
            }
            File output = new File(out);
            ImageIO.write(image, "jpg", output);
            System.out.print(".");
            } catch (Exception e) {}
    }
    
    public int getOtsu(){
        int t=0;
        float sum=0;
        float sumB=0;
        float varMax=0;
        int wb=0;
        int wf=0;
        
        for(int i=0; i<256; i++) sum+=i*histogram[i];
        
        for(int i=0; i<256; i++) {
            wb+=histogram[i];
            if(wb==0) continue;
            wf = picsize -wb;
            
            if(wf == 0) break;
            
            sumB += (float)(i*histogram[i]);
            float mb = sumB/wb;
            float mf = (sum-sumB)/wf;
            
            float varBetween = (float) wb*(float) wf *(mb-mf)*(mb-mf);
            
            if(varBetween > varMax){
                varMax = varBetween;
                t=i;
            }
        }
                
        return t;
    }
}
