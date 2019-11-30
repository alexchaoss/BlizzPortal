package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Act2 {

    @SerializedName("completed")
    @Expose
    private Boolean completed;
    @SerializedName("completedQuests")
    @Expose
    private List<Object> completedQuests = null;

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public List<Object> getCompletedQuests() {
        return completedQuests;
    }

    public void setCompletedQuests(List<Object> completedQuests) {
        this.completedQuests = completedQuests;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("completed", completed).append("completedQuests", completedQuests).toString();
    }

}