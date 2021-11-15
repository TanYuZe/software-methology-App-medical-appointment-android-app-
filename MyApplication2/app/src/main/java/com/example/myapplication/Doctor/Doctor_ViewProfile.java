package com.example.myapplication.Doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BasicInfo;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Doctor_ViewProfile extends AppCompatActivity {
    TextView doc_name, doc_email, doc_password, doc_role, doc_phoneno;
    Button btn_update, btn_changepass;
    ImageButton edit_name, edit_email, edit_password, edit_phoneno;
    String changes;
    FirebaseDatabase rootNode_;
    DatabaseReference reference_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_profile);
        doc_name = findViewById(R.id.doc_name);
        doc_email = findViewById(R.id.doc_email);
        doc_password = findViewById(R.id.doc_password);
        doc_role = findViewById(R.id.doc_role);
        doc_phoneno = findViewById(R.id.doc_phoneno);


        edit_name = findViewById(R.id.imageButton2);
        edit_password = findViewById(R.id.imageButton3);
        edit_phoneno = findViewById(R.id.imageButton4);




        DoctorController doctorController = DoctorController.getINSTANCE();




        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        reference_ = rootNode_.getReference("Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        reference_.orderByChild("email").equalTo(user.getEmail());


        reference_.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    BasicInfo userinfo = snapshot1.getValue(BasicInfo.class);

                    if(userinfo.getId().equals(user.getUid()) )
                    {
                        //maxID = snapshot.getChildrenCount();
                        doc_name.setText(userinfo.getName());
                        doc_email.setText((userinfo.getEmail()));
                        doc_password.setText(userinfo.getPassword());
                        doc_phoneno.setText(String.valueOf(userinfo.getPhonenumber()));
                        doc_role.setText((userinfo.getRole()));
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Doctor_ViewProfile.this);
                final EditText input = new EditText(Doctor_ViewProfile.this);
                mydialog.setMessage("Please enter your name");
                mydialog.setTitle("Name Change");
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                mydialog.setView(input);
                mydialog.setPositiveButton("Ok", null);
                mydialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });

                Button positiveButton = mydialog.show().getButton(AlertDialog.BUTTON_POSITIVE);

                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                changes = input.getText().toString();
                                if(changes.equals(""))
                                {
                                    input.setError("you must enter a text");
                                }
                                else {
                                    doctorController.validateUpdateName(changes, getApplicationContext());
                                    doc_name.setText(changes);
                                    Intent intent = new Intent(Doctor_ViewProfile.this, Doctor_ViewProfile.class);
                                    startActivity(intent);
                                }
                            }
                        });
            }
        });



        edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Doctor_ViewProfile.this);
                final EditText input = new EditText(Doctor_ViewProfile.this);
                mydialog.setMessage("Please enter your new password");
                mydialog.setTitle("Password Change");
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                mydialog.setView(input);
                mydialog.setPositiveButton("Ok", null);
                mydialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


                Button positiveButton = mydialog.show().getButton(AlertDialog.BUTTON_POSITIVE);

                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changes = input.getText().toString();

                        if(changes.equals(""))
                        {
                            input.setError("you must enter a text");
                        }
                        else if(!validatePassword(changes))
                        {
                            input.setError("Password must include at least one uppercase letter, no white spaces and at least 8 characters");
                        }
                        else {

                            doctorController.validateUpdatePassword(changes, getApplicationContext());
                            doc_password.setText(changes);
                            Intent intent = new Intent(Doctor_ViewProfile.this, Doctor_ViewProfile.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        edit_phoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Doctor_ViewProfile.this);
                final EditText input = new EditText(Doctor_ViewProfile.this);
                mydialog.setMessage("Please enter your new Phone Number");
                mydialog.setTitle("Phone Number Change");
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                mydialog.setView(input);
                mydialog.setPositiveButton("Ok", null);
                mydialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


                Button positiveButton = mydialog.show().getButton(AlertDialog.BUTTON_POSITIVE);

                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changes = input.getText().toString();

                        if (changes.equals("")) {
                            input.setError("you must enter a text");
                        } else if (!validatePhoneNo(changes)) {
                            input.setError("Please a valid phone number format. Hint: only 8 digit!");
                        } else {
                            doctorController.validateUpdateName(Integer.parseInt(changes), getApplicationContext());
                            doc_phoneno.setText(changes);
                            Intent intent = new Intent(Doctor_ViewProfile.this, Doctor_ViewProfile.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
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
            return false;
        } else if (!val.matches(passwordVal)) {
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
            return false;
        }
        else if (!val.matches(passwordVal)) {
            return false;
        }
        else {
            return true;
        }
    }
}