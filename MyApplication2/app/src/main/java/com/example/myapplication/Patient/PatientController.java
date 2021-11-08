package com.example.myapplication.Patient;



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
}
