package com.example.myapplication.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_ViewUser extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button View_btn;
    TextView t_name;
    TextView t_role;
    TextView t_password;
    TextView t_email;
    TextView t_phoneno;
    EditText email_input;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);
        email_input = findViewById(R.id.email_input);
        t_name = findViewById(R.id.t_name);
        t_role = findViewById(R.id.t_role);
        t_password = findViewById(R.id.t_password);
        t_email = findViewById(R.id.t_phoneno);
        t_phoneno = findViewById(R.id.t_roles);

        View_btn = findViewById(R.id.ViewAcc_btn);

        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");

        View_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Query query = FirebaseDatabase.getInstance().getReference("User").orderByChild("email").equalTo(String.valueOf(email_input));
//                query.addListenerForSingleValueEvent(valueEventListener);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // Name, email address, and profile photo Url
                    t_name.setText(user.getDisplayName());
                    t_email.setText(user.getEmail());
                    t_role.setText(user.getPhoneNumber());


                    // Check if user's email is verified
                    boolean emailVerified = user.isEmailVerified();

                    // The user's ID, unique to the Firebase project. Do NOT use this value to
                    // authenticate with your backend server, if you have one. Use
                    // FirebaseUser.getIdToken() instead.
                    String uid = user.getUid();
                }






//                String query = "SELECT * FROM Users WHERE email = '" + email
//                cursor = db.rawQuery(query, null);
//                cursor.moveToFirst();
//                t_role.setText(cursor.getString(1));
//                t_name.setText(cursor.getString(2));
//                t_email.setText(cursor.getString(3));
//                t_password.setText(cursor.getString(4));
//                t_phoneno.setText(cursor.getString(5));



            }
        });
    }
//    private void getdata() {
//        refrence_.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        String value = snapshot.getValue(String.class);
//                        t_name.setText(value);
//
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
}