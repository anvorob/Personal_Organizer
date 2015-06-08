/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal_organizer.modules;

import com.personal_organizer.LoginForm;
import com.personal_organizer.Personal_Organizer;
import com.personal_organizer.SignUpForm;
import com.personal_organizer.dao.DAO;
import com.personal_organizer.db.DBFunctions;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Random;
//import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import java.io.*;

/**
 *
 * @author Mikhail
 */
public class Tools {

    public static long time;
    private static boolean showCommentForDebuging = true;
    // указываем путь к файлу с которым мы будем работать
    private static final String PATH = "PersonalOrganizer.ini";
    // класс для чтения файла
    private static InputStream inputstream;
    // класс для записи в файл
    private static OutputStream outputStream;

    //public static org.apache.commons.lang.time.StopWatch sw;
    public static String caption() {
        String caption = "";
        if (!Personal_Organizer.userProfile.getLoginName().equals("")) {
            caption = " - " + Personal_Organizer.userProfile.getLoginName();
        }

        return "Personal Organizer" + caption;
    }

    public static String millisToShortDHMS(long duration) {
        String res = "";
        long days = TimeUnit.MILLISECONDS.toDays(duration);
        long hours = TimeUnit.MILLISECONDS.toHours(duration)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
        if (days == 0) {
            res = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            res = String.format("%dd%02d:%02d:%02d", days, hours, minutes, seconds);
        }
        return res;
    }

    public static void diff(String str, long duration) {
        if (showCommentForDebuging) {
            System.out.println(str);
            System.out.print(duration + " - " + time + " = ");
            duration = duration - time;
            System.out.print(duration + " (");
            System.out.println(millisToShortDHMS(duration) + ")");

        }
    }

    public static void print(String str) {
        if (showCommentForDebuging) {
            System.out.println(str);
        }
    }

    public static String read() {
        String str = "";
        try {
            // инициализируем поток на чтение
            inputstream = new FileInputStream(PATH);

            // читаем первый символ в байтах (ASCII)
            int data = inputstream.read();
            char content;
            // по байтово читаем весь файл
            while (data != -1) {
                // преобразуем полученный байт в символ
                content = (char) data;
                // выводим посимвольно
                str = str + content;
                //System.out.print(content);
                data = inputstream.read();
            }
            // закрываем поток
            inputstream.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        return str;
    }

    // запись в файл используя OutputStream
    public static void write(String st) {
        try {
            // инициализируем поток для вывода данных
            // что позволит нам записать новые данные в файл
            outputStream = new FileOutputStream(PATH);
            // передаем полученную строку st и приводим её к byte массиву.
            outputStream.write(st.getBytes());
            // закрываем поток вывода
            // только после того как мы закроем поток данные попадут в файл.
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String encryption(String password) {
        System.out.println(password);
        StringBuilder newPassword = new StringBuilder("");
        for (int i = 0; i < password.length(); i++) {
            int k = i + 1;
            int j = (int) password.charAt(i);
            j = j + ((i + 1) * ((k % 2) == 0 ? -1 : 1));
            newPassword.append((char) j);
        }
        return newPassword.toString();
    }

    public static String encryptionA(String password) {
        StringBuilder tmpPassword = new StringBuilder("");

        System.out.println("0 - " + password);
        StringBuilder newPassword = new StringBuilder("");
        for (int i = 0; i < password.length(); i++) {
            int k = i + 1;
            int j = (int) password.charAt(i);
            int l = ("" + j).length();
            tmpPassword.append(l);
            tmpPassword.append(j);
        }
        System.out.println("1 - " + tmpPassword);
        String tmpPswrd = tmpPassword.toString();
        int j = tmpPswrd.length() / 2;
        for (int i = 0; i < j; i++) {
            System.out.println(Integer.parseInt(tmpPswrd.substring(0, 2)));
            char tmpPart = (char) Integer.parseInt(tmpPswrd.substring(0, 2));
            tmpPswrd = tmpPswrd.substring(2);
            newPassword.append(tmpPart);
        }
        if (tmpPswrd.length() > 0) {
            char tmpPart = (char) Integer.parseInt(tmpPswrd);
            newPassword.append(tmpPart);
        }

        return newPassword.toString();
    }

    public static String decryption(String password) {
        StringBuilder newPassword = new StringBuilder("");
        for (int i = 0; i < password.length(); i++) {
            int k = i + 1;
            int j = (int) password.charAt(i);
            j = j + ((i + 1) * ((k % 2) == 0 ? 1 : -1));
            newPassword.append((char) j);
        }
        return newPassword.toString();
    }

    public static String decryptionA(String password) {
        StringBuilder newPassword = new StringBuilder("");
        StringBuilder tmpPassword = new StringBuilder("");
        for (int i = 0; i < password.length(); i++) {
            int k = i + 1;
            int code = (int) password.charAt(i);
            String j = String.valueOf(code);
            if (j.length() < 2) {
                if (k < password.length()) {
                    j = "0" + j;
                }
            }
            tmpPassword.append(j);
        }
        String tmpPswrd = tmpPassword.toString();
        System.out.println(tmpPswrd);
        String s = encryptionA("123");
        while (tmpPswrd.length() > 2) {
            int number = Integer.parseInt(tmpPswrd.substring(0, 1));
            char tmpPart = (char) Integer.parseInt(tmpPswrd.substring(1, 1 + number));
            System.out.println(number + " " + Integer.parseInt(tmpPswrd.substring(0, 1 + number)) + " " + Integer.parseInt(tmpPswrd.substring(1, 1 + number)));
            newPassword.append(tmpPart);
            tmpPswrd = tmpPswrd.substring(1 + number);
        }
        return newPassword.toString();
    }

    public static void readSettings(LoginForm frmLogin) {
        String str = read();
        String[] str1 = str.split("\r\n");
        System.out.println(str1.length);

        for (int i = 0; i < str1.length; i++) {
            String param = str1[i].substring(0, str1[i].indexOf('='));
            str = str1[i].replaceAll(param + "=", "");
            System.out.println(str1[i] + " - " + str1[i].indexOf('=') + " - " + param + " = " + str);

            switch (param) {
                case "RememberLogin":
                    frmLogin.setRememberLogin(str.equals("true"));
                    break;
                case "LoginName":
                    if (str.equals("")) {
                        frmLogin.setRememberLogin(false);
                    } else if (frmLogin.getRememberLogin()) {
                        frmLogin.setLoginName(str);
                    }
                    break;
                case "Password":
                    if (str.equals("")) {
                        frmLogin.setRememberLogin(false);
                    } else if (frmLogin.getRememberLogin()) {
                        frmLogin.setPassword(decryptionA(str));
                    }
                    break;
                case "RememberSQLSettings":
                    frmLogin.setRememberSQLSettings(str.equals("true"));
                    break;
                case "ServerAddress":
                    if (str.equals("")) {
                        frmLogin.setRememberSQLSettings(false);
                    } else if (frmLogin.getRememberSQLSettings()) {
                        frmLogin.setServerAddress(str);
                        DAO.dbServerAddress = str;
                    }
                    break;
                case "ServerUserName":
                    if (str.equals("")) {
                        frmLogin.setRememberSQLSettings(false);
                    } else if (frmLogin.getRememberSQLSettings()) {
                        frmLogin.setServerUserName(str);
                        DAO.dbServerUserName = frmLogin.getServerUserName();
                    }
                    break;
                case "ServerPassword":
                    if (str.equals("")) {
                        frmLogin.setRememberSQLSettings(false);
                    } else if (frmLogin.getRememberSQLSettings()) {
                        frmLogin.setServerPassword(decryptionA(str));
                        DAO.dbServerPassword = frmLogin.getServerPassword();
                    }
                    break;
            }
        }
    }

    public static void writeSettings(LoginForm frmLogin) {
        StringBuilder str;
        str = new StringBuilder("");
        str.append("RememberLogin");
        str.append("=");
        str.append(frmLogin.getRememberLogin());
        str.append("\r\n");
        str.append("LoginName");
        str.append("=");
        str.append(frmLogin.getLoginName());
        str.append("\r\n");
        str.append("Password");
        str.append("=");
        str.append(encryptionA(frmLogin.getPassword()));
        str.append("\r\n");
        str.append("RememberSQLSettings");
        str.append("=");
        str.append(frmLogin.getRememberSQLSettings());
        str.append("\r\n");
        str.append("ServerAddress");
        str.append("=");
        str.append(frmLogin.getServerAddress());
        DAO.dbServerAddress = frmLogin.getServerAddress();
        str.append("\r\n");
        str.append("ServerUserName");
        str.append("=");
        str.append(frmLogin.getServerUserName());
        DAO.dbServerUserName = frmLogin.getServerUserName();
        str.append("\r\n");
        str.append("ServerPassword");
        str.append("=");
        str.append(encryptionA(frmLogin.getServerPassword()));
        DAO.dbServerPassword = frmLogin.getServerPassword();

        write(str.toString());
    }

    public static int saveUpdateUserProfile(SignUpForm frmSignUp, String command) {
        int rows = 0;
        System.out.println("btnSave");
        setUserProfile(frmSignUp);
        rows = DBFunctions.saveUpdateUserPassword(command);
        return rows;
    }

    public static void setUserProfile(SignUpForm frmSignUp) {
        System.out.println("setUserProfile");

        Personal_Organizer.userProfile = new UserProfile(frmSignUp.getLoginName(),
                frmSignUp.getPassword(), frmSignUp.getEmail());
        Personal_Organizer.userProfile.setFirstName(frmSignUp.getFirstName());
        Personal_Organizer.userProfile.setLastName(frmSignUp.getLastName());
        Personal_Organizer.userProfile.setBirthDayYear(frmSignUp.getBirthDayYear());
        Personal_Organizer.userProfile.setBirthDayMonth(frmSignUp.getBirthDayMonth());
        Personal_Organizer.userProfile.setBirthDayDay(frmSignUp.getBirthDayDay());

        int year = frmSignUp.getBirthDayYear();
        int month = frmSignUp.getBirthDayMonth();
        int day = frmSignUp.getBirthDayDay();
        Personal_Organizer.userProfile.setBirthDay(new Date(year, month, day));

    }

    public static String generateCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // используемые символы
        String code = "";
        Random r = new Random();
        for(int i = 0; i < length; i++) { // длинна кода  от 10
            code = code + chars.charAt(r.nextInt(chars.length()));
        }
        return code;
    }

    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // С‚СѓС‚ РјРѕР¶РЅРѕ РѕР±СЂР°Р±РѕС‚Р°С‚СЊ РѕС€РёР±РєСѓ
            // РІРѕР·РЅРёРєР°РµС‚ РѕРЅР° РµСЃР»Рё РІ РїРµСЂРµРґР°РІР°РµРјС‹Р№ Р°Р»РіРѕСЂРёС‚Рј РІ getInstance(,,,) РЅРµ СЃСѓС‰РµСЃС‚РІСѓРµС‚
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

}
