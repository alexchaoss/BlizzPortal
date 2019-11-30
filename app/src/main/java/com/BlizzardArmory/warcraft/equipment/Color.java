package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Color {

    @SerializedName("r")
    @Expose
    private int r;
    @SerializedName("g")
    @Expose
    private int g;
    @SerializedName("b")
    @Expose
    private int b;
    @SerializedName("a")
    @Expose
    private float a;

    public Color(int r, int g, int b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

}