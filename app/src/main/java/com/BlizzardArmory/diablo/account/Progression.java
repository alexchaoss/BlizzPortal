package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Progression.
 */
public class Progression {

    @SerializedName("act1")
    @Expose
    private Boolean act1;
    @SerializedName("act3")
    @Expose
    private Boolean act3;
    @SerializedName("act2")
    @Expose
    private Boolean act2;
    @SerializedName("act5")
    @Expose
    private Boolean act5;
    @SerializedName("act4")
    @Expose
    private Boolean act4;

    /**
     * Gets act 1.
     *
     * @return the act 1
     */
    public Boolean getAct1() {
        return act1;
    }

    /**
     * Sets act 1.
     *
     * @param act1 the act 1
     */
    public void setAct1(Boolean act1) {
        this.act1 = act1;
    }

    /**
     * Gets act 3.
     *
     * @return the act 3
     */
    public Boolean getAct3() {
        return act3;
    }

    /**
     * Sets act 3.
     *
     * @param act3 the act 3
     */
    public void setAct3(Boolean act3) {
        this.act3 = act3;
    }

    /**
     * Gets act 2.
     *
     * @return the act 2
     */
    public Boolean getAct2() {
        return act2;
    }

    /**
     * Sets act 2.
     *
     * @param act2 the act 2
     */
    public void setAct2(Boolean act2) {
        this.act2 = act2;
    }

    /**
     * Gets act 5.
     *
     * @return the act 5
     */
    public Boolean getAct5() {
        return act5;
    }

    /**
     * Sets act 5.
     *
     * @param act5 the act 5
     */
    public void setAct5(Boolean act5) {
        this.act5 = act5;
    }

    /**
     * Gets act 4.
     *
     * @return the act 4
     */
    public Boolean getAct4() {
        return act4;
    }

    /**
     * Sets act 4.
     *
     * @param act4 the act 4
     */
    public void setAct4(Boolean act4) {
        this.act4 = act4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("act1", act1).append("act3", act3).append("act2", act2).append("act5", act5).append("act4", act4).toString();
    }

}