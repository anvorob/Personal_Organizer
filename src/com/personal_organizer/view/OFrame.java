/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.view;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
/**
 *
 * @author Tolik
 */
public class OFrame extends JFrame{
    
    public OFrame(){
        this.setTitle("Welcome to Personal Organizer");
        this.setSize(200,400); 
        
        try {
                setLayout(new BorderLayout());
                JEditorPane bg = new JEditorPane();
                File bgimage = new File("img.jpg");
                URL url = bgimage.toURI().toURL();
                bg.setContentType("text/html");
                bg.setText("<html><body style='color: #ffffff; background-image: url(" + url.toString() + ");'></body></html>");
                this.setContentPane(bg);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
                System.out.println("Background image failed to load");
            }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(51,51,51));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
