package com.example.chandan.connectivitymanager;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chandan on 30/11/2016.
 */

public class WifiManagerImplementation extends AsyncTask<Void,Void,Void> {

    private Context mContext;
    private TextView textViewWifiDetails;

    public WifiManagerImplementation(Context mContext, TextView textViewWifiDetails) {
        this.mContext = mContext;
        this.textViewWifiDetails = textViewWifiDetails;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        checkWiFiConnection();
        return null;
    }

    public void checkWiFiConnection(){

        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        List<WifiConfiguration> wifiConfigurationList = wifiManager.getConfiguredNetworks();


        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                int configurationIndex=1;
                textViewWifiDetails.setText("");
                textViewWifiDetails.append("Connection Info-BSSID: "+wifiInfo.getBSSID()+System.getProperty("line.separator"));
                textViewWifiDetails.append("Connection Info-SSID: "+wifiInfo.getSSID()+System.getProperty("line.separator"));
                textViewWifiDetails.append("Connection Info-Rssi: "+wifiInfo.getRssi()+System.getProperty("line.separator"));
                textViewWifiDetails.append("Connection Info-IpAddress: "+wifiInfo.getIpAddress()+System.getProperty("line.separator"));
                textViewWifiDetails.append("Connection Info-MacAddress: "+wifiInfo.getMacAddress()+System.getProperty("line.separator"));
                textViewWifiDetails.append("DHCP Info-dns1: "+wifiManager.getDhcpInfo().dns1+System.getProperty("line.separator"));
                textViewWifiDetails.append("DHCP Info-dns2: "+wifiManager.getDhcpInfo().dns2+System.getProperty("line.separator"));
                textViewWifiDetails.append("DHCP Info-gateway: "+wifiManager.getDhcpInfo().gateway+System.getProperty("line.separator"));
                textViewWifiDetails.append("DHCP Info-ipAddress: "+wifiManager.getDhcpInfo().ipAddress+System.getProperty("line.separator"));
                textViewWifiDetails.append("DHCP Info-leaseDuration: "+wifiManager.getDhcpInfo().leaseDuration+System.getProperty("line.separator"));
                textViewWifiDetails.append("DHCP Info-netmask: "+wifiManager.getDhcpInfo().netmask+System.getProperty("line.separator"));
                textViewWifiDetails.append("DHCP Info-serverAddress: "+wifiManager.getDhcpInfo().serverAddress+System.getProperty("line.separator"));

                for(WifiConfiguration wifiConfiguration:wifiConfigurationList){
                    textViewWifiDetails.append("Configuration-"+configurationIndex+" ConfiguredNetworks-SSID: "+wifiConfiguration.SSID+System.getProperty("line.separator"));
                    textViewWifiDetails.append("Configuration-"+configurationIndex+" ConfiguredNetworks-BSSID: "+wifiConfiguration.BSSID+System.getProperty("line.separator"));
                    textViewWifiDetails.append("Configuration-"+configurationIndex+" ConfiguredNetworks-networkId: "+wifiConfiguration.networkId+System.getProperty("line.separator"));
                    textViewWifiDetails.append("Configuration-"+configurationIndex+" ConfiguredNetworks-priority: "+wifiConfiguration.priority+System.getProperty("line.separator"));
                    configurationIndex++;
                }
            }
        });
    }
}
