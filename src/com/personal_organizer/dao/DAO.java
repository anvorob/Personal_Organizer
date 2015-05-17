/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.dao;

import com.personal_organizer.Personal_Organizer;
import com.personal_organizer.UserProfile;
import com.personal_organizer.db.DBFunctions;

/**
 *
 * @author Mikhail
 */
public class DAO {
    
    
    public DAO (String db_server_name, String db_userid, String db_password){
        
        Personal_Organizer.db = new DBFunctions(db_server_name, db_userid, db_password);

    }

    public boolean checkUserPassword(UserProfile userProfile){
        
        return Personal_Organizer.db.checkUserPassword(userProfile);
    }
    
    public boolean isTheLoginNameNotUsed(){
        return Personal_Organizer.db.isTheLoginNameNotUsed();
    }
    
    public void saveUpdateUserPassword(String command){
        Personal_Organizer.db.saveUpdateUserPassword(command);
    }
}
