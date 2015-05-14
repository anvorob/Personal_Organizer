/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.dao.DAO;
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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    protected MainForm mainform;
    protected JTextField txtUserId;
    protected JPasswordField txtPassword;
    protected JButton btnCancel, btnLogin, btnSignUp;
    protected static final String DB_SERVER_NAME = "localhost";
    protected static final String DB_USERID = "sa";
    protected static final String DB_PASSWORD = "NA@!ru30";

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
        txtUserId.setText("mic");

        gbc.gridy = 1;
        pnlLogin.add(txtPassword = new JPasswordField(10), gbc);
        txtPassword.setText("123");

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

    protected boolean checkUserPassword() {
        boolean unswer = false;
        String userName = txtUserId.getText();
        System.out.println("User ID: '" + userName + "'\n(userName == \"\") = " + (userName.equals("")));

        if (userName.equals("")) {
            System.out.println("User ID can't be empty.");
        } else {
            String userPassword = txtPassword.getText();
            System.out.println("Password: '" + userPassword + "'\n(userPassword == \"\") = " + (userPassword.equals("")));
            if (userPassword.equals("")) {
                System.out.println("Password can't be empty.");
            } else {
                userPassword = md5Custom(userPassword);
                System.out.println(userPassword);
                DAO dao = new DAO(DB_SERVER_NAME, DB_USERID, DB_PASSWORD);
                unswer = dao.checkUserPassword(userName, userPassword);
                if (!unswer) {
                    System.out.println("The combination of user and password"
                            + " was not found.\nTry agaim.");
                } else {
                    System.out.println("Wellcome " + userName + "!");
                    mainform = new MainForm();
                    this.setVisible(false);
                    mainform.setVisible(true);
                }
            }

        }

        return unswer;
    }

    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    class LoginListener implements MouseListener, ActionListener {

        public void listener(Object o) {
            if (o == btnSignUp) {
                Personal_Organizer.loginForm.setVisible(false);
                new SignUpForm();
            } else if (o == btnLogin) {
                if (checkUserPassword()) {

                }
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
