package com.example.myapplication.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Admin_ChangePassword extends AppCompatActivity {

    EditText change_pw, confirm_pw;
    Button changepw_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_password);
        change_pw = findViewById(R.id.new_pw);
        confirm_pw = findViewById(R.id.confirm_pw);
        changepw_btn = findViewById(R.id.btn_cpassword);





    }
}