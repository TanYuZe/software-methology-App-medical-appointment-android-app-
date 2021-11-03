package com.example.myapplication;

import android.content.Context;

public class LoginController {

    protected void validateLogin(String email,String password, Context context){
        LoginEntity user= new LoginEntity();
        user.Login(email, password, context);

    }
}
