package com.example.myapplication.Pharmacist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Prescription;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Pharmacist_PrescData extends AppCompatActivity {
    EditText prescdata;
    EditText drugDosage;
    Button add_btn, delete_btn;
    ListView presc_list;
    String itemSelected;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    ArrayAdapter<String> adapter;
    ArrayList<String>  medlist;
    ArrayList<Prescription> prescriptionArrayList;
    Long maxID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_presc_data);
        prescdata = (EditText) findViewById(R.id.Presc_name);
        drugDosage = (EditText) findViewById(R.id.drugDosage1);
        add_btn = findViewById(R.id.add_btn);
        delete_btn = findViewById(R.id.delete_btn);
        presc_list = findViewById(R.id.Presc_List);



        medlist = new ArrayList<String>();
        prescriptionArrayList = new ArrayList<Prescription>();

        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference().child("Prescription");


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
                    adapter = new ArrayAdapter<String>(getApplicationContext() , android.R.layout.simple_list_item_multiple_choice, medlist);
                    presc_list.setAdapter(adapter);
                    maxID = snapshot.getChildrenCount();
                }
            }
//
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//
            }
        });

        //refrence_.addValueEventListener(new ValueEventListener() {
        //    @Override
        //    public void onDataChange(@NonNull DataSnapshot snapshot)
        //    {
        //        for(DataSnapshot snapshot1 : snapshot.getChildren())
        //        {
        //            Prescription prescription = snapshot1.getValue(Prescription.class);
        //            medlist.add(prescription.getDrugPrescribed());
        //            adapter = new ArrayAdapter<String>(getApplicationContext() , android.R.layout.simple_list_item_multiple_choice, medlist);
        //            presc_list.setAdapter(adapter);
        //            maxID = snapshot.getChildrenCount();
        //        }
        //    }
////
        //    @Override
        //    public void onCancelled(@NonNull DatabaseError error) {
////
        //    }
        //});

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //medlist.add(prescdata.getText().toString());
                Prescription newPrescription = new Prescription();
                newPrescription.setDrugPrescribed(prescdata.getText().toString());
                newPrescription.setDosage(Long.parseLong(drugDosage.getText().toString()));
                newPrescription.setDrugId(maxID + 1);

                for(int i = 0; i < medlist.size(); i++)
                {
                    for(int j = 0; j < prescriptionArrayList.size(); j++)
                    {
                        if(     medlist.get(i).equals(newPrescription.getDrugPrescribed())
                                && prescriptionArrayList.get(j).getDrugPrescribed().equals(newPrescription.getDrugPrescribed())
                                && prescriptionArrayList.get(j).getDosage().equals(newPrescription.getDosage()))
                        {
                            Toast.makeText(getApplicationContext(), "Medication already exist. Try again", Toast.LENGTH_LONG).show();
                            break;
                        }
                        if(     i == medlist.size() - 1
                                && medlist.get(i) != newPrescription.getDrugPrescribed()
                                && j == prescriptionArrayList.size() - 1
                                && prescriptionArrayList.get(j).getDrugPrescribed() != newPrescription.getDrugPrescribed()
                                && prescriptionArrayList.get(j).getDosage() != newPrescription.getDosage()  )
                        {
                            refrence_.child(String.valueOf(maxID + 1)).setValue(newPrescription);

                            Toast.makeText(getApplicationContext(), "Medicine Added!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Pharmacist_Main.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });



        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Prescription newPrescription = new Prescription();
                newPrescription.setDrugPrescribed(prescdata.getText().toString());
                newPrescription.setDosage(Long.parseLong(drugDosage.getText().toString()));
                DatabaseReference ref1 = refrence_;
                ref1.orderByChild("drugPrescribed").equalTo(newPrescription.getDrugPrescribed());
                newPrescription.setDrugId(Long.parseLong(ref1.getKey()));

                refrence_.child(newPrescription.getDrugId().toString()).removeValue();
            }
        });



    }
}