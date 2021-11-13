package com.example.myapplication.Doctor;


import android.provider.ContactsContract;
import android.view.animation.ScaleAnimation;


import androidx.annotation.NonNull;

import com.example.myapplication.Prescribed;
import com.example.myapplication.Prescription;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

                                    Prescribed newPrescribed = new Prescribed();
                                    newPrescribed.set_ID(snapshot2.getKey());
                                    newPrescribed.setDrugID(prescriptionArrayList.get(finalJ).getDrugId());
                                    newPrescribed.setQuantity(prescriptionArrayList.get(finalJ).getQuantity());
                                    newPrescribed.setPrescribed(false);

                                    prescribedArrayList.add(newPrescribed);

                                    Date currentTime = Calendar.getInstance().getTime();
                                    String currentTimeS = currentTime.toString();
                                    sUID[0] = snapshot2.getKey() + "_" + currentTimeS;
                                    if(finalJ == prescriptionArrayList.size()-1)
                                    {
                                        refrence_2.child(sUID[0]).setValue(prescribedArrayList);
                                    }
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
