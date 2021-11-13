package com.example.myapplication.Admin;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Admin_ViewProfile extends AppCompatActivity {
    TextView admin_name, admin_email, admin_password, admin_role, admin_phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_profile);

        admin_name = findViewById(R.id.admin_name);
        admin_email = findViewById(R.id.admin_email);
        admin_password = findViewById(R.id.admin_password);
        admin_role = findViewById(R.id.admin_role);
        admin_phoneno = findViewById(R.id.admin_phoneno);

    }
}