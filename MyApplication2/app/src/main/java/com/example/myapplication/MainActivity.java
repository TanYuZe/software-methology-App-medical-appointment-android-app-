package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    EditText User_Email = (EditText) findViewById(R.id.User_Email);
    EditText User_Pw = (EditText) findViewById(R.id.User_Pw);
    String user_email = User_Email.getText().toString();
    String user_password = User_Pw.getText().toString();

    public void SignInFunction(View view)
    {

        Log.i( "Info", "button pressed");



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
