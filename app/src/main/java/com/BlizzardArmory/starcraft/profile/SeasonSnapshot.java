
package com.BlizzardArmory.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeasonSnapshot {

    @SerializedName("1v1")
    @Expose
    private OneVOne _1v1;
    @SerializedName("2v2")
    @Expose
    private TwoVTwo _2v2;
    @SerializedName("3v3")
    @Expose
    private ThreeVThree _3v3;
    @SerializedName("4v4")
    @Expose
    private FourVFour _4v4;
    @SerializedName("Archon")
    @Expose
    private Archon archon;

    public OneVOne get1v1() {
        return _1v1;
    }

    public void set1v1(OneVOne _1v1) {
        this._1v1 = _1v1;
    }

    public TwoVTwo get2v2() {
        return _2v2;
    }

    public void set2v2(TwoVTwo _2v2) {
        this._2v2 = _2v2;
    }

    public ThreeVThree get3v3() {
        return _3v3;
    }

    public void set3v3(ThreeVThree _3v3) {
        this._3v3 = _3v3;
    }

    public FourVFour get4v4() {
        return _4v4;
    }

    public void set4v4(FourVFour _4v4) {
        this._4v4 = _4v4;
    }

    public Archon getArchon() {
        return archon;
    }

    public void setArchon(Archon archon) {
        this.archon = archon;
    }

}
