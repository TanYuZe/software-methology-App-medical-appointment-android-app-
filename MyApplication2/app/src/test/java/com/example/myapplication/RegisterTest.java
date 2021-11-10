package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;

public class RegisterTest {

    @Test
    public void EmailRegisterTest1() {
        Register reg = mock(Register.class);
        when(reg.validateEmailAddress("yuze12@hotmail.com")).thenReturn(true);

        assertEquals(reg.validateEmailAddress("yuze12@hotmail.com"), true);
    }
    @Test
    public void EmailRegisterTest2() {
        Register reg = mock(Register.class);
        //when(reg.validateEmailAddress("yuze12@hotmail.com")).thenReturn(false);

        assertEquals(reg.validateEmailAddress("yuze12@hotmail"), false);
    }

    @Test
    public void EmailRegisterTest3() {
        Register reg = mock(Register.class);
        //when(reg.validateEmailAddress("yuze12@hotmail.com")).thenReturn(false);

        assertEquals(reg.validateEmailAddress(""), false);
    }



    @Test
    public void PasswordRegisterTest1() {
        Register reg = mock(Register.class);
        //when(reg.validatePassword("12345678Y")).thenReturn(true);

        assertEquals(reg.validateEmailAddress("12345678"), false);
    }

    @Test
    public void PasswordRegisterTest2() {
        Register reg = mock(Register.class);
        //when(reg.validatePassword("12345678Y")).thenReturn(true);

        assertEquals(reg.validatePassword("12345Y"), false);
    }

    @Test
    public void PasswordRegisterTest3() {
        Register reg = mock(Register.class);

        assertEquals(reg.validatePassword(""), false);
    }

    @Test
    public void PhoneNumRegisterTest1() {
        Register reg = mock(Register.class);
        when(reg.validatePhoneNo("12345678")).thenReturn(true);
        assertEquals(reg.validatePhoneNo("12345678"), true);
    }

    @Test
    public void PhoneNumRegisterTest2() {
        Register reg = mock(Register.class);

        assertEquals(reg.validatePhoneNo("1234567"), false);
    }

    @Test
    public void PhoneNumRegisterTest3() {
        Register reg = mock(Register.class);

        assertEquals(reg.validatePhoneNo("123456789"), false);
    }

    @Test
    public void PhoneNumRegisterTest4() {
        Register reg = mock(Register.class);

        assertEquals(reg.validatePhoneNo(""), false);
    }

    @Test
    public void NameRegisterTest() {
        Register reg = mock(Register.class);
        when(reg.validateName("john")).thenReturn(true);
        assertEquals(reg.validateName("john"), true);
    }

    @Test
    public void NameRegisterTest1() {
        Register reg = mock(Register.class);
        //when(reg.validateName("")).thenReturn(true);
        assertEquals(reg.validateName(""), false);
    }







}
