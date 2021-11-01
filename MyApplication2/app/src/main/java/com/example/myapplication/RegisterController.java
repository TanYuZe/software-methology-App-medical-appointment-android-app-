package com.example.myapplication;

public class RegisterController {

    DatabaseManager dbmanager;
    boolean validateRegistration(String name, String email, String password, int phonenum, String role)
    {
        if(dbmanager.valEmailExist(email))
        {
            return false;
        }
        else
        {

            dbmanager.doTheFetch();
            dbmanager.insert(name, email, password, phonenum, role);
            return true;
        }
    }
}
