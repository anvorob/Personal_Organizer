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

    public static String dbServerAddress;
    public static String dbServerUserName;
    public static String dbServerPassword;

    private static boolean showCommentForDebuging = false;
    private static ResultSet rs;
    private static Connection conn;
    private static Statement statement;
    //private String db_connect_string, db_userid, db_password;
    private static String dbPath;
    private static final String DB_NAME = "OrganizerDB";
    private static final String TBL_USERS = "tblUsers";
    private static final String TBL_CONTACT_BOOK = "tblContactBook";
    private static final String TBL_EVENTS = "tblEvents";
    private static final String TBL_MEMOS = "tblMemos";

    private static final String T1COLUMN_ID = "_user_id";
    private static final String T1COLUMN_ID_TYPE = " char(10) NOT NULL PRIMARY KEY, ";
    private static final String T1COLUMN_LOGIN_NAME = "_login_name";
    private static final String T1COLUMN_LOGIN_NAME_TYPE = " char(10) NOT NULL, ";
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
            + T1COLUMN_LOGIN_NAME + T1COLUMN_LOGIN_NAME_TYPE + T1COLUMN_USER_EMAIL
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
//    public DAO() {
//        dbConnect();
//        Tools.diff("dbConnect();", System.currentTimeMillis());
//    }

    private void createDataBase() {
        // create database if not exist
        executeQuery(CREATE_DATABASE);
    }

    private void createTables() {
        // create table users
        executeQuery(CREATE_TABLE_USERS);
    }

    private static void dbConnect() {
        try {
            // get the Class object
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Tools.diff("Class.forName(", System.currentTimeMillis());
            String db_connect_string = "jdbc:sqlserver://" + dbServerAddress + ";databaseName=" + DB_NAME;
            Tools.diff("String db_connect_string = ", System.currentTimeMillis());
            // establish a connection with the database
            conn = DriverManager.getConnection(db_connect_string,
                    dbServerUserName, dbServerPassword);
            Tools.diff("this.conn = DriverManager.getConnection(db_connect_string,", System.currentTimeMillis());
            //print("connected");
            // create a Statement object
            statement = conn.createStatement();
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

    private static void executeQuery(String queryString) {
        try {
            Tools.print("================ NEW QUERY ====================");
            Tools.print(queryString);
            if (statement.execute(queryString)) {
                rs = statement.getResultSet();
                Tools.print("1");
            }
            Tools.print("2");
        } catch (SQLException ex) {
            Tools.print("3");
            Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean checkUserPassword(UserProfile userProfile) {
        dbConnect();
        boolean unswer = false;
        String loginName = userProfile.getLoginName();
        String password = userProfile.getPassword();

        String query = "select " + T1COLUMN_ID + " from " + TBL_USERS + " where "
                + T1COLUMN_LOGIN_NAME + " = '" + loginName + "' and " + T1COLUMN_PASSWORD
                + " = '" + password + "'";
        executeQuery(query);
        try {
            if (rs.next()) {
                unswer = true;
            }
            if (unswer) {
                while (rs.next()) {
                    Tools.print(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return unswer;
    }

    public static void saveUpdateUserPassword(String command) {

        String query;
        String userName = Personal_Organizer.userProfile.getLoginName();
        String password = Personal_Organizer.userProfile.getPassword();
        String userEmail = Personal_Organizer.userProfile.getUserEmail();
        String phone = Personal_Organizer.userProfile.getPhone();
        String userID = Personal_Organizer.userProfile.getUserID();
        if (command.equals("save")) {
            query = "insert into " + TBL_USERS + " values ('" + userID + "', '"
                    + userName + "', '" + userEmail + "', '" + password + "', '"
                    + phone + "')";
        } else {
            query = "update " + TBL_USERS + " set " + T1COLUMN_LOGIN_NAME
                    + " = '" + userName + "', " + T1COLUMN_USER_EMAIL + " = '"
                    + userEmail + "', " + T1COLUMN_PASSWORD + " = '" + password
                    + "', " + T1COLUMN_PHONE + " = '" + phone + "' where "
                    + T1COLUMN_ID + " = '" + userID + "'";
        }
        executeQuery(query);
    }

    public static boolean isTheLoginNameNotUsed() {
        boolean unswer = false;

        String userName = Personal_Organizer.userProfile.getLoginName();
        String query = "select " + T1COLUMN_ID + " from " + TBL_USERS + " where "
                + T1COLUMN_LOGIN_NAME + " = '" + userName + "'";
        executeQuery(query);
        try {
            if (!rs.next()) {
                unswer = true;
            }
            if (!unswer) {
                while (rs.next()) {
                    Tools.print(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return unswer;
    }
}
