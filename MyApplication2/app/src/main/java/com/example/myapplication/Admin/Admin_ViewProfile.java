package com.example.myapplication.Admin;

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

public class Admin_ViewProfile extends AppCompatActivity {
    TextView admin_name, admin_email, admin_password, admin_role, admin_phoneno;
    Button btn_update;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    String changes;
    ImageButton edit_name, edit_email, edit_password, edit_phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_profile);

        admin_name = findViewById(R.id.admin_name);
        admin_email = findViewById(R.id.admin_email);
        admin_password = findViewById(R.id.admin_password);
        admin_role = findViewById(R.id.admin_role);
        admin_phoneno = findViewById(R.id.admin_phoneno);


        edit_name = findViewById(R.id.editbtn1);
        edit_password = findViewById(R.id.editbtn4);
        edit_phoneno = findViewById(R.id.editbtn3);

        AdminController adminController = AdminController.getINSTANCE();



        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        refrence_.orderByChild("email").equalTo(user.getEmail());


        refrence_.addValueEventListener(new ValueEventListener()
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
                        admin_name.setText(userinfo.getName());
                        admin_email.setText((userinfo.getEmail()));
                        admin_password.setText(userinfo.getPassword());
                        admin_phoneno.setText(String.valueOf(userinfo.getPhonenumber()));
                        admin_role.setText((userinfo.getRole()));
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
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Admin_ViewProfile.this);
                //AlertDialog dialog = mydialog.create();
                final EditText input = new EditText(Admin_ViewProfile.this);
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
                                adminController.validateUpdateName(changes, getApplicationContext());
                                admin_name.setText(changes);
                                Intent intent = new Intent(Admin_ViewProfile.this, Admin_ViewProfile.class);
                                startActivity(intent);
                            }
                    }
                });
            }
        });



        edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Admin_ViewProfile.this);
                final EditText input = new EditText(Admin_ViewProfile.this);
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
                            adminController.validateUpdatePassword(changes, getApplicationContext());
                            admin_password.setText(changes);
                            Intent intent = new Intent(Admin_ViewProfile.this, Admin_ViewProfile.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        edit_phoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(Admin_ViewProfile.this);
                final EditText input = new EditText(Admin_ViewProfile.this);
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

                        if(changes.equals(""))
                        {
                            input.setError("you must enter a text");
                        }
                        else if(!validatePhoneNo(changes))
                        {
                            input.setError("Please a valid phone number format. Hint: only 8 digit!");
                        }
                        else {
                            adminController.validateUpdateNumber(Integer.parseInt(changes), getApplicationContext());
                            admin_phoneno.setText(changes);
                            Intent intent = new Intent(Admin_ViewProfile.this, Admin_ViewProfile.class);
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


//    public void open_dialog(View v)
//    {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View row = inflater.inflate(R.layout.activity_admin_update_info,null);
//
//        builder.setView(row);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

}