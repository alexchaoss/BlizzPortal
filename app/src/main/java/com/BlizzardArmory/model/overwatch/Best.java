package com.BlizzardArmory.model.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 * The type Best.
 */
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

    /**
     * Gets all damage done most in game.
     *
     * @return the all damage done most in game
     */
    public Double getAllDamageDoneMostInGame() {
        return allDamageDoneMostInGame;
    }

    /**
     * Sets all damage done most in game.
     *
     * @param allDamageDoneMostInGame the all damage done most in game
     */
    public void setAllDamageDoneMostInGame(Double allDamageDoneMostInGame) {
        this.allDamageDoneMostInGame = allDamageDoneMostInGame;
    }

    /**
     * Gets barrier damage done most in game.
     *
     * @return the barrier damage done most in game
     */
    public Double getBarrierDamageDoneMostInGame() {
        return barrierDamageDoneMostInGame;
    }

    /**
     * Sets barrier damage done most in game.
     *
     * @param barrierDamageDoneMostInGame the barrier damage done most in game
     */
    public void setBarrierDamageDoneMostInGame(Double barrierDamageDoneMostInGame) {
        this.barrierDamageDoneMostInGame = barrierDamageDoneMostInGame;
    }

    /**
     * Gets defensive assists most in game.
     *
     * @return the defensive assists most in game
     */
    public Double getDefensiveAssistsMostInGame() {
        return defensiveAssistsMostInGame;
    }

    /**
     * Sets defensive assists most in game.
     *
     * @param defensiveAssistsMostInGame the defensive assists most in game
     */
    public void setDefensiveAssistsMostInGame(Double defensiveAssistsMostInGame) {
        this.defensiveAssistsMostInGame = defensiveAssistsMostInGame;
    }

    /**
     * Gets eliminations most in game.
     *
     * @return the eliminations most in game
     */
    public Double getEliminationsMostInGame() {
        return eliminationsMostInGame;
    }

    /**
     * Sets eliminations most in game.
     *
     * @param eliminationsMostInGame the eliminations most in game
     */
    public void setEliminationsMostInGame(Double eliminationsMostInGame) {
        this.eliminationsMostInGame = eliminationsMostInGame;
    }

    /**
     * Gets environmental kills most in game.
     *
     * @return the environmental kills most in game
     */
    public Double getEnvironmentalKillsMostInGame() {
        return environmentalKillsMostInGame;
    }

    /**
     * Sets environmental kills most in game.
     *
     * @param environmentalKillsMostInGame the environmental kills most in game
     */
    public void setEnvironmentalKillsMostInGame(Double environmentalKillsMostInGame) {
        this.environmentalKillsMostInGame = environmentalKillsMostInGame;
    }

    /**
     * Gets final blows most in game.
     *
     * @return the final blows most in game
     */
    public Double getFinalBlowsMostInGame() {
        return finalBlowsMostInGame;
    }

    /**
     * Sets final blows most in game.
     *
     * @param finalBlowsMostInGame the final blows most in game
     */
    public void setFinalBlowsMostInGame(Double finalBlowsMostInGame) {
        this.finalBlowsMostInGame = finalBlowsMostInGame;
    }

    /**
     * Gets healing done most in game.
     *
     * @return the healing done most in game
     */
    public Double getHealingDoneMostInGame() {
        return healingDoneMostInGame;
    }

    /**
     * Sets healing done most in game.
     *
     * @param healingDoneMostInGame the healing done most in game
     */
    public void setHealingDoneMostInGame(Double healingDoneMostInGame) {
        this.healingDoneMostInGame = healingDoneMostInGame;
    }

    /**
     * Gets hero damage done most in game.
     *
     * @return the hero damage done most in game
     */
    public Double getHeroDamageDoneMostInGame() {
        return heroDamageDoneMostInGame;
    }

    /**
     * Sets hero damage done most in game.
     *
     * @param heroDamageDoneMostInGame the hero damage done most in game
     */
    public void setHeroDamageDoneMostInGame(Double heroDamageDoneMostInGame) {
        this.heroDamageDoneMostInGame = heroDamageDoneMostInGame;
    }

    /**
     * Gets kills streak best.
     *
     * @return the kills streak best
     */
    public Double getKillsStreakBest() {
        return killsStreakBest;
    }

    /**
     * Sets kills streak best.
     *
     * @param killsStreakBest the kills streak best
     */
    public void setKillsStreakBest(Double killsStreakBest) {
        this.killsStreakBest = killsStreakBest;
    }

    /**
     * Gets melee final blows most in game.
     *
     * @return the melee final blows most in game
     */
    public Double getMeleeFinalBlowsMostInGame() {
        return meleeFinalBlowsMostInGame;
    }

    /**
     * Sets melee final blows most in game.
     *
     * @param meleeFinalBlowsMostInGame the melee final blows most in game
     */
    public void setMeleeFinalBlowsMostInGame(Double meleeFinalBlowsMostInGame) {
        this.meleeFinalBlowsMostInGame = meleeFinalBlowsMostInGame;
    }

    /**
     * Gets multikills best.
     *
     * @return the multikills best
     */
    public Double getMultikillsBest() {
        return multikillsBest;
    }

    /**
     * Sets multikills best.
     *
     * @param multikillsBest the multikills best
     */
    public void setMultikillsBest(Double multikillsBest) {
        this.multikillsBest = multikillsBest;
    }

    /**
     * Gets objective kills most in game.
     *
     * @return the objective kills most in game
     */
    public Double getObjectiveKillsMostInGame() {
        return objectiveKillsMostInGame;
    }

    /**
     * Sets objective kills most in game.
     *
     * @param objectiveKillsMostInGame the objective kills most in game
     */
    public void setObjectiveKillsMostInGame(Double objectiveKillsMostInGame) {
        this.objectiveKillsMostInGame = objectiveKillsMostInGame;
    }

    /**
     * Gets objective time most in game.
     *
     * @return the objective time most in game
     */
    public String getObjectiveTimeMostInGame() {
        return objectiveTimeMostInGame;
    }

    /**
     * Sets objective time most in game.
     *
     * @param objectiveTimeMostInGame the objective time most in game
     */
    public void setObjectiveTimeMostInGame(String objectiveTimeMostInGame) {
        this.objectiveTimeMostInGame = objectiveTimeMostInGame;
    }

    /**
     * Gets offensive assists most in game.
     *
     * @return the offensive assists most in game
     */
    public Double getOffensiveAssistsMostInGame() {
        return offensiveAssistsMostInGame;
    }

    /**
     * Sets offensive assists most in game.
     *
     * @param offensiveAssistsMostInGame the offensive assists most in game
     */
    public void setOffensiveAssistsMostInGame(Double offensiveAssistsMostInGame) {
        this.offensiveAssistsMostInGame = offensiveAssistsMostInGame;
    }

    /**
     * Gets recon assists most in game.
     *
     * @return the recon assists most in game
     */
    public Double getReconAssistsMostInGame() {
        return reconAssistsMostInGame;
    }

    /**
     * Sets recon assists most in game.
     *
     * @param reconAssistsMostInGame the recon assists most in game
     */
    public void setReconAssistsMostInGame(Double reconAssistsMostInGame) {
        this.reconAssistsMostInGame = reconAssistsMostInGame;
    }

    /**
     * Gets solo kills most in game.
     *
     * @return the solo kills most in game
     */
    public Double getSoloKillsMostInGame() {
        return soloKillsMostInGame;
    }

    /**
     * Sets solo kills most in game.
     *
     * @param soloKillsMostInGame the solo kills most in game
     */
    public void setSoloKillsMostInGame(Double soloKillsMostInGame) {
        this.soloKillsMostInGame = soloKillsMostInGame;
    }

    /**
     * Gets teleporter pads destroyed most in game.
     *
     * @return the teleporter pads destroyed most in game
     */
    public Double getTeleporterPadsDestroyedMostInGame() {
        return teleporterPadsDestroyedMostInGame;
    }

    /**
     * Sets teleporter pads destroyed most in game.
     *
     * @param teleporterPadsDestroyedMostInGame the teleporter pads destroyed most in game
     */
    public void setTeleporterPadsDestroyedMostInGame(Double teleporterPadsDestroyedMostInGame) {
        this.teleporterPadsDestroyedMostInGame = teleporterPadsDestroyedMostInGame;
    }

    /**
     * Gets time spent on fire most in game.
     *
     * @return the time spent on fire most in game
     */
    public String getTimeSpentOnFireMostInGame() {
        return timeSpentOnFireMostInGame;
    }

    /**
     * Sets time spent on fire most in game.
     *
     * @param timeSpentOnFireMostInGame the time spent on fire most in game
     */
    public void setTimeSpentOnFireMostInGame(String timeSpentOnFireMostInGame) {
        this.timeSpentOnFireMostInGame = timeSpentOnFireMostInGame;
    }

    /**
     * Gets turrets destroyed most in game.
     *
     * @return the turrets destroyed most in game
     */
    public Double getTurretsDestroyedMostInGame() {
        return turretsDestroyedMostInGame;
    }

    /**
     * Sets turrets destroyed most in game.
     *
     * @param turretsDestroyedMostInGame the turrets destroyed most in game
     */
    public void setTurretsDestroyedMostInGame(Double turretsDestroyedMostInGame) {
        this.turretsDestroyedMostInGame = turretsDestroyedMostInGame;
    }

    /**
     * Gets best list.
     *
     * @return the best list
     */
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
