package com.example.myapplication;

import static org.junit.Assert.*;

import android.content.Context;

import org.junit.Test;

public class MainActivityTest {
    @Test
    public void onLoginWithValidUsernameandPassword() {
        Context appContext = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().getTargetContext();

        MainActivity LC= new MainActivity();
        String username="yuze12@hotmail.com";
        String password="12345678Y";
        boolean Login= LC.onLogin(username,password);

        assertEquals(true, Login);

    }

    @Test
    public void onLoginWithValidUsernameandInvalidPassword() {
        Context appContext = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().getTargetContext();

        LoginController LC= new LoginController();
        String username="S0099922N";
        String password="1234567890";
        boolean onLogin= LC.validateLogin(username,password, appContext.getApplicationContext());

        assertEquals(false, onLogin);

    }

}