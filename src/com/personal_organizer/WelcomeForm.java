/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.view.OFrame;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author Tolik
 */
public class WelcomeForm extends OFrame implements Runnable{
    
    JProgressBar progressbar;
    public WelcomeForm(){
        //this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BorderLayout border=new BorderLayout(0, 30);
        this.setLayout(border);
        //this.setDefaultCloseOperation(OFrame.DISPOSE_ON_CLOSE);
        progressbar=new JProgressBar(0, 100);
        //progressbar.setValue(30);
        //progressbar.setSize(10, 20);
//      progressbar.setString("Almost");
        progressbar.setStringPainted(true);
        //flow.addLayoutComponent("Text", progressbar);
        this.add(progressbar, BorderLayout.SOUTH);
        this.setUndecorated(true);
    }
    

    @Override
    public void run() {
        for(int i=0;i<=100;i++){
            progressbar.setValue(i);
            try{Thread.sleep(i);}catch(Exception e){}
            isRunning();
        }
        
    }
    protected boolean isRunning(){
        int i=progressbar.getValue();
        return i != 100;
    }
}
