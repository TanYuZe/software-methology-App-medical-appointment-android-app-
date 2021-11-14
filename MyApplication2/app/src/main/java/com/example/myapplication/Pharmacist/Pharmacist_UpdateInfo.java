package com.example.myapplication.Pharmacist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Pharmacist_UpdateInfo extends AppCompatActivity {
    EditText et_name, et_password, et_email, et_phoneno;
    Button update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_update_info);
        et_name = findViewById(R.id.et_name4);
        et_password = findViewById(R.id.et_password4);
        et_phoneno = findViewById(R.id.et_phoneno4);
        et_email = findViewById(R.id.et_email4);
        update_btn = findViewById(R.id.pharmacist_update_btn);
    }
}