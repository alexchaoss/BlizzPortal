package com.BlizzardArmory.connection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.warcraft.WowCharacters;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class ImageDownload extends AsyncTask<String, Void, ArrayList<Drawable>> {

    private Context context;
    private ArrayList<Drawable> thumbnails = new ArrayList<>();
    private ArrayList<String> urls;
    private String baseURL;
    private JSONObject characterInformation;


    public ImageDownload(ArrayList<String> urls, String baseURL, Context context, JSONObject characterInformation) {
        this.urls = urls;
        this.context = context;
        this.baseURL = baseURL;
        this.characterInformation = characterInformation;
    }

    public ImageDownload(String url, String baseURL, Context context, JSONObject characterInformation) {
        this.characterInformation = characterInformation;
        this.urls = new ArrayList<>();
        this.urls.add(url);
        this.context = context;
        this.baseURL = baseURL;
    }

    public ImageDownload(ArrayList<String> fullURLs, Context context){
        urls = fullURLs;
        this.context = context;
    }

    public ArrayList<Drawable> getImageFromURL() {
        return doInBackground();
    }

    @Override
    protected ArrayList<Drawable> doInBackground(String... strings) {
        WowCharacters wowCharacters = null;
        HttpURLConnection con;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HostnameVerifier validHostname = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return hostname.equalsIgnoreCase("us.battle.net")
                        || hostname.equalsIgnoreCase("eu.battle.net")
                        || hostname.equalsIgnoreCase("tw.battle.net")
                        || hostname.equalsIgnoreCase("kr.battle.net")
                        || hostname.equalsIgnoreCase("www.battlenet.com.cn")
                        || hostname.equalsIgnoreCase("us.api.blizzard.com")
                        || hostname.equalsIgnoreCase("eu.api.blizzard.com")
                        || hostname.equalsIgnoreCase("tw.api.blizzard.com")
                        || hostname.equalsIgnoreCase("kr.api.blizzard.com")
                        || hostname.equalsIgnoreCase("gateway.battlenet.com.cn")
                        || hostname.equalsIgnoreCase("render-eu.worldofwarcraft.com")
                        || hostname.equalsIgnoreCase("render-us.worldofwarcraft.com")
                        || hostname.equalsIgnoreCase("render-tw.worldofwarcraft.com")
                        || hostname.equalsIgnoreCase("render-kr.worldofwarcraft.com");
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(validHostname);

        if (characterInformation != null) {
            wowCharacters = new WowCharacters(characterInformation);

            for (int i = 0; i < urls.size(); i++) {
                InputStream in;
                Bitmap bmp;
                try {
                    URL url = new URL(baseURL + urls.get(i) + URLConstants.NOT_FOUND_URL_AVATAR + wowCharacters.getRaceList().get(i) + "-" + wowCharacters.getGenderList().get(i) + ".jpg");
                    Log.i("json", url.toString());
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestProperty("Accept-Encoding", "identity");
                    con.setDoInput(true);
                    int responseCode = con.getResponseCode();
                    Log.i("Response code", String.valueOf(responseCode));
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        Log.i("test", url.toString());
                        url = new URL(URLConstants.NOT_FOUND_URL_AVATAR + wowCharacters.getRaceList().get(i) + "-" + wowCharacters.getGenderList().get(i) + ".jpg");
                        Log.i("json", url.toString());
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestProperty("Accept-Encoding", "identity");
                        con.setDoInput(true);
                        responseCode = con.getResponseCode();
                        Log.i("Response code", String.valueOf(responseCode));

                    }
                    con.connect();
                    in = con.getInputStream();
                    bmp = BitmapFactory.decodeStream(in);
                    in.close();
                    Drawable drawable = new BitmapDrawable(context.getResources(), bmp);
                    if (responseCode == 403) {
                        drawable = context.getDrawable(R.drawable.no_avatar);
                        thumbnails.add(drawable);
                    }
                    thumbnails.add(drawable);
                } catch (Exception ex) {
                    Log.e("Exception", ex.toString());
                }
            }

        }else {

            for (int i = 0; i < urls.size(); i++) {
                InputStream in;
                Bitmap bmp;
                try {
                    URL url = new URL(urls.get(i));
                    Log.i("json", url.toString());
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestProperty("Accept-Encoding", "identity");
                    con.setDoInput(true);
                    int responseCode = con.getResponseCode();
                    Log.i("Response code", String.valueOf(responseCode));
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestProperty("Accept-Encoding", "identity");
                        con.setDoInput(true);
                        responseCode = con.getResponseCode();
                        Log.i("Response code", String.valueOf(responseCode));

                    }
                    con.connect();
                    in = con.getInputStream();
                    bmp = BitmapFactory.decodeStream(in);
                    in.close();
                    Drawable drawable = new BitmapDrawable(context.getResources(), bmp);
                    if (responseCode == 403) {
                        drawable = context.getDrawable(R.drawable.no_avatar);
                        thumbnails.add(drawable);
                    }
                    thumbnails.add(drawable);
                } catch (Exception ex) {
                    Log.e("Exception", ex.toString());
                }
            }
        }

        return thumbnails;
    }
}
