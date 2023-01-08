package com.BlizzardArmory.network.oauth

import android.os.Bundle
import android.util.Log
import com.BlizzardArmory.R
import com.BlizzardArmory.ui.auth.AuthorizationFragment
import com.BlizzardArmory.ui.navigation.NavigationActivity
import com.BlizzardArmory.util.state.FragmentTag

object OauthFlowStarter {

    var lastOpenedFragmentNeedingOAuth: String = ""
    var bundle: Bundle? = null
    var started: Boolean = false

    fun startOauthFlow(
        battlenetOAuth2Params: BattlenetOAuth2Params,
        context: NavigationActivity,
        visible: Int
    ) {
        started = true
        Log.i("OAuth2", "START OAUTH")
        val fragment = AuthorizationFragment()
        if (bundle == null) {
            bundle = Bundle()
        }
        bundle?.putInt("visible", visible)
        bundle?.putParcelable(BattlenetConstants.BUNDLE_BNPARAMS, battlenetOAuth2Params)
        fragment.arguments = bundle
        context.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment,
                fragment,
                FragmentTag.AUTHORIZATIONFRAGMENT.name
            ).addToBackStack(FragmentTag.AUTHORIZATIONFRAGMENT.name).commit()
        context.supportFragmentManager.executePendingTransactions()
    }
}