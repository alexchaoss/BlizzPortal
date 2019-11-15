
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Best__ {

    @SerializedName("allDamageDoneMostInGame")
    @Expose
    private int allDamageDoneMostInGame;
    @SerializedName("allDamageDoneMostInLife")
    @Expose
    private int allDamageDoneMostInLife;
    @SerializedName("objectiveTimeMostInGame")
    @Expose
    private String objectiveTimeMostInGame;

    public int getAllDamageDoneMostInGame() {
        return allDamageDoneMostInGame;
    }

    public void setAllDamageDoneMostInGame(int allDamageDoneMostInGame) {
        this.allDamageDoneMostInGame = allDamageDoneMostInGame;
    }

    public int getAllDamageDoneMostInLife() {
        return allDamageDoneMostInLife;
    }

    public void setAllDamageDoneMostInLife(int allDamageDoneMostInLife) {
        this.allDamageDoneMostInLife = allDamageDoneMostInLife;
    }

    public String getObjectiveTimeMostInGame() {
        return objectiveTimeMostInGame;
    }

    public void setObjectiveTimeMostInGame(String objectiveTimeMostInGame) {
        this.objectiveTimeMostInGame = objectiveTimeMostInGame;
    }

}
