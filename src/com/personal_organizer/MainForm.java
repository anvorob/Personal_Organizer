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

    JMenuBar topmenu;
    JMenu memo;
    JMenu calendar;
    JMenu contacts;
    JMenu messenger;
    JMenu info;

    JMenuItem showmessenger;
    JMenuItem messengercontact;
    JMenuItem myInfo;

    JMenuItem infoabout;
    JMenuItem infohelp;

    JPanel pnlcalendar;
    JPanel pnlevents;
    JPanel pnleventscontrol;
    JPanel pnleventstable;
    JPanel pnleventbtns;
    JPanel pnlstatusbar;

    JTable tblevents;

    JLabel statustitle;
    JLabel statusdescript;
    JLabel eventtitle;

    JButton btneventnext;
    JButton btneventprev;
    JButton btnaddevent;
    JButton btndeleteevent;
    JButton btnviewevent;
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

        this.setTitle("MainForm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topmenu = new JMenuBar();
        memo = new JMenu("Memo");
        calendar = new JMenu("Calendar");
        contacts = new JMenu("Contacts");
        messenger = new JMenu("Messenger");
        info = new JMenu("Info");

        messenger.add(showmessenger = new JMenuItem("Show Messenger"));
        messenger.add(messengercontact = new JMenuItem("Contact List"));

        info.add(infoabout = new JMenuItem("About"));
        info.add(infohelp = new JMenuItem("Help"));
        info.add(myInfo = new JMenuItem("My Info"));
        myInfo.addActionListener(this);
        myInfo.setToolTipText("Change or Update Info");

        memo.addMenuListener(new MListeners());

        this.setLayout(new BorderLayout());
        topmenu.add(memo);
        topmenu.add(calendar);
        topmenu.add(contacts);
        topmenu.add(messenger);
        topmenu.add(info);

        this.setJMenuBar(topmenu);
        eventtitle = new JLabel("", SwingConstants.CENTER);
        pnlcalendar = new JPanel();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date();
        eventsDate = date;
        eventtitle.setText(dateFormat.format(date));
        System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48        
        cal = new JCalendar();
        cal.selectDay(date.getYear(), date.getMonth(), date.getDate());

        cal.addDateListener(new DateListener() {

            @Override
            public void dateChanged(Calendar new_c) {
                int day = new_c.get(Calendar.DAY_OF_MONTH);
                int month = new_c.get(Calendar.MONTH) + 1;
                int year = new_c.get(Calendar.YEAR);

                String dateFormat = new SimpleDateFormat("dd/MM/yy").format(new_c.getTime());
                eventtitle.setText(dateFormat);
                eventsDate = new Date(year, month - 1, day);
                int rowCount = mod.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    mod.removeRow(0);
                }
                resetEventsShow();
            }
        });

        this.getContentPane().add(cal, BorderLayout.NORTH);

        pnlevents = new JPanel(new BorderLayout());
        pnleventscontrol = new JPanel(new BorderLayout());
        Icon backward = new ImageIcon(getClass().getResource("/resources/backward_32.png"));
        Icon forward = new ImageIcon(getClass().getResource("/resources/forward_32.png"));
        btneventnext = new JButton(forward);
        btneventprev = new JButton(backward);
        btneventnext.setOpaque(false);
        btneventnext.setContentAreaFilled(false);
        btneventnext.setBorderPainted(false);
        btneventnext.addActionListener(this);
        btneventprev.setOpaque(false);
        btneventprev.setContentAreaFilled(false);
        btneventprev.setBorderPainted(false);
        btneventprev.addActionListener(this);
        pnleventscontrol.add(btneventprev, BorderLayout.WEST);
        pnleventscontrol.add(eventtitle, BorderLayout.CENTER);
        pnleventscontrol.add(btneventnext, BorderLayout.EAST);

        pnleventstable = new JPanel();
        tblevents = new JTable();
        tblevents.setModel(mod);
        rowData = new Object[tblevents.getColumnCount()];
        tblevents.getSelectionModel().addListSelectionListener(this);
        tblevents.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scroll = new JScrollPane(tblevents);
        pnleventstable.add(scroll);

        pnleventbtns = new JPanel(new FlowLayout((int) CENTER_ALIGNMENT, 10, 5));
        pnleventbtns.add(btnaddevent = new JButton("Add Event"));
        pnleventbtns.add(btndeleteevent = new JButton("Delete Event"));
        pnleventbtns.add(btnviewevent = new JButton("View Event"));

        btnaddevent.addActionListener(this);
        btndeleteevent.addActionListener(this);
        btnviewevent.addActionListener(this);

        btndeleteevent.setEnabled(false);
        btnviewevent.setEnabled(false);

        pnlevents.add(pnleventscontrol, BorderLayout.NORTH);
        pnlevents.add(pnleventstable, BorderLayout.CENTER);
        pnlevents.add(pnleventbtns, BorderLayout.SOUTH);
        this.add(pnlevents, BorderLayout.CENTER);

        pnlstatusbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlstatusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        pnlstatusbar.setBackground(Color.GRAY);

        pnlstatusbar.add(statustitle = new JLabel("Title", SwingConstants.RIGHT));
        pnlstatusbar.add(statusdescript = new JLabel("Description"));

        this.add(pnlstatusbar, BorderLayout.SOUTH);

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
        System.out.println("width: " + fw + " x: " + fx + " y: " + fy);
    }

    private String getEventType(int index) {
        String eventType = "";
        switch (index) {
            case 0:
                eventType = "Business meeting";
                break;
            case 1:
                eventType = "Birthday";
                break;
            case 2:
                eventType = "Party";
                break;
        }
        return eventType;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnaddevent) {
            EventForm event = new EventForm();
            event.setVisible(true);

        }
        if (e.getSource() == infohelp) {
            Help help = new Help();
            help.setVisible(true);
        }
        if (e.getSource() == infoabout) {
            About about = new About();
            about.setVisible(true);
        }
        if (e.getSource() == btndeleteevent) {
            System.out.println("Delete");
        }
        if (e.getSource() == btnviewevent) {
            EventForm event = new EventForm();
            event.setVisible(true);
        } else if (e.getSource() == btneventprev) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            eventsDate.setDate(eventsDate.getDate() - 1);
            eventtitle.setText(dateFormat.format(eventsDate));
            cal.selectDay(eventsDate.getYear(), eventsDate.getMonth(), eventsDate.getDate());

            int rowCount = mod.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                mod.removeRow(0);
            }
            resetEventsShow();
        } else if (e.getSource() == btneventnext) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            eventsDate.setDate(eventsDate.getDate() + 1);
            eventtitle.setText(dateFormat.format(eventsDate));
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
        //System.out.println("This is: "++" : "+tblevents.getSelectedColumn());
        int row = tblevents.getSelectedRow();
        //String selectedObject = (String) tblevents.getModel().getValueAt(tblevents.getSelectedRow(), tblevents.getSelectedColumn());
        Object[] rowData = new Object[tblevents.getColumnCount()];
        String selectedObj = "This is: ";
        btnviewevent.setEnabled(true);
        btndeleteevent.setEnabled(true);
        for (int i = 0; i < tblevents.getColumnCount(); i++) {
            rowData[i] = tblevents.getValueAt(row, i);
            selectedObj += tblevents.getValueAt(row, i);
        }
        System.out.println(selectedObj);
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
                        System.out.println(dateFormat.format(currentEvent.getTimeFrom())); //2014/08/06 15:59:48        
                        newRow.add(dateFormat.format(currentEvent.getTimeFrom()));
                        System.out.println(dateFormat.format(currentEvent.getTimeTill())); //2014/08/06 15:59:48        
                        newRow.add(dateFormat.format(currentEvent.getTimeTill()));
                        newRow.add(EventType.getEventType(currentEvent.getType()));
                        newRow.add(currentEvent.getContacts());
                        newRow.add("" + Personal_Organizer.events.indexOf(currentEvent));
                        //data[i][5]=currentEvent.getEventTitle();}
                        mod.addRow(newRow);
                        currentEvent.setShow(true);
                    }

                    if (tblevents.getModel().getRowCount() > 0) {
                        statustitle.setText("" + tblevents.getModel().getValueAt(0, 0));
                        statusdescript.setText(": " + tblevents.getModel().getValueAt(0, 1));
                    } else {
                        statustitle.setText("");
                        statusdescript.setText(": ");

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
