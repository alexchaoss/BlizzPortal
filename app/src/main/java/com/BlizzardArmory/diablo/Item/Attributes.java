package com.BlizzardArmory.diablo.Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Attributes {
    @SerializedName("primary")
    @Expose
    private ArrayList< String > primary = new ArrayList <> ();
    @SerializedName("secondary")
    @Expose
    private ArrayList < String > secondary = new ArrayList <> ();
    @SerializedName("other")
    @Expose
    private ArrayList < String > other = new ArrayList <> ();

    public ArrayList<String> getPrimary() {
        return primary;
    }

    public void setPrimary(ArrayList<String> primary) {
        this.primary = primary;
    }

    public ArrayList<String> getSecondary() {
        return secondary;
    }

    public void setSecondary(ArrayList<String> secondary) {
        this.secondary = secondary;
    }

    public ArrayList<String> getOther() {
        return other;
    }

    public void setOther(ArrayList<String> other) {
        this.other = other;
    }

}
