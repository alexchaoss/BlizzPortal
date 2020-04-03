package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 * The type Average.
 */
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

    /**
     * Gets all damage done avg per 10 min.
     *
     * @return the all damage done avg per 10 min
     */
    public Double getAllDamageDoneAvgPer10Min() {
        return allDamageDoneAvgPer10Min;
    }

    /**
     * Sets all damage done avg per 10 min.
     *
     * @param allDamageDoneAvgPer10Min the all damage done avg per 10 min
     */
    public void setAllDamageDoneAvgPer10Min(Double allDamageDoneAvgPer10Min) {
        this.allDamageDoneAvgPer10Min = allDamageDoneAvgPer10Min;
    }

    /**
     * Gets barrier damage done avg per 10 min.
     *
     * @return the barrier damage done avg per 10 min
     */
    public Double getBarrierDamageDoneAvgPer10Min() {
        return barrierDamageDoneAvgPer10Min;
    }

    /**
     * Sets barrier damage done avg per 10 min.
     *
     * @param barrierDamageDoneAvgPer10Min the barrier damage done avg per 10 min
     */
    public void setBarrierDamageDoneAvgPer10Min(Double barrierDamageDoneAvgPer10Min) {
        this.barrierDamageDoneAvgPer10Min = barrierDamageDoneAvgPer10Min;
    }

    /**
     * Gets deaths avg per 10 min.
     *
     * @return the deaths avg per 10 min
     */
    public float getDeathsAvgPer10Min() {
        return deathsAvgPer10Min;
    }

    /**
     * Sets deaths avg per 10 min.
     *
     * @param deathsAvgPer10Min the deaths avg per 10 min
     */
    public void setDeathsAvgPer10Min(float deathsAvgPer10Min) {
        this.deathsAvgPer10Min = deathsAvgPer10Min;
    }

    /**
     * Gets eliminations avg per 10 min.
     *
     * @return the eliminations avg per 10 min
     */
    public float getEliminationsAvgPer10Min() {
        return eliminationsAvgPer10Min;
    }

    /**
     * Sets eliminations avg per 10 min.
     *
     * @param eliminationsAvgPer10Min the eliminations avg per 10 min
     */
    public void setEliminationsAvgPer10Min(float eliminationsAvgPer10Min) {
        this.eliminationsAvgPer10Min = eliminationsAvgPer10Min;
    }

    /**
     * Gets final blows avg per 10 min.
     *
     * @return the final blows avg per 10 min
     */
    public float getFinalBlowsAvgPer10Min() {
        return finalBlowsAvgPer10Min;
    }

    /**
     * Sets final blows avg per 10 min.
     *
     * @param finalBlowsAvgPer10Min the final blows avg per 10 min
     */
    public void setFinalBlowsAvgPer10Min(float finalBlowsAvgPer10Min) {
        this.finalBlowsAvgPer10Min = finalBlowsAvgPer10Min;
    }

    /**
     * Gets healing done avg per 10 min.
     *
     * @return the healing done avg per 10 min
     */
    public Double getHealingDoneAvgPer10Min() {
        return healingDoneAvgPer10Min;
    }

    /**
     * Sets healing done avg per 10 min.
     *
     * @param healingDoneAvgPer10Min the healing done avg per 10 min
     */
    public void setHealingDoneAvgPer10Min(Double healingDoneAvgPer10Min) {
        this.healingDoneAvgPer10Min = healingDoneAvgPer10Min;
    }

    /**
     * Gets hero damage done avg per 10 min.
     *
     * @return the hero damage done avg per 10 min
     */
    public Double getHeroDamageDoneAvgPer10Min() {
        return heroDamageDoneAvgPer10Min;
    }

    /**
     * Sets hero damage done avg per 10 min.
     *
     * @param heroDamageDoneAvgPer10Min the hero damage done avg per 10 min
     */
    public void setHeroDamageDoneAvgPer10Min(Double heroDamageDoneAvgPer10Min) {
        this.heroDamageDoneAvgPer10Min = heroDamageDoneAvgPer10Min;
    }

    /**
     * Gets objective kills avg per 10 min.
     *
     * @return the objective kills avg per 10 min
     */
    public float getObjectiveKillsAvgPer10Min() {
        return objectiveKillsAvgPer10Min;
    }

    /**
     * Sets objective kills avg per 10 min.
     *
     * @param objectiveKillsAvgPer10Min the objective kills avg per 10 min
     */
    public void setObjectiveKillsAvgPer10Min(float objectiveKillsAvgPer10Min) {
        this.objectiveKillsAvgPer10Min = objectiveKillsAvgPer10Min;
    }

    /**
     * Gets objective time avg per 10 min.
     *
     * @return the objective time avg per 10 min
     */
    public String getObjectiveTimeAvgPer10Min() {
        return objectiveTimeAvgPer10Min;
    }

    /**
     * Sets objective time avg per 10 min.
     *
     * @param objectiveTimeAvgPer10Min the objective time avg per 10 min
     */
    public void setObjectiveTimeAvgPer10Min(String objectiveTimeAvgPer10Min) {
        this.objectiveTimeAvgPer10Min = objectiveTimeAvgPer10Min;
    }

    /**
     * Gets solo kills avg per 10 min.
     *
     * @return the solo kills avg per 10 min
     */
    public float getSoloKillsAvgPer10Min() {
        return soloKillsAvgPer10Min;
    }

    /**
     * Sets solo kills avg per 10 min.
     *
     * @param soloKillsAvgPer10Min the solo kills avg per 10 min
     */
    public void setSoloKillsAvgPer10Min(float soloKillsAvgPer10Min) {
        this.soloKillsAvgPer10Min = soloKillsAvgPer10Min;
    }

    /**
     * Gets time spent on fire avg per 10 min.
     *
     * @return the time spent on fire avg per 10 min
     */
    public String getTimeSpentOnFireAvgPer10Min() {
        return timeSpentOnFireAvgPer10Min;
    }

    /**
     * Sets time spent on fire avg per 10 min.
     *
     * @param timeSpentOnFireAvgPer10Min the time spent on fire avg per 10 min
     */
    public void setTimeSpentOnFireAvgPer10Min(String timeSpentOnFireAvgPer10Min) {
        this.timeSpentOnFireAvgPer10Min = timeSpentOnFireAvgPer10Min;
    }

    /**
     * Gets average.
     *
     * @return the average
     */
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
