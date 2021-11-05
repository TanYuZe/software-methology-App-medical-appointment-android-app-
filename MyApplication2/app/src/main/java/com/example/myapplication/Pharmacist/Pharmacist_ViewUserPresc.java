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

public class Pharmacist_ViewUserPresc extends AppCompatActivity {
    ListView listview;
    EditText presc_token;
    Button view_presc , btn_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_view_user_presc);
        presc_token = (EditText) findViewById(R.id.Presc_token);
        view_presc = findViewById(R.id.View_presc);
        btn_scan = findViewById(R.id.btn_Scan);
        listview = findViewById(R.id.listview);

        ArrayList<String> list = new ArrayList<>();
        //listview.add(xxxxx);
        ArrayAdapter arrayadapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list);
        listview.setAdapter(arrayadapter);


        view_presc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //query out medicines tie to token input from Edittext
            }
        });


        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });








    }
}