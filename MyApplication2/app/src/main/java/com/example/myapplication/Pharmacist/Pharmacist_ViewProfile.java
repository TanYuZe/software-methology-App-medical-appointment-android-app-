package com.example.myapplication.Pharmacist;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    }
}