/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.dao.DAO;
import com.personal_organizer.db.DBFunctions;
import com.personal_organizer.modules.Tools;
import com.personal_organizer.modules.UserProfile;
import com.personal_organizer.view.OLabel;
import java.sql.Date;
//import java.util.Date;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Mikhail
 */
public class SignUpForm extends JFrame {

    private JTextField txtFirstName, txtLastName;
    private JTextField txtPhone, txtEmail, txtLoginName;
    private JPasswordField txtPassword, txtPasswordRepeat;
    private OLabel errLogin, errPassword, errPasswordRepeat, errEmail;
    private JComboBox birthDayYear, birthDayMonth, birthDayDay;

    private JScrollPane scrollPane;

    private JButton btnCancel, btnSave;

    private ImageIcon iconLogin, iconPassword, iconPasswordRepeat, iconEmail;
    private JPanel pnlIconLogin, pnlIconPassword, pnlIconPasswordRepeat, pnlIconEmail;
    private CardLayout cardLayout;

    private static String[] imageList = {"no.png", "yes.png"};

    boolean isLoginNameOk, isPasswordOk, isPasswordRepeatOk, isEmailOk;
    
    {
        isLoginNameOk = false;
        isPasswordOk = false;
        isPasswordRepeatOk = false;
        isEmailOk = false;

    }
    public SignUpForm() {
        this.setTitle("Personal Organizer - Sign Up");
        GridBagConstraints gbc = new GridBagConstraints();
        TitledBorder title = BorderFactory.createTitledBorder("Personal Information");

        JPanel pnlFields = new JPanel();
        {
            pnlFields.setLayout(new GridBagLayout());

            JPanel pnlPersonalInformation = new JPanel();
            {
                pnlPersonalInformation.setLayout(new GridBagLayout());

                pnlPersonalInformation.setBorder(title);

                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(5, 10, 5, 10);
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.gridx = 0;
                gbc.gridy = 0;
                pnlPersonalInformation.add(new JLabel("Login Name: *"), gbc);

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtLoginName = new JTextField("", 10), gbc);
                txtLoginName.setText("mic");
                txtLoginName.addFocusListener(new focusListener());

                gbc.gridx = 2;
                pnlIconLogin = new JPanel();
                cardLayout = new CardLayout();
                pnlIconLogin.setLayout(cardLayout);
                pnlIconLogin.add(new JLabel(new ImageIcon(imageList[0])));
                pnlIconLogin.add(new JLabel(new ImageIcon(imageList[1])));
                pnlPersonalInformation.add(pnlIconLogin, gbc);

                gbc.gridy = 1;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                pnlPersonalInformation.add(errLogin = new OLabel(), gbc);
                gbc.gridwidth = 1;
                
                gbc.gridx = 2;
                gbc.gridy = 2;
                pnlIconPassword = new JPanel();
                pnlIconPassword.setLayout(cardLayout);
                pnlIconPassword.add(new JLabel(new ImageIcon(imageList[0])));
                pnlIconPassword.add(new JLabel(new ImageIcon(imageList[1])));
                pnlPersonalInformation.add(pnlIconPassword, gbc);

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtPassword = new JPasswordField("", 10), gbc);
                txtPassword.setText("micmic");
                txtPassword.addFocusListener(new focusListener());
                System.out.println("txtPassword.getText() = " + txtPassword.getText());
                System.out.println("txtPassword.getPassword() = " + txtPassword.getPassword());

                gbc.gridx = 0;
                pnlPersonalInformation.add(new JLabel("Password: *"), gbc);

                gbc.gridy = 3;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                pnlPersonalInformation.add(errPassword = new OLabel(), gbc);
                gbc.gridwidth = 1;

                gbc.gridx = 0;
                gbc.gridy = 4;
                pnlPersonalInformation.add(new JLabel("Repeat Password: *"), gbc);

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtPasswordRepeat = new JPasswordField("", 10), gbc);
                txtPasswordRepeat.addFocusListener(new focusListener());
                txtPasswordRepeat.setText("micmic");

                gbc.gridx = 2;
                pnlIconPasswordRepeat = new JPanel();
                //cardIconPassword = new CardLayout();
                pnlIconPasswordRepeat.setLayout(cardLayout);
                pnlIconPasswordRepeat.add(new JLabel(new ImageIcon(imageList[0])));
                pnlIconPasswordRepeat.add(new JLabel(new ImageIcon(imageList[1])));
                pnlPersonalInformation.add(pnlIconPasswordRepeat, gbc);

                gbc.gridy = 5;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                pnlPersonalInformation.add(errPasswordRepeat = new OLabel(), gbc);
                gbc.gridwidth = 1;

                gbc.gridy = 6;
                gbc.gridx = 1;
                pnlPersonalInformation.add(txtFirstName = new JTextField("", 10), gbc);

                gbc.gridx = 0;
                pnlPersonalInformation.add(new JLabel("First Name: "), gbc);

                gbc.gridy = 7;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                pnlPersonalInformation.add(new OLabel(), gbc);
                gbc.gridwidth = 1;

                gbc.gridx = 0;
                gbc.gridy = 8;
                pnlPersonalInformation.add(new JLabel("Last Name: "), gbc);

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtLastName = new JTextField("", 10), gbc);

                gbc.gridy = 9;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                pnlPersonalInformation.add(new OLabel(), gbc);
                gbc.gridwidth = 1;

                gbc.gridx = 1;
                gbc.gridy = 10;
                pnlPersonalInformation.add(txtPhone = new JTextField("", 10), gbc);

                gbc.gridx = 0;
                pnlPersonalInformation.add(new JLabel("Phone No.: "), gbc);

                gbc.gridy = 11;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                pnlPersonalInformation.add(new OLabel(), gbc);
                gbc.gridwidth = 1;

                gbc.gridx = 0;
                gbc.gridy = 12;
                pnlPersonalInformation.add(new JLabel("E-mail: *"), gbc);

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtEmail = new JTextField("", 10), gbc);
                txtEmail.setText("mixnov@bk.ru");

                gbc.gridx = 2;
                pnlIconEmail = new JPanel();
                pnlIconEmail.setLayout(cardLayout);
                pnlIconEmail.add(new JLabel(new ImageIcon(imageList[0])));
                pnlIconEmail.add(new JLabel(new ImageIcon(imageList[1])));
                pnlPersonalInformation.add(pnlIconEmail, gbc);

                gbc.gridy = 13;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                pnlPersonalInformation.add(errEmail = new OLabel(), gbc);
                gbc.gridwidth = 1;

                gbc.gridy = 14;
                gbc.gridx = 1;
                JPanel pnlBirthDay = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 5));
                String[] days = {"", " 01", " 02", " 03", " 04", " 05", " 06",
                    " 07", " 08", " 09", " 10", " 11", " 12", " 13", " 14", " 15", " 16",
                    " 17", " 18", " 19", " 20", " 21", " 22", " 23", " 24", " 25", " 26",
                    " 27", " 28", " 29", " 30", " 31"};
                String[] months = {"", " 01", " 02", " 03", " 04", " 05", " 06",
                    " 07", " 08", " 09", " 10", " 11", " 12"};
                String[] years1 = {"", "1976", "1977", "1978", "1979", "1980", "1981", "1982",
                    "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991",
                    "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000"};
                pnlBirthDay.add(birthDayDay = new JComboBox(days));
                pnlBirthDay.add(birthDayMonth = new JComboBox(months));
                pnlBirthDay.add(birthDayYear = new JComboBox(years1));
                pnlPersonalInformation.add(pnlBirthDay, gbc);
                birthDayDay.setSelectedIndex(24);
                birthDayMonth.setSelectedIndex(4);
                birthDayYear.setSelectedIndex(1976 - 1975);
                gbc.gridx = 0;
                pnlPersonalInformation.add(new JLabel("Date of Birth: "), gbc);

                gbc.gridy = 0;
                pnlFields.add(pnlPersonalInformation, gbc);
            }
        }

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //gbc.gridy = 2;
        //pnlButtons.add(btnUpdate = new JButton("Update"));
        //btnUpdate.addActionListener(new UpdateListener());

        //gbc.gridx = 1;
        pnlButtons.add(btnCancel = new JButton("Cancel"));
        btnCancel.addActionListener(new SignUpListener());

        //gbc.gridx = 2;
        pnlButtons.add(btnSave = new JButton("Save"));
        btnSave.addActionListener(new SignUpListener());

        this.add(scrollPane = new JScrollPane(pnlFields), BorderLayout.CENTER);
        //scrollPane.setBounds(0, 0, 500, 700);
        this.add(pnlButtons, BorderLayout.SOUTH);

        //this.setSize(550, 180);
        this.addWindowListener(new closingListener());
        this.pack();
        this.setLocationRelativeTo(null);
        //this.setVisible(true);

    }

    private void setImageNo(CardLayout card, JPanel pnl) {
        card.first(pnl);
    }

    private void setImageYes(CardLayout card, JPanel pnl) {
        card.last(pnl);
    }

    private void saveUpdateUserProfile(String command) {
        int rows = Tools.saveUpdateUserProfile(this, command);
        if (rows > 0) {
            JOptionPane.showMessageDialog(Personal_Organizer.signUpForm,
                    "Login Name saved. Now you can Login.");
            this.setVisible(false);
            Personal_Organizer.loginForm.setLoginName(txtLoginName.getText());
            Personal_Organizer.loginForm.setPassword(txtPassword.getText());
            //Personal_Organizer.loginForm.setRememberLogin(false);
            Personal_Organizer.loginForm.setVisible(true);
        }
    }

    public String getFirstName() {
        return this.txtFirstName.getText();
    }

    public void setFirstName(String FirstName) {
        this.txtFirstName.setText(FirstName);
    }

    public String getLastName() {
        return this.txtLastName.getText();
    }

    public void setLastName(String LastName) {

    }

    public String getPhone() {
        return this.txtPhone.getText();
    }

    public void setPhone(String Phone) {
        this.txtPhone.setText(Phone);
    }

    public String getEmail() {
        return this.txtEmail.getText();
    }

    public void setEmail(String Email) {
        this.txtEmail.setText(Email);
    }

    public String getLoginName() {
        return this.txtLoginName.getText();
    }

    public void setLoginName(String LoginName) {
        this.txtLoginName.setText(LoginName);
    }

    public String getPassword() {
        return this.txtPassword.getText();
    }

    public void setPassword(String Password) {
        this.txtPassword.setText(Password);
    }

    public String getPasswordRepeat() {
        return this.txtPasswordRepeat.getText();
    }

    public void setPasswordRepeat(String PasswordRepeat) {
        this.txtPasswordRepeat.setText(PasswordRepeat);
    }

    public int getBirthDayYear() {
        return this.birthDayYear.getSelectedIndex() + 1975;
    }

    public void setBirthDayYear(int birthDayYear) {
        this.birthDayYear.setSelectedIndex(birthDayYear - 1975);
    }

    public int getBirthDayMonth() {
        return this.birthDayMonth.getSelectedIndex();
    }

    public void setBirthDayMonth(int birthDayMonth) {
        this.birthDayMonth.setSelectedIndex(birthDayMonth);
    }

    public int getBirthDayDay() {
        return this.birthDayDay.getSelectedIndex();
    }

    public void setBirthDayDay(int birthDayDay) {
        this.birthDayDay.setSelectedIndex(birthDayDay);
    }

    public Date getBirthDay() {
        return new Date(getBirthDayYear(), getBirthDayMonth(), getBirthDayDay());
    }

    public void setBirthDay(Date birthDayYear) {
        this.birthDayYear.setSelectedIndex(birthDayYear.getYear() - 1975);
        this.birthDayMonth.setSelectedIndex(birthDayYear.getMonth());
        this.birthDayDay.setSelectedIndex(birthDayYear.getDate());
    }

    class SignUpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCancel) {
//                setVisible(false);
//                Personal_Organizer.loginForm.setVisible(true);
                setImageNo(cardLayout, pnlIconLogin);
                setImageYes(cardLayout, pnlIconPassword);
                setImageNo(cardLayout, pnlIconPasswordRepeat);
                setImageYes(cardLayout, pnlIconEmail);
            } else {
                if (e.getSource() == btnSave) {
//                    String command = e.getActionCommand().toLowerCase();
//                    saveUpdateUserProfile(command);
                    setImageYes(cardLayout, pnlIconLogin);
                    //cardLayout.last(pnlIconLogin);
                    setImageNo(cardLayout, pnlIconPassword);
                    //cardLayout.first(pnlIconPassword);
                    setImageYes(cardLayout, pnlIconPasswordRepeat);
                    //cardLayout.last(pnlIconPasswordRepeat);
                    setImageNo(cardLayout, pnlIconEmail);
                    //cardLayout.first(pnlIconEmail);

                }
            }
        }
    }

    class closingListener extends WindowAdapter {

//        @Override
//        public void windowOpened(WindowEvent e) {
//
//        }
//
        @Override
        public void windowClosing(WindowEvent e) {
            setVisible(false);
            Personal_Organizer.loginForm.setVisible(true);
        }

//
//        @Override
//        public void windowClosed(WindowEvent e) {
//
//        }
//
//        @Override
//        public void windowIconified(WindowEvent e) {
//
//        }
//
//        @Override
//        public void windowDeiconified(WindowEvent e) {
//
//        }
//
//        @Override
//        public void windowActivated(WindowEvent e) {
//
//        }
//
//        @Override
//        public void windowDeactivated(WindowEvent e) {
//
//        }
//
    }

    class focusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            setUserProfile();
            if (e.getSource() == txtLoginName) {
                String logon = txtLoginName.getText();
                if (logon.length() < 3) {
                    setImageNo(cardLayout, pnlIconLogin);
                    errLogin.setText("The user name can't be less then 3 characters.");
                    System.out.println("The user name can't be less then 3 characters.");
                } else if (DBFunctions.isTheLoginNameNotUsed()) {
                    setImageYes(cardLayout, pnlIconLogin);
                    errLogin.setText("");
                    System.out.println("Login - Ok");
                } else {
                    setImageNo(cardLayout, pnlIconLogin);
                    errLogin.setText("The user name is already used.");
                    System.out.println("The user name is already used.");
                }
            } else if (e.getSource() == txtPassword) {
                String password = txtPassword.getText();
                if (password.length() < 6) {
                    setImageNo(cardLayout, pnlIconPassword);
                    errPassword.setToolTipText("The password can't be less then 6 characters.");
                    System.out.println("The password can't be less then 6 characters.");
                } else {
                    boolean hasNumber = false;
                    boolean hasCapital = false;
                    boolean hasSmall = false;
                    boolean hasSpecificCharacters = false;
                    System.out.println(hasNumber + " - " + hasCapital + " - " + hasSmall + " - " + hasSpecificCharacters);
                    for (int i = 0; i < password.length(); i++) {
                        if (hasNumber && hasCapital && hasSmall && hasSpecificCharacters) {
                            break;
                        }
                        if (!hasNumber && Character.isDigit(password.charAt(i))) {
                            hasNumber = true;
                        }
                        if (!hasCapital && Character.isUpperCase(password.charAt(i))) {
                            hasCapital = true;
                        }
                        if (!hasSmall && Character.isLowerCase(password.charAt(i))) {
                            hasSmall = true;
                        }
                        //if(!hasNumber && Character.isDigit(password.charAt(i))) hasNumber = true;
                    }
                    String warning = "";
                    if (!hasNumber) {
                        warning = warning + "The password has ro contain numbers.\n";
                    }
                    if (!hasCapital) {
                        warning = warning + "The password has ro contain Upper case letters.\n";
                    }
                    if (!hasSmall) {
                        warning = warning + "The password has ro contain Lower case letters.\n";
                    }
                    if (warning.equals("")) {
                        setImageYes(cardLayout, pnlIconPassword);
                        errPassword.setText("");
                        System.out.println("Password - Ok");
                    } else {
                        setImageNo(cardLayout, pnlIconPassword);
                        errPassword.setText(warning);
                        System.out.println(warning);
                    }
                }
            } else if (e.getSource() == txtPasswordRepeat) {
                if (!txtPassword.getText().equals(txtPasswordRepeat.getText())) {
                    setImageNo(cardLayout, pnlIconPasswordRepeat);
                    errPasswordRepeat.setText("The password and repeat password are not match.");
                    System.out.println("The password and repeat password are not match. "+txtPassword.getText()+" != "+txtPasswordRepeat.getText());
                } else {
                    setImageYes(cardLayout, pnlIconPasswordRepeat);
                    errPasswordRepeat.setText("");
                    System.out.println("Password Repeat - Ok.");
                }
            }

        }

    }

    void setUserProfile() {
        Tools.setUserProfile(this);
    }
}
