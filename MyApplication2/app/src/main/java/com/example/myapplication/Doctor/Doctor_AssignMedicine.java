package com.example.myapplication.Doctor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BasicInfo;
import com.example.myapplication.Patient.Patient;
import com.example.myapplication.Prescribed;
import com.example.myapplication.Prescription;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doctor_AssignMedicine extends AppCompatActivity
{
    EditText patient_email_text;
    EditText filter_text;
    ListView listview_med;
    Button assign_med_btn;
    ArrayAdapter<String> adapter;
    ArrayAdapter<Prescription> adapter2;
    ArrayList<String> medlist;
    ArrayList<Prescription> drugsSelected;
    ArrayList<Prescription> prescriptionArrayList;
    ArrayList<Prescribed> prescribedArrayList;
    ArrayList<Patient> patientArrayList;
    ArrayList<String> stringArrayList;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    DatabaseReference refrence_2;

    //



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_assign_medicine);
        assign_med_btn = findViewById(R.id.Assignmed_btn);
        patient_email_text = findViewById(R.id.patient_email);
        listview_med = findViewById(R.id.ListViewMed);
        filter_text = (EditText) findViewById(R.id.filter_text);
        medlist = new ArrayList<String>();
        drugsSelected = new ArrayList<Prescription>();
        prescriptionArrayList = new ArrayList<Prescription>();
        prescribedArrayList = new ArrayList<Prescribed>();
        stringArrayList = new ArrayList<String>();
        patientArrayList = new ArrayList<Patient>();

        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Prescription");
        refrence_2 = rootNode_.getReference("Users");

        refrence_.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Prescription prescription;
                    prescription = snapshot1.getValue(Prescription.class);
                    medlist.add(prescription.getDrugPrescribed());
                    prescriptionArrayList.add(prescription);
                    //adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_multiple_choice, medlist);
                    adapter2 = new ArrayAdapter<Prescription>(getApplication(), android.R.layout.simple_list_item_multiple_choice, prescriptionArrayList)
                    {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent)
                        {
                            View view = super.getView(position, convertView, parent);

                            TextView tv = (TextView) view.findViewById(android.R.id.text1);

                            String texto = prescriptionArrayList.get(position).getDrugPrescribed();
                            tv.setText(texto);

                            return view;
                        }
                    };
                    listview_med.setAdapter(adapter2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

        refrence_2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snapshot1: snapshot.getChildren())
                {

                    Patient patient = snapshot1.getValue(Patient.class);
                    stringArrayList.add(snapshot1.getKey());
                    patientArrayList.add(patient);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        filter_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (Doctor_AssignMedicine.this).adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        assign_med_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set and get items from listview checkbox

                for (int i = 0; i < listview_med.getCount(); i++)
                {
                    if (listview_med.isItemChecked(i))
                    {
                        drugsSelected.add(prescriptionArrayList.get(i));
                    }
                }

                if(drugsSelected.size() != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Doctor_AssignMedicine.this);
                    builder.setTitle("Confirm Prescription!");
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id)
                                {
                                    String patientsEmail = patient_email_text.getText().toString();
                                    onAssignMed(drugsSelected, patientsEmail, stringArrayList, getApplicationContext());
                                    finish();
                                    Toast.makeText(Doctor_AssignMedicine.this, "Prescription added to User", Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder.create();
                    alert11.show();
                }
                else
                {
                    Toast.makeText(Doctor_AssignMedicine.this, "No Prescription Assigned to User", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }

    void onAssignMed(ArrayList<Prescription> prescriptionArrayList1, String PatientsEmail, ArrayList<String> stringArrayList, Context context)
    {
        DoctorController doctorController = new DoctorController();
       doctorController.validateAddMedicine(prescriptionArrayList1, PatientsEmail, stringArrayList, context);
    }

    void sendEmail(String email, String subject, String message, Context context)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent, "Chooose an email client"));
    }
}
