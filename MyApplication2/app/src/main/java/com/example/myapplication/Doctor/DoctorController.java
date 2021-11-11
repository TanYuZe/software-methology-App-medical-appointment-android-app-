package com.example.myapplication.Doctor;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.example.myapplication.Patient.Patient;
import com.example.myapplication.Prescribed;
import com.example.myapplication.Prescription;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DoctorController {
    private static DoctorController INSTANCE = null;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    DatabaseReference refrence_2;

    ArrayList<Prescribed> prescribedArrayList;

    public DoctorController() {
    };

    public static DoctorController getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DoctorController();
        }
        return (INSTANCE);
    }

    public void validateAddMedicine(ArrayList<Prescription> prescriptionArrayList, String email, ArrayList<String> stringArrayList)
    {
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");
        refrence_2 = rootNode_.getReference("Prescribed");
        prescribedArrayList = new ArrayList<Prescribed>();

        Prescribed newPrescribed = new Prescribed();
        final Long[] maxID = new Long[1];

        for(int i = 0; i < stringArrayList.size(); i++)
        {
            DatabaseReference zaEmail = refrence_.child((stringArrayList.get(i))).child("email");
            int finalI = i;
            zaEmail.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    String emailed = snapshot.getValue(String.class);
                    if(emailed.equals(email))
                    {
                        for(int j = 0; j < prescriptionArrayList.size(); j++)
                        {
                            DatabaseReference UID = refrence_.child(stringArrayList.get(finalI));
                            int finalJ = j;
                            UID.addListenerForSingleValueEvent(new ValueEventListener()
                            {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot2)
                                {
                                    final String[] sUID = {snapshot2.getKey() + "_" + String.valueOf(finalI)};

                                    refrence_2.addListenerForSingleValueEvent(new ValueEventListener()
                                    {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot3)
                                        {
                                            maxID[0] = snapshot3.getChildrenCount();
                                            if(snapshot3.hasChild((snapshot2.getKey() + "_" + finalJ )))
                                            {
                                                sUID[0] = snapshot2.getKey() + "_" + String.valueOf(maxID[0]);

                                                newPrescribed.set_ID(snapshot2.getKey());
                                                newPrescribed.setDrugID(prescriptionArrayList.get(finalJ).getDrugId());
                                                newPrescribed.setQuantity(1);
                                                newPrescribed.setPrescribed(false);

                                                prescribedArrayList.add(newPrescribed);
                                            }
                                            else if(!snapshot3.hasChild((snapshot2.getKey() + "_" + finalJ )))
                                            {
                                                sUID[0] = (snapshot2.getKey() + "_" + finalJ);
                                                newPrescribed.set_ID(snapshot2.getKey());
                                                newPrescribed.setDrugID(prescriptionArrayList.get(finalJ).getDrugId());
                                                newPrescribed.setQuantity(1);
                                                newPrescribed.setPrescribed(false);

                                                prescribedArrayList.add(newPrescribed);
                                            }

                                            refrence_2.child(sUID[0]).setValue(prescriptionArrayList);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }


}
