
package com.BlizzardArmory.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Criterium.
 */
public class Criterium {

    @SerializedName("criterionId")
    @Expose
    private String criterionId;
    @SerializedName("earned")
    @Expose
    private Earned earned;

    /**
     * Gets criterion id.
     *
     * @return the criterion id
     */
    public String getCriterionId() {
        return criterionId;
    }

    /**
     * Sets criterion id.
     *
     * @param criterionId the criterion id
     */
    public void setCriterionId(String criterionId) {
        this.criterionId = criterionId;
    }

    /**
     * Gets earned.
     *
     * @return the earned
     */
    public Earned getEarned() {
        return earned;
    }

    /**
     * Sets earned.
     *
     * @param earned the earned
     */
    public void setEarned(Earned earned) {
        this.earned = earned;
    }

}
