package com.BlizzardArmory.diablo.Character;

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
    @SerializedName("bracers")
    @Expose
    private Bracers bracers;
    @SerializedName("feet")
    @Expose
    private Feet feet;
    @SerializedName("leftFinger")
    @Expose
    private LeftFinger leftFinger;
    @SerializedName("rightFinger")
    @Expose
    private RightFinger rightFinger;
    @SerializedName("mainHand")
    @Expose
    private MainHand mainHand;
    @SerializedName("offHand")
    @Expose
    private OffHand offHand;

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

    public Bracers getBracers() {
        return bracers;
    }

    public void setBracers(Bracers bracers) {
        this.bracers = bracers;
    }

    public Feet getFeet() {
        return feet;
    }

    public void setFeet(Feet feet) {
        this.feet = feet;
    }

    public LeftFinger getLeftFinger() {
        return leftFinger;
    }

    public void setLeftFinger(LeftFinger leftFinger) {
        this.leftFinger = leftFinger;
    }

    public RightFinger getRightFinger() {
        return rightFinger;
    }

    public void setRightFinger(RightFinger rightFinger) {
        this.rightFinger = rightFinger;
    }

    public MainHand getMainHand() {
        return mainHand;
    }

    public void setMainHand(MainHand mainHand) {
        this.mainHand = mainHand;
    }


    public OffHand getOffHand() {
        return offHand;
    }

    public void setOffHand(OffHand offHand) {
        this.offHand = offHand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("head", head).append("neck", neck).append("torso", torso).append("shoulders", shoulders).append("legs", legs).append("waist", waist).append("hands", hands).append("bracers", bracers).append("feet", feet).append("leftFinger", leftFinger).append("rightFinger", rightFinger).append("mainHand", mainHand).toString();
    }

}