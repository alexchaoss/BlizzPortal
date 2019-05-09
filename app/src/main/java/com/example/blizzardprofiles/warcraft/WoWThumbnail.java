package com.example.blizzardprofiles.warcraft;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.example.blizzardprofiles.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WoWThumbnail extends AsyncTask<String, Void, ArrayList<Drawable>> {

    private WOWCharacters characters;
    private Context context;
    private ArrayList<Drawable> thumbnails = new ArrayList<>();
    private final String THUMNAIL_URL = "http://render-us.worldofwarcraft.com/character/";
    ArrayList<String> urls;

    public WoWThumbnail(WOWCharacters wowCharacters, Context context){
        this.characters = wowCharacters;
        this.context = context;
    }

    public ArrayList<Drawable> getImageFromURL(){
        urls = characters.getUrlThumbnail();
        for(int i = 0; i<urls.size();i++){
            InputStream in =null;
            Bitmap bmp=null;
            try{

                URL url = new URL(THUMNAIL_URL + urls.get(i));
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
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.no_avatar);
                thumbnails.add(drawable);
            }

        }
        return  thumbnails;
    }

    @Override
    protected ArrayList<Drawable> doInBackground(String... strings) {
        return getImageFromURL();
    }
}
