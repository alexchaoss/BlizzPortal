package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

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

    public Double getBarrierDamageDone() {
        return barrierDamageDone;
    }

    public void setBarrierDamageDone(Double barrierDamageDone) {
        this.barrierDamageDone = barrierDamageDone;
    }

    public Double getDamageDone() {
        return damageDone;
    }

    public void setDamageDone(Double damageDone) {
        this.damageDone = damageDone;
    }

    public Double getDeaths() {
        return deaths;
    }

    public void setDeaths(Double deaths) {
        this.deaths = deaths;
    }

    public Double getEliminations() {
        return eliminations;
    }

    public void setEliminations(Double eliminations) {
        this.eliminations = eliminations;
    }

    public Double getEnvironmentalKills() {
        return environmentalKills;
    }

    public void setEnvironmentalKills(Double environmentalKills) {
        this.environmentalKills = environmentalKills;
    }

    public Double getFinalBlows() {
        return finalBlows;
    }

    public void setFinalBlows(Double finalBlows) {
        this.finalBlows = finalBlows;
    }

    public Double getHeroDamageDone() {
        return heroDamageDone;
    }

    public void setHeroDamageDone(Double heroDamageDone) {
        this.heroDamageDone = heroDamageDone;
    }

    public Double getMeleeFinalBlows() {
        return meleeFinalBlows;
    }

    public void setMeleeFinalBlows(Double meleeFinalBlows) {
        this.meleeFinalBlows = meleeFinalBlows;
    }

    public Double getMultikills() {
        return multikills;
    }

    public void setMultikills(Double multikills) {
        this.multikills = multikills;
    }

    public Double getObjectiveKills() {
        return objectiveKills;
    }

    public void setObjectiveKills(Double objectiveKills) {
        this.objectiveKills = objectiveKills;
    }

    public String getObjectiveTime() {
        return objectiveTime;
    }

    public void setObjectiveTime(String objectiveTime) {
        this.objectiveTime = objectiveTime;
    }

    public Double getSoloKills() {
        return soloKills;
    }

    public void setSoloKills(Double soloKills) {
        this.soloKills = soloKills;
    }

    public String getTimeSpentOnFire() {
        return timeSpentOnFire;
    }

    public void setTimeSpentOnFire(String timeSpentOnFire) {
        this.timeSpentOnFire = timeSpentOnFire;
    }

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
