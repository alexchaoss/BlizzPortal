package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 * The type Match awards.
 */
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

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public double getCards() {
        return cards;
    }

    /**
     * Sets cards.
     *
     * @param cards the cards
     */
    public void setCards(double cards) {
        this.cards = cards;
    }

    /**
     * Gets medals.
     *
     * @return the medals
     */
    public double getMedals() {
        return medals;
    }

    /**
     * Sets medals.
     *
     * @param medals the medals
     */
    public void setMedals(double medals) {
        this.medals = medals;
    }

    /**
     * Gets medals bronze.
     *
     * @return the medals bronze
     */
    public double getMedalsBronze() {
        return medalsBronze;
    }

    /**
     * Sets medals bronze.
     *
     * @param medalsBronze the medals bronze
     */
    public void setMedalsBronze(double medalsBronze) {
        this.medalsBronze = medalsBronze;
    }

    /**
     * Gets medals gold.
     *
     * @return the medals gold
     */
    public double getMedalsGold() {
        return medalsGold;
    }

    /**
     * Sets medals gold.
     *
     * @param medalsGold the medals gold
     */
    public void setMedalsGold(double medalsGold) {
        this.medalsGold = medalsGold;
    }

    /**
     * Gets medals silver.
     *
     * @return the medals silver
     */
    public double getMedalsSilver() {
        return medalsSilver;
    }

    /**
     * Sets medals silver.
     *
     * @param medalsSilver the medals silver
     */
    public void setMedalsSilver(double medalsSilver) {
        this.medalsSilver = medalsSilver;
    }

    /**
     * Gets match.
     *
     * @return the match
     */
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
