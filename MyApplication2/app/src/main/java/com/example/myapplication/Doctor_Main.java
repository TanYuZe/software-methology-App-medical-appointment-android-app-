package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Doctor_Main extends AppCompatActivity {
    Button Assign_med;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        Assign_med = findViewById(R.id.AssignMed_btn);


        Assign_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor_Main.this, Doctor_AssignMedicine.class);
                startActivity(intent);
            }
        });


    }
}