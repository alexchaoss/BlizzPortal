package com.BlizzardArmory.warcraft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BonusStat {

    @SerializedName("stat")
    @Expose
    private Integer stat;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("stat", stat).append("amount", amount).toString();
    }

}
