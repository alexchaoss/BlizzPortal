package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * The type Game.
 */
public class Game {

    @SerializedName("gamesWon")
    @Expose
    private int gamesWon;
    @SerializedName("timePlayed")
    @Expose
    private String timePlayed;

    /**
     * Gets games won.
     *
     * @return the games won
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Sets games won.
     *
     * @param gamesWon the games won
     */
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * Gets time played.
     *
     * @return the time played
     */
    public String getTimePlayed() {
        return timePlayed;
    }

    /**
     * Sets time played.
     *
     * @param timePlayed the time played
     */
    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public HashMap<String, String> getGame() {
        HashMap<String, String> gameList = new HashMap<>();

        gameList.put("GAMES WON", String.valueOf(gamesWon));
        gameList.put("TIME PLAYED", timePlayed);

        return gameList;
    }

}
