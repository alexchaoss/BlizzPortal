package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Azerite details.
 */
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

    /**
     * Gets selected powers.
     *
     * @return the selected powers
     */
    public List<SelectedPower> getSelectedPowers() {
        return selectedPowers;
    }

    /**
     * Sets selected powers.
     *
     * @param selectedPowers the selected powers
     */
    public void setSelectedPowers(List<SelectedPower> selectedPowers) {
        this.selectedPowers = selectedPowers;
    }

    /**
     * Gets selected powers string.
     *
     * @return the selected powers string
     */
    public String getSelectedPowersString() {
        return selectedPowersString;
    }

    /**
     * Sets selected powers string.
     *
     * @param selectedPowersString the selected powers string
     */
    public void setSelectedPowersString(String selectedPowersString) {
        this.selectedPowersString = selectedPowersString;
    }

    /**
     * Gets percentage to next level.
     *
     * @return the percentage to next level
     */
    public float getPercentageToNextLevel() {
        return percentageToNextLevel;
    }

    /**
     * Sets percentage to next level.
     *
     * @param percentageToNextLevel the percentage to next level
     */
    public void setPercentageToNextLevel(float percentageToNextLevel) {
        this.percentageToNextLevel = percentageToNextLevel;
    }

    /**
     * Gets selected essences.
     *
     * @return the selected essences
     */
    public List<SelectedEssence> getSelectedEssences() {
        return selectedEssences;
    }

    /**
     * Sets selected essences.
     *
     * @param selectedEssences the selected essences
     */
    public void setSelectedEssences(List<SelectedEssence> selectedEssences) {
        this.selectedEssences = selectedEssences;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

}
