package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class Doctor_AssignMedicine extends AppCompatActivity implements SearchView.OnQueryTextListener
{
    EditText filter_text;
    ListView listview_med;
    ArrayAdapter<String> adapter;
    ArrayList<String> medlist = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_assign_medicine);
        listview_med = findViewById(R.id.ListViewMed);
        filter_text = (EditText) findViewById(R.id.filter_text);

        for (int i = 1; i < 50; i++) {
            medlist.add("Medicine" + i);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, medlist);
        listview_med.setAdapter(adapter);

        filter_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (Doctor_AssignMedicine.this).adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.assignmed_menu, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_done) {
            String itemSelected = "Selected items: \n";
            for (int i = 0; i < listview_med.getCount(); i++) {
                if (listview_med.isItemChecked(i)) {
                    itemSelected += listview_med.getItemAtPosition(i) + "\n";
                }
            }
            Toast.makeText(this, itemSelected, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
