package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

public class Best {

    @SerializedName("allDamageDoneMostInGame")
    @Expose
    private Double allDamageDoneMostInGame = (double) 0;
    @SerializedName("barrierDamageDoneMostInGame")
    @Expose
    private Double barrierDamageDoneMostInGame = (double) 0;
    @SerializedName("defensiveAssistsMostInGame")
    @Expose
    private Double defensiveAssistsMostInGame = (double) 0;
    @SerializedName("eliminationsMostInGame")
    @Expose
    private Double eliminationsMostInGame = (double) 0;
    @SerializedName("environmentalKillsMostInGame")
    @Expose
    private Double environmentalKillsMostInGame = (double) 0;
    @SerializedName("finalBlowsMostInGame")
    @Expose
    private Double finalBlowsMostInGame = (double) 0;
    @SerializedName("healingDoneMostInGame")
    @Expose
    private Double healingDoneMostInGame = (double) 0;
    @SerializedName("heroDamageDoneMostInGame")
    @Expose
    private Double heroDamageDoneMostInGame = (double) 0;
    @SerializedName("killsStreakBest")
    @Expose
    private Double killsStreakBest = (double) 0;
    @SerializedName("meleeFinalBlowsMostInGame")
    @Expose
    private Double meleeFinalBlowsMostInGame = (double) 0;
    @SerializedName("multikillsBest")
    @Expose
    private Double multikillsBest = (double) 0;
    @SerializedName("objectiveKillsMostInGame")
    @Expose
    private Double objectiveKillsMostInGame = (double) 0;
    @SerializedName("objectiveTimeMostInGame")
    @Expose
    private String objectiveTimeMostInGame;
    @SerializedName("offensiveAssistsMostInGame")
    @Expose
    private Double offensiveAssistsMostInGame = (double) 0;
    @SerializedName("reconAssistsMostInGame")
    @Expose
    private Double reconAssistsMostInGame = (double) 0;
    @SerializedName("soloKillsMostInGame")
    @Expose
    private Double soloKillsMostInGame = (double) 0;
    @SerializedName("teleporterPadsDestroyedMostInGame")
    @Expose
    private Double teleporterPadsDestroyedMostInGame = (double) 0;
    @SerializedName("timeSpentOnFireMostInGame")
    @Expose
    private String timeSpentOnFireMostInGame;
    @SerializedName("turretsDestroyedMostInGame")
    @Expose
    private Double turretsDestroyedMostInGame = (double) 0;

    public Double getAllDamageDoneMostInGame() {
        return allDamageDoneMostInGame;
    }

    public void setAllDamageDoneMostInGame(Double allDamageDoneMostInGame) {
        this.allDamageDoneMostInGame = allDamageDoneMostInGame;
    }

    public Double getBarrierDamageDoneMostInGame() {
        return barrierDamageDoneMostInGame;
    }

    public void setBarrierDamageDoneMostInGame(Double barrierDamageDoneMostInGame) {
        this.barrierDamageDoneMostInGame = barrierDamageDoneMostInGame;
    }

    public Double getDefensiveAssistsMostInGame() {
        return defensiveAssistsMostInGame;
    }

    public void setDefensiveAssistsMostInGame(Double defensiveAssistsMostInGame) {
        this.defensiveAssistsMostInGame = defensiveAssistsMostInGame;
    }

    public Double getEliminationsMostInGame() {
        return eliminationsMostInGame;
    }

    public void setEliminationsMostInGame(Double eliminationsMostInGame) {
        this.eliminationsMostInGame = eliminationsMostInGame;
    }

    public Double getEnvironmentalKillsMostInGame() {
        return environmentalKillsMostInGame;
    }

    public void setEnvironmentalKillsMostInGame(Double environmentalKillsMostInGame) {
        this.environmentalKillsMostInGame = environmentalKillsMostInGame;
    }

    public Double getFinalBlowsMostInGame() {
        return finalBlowsMostInGame;
    }

    public void setFinalBlowsMostInGame(Double finalBlowsMostInGame) {
        this.finalBlowsMostInGame = finalBlowsMostInGame;
    }

    public Double getHealingDoneMostInGame() {
        return healingDoneMostInGame;
    }

    public void setHealingDoneMostInGame(Double healingDoneMostInGame) {
        this.healingDoneMostInGame = healingDoneMostInGame;
    }

    public Double getHeroDamageDoneMostInGame() {
        return heroDamageDoneMostInGame;
    }

    public void setHeroDamageDoneMostInGame(Double heroDamageDoneMostInGame) {
        this.heroDamageDoneMostInGame = heroDamageDoneMostInGame;
    }

    public Double getKillsStreakBest() {
        return killsStreakBest;
    }

    public void setKillsStreakBest(Double killsStreakBest) {
        this.killsStreakBest = killsStreakBest;
    }

    public Double getMeleeFinalBlowsMostInGame() {
        return meleeFinalBlowsMostInGame;
    }

    public void setMeleeFinalBlowsMostInGame(Double meleeFinalBlowsMostInGame) {
        this.meleeFinalBlowsMostInGame = meleeFinalBlowsMostInGame;
    }

    public Double getMultikillsBest() {
        return multikillsBest;
    }

    public void setMultikillsBest(Double multikillsBest) {
        this.multikillsBest = multikillsBest;
    }

    public Double getObjectiveKillsMostInGame() {
        return objectiveKillsMostInGame;
    }

    public void setObjectiveKillsMostInGame(Double objectiveKillsMostInGame) {
        this.objectiveKillsMostInGame = objectiveKillsMostInGame;
    }

    public String getObjectiveTimeMostInGame() {
        return objectiveTimeMostInGame;
    }

    public void setObjectiveTimeMostInGame(String objectiveTimeMostInGame) {
        this.objectiveTimeMostInGame = objectiveTimeMostInGame;
    }

    public Double getOffensiveAssistsMostInGame() {
        return offensiveAssistsMostInGame;
    }

    public void setOffensiveAssistsMostInGame(Double offensiveAssistsMostInGame) {
        this.offensiveAssistsMostInGame = offensiveAssistsMostInGame;
    }

    public Double getReconAssistsMostInGame() {
        return reconAssistsMostInGame;
    }

    public void setReconAssistsMostInGame(Double reconAssistsMostInGame) {
        this.reconAssistsMostInGame = reconAssistsMostInGame;
    }

    public Double getSoloKillsMostInGame() {
        return soloKillsMostInGame;
    }

    public void setSoloKillsMostInGame(Double soloKillsMostInGame) {
        this.soloKillsMostInGame = soloKillsMostInGame;
    }

    public Double getTeleporterPadsDestroyedMostInGame() {
        return teleporterPadsDestroyedMostInGame;
    }

    public void setTeleporterPadsDestroyedMostInGame(Double teleporterPadsDestroyedMostInGame) {
        this.teleporterPadsDestroyedMostInGame = teleporterPadsDestroyedMostInGame;
    }

    public String getTimeSpentOnFireMostInGame() {
        return timeSpentOnFireMostInGame;
    }

    public void setTimeSpentOnFireMostInGame(String timeSpentOnFireMostInGame) {
        this.timeSpentOnFireMostInGame = timeSpentOnFireMostInGame;
    }

    public Double getTurretsDestroyedMostInGame() {
        return turretsDestroyedMostInGame;
    }

    public void setTurretsDestroyedMostInGame(Double turretsDestroyedMostInGame) {
        this.turretsDestroyedMostInGame = turretsDestroyedMostInGame;
    }

    public HashMap<String, String> getBestList() {
        NumberFormat formatter = new DecimalFormat("#0");
        HashMap<String, String> best = new HashMap<>();
        best.put("ALL DAMAGE DONE ", formatter.format(allDamageDoneMostInGame));
        best.put("BARRIER DAMAGE DONE ", formatter.format(barrierDamageDoneMostInGame));
        best.put("DEFENSIVE ASSIST - MOST IN GAME ", formatter.format(defensiveAssistsMostInGame));
        best.put("ELEMINATIONS - MOST IN GAME ", formatter.format(eliminationsMostInGame));
        best.put("ENVIRONMENTAL KILL - MOST IN GAME ", formatter.format(environmentalKillsMostInGame));
        best.put("FINAL BLOWS - MOST IN GAME ", formatter.format(finalBlowsMostInGame));
        best.put("HEALING DONE - MOST IN GAME ", formatter.format(healingDoneMostInGame));
        best.put("HERO DAMAGE DONE - MOST IN GAME ", formatter.format(heroDamageDoneMostInGame));
        best.put("KILL STREAK BEST ", formatter.format(killsStreakBest));
        best.put("MELEE FINAL BLOWS - MOST IN GAME ", formatter.format(meleeFinalBlowsMostInGame));
        best.put("MULTIKILL BEST ", formatter.format(multikillsBest));
        best.put("OBJECTIVE KILLS - MOST IN GAME ", formatter.format(objectiveKillsMostInGame));
        best.put("OBJECTIVE TIME - MOST IN GAME ", objectiveTimeMostInGame);
        best.put("OFFENSIVE ASSISTS - MOST IN GAME ", formatter.format(offensiveAssistsMostInGame));
        best.put("RECON ASSISTS - MOST IN GAME ", formatter.format(reconAssistsMostInGame));
        best.put("SOLO KILLS - MOST IN GAME ", formatter.format(soloKillsMostInGame));
        best.put("TELEPORTER PADS DESTROYED - MOST IN GAME ", formatter.format(teleporterPadsDestroyedMostInGame));
        best.put("TIME SPENT ON FIRE - MOST IN GAME ", timeSpentOnFireMostInGame);
        best.put("TURRETS DESTROYED - MOST IN GAME ", formatter.format(turretsDestroyedMostInGame));

        return best;
    }

}
