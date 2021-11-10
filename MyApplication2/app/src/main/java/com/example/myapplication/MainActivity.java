package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Admin.Admin_Main;
import com.example.myapplication.Doctor.Doctor_Main;
import com.example.myapplication.Patient.Patient_Main;
import com.example.myapplication.Pharmacist.Pharmacist_Main;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{

    Button Login_btn, Register_btn;
    EditText editUserID;
    EditText editUserPwd;
    DatabaseManager dbManager;
    DatabaseHelper dbhelper;

    FirebaseDatabase rootNode;
    DatabaseReference refrence;
    String user_email;

    FirebaseAuth mAuth;


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
                user_email = editUserID.getText().toString().trim();
                String user_password = editUserPwd.getText().toString().trim();

                onLogin(user_email, user_password);

                //to test functions
                if(user_email.equals("admin") & (user_password.equals("admin")))
                {
                    Intent intent = new Intent(MainActivity.this, Admin_Main.class);
                    startActivity(intent);
                }
                else if(user_email.equals("patient") & (user_password.equals("patient")))
                {
                    Intent intent = new Intent(MainActivity.this, Patient_Main.class);
                    startActivity(intent);
                }
                else if(user_email.equals("doctor") & (user_password.equals("doctor")))
                {
                    Intent intent = new Intent(MainActivity.this, Doctor_Main.class);
                    startActivity(intent);
                }
                else if(user_email.equals("phar") & (user_password.equals("phar")))
                {
                    Intent intent = new Intent(MainActivity.this, Pharmacist_Main.class);
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