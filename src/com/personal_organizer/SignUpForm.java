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
    JTextField txtPhone, txtEmail;

    JComboBox birthDayYear, birthDayMonth, birthDayDay;

    JScrollPane scrollPane;

    JButton btnCancel, btnSave;
    
    public SignUpForm() {
        this.setTitle("Cornell Application Form");
        GridBagConstraints gbc = new GridBagConstraints();
        TitledBorder title = BorderFactory.createTitledBorder("STUDENT DETAILS");

        JPanel pnlFields = new JPanel();
        {
            pnlFields.setLayout(new GridBagLayout());

            JPanel pnlStudentDetails = new JPanel();
            {
                pnlStudentDetails.setLayout(new GridBagLayout());

                pnlStudentDetails.setBorder(title);

                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(5, 10, 5, 10);
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.gridx = 0;
                gbc.gridy = 0;
                pnlStudentDetails.add(new JLabel("First Name: "), gbc);

                gbc.gridx = 1;
                pnlStudentDetails.add(txtFirstName = new JTextField("", 10), gbc);
                //txtFirstName.setInputVerifier(new InputVerifier);
                gbc.gridx = 2;
                pnlStudentDetails.add(new JLabel("Last Name: "), gbc);

                gbc.gridx = 3;
                pnlStudentDetails.add(txtLastName = new JTextField("", 10), gbc);

                gbc.gridy = 3;

                gbc.gridx = 2;

                //pnlBankCard.add(bankCardExpiryDateMonth = new JComboBox(months));
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

                gbc.gridx = 1;
                pnlStudentDetails.add(pnlBirthDay, gbc);

                gbc.gridx = 0;
                pnlStudentDetails.add(new JLabel("Date of Birth: "), gbc);


                gbc.gridy = 4;
                gbc.gridwidth = 4;


                gbc.gridy = 5;

                gbc.gridy = 6;

                gbc.gridy = 7;
                gbc.gridwidth = 3;
                pnlStudentDetails.add(new JLabel("If you are registered with NZQA and have an"
                        + " NSN please write it here: "), gbc);
                gbc.gridx = 3;
                gbc.gridwidth = 1;

                gbc.gridwidth = 4;
                gbc.gridx = 0;
                gbc.gridy = 0;
                pnlFields.add(pnlStudentDetails, gbc);
            }
        }
        
        
        
        
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        gbc.gridy = 2;
        //pnlButtons.add(btnUpdate = new JButton("Update"));
        //btnUpdate.addActionListener(new UpdateListener());

        gbc.gridx = 1;
        pnlButtons.add(btnCancel = new JButton("Cancel"));
        //btnCancel.addActionListener(new CanceListener());

        gbc.gridx = 2;
        pnlButtons.add(btnSave = new JButton("Save"));
        //btnSave.addActionListener(new SaveListener());

        this.add(scrollPane = new JScrollPane(pnlFields), BorderLayout.CENTER);
        //scrollPane.setBounds(0, 0, 500, 700);
        this.add(pnlButtons, BorderLayout.SOUTH);

        this.setSize(650, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }

}
