/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.dao;

import com.personal_organizer.db.DBFunctions;

/**
 *
 * @author Mikhail
 */
public class DAO {
    
    DBFunctions db;
    
    public DAO (String db_server_name, String db_userid, String db_password){
        
        db = new DBFunctions(db_server_name, db_userid, db_password);

    }

    public boolean checkUserPassword(String userName, String password){
        
        return db.checkUserPassword(userName, password);
    }
}
