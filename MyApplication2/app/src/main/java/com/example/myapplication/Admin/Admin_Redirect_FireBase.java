package com.example.myapplication.Admin;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;


public class Admin_Redirect_FireBase extends AppCompatActivity {
    WebView myWebView;


    DatabaseReference reference_;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_firebase_redirect);
        myWebView = findViewById(R.id.webView);
        myWebView.loadUrl("https://firebase.google.com/");






        //String Useremail = user_email.getText().toString();

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