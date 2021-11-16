package com.example.myapplication.Patient;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ListViewAdapter_PatientViewPres;
import com.example.myapplication.Prescribed;
import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Patient_ViewPrescription extends AppCompatActivity{
    ListView listview_presc;
    ArrayAdapter<String> adapter;
    ArrayList<String> presclist;
    ListViewAdapter_PatientViewPres theListViewAdaptor;
    ArrayList<Prescribed> prescribedArrayList;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_prescription);
        listview_presc = findViewById(R.id.ListView_Patient);
        //presclist = new ArrayList<>();
        prescribedArrayList = new ArrayList<Prescribed>();

        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference().child("Prescribed");

        refrence_.orderByChild("_ID");
        theListViewAdaptor = new ListViewAdapter_PatientViewPres(Patient_ViewPrescription.this , prescribedArrayList);
        listview_presc.setAdapter(theListViewAdaptor);
        PatientController patientController = PatientController.getINSTANCE();

        patientController.validateViewPrescription(prescribedArrayList, getApplicationContext());


    }
}