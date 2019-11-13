
package com.BlizzardArmory.diablo.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RandomAffix {

    @SerializedName("oneOf")
    @Expose
    private List<OneOf> oneOf;

    public List<OneOf> getOneOf() {
        return oneOf;
    }

}
