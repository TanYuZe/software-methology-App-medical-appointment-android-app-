package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter_PatientViewPres extends ArrayAdapter<Prescribed>
{
    ArrayList<Prescribed> array;

    public ListViewAdapter_PatientViewPres(Context context,ArrayList<Prescribed> objects) {
        super(context, 0, objects);
        array = new ArrayList<Prescribed>();
    }

    public View getView(int position, @Nullable View convertview, ViewGroup parent) {

        if (convertview == null) {
            convertview = LayoutInflater.from(getContext()).inflate(R.layout.listview_patient_viewprescription,
                    parent, false);
        }


        Prescribed presc1 = getItem(position);

        TextView tv_drugID = (TextView) convertview.findViewById(R.id.TheMedicationID);
        TextView tV_isPrescribed = (TextView) convertview.findViewById(R.id.PrescribedYet);
        TextView tV_quantity = (TextView) convertview.findViewById(R.id.MedicineQuantity);


        tv_drugID.setText(presc1.getDrugID().toString());
        tV_isPrescribed.setText(String.valueOf(presc1.isPrescribed()));
        tV_quantity.setText(String.valueOf(presc1.getQuantity()));

        return convertview;
    }
}
