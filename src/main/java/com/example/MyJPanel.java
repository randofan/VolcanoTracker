package com.example;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJPanel extends JPanel implements ActionListener {

    public static LavaMap[] maps;
    private int position = 0;
    JButton previous;
    JButton next;
    JLabel date;

    public MyJPanel() throws IOException {

        File folder = new File("Cropped photos"); // sort pictures by date in array
        File[] sortedFolder = sort(folder.listFiles());
        maps = new LavaMap[sortedFolder.length];

        File f = new File("output.csv"); // writes all data to a .txt file
        FileWriter bw = new FileWriter(f);
        bw.write("Color,Color Count,Leading Edge\n");

        for (int i = 0; i < sortedFolder.length; i++) { // adds all LavaMap objects to the File array
            maps[i] = new LavaMap(sortedFolder[i]);
            bw.write(maps[i].toString());
        }
        bw.close();

        this.position = 0;

        previous = new JButton("<"); // control buttons
            previous.addActionListener(this);
            this.add(previous);

        date = new JLabel(maps[position].getName());
            this.add(date);

        next = new JButton(">");
            next.addActionListener(this);
            this.add(next);

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        maps[position].drawMap(g); // draws each map
    }
    
    /**
     * 
     * @param folder
     * @return sorted folder by date
     */
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(next)) {
            if (position < maps.length-1) position++; // updates map[position] to next element and repaints
            date.setText(maps[position].getName());   // reset label text        
            repaint();
        }
        if(e.getSource().equals(previous)) {
            if (position > 0) position--;
            date.setText(maps[position].getName());
            repaint();
        }
             
    }
}
