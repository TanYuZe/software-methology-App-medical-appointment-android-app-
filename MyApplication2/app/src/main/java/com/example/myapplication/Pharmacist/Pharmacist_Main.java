package com.example.myapplication.Pharmacist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class Pharmacist_Main extends AppCompatActivity {
    Button PharmaLogout, PharmacistProfile, RetrievePresc, prescdata_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_main);

        PharmaLogout = findViewById(R.id.Pharmacist_Logout);
        PharmacistProfile = findViewById(R.id.PharmacistProfile);
        RetrievePresc = findViewById(R.id.RetrievePresc);
        prescdata_btn = findViewById(R.id.prescdata_btn);
        Intent intent2 = new Intent(Pharmacist_Main.this, Pharmacist_ViewUserPresc.class);
        Intent intent1 = new Intent(Pharmacist_Main.this, Pharmacist_PrescData.class);
        Intent profIntent = new Intent(Pharmacist_Main.this, Pharmacist_ViewProfile.class);


        PharmacistProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profIntent);
            }
        });


        prescdata_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);

            }
        });





        RetrievePresc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });






        PharmaLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Pharmacist_Main.this);
                builder.setTitle("Confirmation PopUp!").
                        setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes, Log Me Out",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
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