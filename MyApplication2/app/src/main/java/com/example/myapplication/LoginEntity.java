package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.Admin.Admin_Main;
import com.example.myapplication.Doctor.Doctor_Main;
import com.example.myapplication.Patient.Patient_Main;
import com.example.myapplication.Pharmacist.Pharmacist_Main;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class LoginEntity {
    private String ID;
    private String role;
    private String name;
    private String email;
    private String password;
    private String contact;
    FirebaseAuth mAuth;

    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;

    public void Login(String email, String password, Context context)
    {
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        if(email.equals("") || password.equals(""))
        {
            Toast.makeText(context, "Failed to log in", Toast.LENGTH_LONG).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();

                        if(user.isEmailVerified())
                        {
                            String[] tempRole = {""};
                            DatabaseReference zaReference = refrence_.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("role");
                            zaReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String zaRole = snapshot.getValue(String.class);
                                    tempRole[0] = zaRole;switch(tempRole[0])
                                    {
                                        case "Admin":
                                            Intent intent = new Intent(context, Admin_Main.class);
                                            context.startActivity(intent);
                                            break;
                                        case "Pharmacist":
                                            Intent intent1 = new Intent(context, Pharmacist_Main.class);
                                            context.startActivity(intent1);
                                            break;
                                        case "Doctor":
                                            Intent intent2 = new Intent(context, Doctor_Main.class);
                                            context.startActivity(intent2);
                                            break;
                                        case "Patient":
                                            Intent intent3 = new Intent(context, Patient_Main.class);
                                            context.startActivity(intent3);
                                            break;
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        else
                        {
                            user.sendEmailVerification();
                            Toast.makeText(context, "Check your email!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(context, "Failed to log in", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}
