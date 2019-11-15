
package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopHeroes {

    @SerializedName("junkrat")
    @Expose
    private Junkrat_ junkrat;
    @SerializedName("lucio")
    @Expose
    private Lucio_ lucio;
    @SerializedName("mei")
    @Expose
    private Mei_ mei;
    @SerializedName("pharah")
    @Expose
    private Pharah_ pharah;
    @SerializedName("reaper")
    @Expose
    private Reaper_ reaper;
    @SerializedName("tracer")
    @Expose
    private Tracer_ tracer;

    public Junkrat_ getJunkrat() {
        return junkrat;
    }

    public void setJunkrat(Junkrat_ junkrat) {
        this.junkrat = junkrat;
    }

    public Lucio_ getLucio() {
        return lucio;
    }

    public void setLucio(Lucio_ lucio) {
        this.lucio = lucio;
    }

    public Mei_ getMei() {
        return mei;
    }

    public void setMei(Mei_ mei) {
        this.mei = mei;
    }

    public Pharah_ getPharah() {
        return pharah;
    }

    public void setPharah(Pharah_ pharah) {
        this.pharah = pharah;
    }

    public Reaper_ getReaper() {
        return reaper;
    }

    public void setReaper(Reaper_ reaper) {
        this.reaper = reaper;
    }

    public Tracer_ getTracer() {
        return tracer;
    }

    public void setTracer(Tracer_ tracer) {
        this.tracer = tracer;
    }

}
