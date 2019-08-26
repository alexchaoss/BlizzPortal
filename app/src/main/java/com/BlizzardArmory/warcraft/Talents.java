package com.BlizzardArmory.warcraft;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Talents {

    @SerializedName("tier")
    @Expose
    private Integer tier;
    @SerializedName("column")
    @Expose
    private Integer column;
    @SerializedName("spell")
    @Expose
    private Spell spell;
    @SerializedName("spec")
    @Expose
    private Spec spec;

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("tier", tier).append("column", column).append("spell", spell).append("spec", spec).toString();
    }
}
