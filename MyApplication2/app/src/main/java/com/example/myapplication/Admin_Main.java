package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_Main extends AppCompatActivity {
    Button Add_User, Delete_User, View_User;
    Intent adduser_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Add_User = findViewById(R.id.AddUser);
        Delete_User = findViewById(R.id.DeleteUser);
        View_User = findViewById(R.id.ViewUser);
        Intent intent_Adduser = new Intent(Admin_Main.this, Admin_AddUser.class);
        Intent intent_Deleteuser = new Intent(Admin_Main.this, Admin_DeleteUser.class);
        Intent intent_Viewuser = new Intent(Admin_Main.this, Admin_ViewUser.class);




        Add_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent_Adduser);

            }
        });

        Delete_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent_Deleteuser);

            }
        });

        View_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent_Viewuser);

            }
        });










    }





}