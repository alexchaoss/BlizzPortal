
package com.BlizzardArmory.diablo.Item;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {


    @SerializedName("other")
    @Expose
    private List<Object> other;
    @SerializedName("primary")
    @Expose
    private List<Primary> primary;
    @SerializedName("secondary")
    @Expose
    private List<Secondary> secondary;

    public List<Object> getOther() {
        return other;
    }

    public List<Primary> getPrimary() {
        return primary;
    }

    public List<Secondary> getSecondary() {
        return secondary;
    }

}
