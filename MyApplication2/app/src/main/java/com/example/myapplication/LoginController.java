package com.example.myapplication;

import android.content.Context;

public class LoginController {

    private static LoginController INSTANCE = null;

    LoginController()
    {

    };


    public static LoginController getINSTANCE()
    {
        if (INSTANCE == null) {
            INSTANCE = new LoginController();
        }
        return (INSTANCE);
    }



    protected void validateLogin(String email,String password, Context context){
        LoginEntity user= new LoginEntity();
        user.Login(email, password, context);


    }
}
