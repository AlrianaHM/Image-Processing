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
        
        //CountingPalmBunches frame = new CountingPalmBunches();
        //Algoritma
        ///*
        PrintWriter fo = new PrintWriter(new FileWriter("./Dataset/result.txt"));
        for(int i=1;i<=40;i++){
            String inputFile = "./Dataset/"+i+"/"+i+".jpg";
            System.out.print(inputFile);
            String gray = "./Dataset/"+i+"/Sawit01 - gray.jpg";
            String histo = "./Dataset/"+i+"/Sawit02 - histo.jpg";
            String median = "./Dataset/"+i+"/Sawit03 - median.jpg";
            String biner = "./Dataset/"+i+"/Sawit04 - biner.jpg";
            String dila = "./Dataset/"+i+"/Sawit05 - dila.jpg";
            String ero = "./Dataset/"+i+"/Sawit06 - eros.jpg";
            String arff = "./Dataset/arrf/"+i+".arff";
            String label = "./Dataset/"+i+"/Sawit07 - label.jpg";
            String cluster = "./Dataset/"+i+"/Sawit08 - cluster.jpg";
            
            String res = "./Dataset/cluster/"+i+".txt";
            GrayLevel obj = new GrayLevel(inputFile,gray);
            HistogramEqualization obj1 = new HistogramEqualization(gray,histo);
            MedianFiltering obj3 = new MedianFiltering(histo,median);
            Binarization obj2 = new Binarization(median,biner);

            
            //CannyEdges ce = new CannyEdges();
            //BufferedImage in = ImageIO.read(new File("./Dataset/6ib.jpg")); 
            //NewCanny ced = new NewCanny(in, 120, 140); 
            //ImageIO.write(ced.filter(), "png", new File("./Dataset/6cni.png"));

            Dilation d = new Dilation(biner,dila);
            Erosion e = new Erosion(dila,ero);
            ArffCreator arfffile = new ArffCreator(ero,arff);
            CCLabelling ccl = new CCLabelling(ero,label,i);
            fo.println(i+": "+ccl.getRes());
            KMeans km = new KMeans(arff,ero,cluster,res);
        }
        fo.close();//*/
    }
    
}
