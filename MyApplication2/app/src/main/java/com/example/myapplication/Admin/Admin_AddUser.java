package com.example.myapplication.Admin;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DatabaseManager;
import com.example.myapplication.R;
import com.example.myapplication.RegisterEntity;

public class Admin_AddUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DatabaseManager dbManager;
    EditText addname , addemail, addphonenum, addpassword;
    Spinner addrole;
    RegisterEntity entity;
    Button create_btn;
    String user_role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_user);
        addname = (EditText) findViewById(R.id.add_name);
        addemail = (EditText) findViewById(R.id.add_email);
        addpassword = (EditText) findViewById(R.id.add_password);
        addphonenum = (EditText) findViewById(R.id.add_phonenum);
        addrole = (Spinner) findViewById(R.id.add_role);
        create_btn = findViewById(R.id.create_btn);
        AdminController RegObject = AdminController.getINSTANCE();

        dbManager = new DatabaseManager(Admin_AddUser.this);
        try{
            dbManager.open();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Admin_AddUser.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.roles));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addrole.setAdapter(myAdapter);
        addrole.setOnItemSelectedListener(Admin_AddUser.this);


        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_input = addname.getText().toString();
                String email_input = addemail.getText().toString();
                String pw_input =  addpassword.getText().toString();
                String phone_input = addphonenum.getText().toString();
                //Intent intent = new Intent(Admin_AddUser.this, Admin_Main.class);
                validateName(name_input);
                validatePassword(pw_input);
                validatePhoneNo(phone_input);
                validateEmailAddress(email_input);



                if(validateName(name_input) && validatePassword(pw_input) && validatePhoneNo(phone_input) && validateEmailAddress(email_input)) {

                    RegObject.AddUser(name_input, email_input, pw_input, Integer.parseInt(phone_input), user_role, Admin_AddUser.this);

                    Toast.makeText(Admin_AddUser.this, "User Inserted", Toast.LENGTH_SHORT).show();
                    emptyInputEditText();
                }
                return;
            }
        });




    }
    //events for the dropbox ----------------------------
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //get the value select from dropbox
        user_role = parent.getItemAtPosition(position).toString();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //empty the text field
    private void emptyInputEditText() {
        addname.setText("");
        addemail.setText("");
        addphonenum.setText("");
        addpassword.setText("");
    }


    //validation
    public boolean validateEmailAddress(String email)
    {
        String emailInput = email;

        if(!emailInput.equals("") && !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) //Invalid Email
        {
            addemail.setError("Wrong email Format");
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
            addname.setError("Name cannot be empty");
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
            addpassword.setError("Password cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            addpassword.setError("Password must include at least one uppercase letter, no white spaces and at least 8 characters");
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
            addphonenum.setError("Field cannot be empty");
            return false;
        }
        else if (!val.matches(passwordVal)) {
            addphonenum.setError("Please a valid phone number format");
            return false;
        }
        else {
            return true;
        }
    }



}