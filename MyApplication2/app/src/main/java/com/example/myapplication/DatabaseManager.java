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

}


