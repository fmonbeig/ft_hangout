package com.example.ft_hangout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//extend = inheritance
// First we create DatabaseHelper and put the onCreate and onUpgrade method
// we have to define the public constructor as we want
//

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE"; // Creation with the right click  refactor constant
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "customer_db", null, 1); //  super(context, "db_name", NULL, 1);
    }

    // Called the first time the db is accessed
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE  TABLE " + CUSTOMER_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";
        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean deleteOne(CustomerModel customerModel){
        //find customer model  in db . If find, delete it and ret true
        //if not find return false
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + customerModel.getId();

        Cursor cursor = db.rawQuery(query, null); // On passe la commande a la db

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

    //Interaction with DB getWritable getReadable
    public boolean addOne(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase(); // creating a variable where we can write inside a DB
        ContentValues cv = new ContentValues(); // like hashmap with key values

        // We don't put ID because it's autoincrement
        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActive());

        //writing inside the DB
        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert < 0) {
            return false;
        } else {
            return true;
        }
    }

    public List<CustomerModel> getEveryOne() {
        List<CustomerModel> returnList = new ArrayList<>();

        //get data from database

        String queryString = "Select * FROM CUSTOMER_TABLE";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null); //Cursor is the result of the query with row and columns

        // we will check if there is an element in the cursor
        // then we will loop through the query to create every Customer object and push it to the List
        if (cursor.moveToFirst()) { // if true we have an element in the cursor
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean customerActive = cursor.getInt(3) == 1 ? true : false; // Sql don't know what a boolean is so we have to translate it we a clever ternary operatio

                CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerAge, customerActive);
                returnList.add(newCustomer);
            } while (cursor.moveToNext());
        } else {
            //do nothing
        }
        //close the access to sqlite
        cursor.close();
        db.close();
        return returnList;
    }
}
