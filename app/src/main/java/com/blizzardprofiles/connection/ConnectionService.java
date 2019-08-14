package com.blizzardprofiles.connection;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
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
    private long lastUpdateTime = 0;
    private Context context;

    public ConnectionService(String url, Context context){
        urls.add(url);
        this.context = context;
    }

    public ConnectionService(ArrayList<String> urls, Context context){
        this.urls = urls;
        this.context = context;
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
                    urlConnection.setDefaultUseCaches(true);
                    urlConnection.setUseCaches(true);

                    long currentTime = System.currentTimeMillis();
                    long expires = urlConnection.getHeaderFieldDate("Expires", currentTime);
                    long lastModified = urlConnection.getHeaderFieldDate("Last-Modified", currentTime);

                    if (lastModified < lastUpdateTime) {
                        // Skip update
                    } else {
                        // Parse update
                        lastUpdateTime = lastModified;
                    }

                    //File cachedFiles = new File(context.getCacheDir(), fullURL.toString());

                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    int responseCode = urlConnection.getResponseCode();
                    Log.i("Response code", String.valueOf(responseCode));

                    if((responseCode != HttpsURLConnection.HTTP_OK)) {
                        reader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
                        Log.i("Error", reader.toString());
                    } else /*if(cachedFiles.isFile()){*/
                        reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    /*}else {
                        FileOutputStream out = new FileOutputStream(cachedFiles);
                        out.write(urlConnection.getInputStream().read());
                    }*/
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
