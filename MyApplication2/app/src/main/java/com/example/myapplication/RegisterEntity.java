package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.myapplication.Admin.Admin;
import com.example.myapplication.Doctor.Doctor;
import com.example.myapplication.Patient.Patient;
import com.example.myapplication.Pharmacist.Phamarcist;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterEntity {
    private String ID;
    private String role;
    private String name;
    private String email;
    private String password;
    private String contact;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    
    FirebaseAuth mAuth;

    public RegisterEntity(Context context)
    {

    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void addUser(String email, String password, String name, int contactNum, String role, Context context )
    {
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {

                    BasicInfo basicInfo = new BasicInfo(FirebaseAuth.getInstance().getCurrentUser().getUid(), name, email, password, contactNum, role);
                    refrence_.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(basicInfo);
                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                }
            }
        });
    }
}
