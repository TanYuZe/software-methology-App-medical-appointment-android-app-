package com.example.myapplication.Doctor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ListViewAdapter_DoctorUpdatePresc;
import com.example.myapplication.Prescribed;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Doctor_ViewUpdatePresc extends AppCompatActivity implements ListViewAdapter_DoctorUpdatePresc.CheckboxCheckListner{

    EditText email_input;
    ListView list;
    Button update_btn;
    ArrayList<Prescribed> presc;
    ListViewAdapter_DoctorUpdatePresc adapter;
    FirebaseDatabase rootNode_;
    DatabaseReference reference_;
    DatabaseReference reference_2;

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
        reference_2 = rootNode_.getReference("Users");

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_input.getText().toString();

                if(!presc.isEmpty())
                {
                    presc.clear();
                }

                reference_.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                            {
                                Prescribed prescribed = dataSnapshot1.getValue(Prescribed.class);

                                reference_2.child(prescribed.get_ID()).child("email").addListenerForSingleValueEvent(new ValueEventListener()
                                {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot2nd)
                                    {
                                        if(snapshot2nd.getValue(String.class).equals(email))
                                        {
                                            presc.add(prescribed);
                                            adapter.notifyDataSetChanged();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });





    }
    public void getCheckBoxCheckedListner(int position)
    {
        Prescribed removingPrescribed = presc.get(position);

        reference_.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        Prescribed prescribed = dataSnapshot1.getValue(Prescribed.class);
                        if(presc.isEmpty() || position >= presc.size())
                        {
                            break;
                        }
                        else if(prescribed.getDrugID().equals(removingPrescribed.getDrugID())
                                && prescribed.getDate().equals(removingPrescribed.getDate()))
                        {
                            reference_.child(dataSnapshot.getKey()).child(dataSnapshot1.getKey()).removeValue();
                            presc.remove(removingPrescribed);
                            adapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}