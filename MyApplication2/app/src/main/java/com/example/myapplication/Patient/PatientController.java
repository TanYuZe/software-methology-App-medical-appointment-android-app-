package com.example.myapplication.Patient;


import android.content.Context;

import com.example.myapplication.Prescribed;

import java.util.ArrayList;

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
    public void validateViewPrescription(ArrayList<Prescribed> prescribedArrayList, Context context)
    {
        PatientEntity patientEntityEntity = new PatientEntity(context);
        patientEntityEntity.ViewPrescription(prescribedArrayList, context);
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
