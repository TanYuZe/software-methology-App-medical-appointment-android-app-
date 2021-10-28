package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
{


        EditText editUserID;
        EditText editUserPwd;
        DatabaseManager dbManager;

//    private void verifyUser()
//    {
//        if (DatabaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
//                , textInputEditTextPassword.getText().toString().trim())) {
//            Intent accountsIntent = new Intent(activity, UsersListActivity.class);
//            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
//            emptyInputEditText();
//            startActivity(accountsIntent);
//        } else {
//            // Snack Bar to show success message that record is wrong
//            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
//        }
//    }

    public void SignInFunction(View v) {
        EditText User_Email = (EditText) findViewById(R.id.User_Email);
        EditText User_Pw = (EditText) findViewById(R.id.User_Pw);
        String user_email = User_Email.getText().toString().trim();
        String user_password = User_Pw.getText().toString().trim();
        if (user_email.isEmpty() || user_password.isEmpty())
        {
            Toast.makeText(this, "A username and a password are required.", Toast.LENGTH_SHORT).show();
        }

        /**
        if (DatabaseManager.checkUser(user_email, user_password)) {


            Intent accountsIntent = new Intent(this, UsersListActivity.class);
            accountsIntent.putExtra("EMAIL", user_email);
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {


        }
         */
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

    public void SignUpFunction(View v)
    {
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUserID = (EditText) findViewById(R.id.User_Email);
        editUserPwd = (EditText) findViewById(R.id.User_Pw);
        dbManager = new DatabaseManager(this);
        try{
            dbManager.open();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
