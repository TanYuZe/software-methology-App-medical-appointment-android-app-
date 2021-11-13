package com.example.myapplication.Doctor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Doctor_ViewProfile extends AppCompatActivity {
    TextView doc_name, doc_email, doc_password, doc_role, doc_phoneno;
    Button btn_update, btn_changepass;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;

    Long maxID;

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
        DatabaseReference ref1 = refrence_;
        //doc_password.setText(refrence_.child("email"));



//        ref1.addListenerForSingleValueEvent(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot)
//            {
//                for(DataSnapshot snapshot1 : snapshot.getChildren())
//                {
//                    BasicInfo userinfo = snapshot1.getValue(BasicInfo.class);
//
//                    //maxID = snapshot.getChildrenCount();
//                    doc_name.setText(userinfo.getName());
//                    doc_email.setText((userinfo.getEmail()));
//                    doc_password.setText(userinfo.getPassword());
//                    doc_phoneno.setText(String.valueOf(userinfo.getPhonenumber()));
//                    doc_role.setText((userinfo.getRole()));
//
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });

// Attach a listener to read the data at our posts reference
//        refrence_.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                BasicInfo userinfo = dataSnapshot.getValue(BasicInfo.class);
//                doc_name.setText(userinfo.getName());
//                doc_email.setText((userinfo.getEmail()));
//                doc_password.setText(userinfo.getPassword());
//                doc_phoneno.setText(String.valueOf(userinfo.getPhonenumber()));
//                doc_role.setText((userinfo.getRole()));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            doc_name.setText(user.getUid());
//            doc_email.setText(user.getEmail());
//            doc_phoneno.setText(user.getPhoneNumber());
//            doc_password.setText(user.);
//            //doc_role.setText(user.get);
//        }



    }
}