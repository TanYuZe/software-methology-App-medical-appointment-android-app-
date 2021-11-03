package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Patient_ViewProfile extends AppCompatActivity {
    TextView user_name, user_email, user_password, user_role, user_phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_profile);
        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        user_role = findViewById(R.id.user_roles);
        user_phoneno = findViewById(R.id.user_phoneno);
    }
}