package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    String User_roles;

    //Signup button event
//    public void Registration(View view)
//    {
//        EditText Name_input = (EditText) findViewById(R.id.name_input);
//        EditText Email_input = (EditText) findViewById(R.id.email_input);
//        EditText Pw_input = (EditText) findViewById(R.id.pw_input);
//        EditText Pwconfirm_input = (EditText) findViewById(R.id.pw_confirmText);
//        EditText Phone_input = (EditText) findViewById(R.id.phone_input);
//        String name_input = Name_input.getText().toString();
//        String email_input = Email_input.getText().toString();
//        String pw_input = Pw_input.getText().toString();
//        int phone_input = Integer.parseInt(Phone_input.getText().toString());
//
//        Log.i("", name_input);
//        Log.i("", email_input);
//        Log.i("", pw_input);
//
//        Log.i("", User_roles);
//
//        BasicInfo User = new BasicInfo(name_input, email_input, pw_input, phone_input, User_roles);
//        if(User_roles == "Patient")
//        		{
//        			return new Patient(name_input, email_input, pw_input, phone_input, User_roles);
//        		}
//        		else if(User_roles == "Doctor")
//        		{
//        			return new Doctor(name_input, email_input, pw_input, phone_input, User_roles);
//        		}
//        		else if(User_roles == "Admin")
//        		{
//            		return new Admin(name_input, email_input, pw_input, phone_input, User_roles);
//        		}
//                else if(User_roles == "Pharmacist")
//        		{
//           		return new Pharmacist(name_input, email_input, pw_input, phone_input, User_roles);
//        		}
//        	}
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        //Adding an Array into the dropdown box(spinner)
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
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
        //Toast.makeText(parent.getContext(), User_roles, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //events for dropbox -----------------------------------


}



