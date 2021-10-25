package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "CSCI314.db";
    static final int DATABASE_VERSION = 1;

    static String DATABASE_TABLE = "Users";
    static String USER_ID = "_ID";
    static String USER_FULLNAME = "fullName";
    static String USER_EMAIL = "email";
    static String USER_PASSWORD = "password";
    static String USER_CONTACT = "contactNum";
    static String USER_ROLE = "role";

    private static final String CREATE_DB_QUERY = "CREATE TABLE "
            + DATABASE_TABLE + " ( "
            + USER_ROLE + " TEXT NOT NULL, "
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_FULLNAME + " TEXT NOT NULL, "
            + USER_EMAIL + " TEXT NOT NULL, "
            + USER_PASSWORD + " TEXT NOT NULL,"
            + USER_CONTACT + " );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }
}
