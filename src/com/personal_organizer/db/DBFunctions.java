/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.db;

import com.personal_organizer.LoginForm;
import com.personal_organizer.MainForm;
import com.personal_organizer.Personal_Organizer;
import com.personal_organizer.UserProfile;
import com.personal_organizer.dao.DAO;
import com.personal_organizer.modules.Tools;

/**
 *
 * @author Mikhail
 */
public class DBFunctions {

    public DAO myDAO;

    public static boolean checkUserPassword(LoginForm frmLogin) {
        boolean unswer = false;
        String loginName = frmLogin.getLoginName();
        Tools.print("User ID: '" + loginName + "'\n(loginName == \"\") = "
                + (loginName.equals("")));
        if (loginName.equals("")) {
            Tools.print("User ID can't be empty.");
        } else {
            String userPassword = frmLogin.getPassword();
            Tools.print("Password: '" + userPassword + "'\n(userPassword == \"\") = "
                    + (userPassword.equals("")));
            if (userPassword.equals("")) {
                Tools.print("Password can't be empty.");
            } else {
                Personal_Organizer.userProfile = new UserProfile(loginName,
                        userPassword);
                unswer = DAO.checkUserPassword(Personal_Organizer.userProfile);
            }
        }
        return unswer;
    }

    public static boolean isTheLoginNameNotUsed() {
        return DAO.isTheLoginNameNotUsed();
    }

    public static int saveUpdateUserPassword(String command) {
        return DAO.saveUpdateUserPassword(command);
    }
    
}
