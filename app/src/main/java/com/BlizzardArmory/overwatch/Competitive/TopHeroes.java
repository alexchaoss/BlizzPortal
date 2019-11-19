package com.BlizzardArmory.overwatch.Competitive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopHeroes {

    @SerializedName("junkrat")
    @Expose
    private Junkrat junkrat;
    @SerializedName("lucio")
    @Expose
    private Lucio lucio;
    @SerializedName("mei")
    @Expose
    private Mei mei;
    @SerializedName("pharah")
    @Expose
    private Pharah pharah;
    @SerializedName("reaper")
    @Expose
    private Reaper reaper;
    @SerializedName("tracer")
    @Expose
    private Tracer tracer;

    public Junkrat getJunkrat() {
        return junkrat;
    }

    public void setJunkrat(Junkrat junkrat) {
        this.junkrat = junkrat;
    }

    public Lucio getLucio() {
        return lucio;
    }

    public void setLucio(Lucio lucio) {
        this.lucio = lucio;
    }

    public Mei getMei() {
        return mei;
    }

    public void setMei(Mei mei) {
        this.mei = mei;
    }

    public Pharah getPharah() {
        return pharah;
    }

    public void setPharah(Pharah pharah) {
        this.pharah = pharah;
    }

    public Reaper getReaper() {
        return reaper;
    }

    public void setReaper(Reaper reaper) {
        this.reaper = reaper;
    }

    public Tracer getTracer() {
        return tracer;
    }

    public void setTracer(Tracer tracer) {
        this.tracer = tracer;
    }

}
