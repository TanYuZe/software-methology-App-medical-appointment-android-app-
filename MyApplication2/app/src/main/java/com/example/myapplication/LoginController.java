package com.example.myapplication;

import android.content.Context;

public class LoginController {
    private static LoginController INSTANCE=null;
    private LoginController(){};
    public LoginEntity presentUser;

    public static LoginController getInstance(){
        if(INSTANCE==null){
            INSTANCE=new LoginController();
        }
        return(INSTANCE);
    }
    protected boolean validateLogin(String email,String password, Context context){
        LoginEntity user= new LoginEntity();
        if (user.Login(email, password, context)) {

            return true;
        }
        return false;
    }
}
