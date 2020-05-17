
package com.BlizzardArmory.model.starcraft.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Season snapshot.
 */
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

    /**
     * Gets 1 v 1.
     *
     * @return the 1 v 1
     */
    public OneVOne get1v1() {
        return _1v1;
    }

    /**
     * Sets 1 v 1.
     *
     * @param _1v1 the 1 v 1
     */
    public void set1v1(OneVOne _1v1) {
        this._1v1 = _1v1;
    }

    /**
     * Gets 2 v 2.
     *
     * @return the 2 v 2
     */
    public TwoVTwo get2v2() {
        return _2v2;
    }

    /**
     * Sets 2 v 2.
     *
     * @param _2v2 the 2 v 2
     */
    public void set2v2(TwoVTwo _2v2) {
        this._2v2 = _2v2;
    }

    /**
     * Gets 3 v 3.
     *
     * @return the 3 v 3
     */
    public ThreeVThree get3v3() {
        return _3v3;
    }

    /**
     * Sets 3 v 3.
     *
     * @param _3v3 the 3 v 3
     */
    public void set3v3(ThreeVThree _3v3) {
        this._3v3 = _3v3;
    }

    /**
     * Gets 4 v 4.
     *
     * @return the 4 v 4
     */
    public FourVFour get4v4() {
        return _4v4;
    }

    /**
     * Sets 4 v 4.
     *
     * @param _4v4 the 4 v 4
     */
    public void set4v4(FourVFour _4v4) {
        this._4v4 = _4v4;
    }

    /**
     * Gets archon.
     *
     * @return the archon
     */
    public Archon getArchon() {
        return archon;
    }

    /**
     * Sets archon.
     *
     * @param archon the archon
     */
    public void setArchon(Archon archon) {
        this.archon = archon;
    }

}
