package com.BlizzardArmory.warcraft.Spells;

import java.util.List;

import com.BlizzardArmory.warcraft.Spec;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Talent {

    @SerializedName("selected")
    @Expose
    private Boolean selected;
    @SerializedName("talents")
    @Expose
    private List<Talents> talents = null;
    @SerializedName("spec")
    @Expose
    private Spec spec;
    @SerializedName("calcTalent")
    @Expose
    private String calcTalent;
    @SerializedName("calcSpec")
    @Expose
    private String calcSpec;

    public Boolean getSelected() {
        if(selected == null) {
            selected = false;
        }
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public List<Talents> getTalents() {
        return talents;
    }

    public void setTalents(List<Talents> talents) {
        this.talents = talents;
    }

    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    public String getCalcTalent() {
        return calcTalent;
    }

    public void setCalcTalent(String calcTalent) {
        this.calcTalent = calcTalent;
    }

    public String getCalcSpec() {
        return calcSpec;
    }

    public void setCalcSpec(String calcSpec) {
        this.calcSpec = calcSpec;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("selected", selected).append("talents", talents).append("spec", spec).append("calcTalent", calcTalent).append("calcSpec", calcSpec).toString();
    }

}
