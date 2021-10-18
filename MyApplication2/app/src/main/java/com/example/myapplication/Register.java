package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    String User_roles;

    DatabaseManager dbManager;
    EditText Name_input;
    EditText Email_input;
    EditText Pw_input;
    EditText Pwconfirm_input;
    EditText Phone_input;


    public void doTheFetch()
    {
        Cursor cursor = dbManager.fetch();
        if(cursor.moveToFirst())
        {
            do
            {
                String ID = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_ID));
                String FullName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_FULLNAME));

                Log.i("DATABASE_Tag" , "I have inserted ID: " + ID + ", Name: " + FullName);
            }while(cursor.moveToNext());
        }

    }
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
        dbManager = new DatabaseManager(this);
        try{
            dbManager.open();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

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


    //Signup button event
    public void Registration(View view)
    {
        String name_input = Name_input.getText().toString();
        String email_input = Email_input.getText().toString();
        String pw_input = Pw_input.getText().toString();
        String pwConfirm_input = Pwconfirm_input.getText().toString();
        int phone_input = Integer.parseInt(Phone_input.getText().toString());

        if(pw_input.equals(pwConfirm_input))
        {
            //BasicInfo User = new BasicInfo(name_input, email_input, pw_input, phone_input, User_roles);
            switch (User_roles)
            {
                case "Patient":
                    dbManager.insert(name_input, email_input, pw_input, phone_input, User_roles);
                    doTheFetch();
                    break;
                case "Doctor":
                    dbManager.insert(name_input, email_input, pw_input, phone_input, User_roles);
                    doTheFetch();
                    break;

                    case "Admin":
                dbManager.insert(name_input, email_input, pw_input, phone_input, User_roles);
                doTheFetch();
                break;

                case "Pharmacist":
                dbManager.insert(name_input, email_input, pw_input, phone_input, User_roles);
                doTheFetch();
                break;

            }
        }
        else
        {
            Toast.makeText(this, "Password does not match." ,Toast.LENGTH_SHORT).show();
            Log.i("",pw_input);
            Log.i("",pwConfirm_input);
        }

    }

    //events for dropbox -----------------------------------


}



