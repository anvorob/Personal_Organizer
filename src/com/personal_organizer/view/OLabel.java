/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Mikhail
 */
public class OLabel extends JLabel {

    public OLabel() {
        this("");
    }
    public OLabel(String str) {
        super(str);
        Font f = new Font("Seris", Font.ITALIC, 11);
        this.setFont(f);
        //this.setSize(400, 7);
    }

    @Override
    public void setText(String str) {
        super.setText(str);
        if(str.equals("")){
            this.setGreyColor();
            super.setText("weferggrtg");
            
        }else{
            this.setForeground(Color.RED);
            
        }

    }

    public void setRedColor() {
        this.setForeground(Color.RED);
    }

    public void setBlueColor() {
        this.setForeground(Color.BLUE);
    }

    public void setGreyColor() {
        this.setForeground(new Color(239, 239, 239));
    }
}
