/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.modules;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.sql.Date;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Mikhail
 */
public class UserProfile {
    
    private String userID;
    private String firstName;
    private String lastName;
    private String loginName;
    private String userEmail;
    private String password;
    private Date birthDay;
    private int birthDayYear;
    private int birthDayMonth;
    private int birthDayDay;
    private String phone;
    
    
    public UserProfile(String userName, String password) {
        this(userName, password, "", "", "");
    }
    
    public UserProfile(String userName, String password, String userEmail) {
        this(userName,  password, userEmail, "", "");
    }
    
    public UserProfile(String userName, String password, String userEmail, String phone) {
        this(userName, password, userEmail, phone, "");
        
    }
    public UserProfile(String loginName, String password, String userEmail, String phone, String userID) {
        this.loginName = loginName;
        this.userEmail = userEmail;
        this.password = password;
        this.phone = phone;
        this.userID = (userID.equals("")) ? Tools.generateCode(10) : userID;
    }
    
    private UserProfile setUserID(UserProfile userProfile){
        this.userID = Tools.generateCode(10);
        return userProfile;
    }

    public String getLoginName(){
        return this.loginName;
    }

    public void setLoginName(String loginName){
        this.loginName = loginName;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUserEmail(){
        return this.userEmail;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getUserID(){
        return this.userID;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public int getBirthDayYear(){
        return this.birthDayYear;
    }
    
    public void setBirthDayYear(int birthDayYear){
        this.birthDayYear = birthDayYear;
    }
    
    public int getBirthDayMonth(){
        return this.birthDayMonth;
    }
    
    public void setBirthDayMonth(int birthDayMonth){
        this.birthDayMonth = birthDayMonth;
    }
    
    public int getBirthDayDay(){
        return this.birthDayDay;
    }
    
    public void setBirthDayDay(int birthDayDay){
        this.birthDayDay = birthDayDay;
    }

    public Date getBirthDay(){
        return this.birthDay;
    }
    
    public void setBirthDay(Date birthDay){
        this.birthDay = birthDay;
    }

}
