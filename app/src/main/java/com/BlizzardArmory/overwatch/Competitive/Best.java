
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Best {

    @SerializedName("allDamageDoneMostInGame")
    @Expose
    private int allDamageDoneMostInGame;
    @SerializedName("eliminationsMostInGame")
    @Expose
    private int eliminationsMostInGame;
    @SerializedName("finalBlowsMostInGame")
    @Expose
    private int finalBlowsMostInGame;
    @SerializedName("healingDoneMostInGame")
    @Expose
    private int healingDoneMostInGame;
    @SerializedName("killsStreakBest")
    @Expose
    private int killsStreakBest;
    @SerializedName("meleeFinalBlowsMostInGame")
    @Expose
    private int meleeFinalBlowsMostInGame;
    @SerializedName("objectiveKillsMostInGame")
    @Expose
    private int objectiveKillsMostInGame;
    @SerializedName("objectiveTimeMostInGame")
    @Expose
    private String objectiveTimeMostInGame;
    @SerializedName("soloKillsMostInGame")
    @Expose
    private int soloKillsMostInGame;
    @SerializedName("timeSpentOnFireMostInGame")
    @Expose
    private String timeSpentOnFireMostInGame;
    @SerializedName("turretsDestroyedMostInGame")
    @Expose
    private int turretsDestroyedMostInGame;

    public int getAllDamageDoneMostInGame() {
        return allDamageDoneMostInGame;
    }

    public void setAllDamageDoneMostInGame(int allDamageDoneMostInGame) {
        this.allDamageDoneMostInGame = allDamageDoneMostInGame;
    }

    public int getEliminationsMostInGame() {
        return eliminationsMostInGame;
    }

    public void setEliminationsMostInGame(int eliminationsMostInGame) {
        this.eliminationsMostInGame = eliminationsMostInGame;
    }

    public int getFinalBlowsMostInGame() {
        return finalBlowsMostInGame;
    }

    public void setFinalBlowsMostInGame(int finalBlowsMostInGame) {
        this.finalBlowsMostInGame = finalBlowsMostInGame;
    }

    public int getHealingDoneMostInGame() {
        return healingDoneMostInGame;
    }

    public void setHealingDoneMostInGame(int healingDoneMostInGame) {
        this.healingDoneMostInGame = healingDoneMostInGame;
    }

    public int getKillsStreakBest() {
        return killsStreakBest;
    }

    public void setKillsStreakBest(int killsStreakBest) {
        this.killsStreakBest = killsStreakBest;
    }

    public int getMeleeFinalBlowsMostInGame() {
        return meleeFinalBlowsMostInGame;
    }

    public void setMeleeFinalBlowsMostInGame(int meleeFinalBlowsMostInGame) {
        this.meleeFinalBlowsMostInGame = meleeFinalBlowsMostInGame;
    }

    public int getObjectiveKillsMostInGame() {
        return objectiveKillsMostInGame;
    }

    public void setObjectiveKillsMostInGame(int objectiveKillsMostInGame) {
        this.objectiveKillsMostInGame = objectiveKillsMostInGame;
    }

    public String getObjectiveTimeMostInGame() {
        return objectiveTimeMostInGame;
    }

    public void setObjectiveTimeMostInGame(String objectiveTimeMostInGame) {
        this.objectiveTimeMostInGame = objectiveTimeMostInGame;
    }

    public int getSoloKillsMostInGame() {
        return soloKillsMostInGame;
    }

    public void setSoloKillsMostInGame(int soloKillsMostInGame) {
        this.soloKillsMostInGame = soloKillsMostInGame;
    }

    public String getTimeSpentOnFireMostInGame() {
        return timeSpentOnFireMostInGame;
    }

    public void setTimeSpentOnFireMostInGame(String timeSpentOnFireMostInGame) {
        this.timeSpentOnFireMostInGame = timeSpentOnFireMostInGame;
    }

    public int getTurretsDestroyedMostInGame() {
        return turretsDestroyedMostInGame;
    }

    public void setTurretsDestroyedMostInGame(int turretsDestroyedMostInGame) {
        this.turretsDestroyedMostInGame = turretsDestroyedMostInGame;
    }

}
