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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
    JButton btnUpdateEvent;
    static Date eventsDate;
    JCalendar cal;

    static Object[] rowData;
    static int fw, fx, fy;
    public static boolean isNecessaryToReFill = false;
    public static boolean isNotNecessaryToReFill = false;

    MemoForm memoForm;
    ContactListForm contactList;

    Vector<String> columns = new Vector<String>();
    DefaultTableModel mod = new DefaultTableModel(columns, 0);
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
    TableRowSorter<TableModel> sorter;
    ArrayList<Object> result;

    public MainForm() {

        columns.add("Title");
        columns.add("Description");
        columns.add("Date");
        columns.add("Time From");
        columns.add("Time Till");
        columns.add("Type");

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

        Date date = new Date();
        eventsDate = date;

        eventTitle.setText(dateFormat.format(eventsDate));
        //System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48        
        cal = new JCalendar();
        cal.selectDay(date.getYear(), date.getMonth(), date.getDate());

        cal.addDateListener(new DateListener() {

            @Override
            public void dateChanged(Calendar new_c) {
                int day = new_c.get(Calendar.DAY_OF_MONTH);
                int month = new_c.get(Calendar.MONTH) + 1;
                int year = new_c.get(Calendar.YEAR);

                String dateFormated = dateFormat.format(new_c.getTime());
                eventTitle.setText(dateFormated);
                eventsDate = new Date(year, month - 1, day);
                filterTable();
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
        sorter = new TableRowSorter<TableModel>(mod);
        tblEvents.setRowSorter(sorter);
        sorter.setRowFilter(null);

        filterTable();

        tblEvents.getSelectionModel().addListSelectionListener(this);
        tblEvents.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scroll = new JScrollPane(tblEvents);
        pnlEventsTable.add(scroll);

        pnlEventBtns = new JPanel(new FlowLayout((int) CENTER_ALIGNMENT, 10, 5));
        pnlEventBtns.add(btnAddEvent = new JButton("Add Event"));
        pnlEventBtns.add(btnDeleteEvent = new JButton("Delete Event"));
        pnlEventBtns.add(btnUpdateEvent = new JButton("Update Event"));

        btnAddEvent.addActionListener(this);
        btnDeleteEvent.addActionListener(this);
        btnUpdateEvent.addActionListener(this);

        btnDeleteEvent.setEnabled(false);
        btnUpdateEvent.setEnabled(false);

        pnlEvents.add(pnlEventsControl, BorderLayout.NORTH);
        pnlEvents.add(pnlEventsTable, BorderLayout.CENTER);
        pnlEvents.add(pnlEventBtns, BorderLayout.SOUTH);
        this.add(pnlEvents, BorderLayout.CENTER);

        pnlStatusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        pnlStatusBar.setBackground(Color.GRAY);

        pnlStatusBar.add(statusTitle = new JLabel("Title", SwingConstants.RIGHT));
        pnlStatusBar.add(statusDescript = new JLabel(": Description"));

        this.add(pnlStatusBar, BorderLayout.SOUTH);

        this.pack();
        this.setSize(this.getWidth(), this.getHeight() - 30);
        System.out.println("" + this.getWidth() + " " + this.getHeight());
        this.setLocationRelativeTo(null);
        getEvents();
        fillTheTable();
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
            int selection = JOptionPane.showConfirmDialog(null, "You are going to"
                    + " delete this event!\nAre you shure?", "Output",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (selection == JOptionPane.YES_OPTION) {
                deleteEvent();
            }
        }
        if (e.getSource() == btnUpdateEvent) {
            updateEvent();
        } else if (e.getSource() == btnEventPrev) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
            eventsDate.setDate(eventsDate.getDate() - 1);
            eventTitle.setText(dateFormat.format(eventsDate));
            cal.selectDay(eventsDate.getYear(), eventsDate.getMonth(), eventsDate.getDate());
            filterTable();
        } else if (e.getSource() == btnEventNext) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
            eventsDate.setDate(eventsDate.getDate() + 1);
            eventTitle.setText(dateFormat.format(eventsDate));
            cal.selectDay(eventsDate.getYear(), eventsDate.getMonth(), eventsDate.getDate());
            filterTable();
        } else if (e.getSource() == myInfo) {
            Personal_Organizer.signUpForm = new SignUpForm();
            Personal_Organizer.signUpForm.setCommand("Update");
            Personal_Organizer.signUpForm.setVisible(true);
        }
    }

    protected void deleteEvent() {

        int row = tblEvents.getSelectedRow();
        StringBuilder parametrs = new StringBuilder("");
        for (int i = 0; i < tblEvents.getColumnCount(); i++) {
            parametrs.append(tblEvents.getValueAt(row, i));
        }
        result = findTheEvent(parametrs.toString());
        System.out.println(parametrs.toString());
        EventProfile eventToDelete = (EventProfile) result.get(1);
        Personal_Organizer.events.remove(eventToDelete);
        DAO.deleteEvent(result.get(0));
        sorter.setRowFilter(null);
        reFillTheTable reFill = new reFillTheTable();
        Thread reFillTheTable = new Thread(reFill);
        reFillTheTable.start();

    }

    protected void updateEvent() {

        int row = tblEvents.getSelectedRow();
        StringBuilder parametrs = new StringBuilder("");
        for (int i = 0; i < tblEvents.getColumnCount(); i++) {
            parametrs.append(tblEvents.getValueAt(row, i));
        }
        result = findTheEvent(parametrs.toString());
        System.out.println(parametrs.toString());
        EventProfile eventToUpdate = (EventProfile) result.get(1);
        EventForm event = new EventForm(eventToUpdate.getEventTitle(), eventToUpdate);
        reFillTheTable reFill = new reFillTheTable();
        Thread reFillTheTable = new Thread(reFill);
        reFillTheTable.start();
        event.setVisible(true);
    }

    protected ArrayList<Object> findTheEvent(String findString) {
        ArrayList<Object> result = new ArrayList<Object>();
        for (EventProfile currentEvent : Personal_Organizer.events) {
            if (currentEvent.getFieldToFind().equals(findString)) {
                result.add(currentEvent.getEventID());
                result.add(currentEvent);
                break;
            }
        }

        return result;
    }

    protected void resetEventsShow() {
        for (EventProfile event : Personal_Organizer.events) {
            event.setShow(false);
        }
    }

    protected void clearTheTable() {
        int rowCount = mod.getRowCount();
        if (mod.getRowCount() > 0) {
            for (int i = mod.getRowCount() - 1; i > -1; i--) {
                try {
                    mod.removeRow(i);
                } catch (Exception e) {
                    //Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        resetEventsShow();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = tblEvents.getSelectedRow();
        showInStatusBar(row);
        btnUpdateEvent.setEnabled(true);
        btnDeleteEvent.setEnabled(true);
    }

    private void fillTheTable() {
        for (EventProfile currentEvent : Personal_Organizer.events) {
            if (!currentEvent.getShow()) {
                Vector<String> newRow = new Vector<String>();
                newRow.add(currentEvent.getEventTitle());
                newRow.add(currentEvent.getDescription());
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
                System.out.println(dateFormat.format(currentEvent.getTimeFrom()));
                newRow.add(dateFormat.format(currentEvent.getDay()));
                DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                newRow.add(timeFormat.format(currentEvent.getTimeFrom()));
                newRow.add(timeFormat.format(currentEvent.getTimeTill()));
                newRow.add(EventType.getEventType(currentEvent.getType()));
                mod.addRow(newRow);

                StringBuilder parametrs = new StringBuilder("");
                for (String parametr : newRow) {
                    parametrs.append(parametr);
                }
                currentEvent.setFieldToFind(parametrs.toString());
                currentEvent.setShow(true);
            }
        }
    }

    private void showInStatusBar() {
        showInStatusBar(-1);
    }

    private void showInStatusBar(int row) {
        if (statusTitle != null && statusDescript != null) {
            if (row < 0 || tblEvents.getRowCount() <= 0) {
                statusTitle.setText("Title ");
                statusDescript.setText(": Description");
            } else {
                statusTitle.setText("" + tblEvents.getValueAt(row, 0));
                statusDescript.setText(": " + tblEvents.getValueAt(row, 1));
            }
        }
    }

    private void filterTable() {
        try {
            sorter.setRowFilter(
                    RowFilter.regexFilter(dateFormat.format(eventsDate)));
        } catch (PatternSyntaxException pse) {
            System.err.println("Bad regex pattern");
        }
        showInStatusBar();
        if (btnAddEvent != null) {
            btnUpdateEvent.setEnabled(false);
        }
        if (btnDeleteEvent != null) {
            btnDeleteEvent.setEnabled(false);
        }
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

    class reFillTheTable implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (isNecessaryToReFill) {
                    clearTheTable();
                    fillTheTable();
                    filterTable();
                    isNecessaryToReFill = false;
                    break;
                }
                if (isNotNecessaryToReFill) {
                    isNotNecessaryToReFill = false;
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                }
            }
        }

    }
}
