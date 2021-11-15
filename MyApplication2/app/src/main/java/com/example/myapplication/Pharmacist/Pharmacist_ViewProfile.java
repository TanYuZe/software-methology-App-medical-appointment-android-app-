package com.example.myapplication.Pharmacist;

import android.content.DialogInterface;
import android.content.Intent;
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

public class Pharmacist_ViewProfile extends AppCompatActivity {
    TextView phar_name, phar_email, phar_password, phar_role, phar_phoneno;
    ImageButton edit_name, edit_email, edit_password, edit_phoneno;
    Button btn_update;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    String changes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_view_profile);
        phar_name = findViewById(R.id.phar_name);
        phar_email = findViewById(R.id.phar_email);
        phar_password = findViewById(R.id.phar_password);
        phar_role = findViewById(R.id.phar_role);
        phar_phoneno = findViewById(R.id.phar_phoneno);

        edit_name = findViewById(R.id.imageButton9);
        edit_password = findViewById(R.id.imageButton8);
        edit_phoneno = findViewById(R.id.imageButton10);

        PharmacistController Controller = PharmacistController.getINSTANCE();



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

        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Pharmacist_ViewProfile.this);
                final EditText input = new EditText(Pharmacist_ViewProfile.this);
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
                                phar_name.setText(changes);
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
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Pharmacist_ViewProfile.this);
                final EditText input = new EditText(Pharmacist_ViewProfile.this);
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
                                phar_password.setText(changes);
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
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Pharmacist_ViewProfile.this);
                final EditText input = new EditText(Pharmacist_ViewProfile.this);
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
                                    phar_phoneno.setText(changes);
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