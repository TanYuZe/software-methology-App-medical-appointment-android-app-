package com.example.myapplication.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DatabaseManager;
import com.example.myapplication.R;
import com.example.myapplication.RegisterEntity;

public class Admin_AddUser extends AppCompatActivity {
    DatabaseManager dbManager;
    EditText addname , addemail, addphonenum, addpassword, addrole;
    RegisterEntity entity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_user);
        addname = (EditText) findViewById(R.id.add_name);
        addemail = (EditText) findViewById(R.id.add_email);
        addpassword = (EditText) findViewById(R.id.add_password);
        addphonenum = (EditText) findViewById(R.id.add_phonenum);
        addrole = (EditText) findViewById(R.id.add_role);
        entity = new RegisterEntity(this);



    }

    public void CreateUser(View v) {
        String name_input = addname.getText().toString();
        String email_input = addemail.getText().toString();
        String pw_input =  addpassword.getText().toString();
        String role_input = addrole.getText().toString();
        String phone_input = addphonenum.getText().toString();
        Intent intent = new Intent(Admin_AddUser.this, Admin_Main.class);


        entity.addUser(name_input, email_input, pw_input, Integer.parseInt(phone_input), role_input, Admin_AddUser.this);

        Toast.makeText(Admin_AddUser.this, "User Inserted", Toast.LENGTH_SHORT).show();
        emptyInputEditText();
        startActivity(intent);

    }

    private void emptyInputEditText() {
        addname.setText("");
        addemail.setText("");
        addphonenum.setText("");
        addpassword.setText("");
        addrole.setText("");

    }

}