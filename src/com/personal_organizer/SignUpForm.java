/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Mikhail
 */
public class SignUpForm extends JFrame {

    JTextField txtFirstName, txtLastName;
    JTextField txtPhone, txtEmail, txtLoginName;
    JPasswordField txtPassword, txtPassword1;

    JComboBox birthDayYear, birthDayMonth, birthDayDay;

    JScrollPane scrollPane;

    JButton btnCancel, btnSave;
    
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

                
                gbc.gridy = 1;
                pnlPersonalInformation.add(txtPassword = new JPasswordField("", 10), gbc);

                gbc.gridx = 0;
                pnlPersonalInformation.add(new JLabel("Password: *"), gbc);

                gbc.gridy = 2;
                pnlPersonalInformation.add(new JLabel("Repeat Password: *"), gbc);

                gbc.gridx = 1;
                pnlPersonalInformation.add(txtPassword1 = new JPasswordField("", 10), gbc);

                gbc.gridy = 3;
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

                gbc.gridy = 7;
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
        //btnCancel.addActionListener(new CanceListener());

        //gbc.gridx = 2;
        pnlButtons.add(btnSave = new JButton("Save"));
        //btnSave.addActionListener(new SaveListener());

        this.add(scrollPane = new JScrollPane(pnlFields), BorderLayout.CENTER);
        //scrollPane.setBounds(0, 0, 500, 700);
        this.add(pnlButtons, BorderLayout.SOUTH);

        //this.setSize(550, 180);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }

}
