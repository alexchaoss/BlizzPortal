package com.example.blizzardprofiles.connection;

import android.os.StrictMode;
import android.util.Log;

import com.example.blizzardprofiles.URLConstants;
import com.example.blizzardprofiles.activities.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;


public class ConnectionService {

    private static String returnJson;
    private static BufferedReader reader = null;
    private static HttpsURLConnection urlConnection;
    private static String url;

    public static String getStringJSONFromRequest(String baseURL, String urlEndPoint, String accessToken) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        url = baseURL + urlEndPoint + URLConstants.ACCESS_TOKEN_QUERY + accessToken;
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

            String line;
            StringBuilder sb = new StringBuilder();
            int responseCode = urlConnection.getResponseCode();
            Log.i("Response code", String.valueOf(responseCode));
            // Read the correct stream based on the response code.
            if((responseCode == 404) || (responseCode == 500)) {
                reader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
                Log.i("Error", reader.toString());
            } else {
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            returnJson = sb.toString();
        } catch (IOException e) {
            // Some sort of connection error, let's just return some sort of error
            Log.e("Error", e.toString());
            returnJson = "{\"status\":\"nok\", \"reason\":\"URL Connection Error\"}";
        }
        finally {
            if (reader != null) try {
                reader.close();
            } catch (IOException ignored) {
            }

        }
        Log.i("JSON Response", returnJson);
        return returnJson;
    }
}
