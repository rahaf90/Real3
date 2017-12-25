package com.example.toshiba_pc.real3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by toshiba-pc on 22/12/17.
 */

public class MessageDAO {
    private SQLite_handler SQLitedb;
    private String date;
    private String emailannouncer;
    private String emailuser;
    private String phonenumber;
    private String textmessage;
    private int id;
    SQLiteDatabase db;

    //table name
    public static final String Table_messages = "messages";
    //culumns name
    public static final String KEY_date = "date";
    public static final String KEY_announceremail = "announcer email";
    public static final String KEY_useremail = "user email";
    public static final String KEY_phonenumber = "phone number";
    public static final String KEY_textmessage = "text message";

    public static final String[] CULUMNS = {KEY_date, KEY_announceremail, KEY_useremail, KEY_phonenumber, KEY_textmessage};

    public MessageDAO(Context c) {
        SQLitedb = new SQLite_handler(c);

    }


    public MessageDAO(String d, String em, String use, String phone, String text, Context c) {

        this.date = d;
        this.emailannouncer = em;
        this.emailuser = use;
        this.textmessage = text;
        this.phonenumber = phone;
        SQLitedb = new SQLite_handler(c);
    }

    //public String toString(){

    public String getEmailannouncer() {
        return this.emailannouncer;
    }

    ;

    public String getEmailuser() {
        return this.emailuser;
    }

    ;

    public String getTextmessage() {
        return this.textmessage;
    }

    ;

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public int getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String d) {
        this.date = d;
    }

    public void setEmailannouncer(String en) {
        this.emailannouncer = en;
    }

    public void setEmailuser(String em) {
        this.emailuser = em;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setTextmessage(String textmessage) {
        this.textmessage = textmessage;
    }

    public void setId(int identifier) {
        this.id = identifier;
    }

    public void addMessage(String date, String emailannouncer, String emailuser, String phonenumber, String textmessage) {
        db = SQLitedb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_date,date);
        contentValues.put(KEY_announceremail, emailannouncer);
        contentValues.put(KEY_useremail, emailuser);
        contentValues.put(KEY_phonenumber, phonenumber);
        contentValues.put(KEY_textmessage, textmessage);
        db.insert(Table_messages, null, contentValues);
        db.close();

    }

    public Cursor getallAnnouncements(SQLiteDatabase db) {
        Cursor cursor;
        db = SQLitedb.getReadableDatabase();
        String[] projection = {KEY_date, KEY_announceremail, KEY_useremail, KEY_phonenumber, KEY_textmessage};
        cursor = db.query(Table_messages, projection, null, null, null, null, null);
        db.close();
        return cursor;
    }

}


