package com.example.myapplication.Doctor;

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

public class Doctor_ViewProfile extends AppCompatActivity {
    TextView doc_name, doc_email, doc_password, doc_role, doc_phoneno;
    Button btn_update, btn_changepass;
    FirebaseDatabase rootNode_;

    DatabaseReference refrence_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_profile);
        doc_name = findViewById(R.id.doc_name);
        doc_email = findViewById(R.id.doc_email);
        doc_password = findViewById(R.id.doc_password);
        doc_role = findViewById(R.id.doc_role);
        doc_phoneno = findViewById(R.id.doc_phoneno);


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
                        doc_name.setText(userinfo.getName());
                        doc_email.setText((userinfo.getEmail()));
                        doc_password.setText(userinfo.getPassword());
                        doc_phoneno.setText(String.valueOf(userinfo.getPhonenumber()));
                        doc_role.setText((userinfo.getRole()));
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });






    }
}