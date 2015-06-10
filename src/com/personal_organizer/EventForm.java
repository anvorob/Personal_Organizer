/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.dao.DAO;
import com.personal_organizer.modules.EventProfile;
import com.personal_organizer.modules.EventType;
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
    JComboBox cbxDateDate, cbxDateMonth, cbxDateYear;
    JComboBox cbxType;
    JPanel pnlEvent;
    //String[] eventTypes = {"", "Business meeting", "Birthday", "Party"};
    String[] contactList = {"", "Anatolii", "Shuaib", "Mikhail"};
    JButton btnSave, btnCancel;
    JLabel errorMessage;
    EventProfile event;

    boolean allFealdsAreFilled = false;
//    String 

    public EventForm() {
        this("New Event", null);
    }

    public EventForm(String title, EventProfile event) {

        this.event = event;
        this.setTitle(title);
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
        pnlEvent.add(scrlPane, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnlEvent.add(new JLabel("Event Date: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        JPanel pnl = new JPanel();
        String[] days = new String[32];
        days[0] = "";
        for (int i = 0; i < 31; i++) {
            days[i + 1] = ((i < 9) ? "0" : "") + Integer.toString(i + 1);
        }
        cbxDateDate = new JComboBox(days);
        cbxDateDate.setSelectedIndex(MainForm.eventsDate.getDate());
        cbxDateDate.setEnabled(false);

        String[] months = new String[13];
        months[0] = "";
        for (int i = 0; i < 12; i++) {
            months[i + 1] = ((i < 9) ? "0" : "") + Integer.toString(i + 1);
        }
        cbxDateMonth = new JComboBox(months);
        cbxDateMonth.setSelectedIndex(MainForm.eventsDate.getMonth() + 1);
        cbxDateMonth.setEnabled(false);

        String[] years = new String[38];
        years[0] = "";
        for (int i = 0; i < 37; i++) {
            int j = i + 2015;
            years[i + 1] = Integer.toString(j);
        }
        cbxDateYear = new JComboBox(years);
        cbxDateYear.setSelectedIndex(MainForm.eventsDate.getYear() - 114);
        cbxDateYear.setEnabled(false);

        pnl.add(cbxDateDate);
        pnl.add(new JLabel("."));
        pnl.add(cbxDateMonth);
        pnl.add(new JLabel("."));
        pnl.add(cbxDateYear);

        pnlEvent.add(pnl, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlEvent.add(new JLabel("Event time start: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        pnl = new JPanel();
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
        gbc.gridy = 5;
        pnlEvent.add(new JLabel("Event time finish: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 5;
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
        gbc.gridy = 6;
        pnlEvent.add(new JLabel("Event type: "), gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 6;
        pnlEvent.add(cbxType = new JComboBox(EventType.getEventTypes()), gbc);

//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        gbc.gridx = 0;
//        gbc.gridy = 6;
//        pnlEvent.add(new JLabel("Related contacts: "), gbc);
//
//        gbc.gridwidth = 1;
//        gbc.gridheight = 1;
//        gbc.gridx = 1;
//        gbc.gridy = 6;
//        pnlEvent.add(cbxContacts = new JComboBox(contactList), gbc);
//
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 7;
        pnl = new JPanel();
        pnl.add(btnSave = new JButton("Save"));
        btnSave.addActionListener(this);

        pnl.add(btnCancel = new JButton("Cancel"));
        btnCancel.addActionListener(this);
        pnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlEvent.add(pnl, gbc);

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 8;
        pnlEvent.add(errorMessage = new JLabel(""), gbc);
        errorMessage.setHorizontalAlignment(JLabel.CENTER);
        errorMessage.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 13));

        this.add(pnlEvent);
        //this.pack();
        this.setSize(new Dimension(330, 386));
        this.setLocationRelativeTo(null);
        CheckFilledFealds check = new CheckFilledFealds();
        check.start();
        if (this.event != null) {
            txtEventTitle.setText(event.getEventTitle());
            txteEventDescription.setText(event.getDescription());
            cbxTimeFromHours.setSelectedIndex(event.getTimeFrom().getHours() + 1);
            cbxTimeFromMinutes.setSelectedIndex(event.getTimeFrom().getMinutes() + 1);
            cbxTimeTillHours.setSelectedIndex(event.getTimeTill().getHours() + 1);
            cbxTimeTillMinutes.setSelectedIndex(event.getTimeTill().getMinutes() + 1);
            for (int i = 1; i < cbxType.getItemCount(); i++) {
                if (cbxType.getItemAt(i).toString().equals(EventType.getEventType(event.getType()))) {
                    cbxType.setSelectedIndex(i);
                    break;
                }
            }
            btnSave.setText("Update");

            cbxDateDate.setEnabled(true);
            cbxDateMonth.setEnabled(true);
            cbxDateYear.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            MainForm.isNotNecessaryToReFill = true;
            this.setVisible(false);
        } else if (e.getSource() == btnSave) {
            Time timeFrom;
            Time timeTill;
            switch (e.getActionCommand()) {
                case "Save":
                    timeFrom = new Time(cbxTimeFromHours.getSelectedIndex() - 1,
                            cbxTimeFromMinutes.getSelectedIndex() - 1, 00);
                    timeTill = new Time(cbxTimeTillHours.getSelectedIndex() - 1,
                            cbxTimeTillMinutes.getSelectedIndex() - 1, 00);
                    event = new EventProfile(Tools.generateCode(10),
                            Personal_Organizer.userProfile.getUserID(),
                            txtEventTitle.getText(),
                            MainForm.eventsDate,
                            timeFrom,
                            timeTill,
                            txteEventDescription.getText(),
                            EventProfile.getTypeID(cbxType.getSelectedItem().toString()));
                    DAO.saveUpdateEvent(event, e.getActionCommand());
                    Personal_Organizer.events.add(event);
                    MainForm.isNecessaryToReFill = true;
                    this.setVisible(false);
                    break;
                case "Update":
                    timeFrom = new Time(cbxTimeFromHours.getSelectedIndex() - 1,
                            cbxTimeFromMinutes.getSelectedIndex() - 1, 00);
                    timeTill = new Time(cbxTimeTillHours.getSelectedIndex() - 1,
                            cbxTimeTillMinutes.getSelectedIndex() - 1, 00);
                    Date newDate = new Date(cbxDateYear.getSelectedIndex() + 114,
                            cbxDateMonth.getSelectedIndex() - 1, 
                            cbxDateDate.getSelectedIndex());
                    event.setEventTitle(txtEventTitle.getText());
                    event.setDay(newDate);
                    event.setTimeFrom(timeFrom);
                    event.setTimeTill(timeTill);
                    event.setDescription(txteEventDescription.getText());
                    event.setType(EventProfile.getTypeID(cbxType.getSelectedItem().toString()));
                    DAO.saveUpdateEvent(event, e.getActionCommand());
                    MainForm.isNecessaryToReFill = true;
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
                } else if (cbxDateDate.getSelectedIndex() == 0
                        || cbxDateMonth.getSelectedIndex() == 0
                        || cbxDateYear.getSelectedIndex() == 0) {
                    message = "Date is not set!";
                } else if (cbxType.getSelectedIndex() == 0) {
                    message = "Event type in not selected!";
                } else {
                    allFealdsAreFilled = true;
                }
                Date date = new Date();
                if (allFealdsAreFilled) {
                    try {
                        Date eventDate = new Date(cbxDateYear.getSelectedIndex() + 2014,
                                cbxDateMonth.getSelectedIndex(), cbxDateDate.getSelectedIndex());
                    } catch (Exception e) {
                        message = "Date is wrong!";
                        allFealdsAreFilled = false;
                    }
                }
                if (allFealdsAreFilled) {
                    if ((date.getYear() > cbxDateYear.getSelectedIndex() + 114)
                            || (date.getYear() == cbxDateYear.getSelectedIndex() + 114)
                            && (date.getMonth() + 1 > cbxDateMonth.getSelectedIndex())
                            || (date.getYear() == cbxDateYear.getSelectedIndex() + 114
                            && date.getMonth() + 1 == cbxDateMonth.getSelectedIndex()
                            && date.getDate() > cbxDateDate.getSelectedIndex())) {
                        message = "Date is in the past!";
                        allFealdsAreFilled = false;
                    } else if ((date.getDate() == cbxDateDate.getSelectedIndex()
                            && date.getMonth() + 1 == cbxDateMonth.getSelectedIndex()
                            && date.getYear() == cbxDateYear.getSelectedIndex() + 114)
                            && (date.getHours() > cbxTimeFromHours.getSelectedIndex() - 1
                            || (date.getHours() == cbxTimeFromHours.getSelectedIndex() - 1
                            && date.getMinutes() > cbxTimeFromMinutes.getSelectedIndex() - 1))) {
                        message = "Time from is in the past!";
                        allFealdsAreFilled = false;
                    }
                }
                if (allFealdsAreFilled) {

//                    else if  {
//                        message = "Event type in not selected!";
//                        allFealdsAreFilled = false;
//                    }
                    errorMessage.setText("Now you can save the event!");
                    errorMessage.setForeground(Color.blue);
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
