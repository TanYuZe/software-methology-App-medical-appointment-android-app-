package com.example.myapplication.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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



        changepw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm_pw.getText().toString().equals(change_pw.getText().toString()) && confirm_pw.getText().length()>=8){

                    Intent intent=new Intent(Admin_ChangePassword.this,Admin_Main.class);
                    startActivity(intent);
                }
                else if(change_pw.getText().toString().length()<8){
                    Toast.makeText(Admin_ChangePassword.this, "Password need to be 8 letter long", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Admin_ChangePassword.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }

            }
        });





    }
}