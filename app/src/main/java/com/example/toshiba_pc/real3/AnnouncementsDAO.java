package com.example.toshiba_pc.real3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by toshiba-pc on 22/12/17.
 */

public class AnnouncementsDAO {


    private SQLite_handler db_handler;
    private String title;
    private String price;
    private int image;
    SQLiteDatabase db;
    private int id;


    public static final String Table_announcement = "announcement";
    public static final String KEY_id = "_id";
    public static final String KEY_image = "image";
    public static final String KEY_title = "title";
    public static final String KEY_price = "price";
    public static final String[] COLUMNS = {KEY_id, KEY_image, KEY_price, KEY_title};

    public AnnouncementsDAO(Context context) {
        db_handler = new SQLite_handler(context);
    }

    public AnnouncementsDAO(int id, String title, String price, int image, Context context) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.price = price;


        db_handler = new SQLite_handler(context);
    }


    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setPrice(String price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    // getting image
    public int getImage() {
        return this.image;
    }

    // setting image
    public void setImage(int image) {
        this.image = image;
    }

    public void addannouncement(int image, String title, String price) {
        db = db_handler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_price, price);
        values.put(KEY_title, title);
        values.put(KEY_image, image);
        db.insert(Table_announcement,null,values);
        db.close();
    }


    public AnnouncementsDAO getannouncement(int id, Context context) {
        SQLiteDatabase db = db_handler.getReadableDatabase();
        Cursor cursor =
                db.query(Table_announcement, // a. table
                        COLUMNS, // b. column names
                        " _id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        AnnouncementsDAO announcements = null;
        // 3. if we got results get the first one
        if (cursor.moveToFirst()) {
            // 4. build book object
            announcements = new AnnouncementsDAO(context);
            announcements.setId(Integer.parseInt(cursor.getString(0)));
            announcements.setImage(cursor.getInt(2));
            announcements.setTitle(cursor.getString(1));
            announcements.setPrice(cursor.getString(3));
        }
        db.close();
        return (announcements);

    }


    public ArrayList<AnnouncementsDAO> getAllannouncement(Context context) {
        ArrayList<AnnouncementsDAO> announcements = new ArrayList<AnnouncementsDAO>();

        // 1. build the query
        String query = "SELECT  * FROM " + Table_announcement;
        // 2. get reference to readable DB
        SQLiteDatabase db = db_handler.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        AnnouncementsDAO announcements1 = null;
        if (cursor.moveToFirst()) {
            do {
                announcements1 = new AnnouncementsDAO(context);
                announcements1.setId(Integer.parseInt(cursor.getString(0)));
                announcements1.setImage(cursor.getInt(2));
                announcements1.setTitle(cursor.getString(1));
                announcements1.setPrice((cursor.getString(3)));


                announcements.add(announcements1);
            } while (cursor.moveToNext());
        }
        db.close();
        return announcements;
    }

}
