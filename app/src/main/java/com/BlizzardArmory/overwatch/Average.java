package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class Average {

    @SerializedName("allDamageDoneAvgPer10Min")
    @Expose
    private Double allDamageDoneAvgPer10Min = (double) 0;
    @SerializedName("barrierDamageDoneAvgPer10Min")
    @Expose
    private Double barrierDamageDoneAvgPer10Min = (double) 0;
    @SerializedName("deathsAvgPer10Min")
    @Expose
    private float deathsAvgPer10Min = 0;
    @SerializedName("eliminationsAvgPer10Min")
    @Expose
    private float eliminationsAvgPer10Min = 0;
    @SerializedName("finalBlowsAvgPer10Min")
    @Expose
    private float finalBlowsAvgPer10Min = 0;
    @SerializedName("healingDoneAvgPer10Min")
    @Expose
    private Double healingDoneAvgPer10Min = (double) 0;
    @SerializedName("heroDamageDoneAvgPer10Min")
    @Expose
    private Double heroDamageDoneAvgPer10Min = (double) 0;
    @SerializedName("objectiveKillsAvgPer10Min")
    @Expose
    private float objectiveKillsAvgPer10Min = 0;
    @SerializedName("objectiveTimeAvgPer10Min")
    @Expose
    private String objectiveTimeAvgPer10Min;
    @SerializedName("soloKillsAvgPer10Min")
    @Expose
    private float soloKillsAvgPer10Min = 0;
    @SerializedName("timeSpentOnFireAvgPer10Min")
    @Expose
    private String timeSpentOnFireAvgPer10Min;

    public Double getAllDamageDoneAvgPer10Min() {
        return allDamageDoneAvgPer10Min;
    }

    public void setAllDamageDoneAvgPer10Min(Double allDamageDoneAvgPer10Min) {
        this.allDamageDoneAvgPer10Min = allDamageDoneAvgPer10Min;
    }

    public Double getBarrierDamageDoneAvgPer10Min() {
        return barrierDamageDoneAvgPer10Min;
    }

    public void setBarrierDamageDoneAvgPer10Min(Double barrierDamageDoneAvgPer10Min) {
        this.barrierDamageDoneAvgPer10Min = barrierDamageDoneAvgPer10Min;
    }

    public float getDeathsAvgPer10Min() {
        return deathsAvgPer10Min;
    }

    public void setDeathsAvgPer10Min(float deathsAvgPer10Min) {
        this.deathsAvgPer10Min = deathsAvgPer10Min;
    }

    public float getEliminationsAvgPer10Min() {
        return eliminationsAvgPer10Min;
    }

    public void setEliminationsAvgPer10Min(float eliminationsAvgPer10Min) {
        this.eliminationsAvgPer10Min = eliminationsAvgPer10Min;
    }

    public float getFinalBlowsAvgPer10Min() {
        return finalBlowsAvgPer10Min;
    }

    public void setFinalBlowsAvgPer10Min(float finalBlowsAvgPer10Min) {
        this.finalBlowsAvgPer10Min = finalBlowsAvgPer10Min;
    }

    public Double getHealingDoneAvgPer10Min() {
        return healingDoneAvgPer10Min;
    }

    public void setHealingDoneAvgPer10Min(Double healingDoneAvgPer10Min) {
        this.healingDoneAvgPer10Min = healingDoneAvgPer10Min;
    }

    public Double getHeroDamageDoneAvgPer10Min() {
        return heroDamageDoneAvgPer10Min;
    }

    public void setHeroDamageDoneAvgPer10Min(Double heroDamageDoneAvgPer10Min) {
        this.heroDamageDoneAvgPer10Min = heroDamageDoneAvgPer10Min;
    }

    public float getObjectiveKillsAvgPer10Min() {
        return objectiveKillsAvgPer10Min;
    }

    public void setObjectiveKillsAvgPer10Min(float objectiveKillsAvgPer10Min) {
        this.objectiveKillsAvgPer10Min = objectiveKillsAvgPer10Min;
    }

    public String getObjectiveTimeAvgPer10Min() {
        return objectiveTimeAvgPer10Min;
    }

    public void setObjectiveTimeAvgPer10Min(String objectiveTimeAvgPer10Min) {
        this.objectiveTimeAvgPer10Min = objectiveTimeAvgPer10Min;
    }

    public float getSoloKillsAvgPer10Min() {
        return soloKillsAvgPer10Min;
    }

    public void setSoloKillsAvgPer10Min(float soloKillsAvgPer10Min) {
        this.soloKillsAvgPer10Min = soloKillsAvgPer10Min;
    }

    public String getTimeSpentOnFireAvgPer10Min() {
        return timeSpentOnFireAvgPer10Min;
    }

    public void setTimeSpentOnFireAvgPer10Min(String timeSpentOnFireAvgPer10Min) {
        this.timeSpentOnFireAvgPer10Min = timeSpentOnFireAvgPer10Min;
    }

    public HashMap<String, String> getAverage() {
        HashMap<String, String> averageList = new HashMap<>();
        NumberFormat formatter = new DecimalFormat("#0");

        averageList.put("ALL DAMAGE DONE - AVG PER 10 MIN", formatter.format(allDamageDoneAvgPer10Min));
        averageList.put("BARRIER DAMAGE DONE - AVR PER 10 MIN", formatter.format(barrierDamageDoneAvgPer10Min));
        averageList.put("DEATHS - AVG PER 10 MIN", String.valueOf(deathsAvgPer10Min));
        averageList.put("ELEMINATIONS - AVG PER 10 MIN", String.valueOf(eliminationsAvgPer10Min));
        averageList.put("FINAL BLOWS - AVG PER 10 MIN", String.valueOf(finalBlowsAvgPer10Min));
        averageList.put("HEALING DONE - AVG PER 10 MIN", formatter.format(healingDoneAvgPer10Min));
        averageList.put("HERO DAMAGE DONE - AVG PER 10 MIN", formatter.format(heroDamageDoneAvgPer10Min));
        averageList.put("OBJECTIVE KILLS - AVG PER 10 MIN", String.valueOf(objectiveKillsAvgPer10Min));
        averageList.put("OBJECTIVE TIME - AVG PER 10 MIN", objectiveTimeAvgPer10Min);
        averageList.put("SOLO KILLS - AVG PER 10 MIN", String.valueOf(soloKillsAvgPer10Min));
        averageList.put("TIME SPENT ON FIRE - AVG PER 10 MIN", timeSpentOnFireAvgPer10Min);

        return averageList;
    }

}
