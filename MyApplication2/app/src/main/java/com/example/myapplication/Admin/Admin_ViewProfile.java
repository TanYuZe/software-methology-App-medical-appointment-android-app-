package com.example.myapplication.Admin;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BasicInfo;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_ViewProfile extends AppCompatActivity {
    TextView admin_name, admin_email, admin_password, admin_role, admin_phoneno;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    ArrayList<BasicInfo> info;
    Long maxID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_profile);

        admin_name = findViewById(R.id.admin_name);
        admin_email = findViewById(R.id.admin_email);
        admin_password = findViewById(R.id.admin_password);
        admin_role = findViewById(R.id.admin_role);
        admin_phoneno = findViewById(R.id.admin_phoneno);
        info = new ArrayList<BasicInfo>();

        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");


        refrence_.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    BasicInfo userinfo = snapshot1.getValue(BasicInfo.class);

                    maxID = snapshot.getChildrenCount();
                    admin_name.setText(userinfo.getName());
                    admin_email.setText((userinfo.getEmail()));
                    admin_password.setText(userinfo.getPassword());
                    admin_phoneno.setText(String.valueOf(userinfo.getPhonenumber()));
                    admin_role.setText((userinfo.getRole()));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}