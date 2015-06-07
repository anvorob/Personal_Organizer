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
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Vorobiov Anatolii & Mikhail Novizhilov
 */
public class LoginForm extends OFrame {

    private MainForm mainform;
    private JTextField txtLoginName;
    private JPasswordField txtPassword;
    private JTextField txtServerAddress;
    private JTextField txtServerUserName;
    private JPasswordField txtServerPassword;
    private JButton btnCancel, btnLogin, btnSignUp;
    private JCheckBox chbxRememberLogin;
    private JCheckBox chbxRememberSQLSettings;

    public LoginForm() {

        if ((com.sun.awt.AWTUtilities.isTranslucencySupported(com.sun.awt.AWTUtilities.Translucency.PERPIXEL_TRANSLUCENT))
                && (com.sun.awt.AWTUtilities.isTranslucencyCapable(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration()))) {
            this.setUndecorated(true);
            //com.sun.awt.AWTUtilities.setWindowOpaque(this, false);
            com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.75F);
        }
        this.setTitle("Personal Organizer - Login");

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new CloseListener());
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new GridLayout(0, 1));

        JPanel pnlLogin = new JPanel();
        pnlLogin.setLayout(new GridBagLayout());
        TitledBorder title = BorderFactory.createTitledBorder("Login");
        pnlLogin.setBorder(title);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlLogin.add(new JLabel("User ID"), gbc);

        gbc.gridx = 1;
        pnlLogin.add(txtLoginName = new JTextField(10), gbc);
        //txtLoginName.setText("mic");

        gbc.gridy = 1;
        pnlLogin.add(txtPassword = new JPasswordField(10), gbc);
        //txtPassword.setText("123");

        gbc.gridx = 0;
        pnlLogin.add(new JLabel("Password"), gbc);

        JPanel pnlSQLSettings = new JPanel(new GridBagLayout());
        title = BorderFactory.createTitledBorder("SQL server settings");
        pnlSQLSettings.setBorder(title);

        gbc.gridy = 0;

        pnlSQLSettings.add(new JLabel("Address:"), gbc);

        gbc.gridx = 1;
        pnlSQLSettings.add(txtServerAddress = new JTextField(10), gbc);
        //txtServerAddress.setText("");

        gbc.gridy = 1;
        pnlSQLSettings.add(txtServerUserName = new JTextField(10), gbc);
        //txtServerUserName.setText("sa");

        gbc.gridx = 0;
        pnlSQLSettings.add(new JLabel("User ID"), gbc);

        gbc.gridy = 2;
        pnlSQLSettings.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        pnlSQLSettings.add(txtServerPassword = new JPasswordField(10), gbc);
        //txtServerPassword.setText("");

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel pnlRememberSQLSettings = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlRememberSQLSettings.add(chbxRememberSQLSettings = new JCheckBox("Remenber SQL Settings"));
        pnlSQLSettings.add(pnlRememberSQLSettings, gbc);

        //gbc.gridy = 3;
        //pnlLogin.add(pnlSQLSettings, gbc);
        gbc.gridy = 2;
        JPanel pnlRememberLogin = new JPanel(new FlowLayout(FlowLayout.CENTER));

        pnlRememberLogin.add(chbxRememberLogin = new JCheckBox("Remenber Login and Password"));
        pnlLogin.add(pnlRememberLogin, gbc);

        pnlMain.add(pnlLogin);
        pnlMain.add(pnlSQLSettings);

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

        readSettings();

        this.add(pnlMain, BorderLayout.CENTER);
        this.add(pnlButtons, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void checkUserPassword() {
        boolean unswer = false;
        unswer = DBFunctions.checkUserPassword(this);

        if (!unswer) {
            System.out.println("The combination of user and password"
                    + " was not found.\nTry agaim.");
        } else {
            System.out.println("Wellcome " + getLoginName() + "!");
            //Tools.diff("System.out.println(\"Wellcome \" + userName + \"!\");", System.currentTimeMillis());
            Personal_Organizer.mainform = new MainForm();
            //Tools.diff("mainform = new MainForm();", System.currentTimeMillis());
            this.setVisible(false);
            //Tools.diff("this.setVisible(false);", System.currentTimeMillis());
            Personal_Organizer.mainform.setVisible(true);
            //Tools.diff("mainform.setVisible(true);", System.currentTimeMillis());
        }
    }

    public String getLoginName() {
        return this.txtLoginName.getText();
    }

    public void setLoginName(String loginName) {
        this.txtLoginName.setText(loginName);
    }

    public String getPassword() {
        return this.txtPassword.getText();
    }

    public void setPassword(String password) {
        this.txtPassword.setText(password);
    }

    public boolean getRememberLogin() {
        return this.chbxRememberLogin.isSelected();
    }

    public void setRememberLogin(boolean rememberLogin) {
        this.chbxRememberLogin.setSelected(rememberLogin);
    }

    public String getServerAddress() {
        return this.txtServerAddress.getText();
    }

    public void setServerAddress(String serverAddress) {
        this.txtServerAddress.setText(serverAddress);
    }

    public String getServerUserName() {
        return this.txtServerUserName.getText();
    }

    public void setServerUserName(String serverUserName) {
        this.txtServerUserName.setText(serverUserName);
    }

    public String getServerPassword() {
        return this.txtServerPassword.getText();
    }

    public void setServerPassword(String serverPassword) {
        this.txtServerPassword.setText(serverPassword);
    }

    public boolean getRememberSQLSettings() {
        return this.chbxRememberSQLSettings.isSelected();
    }

    public void setRememberSQLSettings(boolean rememberSQLSettings) {
        this.chbxRememberSQLSettings.setSelected(rememberSQLSettings);
    }

    private void readSettings() {
        Tools.readSettings(this);
    }

    private void writeSettings() {
        Tools.writeSettings(this);
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            listener(e.getSource());
            Tools.time = System.currentTimeMillis();
            if (e.getSource() == btnSignUp) {
                Personal_Organizer.loginForm.setVisible(false);
                if (Personal_Organizer.signUpForm == null) {
                    Personal_Organizer.signUpForm = new SignUpForm();
                }
                Personal_Organizer.signUpForm.setVisible(true);
            } else if (e.getSource() == btnLogin) {
                writeSettings();
                checkUserPassword();
            }
        }

    }

    class CloseListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            writeSettings();
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }

    }
}
