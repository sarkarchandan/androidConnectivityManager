# androidConnectivityManager
##### This project is a practice project where I will be trying to explore different aspects of the [ConnectivityManager](https://developer.android.com/reference/android/net/ConnectivityManager.html), [WifiManager](https://developer.android.com/reference/android/net/wifi/WifiManager.html) and [TelephonyManager](https://developer.android.com/reference/android/telephony/TelephonyManager.html). We will analyse the different functionalities that these classes may provide us which can be used in regular android application.
##### This project currently uses two Activity classes apart from the MainActivity. Each of the Activity classes including MainActivity has a helper class which extends from AsyncTask and implement the doInBackground methods to instantiate respective services and dig in for corresponding network related information. Then they print the information in respective TextViews under UI thread.
##### MainActivity has a button which reveals the appropriate network the android device is connected to at any given moment. Any subsequent change in the network could be exposed using the same button. It currently does not use any authentication.
#### In this project we will continue to explore such services provided by Android framework which we can further use to build and use in our applications.
## Build and Deploy
#### This project has been created with Android Studio version 2.2.3 and gradle has been used as the default build system. Application packages will be installed to the device or emulator with the help of Android Debug Bridge tools.
```
git clone git@github.com:sarkarchandan/androidConnectivityManager.git - To clone the project from the GitHub repository.
gradle clean - To clean the project and the previous build files if any.
gradle build - To build the current project and prepare the apk file.
```
## Sample Execution
#### Following are some snapshots taken from the application.
##### 1. MainActivity using the ConnectivityManager and revealing the Wifi network. 
##### 2. MainActivity using the Connectivity Manager and revealing the Mobile network.
##### 3. WifiManager revealing current Wifi network related information in WifiActivity.
##### 4. TelephonyManager revealing current Mobile network related information in MobileActivity.
<img src="https://cloud.githubusercontent.com/assets/19269229/21664688/6957b7de-d2e8-11e6-8878-f64837c0cb84.png" alt="MainActivity using the ConnectivityManager and revealing the Wifi network" width="150" height="300">
<img src="https://cloud.githubusercontent.com/assets/19269229/21664698/8075f084-d2e8-11e6-8360-66b2e26323f7.png" alt="MainActivity using the Connectivity Manager and revealing the Mobile network" width="150" height="300">
<img src="https://cloud.githubusercontent.com/assets/19269229/21664725/ad8cef3c-d2e8-11e6-8e52-4dab2213eb1b.png" alt="WifiManager revealing current Wifi network related information in WifiActivity" width="150" height="300">
<img src="https://cloud.githubusercontent.com/assets/19269229/21664738/c07e9c80-d2e8-11e6-9ddb-018ad4e57d61.png" alt="TelephonyManager revealing current Mobile network related information in MobileActivity" width="150" height="300">



