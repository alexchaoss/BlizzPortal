
package com.BlizzardArmory.diablo.Item;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RandomAffix {

    @SerializedName("oneOf")
    @Expose
    private List<OneOf> oneOf;

    public List<OneOf> getOneOf() {
        return oneOf;
    }

}
