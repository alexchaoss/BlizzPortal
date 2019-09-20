package com.BlizzardArmory.UI.UI_diablo;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.connection.ConnectionService;
import com.BlizzardArmory.connection.ImageDownload;
import com.BlizzardArmory.diablo.Character.CharacterInformation;
import com.BlizzardArmory.diablo.Character.ItemsCharacter;
import com.BlizzardArmory.diablo.Items.Item;
import com.BlizzardArmory.diablo.Items.Items;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class D3CharacterFragment extends Fragment {

    private int id;

    private CharacterInformation characterInformation;
    private JSONObject characterInfo;
    private Items itemsInformation;
    private JSONObject itemInfo;
    private ArrayList<Item> items = new ArrayList<>();

    private RelativeLayout loadingCircle;

    private List<ImageView> imageViewItem = new ArrayList<>();
    List<Drawable> itemIcon;
    private ImageView shoulders;
    private ImageView hands;
    private ImageView ring1;
    private ImageView main_hand;
    private ImageView head;
    private ImageView chest;
    private ImageView belt;
    private ImageView legs;
    private ImageView boots;
    private ImageView amulet;
    private ImageView bracers;
    private ImageView ring2;
    private ImageView off_hand;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d3_character_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        assert bundle != null;
        id = bundle.getInt("id");
        shoulders = view.findViewById(R.id.shoulder);
        hands = view.findViewById(R.id.gloves);
        ring1 = view.findViewById(R.id.ring1);
        main_hand = view.findViewById(R.id.main_hand);
        head = view.findViewById(R.id.head);
        chest = view.findViewById(R.id.chest);
        belt = view.findViewById(R.id.belt);
        legs = view.findViewById(R.id.legs);
        boots = view.findViewById(R.id.boots);
        amulet = view.findViewById(R.id.amulet);
        bracers = view.findViewById(R.id.bracers);
        ring2 = view.findViewById(R.id.ring2);
        off_hand = view.findViewById(R.id.off_hand);
        loadingCircle = view.findViewById(R.id.loadingCircle);

        addImageViewItemsToList();


        Objects.requireNonNull(D3CharacterFragment.this.getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        new PrepareDataD3Character(this).execute();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private static class PrepareDataD3Character extends AsyncTask<Void, Void, Void> {

        private WeakReference<D3CharacterFragment> activityReference;

        PrepareDataD3Character(D3CharacterFragment context) {
            activityReference = new WeakReference<>(context);
        }


        protected void onPreExecute() {
            super.onPreExecute();
            D3CharacterFragment activity = activityReference.get();
            activity.loadingCircle.setVisibility(View.VISIBLE);
        }

        protected Void doInBackground(Void... param) {
            long startTime = System.nanoTime();
            D3CharacterFragment activity = activityReference.get();
            final Gson gson = new GsonBuilder().create();

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity.getContext());
            BnOAuth2Params bnOAuth2Params = Objects.requireNonNull(Objects.requireNonNull(activity.getActivity()).getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
            assert bnOAuth2Params != null;
            BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

            ArrayList<String> urls = new ArrayList<>();

            try {
                urls.add(URLConstants.getBaseURLforAPI() + URLConstants.getD3HeroURL(activity.id) + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());
                urls.add(URLConstants.getBaseURLforAPI() + URLConstants.getD3HeroItemsURL(activity.id) + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken());

                ArrayList<String> jsonInfo;

                jsonInfo = new ConnectionService(urls, activity.getContext()).getStringJSONFromRequest();

                activity.characterInfo = new JSONObject(jsonInfo.get(0));
                activity.characterInformation = gson.fromJson(activity.characterInfo.toString(), CharacterInformation.class);

                activity.itemInfo = new JSONObject(jsonInfo.get(1));
                activity.itemsInformation = gson.fromJson(activity.itemInfo.toString(), Items.class);
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }

            ArrayList<String> itemIconURL = new ArrayList<>();
            getItemIconURL(activity, itemIconURL);
            activity.itemIcon = new ImageDownload(itemIconURL, activity.getContext()).getImageFromURL();

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000000;
            Log.i("time", String.valueOf(duration));

            return null;
        }

        protected void onPostExecute(Void param) {
            super.onPostExecute(param);
            D3CharacterFragment activity = activityReference.get();

            try{
                for(int i = 0; i< activity.imageViewItem.size(); i++){
                    activity.imageViewItem.get(i).setImageDrawable(activity.itemIcon.get(i));
                }
            }catch (Exception e){
                Log.e("Error", e.toString());
            }

            activity.setItemBackgroundColor();


            Objects.requireNonNull(activity.getActivity()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            activity.loadingCircle.setVisibility(View.GONE);
        }


    }

    private void addImageViewItemsToList() {
        imageViewItem.add(shoulders);
        imageViewItem.add(hands);
        imageViewItem.add(ring1);
        imageViewItem.add(main_hand);
        imageViewItem.add(head);
        imageViewItem.add(chest);
        imageViewItem.add(belt);
        imageViewItem.add(legs);
        imageViewItem.add(boots);
        imageViewItem.add(amulet);
        imageViewItem.add(bracers);
        imageViewItem.add(ring2);
        imageViewItem.add(off_hand);
    }

    private void setItemBackgroundColor() {
            selectColor(itemsInformation.getShoulders().getDisplayColor(), shoulders);
            selectColor(itemsInformation.getHands().getDisplayColor(), hands);
            selectColor(itemsInformation.getLeftFinger().getDisplayColor(), ring1);
            selectColor(itemsInformation.getMainHand().getDisplayColor(), main_hand);
            selectColor(itemsInformation.getHead().getDisplayColor(), head);
            selectColor(itemsInformation.getTorso().getDisplayColor(), chest);
            selectColor(itemsInformation.getWaist().getDisplayColor(), belt);
            selectColor(itemsInformation.getLegs().getDisplayColor(), legs);
            selectColor(itemsInformation.getFeet().getDisplayColor(), boots);
            selectColor(itemsInformation.getNeck().getDisplayColor(), amulet);
            selectColor(itemsInformation.getBracers().getDisplayColor(), bracers);
            selectColor(itemsInformation.getRightFinger().getDisplayColor(), ring2);
            selectColor(itemsInformation.getOffHand().getDisplayColor(), off_hand);
    }

    private void isValidItem(){

    }

    private void selectColor(String color, ImageView imageView){
        switch(color){
            case "blue":
                imageView.setBackground(getResources().getDrawable(R.drawable.blue_bg_item_d3, getContext().getTheme()));
                break;
            case "yellow":
                imageView.setBackground(getResources().getDrawable(R.drawable.yellow_bg_item_d3, getContext().getTheme()));
                break;
            case "orange":
                imageView.setBackground(getResources().getDrawable(R.drawable.orange_bg_item_d3, getContext().getTheme()));
                break;
            case "green":
                imageView.setBackground(getResources().getDrawable(R.drawable.green_bg_item_d3, getContext().getTheme()));
                break;
            case "brown":
                imageView.setBackground(getResources().getDrawable(R.drawable.brown_bg_item_d3, getContext().getTheme()));
                break;
        }
    }

    private static void getItemIconURL(D3CharacterFragment activity, List<String> itemIconURL) {
        try{
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getShoulders().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getHands().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getLeftFinger().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getMainHand().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getHead().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getTorso().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getWaist().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getLegs().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getFeet().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getNeck().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getBracers().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getRightFinger().getIcon()) + ".png");
            itemIconURL.add(URLConstants.D3_ICON_ITEMS.replace("icon.png", activity.characterInformation.getItemsCharacter().getOffHand().getIcon()) + ".png");
        }catch (Exception e){
            Log.e("Error", e.toString());
        }

    }

}
