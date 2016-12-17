package com.example.chandan.connectivitymanager;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;

import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by chandan on 30/11/2016.
 */

public class TelephonyManagerImplementation extends AsyncTask<Void,Void,Void> {

    private static Logger logger = Logger.getLogger(TelephonyManagerImplementation.class.getName());

    private Context mContext;
    private TextView textViewMobileDetails;

    private int cellID;
    private int locationAreaCode;
    private int primaryScramblingCode;
    private int mobileCountryCode;
    private int mobileNetworkCode;

    public TelephonyManagerImplementation(Context mContext, TextView textViewMobileDetails) {
        this.mContext = mContext;
        this.textViewMobileDetails = textViewMobileDetails;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        checkMobileConnection();
        return null;
    }

    public void checkMobileConnection(){

        TelephonyManager telephonyManager = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            CellLocation cellLocation = telephonyManager.getCellLocation();
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                cellID = gsmCellLocation.getCid();
                locationAreaCode = gsmCellLocation.getLac();
                primaryScramblingCode = gsmCellLocation.getPsc();
            }
        }catch (Exception e){
            logger.log(Level.SEVERE,e.getMessage());
        }

        String networkOperator = telephonyManager.getNetworkOperator();
        if(!networkOperator.isEmpty()){
            mobileCountryCode = Integer.parseInt(networkOperator.substring(0,3));
            mobileNetworkCode = Integer.parseInt(networkOperator.substring(3));
        }

        //TODO We are trying to use OpenCellID to generate the Cell location in terms of latitude and longitude which requires
        //TODO an API Key. We tried to register for the same but registration is failing for some unknown reason.
        //TODO We are commenting out the respective section until we throubleshoot this issue.
        /*
        OpenCellID Experiment Begin

        String urlForOpenCellID="http://www.opencellid.org/cell/get?mcc="+mobileCountryCode
                +"&mnc="+mobileNetworkCode
                +"&cellid="+cellID
                +"&lac="+locationAreaCode
                +"&fmt=txt";

        try {
            getOpenCellID(urlForOpenCellID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Experiment End
         */

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {

                //int neighboringCellInfoIndex = 1;
                textViewMobileDetails.setText("");
                textViewMobileDetails.append("Telephony Manager-DataState: "+telephonyManager.getDataState()+System.getProperty("line.separator"));
                textViewMobileDetails.append("Telephony Manager-PhoneType: "+telephonyManager.getPhoneType()+System.getProperty("line.separator"));
                textViewMobileDetails.append("Telephony Manager-NetworkType: "+telephonyManager.getNetworkType()+System.getProperty("line.separator"));
                textViewMobileDetails.append("Telephony Manager-SimOperatorName: "+telephonyManager.getSimOperatorName()+System.getProperty("line.separator"));
                textViewMobileDetails.append("Telephony Manager-NetworkOperatorName: "+telephonyManager.getNetworkOperatorName()+System.getProperty("line.separator"));

                textViewMobileDetails.append("GsmCellLocation-CellID: "+cellID+System.getProperty("line.separator"));
                textViewMobileDetails.append("GsmCellLocation-Location Area Code: "+locationAreaCode+System.getProperty("line.separator"));
                textViewMobileDetails.append("GsmCellLocation-Primary Scrambling Code: "+primaryScramblingCode+System.getProperty("line.separator"));

                textViewMobileDetails.append("Telephony Manager-SIMCountryISO: "+telephonyManager.getSimCountryIso()+System.getProperty("line.separator"));

                textViewMobileDetails.append("Telephony Manager-Mobile Country Code(MCC): "+mobileCountryCode+System.getProperty("line.separator"));
                textViewMobileDetails.append("Telephony Manager-Mobile Network Code(MNC): "+mobileNetworkCode+System.getProperty("line.separator"));

                //TODO Investigate whether getAllCellInfo() can be a substitute of Deprecated Method getNeighboringCellInfo().
                //TODO Use of getNeighboringCellInfo() method is not recommended and it is causing security exception resulting in application crash.
                /*
                      java.lang.SecurityException: getNeighboringCellInfo: Neither user 10182 nor current process has android.permission.ACCESS_COARSE_LOCATION.
                      at android.os.Parcel.readException(Parcel.java:1620)
                      at android.os.Parcel.readException(Parcel.java:1573)
                      at com.android.internal.telephony.ITelephony$Stub$Proxy.getNeighboringCellInfo(ITelephony.java:2503)
                 */
                /*
                for(NeighboringCellInfo neighboringCellInfo: telephonyManager.getNeighboringCellInfo()){

                    textViewMobileDetails.append("Neighboring Cell Info-"+neighboringCellInfoIndex+"Cell ID: "+neighboringCellInfo.getCid()+System.getProperty("line.separator"));
                    textViewMobileDetails.append("Neighboring Cell Info-"+neighboringCellInfoIndex+"Location Area Code: "+neighboringCellInfo.getLac()+System.getProperty("line.separator"));
                    textViewMobileDetails.append("Neighboring Cell Info-"+neighboringCellInfoIndex+"Primary Scrambling Code: "+neighboringCellInfo.getPsc()+System.getProperty("line.separator"));
                    textViewMobileDetails.append("Neighboring Cell Info-"+neighboringCellInfoIndex+"Rssi: "+neighboringCellInfo.getRssi()+System.getProperty("line.separator"));
                    textViewMobileDetails.append("Neighboring Cell Info-"+neighboringCellInfoIndex+"Network Type"+neighboringCellInfo.getNetworkType()+System.getProperty("line.separator"));
                    neighboringCellInfoIndex++;
                }
                */

            }
        });
    }

    /*
    Trying OpenCellID


    public void getOpenCellID(String url) throws Exception{

        URL url1 = new URL(url);

        HttpURLConnection httpURLConnection = (HttpURLConnection)url1.openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        logger.info("Data is: "+inputStreamReader.read());

    }
    Open CellID End
     */
}
