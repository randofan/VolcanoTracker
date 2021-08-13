package com.example;

import java.io.IOException;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Hello world!
 *
 */
public class App extends JPanel {

    public static ArrayList<int[]> coords = new ArrayList<>();
    public static void main( String[] args ) throws IOException {

        /* TODO auto crop the originial pictures
        if (i == 0) { 
            height = 163;
            width = 187;
        }
        else if (image.getWidth() != width || image.getHeight() != height) {
            int x = 419;
            int y = 102;
            image = image.getSubimage(x, y, image.getWidth()-x-10, image.getHeight()-300);
        }
        */

        JFrame frame = new JFrame("Map");
            frame.setSize(187*4, 163*4 + 50); // JFrame pixel = 4 x pic pixel
            frame.add(new MyJPanel());        // extra 50 pixels at top for space for header button/label
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
    }
}
