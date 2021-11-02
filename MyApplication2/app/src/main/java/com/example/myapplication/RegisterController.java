package com.example.myapplication;

import com.example.myapplication.Admin.Admin;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterController {

    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;


    public boolean validateRegistration(int id, String email, String password, String name, int contactNum, String role )
    {
        rootNode_ = FirebaseDatabase.getInstance();
        refrence_ = rootNode_.getReference("Users");

        switch (role)
        {
            case "Patient":
                Patient patient = new Patient(id, email, password, name, contactNum, role);
                refrence_.setValue(patient);
                return true;
            case "Doctor":
                Doctor doctor = new Doctor(id, email, password, name, contactNum, role);
                refrence_.setValue(doctor);
                return true;
            case "Phamarcist":
                Phamarcist pharmacist = new Phamarcist(id, email, password, name, contactNum, role);
                refrence_.setValue(pharmacist);
                return true;
            case "Admin":
                Admin admin = new Admin(id, email, password, name, contactNum, role);
                refrence_.child(String.valueOf(id)).setValue(admin);
                return true;

        }
        //BasicInfo basicInfo = new BasicInfo();
        //refrence_.setValue(basicInfo);
        return false;
    }
}
