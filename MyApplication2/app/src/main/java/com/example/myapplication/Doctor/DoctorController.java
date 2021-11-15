package com.example.myapplication.Doctor;

import android.content.Context;
import android.os.StrictMode;

import androidx.annotation.NonNull;

import com.example.myapplication.Prescribed;
import com.example.myapplication.Prescription;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DoctorController {
    private static DoctorController INSTANCE = null;
    FirebaseDatabase rootNode_;
    DatabaseReference refrence_;
    DatabaseReference refrence_2;
    ArrayList<Prescribed> prescribedArrayList;

    public DoctorController() {
    };

    public static DoctorController getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DoctorController();
        }
        return (INSTANCE);
    }

    public void validateAddMedicine(ArrayList<Prescription> prescriptionArrayList, String email, ArrayList<String> stringArrayList, Context context)
    {
        rootNode_ = FirebaseDatabase.getInstance("https://csci314-3846f-default-rtdb.asia-southeast1.firebasedatabase.app");
        refrence_ = rootNode_.getReference("Users");
        refrence_2 = rootNode_.getReference("Prescribed");
        prescribedArrayList = new ArrayList<Prescribed>();

        final String username = "triple7veryvalidemail@gmail.com";
        final String password = "!gundamzero1";

        final Long[] maxID = new Long[1];

        for(int i = 0; i < stringArrayList.size(); i++)
        {
            DatabaseReference zaEmail = refrence_.child((stringArrayList.get(i))).child("email");
            int finalI = i;
            zaEmail.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    String emailed = snapshot.getValue(String.class);
                    if(emailed.equals(email))
                    {
                        for(int j = 0; j < prescriptionArrayList.size(); j++)
                        {
                            DatabaseReference UID = refrence_.child(stringArrayList.get(finalI));
                            int finalJ = j;
                            UID.addListenerForSingleValueEvent(new ValueEventListener()
                            {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot2)
                                {
                                    Date currentTime = Calendar.getInstance().getTime();

                                    final String[] sUID = {snapshot2.getKey() + "_" + String.valueOf(finalI)};

                                    Prescribed newPrescribed = new Prescribed();
                                    newPrescribed.set_ID(snapshot2.getKey());
                                    newPrescribed.setDrugID(prescriptionArrayList.get(finalJ).getDrugId());
                                    newPrescribed.setQuantity(prescriptionArrayList.get(finalJ).getQuantity());
                                    newPrescribed.setDrugName(prescriptionArrayList.get(finalJ).getDrugPrescribed());
                                    newPrescribed.setDate(currentTime.toString());
                                    newPrescribed.setPrescribed(false);
                                    newPrescribed.setToken(snapshot2.child("name").getValue(String.class) + snapshot2.child("email").getValue(String.class));

                                    prescribedArrayList.add(newPrescribed);

                                    String currentTimeS = currentTime.toString();
                                    sUID[0] = snapshot2.getKey() + "_" + currentTimeS;
                                    if(finalJ == prescriptionArrayList.size()-1)
                                    {
                                        refrence_2.child(sUID[0]).setValue(prescribedArrayList);
                                        String finalEmail = emailed;
                                        String subject = "Verification for prescription";
                                        String finalMessage = sUID[0]  + "\n Token is: " + newPrescribed.getToken();
                                        sendEmail(finalEmail, subject, finalMessage, username, password);

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

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
    }

    void sendEmail(String email, String subject, String message, String senderEM, String senderPass)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Properties prop = new Properties();

        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.debug", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(senderEM, senderPass);
                    }
                });


        Message message1 = new MimeMessage(session);
        try
        {
            message1.setFrom(new InternetAddress(senderEM));
            message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message1.setSubject(subject);

            //MimeMultipart mimeMultipart = new MimeMultipart();

            //MimeBodyPart theMessage = new MimeBodyPart();
            //theMessage.setContent(message, "text/plain");

            //mimeMultipart.addBodyPart(theMessage);

            //message1.setContent(mimeMultipart);
            message1.setText(message);

            Transport.send(message1);
        }catch (MessagingException e)
        {
            e.printStackTrace();
        }
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
