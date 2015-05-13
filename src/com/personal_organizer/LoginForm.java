/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.view.OButton;
import com.personal_organizer.view.OFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Vorobiov Anatolii & Mikhail Novizhilov
 */
public class LoginForm extends OFrame {
    
    JTextField txtUserId;
    JPasswordField txtPassword;
    JButton btnCancel, btnLogin, btnSignUp;
    
    public LoginForm() {

        this.setTitle("Personal Organizer - Login");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pnlLogin = new JPanel();
        pnlLogin.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlLogin.add(new JLabel("User ID"), gbc);

        gbc.gridx = 1;
        pnlLogin.add(txtUserId = new JTextField(10), gbc);

        gbc.gridy = 1;
        pnlLogin.add(txtPassword = new JPasswordField(10), gbc);

        gbc.gridx = 0;
        pnlLogin.add(new JLabel("Password"), gbc);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pnlButtons.add(btnLogin = new JButton("Login"), gbc);
//        btnLogin.setOpaque(false);
//        btnLogin.setFocusPainted(false);
//        //btnLogin.setBorderPainted(false);
//        btnLogin.setContentAreaFilled(false);
//        btnLogin.setBorder(BorderFactory.); 
        btnLogin.addActionListener(new LoginListener());
        btnLogin.addMouseListener(new LoginListener());
        //pnlButtons.add(btnCancel = new JButton("Cancel"), gbc);
        //btnCancel.addActionListener(new CanceListener());
        pnlButtons.add(btnSignUp = new JButton("Sign Up"), gbc);
        btnSignUp.addActionListener(new LoginListener());
        btnSignUp.addMouseListener(new LoginListener());
 
        this.add(pnlLogin, BorderLayout.CENTER);
        this.add(pnlButtons, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);

    }
    
    class LoginListener implements MouseListener, ActionListener {

        public void listener(Object o){
            if(o == btnSignUp) {
                Personal_Organizer.loginForm.setVisible(false);
                new SignUpForm();
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            listener(e.getSource());
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        }
        
    }
    
}