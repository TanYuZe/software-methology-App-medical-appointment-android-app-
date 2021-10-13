package com.example.myapplication;

public class Phamarcist extends BasicInfo
{
    public Phamarcist(String name, String email, String password, int phonenumber, String role)
    {
        super (email, password, name, phonenumber, role);
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
