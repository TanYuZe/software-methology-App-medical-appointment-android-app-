package com.example.myapplication;

import android.content.Context;

public class LoginController {

    protected boolean validateLogin(String email,String password, Context context){
        LoginEntity user= new LoginEntity();
        if (user.Login(email, password, context)) {

            return true;
        }
        return false;
    }
}
