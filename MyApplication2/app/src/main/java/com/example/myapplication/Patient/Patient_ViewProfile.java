package com.example.myapplication.Patient;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
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

public class Patient_ViewProfile extends AppCompatActivity {
    TextView pat_name, pat_email, pat_password, pat_role, pat_phoneno;

    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    ImageButton edit_name, edit_email, edit_password, edit_phoneno;
    String changes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_profile);
        pat_name = findViewById(R.id.pat_name);
        pat_email = findViewById(R.id.pat_email);
        pat_password = findViewById(R.id.pat_password);
        pat_role = findViewById(R.id.pat_role);
        pat_phoneno = findViewById(R.id.pat_phoneno);

        edit_name = findViewById(R.id.imageButton5);
        edit_password = findViewById(R.id.imageButton6);
        edit_phoneno = findViewById(R.id.imageButton7);

        PatientController Controller = PatientController.getINSTANCE();


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

        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Patient_ViewProfile.this);
                final EditText input = new EditText(Patient_ViewProfile.this);
                mydialog.setMessage("Please enter your name");
                mydialog.setTitle("Name Change");
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                mydialog.setView(input);

                mydialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                changes = input.getText().toString();
                                Controller.updateName(changes);
                                pat_name.setText(changes);
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
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Patient_ViewProfile.this);
                final EditText input = new EditText(Patient_ViewProfile.this);
                mydialog.setMessage("Please enter your new password");
                mydialog.setTitle("Password Change");
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                mydialog.setView(input);

                mydialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                changes = input.getText().toString();
                                Controller.updatePassword(changes);
                                pat_password.setText(changes);
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
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Patient_ViewProfile.this);
                final EditText input = new EditText(Patient_ViewProfile.this);
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
                                    pat_phoneno.setText(changes);
                                    Controller.updateNumber(Integer.parseInt(changes));
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