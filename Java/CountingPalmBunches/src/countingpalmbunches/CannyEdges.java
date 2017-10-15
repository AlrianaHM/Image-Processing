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
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *
 * @author Alriana
 */
public class CannyEdges {
    
    BufferedImage image;
    
    private BufferedImage edgesImage;
    int w,h,picsize;
    
    //for gaussian filtering
    int gaussianKernelWidth=16;
    float gaussianKernelRadius=2f;
    private final static float GAUSSIAN_CUT_OFF = 0.005f;
    private final static float MAGNITUDE_SCALE = 100F;
    private final static float MAGNITUDE_LIMIT = 1000F;
    private final static int MAGNITUDE_MAX = (int) (MAGNITUDE_SCALE * MAGNITUDE_LIMIT);

    
    //for gradien
    float[] convX;
    float[] convY;
    float[] gradientX;
    float[] gradientY;
    int[] data;
    int[] magnitude;
    
    //for non-maximun suppression
    
    // For double thresholding
    float lowT=5f, highT=10f;
    
    public CannyEdges(){
        try{
            System.out.println("Canny edges Start");
            File input = new File("./Dataset/6mf.jpg");
            image = ImageIO.read(input);
            w = image.getWidth();
            h = image.getHeight();
            picsize = w*h;
            initArrays();
            readLuminance();
            computeGradients(gaussianKernelRadius, gaussianKernelWidth);
            int low = Math.round(lowT * MAGNITUDE_SCALE);
            int high = Math.round( highT * MAGNITUDE_SCALE);
            performHysteresis(low, high);
            thresholdEdges();
            writeEdges(data);
            File output = new File("./Dataset/6canny.jpg");
            ImageIO.write(edgesImage, "jpg", output);
        }catch(Exception e){
            
        }
    }
    
    private void initArrays(){
        if (data == null || picsize != data.length) {
            data = new int[picsize];
            magnitude = new int[picsize];

            convX = new float[picsize];
            convY = new float[picsize];
            gradientX = new float[picsize];
            gradientY = new float[picsize];
        }
    }
    private void readLuminance() {
        int type = image.getType();
        if (type == BufferedImage.TYPE_INT_RGB || type == BufferedImage.TYPE_INT_ARGB) {
            int[] pixels = (int[]) image.getData().getDataElements(0, 0, w, h, null);
            for (int i = 0; i < picsize; i++) {
                int p = pixels[i];
                int r = (p & 0xff0000) >> 16;
                int g = (p & 0xff00) >> 8;
                int b = p & 0xff;
                data[i] = luminance(r, g, b);
            }
        } else if (type == BufferedImage.TYPE_BYTE_GRAY) {
            byte[] pixels = (byte[]) image.getData().getDataElements(0, 0, w, h, null);
            for (int i = 0; i < picsize; i++) {
                    data[i] = (pixels[i] & 0xff);
        }
        } else if (type == BufferedImage.TYPE_USHORT_GRAY) {
            short[] pixels = (short[]) image.getData().getDataElements(0, 0, w, h, null);
            for (int i = 0; i < picsize; i++) {
                data[i] = (pixels[i] & 0xffff) / 256;
            }
        } else if (type == BufferedImage.TYPE_3BYTE_BGR) {
            byte[] pixels = (byte[]) image.getData().getDataElements(0, 0, w, h, null);
            int offset = 0;
            for (int i = 0; i < picsize; i++) {
                int b = pixels[offset++] & 0xff;
                int g = pixels[offset++] & 0xff;
                int r = pixels[offset++] & 0xff;
                data[i] = luminance(r, g, b);
            }
        } else {
            throw new IllegalArgumentException("Unsupported image type: " + type);
        }
    }
    
    private int luminance(float r, float g, float b) {
        return Math.round(0.299f * r + 0.587f * g + 0.114f * b);
    }
    
    private void computeGradients(float kernelRadius, int kernelWidth){
        float kernel[] = new float[kernelWidth];
        float diffKernel[] = new float[kernelWidth];
        int kwidth;
        for (kwidth = 0; kwidth < kernelWidth; kwidth++) {
            float g1 = gaussian(kwidth, kernelRadius);
            if (g1 <= GAUSSIAN_CUT_OFF && kwidth >= 2) break;
            float g2 = gaussian(kwidth - 0.5f, kernelRadius);
            float g3 = gaussian(kwidth + 0.5f, kernelRadius);
            kernel[kwidth] = (g1 + g2 + g3) / 3f / (2f * (float) Math.PI * kernelRadius * kernelRadius);
            diffKernel[kwidth] = g3 - g2;
        }

        int initX = kwidth - 1;
        int maxX = w - (kwidth - 1);
        int initY = w * (kwidth - 1);
        int maxY = w * (h - (kwidth - 1));

        //perform convolution in x and y directions
        for (int x = initX; x < maxX; x++) {
            for (int y = initY; y < maxY; y += w) {
                int index = x + y;
                float sumX = data[index] * kernel[0];
                float sumY = sumX;
                int xOffset = 1;
                int yOffset = w;
                for(; xOffset < kwidth ;) {
                    sumY += kernel[xOffset] * (data[index - yOffset] + data[index + yOffset]);
                    sumX += kernel[xOffset] * (data[index - xOffset] + data[index + xOffset]);
                    yOffset += w;
                    xOffset++;
                }

                convY[index] = sumY;
                convX[index] = sumX;
                System.out.print(convY[index] + " ");
            }
            System.out.println("");
        }

        for (int x = initX; x < maxX; x++) {
            for (int y = initY; y < maxY; y += w) {
                float sum = 0f;
                int index = x + y;
                for (int i = 1; i < kwidth; i++)
                        sum += diffKernel[i] * (convX[index - i] - convY[index + i]);

                gradientX[index] = sum;
            }
        }

        for (int x = kwidth; x < w - kwidth; x++) {
            for (int y = initY; y < maxY; y += w) {
                float sum = 0.0f;
                int index = x + y;
                int yOffset = w;
                for (int i = 1; i < kwidth; i++) {
                    sum += diffKernel[i] * (convX[index - yOffset] - convX[index + yOffset]);
                    yOffset += w;
                }
                gradientY[index] = sum;
            }
        }

        initX = kwidth;
        maxX = w - kwidth;
        initY = w * kwidth;
        maxY = w * (h - kwidth);
        for (int x = initX; x < maxX; x++) {
            for (int y = initY; y < maxY; y += w) {
                int index = x + y;
                int indexN = index - w;
                int indexS = index + w;
                int indexW = index - 1;
                int indexE = index + 1;
                int indexNW = indexN - 1;
                int indexNE = indexN + 1;
                int indexSW = indexS - 1;
                int indexSE = indexS + 1;

                float xGrad = gradientX[index];
                float yGrad = gradientY[index];
                float gradMag = hypot(xGrad, yGrad);

                //perform non-maximal supression
                float nMag = hypot(gradientX[indexN], gradientY[indexN]);
                float sMag = hypot(gradientX[indexS], gradientY[indexS]);
                float wMag = hypot(gradientX[indexW], gradientY[indexW]);
                float eMag = hypot(gradientX[indexE], gradientY[indexE]);
                float neMag = hypot(gradientX[indexNE], gradientY[indexNE]);
                float seMag = hypot(gradientX[indexSE], gradientY[indexSE]);
                float swMag = hypot(gradientX[indexSW], gradientY[indexSW]);
                float nwMag = hypot(gradientX[indexNW], gradientY[indexNW]);
                float tmp;

                if (xGrad * yGrad <= (float) 0 /*(1)*/
                    ? Math.abs(xGrad) >= Math.abs(yGrad) /*(2)*/
                            ? (tmp = Math.abs(xGrad * gradMag)) >= Math.abs(yGrad * neMag - (xGrad + yGrad) * eMag) /*(3)*/
                                    && tmp > Math.abs(yGrad * swMag - (xGrad + yGrad) * wMag) /*(4)*/
                            : (tmp = Math.abs(yGrad * gradMag)) >= Math.abs(xGrad * neMag - (yGrad + xGrad) * nMag) /*(3)*/
                                    && tmp > Math.abs(xGrad * swMag - (yGrad + xGrad) * sMag) /*(4)*/
                    : Math.abs(xGrad) >= Math.abs(yGrad) /*(2)*/
                            ? (tmp = Math.abs(xGrad * gradMag)) >= Math.abs(yGrad * seMag + (xGrad - yGrad) * eMag) /*(3)*/
                                    && tmp > Math.abs(yGrad * nwMag + (xGrad - yGrad) * wMag) /*(4)*/
                            : (tmp = Math.abs(yGrad * gradMag)) >= Math.abs(xGrad * seMag + (yGrad - xGrad) * sMag) /*(3)*/
                                    && tmp > Math.abs(xGrad * nwMag + (yGrad - xGrad) * nMag) /*(4)*/
                    ) {
                    magnitude[index] = gradMag >= MAGNITUDE_LIMIT ? MAGNITUDE_MAX : (int) (MAGNITUDE_SCALE * gradMag);
                    //NOTE: The orientation of the edge is not employed by this
                    //implementation. It is a simple matter to compute it at
                    //this point as: Math.atan2(yGrad, xGrad);
                } else {
                    magnitude[index] = 0;
                }
            }
        }
    }
    
    private float hypot(float x, float y) {
        return (float) Math.hypot(x, y);
    }
    private float gaussian(float x, float sigma) {
        return (float) Math.exp(-(x * x) / (2f * sigma * sigma));
    }
    
    private void performHysteresis(int low, int high) {
        //NOTE: this implementation reuses the data array to store both
        //luminance data from the image, and edge intensity from the processing.
        //This is done for memory efficiency, other implementations may wish
        //to separate these functions.
        Arrays.fill(data, 0);

        int offset = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (data[offset] == 0 && magnitude[offset] >= high) {
                    follow(x, y, offset, low);
                }
                offset++;
            }
        }
    }
    
    private void follow(int x1, int y1, int i1, int threshold) {
        int x0 = x1 == 0 ? x1 : x1 - 1;
        int x2 = x1 == w - 1 ? x1 : x1 + 1;
        int y0 = y1 == 0 ? y1 : y1 - 1;
        int y2 = y1 == h -1 ? y1 : y1 + 1;

        data[i1] = magnitude[i1];
        for (int x = x0; x <= x2; x++) {
            for (int y = y0; y <= y2; y++) {
                int i2 = x + y * w;
                if ((y != y1 || x != x1)
                    && data[i2] == 0 
                    && magnitude[i2] >= threshold) {
                    follow(x, y, i2, threshold);
                    return;
                }
            }
        }
    }
    private void thresholdEdges() {
        for (int i = 0; i < picsize; i++) {
            data[i] = data[i] > 0 ? -1 : 0x00000000;
        }
    }
    private void writeEdges(int pixels[]) {
        if (edgesImage == null) {
            edgesImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        }
        edgesImage.getWritableTile(0, 0).setDataElements(0, 0, w, h, pixels);
    }
}
