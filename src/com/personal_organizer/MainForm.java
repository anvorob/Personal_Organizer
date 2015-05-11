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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
public class MainForm extends JFrame{
    
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

    
        public MainForm(){
            
            this.setTitle("MainForm");
            
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
            
            
            this.setLayout(new FlowLayout());
            topmenu.add(memo);
            topmenu.add(calendar);
            topmenu.add(contacts);
            topmenu.add(messenger);
            topmenu.add(info);
            
            this.setJMenuBar(topmenu);
            this.pack();
        }
        class MListeners  implements MenuListener{

        @Override
        public void menuSelected(MenuEvent e) {
            System.out.println("Menu selected");
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


