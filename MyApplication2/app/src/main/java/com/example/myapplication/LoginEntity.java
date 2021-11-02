package com.example.myapplication;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoginEntity {
    DatabaseManager dbManager;
    private String ID;
    private String role;
    private String name;
    private String email;
    private String password;
    private String contact;
    DatabaseHelper DBHelper;


    public boolean Login(String email, String password, Context context)
    {

        dbManager = new DatabaseManager(context);
        try{
            dbManager.open();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

       return validateUser(email, password);
    }

    public boolean validateUser(String email, String password) {
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        String query = "SELECT * FROM DATABASE_TABLE WHERE email = '" + email + "' and password ='"+ password+"'";
        Cursor cursor = db.rawQuery(query, null);
        int CursorCount = cursor.getCount();
        if (CursorCount > 0)
        {
            return true;
        }
        return false;
    }
}
