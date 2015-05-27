/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.dao.DAO;
import com.personal_organizer.db.DBFunctions;
import com.personal_organizer.modules.Tools;
import java.sql.Date;
//import java.util.Date;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JComboBox birthDayYear, birthDayMonth, birthDayDay;

    private JScrollPane scrollPane;

    private JButton btnCancel, btnSave;

    private ImageIcon iconLogin, iconPassword, iconPasswordRepeat, iconEmail;
    private static String[] imageList = {"yes.png", "yes.png"};

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

                gbc.gridx = 2;
                pnlPersonalInformation.add(new JLabel(iconLogin = new ImageIcon(imageList[0])), gbc);

                gbc.gridy = 1;
                pnlPersonalInformation.add(new JLabel(iconPassword = new ImageIcon(imageList[0])), gbc);

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtPassword = new JPasswordField("", 10), gbc);
                txtPassword.setText("micmic");

                gbc.gridx = 0;
                pnlPersonalInformation.add(new JLabel("Password: *"), gbc);

                gbc.gridy = 2;
                pnlPersonalInformation.add(new JLabel("Repeat Password: *"), gbc);

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtPasswordRepeat = new JPasswordField("", 10), gbc);
                txtPasswordRepeat.setText("micmic");

                gbc.gridx = 2;
                pnlPersonalInformation.add(new JLabel(iconPasswordRepeat = new ImageIcon(imageList[0])), gbc);

                gbc.gridy = 3;
                gbc.gridx = 1;
                pnlPersonalInformation.add(txtFirstName = new JTextField("", 10), gbc);

                gbc.gridx = 0;
                pnlPersonalInformation.add(new JLabel("First Name: "), gbc);

                gbc.gridy = 4;
                pnlPersonalInformation.add(new JLabel("Last Name: "), gbc);

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtLastName = new JTextField("", 10), gbc);

                gbc.gridy = 5;
                pnlPersonalInformation.add(txtPhone = new JTextField("", 10), gbc);

                gbc.gridx = 0;
                pnlPersonalInformation.add(new JLabel("Phone No.: "), gbc);

                gbc.gridy = 6;
                pnlPersonalInformation.add(new JLabel("E-mail: *"), gbc);

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtEmail = new JTextField("", 10), gbc);
                txtEmail.setText("mixnov@bk.ru");

                gbc.gridx = 2;
                pnlPersonalInformation.add(new JLabel(iconEmail = new ImageIcon(imageList[0])), gbc);

                gbc.gridy = 7;
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

    class SignUpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCancel) {
                setVisible(false);
                Personal_Organizer.loginForm.setVisible(true);
            } else {
                if (e.getSource() == btnSave) {
                    saveUpdateUserProfile(e.getActionCommand().toLowerCase());
                }
            }
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

    class closingListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            setVisible(false);
            Personal_Organizer.loginForm.setVisible(true);
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
