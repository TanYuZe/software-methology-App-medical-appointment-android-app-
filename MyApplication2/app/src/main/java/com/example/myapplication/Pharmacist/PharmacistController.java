package com.example.myapplication.Pharmacist;

import android.content.Context;

import com.example.myapplication.Prescription;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PharmacistController {
    private static PharmacistController INSTANCE = null;

    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;

    private PharmacistController() {
    };

    public static PharmacistController getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PharmacistController();
        }
        return (INSTANCE);
    }

//    public void validateAddMedicine(ArrayList<Prescription> prescriptionArrayList, String email, ArrayList<String> stringArrayList, Context context)
//    {
//        PharmacistEntity doctorEntity = new PharmacistEntity(context);
//        doctorEntity.addMedicine(prescriptionArrayList, email, stringArrayList, context);
//    }

    public void validateUpdateName(String name, Context context)
    {
        PharmacistEntity pharmacistEntity = new PharmacistEntity(context);
        pharmacistEntity.updateName(name);
    }
    public void validateUpdatePassword(String password, Context context)
    {
        PharmacistEntity pharmacistEntity = new PharmacistEntity(context);
        pharmacistEntity.updatePassword(password);
    }
    public void validateUpdateNumber(int number, Context context)
    {
        PharmacistEntity pharmacistEntity = new PharmacistEntity(context);
        pharmacistEntity.updateNumber(number);
    }

    public void validateAddPrescription(Long maxID, String drugprescribed, String drugdosage, ArrayList<Prescription> prescriptionArrayList,ArrayList<String> medlist, Context context)
    {
        PharmacistEntity pharmacistEntity = new PharmacistEntity(context);
        pharmacistEntity.AddPrescription(maxID, drugprescribed, drugdosage, prescriptionArrayList, medlist, context);
    }

    public void validateDeletePrescription(ArrayList<Prescription> prescriptionArrayList, int position, Context context)
    {
        PharmacistEntity pharmacistEntity = new PharmacistEntity(context);
        pharmacistEntity.DeletePrescription(prescriptionArrayList, position);
    }

    public void validateFetchPrescTable(Long maxID, ArrayList<String>  medlist, ArrayList<Prescription> prescriptionArrayList, Context context)
    {
        PharmacistEntity pharmacistEntity = new PharmacistEntity(context);
        //pharmacistEntity.FetchPrescTable(maxID, medlist, prescriptionArrayList);

    }

}
