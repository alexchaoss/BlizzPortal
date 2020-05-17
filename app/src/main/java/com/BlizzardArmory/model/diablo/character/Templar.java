package com.BlizzardArmory.model.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * The type Templar.
 */
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

    /**
     * Gets slug.
     *
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Sets slug.
     *
     * @param slug the slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Gets items character.
     *
     * @return the items character
     */
    public ItemsCharacter getItemsCharacter() {
        return itemsCharacter;
    }

    /**
     * Sets items character.
     *
     * @param itemsCharacter the items character
     */
    public void setItemsCharacter(ItemsCharacter itemsCharacter) {
        this.itemsCharacter = itemsCharacter;
    }

    /**
     * Gets stats.
     *
     * @return the stats
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Sets stats.
     *
     * @param stats the stats
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     * Gets skills.
     *
     * @return the skills
     */
    public List<Object> getSkills() {
        return skills;
    }

    /**
     * Sets skills.
     *
     * @param skills the skills
     */
    public void setSkills(List<Object> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("slug", slug).append("level", level).append("itemsCharacter", itemsCharacter).append("stats", stats).append("skills", skills).toString();
    }

}