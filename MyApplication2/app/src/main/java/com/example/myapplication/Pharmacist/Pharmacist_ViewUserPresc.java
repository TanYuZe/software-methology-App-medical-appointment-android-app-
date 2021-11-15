package com.example.myapplication.Pharmacist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ListViewAdapter_Phar_viewuserpresc;
import com.example.myapplication.Prescribed;
import com.example.myapplication.Prescription;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pharmacist_ViewUserPresc extends AppCompatActivity implements ListViewAdapter_Phar_viewuserpresc.CheckboxCheckListner{
    ListView listview;
    EditText presc_token;
    Button view_presc , btn_scan;
    ListViewAdapter_Phar_viewuserpresc adapter;
    ArrayList<Prescribed> presc;

    FirebaseDatabase rootNode_;
    DatabaseReference reference_;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_view_user_presc);
        presc_token = (EditText) findViewById(R.id.Presc_token);
        view_presc = findViewById(R.id.View_presc);
        btn_scan = findViewById(R.id.btn_Scan);
        listview = findViewById(R.id.listview);
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        reference_ = rootNode_.getReference("Prescribed");
        presc = new ArrayList<Prescribed>();

        adapter = new ListViewAdapter_Phar_viewuserpresc(Pharmacist_ViewUserPresc.this, presc);
//        ArrayAdapter arrayadapter = new ArrayAdapter(Pharmacist_ViewUserPresc.this, android.R.layout.simple_expandable_list_item_1, list);
        listview.setAdapter(adapter);
        adapter.setCheckedListner((ListViewAdapter_Phar_viewuserpresc.CheckboxCheckListner) this);




        view_presc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //query out prescribed tied to the token input from Edittext

                String token = presc_token.getText().toString();

                reference_.orderByChild("_ID");

                reference_.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                            {
                                Prescribed prescribed = dataSnapshot1.getValue(Prescribed.class);
                                if(prescribed.getToken().equals(token) && !prescribed.isPrescribed())
                                {
                                    presc.add(prescribed);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // we need to create the object
                // of IntentIntegrator class
                // which is the class of QR library
                IntentIntegrator intentIntegrator = new IntentIntegrator(Pharmacist_ViewUserPresc.this);
                intentIntegrator.setPrompt("Scan a barcode or QR Code");
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.initiateScan();


            }
        });

    }

    public void getCheckBoxCheckedListner(int position)
    {
        //Write code here to change from false to true
        presc.get(position).setPrescribed(true);


        reference_.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        Prescribed prescribed = dataSnapshot1.getValue(Prescribed.class);

                        if(presc.isEmpty())
                        {
                            break;
                        }
                        else if(presc.get(position).get_ID().equals(prescribed.get_ID())
                            && presc.get(position).getDrugID().equals(prescribed.getDrugID()))
                        {
                            //reference_.child(dataSnapshot.getKey()).child(dataSnapshot1.getKey()).child("prescribed");

                            Map<String, Object> updatePrescribed = new HashMap<>();
                            updatePrescribed.put(dataSnapshot.getKey() + "/" + dataSnapshot1.getKey() + "/prescribed", true);
                            reference_.updateChildren(updatePrescribed);

                            presc.remove(presc.get(position));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }






    //Qr scanner code
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                // if the intentResult is not null we'll set
                // the content and format of scan message
                presc_token.setText(intentResult.getContents());
                //messageFormat.setText(intentResult.getFormatName());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}