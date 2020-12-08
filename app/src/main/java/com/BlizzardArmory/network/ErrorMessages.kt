package com.BlizzardArmory.network

import android.content.res.Resources
import com.BlizzardArmory.R

/**
 * The type Error messages.
 */
class ErrorMessages(resources: Resources) {
    /**
     * The constant NO_INTERNET.
     */
    val NO_INTERNET = resources.getString(R.string.no_internet)

    /**
     * The constant TURN_ON_CONNECTION_MESSAGE.
     */
    val TURN_ON_CONNECTION_MESSAGE = resources.getString(R.string.turn_on_connection)

    /**
     * The constant RETRY.
     */
    val RETRY = resources.getString(R.string.retry)

    /**
     * The constant BACK.
     */
    val BACK = resources.getString(R.string.back)

    /**
     * The constant OK.
     */
    val OK = resources.getString(R.string.ok)

    /**
     * The constant UNAVAILABLE.
     */
    val UNAVAILABLE = resources.getString(R.string.unavailable)
    val UNEXPECTED = resources.getString(R.string.unexpected)

    /**
     * The constant ACCOUNT_NOT_FOUND.
     */
    val ACCOUNT_NOT_FOUND = resources.getString(R.string.account_not_found)

    /**
     * The constant OW_ACCOUNT_NOT_FOUND.
     */
    val OW_ACCOUNT_NOT_FOUND = resources.getString(R.string.ow_account_not_found)

    /**
     * The constant SC2_ACCOUNT_NOT_FOUND.
     */
    val SC2_ACCOUNT_NOT_FOUND = resources.getString(R.string.sc2_account_not_found)

    /**
     * The constant SC2_SERVERS_DOWN.
     */
    val SC2_SERVERS_DOWN = resources.getString(R.string.sc2_servers_down)

    /**
     * The constant SERVERS_ERROR.
     */
    val SERVERS_ERROR = resources.getString(R.string.server_error)

    /**
     * The constant BLIZZ_SERVERS_DOWN.
     */
    val BLIZZ_SERVERS_DOWN = resources.getString(R.string.blizz_server_down)

    /**
     * The constant INFORMATION_OUTDATED.
     */
    val INFORMATION_OUTDATED = resources.getString(R.string.outdated_info)

    /**
     * The constant LOGIN_TO_UPDATE.
     */
    val LOGIN_TO_UPDATE = resources.getString(R.string.login_again)
}