package com.example.chandan.connectivitymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WifiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);


        TextView textViewWifiDetails = (TextView)findViewById(R.id.textViewWifiDetails);
        if(getApplicationContext()!=null) {
            new WifiManagerImplementation(getApplicationContext(), textViewWifiDetails).execute();
        }
    }
}
