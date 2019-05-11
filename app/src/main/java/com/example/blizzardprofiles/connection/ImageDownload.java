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
import com.example.blizzardprofiles.warcraft.WOWCharacters;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ImageDownload extends AsyncTask<String, Void, ArrayList<Drawable>> {

    private Context context;
    private ArrayList<Drawable> thumbnails = new ArrayList<>();
    private ArrayList<String> urls;
    private String baseURL;

    public ImageDownload(ArrayList<String> urls, String baseURL, Context context){
        this.urls = urls;
        this.context = context;
        this.baseURL = baseURL;
    }

    public ImageDownload(String url, String baseURL, Context context){
        this.urls = new ArrayList<>();
        this.urls.add(url);
        this.context = context;
        this.baseURL = baseURL;
    }

    public ArrayList<Drawable> getImageFromURL(){
        for(int i = 0; i<urls.size();i++){
            InputStream in =null;
            Bitmap bmp=null;
            try{

                URL url = new URL(baseURL + urls.get(i));
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setDoInput(true);
                con.connect();
                in = con.getInputStream();
                bmp = BitmapFactory.decodeStream(in);
                in.close();
                Drawable drawable = new BitmapDrawable(context.getResources(), bmp);
                thumbnails.add(drawable);
            }
            catch(Exception ex){
                Log.e("Exception",ex.toString());
                if(baseURL.equals(URLConstants.getRenderZoneURL())){
                    Drawable noAvatar = ContextCompat.getDrawable(context, R.drawable.no_avatar);
                    thumbnails.add(noAvatar);
                }

            }
        }
        return  thumbnails;
    }

    @Override
    protected ArrayList<Drawable> doInBackground(String... strings) {
        return getImageFromURL();
    }
}
