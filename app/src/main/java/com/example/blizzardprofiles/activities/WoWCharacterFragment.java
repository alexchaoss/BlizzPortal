package com.example.blizzardprofiles.activities;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.example.blizzardprofiles.R;
import com.example.blizzardprofiles.URLConstants;
import com.example.blizzardprofiles.connection.ConnectionService;
import com.example.blizzardprofiles.connection.ImageDownload;
import com.example.blizzardprofiles.warcraft.Items;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WoWCharacterFragment extends Fragment {

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;
    private JSONObject characterItems;
    private Items items;
    ArrayList<String> iconURL = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.character_fragment, container, false);
        ImageView head = view.findViewById(R.id.head);
        ImageView neck = view.findViewById(R.id.neck);
        ImageView shoulder = view.findViewById(R.id.shoulder);
        ImageView back = view.findViewById(R.id.back);
        ImageView chest = view.findViewById(R.id.chest);
        ImageView wrist = view.findViewById(R.id.wrist);
        ImageView hands = view.findViewById(R.id.hands);
        ImageView waist = view.findViewById(R.id.waist);
        ImageView legs = view.findViewById(R.id.legs);
        ImageView feet = view.findViewById(R.id.feet);
        ImageView finger1 = view.findViewById(R.id.finger1);
        ImageView finger2 = view.findViewById(R.id.finger2);
        ImageView trinket1 = view.findViewById(R.id.trinket1);
        ImageView trinket2 = view.findViewById(R.id.trinket2);
        ImageView mainHand = view.findViewById(R.id.main_hand);
        ImageView offHand= view.findViewById(R.id.off_hand);


        prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        bnOAuth2Params = this.getActivity().getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        try {
            characterItems = new JSONObject(ConnectionService.getStringJSONFromRequest(URLConstants.getBaseURLforAPI(),
                    URLConstants.WOW_CHAR_URL + swapRealmCharacterFromURL(), bnOAuth2Helper.getAccessToken()));
            items = new Items(characterItems.getJSONObject("items"));
        }catch (Exception e){
            Log.e("Error", e.toString());
        }
        getItemIconURL();

        ArrayList<Drawable> icons = new ImageDownload(iconURL, URLConstants.WOW_ICONS_URL, view.getContext()).getImageFromURL();

        head.setImageDrawable(icons.get(0));
        neck.setImageDrawable(icons.get(1));
        shoulder.setImageDrawable(icons.get(2));
        back.setImageDrawable(icons.get(3));
        chest.setImageDrawable(icons.get(4));
        wrist.setImageDrawable(icons.get(5));
        hands.setImageDrawable(icons.get(6));
        waist.setImageDrawable(icons.get(7));
        legs.setImageDrawable(icons.get(8));
        feet.setImageDrawable(icons.get(9));
        finger1.setImageDrawable(icons.get(10));
        finger2.setImageDrawable(icons.get(11));
        trinket1.setImageDrawable(icons.get(12));
        trinket2.setImageDrawable(icons.get(13));
        mainHand.setImageDrawable(icons.get(14));
        offHand.setImageDrawable(icons.get(15));

        return view;
    }

    private String swapRealmCharacterFromURL(){
        return URLConstants.WOW_ITEM_QUERY.replace("realm/character", WoWActivity.characterRealm+"/"+WoWActivity.characterClicked);
    }

    private void getItemIconURL(){
        try{
            iconURL.add(items.getHead().get("icon").toString());
            iconURL.add(items.getNeck().get("icon").toString());
            iconURL.add(items.getShoulder().get("icon").toString());
            iconURL.add(items.getChest().get("icon").toString());
            iconURL.add(items.getHands().get("icon").toString());
            iconURL.add(items.getWrist().get("icon").toString());
            iconURL.add(items.getWaist().get("icon").toString());
            iconURL.add(items.getLegs().get("icon").toString());
            iconURL.add(items.getFeet().get("icon").toString());
            iconURL.add(items.getFinger1().get("icon").toString());
            iconURL.add(items.getFinger2().get("icon").toString());
            iconURL.add(items.getTrinket1().get("icon").toString());
            iconURL.add(items.getTrinket2().get("icon").toString());
            iconURL.add(items.getMainHand().get("icon").toString());
            iconURL.add(items.getOffHand().get("icon").toString());
        }catch (JSONException e){
            Log.e("Error", e.toString());
        }
    }
}
