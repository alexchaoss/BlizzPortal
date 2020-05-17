package com.BlizzardArmory.model.warcraft.equipment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Color.
 */
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

    /**
     * Instantiates a new Color.
     *
     * @param r the r
     * @param g the g
     * @param b the b
     * @param a the a
     */
    public Color(int r, int g, int b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * Gets r.
     *
     * @return the r
     */
    public int getR() {
        return r;
    }

    /**
     * Sets r.
     *
     * @param r the r
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * Gets g.
     *
     * @return the g
     */
    public int getG() {
        return g;
    }

    /**
     * Sets g.
     *
     * @param g the g
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * Gets b.
     *
     * @return the b
     */
    public int getB() {
        return b;
    }

    /**
     * Sets b.
     *
     * @param b the b
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * Gets a.
     *
     * @return the a
     */
    public float getA() {
        return a;
    }

    /**
     * Sets a.
     *
     * @param a the a
     */
    public void setA(float a) {
        this.a = a;
    }

}