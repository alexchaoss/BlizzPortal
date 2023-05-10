package com.BlizzardArmory.model.overwatch.account

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


import java.util.*

/**
 * The type Game.
 */

@Keep
data class Game(

    @SerializedName("gamesWon")
    var gamesWon: Int,

    @SerializedName("timePlayed")
    var timePlayed: String
) {
    val game: HashMap<String, String>
        get() {
            val gameList = HashMap<String, String>()
            gameList["GAMES WON"] = gamesWon.toString()
            gameList["TIME PLAYED"] = timePlayed
            return gameList
        }
}