package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class Awards {

    @SerializedName("cards")
    @Expose
    private Double cards = (double) 0;
    @SerializedName("medals")
    @Expose
    private Double medals = (double) 0;
    @SerializedName("medalsBronze")
    @Expose
    private Double medalsBronze = (double) 0;
    @SerializedName("medalsSilver")
    @Expose
    private Double medalsSilver = (double) 0;
    @SerializedName("medalsGold")
    @Expose
    private Double medalsGold = (double) 0;

    public Double getCards() {
        return cards;
    }

    public void setCards(Double cards) {
        this.cards = cards;
    }

    public Double getMedals() {
        return medals;
    }

    public void setMedals(Double medals) {
        this.medals = medals;
    }

    public Double getMedalsBronze() {
        return medalsBronze;
    }

    public void setMedalsBronze(Double medalsBronze) {
        this.medalsBronze = medalsBronze;
    }

    public Double getMedalsSilver() {
        return medalsSilver;
    }

    public void setMedalsSilver(Double medalsSilver) {
        this.medalsSilver = medalsSilver;
    }

    public Double getMedalsGold() {
        return medalsGold;
    }

    public void setMedalsGold(Double medalsGold) {
        this.medalsGold = medalsGold;
    }

    public HashMap<String, String> getAwards() {
        HashMap<String, String> awardsList = new HashMap<>();
        NumberFormat formatter = new DecimalFormat("#0");

        awardsList.put("CARDS", formatter.format(cards));
        awardsList.put("MEDALS", formatter.format(medals));
        awardsList.put("MEDALS - GOLD", formatter.format(medalsGold));
        awardsList.put("MEDALS - SILVER", formatter.format(medalsSilver));
        awardsList.put("MEDALS - BRONZE", formatter.format(medalsBronze));

        return awardsList;
    }

}
