/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
//import java.util.Date;
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
        this.password = md5Custom(password);
        this.phone = phone;
        this.userID = (userID.equals("")) ? generateCode() : userID;
    }
    
    private UserProfile setUserID(UserProfile userProfile){
        this.userID = generateCode();
        return userProfile;
    }

    private String generateCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // используемые символы
        String code = "";
        Random r = new Random();
        for(int i = 0; i < 10; i++) { // длинна кода  от 10
            code = code + chars.charAt(r.nextInt(chars.length()));
        }
        return code;
    }

    private static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
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

    public void getPassword(String password){
        this.password = password;
    }

    public String getUserEmail(){
        return this.userEmail;
    }

    public void getUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public String getPhone(){
        return this.phone;
    }

    public void getPhone(String phone){
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
