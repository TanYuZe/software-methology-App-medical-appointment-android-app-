package com.example.myapplication.Pharmacist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Admin.Admin_UpdateInfo;
import com.example.myapplication.Admin.Admin_ViewProfile;
import com.example.myapplication.BasicInfo;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pharmacist_ViewProfile extends AppCompatActivity {
    TextView phar_name, phar_email, phar_password, phar_role, phar_phoneno;
    Button btn_update;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_view_profile);
        phar_name = findViewById(R.id.phar_name);
        phar_email = findViewById(R.id.phar_email);
        phar_password = findViewById(R.id.phar_password);
        phar_role = findViewById(R.id.phar_role);
        phar_phoneno = findViewById(R.id.phar_phoneno);
        btn_update = findViewById(R.id.btn_update2);

        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        refrence_.orderByChild("email").equalTo(user.getEmail());


        refrence_.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    BasicInfo userinfo = snapshot1.getValue(BasicInfo.class);

                    if(userinfo.getId().equals(user.getUid()) )
                    {
                        //maxID = snapshot.getChildrenCount();
                        phar_name.setText(userinfo.getName());
                        phar_email.setText((userinfo.getEmail()));
                        phar_password.setText(userinfo.getPassword());
                        phar_phoneno.setText(String.valueOf(userinfo.getPhonenumber()));
                        phar_role.setText((userinfo.getRole()));
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pharmacist_ViewProfile.this, Pharmacist_UpdateInfo.class);
                startActivity(intent);
            }
        });
    }
}