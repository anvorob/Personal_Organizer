/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.dao.DAO;
import com.personal_organizer.modules.EventProfile;
import com.personal_organizer.modules.Tools;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Time;
//import java.sql.Date;
//import java.sql.Time;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;

/**
 *
 * @author Tolik
 */
public class EventForm extends JFrame implements ActionListener {

    JTextField txtEventTitle;
    JTextArea txteEventDescription;
    JComboBox cbxTimeFromHours, cbxTimeFromMinutes;
    JComboBox cbxTimeTillHours, cbxTimeTillMinutes;
    JComboBox cbxContacts, cbxType;
    JPanel pnlEvent;
    String[] eventTypes = {"", "Business meeting", "Birthday", "Party"};
    String[] contactList = {"", "Anatolii", "Shuaib", "Mikhail"};
    JButton btnSave, btnCancel;
    JLabel errorMessage;
    boolean allFealdsAreFilled = false;
    Date eventDate;

    public EventForm(Date eventDate) {
        this(eventDate, "New Event");
    }

    public EventForm(Date eventDate, String title) {

//        this.getRootPane().addComponentListener(new ComponentAdapter() {
//            public void componentResized(ComponentEvent e) {
//                // This is only called when the user releases the mouse button.
//                System.out.println("componentResized\nWidth = " + getWidth() + " Height = " + getHeight());
//            }
//        });
        this.eventDate = eventDate;
        this.setTitle(title);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        if (Personal_Organizer.loginForm == null) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        pnlEvent = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        pnlEvent.add(new JLabel("Event title: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        pnlEvent.add(txtEventTitle = new JTextField(10), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlEvent.add(new JLabel("Event description: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        txteEventDescription = new JTextArea(4, 10);
        txteEventDescription.setWrapStyleWord(true);
        txteEventDescription.setLineWrap(true);
        JScrollPane scrlPane = new JScrollPane(txteEventDescription);
//        scrlPane.setSize(new Dimension(10, 4));
        pnlEvent.add(scrlPane, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnlEvent.add(new JLabel("Event time start: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        JPanel pnl = new JPanel();
        String[] hours = new String[25];
        hours[0] = "";
        for (int i = 0; i < 24; i++) {
            hours[i + 1] = ((i < 10) ? "0" : "") + Integer.toString(i);
        }
        cbxTimeFromHours = new JComboBox(hours);
        String[] minutes = new String[61];
        minutes[0] = "";
        for (int i = 0; i < 60; i++) {
            minutes[i + 1] = ((i < 10) ? "0" : "") + Integer.toString(i);
        }
        cbxTimeFromMinutes = new JComboBox(minutes);
        pnl.add(cbxTimeFromHours);
        pnl.add(new JLabel(" : "));
        pnl.add(cbxTimeFromMinutes);
        pnlEvent.add(pnl, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlEvent.add(new JLabel("Event time finish: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        pnl = new JPanel();
        cbxTimeTillHours = new JComboBox(hours);
        cbxTimeTillMinutes = new JComboBox(minutes);
        pnl.add(cbxTimeTillHours);
        pnl.add(new JLabel(" : "));
        pnl.add(cbxTimeTillMinutes);
        pnlEvent.add(pnl, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        pnlEvent.add(new JLabel("Event type: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 5;
        pnlEvent.add(cbxType = new JComboBox(eventTypes), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 6;
        pnlEvent.add(new JLabel("Related contacts: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 6;
        pnlEvent.add(cbxContacts = new JComboBox(contactList), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 7;
        pnlEvent.add(btnSave = new JButton("Save"), gbc);
        btnSave.addActionListener(this);
        
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 7;
        pnlEvent.add(btnCancel = new JButton("Cancel"), gbc);
        btnCancel.addActionListener(this);

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 8;
        pnlEvent.add(errorMessage = new JLabel(""), gbc);
        errorMessage.setHorizontalAlignment(JLabel.CENTER);
        errorMessage.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 11));

        this.add(pnlEvent);
        //this.pack();
        this.setSize(new Dimension(279, 369));
        this.setLocationRelativeTo(null);
        CheckFilledFealds check = new CheckFilledFealds();
        check.start();
        txtEventTitle.setText("New Event");
        txteEventDescription.setText("New Event");
        cbxTimeFromHours.setSelectedIndex(1);
        cbxTimeFromMinutes.setSelectedIndex(1);
        cbxTimeTillHours.setSelectedIndex(1);
        cbxTimeTillMinutes.setSelectedIndex(1);
        cbxType.setSelectedIndex(1);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            this.setVisible(false);
        } else if (e.getSource() == btnSave) {
            switch (e.getActionCommand()) {
                case "Save":
                    Time timeFrom = new Time(cbxTimeFromHours.getSelectedIndex()-1, cbxTimeFromMinutes.getSelectedIndex()-1, 00);
                    Time timeTill = new Time(cbxTimeTillHours.getSelectedIndex()-1, cbxTimeTillMinutes.getSelectedIndex()-1, 00);
                    EventProfile event = new EventProfile(Personal_Organizer.userProfile.getUserID(),
                            Tools.generateCode(10), txtEventTitle.getText(),
                            eventDate,
                            timeFrom,
                            timeTill,
                            txteEventDescription.getText(), cbxType.getSelectedIndex(),
                            contactList);
                    DAO.saveUpdateEvent(event, e.getActionCommand());
                    Personal_Organizer.events.add(event);
                    this.setVisible(false);
                    break;
                case "Update":
                    this.setVisible(false);
                    break;
            }
        }
    }

    class CheckFilledFealds extends Thread {

        @Override
        public void run() {
            String message;
            while (true) {
                allFealdsAreFilled = false;
                message = "";
                if (txtEventTitle.getText().equals("")) {
                    message = "Title feald is empty!";
                } else if (txteEventDescription.getText().equals("")) {
                    message = "Description feald is empty!";
                } else if (cbxTimeFromHours.getSelectedIndex() == 0
                        || cbxTimeFromMinutes.getSelectedIndex() == 0) {
                    message = "Time from is not set!";
                } else if (cbxTimeTillHours.getSelectedIndex() == 0
                        || cbxTimeTillMinutes.getSelectedIndex() == 0) {
                    message = "Time till is not set!";
                } else if (cbxTimeFromHours.getSelectedIndex()
                        > cbxTimeTillHours.getSelectedIndex()
                        || (cbxTimeFromHours.getSelectedIndex()
                        == cbxTimeTillHours.getSelectedIndex()
                        && cbxTimeFromMinutes.getSelectedIndex()
                        > cbxTimeTillMinutes.getSelectedIndex())) {
                    message = "Time till is less then from!";
                } else if (cbxType.getSelectedIndex() == 0) {
                    message = "Event type in not selected!";
                } else {
                    allFealdsAreFilled = true;
                }
                btnSave.setEnabled(allFealdsAreFilled);
                if (allFealdsAreFilled) {
                    errorMessage.setText("Now you can save the event!");
                    errorMessage.setForeground(Color.blue);
                } else {
                    errorMessage.setText(message);
                    errorMessage.setForeground(Color.red);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
            }
        }
    }

}
