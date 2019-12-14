package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Game {

    @SerializedName("gamesWon")
    @Expose
    private int gamesWon;
    @SerializedName("timePlayed")
    @Expose
    private String timePlayed;

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public String getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    public HashMap<String, String> getGame() {
        HashMap<String, String> gameList = new HashMap<>();

        gameList.put("GAMES WON", String.valueOf(gamesWon));
        gameList.put("TIME PLAYED", timePlayed);

        return gameList;
    }

}
