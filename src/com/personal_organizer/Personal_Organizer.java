/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.modules.UserProfile;
import com.personal_organizer.LoginForm;
import com.personal_organizer.modules.EventProfile;
import com.personal_organizer.messenger.Gui;
import com.personal_organizer.modules.EventType;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikhail
 */
public class Personal_Organizer {

    public static LoginForm loginForm;
    public static MainForm mainform;
    public static SignUpForm signUpForm;
    public static UserProfile userProfile;
    public static boolean dbExist;
    public static ArrayList<EventProfile> events;
    public static ArrayList<EventType> eventTypes;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        events = new ArrayList<EventProfile>();
        eventTypes = new ArrayList<EventType>();
            //          WelcomeForm welcomeform=new WelcomeForm();
//          welcomeform.setVisible(true);
//          Thread t=new Thread(welcomeform);
//          t.start();
//          while(welcomeform.isRunning()){
//              System.out.println(""+welcomeform.isRunning());
//          }
//          if(welcomeform.isRunning()==false){
//              welcomeform.setVisible(false);
//          }
//          new EventProfile();
          loginForm = new LoginForm();
          loginForm.setVisible(true);
//        EventForm eventFrm = new EventForm(new Date(1,1,15));
//        eventFrm.setVisible(true);
        
//          mainform=new MainForm();
//          mainform.setVisible(true);
//          mainform.frameSize();
//        try {
//            System.out.println(InetAddress.getLocalHost().getHostAddress());
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(Personal_Organizer.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    
}
