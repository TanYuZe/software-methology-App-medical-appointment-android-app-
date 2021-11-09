package com.example.myapplication.Admin;

import android.content.Context;

import com.example.myapplication.RegisterEntity;

public class AdminController {
    private static AdminController INSTANCE = null;
    private AdminController() {
    };

    public static AdminController getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AdminController();
        }
        return (INSTANCE);
    }

    public void AddUser(String email, String password, String name, int contactNum, String role, Context context )
    {
        RegisterEntity registerEntity = new RegisterEntity(context);
        registerEntity.addUser(email, password, name, contactNum, role, context);
    }




}
