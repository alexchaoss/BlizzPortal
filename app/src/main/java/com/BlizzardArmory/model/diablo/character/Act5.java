package com.BlizzardArmory.model.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * The type Act 5.
 */
public class Act5 {

    @SerializedName("completed")
    @Expose
    private Boolean completed;
    @SerializedName("completedQuests")
    @Expose
    private List<Object> completedQuests = null;

    /**
     * Gets completed.
     *
     * @return the completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * Sets completed.
     *
     * @param completed the completed
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    /**
     * Gets completed quests.
     *
     * @return the completed quests
     */
    public List<Object> getCompletedQuests() {
        return completedQuests;
    }

    /**
     * Sets completed quests.
     *
     * @param completedQuests the completed quests
     */
    public void setCompletedQuests(List<Object> completedQuests) {
        this.completedQuests = completedQuests;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("completed", completed).append("completedQuests", completedQuests).toString();
    }

}