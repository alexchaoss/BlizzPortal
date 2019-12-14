package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class MatchAwards {

    @SerializedName("cards")
    @Expose
    private double cards;
    @SerializedName("medals")
    @Expose
    private double medals;
    @SerializedName("medalsBronze")
    @Expose
    private double medalsBronze;
    @SerializedName("medalsGold")
    @Expose
    private double medalsGold;
    @SerializedName("medalsSilver")
    @Expose
    private double medalsSilver;

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

    public double getMedalsGold() {
        return medalsGold;
    }

    public void setMedalsGold(double medalsGold) {
        this.medalsGold = medalsGold;
    }

    public double getMedalsSilver() {
        return medalsSilver;
    }

    public void setMedalsSilver(double medalsSilver) {
        this.medalsSilver = medalsSilver;
    }

    public HashMap<String, String> getMatch() {
        HashMap<String, String> matchList = new HashMap<>();
        NumberFormat formatter = new DecimalFormat("#0");

        matchList.put("CARDS", formatter.format(cards));
        matchList.put("MEDALS", formatter.format(medals));
        matchList.put("MEDALS - GOLD", formatter.format(medalsGold));
        matchList.put("MEDALS - SILVER", formatter.format(medalsSilver));
        matchList.put("MEDALS - BRONZE", formatter.format(medalsBronze));

        return matchList;
    }

}
