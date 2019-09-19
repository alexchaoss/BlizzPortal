package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Death {

    @SerializedName("killer")
    @Expose
    private Integer killer;
    @SerializedName("time")
    @Expose
    private Integer time;

    public Integer getKiller() {
        return killer;
    }

    public void setKiller(Integer killer) {
        this.killer = killer;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("killer", killer).append("time", time).toString();
    }

}