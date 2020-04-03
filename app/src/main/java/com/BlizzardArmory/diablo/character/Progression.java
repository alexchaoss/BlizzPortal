package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Progression.
 */
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

    /**
     * Gets act 1.
     *
     * @return the act 1
     */
    public Act1 getAct1() {
        return act1;
    }

    /**
     * Sets act 1.
     *
     * @param act1 the act 1
     */
    public void setAct1(Act1 act1) {
        this.act1 = act1;
    }

    /**
     * Gets act 2.
     *
     * @return the act 2
     */
    public Act2 getAct2() {
        return act2;
    }

    /**
     * Sets act 2.
     *
     * @param act2 the act 2
     */
    public void setAct2(Act2 act2) {
        this.act2 = act2;
    }

    /**
     * Gets act 3.
     *
     * @return the act 3
     */
    public Act3 getAct3() {
        return act3;
    }

    /**
     * Sets act 3.
     *
     * @param act3 the act 3
     */
    public void setAct3(Act3 act3) {
        this.act3 = act3;
    }

    /**
     * Gets act 4.
     *
     * @return the act 4
     */
    public Act4 getAct4() {
        return act4;
    }

    /**
     * Sets act 4.
     *
     * @param act4 the act 4
     */
    public void setAct4(Act4 act4) {
        this.act4 = act4;
    }

    /**
     * Gets act 5.
     *
     * @return the act 5
     */
    public Act5 getAct5() {
        return act5;
    }

    /**
     * Sets act 5.
     *
     * @param act5 the act 5
     */
    public void setAct5(Act5 act5) {
        this.act5 = act5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("act1", act1).append("act2", act2).append("act3", act3).append("act4", act4).append("act5", act5).toString();
    }

}
