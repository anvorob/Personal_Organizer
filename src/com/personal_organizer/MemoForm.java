/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Tolik
 */
public class MemoForm extends JFrame implements ActionListener{
    
    JPanel pnltop;
    JPanel pnlmemo;

    JButton close;
    JButton add;
    
    JTextArea txtmemo;
    public MemoForm(){
        //this.setTitle("Memos");
        Point p=new Point(930,20);
        this.setLocation(p);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(200, 200));
        pnltop = new JPanel();
        BoxLayout toplayout= new BoxLayout(pnltop,BoxLayout.X_AXIS);
        
        pnltop.setLayout(toplayout);
        Icon imgclose = new ImageIcon(getClass().getResource("/resources/close_16.png"));
        Icon imgadd =new ImageIcon(getClass().getResource("/resources/add_16.png"));
        close = new JButton(imgclose);
        add=new JButton(imgadd);
        close.setOpaque(false);
        close.setContentAreaFilled(false);
        close.setBorderPainted(false);
        close.addActionListener(this);
        add.setOpaque(false);
        add.setContentAreaFilled(false);
        add.setBorderPainted(false);
        pnltop.add(add);
        pnltop.add(Box.createHorizontalGlue());
        pnltop.add(close);
        //pnltop.setMinimumSize(new Dimension(20, 20));
        this.add(pnltop, BorderLayout.NORTH);
        JPanel pnlmemo=new JPanel(new GridLayout(1, 1));
        txtmemo=new JTextArea(5, 5);
        txtmemo.setLineWrap(true);
        txtmemo.setWrapStyleWord(true);
        txtmemo.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
            System.out.println(""+txtmemo.getSize().height+" : "+txtmemo.getSize().width);
            changesize(txtmemo.getSize().height, txtmemo.getSize().width);
        }
    });
        txtmemo.setBackground(Color.LIGHT_GRAY);
        pnlmemo.add(txtmemo);
        System.out.println(""+pnlmemo.getSize().width+" : "+pnlmemo.getSize().height);
        this.add(pnlmemo, BorderLayout.CENTER);
        this.setUndecorated(true);
    }

    public void changesize(int h, int w){
        this.setSize(w, h);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==close){
            System.exit(0);
        }
} 
}
