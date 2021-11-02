package com.example.myapplication;

import android.content.Context;

import com.example.myapplication.Admin.Admin;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterController {
    private static RegisterController INSTANCE = null;

    RegisterController()
    {

    };


    public static RegisterController getINSTANCE()
    {
        if (INSTANCE == null) {
            INSTANCE = new RegisterController();
        }
        return (INSTANCE);
    }


    public void validateSuccess(String email, String password, String name, int contactNum, String role, Context context )
    {
        RegisterEntity registerEntity = new RegisterEntity(context);
        registerEntity.addUser(email, password, name, contactNum, role, context);
    }
}
