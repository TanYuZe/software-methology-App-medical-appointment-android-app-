package com.example.myapplication.Doctor;

public class DoctorController {
    private static DoctorController INSTANCE = null;

    private DoctorController() {
    };

    public static DoctorController getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DoctorController();
        }
        return (INSTANCE);
    }
}
