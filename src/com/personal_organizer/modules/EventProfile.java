/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.modules;

import java.sql.Date;
import com.personal_organizer.dao.DAO;

/**
 *
 * @author Tolik
 */
public class EventProfile {
    private String userID;
    private String eventID;
    private Date day;
    private String timeFrom;
    private String timeTill;
    private String description;
    private String type;
    private String[] contacts;
    
    public EventProfile(){
        this("","","","","","",new String[2]);
    }
    public EventProfile(String user, String event, String From, String Till, String descript, String t, String[] cont){
        userID=user;
        eventID=event;
        //day=;
        timeFrom=From;
        timeTill=Till;
        description=descript;
        type=t;
        contacts=cont;
        //System.out.println("Print piski");
        //DAO.getEvent();
        //System.out.println("Print siski");
    }
    
}
