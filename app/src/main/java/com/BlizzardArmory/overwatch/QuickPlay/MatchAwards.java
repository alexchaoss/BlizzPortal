
package com.BlizzardArmory.overwatch.QuickPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchAwards {

    @SerializedName("cards")
    @Expose
    private int cards;
    @SerializedName("medals")
    @Expose
    private int medals;
    @SerializedName("medalsBronze")
    @Expose
    private int medalsBronze;
    @SerializedName("medalsGold")
    @Expose
    private int medalsGold;
    @SerializedName("medalsSilver")
    @Expose
    private int medalsSilver;

    public int getCards() {
        return cards;
    }

    public void setCards(int cards) {
        this.cards = cards;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    public int getMedalsBronze() {
        return medalsBronze;
    }

    public void setMedalsBronze(int medalsBronze) {
        this.medalsBronze = medalsBronze;
    }

    public int getMedalsGold() {
        return medalsGold;
    }

    public void setMedalsGold(int medalsGold) {
        this.medalsGold = medalsGold;
    }

    public int getMedalsSilver() {
        return medalsSilver;
    }

    public void setMedalsSilver(int medalsSilver) {
        this.medalsSilver = medalsSilver;
    }

}
