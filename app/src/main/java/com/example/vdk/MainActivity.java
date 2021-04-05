package com.example.vdk;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
Button tren, duoi, trai,phai;
ImageView wifi;
TextView speed,battery;
Switch onoff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        tren=findViewById(R.id.tren);
        duoi=findViewById(R.id.duoi);
        trai=findViewById(R.id.trai);
        phai=findViewById(R.id.phai);
        wifi=findViewById(R.id.nuttron);
        speed=findViewById(R.id.httocdo);
        battery=findViewById(R.id.htpin);
        onoff=findViewById(R.id.onoff);
       mDatabase.child("wifi").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               boolean value=dataSnapshot.getValue(Boolean.class);
               if (value ==true){
                   wifi.setBackgroundResource(R.drawable.nuttron);

               }
               else
               {
                   wifi.setBackgroundResource(R.drawable.nuttrondo);
               }
           }


           public void onCancelled(DatabaseError databaseError) {

           }
       });
        mDatabase.child("speed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double value=dataSnapshot.getValue(Double.class);
                speed.setText(String.valueOf(value));
            }


            public void onCancelled(DatabaseError databaseError) {


            }
        });
        mDatabase.child("battery").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double value=dataSnapshot.getValue(Double.class);
                battery.setText(String.valueOf(value));
            }


            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("move").setValue("up");
            }
        });
        duoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("move").setValue("down");
            }
        });
        trai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("move").setValue("left");
            }
        });
        phai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("move").setValue("right");
            }
        });
        onoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


    }
}