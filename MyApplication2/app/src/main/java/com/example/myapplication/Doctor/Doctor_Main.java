package com.example.myapplication.Doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class Doctor_Main extends AppCompatActivity {

    Button Assign_med, doctor_logout, doctorViewProf, doctor_viewPresc;

    Button view_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        Assign_med = findViewById(R.id.AssignMed_btn);
        doctor_logout = findViewById(R.id.Doctor_Logout);
        doctor_viewPresc = findViewById(R.id.Viewpresc_btn);

        doctorViewProf = findViewById(R.id.button2);


        doctor_viewPresc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor_Main.this, Doctor_ViewUpdatePresc.class);
                startActivity(intent);
            }
        });



        doctorViewProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Doctor_Main.this, Doctor_ViewProfile.class);
                startActivity(intent);
            }
        });

//        view_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });


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
                                FirebaseAuth.getInstance().signOut();
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

