package com.BlizzardArmory.network.oauth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.BlizzardArmory.ui.main.AuthorizationTokenActivity
import com.BlizzardArmory.ui.navigation.GamesActivity

object OauthFlowStarter {

    var lastOpenedFragmentNeedingOAuth: String = ""

    var bundle: Bundle? = null

    fun startOauthFlow(battlenetOAuth2Params: BattlenetOAuth2Params, context: Context, visible: Int) {
        Log.i("OAuth2", "START OAUTH")
        val intent = Intent(context, AuthorizationTokenActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        intent.putExtra("visisble", visible)
        intent.putExtra(BattlenetConstants.BUNDLE_BNPARAMS, battlenetOAuth2Params)
        intent.putExtra(BattlenetConstants.BUNDLE_REDIRECT_ACTIVITY, GamesActivity::class.java)
        context.startActivity(intent)
    }
}