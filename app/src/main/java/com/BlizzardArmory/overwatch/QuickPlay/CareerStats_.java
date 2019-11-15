
package com.BlizzardArmory.overwatch.QuickPlay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CareerStats_ {

    @SerializedName("allHeroes")
    @Expose
    private AllHeroes allHeroes;

    public AllHeroes getAllHeroes() {
        return allHeroes;
    }

    public void setAllHeroes(AllHeroes allHeroes) {
        this.allHeroes = allHeroes;
    }

}
