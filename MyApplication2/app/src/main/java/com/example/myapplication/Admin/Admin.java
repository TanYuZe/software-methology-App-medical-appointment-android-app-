package com.example.myapplication.Admin;

import com.example.myapplication.BasicInfo;

public class Admin extends BasicInfo
{

    public Admin(String id, String name, String email, String password, int phonenumber, String role)
    {
        super (id, name, email, password, phonenumber, role);
    }

    public String getEmail()
    {

        return super.getEmail();
    }

    public String getPassword()
    {

        return super.getPassword();
    }

    public String getName ()
    {

        return super.getName();
    }

    public int getPhonenumber ()
    {

        return super.getPhonenumber();
    }
    public String getRole ()
    {

        return super.getRole();
    }
}
