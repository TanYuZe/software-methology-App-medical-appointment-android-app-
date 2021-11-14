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

public class ListViewAdapter_Phar_viewuserpresc extends ArrayAdapter<Prescribed> {

    ArrayList<Prescribed> array;
    private ListViewAdapter_Phar_viewuserpresc.CheckboxCheckListner checkedListner;



    public ListViewAdapter_Phar_viewuserpresc(Context context, ArrayList<Prescribed> presc) {
        super(context, 0, presc);
        // TODO Auto-generated constructor stub
        array = new ArrayList<Prescribed>();



    }





    public View getView(int position, @Nullable View convertview, ViewGroup parent) {

        if(convertview == null) {
            convertview = LayoutInflater.from(getContext()).inflate(R.layout.listview_phar_viewuserpresc,
                    parent, false);
        }


        Prescribed presc1 = getItem(position);
        TextView tv_drugid = (TextView) convertview.findViewById(R.id.tv_drugID);
        TextView tv_drugname = (TextView) convertview.findViewById(R.id.tv_medname);
        TextView tv_quantity = (TextView) convertview.findViewById(R.id.tv_quantity);
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


        tv_drugid.setText(String.valueOf(presc1.drugID));
        tv_drugname.setText(String.valueOf(presc1.drugID));
        tv_quantity.setText(presc1.quantity);

        return convertview;

    };

    public interface CheckboxCheckListner
    {
        void getCheckBoxCheckedListner(int position);
    }

    public void setCheckedListner(ListViewAdapter_Phar_viewuserpresc.CheckboxCheckListner checkedListner)
    {
        this.checkedListner = checkedListner;
    }



}
