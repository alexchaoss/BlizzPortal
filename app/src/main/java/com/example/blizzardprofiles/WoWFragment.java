package com.example.blizzardprofiles;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.io.IOException;
import java.util.ArrayList;

public class WoWFragment  extends Fragment {

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;

    private static final String TAG = "WoWFragment";
    private final String WOW_CHAR_URL = "/wow/user/characters";
    private JSONObject wowCharacters;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.wow_fragment, container, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        bnOAuth2Params = this.getActivity().getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        try {
            wowCharacters = (JSONObject) JSONSerializer.toJSON(ConnectionService.getStringJSONFromRequest(WOW_CHAR_URL, bnOAuth2Helper.getAccessToken()));
            Log.i("json", wowCharacters.toString());
        }catch (IOException e){
            Log.e("Error", e.toString());
        }
        linearLayout = view.findViewById(R.id.linear_wow_characters);

        WOWCharacters characterList = new WOWCharacters(wowCharacters);
        ArrayList<String> characterNames = characterList.getCharacterNamesList();
        ArrayList<String> realms = characterList.getRealmsList();
        ArrayList<String> levels = characterList.getLevelList();
        ArrayList<Drawable> thumbnails =  new WoWThumbnail(characterList, getContext()).getImageFromURL();
        //ArrayList<String> className = characterList.getClassList();

        ArrayList<LinearLayout> linearLayoutCharacterList = new ArrayList<>();

        LinearLayout.LayoutParams layoutParamsImage = new LinearLayout.LayoutParams(150, 150);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,0,0,75);

        LinearLayout.LayoutParams layoutParamsInfo = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsInfo.setMargins(25,0,0,0);


        for(int i = 0; i<characterNames.size();i++) {

            LinearLayout linearLayoutCharacters = new LinearLayout(view.getContext());
            LinearLayout linearLayoutText = new LinearLayout(view.getContext());
            LinearLayout linearLayoutLevelClass = new LinearLayout(view.getContext());
            linearLayoutCharacters.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutText.setOrientation(LinearLayout.VERTICAL);
            linearLayoutLevelClass.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutText.setDividerPadding(10);


            //Add character thumbnail to view
            ImageView imageView = new ImageView(view.getContext());
            imageView.setImageDrawable(thumbnails.get(i));
            imageView.setLayoutParams(layoutParamsImage);

            //Add character name to view
            TextView textViewName = new TextView(view.getContext());
            textViewName.setText(characterNames.get(i));
            textViewName.setTextColor(Color.WHITE);
            textViewName.setTextSize(17);

            //Add level to view
            TextView textViewLevel = new TextView(view.getContext());
            textViewLevel.setText(levels.get(i));
            textViewLevel.setTextColor(Color.WHITE);
            textViewLevel.setTextSize(15);

            //Add class to view
           // TextView textViewClass = new TextView(view.getContext());
           // textViewClass.setText(className.get(i));
           // textViewLevel.setTextColor(Color.WHITE);
           // textViewLevel.setTextSize(15);


            //Add realm to view
            TextView textViewRealm = new TextView(view.getContext());
            textViewRealm.setText(realms.get(i));
            textViewRealm.setTextColor(Color.WHITE);
            textViewRealm.setTextSize(15);

            linearLayoutLevelClass.addView(textViewLevel, layoutParamsInfo);
           // linearLayoutLevelClass.addView(textViewClass, layoutParamsInfo);

            linearLayoutText.addView(textViewName, layoutParamsInfo);
            linearLayoutText.addView(linearLayoutLevelClass, layoutParamsInfo);
            linearLayoutText.addView(textViewRealm, layoutParamsInfo);


            //Add views to layout
            linearLayoutCharacters.addView(imageView);
            linearLayoutCharacters.addView(linearLayoutText);
            linearLayoutCharacterList.add(linearLayoutCharacters);
        }

        for(LinearLayout linear: linearLayoutCharacterList) {
            linearLayout.addView(linear);
            linear.setLayoutParams(layoutParams);
        }

        return view;
    }
}
