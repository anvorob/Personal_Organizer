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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
public class MainForm extends JFrame implements ActionListener{
    
    JMenuBar topmenu;
    JMenu memo;
    JMenu calendar;
    JMenu contacts;
    JMenu messenger;
    JMenu info;
    
    JMenuItem showmessenger;
    JMenuItem messengercontact;
    
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
    
    static public int fw,fx,fy;
    
    MemoForm memoform;
        public MainForm(){
            
            this.setTitle("MainForm");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            topmenu = new JMenuBar();
            memo=new JMenu("Memo");
            calendar=new JMenu("Calendar");
            contacts=new JMenu("Contacts");
            messenger=new JMenu("Messenger");
            info=new JMenu("Info");
            
            messenger.add(showmessenger=new JMenuItem("Show Messenger"));
            messenger.add(messengercontact=new JMenuItem("Contact List"));
            
            info.add(infoabout=new JMenuItem("About"));
            info.add(infohelp=new JMenuItem("Help"));
            
            memo.addMenuListener(new MListeners());
            
            
            this.setLayout(new BorderLayout());
            topmenu.add(memo);
            topmenu.add(calendar);
            topmenu.add(contacts);
            topmenu.add(messenger);
            topmenu.add(info);
            
            this.setJMenuBar(topmenu);
            eventtitle=new JLabel("", SwingConstants.CENTER);
            pnlcalendar=new JPanel();
            
            JCalendar cal = new JCalendar();
	    cal.addDateListener(new DateListener(){

	      @Override
	      public void dateChanged(Calendar new_c) {
		int day = new_c.get(Calendar.DAY_OF_MONTH);
		int month = new_c.get(Calendar.MONTH) + 1;
		int year = new_c.get(Calendar.YEAR);
		System.out.println("Selected: " + day + "/" +
		            month + "/" + year + " (DD/MM/YYYY)");
                eventtitle.setText(""+day+"/"+month+"/"+year);
	      }
	      
	    });
	    this.getContentPane().add(cal, BorderLayout.NORTH);
            
            
            pnlevents=new JPanel(new BorderLayout());
            pnleventscontrol = new JPanel(new BorderLayout());
            btneventnext=new JButton("Next");
            btneventprev=new JButton("Prev");
            //eventtitle=new JLabel("Title");
            pnleventscontrol.add(btneventprev, BorderLayout.EAST);
            pnleventscontrol.add(eventtitle, BorderLayout.CENTER);
            pnleventscontrol.add(btneventnext, BorderLayout.WEST);
            
            pnleventstable = new JPanel();
            String columns[]={"Title","Description","Time From","Time Till","Type","Contacts"};
            Object[][] data={{"Business meeting","140 Hobson","9:00","10:00","Meeting","Tom"},
            {"","","","","",""},
            {"","","","","",""},
            {"","","","","",""},
            {"","","","","",""},
            {"","","","","",""}};
            tblevents=new JTable(data,columns);
            tblevents.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            System.out.println(""+tblevents.getModel().getValueAt(0, 2));
            
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
            
            JScrollPane scroll=new JScrollPane(tblevents);
            pnleventstable.add(scroll);
            
            pnleventbtns= new JPanel(new FlowLayout((int) CENTER_ALIGNMENT, 10,5));
            pnleventbtns.add(btnaddevent=new JButton("Add Event"));
            pnleventbtns.add(btndeleteevent=new JButton("Delete Event"));
            pnleventbtns.add(btnviewevent=new JButton("View Event"));
            
            btnaddevent.addActionListener(this);
            btndeleteevent.addActionListener(this);
            btnviewevent.addActionListener(this);
            
            
            pnlevents.add(pnleventscontrol, BorderLayout.NORTH);
            pnlevents.add(pnleventstable, BorderLayout.CENTER);
            pnlevents.add(pnleventbtns, BorderLayout.SOUTH);
            this.add(pnlevents, BorderLayout.CENTER);
            
            pnlstatusbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnlstatusbar.setBorder(new BevelBorder(BevelBorder.LOWERED));
            pnlstatusbar.setBackground(Color.GRAY);
            
            pnlstatusbar.add(statustitle= new JLabel("Title", SwingConstants.RIGHT));
            pnlstatusbar.add(statusdescript=new JLabel("Description"));
                    
            statustitle.setText(""+tblevents.getModel().getValueAt(0, 0));
            statusdescript.setText(": "+tblevents.getModel().getValueAt(0, 1));
            this.add(pnlstatusbar, BorderLayout.SOUTH);
            
            this.pack();
            this.setLocationRelativeTo(null);
        }
    public void frameSize(){
        fw=this.getWidth();
        Point p=this.getLocationOnScreen();
        this.setExtendedState(JFrame.NORMAL);
        Rectangle r=this.getBounds();
        fx=(int)r.getX();
        fy=(int)r.getY();
//        fx=this.getLocation().x;
//        fy=this.getLocation().y;
//            fx=p.x;
//            fy=p.y;
        System.out.println("width: "+fw+" x: "+fx+" y: "+fy);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btnaddevent){
            EventForm event=new EventForm();
            event.setVisible(true);
       } 
       if(e.getSource()==btndeleteevent){
           System.out.println("Delete");
       }
       if(e.getSource()==btnviewevent){
           EventForm event=new EventForm();
            event.setVisible(true);
       }
    }
        class MListeners  implements MenuListener{

        @Override
        public void menuSelected(MenuEvent e) {
            if(e.getSource()==memo){
                memoform=new MemoForm(fw,fx,fy);
                memoform.setVisible(true);
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


