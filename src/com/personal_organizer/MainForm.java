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
import com.personal_organizer.modules.Tools;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MainForm extends JFrame implements ActionListener {

    JMenuBar topMenu;
    JMenu memo;
    JMenu calendar;
    JMenu contacts;
    JMenu messenger;
    JMenu info;

    JMenuItem showMessenger;
    JMenuItem messengerContact;

    JMenuItem infoAbout;
    JMenuItem infoHelp;

    JPanel pnlCalendar;
    JPanel pnlEvents;
    JPanel pnlEventscontrol;
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

    MemoForm memoForm;

    public MainForm() {

        this.setTitle(Tools.caption());
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

        JCalendar cal = new JCalendar();
        cal.addDateListener(new DateListener() {

            @Override
            public void dateChanged(Calendar new_c) {
                int day = new_c.get(Calendar.DAY_OF_MONTH);
                int month = new_c.get(Calendar.MONTH) + 1;
                int year = new_c.get(Calendar.YEAR);
                System.out.println("Selected: " + day + "/"
                        + month + "/" + year + " (DD/MM/YYYY)");
                eventTitle.setText("" + day + "/" + month + "/" + year);
            }

        });
        this.getContentPane().add(cal, BorderLayout.NORTH);

        pnlEvents = new JPanel(new BorderLayout());
        pnlEventscontrol = new JPanel(new BorderLayout());
        //eventtitle=new JLabel("Title");
        pnlEventscontrol.add(btnEventPrev = new JButton("Prev"), BorderLayout.WEST);
        pnlEventscontrol.add(eventTitle, BorderLayout.CENTER);
        pnlEventscontrol.add(btnEventNext = new JButton("Next"), BorderLayout.EAST);

        pnlEventsTable = new JPanel();
        String columns[] = {"Title", "Description", "Time From", "Time Till", "Type", "Contacts"};
        Object[][] data = {{"Business meeting", "140 Hobson", "9:00", "10:00", "Meeting", "Tom"},
        {"", "", "", "", "", ""},
        {"", "", "", "", "", ""},
        {"", "", "", "", "", ""},
        {"", "", "", "", "", ""},
        {"", "", "", "", "", ""}};
        tblEvents = new JTable(data, columns);
        tblEvents.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        System.out.println("" + tblEvents.getModel().getValueAt(0, 2));

//            tblEvents.getColumnModel().getColumn(0).setPreferredWidth(27);
//            tblEvents.getColumnModel().getColumn(1).setPreferredWidth(120);
//            tblEvents.getColumnModel().getColumn(2).setPreferredWidth(100);
//            tblEvents.getColumnModel().getColumn(3).setPreferredWidth(90);
//            tblEvents.getColumnModel().getColumn(4).setPreferredWidth(90);
//            tblEvents.getColumnModel().getColumn(6).setPreferredWidth(120);
//            tblEvents.getColumnModel().getColumn(7).setPreferredWidth(100);
//            tblEvents.getColumnModel().getColumn(8).setPreferredWidth(95);
//            tblEvents.getColumnModel().getColumn(9).setPreferredWidth(40);
//            tblEvents.getColumnModel().getColumn(10).setPreferredWidth(400);
        JScrollPane scroll = new JScrollPane(tblEvents);
        pnlEventsTable.add(scroll);

        pnlEventBtns = new JPanel(new FlowLayout((int) CENTER_ALIGNMENT, 10, 5));
        pnlEventBtns.add(btnAddEvent = new JButton("Add Event"));
        pnlEventBtns.add(btnDeleteEvent = new JButton("Delete Event"));
        pnlEventBtns.add(btnViewEvent = new JButton("View Event"));

        pnlEvents.add(pnlEventscontrol, BorderLayout.NORTH);
        pnlEvents.add(pnlEventsTable, BorderLayout.CENTER);
        pnlEvents.add(pnlEventBtns, BorderLayout.SOUTH);
        this.add(pnlEvents, BorderLayout.CENTER);

        pnlStatusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        pnlStatusBar.setBackground(Color.GRAY);

        pnlStatusBar.add(statusTitle = new JLabel("Title", SwingConstants.RIGHT));
        pnlStatusBar.add(statusDescript = new JLabel("Description"));

        statusTitle.setText("" + tblEvents.getModel().getValueAt(0, 0));
        statusDescript.setText(": " + tblEvents.getModel().getValueAt(0, 1));
        this.add(pnlStatusBar, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class MListeners implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            if (e.getSource() == memo) {
                memoForm = new MemoForm();
                memoForm.setVisible(true);
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
}
