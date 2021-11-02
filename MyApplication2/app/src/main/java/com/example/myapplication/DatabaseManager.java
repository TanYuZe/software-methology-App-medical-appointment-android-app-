package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLDataException;

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


    public void DeleteUser(String email)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "DELETE FROM DATABASE_TABLE WHERE email = '" + email;
        Cursor cursor = db.rawQuery(query, null);
    }

    public void doTheFetch()
    {
        Cursor cursor = fetch();
        if(cursor.moveToFirst())
        {
            do
            {
                String ID = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_ID));
                String FullName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_FULLNAME));

                Log.i("DATABASE_Tag" , "I have inserted ID: " + ID + ", Name: " + FullName);
            }while(cursor.moveToNext());
        }
        cursor.close();

    }


    //for resgiter class to check whether email alr exit anot
    public boolean valEmailExist(String email)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM DATABASE_TABLE WHERE email = '" + email;
        Cursor cursor = db.rawQuery(query, null);
        int CursorCount = cursor.getCount();
        if (CursorCount > 0)
        {
            return false;
        }
        return true;
    }




}


