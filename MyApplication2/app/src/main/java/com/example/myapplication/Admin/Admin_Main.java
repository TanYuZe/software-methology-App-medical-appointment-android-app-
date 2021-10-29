package com.example.myapplication.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Admin_Main extends AppCompatActivity {
    Button Add_User, Delete_User, View_User, Logout_btn;
    Intent adduser_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Add_User = findViewById(R.id.AddUser);
        Delete_User = findViewById(R.id.DeleteUser);
        View_User = findViewById(R.id.ViewUser);
        Logout_btn = findViewById(R.id.Admin_Logout);
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

        Logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Main.this);
                builder.setTitle("Confirmation PopUp!").
                        setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes, Log Me Out",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                builder.setNegativeButton("No, Continue",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });








    }





}