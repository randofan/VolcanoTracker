package com.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class LavaMap {

    private int colorCounter = 0;
    private double leadingEdge = 0;
    private String name;
    private ArrayList<LavaPoint> coords;
    
    public LavaMap(File f) throws IOException {
        coords = new ArrayList<>();
        BufferedImage image = ImageIO.read(f);

        double maxDistance = 0;
        int count = 0; 

        for (int i = 0; i < image.getWidth(); i++) { // iterates over entire image
            for (int j = 0; j < image.getHeight(); j++) {
                Color clr = new Color(image.getRGB(i, j));
                if (colorPicker(clr, i, j)) {
                    double temp = Math.abs(Math.sqrt((i*i) + (j*j)));
                    if (temp > maxDistance) {
                        maxDistance = temp; // update leading edge
                    }
                    count++; // update color counter
                    this.coords.add(new LavaPoint(i, j)); // adds this coord to ArrayList to draw image
                }
            }
        }
        this.name = f.getName();
        this.leadingEdge = maxDistance;
        this.colorCounter = count;
    }

    public int getColorCounter() {
        return colorCounter;
    }

    public double getLeadingEdge() {
        return leadingEdge;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return (this.name + "\t" + this.colorCounter + "\t" + this.leadingEdge);
    }

    public boolean colorPicker(Color clr, int x, int y) throws IOException {

        float[] hsb = Color.RGBtoHSB(clr.getRed(), clr.getGreen(), clr.getBlue(), null);
        if (!(hsb[0] < 15 && hsb[1] < 0.95 && hsb[2] > 0.25) &&
            hsb[2] > 0.25) {
            // ((hsb[0] > 0.9 || hsb[0] < 0.09) && hsb[1] > 0.9 && hsb[2] > 0.1)) {
            System.out.println("" + x + ", " + y + ": " + Arrays.toString(hsb));
            return true;
        }
        else return false;

        // int blue = clr.getBlue(), red = clr.getRed(), green = clr.getGreen();
        // if (red > 245 && red < 256 && green > 0 && green < 10 && blue > 0 && blue < 10) {
        //     return true;
        // }
        // else if (red > 170 && red < 180 && green > 30 && green < 40 && blue > 29 && blue < 39) {
        //     return true;
        // }
        // else if (red > 236 && red < 246 && green > 96 && green < 106 && blue > 83 && blue < 103) {
        //     return true;
        // }
        
        // else if (red > 245 && red < 256 && green > 118 && green < 128 && blue > 125 && blue < 135) {
        //     return true;
        // }
        // else if (red > 163 && red < 173 && green > 28 && green < 38 && blue > 42 && blue < 52) {
        //     return true;
        // }
        // else if (red > 240 && red < 250 && green > 164 && green < 174 && blue > 168 && blue < 178) {
        //     return true;
        // }
        // else {
        //     return false;
        // }
    }

    public void drawMap(Graphics g) {
        for (LavaPoint lavaPoint : coords) {
            lavaPoint.drawPoint(g);
        }
    }

    public class LavaPoint {

        private int x;
        private int y;

        public LavaPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    
        public void drawPoint(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(x*4, y*4 + 50, 4, 4);
        }
    } 
}
