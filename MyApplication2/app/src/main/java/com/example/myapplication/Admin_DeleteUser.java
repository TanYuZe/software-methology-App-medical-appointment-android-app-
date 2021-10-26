package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_DeleteUser extends AppCompatActivity {
    EditText user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_user);
        user_email = (EditText) findViewById(R.id.Email_input);

    }

    public void Delete(View v)
    {
        String email = user_email.getText().toString().trim();
//        try
//        {
//            String d="DELETE FROM  WHERE ="+ email;
//            db.execSQL(d);
//        }
//        catch(Exception e)
//        {
//            System.out.print("Error..................");
//        }

    }
}