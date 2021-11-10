package com.example.myapplication.Doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Medicine> {



    public ListViewAdapter(Context context, ArrayList<Medicine> medicine)
    {
        super(context, R.layout.listview_adapter, medicine);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Medicine medicine = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_adapter, parent, false);
        }

        TextView medname = convertView.findViewById(R.id.medname);
        EditText quantity = convertView.findViewById(R.id.quantity_input);
        Button add_btn = convertView.findViewById(R.id.ib_addnew);
        Button minus_btn =convertView.findViewById(R.id.ib_remove);

        medname.setText(medicine.medname);


        return convertView;
    }
}
