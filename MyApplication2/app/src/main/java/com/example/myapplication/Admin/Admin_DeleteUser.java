package com.example.myapplication.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Admin_DeleteUser extends AppCompatActivity {
    EditText user_email;

    Button delete_btn;

    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;


    DatabaseReference reference_;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_user);
        user_email = (EditText) findViewById(R.id.Email_input);
        delete_btn = findViewById(R.id.DeleteAcc_btn);
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference().child("Users");

        String Useremail = user_email.getText().toString();

//        delete_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_DeleteUser.this);
//                builder.setTitle("Confirm Prescription!").setMessage("Do you want to delete user of " + Useremail);
//                builder.setPositiveButton("Yes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id)
//                            {
//                                DatabaseReference ref1 = refrence_;
//                                ref1.orderByChild("email").equalTo(Useremail);
//                                refrence_.child(Useremail).removeValue();
//
//                            }
//                        });
//                builder.setNegativeButton("No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alert11 = builder.create();
//                alert11.show();
//
//            }
//        });






//    //delete user base on email
//    public void Delete(View v)
//    {
//        String email = user_email.getText().toString().trim();
//    }


    }



}