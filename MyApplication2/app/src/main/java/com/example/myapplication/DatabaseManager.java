package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx) {
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String FullName, String Email, String Password, int ContactNum, String Role) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_ROLE, Role);
        contentValues.put(DatabaseHelper.USER_FULLNAME, FullName);
        contentValues.put(DatabaseHelper.USER_EMAIL, Email);
        contentValues.put(DatabaseHelper.USER_PASSWORD, Password);
        contentValues.put(DatabaseHelper.USER_CONTACT, ContactNum);

        database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper.USER_ID,
                DatabaseHelper.USER_ROLE,
                DatabaseHelper.USER_FULLNAME,
                DatabaseHelper.USER_EMAIL,
                DatabaseHelper.USER_PASSWORD,
                DatabaseHelper.USER_CONTACT};
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void addUser(BasicInfo user) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.USER_ID, user.getId());
        values.put(DatabaseHelper.USER_ROLE, user.getRole());
        values.put(DatabaseHelper.USER_FULLNAME, user.getName());
        values.put(DatabaseHelper.USER_EMAIL, user.getEmail());
        values.put(DatabaseHelper.USER_PASSWORD, user.getPassword());
        values.put(DatabaseHelper.USER_CONTACT, user.getPhonenumber());

        // Inserting Row
        db.insert(DatabaseHelper.DATABASE_TABLE, null, values);
        db.close();
    }
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    /**
    public List<BasicInfo> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                DatabaseHelper.USER_ID,
                DatabaseHelper.USER_ROLE,
                DatabaseHelper.USER_FULLNAME,
                DatabaseHelper.USER_EMAIL,
                DatabaseHelper.USER_PASSWORD,
                DatabaseHelper.USER_CONTACT};



        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        // query the user table
        Cursor cursor = db.query(DatabaseHelper.DATABASE_TABLE, //Table to query
                columns,            //columns to return
                null,       //columns for the WHERE clause
                null,    //The values for the WHERE clause
                null,       //group the rows
                null,        //filter by row groups
                null); //The sort order
        // Traversing through all rows and adding to list
        List<BasicInfo> userList = new ArrayList<BasicInfo>();

        int numberOfColumns = cursor.getColumnCount();
        for(cursor.moveToFirst(); !cursor.moveToLast(); cursor.moveToNext())
        {
            BasicInfo user = new BasicInfo();


            userList.add(cursor.getcolumn);
        }

        cursor.close();
        db.close();
        // return user list
        return userList;
    }

     */

    public void updateUser(BasicInfo user) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.USER_ID, user.getId());
        values.put(DatabaseHelper.USER_ROLE, user.getRole());
        values.put(DatabaseHelper.USER_FULLNAME, user.getName());
        values.put(DatabaseHelper.USER_EMAIL, user.getEmail());
        values.put(DatabaseHelper.USER_PASSWORD, user.getPassword());
        values.put(DatabaseHelper.USER_CONTACT, user.getPhonenumber());

        // updating row
        db.update(DatabaseHelper.DATABASE_TABLE, values, DatabaseHelper.USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteUser(BasicInfo user) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        // delete user record by id
        db.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
    public static boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                DatabaseHelper.USER_ID
        };
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        // selection criteria
        String selection = DatabaseHelper.USER_EMAIL + " = ?" + "AND" + DatabaseHelper.USER_PASSWORD + " = ?";

        // selection argument
        String[] selectionArgs = {email, password};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
    /**
        Cursor cursor = db.query(DatabaseHelper.DATABASE_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
    */


}


