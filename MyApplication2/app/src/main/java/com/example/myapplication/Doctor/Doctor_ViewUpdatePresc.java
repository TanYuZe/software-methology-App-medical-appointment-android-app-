package com.example.myapplication.Doctor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ListViewAdapter_DoctorUpdatePresc;
import com.example.myapplication.Prescribed;
import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Doctor_ViewUpdatePresc extends AppCompatActivity implements ListViewAdapter_DoctorUpdatePresc.CheckboxCheckListner{

    EditText email_input;
    ListView list;
    Button update_btn;
    ArrayList<Prescribed> presc;
    ListViewAdapter_DoctorUpdatePresc adapter;
    FirebaseDatabase rootNode_;
    DatabaseReference reference_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_update_presc);
        email_input = findViewById(R.id.viewupdate_emailinput);
        list = findViewById(R.id.listview1);
        update_btn = findViewById(R.id. update_btn);

        presc = new ArrayList<Prescribed>();

        adapter = new ListViewAdapter_DoctorUpdatePresc(this, presc);

        list.setAdapter(adapter);
        adapter.setCheckedListner((ListViewAdapter_DoctorUpdatePresc.CheckboxCheckListner) this);


        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        reference_ = rootNode_.getReference("Prescribed");






        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get presc base on user email



            }
        });





    }
    public void getCheckBoxCheckedListner(int position)
    {
        //write code here to be able to delete listview entries



        //frontend code to delete visually
        presc.remove(presc.get(position));

    }
}