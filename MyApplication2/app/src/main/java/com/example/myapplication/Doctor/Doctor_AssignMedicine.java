package com.example.myapplication.Doctor;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Prescription;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doctor_AssignMedicine extends AppCompatActivity
{
    EditText filter_text;
    ListView listview_med;
    Button assign_med_btn;
    ArrayAdapter<String> adapter;
    ArrayList<String> medlist;
    String itemSelected;
    String items;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    ArrayList<Medicine> userArrayList;
    ListViewAdapter listAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_assign_medicine);
        assign_med_btn = findViewById(R.id.Assignmed_btn);
        listview_med = findViewById(R.id.ListViewMed);
        filter_text = (EditText) findViewById(R.id.filter_text);
        medlist = new ArrayList<String>();

        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Prescription");


//        userArrayList = new ArrayList<>();
//
//        for(int i = 0; i< 50; i++){
//
//            Medicine med = new Medicine("name" + i , 0);
//            userArrayList.add(med);
//
//        }
        //listAdapter = new ListViewAdapter(Doctor_AssignMedicine.this,userArrayList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, medlist);
        listview_med.setAdapter(adapter);

        refrence_.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Prescription prescription;
                    prescription = snapshot1.getValue(Prescription.class);
                    medlist.add(prescription.getDrugPrescribed());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        filter_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (Doctor_AssignMedicine.this).adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        assign_med_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set and get items from listview checkbox
                itemSelected = "Selected Prescriptions: \n";

                for (int i = 0; i < listview_med.getCount(); i++) {
                    if (listview_med.isItemChecked(i)) {
                        items = listview_med.getItemAtPosition(i).toString();
                        itemSelected += items  + "\n";
                    }
                }

                if(!items.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Doctor_AssignMedicine.this);
                    builder.setTitle("Confirm Prescription!").setMessage(itemSelected);
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                    Toast.makeText(Doctor_AssignMedicine.this, "Prescription added to User", Toast.LENGTH_SHORT).show();
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
                else
                {
                    Toast.makeText(Doctor_AssignMedicine.this, "No Prescription Assigned to User", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate(R.menu.assignmed_menu, menu);
//        return true;
//        //return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//
//
//
//        if (id == R.id.item_done) {
//            itemSelected = "Selected items: \n";
//            for (int i = 0; i < listview_med.getCount(); i++) {
//                if (listview_med.isItemChecked(i)) {
//                    itemSelected += listview_med.getItemAtPosition(i) + "\n";
//                }
//            }
//            Toast.makeText(this, itemSelected, Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//
//    }




}
