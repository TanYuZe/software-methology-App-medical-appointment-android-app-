package com.example.myapplication.Patient;


import android.content.Context;

import androidx.annotation.NonNull;

import com.example.myapplication.Doctor.DoctorEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PatientController {
    private static PatientController INSTANCE = null;


    private PatientController() {
    };

    public static PatientController getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PatientController();
        }
        return (INSTANCE);
    }
    public void validateUpdateName(String name, Context context)
    {
        PatientEntity patientEntityEntity = new PatientEntity(context);
        patientEntityEntity.updateName(name);
    }
    public void validateUpdatePassword(String password, Context context)
    {
        PatientEntity patientEntityEntity = new PatientEntity(context);
        patientEntityEntity.updatePassword(password);
    }
    public void validateUpdateNumber(int number, Context context)
    {
        PatientEntity patientEntityEntity = new PatientEntity(context);
        patientEntityEntity.updateNumber(number);
    }
}
