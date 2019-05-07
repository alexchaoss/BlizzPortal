package com.example.blizzardprofiles;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        }catch (IOException e){
            Log.e("Error", e.toString());
        }
        linearLayout = view.findViewById(R.id.linear_wow_characters);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        WOWCharacters characterList = new WOWCharacters(wowCharacters);
        ArrayList<String> characters = characterList.getCharacterNamesList();
        Log.i("character number", String.valueOf(characters.size()));
        ArrayList<TextView> textViews = new ArrayList<>();
        for(int i = 0; i<characters.size();i++) {
            TextView textView = new TextView(view.getContext());
            textView.setText(characters.get(i));
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(25);
            textViews.add(textView);
        }
        for(TextView textView: textViews) {
            linearLayout.addView(textView);
        }

        return view;
    }
}
