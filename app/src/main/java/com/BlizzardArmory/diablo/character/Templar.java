package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Templar {

    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("itemsCharacter")
    @Expose
    private ItemsCharacter itemsCharacter;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("skills")
    @Expose
    private List<Object> skills = null;

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

    public ItemsCharacter getItemsCharacter() {
        return itemsCharacter;
    }

    public void setItemsCharacter(ItemsCharacter itemsCharacter) {
        this.itemsCharacter = itemsCharacter;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public List<Object> getSkills() {
        return skills;
    }

    public void setSkills(List<Object> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("slug", slug).append("level", level).append("itemsCharacter", itemsCharacter).append("stats", stats).append("skills", skills).toString();
    }

}