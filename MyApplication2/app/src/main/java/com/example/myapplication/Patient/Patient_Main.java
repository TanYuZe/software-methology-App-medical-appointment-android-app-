package com.example.myapplication.Patient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Patient_Main extends AppCompatActivity {
    
    Button Logout_btn, View_profilebtn, View_presbtn, View_QR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);
        Logout_btn = findViewById(R.id.admin_Logout);
        View_profilebtn = findViewById(R.id.viewProfile);
        View_presbtn = findViewById(R.id.ViewPresc);
        View_QR = findViewById(R.id.viewQR);
        Intent intent1 = new Intent(Patient_Main.this, Patient_ViewProfile.class);
        Intent intent2 = new Intent(Patient_Main.this, Patient_ViewPrescription.class);
        Intent intent3 = new Intent(Patient_Main.this, Patient_QRcode.class);



        View_profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent1);

            }
        });

        View_presbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent2);

            }
        });

        View_QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent3);

            }
        });



        Logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Patient_Main.this);
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