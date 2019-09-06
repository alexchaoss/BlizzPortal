package com.BlizzardArmory.diablo.Character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Progression {

    @SerializedName("act1")
    @Expose
    private Act1 act1;
    @SerializedName("act2")
    @Expose
    private Act2 act2;
    @SerializedName("act3")
    @Expose
    private Act3 act3;
    @SerializedName("act4")
    @Expose
    private Act4 act4;
    @SerializedName("act5")
    @Expose
    private Act5 act5;

    public Act1 getAct1() {
        return act1;
    }

    public void setAct1(Act1 act1) {
        this.act1 = act1;
    }

    public Act2 getAct2() {
        return act2;
    }

    public void setAct2(Act2 act2) {
        this.act2 = act2;
    }

    public Act3 getAct3() {
        return act3;
    }

    public void setAct3(Act3 act3) {
        this.act3 = act3;
    }

    public Act4 getAct4() {
        return act4;
    }

    public void setAct4(Act4 act4) {
        this.act4 = act4;
    }

    public Act5 getAct5() {
        return act5;
    }

    public void setAct5(Act5 act5) {
        this.act5 = act5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("act1", act1).append("act2", act2).append("act3", act3).append("act4", act4).append("act5", act5).toString();
    }

}
