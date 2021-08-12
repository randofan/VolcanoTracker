package com.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Hello world!
 *
 */
public class App extends JPanel {
    public static void main( String[] args ) throws IOException {
        File folder = new File("Cropped photos");
        File[] sortedFolder = sort(folder.listFiles()); //sort by date

        String[][] arr = new String[sortedFolder.length][3];
        // int height = 0;
        // int width = 0;

        for (int i = 0; i < arr.length; i++) {
            File f = sortedFolder[i];
            BufferedImage image = ImageIO.read(f);

            // if (i == 0) { TODO auto crop the originial pictures
            //     height = 163;
            //     width = 187;
            // }
            // else if (image.getWidth() != width || image.getHeight() != height) {
            //     int x = 419;
            //     int y = 102;
            //     image = image.getSubimage(x, y, image.getWidth()-x-10, image.getHeight()-300);
            // }
            
            double[] numbers = allCalculations(image);


            arr[i][0] = f.getName();     // date
            arr[i][1] = "" + numbers[1]; // leading edge
            arr[i][2] = "" + numbers[0]; // color counter
        }

        // for (int i = 0; i < arr.length; i++) { // print out table
        //     for (int j = 0; j < arr[0].length; j++) {
        //         System.out.print(arr[i][j] + "\t");
        //     }
        //     System.out.println();
        // }

        JFrame frame = new JFrame("Map");
            frame.setSize(187, 163);
            // frame.add();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static File[] sort(File[] folder) {
     
        Comparator<File> c = new Comparator<File>(){

            @Override
            public int compare(File o1, File o2) {
                SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd");
                try {
                    Date date1 = formatter.parse(o1.getName());
                    Date date2 = formatter.parse(o2.getName());
                    return date1.compareTo(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }  
        };

        Arrays.sort(folder, c);
        return folder;
    }

    public static double[] allCalculations(BufferedImage image) throws IOException {
        double maxDistance = 0;
        int count = 0; 

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color clr = new Color(image.getRGB(i, j));
                if (colorPicker(clr)) {
                    double temp = Math.abs(Math.sqrt((i*i) + (j*j)));
                    if (temp > maxDistance) {
                        maxDistance = temp;
                    }
                    count++;
                }
            }
        }
        double[] numbers = new double[2];
        numbers[0] = maxDistance;
        numbers[1] = count;

        return numbers;
    }

    public static boolean colorPicker(Color clr) throws IOException {
        int blue = clr.getBlue(), red = clr.getRed(), green = clr.getGreen();
        if (red > 245 && red < 256 && green > 0 && green < 10 && blue > 0 && blue < 10) {
            return true;
        }
        else if (red > 170 && red < 180 && green > 30 && green < 40 && blue > 29 && blue < 39) {
            return true;
        }
        else if (red > 236 && red < 246 && green > 96 && green < 106 && blue > 83 && blue < 103) {
            return true;
        }
        
        else if (red > 245 && red < 256 && green > 118 && green < 128 && blue > 125 && blue < 135) {
            return true;
        }
        else if (red > 163 && red < 173 && green > 28 && green < 38 && blue > 42 && blue < 52) {
            return true;
        }
        else if (red > 240 && red < 250 && green > 164 && green < 174 && blue > 168 && blue < 178) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        
    }
}
