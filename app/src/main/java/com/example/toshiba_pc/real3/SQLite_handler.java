package com.example.toshiba_pc.real3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by toshiba-pc on 22/12/17.
 */

public class SQLite_handler extends SQLiteOpenHelper {

    private static final int database_version = 1;
    private static final String database_name = "the message";

    public static final String announcementid = "rel_id";

    public SQLite_handler(Context context) {
        super(context, database_name, null, database_version);
    }
    public static final String Announcement_table = "announcement";
    @Override
    public void onCreate(SQLiteDatabase db) {

        String Create_message_table = "CREATE TABLE messages(" +"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " date Text ," +
                "announcer email text," +
                " user email TEXT," +
                "phone number INTEGER," +
                " text message TEXT)";

        String Create_apartement_table = "CREATE TABLE aparetemnt(" +"id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " address TEXT ," + "floor number INTEGER," + "apartement number INTEGER," + "bedrooms number INTEGER, " +
                "bathrooms number INTEGER ," + ",enterencedoor number INTEGER"+announcementid+" INTEGER)";

        String Create_house_table ="CREATE TABLE house(" +"id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " address TEXT ," + "garden TEXT,"+"elevator TEXT"+"swimming pool TEXT,"+announcementid+" INTEGER"+"FOREIGN KEY("+
                announcementid+")";

        String Create_land_table="CREATE TABLE land("+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "+"address TEXT,"+"length REAL,"+"width REAL ,"+
                "Building permit TEXT ,"+announcementid+" INTEGER)";



        String Create_announcement_table="CREATE TABLE "+Announcement_table+"("+ "_id INTEGER PRIMARY KEY AUTOINCREMENT," +"image integer ," +
                "title TEXT,"+"price INTEGER)";
        db.execSQL(Create_announcement_table);
        db.execSQL(Create_message_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists announcement");
        db.execSQL("drop table if exists messages");

        this.onCreate(db);
    }
}