package com.BlizzardArmory.diablo.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Items {

    @SerializedName("head")
    @Expose
    private Head head;
    @SerializedName("neck")
    @Expose
    private Neck neck;
    @SerializedName("torso")
    @Expose
    private Torso torso;
    @SerializedName("shoulders")
    @Expose
    private Shoulders shoulders;
    @SerializedName("legs")
    @Expose
    private Legs legs;
    @SerializedName("waist")
    @Expose
    private Waist waist;
    @SerializedName("hands")
    @Expose
    private Hands hands;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Neck getNeck() {
        return neck;
    }

    public void setNeck(Neck neck) {
        this.neck = neck;
    }

    public Torso getTorso() {
        return torso;
    }

    public void setTorso(Torso torso) {
        this.torso = torso;
    }

    public Shoulders getShoulders() {
        return shoulders;
    }

    public void setShoulders(Shoulders shoulders) {
        this.shoulders = shoulders;
    }

    public Legs getLegs() {
        return legs;
    }

    public void setLegs(Legs legs) {
        this.legs = legs;
    }

    public Waist getWaist() {
        return waist;
    }

    public void setWaist(Waist waist) {
        this.waist = waist;
    }

    public Hands getHands() {
        return hands;
    }

    public void setHands(Hands hands) {
        this.hands = hands;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("head", head).append("neck", neck).append("torso", torso).append("shoulders", shoulders).append("legs", legs).append("waist", waist).append("hands", hands).toString();
    }

}