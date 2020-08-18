package com.BlizzardArmory.connection.oauth;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpRequest;

public class BattlenetQueryParamsAccessMethod implements Credential.AccessMethod {

    private static final String PARAM_NAME = "oauth_token";

    private static final BattlenetQueryParamsAccessMethod instance = new BattlenetQueryParamsAccessMethod();

    private BattlenetQueryParamsAccessMethod() {
    }

    public static BattlenetQueryParamsAccessMethod getInstance() {
        return instance;
    }

    @Override
    public void intercept(HttpRequest request, String accessToken) {
        request.getUrl().set(PARAM_NAME, accessToken);
    }

    @Override
    public String getAccessTokenFromRequest(HttpRequest request) {
        Object param = request.getUrl().get(PARAM_NAME);
        return param == null ? null : param.toString();
    }
}
