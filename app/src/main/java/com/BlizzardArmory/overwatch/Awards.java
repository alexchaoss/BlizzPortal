package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 * The type Awards.
 */
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

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public Double getCards() {
        return cards;
    }

    /**
     * Sets cards.
     *
     * @param cards the cards
     */
    public void setCards(Double cards) {
        this.cards = cards;
    }

    /**
     * Gets medals.
     *
     * @return the medals
     */
    public Double getMedals() {
        return medals;
    }

    /**
     * Sets medals.
     *
     * @param medals the medals
     */
    public void setMedals(Double medals) {
        this.medals = medals;
    }

    /**
     * Gets medals bronze.
     *
     * @return the medals bronze
     */
    public Double getMedalsBronze() {
        return medalsBronze;
    }

    /**
     * Sets medals bronze.
     *
     * @param medalsBronze the medals bronze
     */
    public void setMedalsBronze(Double medalsBronze) {
        this.medalsBronze = medalsBronze;
    }

    /**
     * Gets medals silver.
     *
     * @return the medals silver
     */
    public Double getMedalsSilver() {
        return medalsSilver;
    }

    /**
     * Sets medals silver.
     *
     * @param medalsSilver the medals silver
     */
    public void setMedalsSilver(Double medalsSilver) {
        this.medalsSilver = medalsSilver;
    }

    /**
     * Gets medals gold.
     *
     * @return the medals gold
     */
    public Double getMedalsGold() {
        return medalsGold;
    }

    /**
     * Sets medals gold.
     *
     * @param medalsGold the medals gold
     */
    public void setMedalsGold(Double medalsGold) {
        this.medalsGold = medalsGold;
    }

    /**
     * Gets awards.
     *
     * @return the awards
     */
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
