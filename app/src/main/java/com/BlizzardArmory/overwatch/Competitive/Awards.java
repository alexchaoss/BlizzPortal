package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Awards {

    @SerializedName("cards")
    @Expose
    private int cards;
    @SerializedName("medals")
    @Expose
    private int medals;
    @SerializedName("medalsBronze")
    @Expose
    private int medalsBronze;
    @SerializedName("medalsSilver")
    @Expose
    private int medalsSilver;
    @SerializedName("medalsGold")
    @Expose
    private int medalsGold;

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

    public int getMedalsSilver() {
        return medalsSilver;
    }

    public void setMedalsSilver(int medalsSilver) {
        this.medalsSilver = medalsSilver;
    }

    public int getMedalsGold() {
        return medalsGold;
    }

    public void setMedalsGold(int medalsGold) {
        this.medalsGold = medalsGold;
    }

}
