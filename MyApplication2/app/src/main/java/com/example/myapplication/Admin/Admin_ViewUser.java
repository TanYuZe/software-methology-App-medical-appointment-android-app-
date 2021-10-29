package com.example.myapplication.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Admin_ViewUser extends AppCompatActivity {

    Button View_btn;
    TextView t_name;
    TextView t_role;
    TextView t_password;
    TextView t_email;
    TextView t_phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);

        t_name = findViewById(R.id.t_name);
        t_role = findViewById(R.id.t_role);
        t_password = findViewById(R.id.t_password);
        t_email = findViewById(R.id.t_email);
        t_phoneno = findViewById(R.id.t_phoneno);

        View_btn = findViewById(R.id.ViewAcc_btn);

        View_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}