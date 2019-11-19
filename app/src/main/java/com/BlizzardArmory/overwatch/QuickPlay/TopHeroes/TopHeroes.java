package com.BlizzardArmory.overwatch.QuickPlay.TopHeroes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopHeroes {

    @SerializedName("topHeroes")
    @Expose
    private TopHeroes topHeroes;

    public TopHeroes getTopHeroes() {
        return topHeroes;
    }

    public void setTopHeroes(TopHeroes topHeroes) {
        this.topHeroes = topHeroes;
    }

}
