package com.BlizzardArmory.model.diablo.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * The type Random affix.
 */
public class RandomAffix {

    @SerializedName("oneOf")
    @Expose
    private List<OneOf> oneOf;

    /**
     * Gets one of.
     *
     * @return the one of
     */
    public List<OneOf> getOneOf() {
        return oneOf;
    }

}
