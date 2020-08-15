package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * The type Game.
 */
data class Game(

        @SerializedName("gamesWon")
        @Expose
        var gamesWon: Int,

        @SerializedName("timePlayed")
        @Expose
        var timePlayed: String
) {
    val game: HashMap<String, String?>
        get() {
            val gameList = HashMap<String, String?>()
            gameList["GAMES WON"] = gamesWon.toString()
            gameList["TIME PLAYED"] = timePlayed
            return gameList
        }
}