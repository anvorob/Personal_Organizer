/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

/**
 *
 * @author Tolik
 */
import com.personal_organizer.calendar.DateListener;
import com.personal_organizer.calendar.JCalendar;
import com.personal_organizer.dao.DAO;
import com.personal_organizer.modules.EventProfile;
import com.personal_organizer.modules.EventType;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

public class MainForm extends JFrame implements ActionListener, ListSelectionListener {

    JMenuBar topMenu;
    JMenu memo;
    JMenu calendar;
    JMenu contacts;
    JMenu messenger;
    JMenu info;

    JMenuItem showMessenger;
    JMenuItem messengerContact;
    JMenuItem myInfo;

    JMenuItem infoAbout;
    JMenuItem infoHelp;

    JPanel pnlCalendar;
    JPanel pnlEvents;
    JPanel pnlEventsControl;
    JPanel pnlEventsTable;
    JPanel pnlEventBtns;
    JPanel pnlStatusBar;

    JTable tblEvents;

    JLabel statusTitle;
    JLabel statusDescript;
    JLabel eventTitle;

    JButton btnEventNext;
    JButton btnEventPrev;
    JButton btnAddEvent;
    JButton btnDeleteEvent;
    JButton btnViewEvent;
    static Date eventsDate;
    JCalendar cal;

    static public Object[] rowData;
    static public int fw, fx, fy;

    MemoForm memoForm;
    ContactListForm contactList;

    Vector<String> columns = new Vector<String>();
    DefaultTableModel mod = new DefaultTableModel(columns, 0);

    public MainForm() {

        columns.add("Title");
        columns.add("Description");
        columns.add("Time From");
        columns.add("Time Till");
        columns.add("Type");
        columns.add("Contacts");

        this.setTitle("Personal Organizer - Welcome " + Personal_Organizer.userProfile.getFirstName());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topMenu = new JMenuBar();
        memo = new JMenu("Memo");
        calendar = new JMenu("Calendar");
        contacts = new JMenu("Contacts");
        messenger = new JMenu("Messenger");
        info = new JMenu("Info");

        messenger.add(showMessenger = new JMenuItem("Show Messenger"));
        messenger.add(messengerContact = new JMenuItem("Contact List"));

        info.add(infoAbout = new JMenuItem("About"));
        info.add(infoHelp = new JMenuItem("Help"));
        info.add(myInfo = new JMenuItem("My Info"));
        myInfo.addActionListener(this);
        myInfo.setToolTipText("Change or Update Info");

        memo.addMenuListener(new MListeners());

        this.setLayout(new BorderLayout());
        topMenu.add(memo);
        topMenu.add(calendar);
        topMenu.add(contacts);
        topMenu.add(messenger);
        topMenu.add(info);

        this.setJMenuBar(topMenu);
        eventTitle = new JLabel("", SwingConstants.CENTER);
        pnlCalendar = new JPanel();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date();
        eventsDate = date;
        eventTitle.setText(dateFormat.format(date));
        //System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48        
        cal = new JCalendar();
        cal.selectDay(date.getYear(), date.getMonth(), date.getDate());

        cal.addDateListener(new DateListener() {

            @Override
            public void dateChanged(Calendar new_c) {
                int day = new_c.get(Calendar.DAY_OF_MONTH);
                int month = new_c.get(Calendar.MONTH) + 1;
                int year = new_c.get(Calendar.YEAR);

                String dateFormat = new SimpleDateFormat("dd/MM/yy").format(new_c.getTime());
                eventTitle.setText(dateFormat);
                eventsDate = new Date(year, month - 1, day);
                int rowCount = mod.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    mod.removeRow(0);
                }
                resetEventsShow();
            }
        });

        this.getContentPane().add(cal, BorderLayout.NORTH);

        pnlEvents = new JPanel(new BorderLayout());
        pnlEventsControl = new JPanel(new BorderLayout());
        Icon backward = new ImageIcon(getClass().getResource("/resources/backward_32.png"));
        Icon forward = new ImageIcon(getClass().getResource("/resources/forward_32.png"));
        btnEventNext = new JButton(forward);
        btnEventPrev = new JButton(backward);
        btnEventNext.setOpaque(false);
        btnEventNext.setContentAreaFilled(false);
        btnEventNext.setBorderPainted(false);
        btnEventNext.addActionListener(this);
        btnEventPrev.setOpaque(false);
        btnEventPrev.setContentAreaFilled(false);
        btnEventPrev.setBorderPainted(false);
        btnEventPrev.addActionListener(this);
        pnlEventsControl.add(btnEventPrev, BorderLayout.WEST);
        pnlEventsControl.add(eventTitle, BorderLayout.CENTER);
        pnlEventsControl.add(btnEventNext, BorderLayout.EAST);

        pnlEventsTable = new JPanel();
        tblEvents = new JTable();
        tblEvents.setModel(mod);
        rowData = new Object[tblEvents.getColumnCount()];
        tblEvents.getSelectionModel().addListSelectionListener(this);
        tblEvents.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scroll = new JScrollPane(tblEvents);
        pnlEventsTable.add(scroll);

        pnlEventBtns = new JPanel(new FlowLayout((int) CENTER_ALIGNMENT, 10, 5));
        pnlEventBtns.add(btnAddEvent = new JButton("Add Event"));
        pnlEventBtns.add(btnDeleteEvent = new JButton("Delete Event"));
        pnlEventBtns.add(btnViewEvent = new JButton("View Event"));

        btnAddEvent.addActionListener(this);
        btnDeleteEvent.addActionListener(this);
        btnViewEvent.addActionListener(this);

        btnDeleteEvent.setEnabled(false);
        btnViewEvent.setEnabled(false);

        pnlEvents.add(pnlEventsControl, BorderLayout.NORTH);
        pnlEvents.add(pnlEventsTable, BorderLayout.CENTER);
        pnlEvents.add(pnlEventBtns, BorderLayout.SOUTH);
        this.add(pnlEvents, BorderLayout.CENTER);

        pnlStatusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        pnlStatusBar.setBackground(Color.GRAY);

        pnlStatusBar.add(statusTitle = new JLabel("Title", SwingConstants.RIGHT));
        pnlStatusBar.add(statusDescript = new JLabel("Description"));

        this.add(pnlStatusBar, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
        getEvents();
        FillTheTable fill = new FillTheTable();
        Thread fillTheTable = new Thread(fill);
        fillTheTable.start();
    }

    private void getEvents() {
        DAO.getEventTypes();
        DAO.getUserEvents();
    }

    public void frameSize() {
        fw = this.getWidth();
        Point p = this.getLocationOnScreen();
        this.setExtendedState(JFrame.NORMAL);
        Rectangle r = this.getBounds();
        fx = (int) r.getX();
        fy = (int) r.getY();
        //System.out.println("width: " + fw + " x: " + fx + " y: " + fy);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddEvent) {
            EventForm event = new EventForm();
            event.setVisible(true);

        }
        if (e.getSource() == infoHelp) {
            Help help = new Help();
            help.setVisible(true);
        }
        if (e.getSource() == infoAbout) {
            About about = new About();
            about.setVisible(true);
        }
        if (e.getSource() == btnDeleteEvent) {
            //System.out.println("Delete");
        }
        if (e.getSource() == btnViewEvent) {
            EventForm event = new EventForm();
            event.setVisible(true);
        } else if (e.getSource() == btnEventPrev) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            eventsDate.setDate(eventsDate.getDate() - 1);
            eventTitle.setText(dateFormat.format(eventsDate));
            cal.selectDay(eventsDate.getYear(), eventsDate.getMonth(), eventsDate.getDate());

            int rowCount = mod.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                mod.removeRow(0);
            }
            resetEventsShow();
        } else if (e.getSource() == btnEventNext) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            eventsDate.setDate(eventsDate.getDate() + 1);
            eventTitle.setText(dateFormat.format(eventsDate));
            cal.selectDay(eventsDate.getYear(), eventsDate.getMonth(), eventsDate.getDate());
            int rowCount = mod.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                mod.removeRow(0);
            }
            resetEventsShow();
        } else if (e.getSource() == myInfo) {
            Personal_Organizer.signUpForm = new SignUpForm();
            Personal_Organizer.signUpForm.setCommand("Update");
            Personal_Organizer.signUpForm.setVisible(true);
        }
    }

    protected void resetEventsShow() {
        for (EventProfile event : Personal_Organizer.events) {
            event.setShow(false);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //System.out.println("This is: "++" : "+tblEvents.getSelectedColumn());
        int row = tblEvents.getSelectedRow();
        //String selectedObject = (String) tblEvents.getModel().getValueAt(tblEvents.getSelectedRow(), tblEvents.getSelectedColumn());
        Object[] rowData = new Object[tblEvents.getColumnCount()];
        String selectedObj = "This is: ";
        btnViewEvent.setEnabled(true);
        btnDeleteEvent.setEnabled(true);
        for (int i = 0; i < tblEvents.getColumnCount(); i++) {
            rowData[i] = tblEvents.getValueAt(row, i);
            selectedObj += tblEvents.getValueAt(row, i);
        }
        //System.out.println(selectedObj);
    }

    class MListeners implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            if (e.getSource() == memo) {
                memoForm = new MemoForm(fw, fx, fy);
                memoForm.setVisible(true);
            }
            if (e.getSource() == contacts) {
                if (contactList == null) {
                    contactList = new ContactListForm();
                    contactList.setVisible(true);
                } else {
                    contactList.setVisible(true);
                    contactList.toFront();
                    contactList.repaint();
                }
            }
        }

        @Override
        public void menuDeselected(MenuEvent e) {
            //System.out.println("Menu deselected");
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            //System.out.println("Menu cenceled");
        }
    }

    class FillTheTable implements Runnable {

        @Override
        public void run() {
            while (true) {
                int i = 0;
                for (EventProfile currentEvent : Personal_Organizer.events) {
                    i++;
                    if (!currentEvent.getShow() && eventsDate.getYear() == currentEvent.getDay().getYear()
                            && eventsDate.getMonth() == currentEvent.getDay().getMonth()
                            && eventsDate.getDate() == currentEvent.getDay().getDate()) {
                        Vector<String> newRow = new Vector<String>();
                        newRow.add(currentEvent.getEventTitle());
                        newRow.add(currentEvent.getDescription());
                        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                        //System.out.println(dateFormat.format(currentEvent.getTimeFrom())); //2014/08/06 15:59:48        
                        newRow.add(dateFormat.format(currentEvent.getTimeFrom()));
                        //System.out.println(dateFormat.format(currentEvent.getTimeTill())); //2014/08/06 15:59:48        
                        newRow.add(dateFormat.format(currentEvent.getTimeTill()));
                        newRow.add(EventType.getEventType(currentEvent.getType()));
                        newRow.add(currentEvent.getContacts());
                        newRow.add("" + Personal_Organizer.events.indexOf(currentEvent));
                        //data[i][5]=currentEvent.geteventTitle();}
                        mod.addRow(newRow);
                        currentEvent.setShow(true);
                    }

                    if (tblEvents.getModel().getRowCount() > 0) {
                        statusTitle.setText("" + tblEvents.getModel().getValueAt(0, 0));
                        statusDescript.setText(": " + tblEvents.getModel().getValueAt(0, 1));
                    } else {
                        statusTitle.setText("");
                        statusDescript.setText(": ");

                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                }
            }
        }

    }
}
