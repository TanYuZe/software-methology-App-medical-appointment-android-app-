package com.example.myapplication.Doctor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Doctor_UpdateInfo extends AppCompatActivity {
    EditText et_name, et_password, et_email, et_phoneno;
    Button update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_update_info);
        et_name = findViewById(R.id.et_name2);
        et_password = findViewById(R.id.et_password2);
        et_phoneno = findViewById(R.id.et_phoneno2);
        et_email = findViewById(R.id.et_email2);
        update_btn = findViewById(R.id.doctor_update_btn2);


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}