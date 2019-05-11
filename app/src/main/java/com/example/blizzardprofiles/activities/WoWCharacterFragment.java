package com.example.blizzardprofiles.activities;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.example.blizzardprofiles.R;
import com.example.blizzardprofiles.URLConstants;
import com.example.blizzardprofiles.connection.ConnectionService;
import com.example.blizzardprofiles.connection.ImageDownload;
import com.example.blizzardprofiles.warcraft.Items;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class WoWCharacterFragment extends Fragment {
    private final String ICON = "icon";

    private String characterRealm;
    private String characterClicked;
    private String urlMain;

    private SharedPreferences prefs;
    private BnOAuth2Helper bnOAuth2Helper;
    private BnOAuth2Params bnOAuth2Params;
    private JSONObject characterItems;
    private Items items;
    private TextView characterName;
    private ImageView background;
    ArrayList<Drawable> icons = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.character_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        characterRealm = bundle.getString("realm");
        characterClicked = bundle.getString("name");
        urlMain = bundle.getString("url");
        characterName = view.findViewById(R.id.character_name);
        background = view.findViewById(R.id.background);

        prefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        bnOAuth2Params = this.getActivity().getIntent().getExtras().getParcelable(BnConstants.BUNDLE_BNPARAMS);
        bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

        Drawable backgroundMain = new ImageDownload(urlMain, URLConstants.getRenderZoneURL(), view.getContext()).getImageFromURL().get(0);





        try {
            characterItems = new JSONObject(ConnectionService.getStringJSONFromRequest(URLConstants.getBaseURLforAPI(),
                    swapRealmCharacterFromURL(), bnOAuth2Helper.getAccessToken()));
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        JSONObject itemObject;
        try {
            String reason = "";
            if (characterItems.isNull("name")) {
                characterName.setText(characterItems.get("reason").toString());
                reason = characterItems.get("reason").toString();
            } else {
                characterName.setText(characterItems.get("name").toString());
            }

            if(!reason.equals("Character not found.")){
                background.setImageDrawable(backgroundMain);
            }

            itemObject = characterItems.getJSONObject("items");
            items = new Items(itemObject);
            getIcons(view);

            ImageView head = view.findViewById(R.id.head);
            ImageView neck = view.findViewById(R.id.neck);
            ImageView shoulder = view.findViewById(R.id.shoulder);
            ImageView back = view.findViewById(R.id.back);
            ImageView chest = view.findViewById(R.id.chest);
            ImageView shirt = view.findViewById(R.id.shirt);
            ImageView tabard = view.findViewById(R.id.tabard);
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
            ImageView offHand = view.findViewById(R.id.off_hand);

            head.setImageDrawable(icons.get(0));
            neck.setImageDrawable(icons.get(1));
            shoulder.setImageDrawable(icons.get(2));
            back.setImageDrawable(icons.get(3));
            chest.setImageDrawable(icons.get(4));
            shirt.setImageDrawable(icons.get(5));
            tabard.setImageDrawable(icons.get(6));
            wrist.setImageDrawable(icons.get(7));
            hands.setImageDrawable(icons.get(8));
            waist.setImageDrawable(icons.get(9));
            legs.setImageDrawable(icons.get(10));
            feet.setImageDrawable(icons.get(11));
            finger1.setImageDrawable(icons.get(12));
            finger2.setImageDrawable(icons.get(13));
            trinket1.setImageDrawable(icons.get(14));
            trinket2.setImageDrawable(icons.get(15));
            mainHand.setImageDrawable(icons.get(16));
            offHand.setImageDrawable(icons.get(17));
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private void getIcons(View view) {
        try {
            if (items.getHead() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_head);
                icons.add(noItem);
            } else {
                Log.i("url", items.getHead().get(ICON).toString());
                Drawable item = new ImageDownload(items.getHead().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getNeck() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_neck);
                icons.add(noItem);
            } else {
                Log.i("url", items.getNeck().get(ICON).toString());
                Drawable item = new ImageDownload(items.getNeck().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getShoulder() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_shoulders);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getShoulder().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getBack() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_back);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getBack().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getChest() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_chest);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getChest().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getShirt() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_shirt);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getShirt().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getTabard() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_tabard);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getTabard().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getWrist() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_wrist);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getWrist().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getHands() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_hands);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getHands().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getWaist() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_waist);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getWaist().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getLegs() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_legs);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getLegs().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getFeet() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_feet);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getFeet().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getFinger1() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_ring);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getFinger1().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getFinger2() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_ring);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getFinger2().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getTrinket1() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_trinket);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getTrinket1().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getTrinket2() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_trinket);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getTrinket2().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getMainHand() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_main_hand);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getMainHand().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }

            if (items.getOffHand() == null) {
                Drawable noItem = ContextCompat.getDrawable(view.getContext(), R.drawable.empty_shield);
                icons.add(noItem);
            } else {
                Drawable item = new ImageDownload(items.getOffHand().get(ICON).toString() + ".jpg", URLConstants.WOW_ICONS_URL + "56/", view.getContext()).getImageFromURL().get(0);
                icons.add(item);
            }
        } catch (JSONException e) {

        }
    }

    private String swapRealmCharacterFromURL() {
        return URLConstants.WOW_ITEM_QUERY.replace("realm/character", characterRealm + "/" + characterClicked);
    }


}
