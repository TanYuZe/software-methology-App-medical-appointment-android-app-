package com.example.myapplication.Doctor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Doctor_ViewProfile extends AppCompatActivity {
    TextView doc_name, doc_email, doc_password, doc_role, doc_phoneno;
    Button btn_update, btn_changepass, btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_profile);
        doc_name = findViewById(R.id.doc_name);
        doc_email = findViewById(R.id.doc_email);
        doc_password = findViewById(R.id.doc_password);
        doc_role = findViewById(R.id.doc_role);
        doc_phoneno = findViewById(R.id.doc_phoneno);

        btn_update = findViewById(R.id.btn_update);
        btn_changepass = findViewById(R.id.btn_changepass);
        btn_home = findViewById(R.id.btn_homepage);
    }
}