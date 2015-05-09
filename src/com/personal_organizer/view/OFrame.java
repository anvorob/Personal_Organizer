/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.view;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Tolik
 */
public class OFrame extends JFrame{
    
    public OFrame(){
        this.setTitle("Welcome to Personal Organizer");
        this.setSize(200,400);
        JEditorPane background = new JEditorPane();
        background.setContentType("text/html");
        background.setText(
     "<html><body style=\"background-image: url(http://hq-wallpapers.ru/wallpapers/8/hq-wallpapers_ru_abstraction3d_39318_1920x1200.jpg);\"></body></html>");
        this.setContentPane(background);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Color bgcolor=new Color(255,0,0);
        this.getContentPane().setBackground(new Color(51,51,51));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
