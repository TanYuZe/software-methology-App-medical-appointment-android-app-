package com.example.myapplication.Doctor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class DoctorUpdatePresc extends AppCompatActivity {
    ListView listview;
    Button update_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_update_presc);
        listview = findViewById(R.id.listview2);
        update_btn = findViewById(R.id.update_btn2);


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }
}