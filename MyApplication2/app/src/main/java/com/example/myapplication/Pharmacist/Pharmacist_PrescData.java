package com.example.myapplication.Pharmacist;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;

public class Pharmacist_PrescData extends AppCompatActivity {
    EditText prescdata;
    Button add_btn, delete_btn;
    ListView presc_list;
    ArrayAdapter<String> adapter;
    ArrayList<String> medlist;
    String itemSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_presc_data);
        prescdata = (EditText) findViewById(R.id.Presc_name);
        add_btn = findViewById(R.id.add_btn);
        delete_btn = findViewById(R.id.delete_btn);
        presc_list = findViewById(R.id.Presc_List);
        medlist = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Pharmacist_PrescData.this, android.R.layout.simple_list_item_multiple_choice, medlist);
        presc_list.setAdapter(adapter);


        for (int i = 1; i < 5; i++) {
            medlist.add("Medicine" + i);

        }

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medlist.add(prescdata.getText().toString());
                adapter.notifyDataSetChanged();
                prescdata.setText("");
            }
        });



        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < presc_list.getCount(); i++) {
                    if (presc_list.isItemChecked(i)) {
                        medlist.remove(presc_list.getItemAtPosition(i).toString());
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });



    }
}