package com.example.myapplication;

public class Doctor extends BasicInfo
{

    public Doctor(String email, String password, String name, int phonenumber, String role)
    {
        super (email, password, name, phonenumber, role);
    }

    public String getEmail()
    {

        return email;
    }

    public String getPassword()
    {

        return password;
    }

    public String getName ()
    {

        return name;
    }

    public int getPhonenumber ()
    {

        return phonenumber;
    }
    public String getRole ()
    {

        return role;
    }
}