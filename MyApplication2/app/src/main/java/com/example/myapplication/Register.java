package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    RegisterController RegObject = RegisterController.getINSTANCE();

    String User_roles;

    EditText Name_input;
    EditText Email_input;
    EditText Pw_input;
    EditText Pwconfirm_input;
    EditText Phone_input;
    Spinner spinner;

    FirebaseDatabase rootNode;
    DatabaseReference refrence;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name_input = (EditText) findViewById(R.id.name_input);
        Email_input = (EditText) findViewById(R.id.email_input);
        Pw_input = (EditText) findViewById(R.id.pw_input);
        Pwconfirm_input = (EditText) findViewById(R.id.pw_confirmText);
        Phone_input = (EditText) findViewById(R.id.phone_input);

        //Adding an Array into the dropdown box(spinner)
        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.roles));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    //events for the dropbox ----------------------------
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //get the value select from dropbox
        User_roles = parent.getItemAtPosition(position).toString();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //Signup button event
    public void Registration(View view)
    {

        String name_input = Name_input.getText().toString();
        String email_input = Email_input.getText().toString();
        String pw_input = Pw_input.getText().toString();
        String pwConfirm_input = Pwconfirm_input.getText().toString();
        String phonenum = Phone_input.getText().toString();
        int phone_input = Integer.parseInt(Phone_input.getText().toString());
        Intent intent = new Intent(Register.this, MainActivity.class);


        if(validateName(name_input) && validatePassword(pw_input) && validatePhoneNo(phonenum) && validateEmailAddress(email_input))
        {

            if(pw_input.equals(pwConfirm_input))
            {
                RegObject.validateSuccess(email_input, pw_input, name_input, phone_input, User_roles, Register.this);
                startActivity(intent);
                Toast.makeText(this, "Account created! Please Log In", Toast.LENGTH_SHORT).show();
            }
            else{
                Pwconfirm_input.setError("Passwords does not match, please confirm your password again!");
            }
            return;
        }


    }

    //validation
    public boolean validateEmailAddress(String email)
    {
        String emailInput = email;

        if(!emailInput.equals("") && !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) //Invalid Email
        {
            Email_input.setError("Wrong email Format");
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean validateName(String name) {
        String val = name;

        if (val.equals(""))
        {
            Name_input.setError("Name cannot be empty");
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean validatePassword(String password) {
        String val = password;
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 4 characters
                "$";

        if (val.equals("")) {
            Pw_input.setError("Password cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            Pw_input.setError("Password must include at least one uppercase letter, no white spaces and at least 8 characters");
            return false;
        } else {
            return true;
        }
    }
    public Boolean validatePhoneNo(String phonenum) {
        String val = phonenum;
        //String passwordVal = "^\\d{8}$";
        String passwordVal = "^[0-9]{8}$";
//        String passwordVal = "^" +
//                //"(?=.*[0-9])" +         //at least 1 digit
//                //"(?=.*[a-z])" +         //at least 1 lower case letter
//                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                //"(?=.*[a-zA-Z])" +      //any letter
//                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=\\S+$)" +           //no white spaces
//                ".{8}" +               //at least 4 characters
//                "$";
        if (val.equals("")) {
            Phone_input.setError("Field cannot be empty");
            return false;
        }
        else if (!val.matches(passwordVal)) {
            Phone_input.setError("Please a valid phone number format");
            return false;
        }
        else {
            return true;
        }
    }
    //events for dropbox -----------------------------------


}



