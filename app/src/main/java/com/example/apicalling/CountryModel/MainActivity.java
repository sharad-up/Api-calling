package com.example.apicalling.CountryModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.apicalling.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements CountryCodeAsync.AsyncListener {
    MylistAdapter adapter;
    RecyclerView recyclerView;
    URL url;

//    private String urlToConnect ="https://pixabay.com/api/?key=8439361-5e1e53a0e1b58baa26ab398f7&page=1&per_page=3";
    private ArrayList<CountryModelClass> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recycler_view);
        String count= getIntent().getStringExtra("String");
        String urlToConnect ="https://pixabay.com/api/?key=8439361-5e1e53a0e1b58baa26ab398f7&page=1&per_page=";
        String urlAdd = urlToConnect + count;


        CountryCodeAsync countryCodeAsync = new CountryCodeAsync(this, this);
//                new CountryCodeAsync.AsyncListener() {
//            @Override
//            public void onCompleted(String s) {
//                Log.e("data", s);
//                try {
//                JSONObject jsonObject = new JSONObject(s);
//                JSONArray jsonArray= jsonObject.getJSONArray("hits");
//                list = new ArrayList<>();
//                for (int j = 0; j <= jsonArray.length(); j++) {
//                    JSONObject object = jsonArray.getJSONObject(j);
//                    CountryModelClass country = new CountryModelClass();
//                    country.setCountryname(object.getString("id"));
//                    country.setShortname(object.getString("views"));
//                    list.add(country);
//                    Log.e("COUNTRY::::::::", country.getCountryname());
//                    setAdapter();
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            }
//        });
        countryCodeAsync.execute(urlAdd);


    }
//
    public void setAdapter(){
        adapter = new MylistAdapter(MainActivity.this,list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCompleted(String s) {
        Log.e("data", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray= jsonObject.getJSONArray("hits");
            list = new ArrayList<>();
            for (int j = 0; j <= jsonArray.length(); j++) {
                JSONObject object = jsonArray.getJSONObject(j);
                CountryModelClass country = new CountryModelClass();
                country.setCountryname(object.getString("id"));
                country.setShortname(object.getString("views"));
                list.add(country);
                Log.e("COUNTRY::::::::", country.getCountryname());
                setAdapter();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
