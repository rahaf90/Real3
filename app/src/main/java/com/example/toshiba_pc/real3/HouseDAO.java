package com.example.toshiba_pc.real3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by toshiba-pc on 22/12/17.
 */

public class HouseDAO {

    private SQLite_handler db_handler;
    private String address;
    private String elevator;
    private String garden;
    private String swimmingpool;
    private int id;



    public HouseDAO(Context context){
        db_handler=new SQLite_handler(context);

    }
    public HouseDAO (String address,String elevator, String garden,String swimmingpool,Context context){

        this.address=address;
        this.elevator=elevator;
        this.garden=garden;
        this.swimmingpool=swimmingpool;
        db_handler=new SQLite_handler(context);

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getElevator() {
        return elevator;
    }

    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    public String getGarden() {
        return garden;
    }
    public String getSwimmingpool() {
        return swimmingpool;
    }

    public int getId() {
        return id;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSwimmingpool(String swimmingpool) {
        this.swimmingpool = swimmingpool;
    }


    private static final String Table_house = "house";
    //culumns
    private static final String KEY_ID = "id";
    private static final String KEY_elevator = "elevator";
    private static final String KEY_garden = "garden";
    private static final String KEY_swimmimgpool = "swimmimgpool";
    private static final String KEY_address = "address";

    private static final String[] COLUMNS = {KEY_ID,KEY_elevator,KEY_garden,KEY_swimmimgpool,KEY_address};

    public void addhouse(String elevator,String garden,String swimmingpool,String address){
        SQLiteDatabase db =db_handler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_address,getAddress());
        values.put(KEY_elevator,getElevator());
        values.put(KEY_garden,getGarden());
        values.put(KEY_swimmimgpool,getSwimmingpool());

        db.insert(Table_house, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        db.close();
    }

    public HouseDAO gethouse(int id, Context context) {
        // 1. get reference to readable DB
        SQLiteDatabase db = db_handler.getReadableDatabase();
        // 2. build query
        Cursor cursor =
                db.query(Table_house, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        HouseDAO house = null;
        // 3. if we got results get the first one
        if (cursor.moveToFirst()) {
            // 4. build book object
            house = new HouseDAO(context);
            house.setId(Integer.parseInt(cursor.getString(0)));
            house.setAddress(cursor.getString(1));
            house.setElevator(cursor.getString(2));
            house.setGarden(cursor.getString(3));
            house.setSwimmingpool(cursor.getString(4));
        }
        db.close();
        return house;
    }


}
