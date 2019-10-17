package com.example.apicalling.CountryModel;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CountryCodeAsync extends AsyncTask<String, Void, String> {
    private AsyncListener mAsyncListener;
    public interface AsyncListener{
        void onCompleted(String s);
    }

    public CountryCodeAsync(Context context, AsyncListener asyncListener) {
        mAsyncListener = asyncListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

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

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mAsyncListener.onCompleted(s);
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
