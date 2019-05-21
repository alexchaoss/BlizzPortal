package com.example.blizzardprofiles.connection;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import com.example.blizzardprofiles.URLConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;


public class ConnectionService extends AsyncTask<String, Void, ArrayList<String>> {

    private String returnJson;
    private BufferedReader reader = null;
    private ArrayList<String> jsonList = new ArrayList<>();
    private ArrayList<String> urls = new ArrayList<>();

    public ConnectionService(String url){
        urls.add(url);
    }

    public ConnectionService(ArrayList<String> urls){
        this.urls = urls;
    }

    public ArrayList<String> getStringJSONFromRequest() {
        return doInBackground();
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        HttpsURLConnection urlConnection;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        for(int i = 0; i<urls.size(); i++){
            try {
                if(urls.get(i) != null){
                    URL fullURL = new URL(urls.get(i));
                    urlConnection = (HttpsURLConnection) fullURL.openConnection();
                    urlConnection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                    urlConnection.setRequestProperty("Accept","application/json");
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setUseCaches(true);
                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    int responseCode = urlConnection.getResponseCode();
                    Log.i("Response code", String.valueOf(responseCode));

                    if((responseCode == 404) || (responseCode == 500)) {
                        reader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
                        Log.i("Error", reader.toString());
                    } else {
                        reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    }
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append('\n');
                    }

                    returnJson = stringBuilder.toString();
                    jsonList.add(returnJson);
                }else{
                    jsonList.add(null);
                }

            } catch (IOException e) {
                Log.e("Error", e.toString());
            }
            finally {
                if (reader != null) try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
            if (returnJson.length() > 4000) {
                Log.v("json", "sb.length = " + returnJson.length());
                int chunkCount = returnJson.length() / 4000;     // integer division
                for (int j = 0; j <= chunkCount; j++) {
                    int max = 4000 * (j + 1);
                    if (max >= returnJson.length()) {
                        Log.v("json", returnJson.substring(4000 * j));
                    } else {
                        Log.v("json", returnJson.substring(4000 * j, max));
                    }
                }
            } else {
                Log.v("json", returnJson);
            }
        }

        return jsonList;
    }
}
