package com.example.myapplication.Pharmacist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ListViewAdapter_Phar_precdata;
import com.example.myapplication.Prescription;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pharmacist_PrescData extends AppCompatActivity implements ListViewAdapter_Phar_precdata.CheckboxCheckListner{
    EditText prescdata;
    EditText drugDosage;
    Button add_btn;
    ListView presc_list;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    ListViewAdapter_Phar_precdata adapter;
    ArrayList<String>  medlist;
    ArrayList<Prescription> prescriptionArrayList;
    ArrayList<Prescription> SelectedList;
    Long maxID;
    ArrayList<String> deletelist;
    Prescription newPrescription1;
    PharmacistController pharmacistController;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_presc_data);
        prescdata = (EditText) findViewById(R.id.Presc_name);
        drugDosage = (EditText) findViewById(R.id.drugDosage1);
        add_btn = findViewById(R.id.add_btn);
        presc_list = findViewById(R.id.presc_list);


        SelectedList = new ArrayList<Prescription>();
        newPrescription1 = new Prescription();
        pharmacistController = PharmacistController.getINSTANCE();


        medlist = new ArrayList<String>();
        prescriptionArrayList = new ArrayList<Prescription>();

        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference().child("Prescription");

        adapter = new ListViewAdapter_Phar_precdata(Pharmacist_PrescData.this , prescriptionArrayList);
        presc_list.setAdapter(adapter);
        adapter.setCheckedListner((ListViewAdapter_Phar_precdata.CheckboxCheckListner) this);

        //pharmacistController.validateFetchPrescTable(maxID, medlist, prescriptionArrayList, getApplicationContext());


        refrence_.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Prescription prescription = snapshot1.getValue(Prescription.class);
                    medlist.add(prescription.getDrugPrescribed());
                    prescriptionArrayList.add(prescription);
                    maxID = snapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pharmacistController.validateAddPrescription(maxID, prescdata.getText().toString(), drugDosage.getText().toString(),prescriptionArrayList, medlist, getApplicationContext());
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void getCheckBoxCheckedListner(int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Pharmacist_PrescData.this);
        builder.setTitle("Confirm Prescription!").setMessage("Do you want to delete " + prescriptionArrayList.get(position).getDrugPrescribed());
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        pharmacistController.validateDeletePrescription(prescriptionArrayList, position, getApplicationContext());
                        prescriptionArrayList.remove(prescriptionArrayList.get(position));
                        adapter.notifyDataSetChanged();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder.create();
        alert11.show();
    }


}