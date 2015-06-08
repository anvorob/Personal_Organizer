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
    Date eventsDate;

    static public Object[] rowData;
    static public int fw, fx, fy;

    MemoForm memoForm;
    ContactListForm contactList;

    //String columns[] = {"Title", "Description", "Time From", "Time Till", "Type", "Contacts"};
    Vector<String> columns = new Vector<String>();
    DefaultTableModel mod = new DefaultTableModel(columns, 0);
    int numberOfRows;

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

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        eventsDate = date;
        eventtitle.setText(dateFormat.format(date));
        System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48        
        JCalendar cal = new JCalendar(date.getDate() - 1, date.getMonth(), date.getYear() - 100);
        //DateListener cl;
        cal.addDateListener(new DateListener() {

            @Override
            public void dateChanged(Calendar new_c) {
                int day = new_c.get(Calendar.DAY_OF_MONTH);
                int month = new_c.get(Calendar.MONTH) + 1;
                int year = new_c.get(Calendar.YEAR);

                System.out.println("Selected: " + new_c.getTime() + " (dd/MM/yyyy)");
                String dateFormat = new SimpleDateFormat("dd/MM/yyyy").format(new_c.getTime());
                System.out.println("Selected: " + dateFormat + " (dd/MM/yyyy)");
                dateFormat = ((("" + day).length() == 1) ? "0" : "") + day + "/"
                        + ((("" + month).length() == 1) ? "0" : "") + month + "/"
                        + year;
                System.out.println("Selected: " + dateFormat + " (DD/MM/YYYY)");
                eventtitle.setText(dateFormat);
                eventsDate = new Date(year, month, day);
            }

        });
        //cl.dateChanged(new Calendar);
//                String dateFormat = new SimpleDateFormat("dd/MM/yyyy").format(new_c.getTime());
//                System.out.println("Selected: " + date + " (dd/MM/yyyy)");
//                dateFormat = ((("" + day).length() == 1) ? "0" : "") + day + "/"
//                        + ((("" + month).length() == 1) ? "0" : "") + month + "/"
//                        + year;
//                System.out.println("Selected: " + dateFormat + " (DD/MM/YYYY)");

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
        //eventtitle=new JLabel("Title");
        pnleventscontrol.add(btneventprev, BorderLayout.WEST);
        pnleventscontrol.add(eventtitle, BorderLayout.CENTER);
        pnleventscontrol.add(btneventnext, BorderLayout.EAST);

        pnleventstable = new JPanel();
//        Object[][] data = new Object[5][6];
//                int i = 0;
//
//                data[i][0] = "Business meeting";
//                data[i][4] = "Meeting";
//                data[i][5] = "Tom";
//        
//        tblevents = new JTable(data, columns);
        tblevents = new JTable();
        tblevents.setModel(mod);
        Vector<String> newRow = new Vector<String>();
        newRow.add("AaValue");
        newRow.add("BbValue");
        newRow.add("CcValue");
        newRow.add("DdValue");
        mod.addRow(newRow);
        numberOfRows = 1;
        rowData = new Object[tblevents.getColumnCount()];
        tblevents.getSelectionModel().addListSelectionListener(this);
        tblevents.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        System.out.println("" + tblevents.getModel().getValueAt(0, 2));

//            tblevents.getColumnModel().getColumn(0).setPreferredWidth(27);
//            tblevents.getColumnModel().getColumn(1).setPreferredWidth(120);
//            tblevents.getColumnModel().getColumn(2).setPreferredWidth(100);
//            tblevents.getColumnModel().getColumn(3).setPreferredWidth(90);
//            tblevents.getColumnModel().getColumn(4).setPreferredWidth(90);
//            tblevents.getColumnModel().getColumn(6).setPreferredWidth(120);
//            tblevents.getColumnModel().getColumn(7).setPreferredWidth(100);
//            tblevents.getColumnModel().getColumn(8).setPreferredWidth(95);
//            tblevents.getColumnModel().getColumn(9).setPreferredWidth(40);
//            tblevents.getColumnModel().getColumn(10).setPreferredWidth(400);
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

        statustitle.setText("" + tblevents.getModel().getValueAt(0, 0));
        statusdescript.setText(": " + tblevents.getModel().getValueAt(0, 1));
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
//        fx=this.getLocation().x;
//        fy=this.getLocation().y;
//            fx=p.x;
//            fy=p.y;
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
            EventForm event = new EventForm(eventsDate);
            event.setVisible(true);
//            FillTheTable fill = new FillTheTable();
//            Thread fillTheTable = new Thread(fill);
//            fillTheTable.start();

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
            EventForm event = new EventForm(eventsDate);
            event.setVisible(true);
        } else if (e.getSource() == btneventprev) {
//           Icon backward = new ImageIcon(getClass().getResource("/resources/backward_32_pressed.png"));
//           btneventprev=new JButton(backward);
            System.out.println("Pressed prev");
        } else if (e.getSource() == btneventnext) {
//            Icon forward =new ImageIcon(getClass().getResource("/resources/forward_32_pressed.png"));
//            btneventnext=new JButton(forward);
            System.out.println("Pressed next");
        } else if (e.getSource() == myInfo) {
            Personal_Organizer.signUpForm = new SignUpForm();
            Personal_Organizer.signUpForm.setCommand("Update");
            //this.setVisible(false);
            Personal_Organizer.signUpForm.setVisible(true);
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
            System.out.println("Menu deselected");
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            System.out.println("Menu cenceled");
        }

    }

    class FillTheTable implements Runnable {

        @Override
        public void run() {
            while (true) {
                Object[][] data = new Object[10][6];
//                data = {
//                    {"Business meeting"
//                    
//                    
//                , "140 Hobson", "9:00", "10:00", "Meeting", "Tom"}};
                if (numberOfRows < 2) {

                    Vector<String> newRow = new Vector<String>();
                    newRow.add("Business meeting");
                    newRow.add("140 Hobson");
                    newRow.add("09:00");
                    newRow.add("10:00");
                    newRow.add("Meeting");
                    newRow.add("Tom");
                    mod.addRow(newRow);
                    numberOfRows++;
                }
//                data[i][0] = "Business meeting";
//                data[i][1] = "140 Hobson";
//                data[i][2] = "09:00";
//                data[i][3] = "10:00";
//                data[i][4] = "Meeting";
//                data[i][5] = "Tom";
//                
//
                int i = 0;
                if (Personal_Organizer.events.size() > numberOfRows - 2) {
                    for (EventProfile currentEvent : Personal_Organizer.events) {
                        i++;
                        if (i > numberOfRows - 2) {
                            Vector<String> newRow = new Vector<String>();
                            newRow.add(currentEvent.getEventTitle());
                            newRow.add(currentEvent.getDescription());
                            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                            System.out.println(dateFormat.format(currentEvent.getTimeFrom())); //2014/08/06 15:59:48        
                            newRow.add(dateFormat.format(currentEvent.getTimeFrom()));
                            System.out.println(dateFormat.format(currentEvent.getTimeTill())); //2014/08/06 15:59:48        
                            newRow.add(dateFormat.format(currentEvent.getTimeTill()));
                            newRow.add(getEventType(currentEvent.getType()));
                            //data[i][5]=currentEvent.getEventTitle();}
                            mod.addRow(newRow);
                            numberOfRows++;
                        }
                    }

                }
//                tblevents.set = new JTable(data, columns);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
