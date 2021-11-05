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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;

public class Doctor_AssignMedicine extends AppCompatActivity
{
    EditText filter_text;
    ListView listview_med;
    Button assign_med_btn;
    ArrayAdapter<String> adapter;
    ArrayList<String> medlist;
    String itemSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_assign_medicine);
        assign_med_btn = findViewById(R.id.Assignmed_btn);
        listview_med = findViewById(R.id.ListViewMed);
        filter_text = (EditText) findViewById(R.id.filter_text);
        medlist = new ArrayList<String>();

        for (int i = 1; i < 50; i++) {
            medlist.add("Medicine" + i);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, medlist);
        listview_med.setAdapter(adapter);

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
                        itemSelected += listview_med.getItemAtPosition(i) + "\n";
                    }
                }

                //
                AlertDialog.Builder builder = new AlertDialog.Builder(Doctor_AssignMedicine.this);
                builder.setTitle("Confirm Prescription!").
                        setMessage(itemSelected);
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
