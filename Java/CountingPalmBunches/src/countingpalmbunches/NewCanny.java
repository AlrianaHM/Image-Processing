/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
LIRE
 */
package countingpalmbunches;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *
 * @author Alriana
 */
public class NewCanny {
    static ConvolveOp gaussian = new ConvolveOp(new Kernel(5, 5, makeGaussianKernel(5, 1.4f)));
    static ColorConvertOp grayscale = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
 
    int[] tmp255 = {255};
    int[] tmp128 = {128};
    int[] tmp000 = {0};
    int[] tmpPixel = {0};
    // double thresholds for Canny edge detector
    double thresholdLow = 60, thresholdHigh = 100;
 
    BufferedImage bimg;
 
     /**
      * Create a Canny Edge Detector for the given image. Set the thresholds yourself. Use {@link CannyEdgeDetector#filter} to create
      * the edge image.
      * @param image the input image.
      * @param thresholdHigh higher of the thresholds
      * @param thresholdLow lower of the thresholds
      */
    public NewCanny(BufferedImage image, double thresholdHigh, double thresholdLow) {
        this.bimg = image;
        this.thresholdHigh = thresholdHigh;
        this.thresholdLow = thresholdLow;
    }
 
     /**
      * Create a Canny Edge Detector for the given image. Set the thresholds yourself. Use {@link CannyEdgeDetector#filter} to create
      * the edge image.
      * @param bimg
      */
    public NewCanny(BufferedImage bimg) {
        this.bimg = bimg;
    }
 
    /**
     * Returns the edge image in grayscale. Edges are black (int 0) all other pixels are white (int 255)
     * @return the filtered image.
     */
    public BufferedImage filter() {
        // All for Canny Edge ...
        BufferedImage gray;
        double[][] gx, gy;
        double[][] gd, gm;
        int[][] gdRounded;
 
        // doing canny edge detection first:
        // filter images:
        gray = grayscale.filter(bimg, null);
        gray = gaussian.filter(gray, null);
        gx = sobelFilterX(gray);
        gy = sobelFilterY(gray);
        int width = gray.getWidth();
        int height = gray.getHeight();
        gd = new double[width][height];
        gm = new double[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // setting gradient magnitude and gradient direction
                if (gx[x][y] != 0) {
                    gd[x][y] = Math.atan(gy[x][y] / gx[x][y]);
                } else {
                    gd[x][y] = Math.PI / 2d;
                }
                gm[x][y] = Math.hypot(gy[x][y], gx[x][y]);
            }
        }
        // Non-maximum suppression
        for (int x = 0; x < width; x++) {
            gray.getRaster().setPixel(x, 0, new int[]{255});
            gray.getRaster().setPixel(x, height - 1, new int[]{255});
        }
        for (int y = 0; y < height; y++) {
            gray.getRaster().setPixel(0, y, new int[]{255});
            gray.getRaster().setPixel(width - 1, y, new int[]{255});
        }
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                if (gd[x][y] < (Math.PI / 8d) && gd[x][y] >= (-Math.PI / 8d)) {
                    if (gm[x][y] > gm[x + 1][y] && gm[x][y] > gm[x - 1][y])
                        setPixel(x, y, gray, gm[x][y]);
                    else
                        gray.getRaster().setPixel(x, y, tmp255);
                } else if (gd[x][y] < (3d * Math.PI / 8d) && gd[x][y] >= (Math.PI / 8d)) {
                    if (gm[x][y] > gm[x - 1][y - 1] && gm[x][y] > gm[x - 1][y - 1])
                        setPixel(x, y, gray, gm[x][y]);
                    else
                        gray.getRaster().setPixel(x, y, tmp255);
                } else if (gd[x][y] < (-3d * Math.PI / 8d) || gd[x][y] >= (3d * Math.PI / 8d)) {
                    if (gm[x][y] > gm[x][y + 1] && gm[x][y] > gm[x][y + 1])
                        setPixel(x, y, gray, gm[x][y]);
                    else
                        gray.getRaster().setPixel(x, y, tmp255);
                } else if (gd[x][y] < (-Math.PI / 8d) && gd[x][y] >= (-3d * Math.PI / 8d)) {
                    if (gm[x][y] > gm[x + 1][y - 1] && gm[x][y] > gm[x - 1][y + 1])
                        setPixel(x, y, gray, gm[x][y]);
                    else
                        gray.getRaster().setPixel(x, y, tmp255);
                } else {
                    gray.getRaster().setPixel(x, y, tmp255);
                }
            }
        }
        // hysteresis ... walk along lines of strong pixels and make the weak ones strong.
        int[] tmp = {0};
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                if (gray.getRaster().getPixel(x, y, tmp)[0] < 50) {
                    // It's a strong pixel, lets find the neighbouring weak ones.
                    trackWeakOnes(x, y, gray);
                }
            }
        }
        // removing the single weak pixels.
        for (int x = 2; x < width - 2; x++) {
            for (int y = 2; y < height - 2; y++) {
                if (gray.getRaster().getPixel(x, y, tmp)[0] > 50) {
                    gray.getRaster().setPixel(x, y, tmp255);
                }
            }
        }
        return gray;
    }
 
    /**
     * Recursive tracking of weak points.
     *
     * @param x
     * @param y
     * @param gray
     */
    private void trackWeakOnes(int x, int y, BufferedImage gray) {
        for (int xx = x - 1; xx <= x + 1; xx++)
            for (int yy = y - 1; yy <= y + 1; yy++) {
                if (isWeak(xx, yy, gray)) {
                    gray.getRaster().setPixel(xx, yy, tmp000);
                    trackWeakOnes(xx, yy, gray);
                }
            }
    }
 
    private boolean isWeak(int x, int y, BufferedImage gray) {
        return (gray.getRaster().getPixel(x, y, tmpPixel)[0] > 0 && gray.getRaster().getPixel(x, y, tmpPixel)[0] < 255);
    }
 
    private void setPixel(int x, int y, BufferedImage gray, double v) {
        if (v > thresholdHigh) gray.getRaster().setPixel(x, y, tmp000);
        else if (v > thresholdLow) gray.getRaster().setPixel(x, y, tmp128);
        else gray.getRaster().setPixel(x, y, tmp255);
    }
 
    private double[][] sobelFilterX(BufferedImage gray) {
        double[][] result = new double[gray.getWidth()][gray.getHeight()];
        int[] tmp = new int[1];
        int tmpSum;
        for (int x = 1; x < gray.getWidth() - 1; x++) {
            for (int y = 1; y < gray.getHeight() - 1; y++) {
                tmpSum = 0;
                tmpSum += gray.getRaster().getPixel(x - 1, y - 1, tmp)[0];
                tmpSum += 2 * gray.getRaster().getPixel(x - 1, y, tmp)[0];
                tmpSum += gray.getRaster().getPixel(x - 1, y + 1, tmp)[0];
                tmpSum -= gray.getRaster().getPixel(x + 1, y - 1, tmp)[0];
                tmpSum -= 2 * gray.getRaster().getPixel(x + 1, y, tmp)[0];
                tmpSum -= gray.getRaster().getPixel(x + 1, y + 1, tmp)[0];
                result[x][y] = tmpSum;
            }
        }
        for (int x = 0; x < gray.getWidth(); x++) {
            result[x][0] = 0;
            result[x][gray.getHeight() - 1] = 0;
        }
        for (int y = 0; y < gray.getHeight(); y++) {
            result[0][y] = 0;
            result[gray.getWidth() - 1][y] = 0;
        }
        return result;
    }
 
    private double[][] sobelFilterY(BufferedImage gray) {
        double[][] result = new double[gray.getWidth()][gray.getHeight()];
        int[] tmp = new int[1];
        int tmpSum = 0;
        for (int x = 1; x < gray.getWidth() - 1; x++) {
            for (int y = 1; y < gray.getHeight() - 1; y++) {
                tmpSum = 0;
                tmpSum += gray.getRaster().getPixel(x - 1, y - 1, tmp)[0];
                tmpSum += 2 * gray.getRaster().getPixel(x, y - 1, tmp)[0];
                tmpSum += gray.getRaster().getPixel(x + 1, y - 1, tmp)[0];
                tmpSum -= gray.getRaster().getPixel(x - 1, y + 1, tmp)[0];
                tmpSum -= 2 * gray.getRaster().getPixel(x, y + 1, tmp)[0];
                tmpSum -= gray.getRaster().getPixel(x + 1, y + 1, tmp)[0];
                result[x][y] = tmpSum;
            }
        }
        for (int x = 0; x < gray.getWidth(); x++) {
            result[x][0] = 0;
            result[x][gray.getHeight() - 1] = 0;
        }
        for (int y = 0; y < gray.getHeight(); y++) {
            result[0][y] = 0;
            result[gray.getWidth() - 1][y] = 0;
        }
        return result;
    }
 
 
    
    public static float[] makeGaussianKernel(int radius, float sigma) {
        float[] kernel = new float[radius * radius];
        float sum = 0;
        for (int y = 0; y < radius; y++) {
            for (int x = 0; x < radius; x++) {
                int off = y * radius + x;
                int xx = x - radius / 2;
                int yy = y - radius / 2;
                kernel[off] = (float) Math.pow(Math.E, -(xx * xx + yy * yy)
                        / (2 * (sigma * sigma)));
                sum += kernel[off];
            }
        }
        for (int i = 0; i < kernel.length; i++)
            kernel[i] /= sum;
        return kernel;
    }
}
