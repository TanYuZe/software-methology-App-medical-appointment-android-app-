package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Prescription> {


    ArrayList<Prescription> presc;
    boolean isOnTextChanged;
    int checkAccumulator;
    ArrayList<Prescription> array;
    private CheckboxCheckListner checkedListner;

    //private final Context context;



    public ListViewAdapter(Context context, ArrayList<Prescription> presc) {
        super(context, 0, presc);
        // TODO Auto-generated constructor stub
        checkAccumulator = 0;
        array = new ArrayList<Prescription>();



    }





    public View getView(int position, @Nullable View convertview, ViewGroup parent) {

        if(convertview == null) {
            convertview = LayoutInflater.from(getContext()).inflate(R.layout.mylist,
                    parent, false);
        }



        Prescription presc1 = getItem(position);
        TextView titleText = (TextView) convertview.findViewById(R.id.title);
        TextView subtitleText = (TextView) convertview.findViewById(R.id.subtitle);
        EditText q_input = (EditText) convertview.findViewById(R.id.Quantity_input);
        ImageButton btnPlus = convertview.findViewById(R.id.ib_addnew1);
        ImageButton btnMinus = convertview.findViewById(R.id.ib_remove1);
        CheckBox cb = convertview.findViewById(R.id.checkBox);

        q_input.setText(presc1.quantity+"");


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                updateQuantity(position,q_input,1);
            }
        });
        //listViewHolder.edTextQuantity.setText("0");
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(position,q_input,-1);

            }
        });


//        CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(checkListener !=null)
//                {
//                    checkListener.getCheckBoxChecked()
//                }
//
//
//                countCheck(isChecked);
//                Log.i("MAIN", checkAccumulator + "");
//            }
//        };
//
//        cb.setOnCheckedChangeListener(checkListener);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(checkedListner != null)
                {
                    checkedListner.getCheckBoxCheckedListner(position);

                }
            }
        });




//        cb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if(cb.isChecked())
//                {
//
//
//
//
//                }
//                notifyDataSetChanged();
//
//            }
//        });




        titleText.setText(presc1.drugPrescribed);
        subtitleText.setText(presc1.dosage.toString());











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



    private void countCheck(boolean isChecked) {

        checkAccumulator += isChecked ? 1 : -1 ;
    }



    public ArrayList<Prescription> getArray() {
        return array;
    }

    private void updateQuantity(int position, EditText edTextQuantity, int value) {

        Prescription products = getItem(position);
        if(value > 0)
        {
            products.quantity = products.quantity + 1;
        }
        else
        {
            if(products.quantity > 0)
            {
                products.quantity = products.quantity - 1;
            }

        }
        edTextQuantity.setText(products.quantity+"");
    }



}
