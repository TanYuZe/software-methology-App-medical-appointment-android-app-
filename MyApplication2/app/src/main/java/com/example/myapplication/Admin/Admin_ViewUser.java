package com.example.myapplication.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BasicInfo;
import com.example.myapplication.ListViewAdapter_Admin_ViewUsers;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
    String email;
    ArrayList<BasicInfo> basicInfoArrayList;
    ListViewAdapter_Admin_ViewUsers listViewAdapter_admin_viewUsers;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);
        email_input = findViewById(R.id.Email_input);
        t_name = findViewById(R.id.t_name);
        t_role = findViewById(R.id.t_role);
        t_password = findViewById(R.id.t_password);
        t_email = findViewById(R.id.t_phoneno);
        t_phoneno = findViewById(R.id.t_roles);

        //listView = findViewById(R.id.listview);

        View_btn = findViewById(R.id.DeleteAcc_btn);


        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");

        AdminController adminController = AdminController.getINSTANCE();


        refrence_.orderByChild("email").equalTo(email);
        View_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refrence_.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        email = email_input.getText().toString();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            BasicInfo userinfo = snapshot1.getValue(BasicInfo.class);
                            if (userinfo.getEmail().equals(email)) {
                              ////maxID = snapshot.getChildrenCount();
                              t_name.setText(userinfo.getName());
                              t_email.setText((userinfo.getEmail()));
                              t_password.setText(userinfo.getPassword());
                              t_phoneno.setText(String.valueOf(userinfo.getPhonenumber()));
                              t_role.setText((userinfo.getRole()));
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



}



