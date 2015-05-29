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
//        super;
        Font f = new Font("Seris", Font.ITALIC, 11);
        this.setFont(f);
        //this.setSize(400, 7);
    }
    
    @Override
    public void setText(String str){
        super.setText(str);
        if(str.equals("")){
            //f = new Font("Serif",  Font.ITALIC, 11)
            this.setForeground(new Color(239, 239, 239));
            super.setText("weferggrtg");
            
        }else{
            this.setForeground(Color.RED);
            
        }
        
    }
}
