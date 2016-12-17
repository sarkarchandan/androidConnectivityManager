package com.example.chandan.connectivitymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MobileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);


        TextView textViewMobileDetails = (TextView)findViewById(R.id.textViewMobileDetails);
        if(getApplicationContext()!=null) {
            new TelephonyManagerImplementation(getApplicationContext(),textViewMobileDetails).execute();
        }
    }
}
