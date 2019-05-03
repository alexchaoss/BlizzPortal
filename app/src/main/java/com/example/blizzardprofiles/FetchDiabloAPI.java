package com.example.blizzardprofiles;

import android.content.Intent;

import com.dementh.lib.battlenet_oauth2.BnConstants;
import com.dementh.lib.battlenet_oauth2.activities.BnOAuthAccessTokenActivity;
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params;

import org.json.JSONObject;


public class FetchDiabloAPI {


    protected JSONObject fetchJSONOBJECT(){
        JSONObject result = null;
        BnOAuth2Params params = new BnOAuth2Params(OAuthTokens.DIABLO3.getClientKey(),OAuthTokens.DIABLO3.getSecretKey(),
                BnConstants.ZONE_UNITED_STATES, "https://localhost","D3getAPI");
        return result;
    }
}




