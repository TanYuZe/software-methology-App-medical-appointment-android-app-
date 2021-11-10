package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class LoginTest {
    @Test
    public void Loginwithvalid() {
        MainActivity login = mock(MainActivity.class);
        when(login.onLogin("yuze12@hotmail.com","12345678Y")).thenReturn(true);

        assertEquals(login.onLogin("yuze12@hotmail.com","12345678Y"),true);



    }
    @Test
    public void LoginwithInvalid() {
        MainActivity login = mock(MainActivity.class);
        when(login.onLogin("yuze12@hotmail.com","12345678Y")).thenReturn(true);

        assertEquals(login.onLogin("yuze12@coldmail.com","1234dsdsds"),false);



    }

    @Test
    public void LoginWithSpacings() {
        MainActivity login = mock(MainActivity.class);
        when(login.onLogin("yuze12@hotmail.com","12345678Y")).thenReturn(true);

        assertEquals(login.onLogin("yuze12@hotmail.com ","12345678 "),false);


    }


    @Test
    public void LoginWithEmpty() {
        MainActivity login = mock(MainActivity.class);
        when(login.onLogin("yuze12@hotmail.com","12345678Y")).thenReturn(true);

        assertEquals(login.onLogin("",""),false);


    }



}
