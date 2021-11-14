package com.example.myapplication.Patient;

import android.os.Bundle;
import android.widget.Button;
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

public class Patient_ViewProfile extends AppCompatActivity {
    TextView pat_name, pat_email, pat_password, pat_role, pat_phoneno;
    Button btn_update, btn_changepass;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_profile);
        pat_name = findViewById(R.id.pat_name);
        pat_email = findViewById(R.id.pat_email);
        pat_password = findViewById(R.id.pat_password);
        pat_role = findViewById(R.id.pat_role);
        pat_phoneno = findViewById(R.id.pat_phoneno);

        btn_update = findViewById(R.id.btn_update);
        btn_changepass = findViewById(R.id.btn_changepass);

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
                        pat_name.setText(userinfo.getName());
                        pat_email.setText((userinfo.getEmail()));
                        pat_password.setText(userinfo.getPassword());
                        pat_phoneno.setText(String.valueOf(userinfo.getPhonenumber()));
                        pat_role.setText((userinfo.getRole()));
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
}