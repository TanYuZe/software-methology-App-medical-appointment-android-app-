package com.example.myapplication.Pharmacist;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Pharmacist_ViewProfile extends AppCompatActivity {
    TextView phar_name, phar_email, phar_password, phar_role, phar_phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_view_profile);
        phar_name = findViewById(R.id.phar_name);
        phar_email = findViewById(R.id.phar_email);
        phar_password = findViewById(R.id.phar_password);
        phar_role = findViewById(R.id.phar_role);
        phar_phoneno = findViewById(R.id.phar_phoneno);
    }
}