package com.example.chandan.connectivitymanager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appContext=getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewWifi = (TextView)findViewById(R.id.textViewWifi);
        TextView textViewMobile = (TextView)findViewById(R.id.textViewMobile);
        ImageView imageViewWifi = (ImageView)findViewById(R.id.imageViewWifi);
        ImageView imageViewMobile = (ImageView)findViewById(R.id.imageViewMobile);
        Button networkButton = (Button)findViewById(R.id.netWorkButton);
        Button wifiButton = (Button)findViewById(R.id.wifiButton);
        Button mobileButton = (Button)findViewById(R.id.mobileButton);

        networkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(appContext!=null) {
                    new ConnecitivityManagerImplementation(appContext,textViewWifi,textViewMobile,imageViewWifi,imageViewMobile,wifiButton,mobileButton).execute();
                }
            }
        });

        wifiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wifi = new Intent(appContext,WifiActivity.class);
                startActivity(wifi);
            }
        });

        mobileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mobile = new Intent(appContext,MobileActivity.class);
                startActivity(mobile);
            }
        });
    }
}
