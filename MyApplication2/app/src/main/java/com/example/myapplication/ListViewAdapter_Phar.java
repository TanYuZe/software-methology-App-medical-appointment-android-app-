package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewAdapter_Phar extends ArrayAdapter<Prescription> {
    ArrayList<Prescription> presc;

    ArrayList<Prescription> array;
    private ListViewAdapter_Phar.CheckboxCheckListner checkedListner;



    public ListViewAdapter_Phar(Context context, ArrayList<Prescription> presc) {
        super(context, 0, presc);
        // TODO Auto-generated constructor stub
        array = new ArrayList<Prescription>();



    }





    public View getView(int position, @Nullable View convertview, ViewGroup parent) {

        if(convertview == null) {
            convertview = LayoutInflater.from(getContext()).inflate(R.layout.listview_phar,
                    parent, false);
        }


        Prescription presc1 = getItem(position);
        TextView titleText = (TextView) convertview.findViewById(R.id.medicinename);
        ImageButton imgbtn = convertview.findViewById(R.id.imageButton1);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedListner != null)
                {
                    checkedListner.getCheckBoxCheckedListner(position);

                }

            }
        });


        titleText.setText(presc1.drugPrescribed);

        return convertview;

    };

    public interface CheckboxCheckListner
    {
        void getCheckBoxCheckedListner(int position);
    }

    public void setCheckedListner(CheckboxCheckListner checkedListner)
    {
        this.checkedListner = checkedListner;
    }









}
