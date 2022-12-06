package com.example.ft_hangout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper  extends SQLiteOpenHelper {

    public static final String CONTACT_TABLE = "CONTACT_TABLE";
    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String COLUMN_FIRST_NAME = "COLUMN_FIRST_NAME";
    public static final String COLUMN_NAME = "COLUMN_NAME";
    public static final String COLUMN_PHONE = "COLUMN_PHONE";
    public static final String COLUMN_ADDRESS = "COLUMN_ADDRESS";
    public static final String COLUMN_OTHER_INFORMATION = "COLUMN_OTHER_INFORMATION";

    public DbHelper(@Nullable Context context) {
        super(context, "contact_db", null, 1);
    }

    // First write the query creating the table then refactor variable with constant
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + CONTACT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " TEXT, " + COLUMN_NAME + " TEXT, " + COLUMN_PHONE + " INT, " + COLUMN_ADDRESS + " TEXT, " + COLUMN_OTHER_INFORMATION + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean addOne(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase(); // creating a variable where we can write inside a DB
        ContentValues cv = new ContentValues(); // like hashmap with key values

        // We don't put ID because it's autoincrement
        cv.put(COLUMN_FIRST_NAME, contact.getFirstName());
        cv.put(COLUMN_NAME, contact.getName());
        cv.put(COLUMN_PHONE, contact.getPhone());
        cv.put(COLUMN_ADDRESS, contact.getAddress());
        cv.put(COLUMN_OTHER_INFORMATION, contact.getOtherInformation());

        //writing inside the DB
        long insert = db.insert(CONTACT_TABLE, null, cv);
        db.close();
        if (insert < 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean modify(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(); // like hashmap with key values
        String where = COLUMN_ID + "=" + contact.getId();

        // We don't put ID because it's autoincrement
        cv.put(COLUMN_FIRST_NAME, contact.getFirstName());
        cv.put(COLUMN_NAME, contact.getName());
        cv.put(COLUMN_PHONE, contact.getPhone());
        cv.put(COLUMN_ADDRESS, contact.getAddress());
        cv.put(COLUMN_OTHER_INFORMATION, contact.getOtherInformation());

        long insert = db.update(CONTACT_TABLE, cv,COLUMN_ID + "=?",
                new String[] {Integer.toString(contact.getId())} );
        db.close();
        if (insert < 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteOne(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + CONTACT_TABLE + " WHERE " + COLUMN_ID + " = " + contact.getId();
        Cursor cursor = db.rawQuery(query, null); // get the result from the Database

        // If we have something everything is fine
        if (cursor.moveToFirst()){
            cursor.close();
            db.close();
            return true;
        }
        else{
            cursor.close();
            db.close();
            return false;
        }
    }

    public List<Contact> getEveryOne() {
        List<Contact> returnList = new ArrayList<>();

        //get data from database
        String queryString = "Select * FROM CONTACT_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor is the result of the query with row and columns
        Cursor cursor = db.rawQuery(queryString, null);
        // we check if there is an element in the cursor
        // then we loop through the query to create every Customer object and push it to the List
        if (cursor.moveToFirst()) { // if true we have an element in the cursor
            do {
                int contactID = cursor.getInt(0);
                String contactFirstName = cursor.getString(1);
                String contactName = cursor.getString(2);
                int contactPhone = cursor.getInt(3);
                String contactAddress = cursor.getString(4);
                String contactOtherInformation = cursor.getString(5);
                Contact newContact = new Contact(contactID, contactFirstName, contactName,
                                                contactPhone, contactAddress, contactOtherInformation);
                returnList.add(newContact);
            } while (cursor.moveToNext());
        }
        //close the access to DB
        cursor.close();
        db.close();
        return returnList;
    }

}
