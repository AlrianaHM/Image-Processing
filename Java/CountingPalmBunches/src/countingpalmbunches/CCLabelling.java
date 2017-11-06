/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingpalmbunches;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author Alriana
 * https://github.com/klonikar/connected-components-labeling/
 */
public class CCLabelling {
    BufferedImage image;
    Graphics inputGD;
    
    int w,h,picsize;
    int background;
    int[][] piksel;
    String dir;
    PrintWriter fo;
    int oval;
    public CCLabelling(String in, String out){
        try{
            dir = in.substring(0, 12);
            System.out.println("Labelling Start");
            File input = new File(in);
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            picsize = w*h;
            int bg = 0xFF000000;
            oval=0;            
            //System.out.println("bg: " + bg);
            Map<Integer, BufferedImage> components = process(image, bg);
            for(Integer c : components.keySet()) {
    			//ImageIO.write(components.get(c), format, new File(getBaseFileName(args[0]) + "-component-" + c + "."  + format));
            }
            System.out.println("Oval created= "+ oval);
            File output = new File(out);
            ImageIO.write(image, "jpg", output);
            System.out.println("Labelling End");
        }catch(Exception e){
            
        }
    }
    
    public int getRes(){
        return this.oval;
    }
    
    public Map<Integer, BufferedImage> process(BufferedImage image, int bgColor) throws IOException{
        
        w = image.getWidth();
        h = image.getHeight();
        background = bgColor;
        picsize = w*h;
        piksel= new int[w][h];

        Map<Integer, List<Pixel>> patterns = find();
        Map<Integer, BufferedImage> images = new HashMap<Integer, BufferedImage>();

        inputGD = image.getGraphics();
        inputGD.setColor(Color.YELLOW);
        
        fo = new PrintWriter(new FileWriter(dir+"oval.txt"));
        for(Integer id : patterns.keySet()){
            BufferedImage bmp = createBitmap(patterns.get(id));
            images.put(id, bmp);
        }
        fo.close();

        inputGD.dispose();
        return images;
    }
    
    private Map<Integer, List<Pixel>> find(){
        int labelCount =1;
        Map<Integer, Label> allLabels = new HashMap<Integer, Label>();
        
        for(int i=0; i<h;i++){
            for(int j=0;j<w;j++){
                Pixel currentP;
                if(image.getRGB(j,i) <= 0xFF808080){
                    currentP = new Pixel(j,i,0xFF000000);
                } else {
                    currentP = new Pixel(j,i,0xFFFFFFFF);
                }
                
                
                if(currentP.color == background){
                    continue;
                }
                
                List<Integer> neighboringLabels = getNeighboringLabels(currentP);
                int currentL;
                
                if(neighboringLabels.isEmpty()){
                    currentL = labelCount;
                    allLabels.put(currentL, new Label(currentL));
                    labelCount++;
                    
                } else{
                    currentL = min(neighboringLabels, allLabels);
                    Label root = allLabels.get(currentL).GetRoot();
                        
                    for(Integer neighbor : neighboringLabels){
                        if(root.name != allLabels.get(neighbor).GetRoot().name){
                            //System.out.println(allLabels.get(currentL).name);
                            allLabels.get(neighbor).Join(allLabels.get(currentL));
                        }
                    }
                }
                piksel[j][i] = currentL;
            }
        }
        Map<Integer, List<Pixel>> patterns = aggregatePatterns(allLabels);
        //System.out.println(allLabels.size());
        /*for(int i=1; i<= allLabels.size();i++){
            System.out.println(i+"x= "+allLabels.get(i).name);
        }*/
        return patterns;
    }
    
    private List<Integer> getNeighboringLabels(Pixel p){
        List<Integer> neighboringLabels = new ArrayList<Integer>();
        
        for(int i=p.y-1; i<=p.y+2 && i<h-1; i++){
            for(int j=p.x-1; j<=p.x+2 && j<w-1; j++){
                if(i>-1 && j>-1 && piksel[j][i] !=0){
                    neighboringLabels.add(piksel[j][i]);
                }
            }
        }
        
        return neighboringLabels;
    }
    
    private Map<Integer, List<Pixel>> aggregatePatterns(Map<Integer, Label> allLabels){
        Map<Integer, List<Pixel>> patterns = new HashMap<Integer, List<Pixel>>();
        
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                int patternNumber = piksel[j][i];
                //System.out.println(patternNumber);
                if(patternNumber != 0){
                    patternNumber = allLabels.get(patternNumber).GetRoot().name;
                    //System.out.println(patternNumber);
                    if(!patterns.containsKey(patternNumber)){
                        patterns.put(patternNumber, new ArrayList<Pixel>());
                    }

                    patterns.get(patternNumber).add(new Pixel(j, i, image.getRGB(j, i)));
                }
            }
        }
        
        return patterns;
    }
    
    private BufferedImage createBitmap(List<Pixel> pattern){
        int minX = min(pattern, true);
        int maxX = max(pattern, true);
        
        int minY = min(pattern, false);
        int maxY = max(pattern, false);
        
        int width = maxX + 1 - minX;
        int height = maxY + 1 - minY;
        
        BufferedImage bmp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        for( Pixel pix: pattern){
            bmp.setRGB(pix.x - minX, pix.y - minY, pix.color);
        }
        
        int a = maxX-minX;
        int b = maxY-minY;
        if(a>70 && b>70) {
            
            fo.println("x="+a+" y="+b);
            for(int i=0;i<10;i++)
                inputGD.drawOval(minX-i, minY-i, maxX-minX+i*2, maxY-minY+i*2);
            
            
            oval++;
        }

        return bmp;
    }
    
    private static int min(List<Integer> neighboringLabels, Map<Integer, Label> allLabels) {
    	if(neighboringLabels.isEmpty())
    		return 0; // TODO: is 0 appropriate for empty list
    	
    	int ret = allLabels.get(neighboringLabels.get(0)).GetRoot().name;
    	for(Integer n : neighboringLabels) {
    		int curVal = allLabels.get(n).GetRoot().name;
    		ret = (ret < curVal ? ret : curVal);
    	}
    	return ret;
    }
    
    private static int min(List<Pixel> pattern, boolean xOrY) {
    	if(pattern.isEmpty())
    		return 0; // TODO: is 0 appropriate for empty list
    	
    	int ret = (xOrY ? pattern.get(0).x : pattern.get(0).y);
    	for(Pixel p : pattern) {
    		int curVal = (xOrY ? p.x : p.y);
    		ret = (ret < curVal ? ret : curVal);
    	}
    	return ret;
    }

    private static int max(List<Pixel> pattern, boolean xOrY) {
    	if(pattern.isEmpty())
    		return 0; // TODO: is 0 appropriate for empty list
    	
    	int ret = (xOrY ? pattern.get(0).x : pattern.get(0).y);
    	for(Pixel p : pattern) {
    		int curVal = (xOrY ? p.x : p.y);
    		ret = (ret > curVal ? ret : curVal);
    	}
    	return ret;
}
}
