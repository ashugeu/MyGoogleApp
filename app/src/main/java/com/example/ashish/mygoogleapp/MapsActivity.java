package com.example.ashish.mygoogleapp;

import android.app.Activity;
import android.support.annotation.BoolRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import okhttp3.MediaType;
import okhttp3.FormBody;
import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MapsActivity extends Activity{

    public static final String myTag = "UploadData";
    public static final String URL = "https://docs.google.com/forms/d/e/1FAIpQLScsh9BXNJrYBw6p2gw9MAC6OUHvPR3Q1mhMeF7K9yElGyt25Q/formResponse";
    public static final String latKey = "entry.1098124179";
    public static final String longKey = "entry.1125203045";
    Button btnShowLocation;
    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        // show location button click event
        Log.i(myTag, "OnCreate()");
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(MapsActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    String lat = Double.toString(latitude);
                    String lon = Double.toString(longitude);
                    postData(URL,lat,lon);
                    //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    gps.showSettingsAlert();
                }

            }
        });
    }
    public void postData(String fullUrl,String col1, String col2) {

        HttpRequest mReq = new HttpRequest();
        String data = latKey+"=" + URLEncoder.encode(col1) + "&" +
                longKey+"=" + URLEncoder.encode(col2);
        System.out.println(data);
        String response = mReq.sendPost(fullUrl, data);
        //Log.i(myTag, response);
        System.out.println(response);
        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + col1 + "\nLong: " + col2, Toast.LENGTH_LONG).show();
    }


}
