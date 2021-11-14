package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewAdapter_DoctorUpdatePresc extends ArrayAdapter<Prescribed> {
    ArrayList<Prescribed> presc;
    boolean isOnTextChanged;
    int checkAccumulator;
    ArrayList<Prescribed> array;
    private ListViewAdapter.CheckboxCheckListner checkedListner;

    //private final Context context;



    public ListViewAdapter_DoctorUpdatePresc(Context context, ArrayList<Prescribed> presc) {
        super(context, 0, presc);
        // TODO Auto-generated constructor stub
        checkAccumulator = 0;
        array = new ArrayList<Prescribed>();
    }

    public View getView(int position, @Nullable View convertview, ViewGroup parent) {

        if (convertview == null) {
            convertview = LayoutInflater.from(getContext()).inflate(R.layout.listview_doctor_updatepresc,
                    parent, false);
        }


        Prescribed presc1 = getItem(position);

        EditText et_drugid = (EditText) convertview.findViewById(R.id.et_drugid);
        EditText q_input = (EditText) convertview.findViewById(R.id.et_quantity);


        et_drugid.setText(presc1.getDrugID().toString());
        q_input.setText(presc1.getQuantity());





        return convertview;
    }



}
