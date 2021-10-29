package com.example.myapplication.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DatabaseManager;
import com.example.myapplication.R;

public class Admin_DeleteUser extends AppCompatActivity {
    EditText user_email;
    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_user);
        user_email = (EditText) findViewById(R.id.Email_input);

    }

    //delete user base on email
    public void Delete(View v)
    {
        String email = user_email.getText().toString().trim();
        db = new DatabaseManager(this);
        db.DeleteUser(email);

    }
}