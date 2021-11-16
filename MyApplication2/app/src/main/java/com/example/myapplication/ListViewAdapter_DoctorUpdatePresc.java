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

public class ListViewAdapter_DoctorUpdatePresc extends ArrayAdapter<Prescribed> {
    ArrayList<Prescribed> presc;
    boolean isOnTextChanged;
    int checkAccumulator;
    ArrayList<Prescribed> array;
    private ListViewAdapter_DoctorUpdatePresc.CheckboxCheckListner checkedListner;

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

        TextView tv_prescribed = (TextView) convertview.findViewById(R.id.tvv_prescribed);
        TextView tv_drugname = (TextView) convertview.findViewById(R.id.tvv_drugname);
        TextView tv_quantity = (TextView) convertview.findViewById(R.id.tvv_quantity);
        TextView tv_date = convertview.findViewById(R.id.date1);
        ImageButton imgbtn = convertview.findViewById(R.id.imageButton11);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedListner != null)
                {
                    checkedListner.getCheckBoxCheckedListner(position);

                }

            }
        });


        tv_prescribed.setText(String.valueOf(presc1.isPrescribed));
        tv_drugname.setText(presc1.getDrugName());
        tv_quantity.setText(String.valueOf(presc1.getQuantity()));
        tv_date.setText(presc1.getDate());





        return convertview;
    }
    public interface CheckboxCheckListner
    {
        void getCheckBoxCheckedListner(int position);
    }

    public void setCheckedListner(ListViewAdapter_DoctorUpdatePresc.CheckboxCheckListner checkedListner)
    {
        this.checkedListner = checkedListner;
    }



}
