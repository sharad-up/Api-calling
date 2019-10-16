package com.example.apicalling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.apicalling.CountryModel.CountryModelClass;
import com.example.apicalling.CountryModel.MylistAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    MylistAdapter adapter;
    RecyclerView recyclerView;
    URL url;
    private String urlToConnect = "http://52.191.15.195/B2CTestApi/api/Country/GetCallingCodes";
    private ArrayList<CountryModelClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recyclerview);

        CountryCodeAsync countryCodeAsync = new CountryCodeAsync();
        countryCodeAsync.execute(urlToConnect);

    }

    private class CountryCodeAsync extends AsyncTask<String, Void, String> {

        // main
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // background
        @Override
        protected String doInBackground(String... strings) {
            String countryUrl = strings[0];
            try {
                URL url = new URL(countryUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return convertInputStreamToString(in);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // main
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("data", s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray= jsonObject.getJSONArray("Data");
                list = new ArrayList<>();
                for (int j = 0; j <= jsonArray.length(); j++) {
                    JSONObject object = jsonArray.getJSONObject(j);
                    CountryModelClass country = new CountryModelClass();
                    country.setCountryname(object.getString("Name"));
                    country.setShortname(object.getString("ShortName"));
                    list.add(country);
                    Log.e("COUNTRY::::::::", country.getCountryname());
                    setAdapter();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public void setAdapter(){
        adapter = new MylistAdapter(MainActivity.this,list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private String convertInputStreamToString(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e){

        }

        return builder.toString();
    }
}
