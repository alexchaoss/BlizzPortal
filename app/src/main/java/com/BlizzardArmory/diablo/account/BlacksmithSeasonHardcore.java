package com.BlizzardArmory.diablo.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BlacksmithSeasonHardcore {

    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("level")
    @Expose
    private Integer level;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("slug", slug).append("level", level).toString();
    }

}