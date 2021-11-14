package com.example.myapplication.Pharmacist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ListViewAdapter_Phar;
import com.example.myapplication.Prescription;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pharmacist_PrescData extends AppCompatActivity implements ListViewAdapter_Phar.CheckboxCheckListner{
    EditText prescdata;
    EditText drugDosage;
    Button add_btn, delete_btn;
    ListView presc_list;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    ListViewAdapter_Phar adapter;
    ArrayList<String>  medlist;
    ArrayList<Prescription> prescriptionArrayList;
    ArrayList<Prescription> SelectedList;
    Long maxID;
    ArrayList<String> deletelist;
    Prescription newPrescription1;




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



        medlist = new ArrayList<String>();
        prescriptionArrayList = new ArrayList<Prescription>();

        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference().child("Prescription");

        adapter = new ListViewAdapter_Phar(Pharmacist_PrescData.this , prescriptionArrayList);
        presc_list.setAdapter(adapter);
        adapter.setCheckedListner((ListViewAdapter_Phar.CheckboxCheckListner) this);




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
                Prescription newPrescription = new Prescription();
                newPrescription.setDrugPrescribed(prescdata.getText().toString());
                newPrescription.setDosage(Long.parseLong(drugDosage.getText().toString()));
                newPrescription.setQuantity(0);
                newPrescription.setDrugId(maxID + 1);

                for(int i = 0; i < medlist.size(); i++)
                {
                    for(int j = 0; j < prescriptionArrayList.size(); j++)
                    {
                        Long index = Long.valueOf(j);
                        if(     medlist.get(i).equals(newPrescription.getDrugPrescribed())
                                && prescriptionArrayList.get(j).getDrugPrescribed().equals(newPrescription.getDrugPrescribed())
                                && prescriptionArrayList.get(j).getDosage().equals(newPrescription.getDosage()))
                        {
                            Toast.makeText(getApplicationContext(), "Medication already exist. Try again", Toast.LENGTH_LONG).show();
                            break;
                        }
                        if(prescriptionArrayList.get(j).getDrugId() != index + 1
                            && prescriptionArrayList.get(j).getDrugPrescribed() != newPrescription.getDrugPrescribed())
                        {
                            newPrescription.setDrugId(index + 1);
                            refrence_.child(String.valueOf(index + 1)).setValue(newPrescription);
                        }
                        else if(     i == medlist.size() - 1
                                && medlist.get(i) != newPrescription.getDrugPrescribed()
                                && j == prescriptionArrayList.size() - 1
                                && prescriptionArrayList.get(j).getDrugPrescribed() != newPrescription.getDrugPrescribed()
                                && prescriptionArrayList.get(j).getDosage() != newPrescription.getDosage()  )
                        {
                            refrence_.child(String.valueOf(maxID + 1)).setValue(newPrescription);

                            Toast.makeText(getApplicationContext(), "Medicine Added!", Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(getApplicationContext(), Pharmacist_Main.class);
//                            startActivity(intent);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });



//        delete_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //newPrescription1 = new Prescription();
////                for(int i=0; i < presc_list.getCount(); i++) {
////
////                    if (presc_list.isItemChecked(i)) {
////                        deletelist.add(medlist.get(i));
////                    }
////                }
//                //newPrescription1.setDrugPrescribed(SelectedList.get());
//
////                newPrescription.setDrugPrescribed(prescdata.getText().toString());
////                newPrescription.setDosage(Long.parseLong(drugDosage.getText().toString()));
////                newPrescription.setQuantity(0);
//                DatabaseReference ref1 = refrence_;
//                ref1.orderByChild("drugPrescribed").equalTo(newPrescription1.getDrugPrescribed());
//                //newPrescription1.setDrugId(Long.parseLong(ref1.getKey()));
//
//                refrence_.child(ref1.getKey()).removeValue();
//
//                adapter.notifyDataSetChanged();
//
//
//
//            }
//        });



    }

    public void getCheckBoxCheckedListner(int position)
    {
        newPrescription1.setDrugPrescribed(prescriptionArrayList.get(position).getDrugPrescribed());
        newPrescription1.setDosage(prescriptionArrayList.get(position).getDosage());
        newPrescription1.setQuantity(0);
        newPrescription1.setDrugId(prescriptionArrayList.get(position).getDrugId());
        prescriptionArrayList.get(position).getDrugPrescribed();



        AlertDialog.Builder builder = new AlertDialog.Builder(Pharmacist_PrescData.this);
        builder.setTitle("Confirm Prescription!").setMessage("Do you want to delete " +prescriptionArrayList.get(position).getDrugPrescribed());
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        DatabaseReference ref1 = refrence_;
                        ref1.orderByChild("drugPrescribed").equalTo(prescriptionArrayList.get(position).getDrugId());
                        refrence_.child(prescriptionArrayList.get(position).getDrugId().toString()).removeValue();
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