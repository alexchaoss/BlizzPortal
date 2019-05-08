package com.example.blizzardprofiles.connection;

import android.os.StrictMode;
import android.util.Log;

import com.example.blizzardprofiles.activities.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;


public class ConnectionService {


    private static final String BASE_URL_USER_INFO = "https://zone.battle.net";
    private static final String BASE_URL_CN_USER_INFO = "https://www.battlenet.com.cn";
    private static final String BASE_URL_API = "https://zone.api.blizzard.com";
    private static final String BASE_URL_CN_API = "https://gateway.battlenet.com.cn";
    private static final String ACCESS_TOKEN = "?access_token=";

    private static String returnJson;
    private static BufferedReader reader = null;
    private static HttpsURLConnection urlConnection;
    private static String url;

    public static String getStringJSONFromRequest(String urlEndPoint, String accessToken) throws IOException{
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        url = "";
        getURL(urlEndPoint, accessToken);
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

    private static void getURL(String urlEndPoint, String accessToken) {
        if(MainActivity.selectedRegion.equals("cn")){
            if(urlEndPoint.equals("/oauth/userinfo")) {
                url += BASE_URL_CN_USER_INFO + urlEndPoint + ACCESS_TOKEN + accessToken;
            }else{
                url += BASE_URL_CN_API + urlEndPoint + ACCESS_TOKEN + accessToken;
            }
        }else if(urlEndPoint.equals("/oauth/userinfo")){
            url += BASE_URL_USER_INFO.replace("zone",MainActivity.selectedRegion.toLowerCase()) + urlEndPoint + ACCESS_TOKEN + accessToken;
        }else{
            url += BASE_URL_API.replace("zone",MainActivity.selectedRegion.toLowerCase()) + urlEndPoint + ACCESS_TOKEN + accessToken;
        }
    }
}
