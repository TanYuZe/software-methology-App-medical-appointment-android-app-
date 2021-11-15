package com.example.myapplication.Doctor;

import android.content.Context;
import android.os.StrictMode;

import androidx.annotation.NonNull;

import com.example.myapplication.Prescribed;
import com.example.myapplication.Prescription;
import com.example.myapplication.RegisterEntity;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DoctorController {
    private static DoctorController INSTANCE = null;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    DatabaseReference refrence_2;
    ArrayList<Prescribed> prescribedArrayList;

    public DoctorController() {
    };

    public static DoctorController getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DoctorController();
        }
        return (INSTANCE);
    }

    public void validateAddMedicine(ArrayList<Prescription> prescriptionArrayList, String email, ArrayList<String> stringArrayList, Context context)
    {
        DoctorEntity doctorEntity = new DoctorEntity(context);
        doctorEntity.addMedicine(prescriptionArrayList, email, stringArrayList, context);
    }

    public void validateUpdateName(String name, Context context)
    {
        DoctorEntity doctorEntity = new DoctorEntity(context);
        doctorEntity.updateName(name);
    }
    public void validateUpdatePassword(String password, Context context)
    {
        DoctorEntity doctorEntity = new DoctorEntity(context);
        doctorEntity.updatePassword(password);
    }
    public void validateUpdateName(int number, Context context)
    {
        DoctorEntity doctorEntity = new DoctorEntity(context);
        doctorEntity.updateNumber(number);
    }
}
