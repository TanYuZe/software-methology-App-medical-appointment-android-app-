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

    public enum user_role {
        Patient,
        Doctor,
        Admin,
        Pharmacist,
    }

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

        if(validateUser(email, password))
        {
            return true;
        }
        else
        {
            return false;
        }



    }

    public boolean validateUser(String email, String password) {
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        String query = "SELECT * FROM DATABASE_TABLE WHERE USER_EMAIL = '" + email + "' and USER_PASSWORD ='"+ password+"'";
        Cursor cursor = db.rawQuery(query, null);
        int CursorCount = cursor.getCount();
        if (CursorCount > 0)
        {
            return true;
        }
        return false;
    }
}
