package com.example.myapplication.Pharmacist;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
    Button add_btn, delete_btn;
    ListView presc_list;
    String itemSelected;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_presc_data);
        prescdata = (EditText) findViewById(R.id.Presc_name);
        add_btn = findViewById(R.id.add_btn);
        delete_btn = findViewById(R.id.delete_btn);
        presc_list = findViewById(R.id.Presc_List);



        ArrayList<String>  medlist = new ArrayList<String>();

        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference();


        refrence_.child("Prescription").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Prescription prescription = snapshot1.getValue(Prescription.class);
                    medlist.add(prescription.getDrugPrescribed());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medlist.add(prescdata.getText().toString());
            }
        });



        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < presc_list.getCount(); i++) {
                    if (presc_list.isItemChecked(i)) {
                        medlist.remove(presc_list.getItemAtPosition(i));
                    }
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, medlist);
        presc_list.setAdapter(adapter);

    }
}