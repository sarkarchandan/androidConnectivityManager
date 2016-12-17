package com.example.chandan.connectivitymanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;

import java.util.logging.Logger;

/**
 * Created by chandan on 29/11/2016.
 */

public class ConnecitivityManagerImplementation extends AsyncTask<Void,Void,Void> {

    private static Logger logger = Logger.getLogger(ConnecitivityManagerImplementation.class.getName());

    private Context myContext;
    private TextView textViewWifi;
    private TextView textViewMobile;
    private ImageView imageViewWifi;
    private ImageView imageViewMobile;
    private Button wifiButton;
    private Button mobileButton;

    public ConnecitivityManagerImplementation(Context myContext, TextView textViewWifi, TextView textViewMobile, ImageView imageViewWifi, ImageView imageViewMobile, Button wifiButton, Button mobileButton) {
        this.myContext = myContext;
        this.textViewWifi = textViewWifi;
        this.textViewMobile = textViewMobile;
        this.imageViewWifi = imageViewWifi;
        this.imageViewMobile = imageViewMobile;
        this.wifiButton = wifiButton;
        this.mobileButton = mobileButton;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        checkConnection();
        return null;
    }

    public void checkConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        logger.info("Connectivity Manager object created");
        if(connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isAvailable()){

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            logger.info("We have got network information");

            if(networkInfo!=null){
                if(networkInfo.getType()==connectivityManager.TYPE_WIFI){
                    //If connected to wifi

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            Toast.makeText(myContext,networkInfo.getTypeName(),Toast.LENGTH_LONG).show();
                            textViewMobile.setVisibility(View.INVISIBLE);
                            textViewWifi.setVisibility(View.VISIBLE);
                            imageViewMobile.setVisibility(View.INVISIBLE);
                            imageViewWifi.setVisibility(View.VISIBLE);
                            mobileButton.setVisibility(View.INVISIBLE);
                            wifiButton.setVisibility(View.VISIBLE);
                            imageViewWifi.setImageResource(R.drawable.wifi);
                            textViewWifi.setText("");
                            textViewWifi.append("Network Type: "+networkInfo.getTypeName()+System.getProperty("line.separator"));
                            textViewWifi.append("Network Name: "+networkInfo.getExtraInfo()+System.getProperty("line.separator"));
                            textViewWifi.append("Network Status: "+networkInfo.getState().name()+System.getProperty("line.separator"));
                        }
                    });
                }else if(networkInfo.getType()==connectivityManager.TYPE_MOBILE){
                    //If connected to mobile data

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            Toast.makeText(myContext,networkInfo.getTypeName(),Toast.LENGTH_LONG).show();
                            textViewWifi.setVisibility(View.INVISIBLE);
                            textViewMobile.setVisibility(View.VISIBLE);
                            imageViewWifi.setVisibility(View.INVISIBLE);
                            imageViewMobile.setVisibility(View.VISIBLE);
                            wifiButton.setVisibility(View.INVISIBLE);
                            mobileButton.setVisibility(View.VISIBLE);
                            imageViewMobile.setImageResource(R.drawable.mobile);
                            textViewMobile.setText("");
                            textViewMobile.append("Network Type: "+networkInfo.getTypeName()+System.getProperty("line.separator"));
                            textViewMobile.append("Network SubType: "+networkInfo.getSubtypeName()+System.getProperty("line.separator"));
                            textViewMobile.append("Network Name: "+networkInfo.getExtraInfo()+System.getProperty("line.separator"));
                            textViewMobile.append("Network Status: "+networkInfo.getState().name()+System.getProperty("line.separator"));
                        }
                    });
                }else if(!networkInfo.isConnected()){
                    imageViewWifi.setImageResource(R.drawable.stoneage);
                    logger.info("Not Connected to Internet");
                }
            }
        }
    }

}
