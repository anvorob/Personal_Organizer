/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
/**
 *
 * @author Tolik
 */
public class EventForm extends JFrame{
    JTextField eventTitle,timeFrom,timeTill;
    JComboBox<Object> contacts,type;
    JTextArea eventDescription;
    JPanel pnlevent;
    String[] items={"Business meeting","Birthday","Party"};
    String[] contactList={"Anatolii","Shuaib","Mikhail"};
    JButton btnSave,btnCencel;
    
    public EventForm(){
        this("New Event");
    }
    public EventForm(String title){
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        pnlevent=new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(5, 5, 5, 5);
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(new JLabel("Event title: "),gbc);
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(eventTitle=new JTextField(10),gbc);      
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(new JLabel("Event description: "),gbc);

        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(eventDescription=new JTextArea(4, 10),gbc); 
        Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        eventDescription.setBorder(border);
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(new JLabel("Event time start: "),gbc);
 
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(timeFrom=new JTextField(10),gbc); 
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(new JLabel("Event time finish: "),gbc);
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(timeTill=new JTextField(10),gbc); 
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(new JLabel("Event type: "),gbc);

        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(type=new JComboBox<>(items),gbc);
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(new JLabel("Related contacts: "),gbc);

        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(contacts=new JComboBox<>(contactList),gbc); 
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=6;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(btnSave=new JButton("Save"),gbc);
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=6;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlevent.add(btnCencel=new JButton("Cencel"),gbc); 
        
        this.add(pnlevent);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
