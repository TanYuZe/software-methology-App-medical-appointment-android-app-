package com.example.myapplication.Doctor;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ListViewAdapter_DoctorUpdatePresc;
import com.example.myapplication.Prescribed;
import com.example.myapplication.R;

import java.util.ArrayList;

public class DoctorUpdatePresc extends AppCompatActivity {
    ListView listview;
    Button update_btn;
    ListViewAdapter_DoctorUpdatePresc adapter;
    ArrayList<Prescribed> array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_update_presc);
        listview = findViewById(R.id.listview2);
        update_btn = findViewById(R.id.update_btn2);
        array = new ArrayList<Prescribed>();
        Prescribed presc = new Prescribed("hello", Long.parseLong("a12"), "dsds", 23, true);
        array.add(presc);
        adapter = new ListViewAdapter_DoctorUpdatePresc(DoctorUpdatePresc.this,  array);


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(DoctorUpdatePresc.this);
                final EditText input = new EditText(DoctorUpdatePresc.this);
                mydialog.setMessage("Please enter your name");
                mydialog.setTitle("Name Change");
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                mydialog.setView( getLayoutInflater().inflate( R.layout.listview_doctor_updatepresc, null ) );
                View view = getLayoutInflater().inflate( R.layout.listview_doctor_updatepresc, null);
                EditText et_drugid = (EditText) view.findViewById(R.id.et_drugid);
                EditText q_input = (EditText) view.findViewById(R.id.et_quantity);
                mydialog.setView(view);


                mydialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
//                                changes = input.getText().toString();
//                                admin_name.setText(changes);
                            }
                        });
                mydialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = mydialog.create();
                alert11.show();


            }
        });


    }
}