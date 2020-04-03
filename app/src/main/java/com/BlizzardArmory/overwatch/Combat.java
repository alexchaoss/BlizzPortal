package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 * The type Combat.
 */
public class Combat {

    @SerializedName("barrierDamageDone")
    @Expose
    private Double barrierDamageDone = (double) 0;
    @SerializedName("damageDone")
    @Expose
    private Double damageDone = (double) 0;
    @SerializedName("deaths")
    @Expose
    private Double deaths = (double) 0;
    @SerializedName("eliminations")
    @Expose
    private Double eliminations = (double) 0;
    @SerializedName("environmentalKills")
    @Expose
    private Double environmentalKills = (double) 0;
    @SerializedName("finalBlows")
    @Expose
    private Double finalBlows = (double) 0;
    @SerializedName("heroDamageDone")
    @Expose
    private Double heroDamageDone = (double) 0;
    @SerializedName("meleeFinalBlows")
    @Expose
    private Double meleeFinalBlows = (double) 0;
    @SerializedName("multikills")
    @Expose
    private Double multikills = (double) 0;
    @SerializedName("objectiveKills")
    @Expose
    private Double objectiveKills = (double) 0;
    @SerializedName("objectiveTime")
    @Expose
    private String objectiveTime;
    @SerializedName("soloKills")
    @Expose
    private Double soloKills = (double) 0;
    @SerializedName("timeSpentOnFire")
    @Expose
    private String timeSpentOnFire;

    /**
     * Gets barrier damage done.
     *
     * @return the barrier damage done
     */
    public Double getBarrierDamageDone() {
        return barrierDamageDone;
    }

    /**
     * Sets barrier damage done.
     *
     * @param barrierDamageDone the barrier damage done
     */
    public void setBarrierDamageDone(Double barrierDamageDone) {
        this.barrierDamageDone = barrierDamageDone;
    }

    /**
     * Gets damage done.
     *
     * @return the damage done
     */
    public Double getDamageDone() {
        return damageDone;
    }

    /**
     * Sets damage done.
     *
     * @param damageDone the damage done
     */
    public void setDamageDone(Double damageDone) {
        this.damageDone = damageDone;
    }

    /**
     * Gets deaths.
     *
     * @return the deaths
     */
    public Double getDeaths() {
        return deaths;
    }

    /**
     * Sets deaths.
     *
     * @param deaths the deaths
     */
    public void setDeaths(Double deaths) {
        this.deaths = deaths;
    }

    /**
     * Gets eliminations.
     *
     * @return the eliminations
     */
    public Double getEliminations() {
        return eliminations;
    }

    /**
     * Sets eliminations.
     *
     * @param eliminations the eliminations
     */
    public void setEliminations(Double eliminations) {
        this.eliminations = eliminations;
    }

    /**
     * Gets environmental kills.
     *
     * @return the environmental kills
     */
    public Double getEnvironmentalKills() {
        return environmentalKills;
    }

    /**
     * Sets environmental kills.
     *
     * @param environmentalKills the environmental kills
     */
    public void setEnvironmentalKills(Double environmentalKills) {
        this.environmentalKills = environmentalKills;
    }

    /**
     * Gets final blows.
     *
     * @return the final blows
     */
    public Double getFinalBlows() {
        return finalBlows;
    }

    /**
     * Sets final blows.
     *
     * @param finalBlows the final blows
     */
    public void setFinalBlows(Double finalBlows) {
        this.finalBlows = finalBlows;
    }

    /**
     * Gets hero damage done.
     *
     * @return the hero damage done
     */
    public Double getHeroDamageDone() {
        return heroDamageDone;
    }

    /**
     * Sets hero damage done.
     *
     * @param heroDamageDone the hero damage done
     */
    public void setHeroDamageDone(Double heroDamageDone) {
        this.heroDamageDone = heroDamageDone;
    }

    /**
     * Gets melee final blows.
     *
     * @return the melee final blows
     */
    public Double getMeleeFinalBlows() {
        return meleeFinalBlows;
    }

    /**
     * Sets melee final blows.
     *
     * @param meleeFinalBlows the melee final blows
     */
    public void setMeleeFinalBlows(Double meleeFinalBlows) {
        this.meleeFinalBlows = meleeFinalBlows;
    }

    /**
     * Gets multikills.
     *
     * @return the multikills
     */
    public Double getMultikills() {
        return multikills;
    }

    /**
     * Sets multikills.
     *
     * @param multikills the multikills
     */
    public void setMultikills(Double multikills) {
        this.multikills = multikills;
    }

    /**
     * Gets objective kills.
     *
     * @return the objective kills
     */
    public Double getObjectiveKills() {
        return objectiveKills;
    }

    /**
     * Sets objective kills.
     *
     * @param objectiveKills the objective kills
     */
    public void setObjectiveKills(Double objectiveKills) {
        this.objectiveKills = objectiveKills;
    }

    /**
     * Gets objective time.
     *
     * @return the objective time
     */
    public String getObjectiveTime() {
        return objectiveTime;
    }

    /**
     * Sets objective time.
     *
     * @param objectiveTime the objective time
     */
    public void setObjectiveTime(String objectiveTime) {
        this.objectiveTime = objectiveTime;
    }

    /**
     * Gets solo kills.
     *
     * @return the solo kills
     */
    public Double getSoloKills() {
        return soloKills;
    }

    /**
     * Sets solo kills.
     *
     * @param soloKills the solo kills
     */
    public void setSoloKills(Double soloKills) {
        this.soloKills = soloKills;
    }

    /**
     * Gets time spent on fire.
     *
     * @return the time spent on fire
     */
    public String getTimeSpentOnFire() {
        return timeSpentOnFire;
    }

    /**
     * Sets time spent on fire.
     *
     * @param timeSpentOnFire the time spent on fire
     */
    public void setTimeSpentOnFire(String timeSpentOnFire) {
        this.timeSpentOnFire = timeSpentOnFire;
    }

    /**
     * Gets combat.
     *
     * @return the combat
     */
    public HashMap<String, String> getCombat() {
        HashMap<String, String> combatList = new HashMap<>();
        NumberFormat formatter = new DecimalFormat("#0");

        combatList.put("BARRIER DAMAGE DONE", formatter.format(barrierDamageDone));
        combatList.put("DAMAGE DONE", formatter.format(damageDone));
        combatList.put("DEATHS", formatter.format(deaths));
        combatList.put("ELIMINATIONS", formatter.format(eliminations));
        combatList.put("ENVIRONMENTAL KILLS", formatter.format(environmentalKills));
        combatList.put("FINAL BLOWS", formatter.format(finalBlows));
        combatList.put("HERO DAMAGE DONE", formatter.format(heroDamageDone));
        combatList.put("MELEE FINAL BLOWS", formatter.format(meleeFinalBlows));
        combatList.put("MULTIKILLS", formatter.format(multikills));
        combatList.put("OBJECTIVE KILLS", formatter.format(objectiveKills));
        combatList.put("OBJECTIVE TIME", objectiveTime);
        combatList.put("SOLO KILLS", formatter.format(soloKills));
        combatList.put("TIME SPENT ON FIRE", timeSpentOnFire);

        return combatList;
    }

}
