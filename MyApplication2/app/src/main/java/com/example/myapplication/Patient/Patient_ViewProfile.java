package com.example.myapplication.Patient;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Patient_ViewProfile extends AppCompatActivity {
    TextView pat_name, pat_email, pat_password, pat_role, pat_phoneno;
    Button btn_update, btn_changepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_profile);
        pat_name = findViewById(R.id.pat_name);
        pat_email = findViewById(R.id.pat_email);
        pat_password = findViewById(R.id.pat_password);
        pat_role = findViewById(R.id.pat_role);
        pat_phoneno = findViewById(R.id.pat_phoneno);

        btn_update = findViewById(R.id.btn_update);
        btn_changepass = findViewById(R.id.btn_changepass);


    }
}