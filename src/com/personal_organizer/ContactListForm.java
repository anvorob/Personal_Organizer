/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;


//import com.personal_organizer.modules.Tools;
import com.personal_organizer.view.OFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author Mikhail
 */
public class ContactListForm extends OFrame implements ActionListener,ListSelectionListener {
   
    JPanel pnlContacts;
    JPanel pnlEventsControl;
    JPanel pnlContactsTable;
    JPanel pnlContactBtns;
    JPanel pnlContactForm;
    
    JTable tblContacts;
    
    JTextField contactFirstName;
    JTextField contactLastName;
    JTextField contactPhoneNumber;
    JTextField contactEmail;
    JTextField contactBirthday;
    JTextArea contactNotes;
    
    
    JButton btnAddContact;
    JButton btnDeleteContact;
    
    static public Object[] rowContactData;

    
    public ContactListForm() {

        //this.setTitle(Tools.caption());
        this.setTitle("Contact List");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        
        pnlContactsTable = new JPanel();
        String columns[] = {"First Name", "Last Name", "Phone Number", "Email", "Birthday", "Notes"};
        Object[][] data = {{"Mikhail", "Novozhilov", "021034020", "micnov@mail.ru", "10/04/1980", "Dude from school"},
        {"", "", "", "", "", ""},
        {"", "", "", "", "", ""},
        {"", "", "", "", "", ""},
        {"", "", "", "", "", ""},
        {"", "", "", "", "", ""}};
        tblContacts = new JTable(data, columns);
        rowContactData = new Object[tblContacts.getColumnCount()];
        tblContacts.getSelectionModel().addListSelectionListener(this);
        tblContacts.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        System.out.println("" + tblContacts.getModel().getValueAt(0, 2));

        JScrollPane scroll = new JScrollPane(tblContacts);
        pnlContactsTable.add(scroll);

        pnlContactBtns = new JPanel(new FlowLayout((int) CENTER_ALIGNMENT, 10, 5));
        pnlContactBtns.add(btnAddContact = new JButton("Add Contact"));
        pnlContactBtns.add(btnDeleteContact = new JButton("Delete Contact"));
        
        btnAddContact.addActionListener(this);
        btnDeleteContact.addActionListener(this);
            
        btnDeleteContact.setEnabled(false);
        
        pnlContactForm=new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(5, 5, 5, 5);
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(new JLabel("First Name: "),gbc);
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(contactFirstName=new JTextField(10),gbc);      
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(new JLabel("Last Name: "),gbc);

        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(contactLastName=new JTextField(10),gbc); 
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(new JLabel("Phone number: "),gbc);
 
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(contactPhoneNumber=new JTextField(10),gbc); 
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(new JLabel("Email: "),gbc);
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(contactEmail=new JTextField(10),gbc); 
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(new JLabel("Birthday: "),gbc);

        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(contactBirthday=new JTextField(10),gbc);
        
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(new JLabel("Notes: "),gbc);

        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContactForm.add(contactNotes=new JTextArea(4,10),gbc); 
        Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        contactNotes.setBorder(border);
        
        pnlContacts=new JPanel(new GridBagLayout());
        //GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(5, 5, 5, 5);
        gbc.gridwidth=1;
        gbc.gridheight=2;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContacts.add(pnlContactsTable,gbc);
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContacts.add(pnlContactForm,gbc);
        
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        pnlContacts.add(pnlContactBtns,gbc);

        this.add(pnlContacts, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==btnAddContact){
            contactFirstName.setText(null);
            contactLastName.setText(null);
            contactPhoneNumber.setText(null);
            contactEmail.setText(null);
            contactBirthday.setText(null);
            contactNotes.setText(null);
        }
        if(e.getSource()==btnDeleteContact){
            System.out.println("Misha, dobav SQL sdes");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //
        //This piece of code load things from table and put into forms
        // when you click on particula row.
        //
        int row = tblContacts.getSelectedRow();
        
        Object[] rowContactData = new Object[tblContacts.getColumnCount()];
        btnDeleteContact.setEnabled(true);
        for (int i = 0; i < tblContacts.getColumnCount(); i++) {
            rowContactData[i] = tblContacts.getValueAt(row, i);
            switch(i){
                case 0: contactFirstName.setText((String)tblContacts.getValueAt(row, i));break;
                case 1: contactLastName.setText((String)tblContacts.getValueAt(row, i));break;
                case 2: contactPhoneNumber.setText((String)tblContacts.getValueAt(row, i));break;
                case 3: contactEmail.setText((String)tblContacts.getValueAt(row, i));break;
                case 4: contactBirthday.setText((String)tblContacts.getValueAt(row, i));break;
                case 5: contactNotes.setText((String)tblContacts.getValueAt(row, i));break;
                default:    System.out.println("sas");break;
            }
           
        }
        
    }
}
