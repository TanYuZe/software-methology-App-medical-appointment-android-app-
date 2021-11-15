package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewAdapter_Admin_ViewUsers extends ArrayAdapter<BasicInfo>
{

    public ListViewAdapter_Admin_ViewUsers(Context context, ArrayList<BasicInfo> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, @Nullable View convertview, ViewGroup parent) {

        if(convertview == null) {
            convertview = LayoutInflater.from(getContext()).inflate(R.layout.listview_phar_viewuserpresc,
                    parent, false);
        }


        BasicInfo basicInfo = getItem(position);

        TextView tvName = (TextView) convertview.findViewById(R.id.Admin_User_name);
        TextView tvUID = (TextView) convertview.findViewById(R.id.Admin_User_UID);
        TextView tvContact = (TextView) convertview.findViewById(R.id.Admin_User_Contact);
        TextView tvEmail = (TextView) convertview.findViewById(R.id.Admin_User_Email);

        tvName.setText(basicInfo.getName());
        tvUID.setText(basicInfo.getId());
        tvContact.setText(String.valueOf(basicInfo.getPhonenumber()));
        tvEmail.setText(basicInfo.getEmail());

        return convertview;

    };
}
