package com.example.myapplication;

public class BasicInfo
{
    public String email;
    public String password;
    public String name;
    public int phonenumber;
    public String role;

    public BasicInfo()
    {

    }
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



//    public int getId()
//    {
//
//        return id;
//    }
//
//    public void setId(int id)
//    {
//        this.id = id;
//    }


    public String getEmail()
    {

        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {

        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName ()
    {

        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getPhonenumber ()
    {

        return phonenumber;
    }
    public void setPhonenumber(int phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getRole ()
    {

        return role;
    }
    public void setRole(String role)
    {
        this.role = role;
    }




}






