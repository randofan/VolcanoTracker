package com.example;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.awt.*;

public class MyJPanel extends JPanel implements KeyListener {

    public static LavaMap[] maps;
    private int position = 0;

    public MyJPanel() throws IOException {
        addKeyListener(this);
        
        File folder = new File("Cropped photos");
        File[] sortedFolder = sort(folder.listFiles()); // sort by date
        maps = new LavaMap[sortedFolder.length];

        System.out.println("Color\tColor Count\tLeading Edge");
        for (int i = 0; i < sortedFolder.length; i++) {
            maps[i] = new LavaMap(sortedFolder[i]);
            System.out.println(maps[i]);
        }
        this.position = 0;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        maps[position].drawMap(g);
    }
    
    public static File[] sort(File[] folder) {
     
        Comparator<File> c = new Comparator<File>(){

            @Override
            public int compare(File o1, File o2) {
                SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd");
                try {
                    Date date1 = (Date) formatter.parse(o1.getName());
                    Date date2 = (Date) formatter.parse(o2.getName());
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

    @Override
    public void keyPressed(KeyEvent e) {
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                // if (position >= maps.length-1) position++;
                // System.out.println("Right Arrow");
                repaint();
                break;
            case KeyEvent.VK_LEFT:
                // if (position <= 0) position--;
                // System.out.println("Left Arrow");
                repaint();
                break;
            default: break;
        }
        
    }
}
