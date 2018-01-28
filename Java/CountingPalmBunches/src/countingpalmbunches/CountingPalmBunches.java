/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingpalmbunches;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JPanel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alriana
 */
public class CountingPalmBunches extends JPanel{

    /**
     * @param args the command line arguments
     */
    
    
    //*/
    public static void main(String[] args) throws IOException {
        /*
        GraphicUI ui = new GraphicUI();
        ui.start();
        */
        //Algoritma
        
        PrintWriter fo = new PrintWriter(new FileWriter("./Dataset/result.txt"));
        for(int i=24;i<=34;i++){
            long start = System.currentTimeMillis();
            String inputFile = "./Dataset/"+i+"/"+i+".jpg";
            System.out.print(inputFile);
            String gray = "./Dataset/"+i+"/"+i+"-01-gray.jpg";
            String histo = "./Dataset/"+i+"/"+i+"-02-histo.jpg";
            String biner = "./Dataset/"+i+"/"+i+"03 -biner.jpg";
            String median = "./Dataset/"+i+"/"+i+"04-median.jpg";
            String dila = "./Dataset/"+i+"/"+i+"05 -dila.jpg";
            String ero = "./Dataset/"+i+"/"+i+"06 -eros.jpg";
            String arff = "./Dataset/arrf/"+i+".arff";
            String label = "./Dataset/"+i+"/"+i+"07-label.jpg";
            String cluster = "./Dataset/"+i+"/"+i+"08-cluster.jpg";
            
            String res = "./Dataset/cluster/"+i+".txt";
            GrayLevel obj = new GrayLevel(inputFile,gray);
            HistogramEqualization obj1 = new HistogramEqualization(gray,histo);
            
            Binarization obj2 = new Binarization(histo,biner);
            MedianFiltering obj3 = new MedianFiltering(biner,median);

            Dilation d = new Dilation(median,dila);
            Erosion e = new Erosion(dila,ero);
            ArffCreator arfffile = new ArffCreator(ero,arff);
            CCLabelling ccl = new CCLabelling(ero,label,i);
            fo.print(i+": "+ccl.getRes());
            KMeans km = new KMeans(arff,ero,cluster,res);
            long elapsedTime = System.currentTimeMillis() - start;
            double sec = (elapsedTime/1000F);
            fo.println(" "+String.format( "%.3f", sec ) +" s");
            System.out.println(" "+String.format( "%.3f", sec )+" s");
        }
        fo.close();
    }
    
}
