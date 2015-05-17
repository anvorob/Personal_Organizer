/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.LoginForm;
import com.personal_organizer.dao.DAO;
import com.personal_organizer.db.DBFunctions;

/**
 *
 * @author Mikhail
 */
public class Personal_Organizer {

    public static LoginForm loginForm;
    public static MainForm mainform;
    public static UserProfile userProfile;
    public static SignUpForm signUpForm;
    public static DBFunctions db;
    public static DAO dao;
    public static final String DB_SERVER_NAME = "localhost";
    public static final String DB_USERID = "sa";
    public static final String DB_PASSWORD = "NA@!ro20";
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
//          WelcomeForm welcomeform=new WelcomeForm();
          loginForm = new LoginForm();
//          welcomeform.setVisible(true);
//          Thread t=new Thread(welcomeform);
//          t.start();
//          while(welcomeform.isRunning()){
//              System.out.println(""+welcomeform.isRunning());
//          }
//          if(welcomeform.isRunning()==false){
//              welcomeform.setVisible(false);
//          }
//          
          loginForm.setVisible(true);
          //mainform=new MainForm();
          //mainform.setVisible(true);
    }
    
    public static void connectDB(){
        dao = new DAO(DB_SERVER_NAME, DB_USERID, DB_PASSWORD);

    }
}
