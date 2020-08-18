package com.BlizzardArmory.connection.oauth;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.api.client.auth.oauth2.Credential;

public class BattlenetOAuth2Params implements Parcelable {

    private final String TOKEN_URL_CN = "https://www.battlenet.com.cn/oauth/token";
    private final String AUTH_URL_CN = "https://www.battlenet.com.cn/oauth/authorize";

    private final String TOKEN_URL_NOT_CN = "https://zone.battle.net/oauth/token";
    private final String AUTH_URL_NOT_CN = "https://zone.battle.net/oauth/authorize";

    private final String ZONE_DEFAULT = "zone";

    private String clientId;
    private String scope;
    private String rederictUri;
    private String userId;
    private String zone;
    private String tokenServerUrl;
    private String authorizationServerEncodedUrl;

    public BattlenetOAuth2Params(String clientId, String zone, String rederictUri, String userId, String... scopes) {
        this.clientId = clientId;
        this.zone = zone;

        // If we login in China servers, we have different URL
        if (BattlenetConstants.ZONE_CHINA.equalsIgnoreCase(zone)) {
            this.tokenServerUrl = TOKEN_URL_CN;
            this.authorizationServerEncodedUrl = AUTH_URL_CN;
        } else {
            this.tokenServerUrl = TOKEN_URL_NOT_CN.replace(ZONE_DEFAULT, zone);
            this.authorizationServerEncodedUrl = AUTH_URL_NOT_CN.replace(ZONE_DEFAULT, zone);
        }

        this.rederictUri = rederictUri;
        this.userId = userId;

        // Create SCOPES with required pattern (Scope+Scope+...)
        StringBuilder sb = new StringBuilder();
        if (null != scopes && 0 < scopes.length) {
            for (String scope : scopes) {
                sb.append(scope).append("+");
            }
        } else {
            // Access to WoW and SC2 data
            sb.append(BattlenetConstants.SCOPE_WOW).append("+");
            sb.append(BattlenetConstants.SCOPE_SC2).append("+");
        }

        this.scope = sb.toString().substring(0, sb.toString().length() - 1);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRederictUri() {
        return rederictUri;
    }

    public void setRederictUri(String rederictUri) {
        this.rederictUri = rederictUri;
    }

    public String getTokenServerUrl() {
        return tokenServerUrl;
    }

    public void setTokenServerUrl(String tokenServerUrl) {
        this.tokenServerUrl = tokenServerUrl;
    }

    public String getAuthorizationServerEncodedUrl() {
        return authorizationServerEncodedUrl;
    }

    public void setAuthorizationServerEncodedUrl(String authorizationServerEncodedUrl) {
        this.authorizationServerEncodedUrl = authorizationServerEncodedUrl;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Credential.AccessMethod getAccessMethod() {
        return BattlenetQueryParamsAccessMethod.getInstance();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.clientId);
        dest.writeString(this.scope);
        dest.writeString(this.rederictUri);
        dest.writeString(this.userId);
        dest.writeString(this.zone);
        dest.writeString(this.tokenServerUrl);
        dest.writeString(this.authorizationServerEncodedUrl);
    }

    protected BattlenetOAuth2Params(Parcel in) {
        this.clientId = in.readString();
        this.scope = in.readString();
        this.rederictUri = in.readString();
        this.userId = in.readString();
        this.zone = in.readString();
        this.tokenServerUrl = in.readString();
        this.authorizationServerEncodedUrl = in.readString();
    }

    public static final Parcelable.Creator<BattlenetOAuth2Params> CREATOR = new Parcelable.Creator<BattlenetOAuth2Params>() {
        @Override
        public BattlenetOAuth2Params createFromParcel(Parcel source) {
            return new BattlenetOAuth2Params(source);
        }

        @Override
        public BattlenetOAuth2Params[] newArray(int size) {
            return new BattlenetOAuth2Params[size];
        }
    };
}
