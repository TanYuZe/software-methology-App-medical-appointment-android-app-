package com.example.myapplication.Admin;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.myapplication.BasicInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AdminEntity {

    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;

    public AdminEntity(Context context) {
    }

    public BasicInfo GetUserInfo(String email)
    {
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");
        refrence_.orderByChild("email").equalTo(email);
        BasicInfo Userinfo = new BasicInfo();

        refrence_.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    BasicInfo userinfo = snapshot1.getValue(BasicInfo.class);
                    Userinfo.setPhonenumber(userinfo.getPhonenumber());
                    Userinfo.setEmail(userinfo.getEmail());
                    Userinfo.setPassword(userinfo.getPassword());
                    Userinfo.setId(userinfo.getId());
                    Userinfo.setName(userinfo.getName());
                    Userinfo.setRole(userinfo.getRole());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return Userinfo;

    }

    public void updateName(String Name)
    {
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        refrence_.child(user.getUid()).orderByChild("email");

        refrence_.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    if(dataSnapshot.getKey().equals(user.getUid()))
                    {
                            Map<String, Object> userUpdates = new HashMap<>();
                            userUpdates.put(user.getUid() + "/name", Name);
                            refrence_.updateChildren(userUpdates);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updatePassword(String Password)
    {
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        refrence_.child(user.getUid()).orderByChild("email");

        refrence_.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    if(dataSnapshot.getKey().equals(user.getUid()))
                    {
                        Map<String, Object> userUpdates = new HashMap<>();
                        userUpdates.put(user.getUid() + "/password", Password);
                        refrence_.updateChildren(userUpdates);

                        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), dataSnapshot.child("password").getValue(String.class));

                        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    user.updatePassword(Password);
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void updateNumber(int Number)
    {
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        refrence_.child(user.getUid()).orderByChild("email");

        refrence_.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    if(dataSnapshot.getKey().equals(user.getUid()))
                    {
                        Map<String, Object> userUpdates = new HashMap<>();
                        userUpdates.put(user.getUid() + "/phonenumber", Number);
                        refrence_.updateChildren(userUpdates);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
