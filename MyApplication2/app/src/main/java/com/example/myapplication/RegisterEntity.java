package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.Admin.Admin;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterEntity {
    private String ID;
    private String role;
    private String name;
    private String email;
    private String password;
    private String contact;
    DatabaseHelper DBHelper;
    DatabaseManager dbManager;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;

    public RegisterEntity(Context context)
    {
        DBHelper = new DatabaseHelper(context);
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void addUser(String email, String password, String name, int contactNum, String role, Context context )
    {
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");

        dbManager = new DatabaseManager(context);
        try{
            dbManager.open();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        SQLiteDatabase db = DBHelper.getWritableDatabase();

        String query = "SELECT _ID FROM Users WHERE fullName = '" + name
                + "' AND email = '" + email
                + "' AND password = '" + password
                + "' AND role = '" + role
                + "'";


        Cursor cursor;

        int idResult = 0;String result = "";

        switch (role)
        {
            case "Patient":
                dbManager.insert(name, email, password, contactNum, role);

                cursor = db.rawQuery(query, null);
                cursor.moveToFirst();
                result = cursor.getString(0);
                idResult = Integer.parseInt(result);

                Patient patient = new Patient(idResult, name, email, password, contactNum, role);
                refrence_.child(String.valueOf(idResult)).setValue(patient);
                break;

            case "Doctor":
                dbManager.insert(name, email, password, contactNum, role);

                cursor = db.rawQuery(query, null);
                cursor.moveToFirst();
                result = cursor.getString(0);
                idResult = Integer.parseInt(result);

                Doctor doctor = new Doctor(idResult, name, email, password, contactNum, role);
                refrence_.child(String.valueOf(idResult)).setValue(doctor);
                break;

            case "Phamarcist":
                dbManager.insert(name, email, password, contactNum, role);

                cursor = db.rawQuery(query, null);
                cursor.moveToFirst();
                result = cursor.getString(0);
                idResult = Integer.parseInt(result);

                Phamarcist pharmacist = new Phamarcist(idResult, name, email, password, contactNum, role);
                refrence_.child(String.valueOf(idResult)).setValue(pharmacist);
                break;

            case "Admin":
                dbManager.insert(name, email, password, contactNum, role);

                cursor = db.rawQuery(query, null);
                cursor.moveToFirst();
                result = cursor.getString(0);
                idResult = Integer.parseInt(result);

                Admin admin = new Admin(idResult, name, email, password, contactNum, role);
                refrence_.child(String.valueOf(idResult)).setValue(admin);
                break;
        }
        //BasicInfo basicInfo = new BasicInfo();
        //refrence_.setValue(basicInfo);
    }
}
