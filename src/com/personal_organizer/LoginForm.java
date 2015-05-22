/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.db.DBFunctions;
import com.personal_organizer.modules.Tools;
import com.personal_organizer.view.OFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Vorobiov Anatolii & Mikhail Novizhilov
 */
public class LoginForm extends OFrame {

    private boolean showCommentForDebuging = false;
    private MainForm mainform;
    private JTextField txtUserId;
    private JPasswordField txtPassword;
    private JButton btnCancel, btnLogin, btnSignUp;
    private JCheckBox chbxRememberLogin;

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

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        //gbc.fill = GridBagLayout.;
        pnlLogin.add(chbxRememberLogin = new JCheckBox("Remenber Login and Password"), gbc);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pnlButtons.add(btnLogin = new JButton("Login"));

//        btnLogin.setOpaque(false);
//        btnLogin.setFocusPainted(false);
//        //btnLogin.setBorderPainted(false);
//        btnLogin.setContentAreaFilled(false);
//        btnLogin.setBorder(BorderFactory.); 
        btnLogin.addActionListener(new LoginListener());
        //pnlButtons.add(btnCancel = new JButton("Cancel"), gbc);
        //btnCancel.addActionListener(new CanceListener());
        pnlButtons.add(btnSignUp = new JButton("Sign Up"), gbc);
        btnSignUp.addActionListener(new LoginListener());

        this.add(pnlLogin, BorderLayout.CENTER);
        this.add(pnlButtons, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void checkUserPassword() {
        boolean unswer = false;
        String userName = txtUserId.getText();
        print("User ID: '" + userName + "'\n(userName == \"\") = " + (userName.equals("")));
        Tools.diff("String userName = txtUserId.getText();", System.currentTimeMillis());
        if (userName.equals("")) {
            print("User ID can't be empty.");
        } else {
            String userPassword = txtPassword.getText();
            print("Password: '" + userPassword + "'\n(userPassword == \"\") = " + (userPassword.equals("")));
            Tools.diff("String userPassword = txtPassword.getText();", System.currentTimeMillis());
            if (userPassword.equals("")) {
                print("Password can't be empty.");
            } else {
                DBFunctions db = new DBFunctions();
                Tools.diff("DBFunctions db = new DBFunctions();", System.currentTimeMillis());
                Personal_Organizer.userProfile = new UserProfile(userName, userPassword);
                Tools.diff("Personal_Organizer.userProfile = new UserProfile(userName, userPassword);", System.currentTimeMillis());
                unswer = db.checkUserPassword(Personal_Organizer.userProfile);
                Tools.diff("unswer = db.checkUserPassword(Personal_Organizer.userProfile);", System.currentTimeMillis());
                if (!unswer) {
                    System.out.println("The combination of user and password"
                            + " was not found.\nTry agaim.");
                } else {
                    System.out.println("Wellcome " + userName + "!");
                    Tools.diff("System.out.println(\"Wellcome \" + userName + \"!\");", System.currentTimeMillis());
                    mainform = new MainForm();
                    Tools.diff("mainform = new MainForm();", System.currentTimeMillis());
                    this.setVisible(false);
                    Tools.diff("this.setVisible(false);", System.currentTimeMillis());
                    mainform.setVisible(true);
                    Tools.diff("mainform.setVisible(true);", System.currentTimeMillis());
                }
            }
        }
    }

    class LoginListener implements ActionListener {

        public void listener(Object o) {
            Tools.time = System.currentTimeMillis();
            if (o == btnSignUp) {
                Personal_Organizer.loginForm.setVisible(false);
                if (Personal_Organizer.signUpForm == null) {
                    Personal_Organizer.signUpForm = new SignUpForm();
                }
                Personal_Organizer.signUpForm.setVisible(true);
            } else if (o == btnLogin) {
                checkUserPassword();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            listener(e.getSource());
        }

    }

    private void print(String str) {
        if (showCommentForDebuging) {
            System.out.println(str);
        }
    }

    public static void write(String fileName, String text) {
        //Определяем файл
        File file = new File(fileName);

        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                //Записываем текст у файл
                out.print(text);
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
