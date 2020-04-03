package com.BlizzardArmory.diablo.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The type Items character.
 */
public class ItemsCharacter {

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

    /**
     * Gets head.
     *
     * @return the head
     */
    public Head getHead() {
        return head;
    }

    /**
     * Sets head.
     *
     * @param head the head
     */
    public void setHead(Head head) {
        this.head = head;
    }

    /**
     * Gets neck.
     *
     * @return the neck
     */
    public Neck getNeck() {
        return neck;
    }

    /**
     * Sets neck.
     *
     * @param neck the neck
     */
    public void setNeck(Neck neck) {
        this.neck = neck;
    }

    /**
     * Gets torso.
     *
     * @return the torso
     */
    public Torso getTorso() {
        return torso;
    }

    /**
     * Sets torso.
     *
     * @param torso the torso
     */
    public void setTorso(Torso torso) {
        this.torso = torso;
    }

    /**
     * Gets shoulders.
     *
     * @return the shoulders
     */
    public Shoulders getShoulders() {
        return shoulders;
    }

    /**
     * Sets shoulders.
     *
     * @param shoulders the shoulders
     */
    public void setShoulders(Shoulders shoulders) {
        this.shoulders = shoulders;
    }

    /**
     * Gets legs.
     *
     * @return the legs
     */
    public Legs getLegs() {
        return legs;
    }

    /**
     * Sets legs.
     *
     * @param legs the legs
     */
    public void setLegs(Legs legs) {
        this.legs = legs;
    }

    /**
     * Gets waist.
     *
     * @return the waist
     */
    public Waist getWaist() {
        return waist;
    }

    /**
     * Sets waist.
     *
     * @param waist the waist
     */
    public void setWaist(Waist waist) {
        this.waist = waist;
    }

    /**
     * Gets hands.
     *
     * @return the hands
     */
    public Hands getHands() {
        return hands;
    }

    /**
     * Sets hands.
     *
     * @param hands the hands
     */
    public void setHands(Hands hands) {
        this.hands = hands;
    }

    /**
     * Gets bracers.
     *
     * @return the bracers
     */
    public Bracers getBracers() {
        return bracers;
    }

    /**
     * Sets bracers.
     *
     * @param bracers the bracers
     */
    public void setBracers(Bracers bracers) {
        this.bracers = bracers;
    }

    /**
     * Gets feet.
     *
     * @return the feet
     */
    public Feet getFeet() {
        return feet;
    }

    /**
     * Sets feet.
     *
     * @param feet the feet
     */
    public void setFeet(Feet feet) {
        this.feet = feet;
    }

    /**
     * Gets left finger.
     *
     * @return the left finger
     */
    public LeftFinger getLeftFinger() {
        return leftFinger;
    }

    /**
     * Sets left finger.
     *
     * @param leftFinger the left finger
     */
    public void setLeftFinger(LeftFinger leftFinger) {
        this.leftFinger = leftFinger;
    }

    /**
     * Gets right finger.
     *
     * @return the right finger
     */
    public RightFinger getRightFinger() {
        return rightFinger;
    }

    /**
     * Sets right finger.
     *
     * @param rightFinger the right finger
     */
    public void setRightFinger(RightFinger rightFinger) {
        this.rightFinger = rightFinger;
    }

    /**
     * Gets main hand.
     *
     * @return the main hand
     */
    public MainHand getMainHand() {
        return mainHand;
    }

    /**
     * Sets main hand.
     *
     * @param mainHand the main hand
     */
    public void setMainHand(MainHand mainHand) {
        this.mainHand = mainHand;
    }


    /**
     * Gets off hand.
     *
     * @return the off hand
     */
    public OffHand getOffHand() {
        return offHand;
    }

    /**
     * Sets off hand.
     *
     * @param offHand the off hand
     */
    public void setOffHand(OffHand offHand) {
        this.offHand = offHand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("head", head).append("neck", neck).append("torso", torso).append("shoulders", shoulders).append("legs", legs).append("waist", waist).append("hands", hands).append("bracers", bracers).append("feet", feet).append("leftFinger", leftFinger).append("rightFinger", rightFinger).append("mainHand", mainHand).toString();
    }

}