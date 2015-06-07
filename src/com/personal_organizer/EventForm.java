/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public EventForm() {
        this("New Event");
    }

    public EventForm(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        pnlEvent = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(new JLabel("Event title: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(txtEventTitle = new JTextField(10), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(new JLabel("Event description: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(txteEventDescription = new JTextArea(4, 10), gbc);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        txteEventDescription.setBorder(border);
        txteEventDescription.setWrapStyleWord(true);
        txteEventDescription.setLineWrap(true);
        txteEventDescription.setColumns(18);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(new JLabel("Event time start: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
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
        pnl.add(cbxTimeFromMinutes);
        pnlEvent.add(pnl, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(new JLabel("Event time finish: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnl = new JPanel();
        cbxTimeTillHours = new JComboBox(hours);
        cbxTimeTillMinutes = new JComboBox(minutes);
        pnl.add(cbxTimeTillHours);
        pnl.add(cbxTimeTillMinutes);
        pnlEvent.add(pnl, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(new JLabel("Event type: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(cbxType = new JComboBox(eventTypes), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(new JLabel("Related contacts: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(cbxContacts = new JComboBox(contactList), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(btnSave = new JButton("Save"), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlEvent.add(btnCancel = new JButton("Cancel"), gbc);

//        gbc.gridwidth = 2;
//        gbc.gridheight = 1;
//        gbc.gridx = 0;
//        gbc.gridy = 7;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        pnlEvent.add(errorMessage = new JLabel(""), gbc);
//        errorMessage.setForeground(Color.red);
        
        this.add(pnlEvent);
        this.pack();
        this.setLocationRelativeTo(null);
        CheckFilledFealds check = new CheckFilledFealds();
        check.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            this.setVisible(false);
        } else if (e.getSource() == btnSave) {
            if (e.getActionCommand().equals("Save")) {
                this.setVisible(false);

            } else if (e.getActionCommand().equals("Update")) {
                this.setVisible(false);

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
                } else if (cbxTimeFromHours.getSelectedIndex() == 0 ||
                        cbxTimeFromMinutes.getSelectedIndex() == 0) {
                    message = "Time from is not set!";
                } else if (cbxTimeTillHours.getSelectedIndex() == 0 ||
                        cbxTimeTillMinutes.getSelectedIndex() == 0) {
                    message = "Time till is not set!";
                } else if (cbxTimeFromHours.getSelectedIndex() > 
                        cbxTimeTillHours.getSelectedIndex()
                        || (cbxTimeFromHours.getSelectedIndex() == 
                        cbxTimeTillHours.getSelectedIndex()
                        && cbxTimeFromMinutes.getSelectedIndex() > 
                        cbxTimeTillMinutes.getSelectedIndex())) {
                    message = "Time till is less then from!";
                } else if (cbxType.getSelectedIndex() == 0) {
                    message = "Event type in not selected!";
                } else {
                    allFealdsAreFilled = true;
                }
                btnSave.setEnabled(allFealdsAreFilled);
                btnSave.setToolTipText(message);
                errorMessage.setText(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("Error");
                }
                        
            }
        }

    }

}
