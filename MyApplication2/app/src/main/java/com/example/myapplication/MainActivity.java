package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Admin.Admin_Main;

public class MainActivity extends AppCompatActivity
{

    Button Login_btn, Register_btn;
    EditText editUserID;
    EditText editUserPwd;
    DatabaseManager dbManager;
    DatabaseHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUserID = (EditText) findViewById(R.id.User_Email);
        editUserPwd = (EditText) findViewById(R.id.User_Pw);
        Login_btn = (Button) findViewById(R.id.Login_Button);
        Register_btn = (Button) findViewById(R.id.Register_button);

        dbManager = new DatabaseManager(this);
        try{
            dbManager.open();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //click function for Register button
        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });



        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email = editUserID.getText().toString().trim();
                String user_password = editUserPwd.getText().toString().trim();
//                if (user_email.equals("") || user_password.equals(""))
//                {
//                    Toast.makeText(MainActivity.this, "A username and a password are required.", Toast.LENGTH_SHORT).show();
//                }
//                else if(onLogin(user_email, user_password)) {
//                    displaySuccess();
//                }
//
//                else
//                {
//                    displayInvalidCredentials();
//                }

                //to test admin functions
                if(user_email.equals("admin") & (user_password.equals("admin")))
                {
                    Intent intent = new Intent(MainActivity.this, Admin_Main.class);
                    startActivity(intent);
                }

            }

        });


    }



    boolean onLogin(String email, String password) {
        LoginController control= new LoginController();
        return control.validateLogin(email ,password,MainActivity.this);
    }


    void displayemptyerror() {
        Toast.makeText(MainActivity.this, "email or password cannot be empty" , Toast.LENGTH_SHORT).show();
    }


    void displaySuccess() {
        Toast.makeText(MainActivity.this, "Login Success " , Toast.LENGTH_SHORT).show();
    }
    void displayInvalidCredentials() {
        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
    }



}






//        if (User.getRole().equals("Admin"))
//        {
//            Intent intent = new Intent(MainActivity.this, Admin_Main.class);
//
//            startActivity(intent);
//        }
//        elif(User.getRole().equals("Patient"))
//        {
//
//            Intent intent = new Intent(MainActivity.this, Patient_Main.class);
//        }
//        elif(User.getRole().equals("Doctor"))
//        {
//
//            Intent intent = new Intent(MainActivity.this, Doctor_Main.class);
//        }
//        elif(User.getRole().equals("Pharmacist"))
//        {
//
//            Intent intent = new Intent(MainActivity.this, Pharmacist_Main.class);
//        }

//    dbManager = new DatabaseManager(this);
//        try{
//    dbManager.open();
//}
//        catch (Exception e)
//    {
//        e.printStackTrace();
//    }

/**
 if (DatabaseManager.checkUser(user_email, user_password)) {


 Intent accountsIntent = new Intent(this, UsersListActivity.class);
 accountsIntent.putExtra("EMAIL", user_email);
 emptyInputEditText();
 startActivity(accountsIntent);


 } else {


 }
 */