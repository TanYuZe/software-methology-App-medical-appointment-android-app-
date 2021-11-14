package com.example.myapplication.Admin;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Admin_UpdateInfo extends AppCompatActivity {
    EditText et_name, et_password, et_email, et_role, et_phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_info);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        et_phoneno = findViewById(R.id.et_phoneno);
        et_email = findViewById(R.id.et_email);
        et_role = findViewById(R.id.et_role);
    }
}