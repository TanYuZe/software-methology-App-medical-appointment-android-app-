package com.example.myapplication.Doctor;

import java.util.ArrayList;

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

    public void validateAddMedicine(ArrayList<String> StringArray)
    {

    }

}
