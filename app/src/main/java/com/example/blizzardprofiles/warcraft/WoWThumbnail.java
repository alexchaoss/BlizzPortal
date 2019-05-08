package com.example.blizzardprofiles.warcraft;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.blizzardprofiles.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class WoWThumbnail {

    private WOWCharacters characters;
    private Context context;
    private ArrayList<Drawable> thumbnails = new ArrayList<>();
    private final String THUMNAIL_URL = "http://render-us.worldofwarcraft.com/character/";
    private final String ENCODING = "?accept-encoding:gzip";

    public WoWThumbnail(WOWCharacters wowCharacters, Context context){
        this.characters = wowCharacters;
        this.context = context;
    }

    public ArrayList<Drawable> getImageFromURL(){

        ArrayList<String> urls = characters.getUrlThumbnail();
        for(int i = 0; i<urls.size();i++){
            try{
                InputStream is = (InputStream) new URL(THUMNAIL_URL + urls.get(i) + ENCODING).getContent();
                Drawable drawable = Drawable.createFromStream(is, "src");
                thumbnails.add(drawable);
            }catch (Exception e){
                Log.e("Error",e.toString());
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.no_avatar);
                thumbnails.add(drawable);
            }
        }
        return  thumbnails;
    }
}
