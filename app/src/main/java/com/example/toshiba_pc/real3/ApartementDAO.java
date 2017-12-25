package com.example.toshiba_pc.real3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by toshiba-pc on 24/12/17.
 */

public class ApartementDAO {


    private String address;
    private int floornumber;
    private  int apartmentnumber;
    private int bedroomsnumber;
    private int bathroomsnumber;
    private int enterancedoornumber;
    private SQLite_handler db_handler;
    private int id;


    public ApartementDAO(Context context){
        db_handler=new SQLite_handler(context);

    }
    public ApartementDAO (String address,int floornumber, int apartmentnumber,int bedroomsnumber,int bathroomsnumber,int enterancedoornumber,Context context){

        this.address=address;
        this.apartmentnumber=apartmentnumber;
        this.bathroomsnumber=bathroomsnumber;
        this.bedroomsnumber=bedroomsnumber;
        this.enterancedoornumber=enterancedoornumber;
        this.floornumber=floornumber;
        db_handler=new SQLite_handler(context);

    }

    public int getFloornumber() {
        return floornumber;
    }

    public String getAddress() {
        return address;
    }

    public int getApartmentnumber() {
        return apartmentnumber;
    }

    public int getBedroomsnumber() {
        return bedroomsnumber;
    }

    public int getBathroomsnumber() {
        return bathroomsnumber;
    }

    public int getEnterancedoornumber() {
        return enterancedoornumber;
    }
    public int getId(){
        return 	this.id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setApartmentnumber(int apartmentnumber) {
        this.apartmentnumber = apartmentnumber;
    }

    public void setBedroomsnumber(int bedroomsnumber) {
        this.bedroomsnumber = bedroomsnumber;
    }

    public void setFloornumber(int floornumber) {
        this.floornumber = floornumber;
    }

    public void setBathroomsnumber(int bathroomsnumber) {
        this.bathroomsnumber = bathroomsnumber;
    }

    public void setEnterancedoornumber(int enterancedoornumber) {
        this.enterancedoornumber = enterancedoornumber;
    }

    public void setId(int identifier){
        this.id=identifier;
    }
    private static final String Table_apartment = "apartement";
    //culumns
    private static final String KEY_ID = "id";
    private static final String KEY_address = "address";
    private static final String KEY_floornumber = "floornumber";
    private static final String KEY_apartementnumber = "apartementnumber";
    private static final String KEY_bedroomsnumber = "bedroomsnumber";
    private static final String KEY_bathroomnumbers = "bathroomnumbers";
    private static final String KEY_eneterencedoornumber = "eneterencedoornumber";

    private static final String[] COLUMNS = {KEY_ID,KEY_address,KEY_floornumber,KEY_apartementnumber,KEY_bedroomsnumber,KEY_bathroomnumbers,KEY_eneterencedoornumber};

    public void addApartment(ApartementDAO apartment){
        // 1. get reference to writable DB
        SQLiteDatabase db =db_handler.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_address, apartment.getAddress());
        values.put(KEY_apartementnumber,apartment.getApartmentnumber());
        values.put(KEY_bathroomnumbers, apartment.getBathroomsnumber());
        values.put(KEY_bedroomsnumber, apartment.getBedroomsnumber());
        values.put(KEY_eneterencedoornumber, apartment.getEnterancedoornumber());
        values.put(KEY_floornumber, apartment.getFloornumber());

        // 3. insert
        db.insert(Table_apartment, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public ApartementDAO getapartment(int id, Context context) {
        // 1. get reference to readable DB
        SQLiteDatabase db = db_handler.getReadableDatabase();
        // 2. build query
        Cursor cursor =
                db.query(Table_apartment, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        ApartementDAO apartment = null;
        // 3. if we got results get the first one
        if (cursor.moveToFirst()) {
            // 4. build book object
            apartment = new ApartementDAO(context);
            apartment.setAddress(cursor.getString(0));
            apartment.setApartmentnumber(Integer.parseInt(cursor.getString(1)));
            apartment.setFloornumber(Integer.parseInt(cursor.getString(2)));
            apartment.setBedroomsnumber(Integer.parseInt(cursor.getString(3)));
            apartment.setBathroomsnumber(Integer.parseInt(cursor.getString(4)));
            apartment.setEnterancedoornumber(Integer.parseInt(cursor.getString(5)));
        }
        // 5. return book
        return apartment;
    }
    // Get All Books
    public ArrayList<ApartementDAO> getAllApartments(Context context) {
        ArrayList<ApartementDAO> apartments = new ArrayList<ApartementDAO>();

        // 1. build the query
        String query = "SELECT  * FROM " + Table_apartment;
        // 2. get reference to readable DB
        SQLiteDatabase db = db_handler.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ApartementDAO apartment = null;
        if (cursor.moveToFirst()) {
            do {
                apartment = new ApartementDAO(context);
                apartment.setAddress(cursor.getString(0));
                apartment.setApartmentnumber(Integer.parseInt(cursor.getString(1)));
                apartment.setFloornumber(Integer.parseInt(cursor.getString(2)));
                apartment.setBedroomsnumber(Integer.parseInt(cursor.getString(3)));
                apartment.setBathroomsnumber(Integer.parseInt(cursor.getString(4)));
                apartment.setEnterancedoornumber(Integer.parseInt(cursor.getString(5)));
                // Add book to books
                apartments.add(apartment);
            } while (cursor.moveToNext());
        }
        // return books
        return apartments;
    }
    // Updating single book
    public int updateBook(ApartementDAO apartment) {

        // 1. get reference to writable DB
        SQLiteDatabase db = db_handler.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("address", apartment.getAddress());
        values.put("apartementnumber", apartment.getApartmentnumber());
        values.put("floornumber", apartment.getFloornumber());
        values.put("bedroomsnumber", apartment.getBedroomsnumber());
        values.put("bathroomnumbers", apartment.getBathroomsnumber());
        values.put("eneterencedoornumber", apartment.getEnterancedoornumber());

        // 3. updating row
        int i = db.update(Table_apartment, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(apartment.getId()) }); //selection args

        // 4. close
        db.close();
        return i;
    }
    // Deleting single book
    public void deleteapartment(ApartementDAO apartment) {

        // 1. get reference to writable DB
        SQLiteDatabase db = db_handler.getWritableDatabase();

        // 2. delete
        db.delete(Table_apartment,
                KEY_ID+" = ?",
                new String[] { String.valueOf(apartment.getId()) });

        // 3. close
        db.close();
    }
}
