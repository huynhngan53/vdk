package com.example.vdk;

import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    Button tren, duoi, trai, phai, stop;
    ImageView wifi;
    TextView speed, battery;
    Switch onoff;

    // hàm chuyển nhị phân sang thập phân
    private int convertBinaryToDecimal(String binaryString) {
        return Integer.parseInt(binaryString, 2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        tren = findViewById(R.id.tren);
        duoi = findViewById(R.id.duoi);
        trai = findViewById(R.id.trai);
        phai = findViewById(R.id.phai);
        stop = findViewById(R.id.stop);
        wifi = findViewById(R.id.nuttron);
        speed = findViewById(R.id.httocdo);
        battery = findViewById(R.id.htpin);
        onoff = findViewById(R.id.onoff);
        mDatabase.child("wifi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean value = dataSnapshot.getValue(Boolean.class);
                if (value == true) {
                    wifi.setBackgroundResource(R.drawable.nuttron);

                } else {
                    wifi.setBackgroundResource(R.drawable.nuttrondo);
                }
            }

            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDatabase.child("speed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double value = dataSnapshot.getValue(Double.class);
                speed.setText(String.valueOf(value));
            }

            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDatabase.child("battery").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//              nhận thập phân (code cũ)
//              double value = dataSnapshot.getValue(Double.class);
//              battery.setText(String.valueOf(value));

//              nhận nhị phân (code mới)
//              nhị phân phải lưu trên firebase dạng string,
//              vì lưu dạng number firebase sẽ hiểu là thập phân và tự bỏ đi số 0 đằng trước
//              lấy chuỗi nhị phân về
                String value = dataSnapshot.getValue(String.class);
//              chuyển nhị phân sang thập phân
                int decimal = convertBinaryToDecimal(value);
//              hiển thị lên text view
                battery.setText(String.valueOf(decimal));
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
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("move").setValue("stop");
            }
        });
        onoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mDatabase.child("active").setValue(isChecked);
            }
        });


    }
}