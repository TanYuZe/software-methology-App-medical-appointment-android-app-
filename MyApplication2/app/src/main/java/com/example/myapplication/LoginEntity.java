package com.example.myapplication;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoginEntity {
    DatabaseManager dbManager;
    private String ID;
    String role;
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
        LoginEntity user = validateUser(email,password);
        LoginController.getInstance().presentUser= user;
        return user != null;
    }
    LoginEntity validateUser(String email, String password) {
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        String query = "SELECT * FROM DATABASE_TABLE WHERE email = '" + email + "' and password ='"+ password+"'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null && cursor.moveToFirst()) {
            LoginEntity user = new LoginEntity();
            user.ID = cursor.getString(0);
            user.role = cursor.getString(1);
            user.name = cursor.getString(2);
            user.email = cursor.getString(3);
            user.password = cursor.getString(4);
            user.contact = cursor.getString(5);

            cursor.close();
            return user;
        }
        else {
            return null;
        }
    }
//    public boolean validateUser(String email, String password) {
//        SQLiteDatabase db = DBHelper.getWritableDatabase();
//        String query = "SELECT * FROM DATABASE_TABLE WHERE email = '" + email + "' and password ='"+ password+"'";
//        Cursor cursor = db.rawQuery(query, null);
//        int CursorCount = cursor.getCount();
//        if (CursorCount > 0)
//        {
//            return true;
//        }
//        return false;
//    }
}
