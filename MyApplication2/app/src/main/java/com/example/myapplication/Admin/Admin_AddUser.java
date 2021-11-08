package com.example.myapplication.Admin;

import android.content.Intent;
import android.os.Bundle;
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
                Intent intent = new Intent(Admin_AddUser.this, Admin_Main.class);


                RegObject.addUser(name_input, email_input, pw_input, Integer.parseInt(phone_input), user_role, Admin_AddUser.this);

                Toast.makeText(Admin_AddUser.this, "User Inserted", Toast.LENGTH_SHORT).show();
                emptyInputEditText();
                startActivity(intent);
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

}