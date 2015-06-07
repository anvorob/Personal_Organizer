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

    public EventProfile() {
        this("", "", new Date(12,12,15), "", "", "", "", new String[2]);
    }

    public EventProfile(String userID, String eventID, Date day, String timeFrom, String timeTill,
            String description, String type, String[] contacts) {
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
