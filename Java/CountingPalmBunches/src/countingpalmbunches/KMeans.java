/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingpalmbunches;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.imageio.ImageIO;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
/**
 *
 * @author Alriana
 */
public class KMeans {
    int w,h,picsize;
    BufferedImage image;
    Graphics inputGD;
    PrintWriter fo;
    
    public KMeans(String fileName, String inImage, String outImage, String out) {
        try{
            System.out.println(".");
            File input = new File(inImage);
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            picsize = w*h;
            //System.out.println(w+"x"+h);
            
            inputGD = image.getGraphics();
            inputGD.setColor(Color.YELLOW);
        
            Instances data;
            Instances centroids;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            data = new Instances(reader);
            reader.close();
            
            fo = new PrintWriter(new FileWriter(out));
            SimpleKMeans SK = new SimpleKMeans();
            SK.setPreserveInstancesOrder(false);
            SK.setNumClusters(20);
            SK.buildClusterer(data);
            
            //System.out.println(SK.toString());
            
            ClusterEvaluation c= new ClusterEvaluation();
            c.setClusterer(SK);
            c.evaluateClusterer(data);
            //System.out.println(c.clusterResultsToString());
            //System.out.println(SK.getClusterCentroids());
            centroids = SK.getClusterCentroids();
            
            fo.println(c.clusterResultsToString());
            for ( int i = 0; i < centroids.numInstances(); i++ ) {
                Instance inst = centroids.instance( i );
                
                Double x = inst.value( 0 );
                Double y = inst.value( 1 );
                //System.out.print(i+": ");
                fo.println( i + " x:" + x.intValue() +", y: "+ y.intValue() );
                BufferedImage bmp = createBitmap(x.intValue(),y.intValue());
                
            }
            inputGD.dispose();

            //fo.println(SK.getClusterCentroids().toString());
            fo.close();
            
            File output = new File(outImage);
            ImageIO.write(image, "jpg", output);
        }
        catch(Exception e){
            System.out.println( e );
        }

    }
    
    private BufferedImage createBitmap(int x, int y){
        //System.out.println(x+"x"+y);
        
        BufferedImage bmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int a=x-25;
        int b=y-25;
        int d=50;
        //System.out.println(a+" "+ b+" "+ c+" "+ d);
        for(int i=0;i<5;i++){
            if(x-25 <= 70 || w-x<=70) d= x-25;
            if(y-25 <= 70 || h-y<=70) d= x-25;
            inputGD.drawOval(y-i, x-i, 50+i*2, 50+i*2);
            inputGD.setColor(Color.YELLOW);
        }         
            
        return bmp;
    }
   
}

