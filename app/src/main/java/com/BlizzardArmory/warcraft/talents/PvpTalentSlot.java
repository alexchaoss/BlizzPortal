package com.BlizzardArmory.warcraft.talents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Pvp talent slot.
 */
public class PvpTalentSlot {

    @SerializedName("selected")
    @Expose
    private Selected selected;
    @SerializedName("slot_number")
    @Expose
    private int slotNumber;

    /**
     * Gets selected.
     *
     * @return the selected
     */
    public Selected getSelected() {
        return selected;
    }

    /**
     * Sets selected.
     *
     * @param selected the selected
     */
    public void setSelected(Selected selected) {
        this.selected = selected;
    }

    /**
     * Gets slot number.
     *
     * @return the slot number
     */
    public int getSlotNumber() {
        return slotNumber;
    }

    /**
     * Sets slot number.
     *
     * @param slotNumber the slot number
     */
    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

}
