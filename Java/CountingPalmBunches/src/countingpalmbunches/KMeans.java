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
import static java.lang.Math.pow;
import java.util.Arrays;
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
    int oval;
    double[] minX,minY;
    double[] maxX,maxY;
    
    public KMeans(String fileName, String inImage, String outImage, String out) {
        try{
            //System.out.println(".");
            File input = new File(inImage);
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            picsize = w*h;
            //System.out.println(w+"x"+h);
            int num = 10;
            inputGD = image.getGraphics();
            inputGD.setColor(Color.YELLOW);
        
            Instances data;
            Instances centroids,std,member;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            data = new Instances(reader);
            reader.close();
            
            fo = new PrintWriter(new FileWriter(out));
            SimpleKMeans SK = new SimpleKMeans();
            SK.setPreserveInstancesOrder(true);
            SK.setNumClusters(num);
            SK.setDisplayStdDevs(true);
            SK.buildClusterer(data);
            
            ClusterEvaluation c= new ClusterEvaluation();
            c.setClusterer(SK);
            c.evaluateClusterer(data);
            
            int[] assignments = SK.getAssignments();
            
            minX = new double[num];
            Arrays.fill(minX,new Double(w+1));
            minY = new double[num];
            Arrays.fill(minY,new Double(h+1));
            maxX = new double[num];
            maxY = new double[num];
            
            for ( int i = 0; i < assignments.length; i++ ) {
                double x = data.instance(i).value(0);
                double y = data.instance(i).value(1);
                int cidx= assignments[i];
                //System.out.println(minX[cidx]);
                if(x<minX[cidx]){
                    minX[cidx]=x;
                }
                if(y<minY[cidx]){
                    minY[cidx]=y;
                }
                if(x>maxX[cidx]){
                    maxX[cidx]=x;
                }
                if(y>maxY[cidx]){
                    maxY[cidx]=y;
                }
            }
            
            oval = 0;
            std= SK.getClusterStandardDevs();
            centroids = SK.getClusterCentroids();
            double[] clusterSize = SK.getClusterSizes();
            
            int[] dA= new int[num];
            int[] dB= new int[num];
            int[] area= new int[num];
            double[] dens= new double[num];
            int sd=0,ss=0;
            for ( int i = 0; i < num; i++ ) {
                dA[i] = (int) (maxX[i]-minX[i]);
                dB[i] = (int) (maxY[i]-minY[i]);
                area[i]= dA[i]*dB[i];
                dens[i] = clusterSize[i]/area[i];
                sd+=dens[i]*100;
                ss+=clusterSize[i];
            }
            int rd=sd/num;
            int rs=ss/num;
            System.out.println("dens:"+rd+" size:"+rs);
            
            //fo.println(c.clusterResultsToString());
            for ( int i = 0; i < centroids.numInstances(); i++ ) {
                Instance inst = centroids.instance( i );
                Instance s= std.instance(i);
                //System.out.println((int)clusterSize[i]);
                
                //System.out.println((int)(dens*100));
                //System.out.println((int)clusterSize[i] +" | "+ dens[i]*100);
                
                if ( dens[i]*100>=rd || clusterSize[i]>=rs){
                    Double x = inst.value( 0 );
                    Double y = inst.value( 1 );
                    
                    oval++;
                    BufferedImage bmp = createBitmap(x.intValue(),y.intValue(), dA[i], dB[i]);

                }
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
    
    private BufferedImage createBitmap(int x, int y, int da, int db){
        //System.out.println(x+"x"+y);
        
        BufferedImage bmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int a=x-25;
        int b=y-25;
        //System.out.println(a+" "+ b+" "+ c+" "+ d);
        for(int i=0;i<5;i++){
            inputGD.drawOval(y-i-db/2, x-i-da/2, db+i*2, da+i*2);
            
            inputGD.setColor(Color.YELLOW);
        }         
            
        return bmp;
    }
    
    public int getRes(){
        return this.oval;
    }
   
}

