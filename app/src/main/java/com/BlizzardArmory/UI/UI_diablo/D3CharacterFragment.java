package com.BlizzardArmory.UI.UI_diablo;

import android.content.SharedPreferences;
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
import android.widget.RelativeLayout;

import com.BlizzardArmory.R;
import com.BlizzardArmory.URLConstants;
import com.BlizzardArmory.connection.ConnectionService;
import com.BlizzardArmory.diablo.Character.CharacterInformation;
import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Objects;

public class D3CharacterFragment extends Fragment {

    private int id;

    private CharacterInformation characterInformation;
    private JSONObject characterInfo;

    private RelativeLayout loadingCircle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d3_character_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        assert bundle != null;
        id = bundle.getInt("id");
        loadingCircle = view.findViewById(R.id.loadingCircle);

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
            D3CharacterFragment activity = activityReference.get();
            final Gson gson = new GsonBuilder().create();

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity.getContext());
            BnOAuth2Params bnOAuth2Params = Objects.requireNonNull(Objects.requireNonNull(activity.getActivity()).getIntent().getExtras()).getParcelable(BnConstants.BUNDLE_BNPARAMS);
            assert bnOAuth2Params != null;
            BnOAuth2Helper bnOAuth2Helper = new BnOAuth2Helper(prefs, bnOAuth2Params);

            try {
                activity.characterInfo = new JSONObject(new ConnectionService(URLConstants.getBaseURLforAPI() + URLConstants.getD3HeroURL(activity.id)
                        + URLConstants.ACCESS_TOKEN_QUERY + bnOAuth2Helper.getAccessToken(), activity.getContext()).getStringJSONFromRequest().get(0));
                activity.characterInformation = gson.fromJson(activity.characterInfo.toString(), CharacterInformation.class);
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }

            return null;
        }

        protected void onPostExecute(Void param) {
            super.onPostExecute(param);
            D3CharacterFragment activity = activityReference.get();
        }
    }

}
