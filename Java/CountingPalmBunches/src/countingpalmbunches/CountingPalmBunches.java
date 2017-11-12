/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingpalmbunches;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Alriana
 */
public class CountingPalmBunches extends JFrame{

    /**
     * @param args the command line arguments
     */
    /*
    //GUI
    final JFrame frame;
    final JLabel label;
    final JPanel panel;
    
    
    
    Image input;
    public CountingPalmBunches(){
        frame = new JFrame("Counting Palm Bunches");
        //label = new JLabel("Counting");
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        label = new JLabel(" ");
        panel.add(label, BorderLayout.WEST);
        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);    }
    */
    public static void main(String[] args) throws IOException {
        
        //CountingPalmBunches frame = new CountingPalmBunches();
        //Algoritma
        PrintWriter fo = new PrintWriter(new FileWriter("./Dataset/result.txt"));
        for(int i=0;i<=0;i++){
            String inputFile = "./Dataset/"+i+"/"+i+".jpg";
            System.out.print(inputFile);
            String gray = "./Dataset/"+i+"/Sawit01 - gray.jpg";
            String histo = "./Dataset/"+i+"/Sawit02 - histo.jpg";
            String median = "./Dataset/"+i+"/Sawit03 - median.jpg";
            String biner = "./Dataset/"+i+"/Sawit04 - biner.jpg";
            String dila = "./Dataset/"+i+"/Sawit05 - dila.jpg";
            String ero = "./Dataset/"+i+"/Sawit06 - eros.jpg";
            String label = "./Dataset/"+i+"/Sawit07 - label.jpg";
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
            CCLabelling ccl = new CCLabelling(ero,label,i);
            fo.println(i+": "+ccl.getRes());
        }
        fo.close();
    }
    
}
