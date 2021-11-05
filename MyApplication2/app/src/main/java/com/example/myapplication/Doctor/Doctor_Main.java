package com.example.myapplication.Doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Doctor_Main extends AppCompatActivity {
    Button Assign_med, doctor_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        Assign_med = findViewById(R.id.AssignMed_btn);
        doctor_logout = findViewById(R.id.Doctor_Logout);


        Assign_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor_Main.this, Doctor_AssignMedicine.class);
                startActivity(intent);
            }
        });

        doctor_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Doctor_Main.this);
                builder.setTitle("Confirmation PopUp!").
                        setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes, Log Me Out",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                builder.setNegativeButton("No, Continue",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });


    }
}