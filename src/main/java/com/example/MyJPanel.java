package com.example;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
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
        
        File folder = new File("Cropped photos");
        File[] sortedFolder = sort(folder.listFiles()); // sort pictures by date
        maps = new LavaMap[sortedFolder.length];

        System.out.println("Color\tColor Count\tLeading Edge");
        // for (int i = 0; i < sortedFolder.length; i++) { // adds all LavaMap objects to the File array
        //     maps[i] = new LavaMap(sortedFolder[i]);
        //     System.out.println(maps[i]);
        // }

        maps[0] = new LavaMap(sortedFolder[25]); // testing code
        System.out.println(maps[0]);

        this.position = 0;


        previous = new JButton("<");
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
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(next)) {
            if (position < maps.length-1) position++;
            date.setText(maps[position].getName());            
            repaint();
        }
        if(e.getSource().equals(previous)) {
            if (position > 0) position--;
            date.setText(maps[position].getName());
            repaint();
        }
             
    }
}
