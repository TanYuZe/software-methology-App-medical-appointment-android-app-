package com.example.myapplication.Doctor;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    ImageButton edit_name, edit_email, edit_password, edit_phoneno;
    String changes;
    FirebaseDatabase rootNode_;
    DatabaseReference reference_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_profile);
        doc_name = findViewById(R.id.doc_name);
        doc_email = findViewById(R.id.doc_email);
        doc_password = findViewById(R.id.doc_password);
        doc_role = findViewById(R.id.doc_role);
        doc_phoneno = findViewById(R.id.doc_phoneno);


        edit_name = findViewById(R.id.imageButton2);
        edit_password = findViewById(R.id.imageButton3);
        edit_phoneno = findViewById(R.id.imageButton4);


        btn_update = findViewById(R.id.btn_update);
        btn_changepass = findViewById(R.id.btn_changepass);

        DoctorController doctorController = DoctorController.getINSTANCE();




        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        reference_ = rootNode_.getReference("Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        reference_.orderByChild("email").equalTo(user.getEmail());


        reference_.addValueEventListener(new ValueEventListener()
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

        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Doctor_ViewProfile.this);
                final EditText input = new EditText(Doctor_ViewProfile.this);
                mydialog.setMessage("Please enter your name");
                mydialog.setTitle("Name Change");
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                mydialog.setView(input);

                mydialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                changes = input.getText().toString();
                                doctorController.updateName(changes);
                                doc_name.setText(changes);
                            }
                        });
                mydialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = mydialog.create();
                alert11.show();
            }
        });



        edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Doctor_ViewProfile.this);
                final EditText input = new EditText(Doctor_ViewProfile.this);
                mydialog.setMessage("Please enter your new password");
                mydialog.setTitle("Password Change");
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                mydialog.setView(input);

                mydialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                changes = input.getText().toString();
                                doctorController.updatePassword(changes);
                                doc_password.setText(changes);
                            }
                        });
                mydialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = mydialog.create();
                alert11.show();
            }
        });

        edit_phoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Doctor_ViewProfile.this);
                final EditText input = new EditText(Doctor_ViewProfile.this);
                mydialog.setMessage("Please enter your new Phone Number");
                mydialog.setTitle("Phone Number Change");
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                mydialog.setView(input);

                mydialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                changes = input.getText().toString();

                                if(changes.equals(""))
                                {
                                    input.setError("Cannot be empty");

                                }
                                else {
                                    doc_phoneno.setText(changes);
                                    doctorController.updateNumber(Integer.parseInt(changes));
                                }
                            }
                        });
                mydialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = mydialog.create();
                alert11.show();
            }
        });




    }
}