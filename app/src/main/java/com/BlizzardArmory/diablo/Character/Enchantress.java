package com.BlizzardArmory.diablo.Character;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Enchantress {

    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("items")
    @Expose
    private Items items;
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

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
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
        return new ToStringBuilder(this).append("slug", slug).append("level", level).append("items", items).append("stats", stats).append("skills", skills).toString();
    }

}
