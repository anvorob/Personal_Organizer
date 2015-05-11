/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author Tolik
 */
public class OButton extends JButton{
    
    public OButton(String text){
        this.setText(text);
        this.setBorder(new RoundedBorder(20));
        Color bg=new Color(200,200,0);
        Color fg=new Color(40,40,200);
        Font f=new Font("SansSerif", Font.BOLD, 16);
        this.setFont(f);
        this.setBackground(bg);
        this.setForeground(fg);
    }
}
