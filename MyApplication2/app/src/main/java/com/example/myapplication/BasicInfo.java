package com.example.myapplication;

public class BasicInfo
{
    public final String email;
    public final String password;
    public final String name;
    public final int phonenumber;
    public final String role;


//constructor
    public BasicInfo(String name, String email, String password, int phonenumber, String role)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phonenumber = phonenumber;
        this.role = role;
    }

    public BasicInfo(BasicInfo info)
    {
        this(info.email ,info.password, info.name ,info.phonenumber, info.role);
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

    //This is a comment
}






