package com.example.myapplication.Pharmacist;

import com.example.myapplication.Doctor.DoctorController;

public class PharmacistController {
    private static PharmacistController INSTANCE = null;

    private PharmacistController() {
    };

    public static PharmacistController getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PharmacistController();
        }
        return (INSTANCE);
    }
}
