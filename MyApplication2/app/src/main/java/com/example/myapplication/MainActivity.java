package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{



    public void SignInFunction(View v)
    {
        EditText User_Email = (EditText) findViewById(R.id.User_Email);
        EditText User_Pw = (EditText) findViewById(R.id.User_Pw);
        String user_email = User_Email.getText().toString();
        String user_password = User_Pw.getText().toString();
        if (user_email == "" || user_password == "")
        {
            Toast.makeText(this, "A username and a password are required." ,Toast.LENGTH_SHORT).show();
        }
        Log.i("my activity", user_email);
        Log.i("my activity", user_password);



    }

    public void SignUpFunction(View v)
    {
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
}
