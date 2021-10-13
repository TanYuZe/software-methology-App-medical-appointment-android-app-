package com.example.myapplication;


class Patient extends BasicInfo
{
    public Patient(String name, String email, String password, int phonenumber, String role)
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
