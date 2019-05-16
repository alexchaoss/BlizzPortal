package com.example.blizzardprofiles.connection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.blizzardprofiles.R;
import com.example.blizzardprofiles.URLConstants;
import com.example.blizzardprofiles.warcraft.WowCharacters;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ImageDownload extends AsyncTask<String, Void, ArrayList<Drawable>> {

    private Context context;
    private ArrayList<Drawable> thumbnails = new ArrayList<>();
    private ArrayList<String> urls;
    private String baseURL;
    private JSONObject characterInformation;


    public ImageDownload(ArrayList<String> urls, String baseURL, Context context, JSONObject characterInformation){
        this.urls = urls;
        this.context = context;
        this.baseURL = baseURL;
        this.characterInformation = characterInformation;
    }

    public ImageDownload(String url, String baseURL, Context context, JSONObject characterInformation){
        this.characterInformation = characterInformation;
        this.urls = new ArrayList<>();
        this.urls.add(url);
        this.context = context;
        this.baseURL = baseURL;
    }

    public ArrayList<Drawable> getImageFromURL(){
        return doInBackground();
    }

    @Override
    protected ArrayList<Drawable> doInBackground(String... strings) {
        WowCharacters wowCharacters = null;
        if(characterInformation != null){
            wowCharacters = new WowCharacters(characterInformation);

        }
        for(int i = 0; i<urls.size();i++){
            InputStream in =null;
            Bitmap bmp=null;
            try{
                if(characterInformation != null){
                    URL url = new URL(baseURL + urls.get(i) + URLConstants.NOT_FOUND_URL_AVATAR + wowCharacters.getRaceList().get(i) + "-" + wowCharacters.getGenderList().get(i) + ".jpg");
                }
                URL url = new URL(baseURL + urls.get(i) + URLConstants.NOT_FOUND_URL_AVATAR + wowCharacters.getRaceList().get(i) + "-" + wowCharacters.getGenderList().get(i) + ".jpg");
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestProperty("Accept-Encoding", "identity");
                con.setDoInput(true);
                int responseCode = con.getResponseCode();
                Log.i("Response code", String.valueOf(responseCode));
                if(responseCode == 403){
                    Log.i("json", characterInformation.toString());
                    url = new URL(URLConstants.NOT_FOUND_URL_AVATAR + wowCharacters.getRaceList().get(i) + "-" + wowCharacters.getGenderList().get(i) + ".jpg");
                    con = (HttpURLConnection)url.openConnection();
                    con.setRequestProperty("Accept-Encoding", "identity");
                    con.setDoInput(true);
                }
                con.connect();
                in = con.getInputStream();
                bmp = BitmapFactory.decodeStream(in);
                in.close();
                Drawable drawable = new BitmapDrawable(context.getResources(), bmp);
                thumbnails.add(drawable);
            }
            catch(Exception ex){
                Log.e("Exception",ex.toString());
            }
        }
        return  thumbnails;
    }
}
