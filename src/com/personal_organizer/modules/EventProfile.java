/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.modules;

import com.personal_organizer.Personal_Organizer;
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
    private String type;
    private boolean show = false;
    private String fieldToFind;

    public EventProfile() {
        this("", "", "New event", new Date(1, 1, 15), new Time(0, 0, 0), new Time(0, 0, 0), "", "")
                //, "")
                ;
    }

    public EventProfile(String eventID, String userID, String eventTitle, Date day, Time timeFrom, Time timeTill,
            String description, String type)
            //, String contacts) 
    {
        this.userID = userID;
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.day = day;
        this.timeFrom = timeFrom;
        this.timeTill = timeTill;
        this.description = description;
        this.type = type;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventID() {
        return this.eventID;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventTitle() {
        return this.eventTitle;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Date getDay() {
        return this.day;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Time getTimeFrom() {
        return this.timeFrom;
    }

    public void setTimeTill(Time timeTill) {
        this.timeTill = timeTill;
    }

    public Time getTimeTill() {
        return this.timeTill;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setType(String type) {
//        String result = "";
//        for (EventType eventType : Personal_Organizer.eventTypes) {
//            if (type.equals(eventType.getTypeID())) {
//                result = eventType.getTitle();
//                break;
//            }
//
//        }
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getTypeID() {
        String result = "";
        for (EventType eventType : Personal_Organizer.eventTypes) {
            if (this.type.equals(eventType.getTitle())) {
                result = eventType.getTypeID();
                break;
            }

        }
        return result;
    }

    public static String getTypeID(String typeTitle) {
        String result = "";
        for (EventType eventType : Personal_Organizer.eventTypes) {
            if (typeTitle.equals(eventType.getTitle())) {
                result = eventType.getTypeID();
                break;
            }

        }
        return result;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean getShow() {
        return this.show;
    }

//    public void setNumberOfRow(int numberOfRow) {
//        this.numberOfRow = numberOfRow;
//    }
//
//    public int getNumberOfRow() {
//        return this.numberOfRow;
//    }
//
    public void setFieldToFind(String fieldToFind) {
        this.fieldToFind = fieldToFind;
    }

    public String getFieldToFind() {
        return this.fieldToFind;
    }
}
