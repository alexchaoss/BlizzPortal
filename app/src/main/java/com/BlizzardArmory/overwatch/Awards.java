package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Awards {

    @SerializedName("cards")
    @Expose
    private double cards;
    @SerializedName("medals")
    @Expose
    private double medals;
    @SerializedName("medalsBronze")
    @Expose
    private double medalsBronze;
    @SerializedName("medalsSilver")
    @Expose
    private double medalsSilver;
    @SerializedName("medalsGold")
    @Expose
    private double medalsGold;

    public double getCards() {
        return cards;
    }

    public void setCards(double cards) {
        this.cards = cards;
    }

    public double getMedals() {
        return medals;
    }

    public void setMedals(double medals) {
        this.medals = medals;
    }

    public double getMedalsBronze() {
        return medalsBronze;
    }

    public void setMedalsBronze(double medalsBronze) {
        this.medalsBronze = medalsBronze;
    }

    public double getMedalsSilver() {
        return medalsSilver;
    }

    public void setMedalsSilver(double medalsSilver) {
        this.medalsSilver = medalsSilver;
    }

    public double getMedalsGold() {
        return medalsGold;
    }

    public void setMedalsGold(double medalsGold) {
        this.medalsGold = medalsGold;
    }

}
