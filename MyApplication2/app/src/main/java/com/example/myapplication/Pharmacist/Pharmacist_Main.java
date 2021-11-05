package com.example.myapplication.Pharmacist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Pharmacist_Main extends AppCompatActivity {
    Button PharmaLogout, PharmacistProfile, RetrievePresc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_main);

        PharmaLogout = findViewById(R.id.Pharmacist_Logout);
        PharmacistProfile = findViewById(R.id.PharmacistProfile);
        RetrievePresc = findViewById(R.id.RetrievePresc);
        Intent intent2 = new Intent(Pharmacist_Main.this, Pharmacist_ViewUserPresc.class);











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