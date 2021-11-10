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



    protected boolean validateLogin(String email,String password, Context context){
        LoginEntity user= new LoginEntity();
        if(user.Login(email, password, context))
        {
            return true;
        }
        else
        {
            return false;
        }


    }
}
