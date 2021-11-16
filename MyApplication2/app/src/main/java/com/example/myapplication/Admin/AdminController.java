package com.example.myapplication.Admin;

import android.content.Context;

import com.example.myapplication.BasicInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminController extends BasicInfo
{

    private static AdminController INSTANCE = null;

    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;

    private AdminController() {
    };

    public static AdminController getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AdminController();
        }
        return (INSTANCE);
    }


//    public BasicInfo validateGetUserInfo(String email, Context context)
//    {
//        AdminEntity AdminEntity = new AdminEntity(context);
//        AdminEntity.GetUserInfo(email);
//        return AdminEntity.GetUserInfo(email);
//    }

    public void validateUpdateName(String name, Context context)
    {
        AdminEntity AdminEntity = new AdminEntity(context);
        AdminEntity.updateName(name);
    }
    public void validateUpdatePassword(String password, Context context)
    {
        AdminEntity AdminEntity = new AdminEntity(context);
        AdminEntity.updatePassword(password);
    }
    public void validateUpdateNumber(int number, Context context)
    {
        AdminEntity AdminEntity = new AdminEntity(context);
        AdminEntity.updateNumber(number);
    }





}
