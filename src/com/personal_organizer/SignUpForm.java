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
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
    private static String[] imageList = {"no.png", "yes.png"};
    private int r, g, b;
    boolean isLoginNameOk, isPasswordOk, isPasswordRepeatOk, isEmailOk;

    {
        isLoginNameOk = false;
        isPasswordOk = false;
        isPasswordRepeatOk = false;
        isEmailOk = false;
        r = 239;
        g = 239;
        b = 239;
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
                pnlPersonalInformation.add(lbl1 = new JLabel("Login Name: *"), gbc);
                lbl1.addMouseListener(new mouseListener());

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtLoginName = new JTextField("", 10), gbc);
                //txtLoginName.setText("mic");
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
                //txtPassword.setText("micmic");
                txtPassword.addFocusListener(new focusListener());
                System.out.println("txtPassword.getText() = " + txtPassword.getText());
                System.out.println("txtPassword.getPassword() = " + txtPassword.getPassword());

                gbc.gridx = 0;
                pnlPersonalInformation.add(lbl2 = new JLabel("Password: *"), gbc);
                lbl2.addMouseListener(new mouseListener());

                gbc.gridy = 3;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                pnlPersonalInformation.add(errPassword = new OLabel(), gbc);
                gbc.gridwidth = 1;

                gbc.gridx = 0;
                gbc.gridy = 4;
                pnlPersonalInformation.add(lbl3 = new JLabel("Repeat Password: *"), gbc);
                lbl3.addMouseListener(new mouseListener());

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtPasswordRepeat = new JPasswordField("", 10), gbc);
                txtPasswordRepeat.addFocusListener(new focusListener());
                //txtPasswordRepeat.setText("123");

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
                pnlPersonalInformation.add(lbl4 = new JLabel("First Name: "), gbc);
                lbl4.addMouseListener(new mouseListener());

                gbc.gridy = 7;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                pnlPersonalInformation.add(new OLabel(), gbc);
                gbc.gridwidth = 1;

                gbc.gridx = 0;
                gbc.gridy = 8;
                pnlPersonalInformation.add(lbl5 = new JLabel("Last Name: "), gbc);
                lbl5.addMouseListener(new mouseListener());

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
                pnlPersonalInformation.add(lbl6 = new JLabel("Phone No.: "), gbc);
                lbl6.addMouseListener(new mouseListener());

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
                txtEmail.addFocusListener(new focusListener());
                //txtEmail.setText("mixnov@bk.ru");

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

        txtPasswordRepeat.setEnabled(false);
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

    public void setCommand(String command) {
        btnSave.setText(command);
        UserProfile userProfile = Personal_Organizer.userProfile;
        if (command == "Update") {
            txtLoginName.setText(userProfile.getLoginName());
            if (userProfile.getFirstName() != null) {
                txtFirstName.setText(userProfile.getFirstName());
            }
            if (userProfile.getLastName() != null) {
                txtLastName.setText(userProfile.getLastName());
            }
            if (userProfile.getPhone() != null) {
                txtPhone.setText(userProfile.getPhone());
            }
            txtEmail.setText(userProfile.getUserEmail());
            txtPassword.setText(userProfile.getPassword());
            txtPasswordRepeat.setText(userProfile.getPassword());
            System.out.println(userProfile.getPassword());
            System.out.println(txtPassword.getText());
            System.out.println(txtPasswordRepeat.getText());
            txtLoginName.setEnabled(false);
            setImageYes(cardLayout, pnlIconLogin);
            setImageYes(cardLayout, pnlIconPassword);
            setImageYes(cardLayout, pnlIconPasswordRepeat);
            setImageYes(cardLayout, pnlIconEmail);
        }
    }

    class SignUpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCancel) {
                setVisible(false);
                if (btnSave.getText().equals("Save")) {
                    Personal_Organizer.loginForm.setVisible(true);
                }
            } else {
                if (e.getSource() == btnSave) {
                    String command = e.getActionCommand().toLowerCase();
                    saveUpdateUserProfile(command);
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
            if (btnSave.getText().equals("Update")) {
                Personal_Organizer.mainform.setVisible(true);
            } else {
                Personal_Organizer.loginForm.setVisible(true);
            }
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
                    errPassword.setText("The password can't be less then 6 characters.");
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
                        warning = warning + " numbers,";
                    }
                    if (!hasCapital) {
                        warning = warning + " Upper case letters,";
                    }
                    if (!hasSmall) {
                        warning = warning + " Lower case letters.";
                    }
                    if (warning.equals("")) {
                        setImageYes(cardLayout, pnlIconPassword);
                        errPassword.setText("");
                        txtPasswordRepeat.setEnabled(true);
                        System.out.println("Password - Ok");
                    } else {
                        txtPasswordRepeat.setEnabled(false);
                        warning = "The password doesn't contain" + warning;
                        warning = warning.substring(0, warning.length() - 1) + ".";
                        setImageNo(cardLayout, pnlIconPassword);
                        errPassword.setText(warning);
                        System.out.println(warning);
                    }
                }
            } else if (e.getSource() == txtPasswordRepeat) {
                if (!txtPassword.getText().equals(txtPasswordRepeat.getText())) {
                    setImageNo(cardLayout, pnlIconPasswordRepeat);
                    errPasswordRepeat.setText("The password and repeat password are not match.");
                    System.out.println("The password and repeat password are not match. " + txtPassword.getText() + " != " + txtPasswordRepeat.getText());
                } else {
                    setImageYes(cardLayout, pnlIconPasswordRepeat);
                    errPasswordRepeat.setText("");
                    System.out.println("Password Repeat - Ok.");
                }
            } else if (e.getSource() == txtEmail) {
                int at = 0;
                String eMail = txtEmail.getText();
                String warning = "";
                if (eMail.equals("")) {
                    setImageNo(cardLayout, pnlIconEmail);
                    errEmail.setToolTipText("You have to enter E-Mail address.");
                    warning = "You have to enter E-Mail address.";
                    System.out.println("You have to enter E-Mail address.");
                } else if (eMail.indexOf('.') == -1) {
                    warning = "The '.' sibbol is missing!";
                    System.out.println("The '.' sibbol is missing!");
                } else if ((eMail.indexOf(',') >= 0) || (eMail.indexOf(';') >= 0) || (eMail.indexOf(' ') >= 0)) {
                    warning = "E-mail address is incorrect!";
                    System.out.println("E-mail address is incorrect!");
                } else {
                    at = eMail.indexOf('@');
                    if (at == -1) {
                        warning = "The '@' simbol is missing!";
                        System.out.println("The '@' simbol is missing!");
                    } else if ((at < 1) || (at > eMail.length() - 5)) {
                        warning = "E-mail address is incorrect!";
                        System.out.println("E-mail address is incorrect!");
                    } else if ((eMail.charAt(at - 1) == '.') || (eMail.charAt(at + 1) == '.')) {
                        warning = "E-mail address is incorrect!";
                        System.out.println("E-mail address is incorrect!");
                    }

                }
                if (warning.equals("")) {
                    errEmail.setText("");
                    cardLayout.last(pnlIconEmail);
                } else {
                    errEmail.setText(warning);
                    cardLayout.first(pnlIconEmail);
                }
            }

        }

    }

    void setUserProfile() {
        Tools.setUserProfile(this);
    }

    class mouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
//            Object ee = e.getSource();
//            if(ee == lbl1){
//                r++;
//                if(r == 256) r = 255;
//                lbl1.setText(""+r);
//            } else if(ee == lbl2){
//                g++;
//                if(g == 256) g = 255;
//                lbl2.setText(""+g);
//            } else if(ee == lbl3){
//                b++;
//                if(b == 256) b = 255;
//                lbl3.setText(""+b);
//            } else if(ee == lbl4){
//                r--;
//                if(r == -1) r = 0;
//                lbl4.setText(""+r);
//            } else if(ee == lbl5){
//                g--;
//                if(g == -1) g = 0;
//                lbl5.setText(""+g);
//            } else if(ee == lbl6){
//                b--;
//                if(b == -1) b = 0;
//                lbl6.setText(""+b);
//            }
//            errLogin.setForeground(new Color(r, g, b));
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

    }
}
