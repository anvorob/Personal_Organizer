/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.dao;

import com.personal_organizer.Personal_Organizer;
import com.personal_organizer.UserProfile;
import com.personal_organizer.db.DBFunctions;
import com.personal_organizer.modules.Tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikhail
 */
public class DAO {
    
    
    public static String dbServerName = "localhost";
    public static String dbUserID = "sa";
    public static String dbPassword = "NA@!ro20";

    private boolean showCommentForDebuging = false;
    private ResultSet rs;
    private Connection conn;
    private Statement statement;
    //private String db_connect_string, db_userid, db_password;
    private String dbPath;
    private static final String DB_NAME = "OrganizerDB";
    private static final String TBL_USERS = "tblUsers";
    private static final String TBL_CONTACT_BOOK = "tblContactBook";
    private static final String TBL_EVENTS = "tblEvents";
    private static final String TBL_MEMOS = "tblMemos";

    private static final String T1COLUMN_ID = "_user_id";
    private static final String T1COLUMN_ID_TYPE = " char(10) NOT NULL PRIMARY KEY, ";
    private static final String T1COLUMN_USER_NAME = "_user_name";
    private static final String T1COLUMN_USER_NAME_TYPE = " char(10) NOT NULL, ";
    private static final String T1COLUMN_USER_EMAIL = "_user_email";
    private static final String T1COLUMN_USER_EMAIL_TYPE = " char(30) NOT NULL, ";
    private static final String T1COLUMN_PASSWORD = "_password";
    private static final String T1COLUMN_PASSWORD_TYPE = " char(32) NOT NULL, ";
    private static final String T1COLUMN_PHONE = "_phone";
    private static final String T1COLUMN_PHONE_TYPE = " char(10));";

//    public static final String T2COLUMN_ID = "_id2";
//    public static final String T2COLUMN_CATEGORY = "_category2";
//    public static final String T2COLUMN_NUMBER = "_number";
//    public static final String T2COLUMN_WORD = "_word";
//    public static final String T2COLUMN_WORDR = "_wordr";
    private static final String CREATE_DATABASE
            = "use master\nIF DB_ID (N'" + DB_NAME + "') IS NULL\n"
            + "CREATE DATABASE OrganizerDB\nCOLLATE  Latin1_General_BIN\nWITH "
            + "TRUSTWORTHY ON, DB_CHAINING ON";
//    private static final String CREATE_DATABASE
//            = "USE master;\nGO\nIF DB_ID (N'" + DB_NAME + "') IS NOT NULL\n"
//            + "DROP DATABASE OrganizerDB;\nGO\nCREATE DATABASE OrganizerDB\n"
//            + "COLLATE  Latin1_General_BIN\nWITH TRUSTWORTHY ON, DB_CHAINING ON;\nGO";
    private static final String CREATE_TABLE_USERS = "IF not EXISTS (SELECT 1 FROM "
            + "INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='" + TBL_USERS + "')"
            + "CREATE TABLE " + TBL_USERS + " (" + T1COLUMN_ID + T1COLUMN_ID_TYPE
            + T1COLUMN_USER_NAME + T1COLUMN_USER_NAME_TYPE + T1COLUMN_USER_EMAIL
            + T1COLUMN_USER_EMAIL_TYPE + T1COLUMN_PASSWORD + T1COLUMN_PASSWORD_TYPE
            + T1COLUMN_PHONE + T1COLUMN_PHONE_TYPE;
//    private static final String CREATE_TABLE_USERS = "IF not EXISTS (SELECT 1 FROM "
//            + "INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='" + TBL_USERS + "')"
//            + "CREATE TABLE " + TBL_USERS + " (" + T1COLUMN_ID + T1COLUMN_ID_TYPE
//            + T1COLUMN_USER_NAME + T1COLUMN_USER_EMAIL_TYPE + T1COLUMN_USER_EMAIL
//            + T1COLUMN_USER_EMAIL_TYPE + T1COLUMN_PASSWORD + T1COLUMN_PASSWORD_TYPE
//            + T1COLUMN_PHONE + T1COLUMN_PHONE_TYPE;
//    private static final String CREATE_TABLE_USERS = "IF not EXISTS (SELECT 1 FROM "
//            + "INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='" + TBL_USERS + "')"
//            + "CREATE TABLE " + TBL_USERS + " (" + T1COLUMN_ID + T1COLUMN_ID_TYPE
//            + T1COLUMN_USER_NAME + T1COLUMN_USER_EMAIL_TYPE + T1COLUMN_USER_EMAIL
//            + T1COLUMN_USER_EMAIL_TYPE + T1COLUMN_PASSWORD + T1COLUMN_PASSWORD_TYPE
//            + T1COLUMN_PHONE + T1COLUMN_PHONE_TYPE;
//    private static final String CREATE_TABLE_USERS = "IF not EXISTS (SELECT 1 FROM "
//            + "INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='" + TBL_USERS + "')"
//            + "CREATE TABLE " + TBL_USERS + " (" + T1COLUMN_ID + T1COLUMN_ID_TYPE
//            + T1COLUMN_USER_NAME + T1COLUMN_USER_EMAIL_TYPE + T1COLUMN_USER_EMAIL
//            + T1COLUMN_USER_EMAIL_TYPE + T1COLUMN_PASSWORD + T1COLUMN_PASSWORD_TYPE
//            + T1COLUMN_PHONE + T1COLUMN_PHONE_TYPE;

//    public static final String CREATE_TABLE2 = "CREATE TABLE " + TABLE2_NAME + " ( "
//            + T2COLUMN_ID + " int, " + T2COLUMN_CATEGORY + " int, " + T2COLUMN_NUMBER + " INT, "
//            + T2COLUMN_WORD + " TEXT, " + T2COLUMN_WORDR + " TEXT)";
    
//  public DAO (String db_server_name, String db_userid, String db_password){

    public DAO() {
        dbConnect(dbServerName, dbUserID, dbPassword);
        Tools.diff("dbConnect(dbServerName, dbUserID, dbPassword);", System.currentTimeMillis());
    }

    private void createDataBase(){
        // create database if not exist
        executeQuery(CREATE_DATABASE);
    }
    
    private void createTables(){
        // create table users
        executeQuery(CREATE_TABLE_USERS);
    }

    private void dbConnect(String db_server_name,
            String db_userid,
            String db_password) {
        try {
            // get the Class object
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Tools.diff("Class.forName(", System.currentTimeMillis());
            String db_connect_string = "jdbc:sqlserver://" + db_server_name + ";databaseName=" + DB_NAME;
        Tools.diff("String db_connect_string = ", System.currentTimeMillis());
            // establish a connection with the database
            this.conn = DriverManager.getConnection(db_connect_string,
                    db_userid, db_password);
        Tools.diff("this.conn = DriverManager.getConnection(db_connect_string,", System.currentTimeMillis());
            //print("connected");
            // create a Statement object
            this.statement = conn.createStatement();
        Tools.diff("this.statement = conn.createStatement();", System.currentTimeMillis());
            // create query
//            String queryString = "select * from sysobjects where type='u'";
//            // execute query and get a result
//            executeQuery(queryString);
//            // process the result
//            while (this.rs.next()) {
//                print(this.rs.getString(1));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeQuery(String queryString) {
        try {
            print("================ NEW QUERY ====================");
            print(queryString);
            if (this.statement.execute (queryString)) {
                this.rs = this.statement.getResultSet();
                print("1");
            }
            print("2");
        } catch (SQLException ex) {
            print("3");
            Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkUserPassword(UserProfile userProfile) {

        boolean unswer = false;
        String userName = userProfile.getUserName();
        String password = userProfile.getPassword();
        String query = "select " + T1COLUMN_ID + " from " + TBL_USERS + " where "
                + T1COLUMN_USER_NAME + " = '" + userName + "' and " + T1COLUMN_PASSWORD
                + " = '" + password + "'";
        executeQuery(query);
        try {
            if(rs.next()) unswer = true;
            if (unswer)  {
                while (this.rs.next()) {
                    print(this.rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return unswer;
    }

    public void saveUpdateUserPassword(String command) {

        String query;
        String userName = Personal_Organizer.userProfile.getUserName();
        String password = Personal_Organizer.userProfile.getPassword();
        String userEmail = Personal_Organizer.userProfile.getUserEmail();
        String phone = Personal_Organizer.userProfile.getPhone();
        String userID = Personal_Organizer.userProfile.getUserID();
        if(command.equals("save")) {
            query = "insert into " + TBL_USERS + " values ('" + userID + "', '" 
                + userName + "', '" + userEmail + "', '" + password + "', '" + 
                phone + "')";
        } else {
            query = "update " + TBL_USERS + " set " + T1COLUMN_USER_NAME + 
                " = '" + userName + "', " + T1COLUMN_USER_EMAIL + " = '" + 
                userEmail + "', " + T1COLUMN_PASSWORD + " = '" + password + 
                "', " + T1COLUMN_PHONE + " = '" +phone + "' where "  + 
                T1COLUMN_ID + " = '" + userID + "'";
        }
        executeQuery(query);
    }

    private void print(String str){
        if(showCommentForDebuging) System.out.println(str);
    }

    public boolean isTheLoginNameNotUsed(){
        boolean unswer = false;
        
        String userName = Personal_Organizer.userProfile.getUserName();
        String query = "select " + T1COLUMN_ID + " from " + TBL_USERS + " where "
                + T1COLUMN_USER_NAME + " = '" + userName + "'";
        executeQuery(query);
        try {
            if(!rs.next()) unswer = true;
            if (!unswer)  {
                while (this.rs.next()) {
                    print(this.rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return unswer;
    }
}
