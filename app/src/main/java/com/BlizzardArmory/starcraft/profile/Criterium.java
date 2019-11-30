
package com.BlizzardArmory.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Criterium {

    @SerializedName("criterionId")
    @Expose
    private String criterionId;
    @SerializedName("earned")
    @Expose
    private Earned earned;

    public String getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(String criterionId) {
        this.criterionId = criterionId;
    }

    public Earned getEarned() {
        return earned;
    }

    public void setEarned(Earned earned) {
        this.earned = earned;
    }

}
