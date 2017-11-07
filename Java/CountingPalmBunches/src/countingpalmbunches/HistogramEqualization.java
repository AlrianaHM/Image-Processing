/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingpalmbunches;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
/**
 *
 * @author Alriana
 */
public class HistogramEqualization {
    
    BufferedImage image;
    int w,h;
    int[] histogram = new int[256];
    int[] chistogram = new int[256];
    
    public HistogramEqualization(String in, String out){
        try{
            System.out.println("HE Start");
            File input = new File(in);
            image = ImageIO.read(input);
            
            BufferedImage nimage = new BufferedImage(image.getWidth(), image.getHeight(),
                         BufferedImage.TYPE_BYTE_GRAY);
            WritableRaster wr = image.getRaster();
            WritableRaster er = nimage.getRaster();
            w = wr.getWidth();
            h = wr.getHeight();
            int t= w*h;

            for(int i=0; i<w;i++){
                for(int j=0; j<h;j++){
                    
                    histogram[wr.getSample(i,j,0)]++;
                }
            }

            chistogram[0]=histogram[0];
            for(int i=1; i<256;i++){
                chistogram[i]= chistogram[i-1]+ histogram[i];
            }
            
            float[] arr= new float[256];
            for(int i=0; i<256;i++){
                arr[i]= (float)((chistogram[i]*255.0)/(float)t);
            }
            
            for(int i=0; i<w;i++){
                for(int j=0; j<h;j++){
                    int nval = (int) arr[wr.getSample(i, j, 0)];
                    er.setSample(i, j, 0, nval);
                }
            }
            
            nimage.setData(er);
            File output = new File(out);
            ImageIO.write(nimage, "jpg", output);
            System.out.println("HE End");

        }catch(Exception e){
            
        }
    }
    
}
