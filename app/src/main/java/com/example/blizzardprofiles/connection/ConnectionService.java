package com.example.blizzardprofiles.connection;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import com.example.blizzardprofiles.URLConstants;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;


public class ConnectionService extends AsyncTask<String, Void, String> {

    private static String returnJson;
    private static BufferedReader reader = null;
    private static HttpsURLConnection urlConnection;
    private static String url;

    public String getStringJSONFromRequest(String baseURL, String urlEndPoint, String accessToken) {
        return doInBackground(baseURL, urlEndPoint, accessToken);
    }

    @Override
    protected String doInBackground(String... strings) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        url = strings[0] + strings[1] + URLConstants.ACCESS_TOKEN_QUERY + strings[2];
        Log.i("URL",url );

        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        try {
            URL fullURL = new URL(url);
            urlConnection = (HttpsURLConnection) fullURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-length","0");
            urlConnection.setUseCaches(false);

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
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= returnJson.length()) {
                    Log.v("json", returnJson.substring(4000 * i));
                } else {
                    Log.v("json", returnJson.substring(4000 * i, max));
                }
            }
        } else {
            Log.v("json", returnJson);
        }
        return returnJson;
    }
}
