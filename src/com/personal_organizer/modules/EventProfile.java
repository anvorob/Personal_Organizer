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
    private String eventTitle;
    private Date day;
    private Time timeFrom;
    private Time timeTill;
    private String description;
    private int type;
    private String[] contacts;

    public EventProfile() {
        this("", "", "New event", new Date(1,1,15), new Time(0, 0, 0), new Time(0, 0, 0), "", 0, new String[2]);
    }

    public EventProfile(String userID, String eventID, String eventTitle, Date day, Time timeFrom, Time timeTill,
            String description, int type, String[] contacts) {
        this.userID = userID;
        this.eventID = eventID;
        this.eventTitle = eventTitle;
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

    public void setUserID(String userID){
        this.userID = userID;
    }
    
    public String getUserID(){
        return this.userID;
    }

    public void setEventID(String eventID){
        this.eventID = eventID;
    }
    
    public String getEventID(){
        return this.eventID;
    }
    
    public void setEventTitle(String eventTitle){
        this.eventTitle = eventTitle;
    }
    
    public String getEventTitle(){
        return this.eventTitle;
    }
    
   public void setDay(Date day){
        this.day = day;
    }
    
    public Date getDay(){
        return this.day;
    }
    
    public void setTimeFrom(Time timeFrom){
        this.timeFrom = timeFrom;
    }
    
    public Time getTimeFrom(){
        return this.timeFrom;
    }
    
    public void setTimeTill(Time timeTill){
        this.timeTill = timeTill;
    }
    
    public Time getTimeTill(){
        return this.timeTill;
        
    }
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public int getType(){
        return type;
    }
    
    public void setContacts(String[] contacts){
        this.contacts = contacts;
    }
    
    public String[] getContacts(){
        return this.contacts;
    }
}
