package com.example.wifire;

import java.io.*;
import java.text.*;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends Activity{
	
	String currentLatitude;    //keeps track of the current latitude
	String currentLongitude;   //keeps track of the current longitude
    TextView mainText;		   //text that is displayed in the app
    WifiManager mainWifi;      
    WifiReceiver receiverWifi; 
    List<ScanResult> wifiList; //list of Wi-Fi networks in the area
    StringBuilder sb = new StringBuilder();
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         
       super.onCreate(savedInstanceState);
        
       setContentView(R.layout.activity_main);
       mainText = (TextView) findViewById(R.id.mainText);
       
       //Initiate Wi-Fi service manager
       mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        
       //Check for wifi is disabled
       if(mainWifi.isWifiEnabled() == false){  
    	   //If Wi-Fi disabled then enable it
           Toast.makeText(getApplicationContext(), "wifi is disabled...making it enabled",
           Toast.LENGTH_LONG).show();
                 
           mainWifi.setWifiEnabled(true);
       }
        
       //Wi-Fi scanned value broadcast receiver
       receiverWifi = new WifiReceiver();
        
       //Register broadcast receiver
       //Broadcast receiver will automatically call when number of wifi connections changed
       registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
       mainWifi.startScan();
       mainText.setText("Starting Scan...");
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Refresh");
        return super.onCreateOptionsMenu(menu);
    }
 
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        mainWifi.startScan();
        mainText.setText("Starting Scan");
        return super.onMenuItemSelected(featureId, item);
    }
 
    protected void onPause() {
        unregisterReceiver(receiverWifi);
        super.onPause();
    }
 
    protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }
     
    //Broadcast receiver class called its receive method
    //when number of Wi-Fi connections changed
     
    class WifiReceiver extends BroadcastReceiver{
         
        //This method call when number of Wi-Fi connections changed
        public void onReceive(Context c, Intent intent){
                 
        	//Get the current location
        	LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        	Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        	updateWithNewLocation(location);
        	
            wifiList = mainWifi.getScanResults();
            String s = "";
            //sb.append("\tLatitude: " + currentLatitude);
            //sb.append("\n\tLongitude: " + currentLongitude);
            //sb.append("\n\tNumber Of Wifi connections :"+wifiList.size()+"\n\n");
                                
            for(int i = 0; i < wifiList.size(); i++){
            	ScanResult sr = wifiList.get(i);
            	s += currentLatitude + "," + currentLongitude + "," + sr.SSID + "," + sr.BSSID + "," + sr.level + "\n";
                sb.append(currentLatitude + "," + currentLongitude + "," + sr.SSID + "," + sr.BSSID + "," + sr.level + "\n");
            }
            //create the file that will store the data points in CSV format
            File file = new File("data.txt");
        	try {
				FileOutputStream f1 = new FileOutputStream(file);
				PrintStream p = new PrintStream(f1);
				p.print(s);
				p.close();
				f1.close();
			} catch (FileNotFoundException e) {
			} catch (IOException e){
			}
        	
        	String server = "www.trioptimumengineering.com/wifire";
        	
            mainText.setText(sb); 
        }
        
        private void updateWithNewLocation(Location location){
        	DecimalFormat df = new DecimalFormat("##.000000");
        	
        	if(location != null){
        		double lat = location.getLatitude();
        		double lng = location.getLongitude();
        		currentLatitude = df.format(lat);
        		currentLongitude = df.format(lng);
        	}
        }         
    }
}