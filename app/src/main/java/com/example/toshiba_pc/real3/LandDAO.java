package com.example.toshiba_pc.real3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by toshiba-pc on 24/12/17.
 */

public class LandDAO {

    private SQLite_handler db_handler;
    private int id;
    private Double length;
    private Double width;
    private String buildingpermit ;
    private String address;



    public LandDAO(Context context){

        db_handler=new SQLite_handler(context);
    }

    public LandDAO(String address,Double length,Double width , String buildingpermit,Context context){
        this.address=address;
        this.length=length;
        this.width=width;
        this.buildingpermit=buildingpermit;
        db_handler=new SQLite_handler(context);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLength() {
        return length;
    }


    public Double getWidth() {
        return width;
    }


    public void setLength(Double length) {
        this.length = length;
    }


    public String getBuildingpermit() {
        return buildingpermit;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public void setBuildingpermit(String buildingpermit) {
        this.buildingpermit = buildingpermit;
    }

    private static final String Table_land = "land";
    private static final String KEY_id = "id";
    private static final String KEY_address = "address";
    private static final String KEY_length = "length";
    private static final String KEY_width = "width";
    private static final String KEY_Buildingpermit = "Building permit";

    private static final String[] COLUMNS = {KEY_id,KEY_address,KEY_length,KEY_width,KEY_Buildingpermit};



    public void addland(LandDAO land){
        // 1. get reference to writable DB
        SQLiteDatabase db =db_handler.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_address, land.getAddress());
        values.put(KEY_length, land.getLength());
        values.put(KEY_width,land.getWidth());
        values.put(KEY_Buildingpermit, land.getBuildingpermit());

        // 3. insert
        db.insert(Table_land, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public LandDAO getland(int id, Context context) {
        // 1. get reference to readable DB
        SQLiteDatabase db = db_handler.getReadableDatabase();
        // 2. build query
        Cursor cursor =
                db.query(Table_land, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        LandDAO land = null;
        // 3. if we got results get the first one
        if (cursor.moveToFirst()) {
            // 4. build book object
            land = new LandDAO(context);
            land.setId(Integer.parseInt(cursor.getString(0)));
            land.setAddress(cursor.getString(1));
            land.setLength(Double.valueOf(cursor.getString(2)));
            land.setWidth(Double.valueOf(cursor.getString(3)));
            land.setBuildingpermit(cursor.getString(4));
        }
        // 5. return book
        return land;
    }
    // Get All Books
    public ArrayList<LandDAO> getAllLands(Context context) {
        ArrayList<LandDAO> lands = new ArrayList<LandDAO>();

        // 1. build the query
        String query = "SELECT  * FROM " + Table_land;
        // 2. get reference to readable DB
        SQLiteDatabase db = db_handler.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        LandDAO land;
        if (cursor.moveToFirst()) {
            do {
                land = new LandDAO(context);
                land.setId(Integer.parseInt(cursor.getString(0)));
                land.setAddress(cursor.getString(1));
                land.setLength(Double.valueOf(cursor.getString(2)));
                land.setWidth(Double.valueOf(cursor.getString(3)));
                land.setBuildingpermit(cursor.getString(4));
                // Add book to books
                lands.add(land);
            } while (cursor.moveToNext());
        }
        // return books
        return lands;
    }
    // Updating single book
    public int updateland(LandDAO land) {

        // 1. get reference to writable DB
        SQLiteDatabase db = db_handler.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_address, land.getAddress());
        values.put(KEY_length, land.getLength());
        values.put(KEY_width, land.getWidth());
        values.put(KEY_Buildingpermit, land.getBuildingpermit());

        // 3. updating row
        int i = db.update(Table_land, //table
                values, // column/value
                KEY_id+" = ?", // selections
                new String[] { String.valueOf(land.getId()) }); //selection args

        // 4. close
        db.close();
        return i;
    }
    // Deleting single book
    public void deleteland(LandDAO land) {

        // 1. get reference to writable DB
        SQLiteDatabase db = db_handler.getWritableDatabase();

        // 2. delete
        db.delete(Table_land,
                KEY_id+" = ?",
                new String[] { String.valueOf(land.getId()) });

        // 3. close
        db.close();
    }
}












