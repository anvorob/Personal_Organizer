/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.dao;

import com.personal_organizer.Personal_Organizer;
import com.personal_organizer.modules.UserProfile;
import com.personal_organizer.db.DBFunctions;
import com.personal_organizer.modules.Tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
//import java.util.Date;
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
    //private static final String TBL_USERS = "tblUsers";
    //private static final String TBL_CONTACT_BOOK = "tblContactBook";
    //private static final String TBL_EVENTS = "tblEvents";
    private static final String TBL_MEMOS = "tblMemos";

    private static final String CREATE_DATABASE
            = "use master\nIF DB_ID (N'" + DB_NAME + "') IS NULL\n"
            + "CREATE DATABASE OrganizerDB\nCOLLATE  Latin1_General_BIN\nWITH "
            + "TRUSTWORTHY ON, DB_CHAINING ON";

    private static final String USE_DATABASE
            = "use OrganizerDB";

    private static final String CREATE_TABLE_USERS = "IF not EXISTS (SELECT 1 FROM "
            + "INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='tblUsers')\n"
            + "CREATE TABLE tblUsers (_user_id char(10) NOT NULL PRIMARY KEY, "
            + "_user_first_name varchar(15),\n"
            + "_user_last_name varchar(15),\n"
            + "_login_name varchar(10) NOT NULL,\n"
            + "_user_email varchar(30) NOT NULL,\n"
            + "_password char(32) NOT NULL,\n"
            + "_user_birth_day date,\n"
            + "_phone varchar(10))";

    private static final String CREATE_TABLE_CONTACT_BOOK = "IF not EXISTS (SELECT 1 FROM "
            + "INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='tblContactBook')\n"
            + "CREATE TABLE tblContactBook "
            + "(_contact_id char(10) NOT NULL PRIMARY KEY,\n"
            + "_user_id char(10) NOT NULL FOREIGN KEY REFERENCES tblUsers(_user_id),\n"
            + "_first_name varchar(15) NOT NULL,\n"
            + "_last_name varchar(15) NOT NULL,\n"
            + "_email varchar(30) NOT NULL,\n"
            + "_phone varchar(10),\n"
            + "_notes varchar(50))";

    private static final String CREATE_TABLE_EVENTS = "IF not EXISTS (SELECT 1 FROM "
            + "INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='tblEvents')"
            + "CREATE TABLE tblEvents"
            + "(_user_id char(10) NOT NULL FOREIGN KEY REFERENCES tblUsers(_user_id),\n"
            + "	_event_id char(10) NOT NULL PRIMARY KEY,\n"
            + "	_day date NOT NULL,\n"
            + "	_time_from time NOT NULL,\n"
            + "	_time_till time NOT NULL,\n"
            + "	_description varchar(100) NOT NULL,\n"
            + "	_type int NOT NULL,\n"
            + "	_contacts varchar(100))";

    private static final String CREATE_MEMOS = "IF not EXISTS (SELECT 1 FROM "
            + "INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='tblMemos')"
            + "CREATE TABLE tblMemos "
            + "(_user_id char(10) NOT NULL FOREIGN KEY REFERENCES tblUsers(_user_id),\n"
            + "	_memo_id char(10) NOT NULL PRIMARY KEY,\n"
            + "	_memo_description varchar(100) NOT NULL,\n"
            + "	_due date NOT NULL,\n"
            + "	_color int NOT NULL)";

    private static void createDataBase() {
        // create database if not exist
        executeUpdate(CREATE_DATABASE);
        executeUpdate(USE_DATABASE);
        createTables();
        Personal_Organizer.dbExist = true;
    }

    private static void createTables() {
        // create table users
        executeUpdate(CREATE_TABLE_USERS);
    }

    private static void dbConnect() {
        try {
            // get the Class object
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Tools.diff("Class.forName(", System.currentTimeMillis());
            //String db_connect_string = "jdbc:sqlserver://" + dbServerAddress + ";databaseName=" + DB_NAME;
            String db_connect_string = "jdbc:sqlserver://" + dbServerAddress;
            Tools.diff("String db_connect_string = ", System.currentTimeMillis());
            // establish a connection with the database
            System.out.println("conn = DriverManager.getConnection(" + db_connect_string + ","
                    + dbServerUserName + "," + dbServerPassword + ";");
            conn = DriverManager.getConnection(db_connect_string,
                    dbServerUserName, dbServerPassword);
            Tools.diff("this.conn = DriverManager.getConnection(db_connect_string,", System.currentTimeMillis());
            //print("connected");
            // create a Statement object
            statement = conn.createStatement();
            if (!Personal_Organizer.dbExist) {
                createDataBase();
            }
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

    private static void dbClose() {
        try {
            // get the Class object
            conn.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void executeQuery(String queryString) {
        try {
            Tools.print("================ NEW QUERY ====================");
            Tools.print(queryString);
            rs = statement.executeQuery(queryString);
        } catch (SQLException ex) {
//            Tools.print("3");
//            Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void executeQueryP(String queryString, String[] params) {
        try {
            Tools.print("================ NEW QUERY ====================");
            Tools.print(queryString);
            PreparedStatement prepStmt = conn.prepareStatement(queryString);
            for (int i = 0; i < params.length; i++) {
                prepStmt.setString(i + 1, params[i]);
            }

            rs = prepStmt.executeQuery();
        } catch (SQLException ex) {
//            Tools.print("3");
//            Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int executeUpdate(String queryString) {
        int rows = 0;
        try {
            Tools.print("================ NEW QUERY ====================");
            Tools.print(queryString);
            rows = statement.executeUpdate(queryString);
        } catch (SQLException ex) {
//            Tools.print("3");
//            Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(queryString + " ==> " + rows);
        return rows;
    }

    public static boolean checkUserPassword(UserProfile userProfile) {
        dbConnect();
        boolean unswer = false;
        String loginName = userProfile.getLoginName();
        String password = Tools.md5Custom(userProfile.getPassword());

        String query = "select * from tblUsers where "
                + "_login_name = '" + loginName + "' and _password = "
                + "'" + password + "'";
        String query1 = "select * from tblUsers where "
                + "_login_name = ? and _password = ?";
        String[] params;
        params = new String[2];
        params[0] = loginName;
        params[1] = password;
        executeQueryP(query1, params);
        try {
            if (rs.next()) {
                unswer = true;
//                System.out.println();
//                System.out.println("1 UserID = " + rs.getString(1));
//                System.out.println("2 FirstName = " + rs.getString(2));
//                System.out.println("3 LastName = " + rs.getString(3));
//                System.out.println("5 UserEmail = " + rs.getString(5));
//                
////                new SimpleDateFormat("yyyy-MM-dd").format(date)
////                SimpleDateFormat dt1 = new SimpleDateFormat("dd/mmmm/yyyy");
//                System.out.println("6 BirthDay = " + new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(7)));
                Personal_Organizer.userProfile.setUserID(rs.getString(1));
                Personal_Organizer.userProfile.setFirstName(rs.getString(2));
                Personal_Organizer.userProfile.setLastName(rs.getString(3));
                Personal_Organizer.userProfile.setUserEmail(rs.getString(5));
                Personal_Organizer.userProfile.setBirthDay(rs.getDate(7));
                Personal_Organizer.userProfile.setPhone(rs.getString(8));
//                userProfile.setUserID(rs.getString(1));
//                userProfile.setFirstName(rs.getString(2));
//                userProfile.setLastName(rs.getString(3));
//                userProfile.setUserEmail(rs.getString(4));
//                userProfile.setBirthDay(rs.getDate(7));
//                userProfile.setPhone(rs.getString(8));
                //userProfile.getPassword();
            }
            if (unswer) {
                while (rs.next()) {
                    Tools.print(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbClose();

        return unswer;
    }

    public static int saveUpdateUserPassword(String command) {
        int rows = 0;
        dbConnect();
        String query;
//        String[] params;
//        params = new String[5];
//        
//        params[0] = Personal_Organizer.userProfile.getFirstName(); // firstName
//        params[1] = Personal_Organizer.userProfile.getLastName(); // lastName
//        params[2] = Personal_Organizer.userProfile.getLoginName(); // loginName
//        params[3] = Personal_Organizer.userProfile.getUserEmail(); // userEmail
//        params[4] = Personal_Organizer.userProfile.getPassword(); // password
//        int birthDayYear = Personal_Organizer.userProfile.getBirthDayYear();
//        int birthDayMonth = Personal_Organizer.userProfile.getBirthDayMonth();
//        int birthDayDay = Personal_Organizer.userProfile.getBirthDayDay();
//        Date birthDay = Personal_Organizer.userProfile.getBirthDay();
//        
//        params[5] = Personal_Organizer.userProfile.getPhone(); // phone
//        params[6] = Personal_Organizer.userProfile.getUserID(); // userID

        String firstName = Personal_Organizer.userProfile.getFirstName();
        String lastName = Personal_Organizer.userProfile.getLastName();
        String loginName = Personal_Organizer.userProfile.getLoginName();
        String userEmail = Personal_Organizer.userProfile.getUserEmail();
        String password = Personal_Organizer.userProfile.getPassword();
        int birthDayYear = Personal_Organizer.userProfile.getBirthDayYear();
        int birthDayMonth = Personal_Organizer.userProfile.getBirthDayMonth();
        int birthDayDay = Personal_Organizer.userProfile.getBirthDayDay();
        Date birthDay = Personal_Organizer.userProfile.getBirthDay();
        String phone = Personal_Organizer.userProfile.getPhone();
        String userID = Personal_Organizer.userProfile.getUserID();
        if (command.equals("save")) {
            query = "insert into tblUsers values ('" + userID + "', '"
                    + firstName + "', '" + lastName + "', '" + loginName + "', '"
                    + userEmail + "', '" + password + "', '" + birthDay.getYear()
                    + ((birthDay.getMonth() < 10) ? "0" : "") + birthDay.getMonth()
                    + ((birthDay.getDate() < 10) ? "0" : "") + birthDay.getDate()
                    + "', '" + phone + "')";
        } else {
            query = "update tblUsers set _user_first_name = " + firstName
                    + ", _user_last_name  = " + lastName + ", _user_email = '"
                    + userEmail + "', _password = '" + password + "', _phone = '"
                    + phone + "' where " + "_user_id = '" + userID + "'";
        }
        System.out.println(query);
        rows = executeUpdate(query);
        dbClose();
        return rows;
    }

    public static boolean isTheLoginNameNotUsed() {
        boolean unswer = false;

        String userName = Personal_Organizer.userProfile.getLoginName();
        String query = "select _user_id from tblUsers where _login_name = '"
                + userName + "'";
        System.out.println(query);
        dbConnect();
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

        dbClose();
        return unswer;
    }

    public static void getEvent() {
        String query = "select * from  tblEvents where "
                + "_user_id = 'YRKV2H5QGV'";
        dbConnect();
        executeQuery(query);
        try {

            while (rs.next()) {
                Tools.print(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6));
            }

        } catch (SQLException ex) {
            //Logger.getLogger(DBFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        dbClose();
    }
}
