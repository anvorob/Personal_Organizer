/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.modules;

import java.util.Date;
import com.personal_organizer.dao.DAO;
import java.sql.Time;

/**
 *
 * @author Tolik
 */
public class EventProfile {

    private String userID;
    private String eventID;
    private Date day;
    private Time timeFrom;
    private Time timeTill;
    private String description;
    private int type;
    private String[] contacts;

    public EventProfile() {
        this("", "", new Date(1,1,15), new Time(0, 0, 0), new Time(0, 0, 0), "", 0, new String[2]);
    }

    public EventProfile(String userID, String eventID, Date day, Time timeFrom, Time timeTill,
            String description, int type, String[] contacts) {
        this.userID = userID;
        this.eventID = eventID;
        this.day = day;
        this.timeFrom = timeFrom;
        this.timeTill = timeTill;
        this.description = description;
        this.type = type;
        this.contacts = contacts;
        //DAO.fetchEvents();
        System.out.println("Print piski");
        DAO.getEvent();
        System.out.println("Print siski");
    }

}
