package countingpalmbunches;

import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alriana
 */
public class GraphicUI extends javax.swing.JFrame {
    String gInputFile;
    
    String gray;
    String histo;
    String median;
    String biner;
    String dila;
    String ero;
    String arff;
    String label;
    String cluster;
    /**
     * Creates new form GraphicUI
     */
    public GraphicUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        filename = new javax.swing.JTextPane();
        browse = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        body = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Palm Bunch Counter");
        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(0, 51, 51));
        setPreferredSize(new java.awt.Dimension(1366, 720));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        header.setBackground(new java.awt.Color(0, 51, 51));
        header.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        header.setForeground(new java.awt.Color(51, 51, 51));

        filename.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        filename.setText("Browse");
        filename.setName("filename"); // NOI18N

        browse.setText("Browse");
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });

        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(filename)
                .addGap(18, 18, 18)
                .addComponent(browse)
                .addGap(18, 18, 18)
                .addComponent(clear)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(browse)
                            .addComponent(clear))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(filename))
                .addContainerGap())
        );

        body.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
        body.setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Graylevel");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, -1, -1));

        jButton2.setText("HSV");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, -1, -1));

        jButton3.setText("Graylevel + HSV");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, -1, -1));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("No Image");
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 400, 360));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Hasil Penghitungan"));

        jLabel2.setText("No Result");

        jLabel5.setText("No Result");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addContainerGap(553, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 630, 150));

        body.add(jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Result"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("No Image");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 650, 490));
        jLabel3.getAccessibleContext().setAccessibleName("labelImage");

        body.add(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("JFrame");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser("E:/Alriana/Project/Image Processing/Image-Processing/Java/CountingPalmBunches/Dataset");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image", "jpg", "png", "jpeg");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filename.setText(f.getAbsolutePath());
        
        System.out.println(filename.getText());
        showInput();
    }//GEN-LAST:event_browseActionPerformed
    
    //Clear
    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        filename.setText("");
        jLabel2.setText("Result: ");
        jLabel5.setText("Timer: ");
        jLabel6.setText("");
        //input
        jLabel1.setIcon(null);
        jLabel1.setText("No Image");
        //result
        jLabel3.setIcon(null);
        jLabel3.setText("No Image");
    }//GEN-LAST:event_clearActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
         setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }//GEN-LAST:event_formWindowOpened
    
    //Graylevel
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        //Image Processing
        long start = System.currentTimeMillis();
        gInputFile = filename.getText();
        gInputFile = gInputFile.substring(0,gInputFile.length()-4);
        String numFile = gInputFile.substring(gInputFile.length()-2);
        String tm = numFile.substring(0, 1);
        if(tm.equals("\\")) numFile = numFile.substring(1);

        String inputFile = "./Dataset/"+numFile+"/"+numFile+".jpg";
        System.out.print(inputFile);
        String gray = "./Dataset/"+numFile+"/"+numFile+"-01-gray.jpg";
        String histo = "./Dataset/"+numFile+"/"+numFile+"-02-histo.jpg";
        String biner = "./Dataset/"+numFile+"/"+numFile+"-03-biner.jpg";
        String median = "./Dataset/"+numFile+"/"+numFile+"-04-median.jpg";
        String dila = "./Dataset/"+numFile+"/"+numFile+"-05-dila.jpg";
        String ero = "./Dataset/"+numFile+"/"+numFile+"-06-eros.jpg";
        String label = "./Dataset/"+numFile+"/"+numFile+"-07-label.jpg";
            
        GrayLevel obj = new GrayLevel(inputFile,gray);
        HistogramEqualization obj1 = new HistogramEqualization(gray,histo);
        Binarization obj2 = new Binarization(histo,biner);
        MedianFiltering obj3 = new MedianFiltering(biner,median);
        Dilation d = new Dilation(median,dila);
        Erosion e = new Erosion(dila,ero);
        CCLabelling ccl = new CCLabelling(ero,label,Integer.parseInt(numFile));
        
        long elapsedTime = System.currentTimeMillis() - start;
        double sec = (elapsedTime/1000F);

        //Result
        jLabel2.setText("Result: "+": "+ccl.getRes()+" palm bunch(s)");
        jLabel5.setText("Timer: "+String.format( "%.3f", sec )+" s");
        
        //CCL
        ImageIcon icon= new ImageIcon(label);
        Image image = icon.getImage().getScaledInstance(icon.getIconWidth()/3*2, icon.getIconHeight()/3*2, Image.SCALE_SMOOTH);
        jLabel3.setIcon(new ImageIcon(image));
        jLabel3.setText("");
        
    }//GEN-LAST:event_jButton1ActionPerformed

    //HSV
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        //Image Processing
        long start = System.currentTimeMillis();
        gInputFile = filename.getText();
        gInputFile = gInputFile.substring(0,gInputFile.length()-4);
        String numFile = gInputFile.substring(gInputFile.length()-2);
        String tm = numFile.substring(0, 1);
        if(tm.equals("\\")) numFile = numFile.substring(1);

        String inputFile = "./Dataset/"+numFile+"/"+numFile+".jpg";
        System.out.print(inputFile);
        String hbiner = "./Dataset/"+numFile+"/"+numFile+"-03-hbiner.jpg";
        String median = "./Dataset/"+numFile+"/"+numFile+"-04-median.jpg";
        String dila = "./Dataset/"+numFile+"/"+numFile+"-05-dila.jpg";
        String ero = "./Dataset/"+numFile+"/"+numFile+"-06-eros.jpg";
        String label = "./Dataset/"+numFile+"/"+numFile+"-07-label.jpg";
            
        HSVBinarization objs = new HSVBinarization(inputFile,hbiner);
        MedianFiltering obj3 = new MedianFiltering(hbiner,median);
        Dilation d = new Dilation(median,dila);
        Erosion e = new Erosion(dila,ero);
        CCLabelling ccl = new CCLabelling(ero,label,Integer.parseInt(numFile));
        
        long elapsedTime = System.currentTimeMillis() - start;
        double sec = (elapsedTime/1000F);

        //Result
        jLabel2.setText("Result: "+": "+ccl.getRes()+" palm bunch(s)");
        jLabel5.setText("Timer: "+String.format( "%.3f", sec )+" s");
        
        //CCL
        ImageIcon icon= new ImageIcon(label);
        Image image = icon.getImage().getScaledInstance(icon.getIconWidth()/2, icon.getIconHeight()/2, Image.SCALE_SMOOTH);
        jLabel3.setIcon(new ImageIcon(image));
        jLabel3.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed
    
    //Graylevel+HSV
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        //Image Processing
        long start = System.currentTimeMillis();
        gInputFile = filename.getText();
        gInputFile = gInputFile.substring(0,gInputFile.length()-4);
        String numFile = gInputFile.substring(gInputFile.length()-2);
        String tm = numFile.substring(0, 1);
        if(tm.equals("\\")) numFile = numFile.substring(1);

        String inputFile = "./Dataset/"+numFile+"/"+numFile+".jpg";
        System.out.print(inputFile);
        String gray = "./Dataset/"+numFile+"/"+numFile+"-01-gray.jpg";
        String histo = "./Dataset/"+numFile+"/"+numFile+"-02-histo.jpg";
        String biner = "./Dataset/"+numFile+"/"+numFile+"-03-biner.jpg";
        String hbiner = "./Dataset/"+numFile+"/"+numFile+"-03-hbiner.jpg";
        String xbiner = "./Dataset/"+numFile+"/"+numFile+"-03-xbiner.jpg";
        String median = "./Dataset/"+numFile+"/"+numFile+"-04-median.jpg";
        String dila = "./Dataset/"+numFile+"/"+numFile+"-05-dila.jpg";
        String ero = "./Dataset/"+numFile+"/"+numFile+"-06-eros.jpg";
        String label = "./Dataset/"+numFile+"/"+numFile+"-07-label.jpg";
            
        GrayLevel obj = new GrayLevel(inputFile,gray);
        HistogramEqualization obj1 = new HistogramEqualization(gray,histo);
        Binarization obj2 = new Binarization(histo,biner);
        HSVBinarization objs = new HSVBinarization(inputFile,hbiner);
        ImageSuperimpose add = new ImageSuperimpose(biner, hbiner, xbiner);
        MedianFiltering obj3 = new MedianFiltering(xbiner,median);
        Dilation d = new Dilation(median,dila);
        Erosion e = new Erosion(dila,ero);
        CCLabelling ccl = new CCLabelling(ero,label,Integer.parseInt(numFile));
        
        long elapsedTime = System.currentTimeMillis() - start;
        double sec = (elapsedTime/1000F);

        //Result
        jLabel2.setText("Result: "+": "+ccl.getRes()+" palm bunch(s)");
        jLabel5.setText("Timer: "+String.format( "%.3f", sec )+" s");
        
        //CCL
        ImageIcon icon= new ImageIcon(label);
        Image image = icon.getImage().getScaledInstance(icon.getIconWidth()/2, icon.getIconHeight()/2, Image.SCALE_SMOOTH);
        jLabel3.setIcon(new ImageIcon(image));
        jLabel3.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    void start() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphicUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphicUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphicUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphicUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphicUI().setVisible(true);
            }
        });
    }
    
    public void showInput(){
        String name = filename.getText();

        ImageIcon icon= new ImageIcon(name);
        Image image = icon.getImage().getScaledInstance(icon.getIconWidth()/2, icon.getIconHeight()/2, Image.SCALE_SMOOTH);
        jLabel1.setIcon(new ImageIcon(image));
        jLabel1.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JButton browse;
    private javax.swing.JButton clear;
    private javax.swing.JTextPane filename;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
