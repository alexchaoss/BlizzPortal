
package com.BlizzardArmory.overwatch.QuickPlay.TopHeroes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopHeroes {

    @SerializedName("topHeroes")
    @Expose
    private TopHeroes_ topHeroes;

    public TopHeroes_ getTopHeroes() {
        return topHeroes;
    }

    public void setTopHeroes(TopHeroes_ topHeroes) {
        this.topHeroes = topHeroes;
    }

}
