package com.example.myapplication.Patient;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;

public class Patient_ViewPrescription extends AppCompatActivity {
    ListView listview_presc;
    ArrayAdapter<String> adapter;
    ArrayList<String> presclist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_prescription);
        listview_presc = findViewById(R.id.ListView_presc);
        presclist = new ArrayList<>();

        for(int i = 1; i < 50; i++)
        {
            presclist.add("medicine:\n med1\n med2\n med3\n ");
        }



        adapter = new ArrayAdapter<String>(Patient_ViewPrescription.this, android.R.layout.simple_list_item_1, presclist);
        listview_presc.setAdapter(adapter);

    }
}