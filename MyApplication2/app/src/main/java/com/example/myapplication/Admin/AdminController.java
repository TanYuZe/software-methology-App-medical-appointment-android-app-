package com.example.myapplication.Admin;

public class AdminController {
    private static AdminController INSTANCE = null;
    private AdminController() {
    };

    public static AdminController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdminController();
        }
        return (INSTANCE);
    }




}
