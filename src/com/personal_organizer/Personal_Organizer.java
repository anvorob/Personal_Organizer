/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import com.personal_organizer.LoginForm;

/**
 *
 * @author Mikhail
 */
public class Personal_Organizer {

    public static LoginForm loginForm;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
          WelcomeForm welcomeform=new WelcomeForm();
          welcomeform.setVisible(true);
          Thread t=new Thread(welcomeform);
          t.start();
          System.out.print(""+welcomeform.isRunning());
          loginForm = new LoginForm();
          loginForm.setVisible(true);
    }
    
}
