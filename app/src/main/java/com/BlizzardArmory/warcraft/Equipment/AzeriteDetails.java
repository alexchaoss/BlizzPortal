
package com.BlizzardArmory.warcraft.Equipment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AzeriteDetails {

    @SerializedName("selected_powers")
    @Expose
    private List<SelectedPower> selectedPowers = null;
    @SerializedName("selected_powers_string")
    @Expose
    private String selectedPowersString;
    @SerializedName("percentage_to_next_level")
    @Expose
    private float percentageToNextLevel;
    @SerializedName("selected_essences")
    @Expose
    private List<SelectedEssence> selectedEssences = null;
    @SerializedName("level")
    @Expose
    private Level level;

    public List<SelectedPower> getSelectedPowers() {
        return selectedPowers;
    }

    public void setSelectedPowers(List<SelectedPower> selectedPowers) {
        this.selectedPowers = selectedPowers;
    }

    public String getSelectedPowersString() {
        return selectedPowersString;
    }

    public void setSelectedPowersString(String selectedPowersString) {
        this.selectedPowersString = selectedPowersString;
    }

    public float getPercentageToNextLevel() {
        return percentageToNextLevel;
    }

    public void setPercentageToNextLevel(float percentageToNextLevel) {
        this.percentageToNextLevel = percentageToNextLevel;
    }

    public List<SelectedEssence> getSelectedEssences() {
        return selectedEssences;
    }

    public void setSelectedEssences(List<SelectedEssence> selectedEssences) {
        this.selectedEssences = selectedEssences;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

}
