package com.example.myapplication.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText email_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);
        email_input = findViewById(R.id.email_input);
        t_name = findViewById(R.id.t_name);
        t_role = findViewById(R.id.t_role);
        t_password = findViewById(R.id.t_password);
        t_email = findViewById(R.id.t_phoneno);
        t_phoneno = findViewById(R.id.t_roles);

        View_btn = findViewById(R.id.ViewAcc_btn);

        View_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String query = "SELECT * FROM Users WHERE email = '" + email
//                cursor = db.rawQuery(query, null);
//                cursor.moveToFirst();
//                t_role.setText(cursor.getString(1));
//                t_name.setText(cursor.getString(2));
//                t_email.setText(cursor.getString(3));
//                t_password.setText(cursor.getString(4));
//                t_phoneno.setText(cursor.getString(5));



            }
        });
    }

}