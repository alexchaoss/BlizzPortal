package com.BlizzardArmory.overwatch;

import com.BlizzardArmory.overwatch.Heroes.AllHeroes;
import com.BlizzardArmory.overwatch.Heroes.Ana;
import com.BlizzardArmory.overwatch.Heroes.Ashe;
import com.BlizzardArmory.overwatch.Heroes.Baptiste;
import com.BlizzardArmory.overwatch.Heroes.Bastion;
import com.BlizzardArmory.overwatch.Heroes.Brigitte;
import com.BlizzardArmory.overwatch.Heroes.DVa;
import com.BlizzardArmory.overwatch.Heroes.Doomfist;
import com.BlizzardArmory.overwatch.Heroes.Genji;
import com.BlizzardArmory.overwatch.Heroes.Hanzo;
import com.BlizzardArmory.overwatch.Heroes.Hero;
import com.BlizzardArmory.overwatch.Heroes.Junkrat;
import com.BlizzardArmory.overwatch.Heroes.Lúcio;
import com.BlizzardArmory.overwatch.Heroes.Mccree;
import com.BlizzardArmory.overwatch.Heroes.Mei;
import com.BlizzardArmory.overwatch.Heroes.Mercy;
import com.BlizzardArmory.overwatch.Heroes.Moira;
import com.BlizzardArmory.overwatch.Heroes.Orisa;
import com.BlizzardArmory.overwatch.Heroes.Pharah;
import com.BlizzardArmory.overwatch.Heroes.Reaper;
import com.BlizzardArmory.overwatch.Heroes.Reinhardt;
import com.BlizzardArmory.overwatch.Heroes.Roadhog;
import com.BlizzardArmory.overwatch.Heroes.Sigma;
import com.BlizzardArmory.overwatch.Heroes.Soldier76;
import com.BlizzardArmory.overwatch.Heroes.Sombra;
import com.BlizzardArmory.overwatch.Heroes.Symmetra;
import com.BlizzardArmory.overwatch.Heroes.Torbjörn;
import com.BlizzardArmory.overwatch.Heroes.Tracer;
import com.BlizzardArmory.overwatch.Heroes.Widowmaker;
import com.BlizzardArmory.overwatch.Heroes.Winston;
import com.BlizzardArmory.overwatch.Heroes.WreckingBall;
import com.BlizzardArmory.overwatch.Heroes.Zarya;
import com.BlizzardArmory.overwatch.Heroes.Zenyatta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CareerStats {

    @SerializedName("allHeroes")
    @Expose
    private AllHeroes allHeroes;
    @SerializedName("ana")
    @Expose
    private Ana ana;
    @SerializedName("ashe")
    @Expose
    private Ashe ashe;
    @SerializedName("baptiste")
    @Expose
    private Baptiste baptiste;
    @SerializedName("bastion")
    @Expose
    private Bastion bastion;
    @SerializedName("brigitte")
    @Expose
    private Brigitte brigitte;
    @SerializedName("dVa")
    @Expose
    private DVa dVa;
    @SerializedName("doomfist")
    @Expose
    private Doomfist doomfist;
    @SerializedName("genji")
    @Expose
    private Genji genji;
    @SerializedName("hanzo")
    @Expose
    private Hanzo hanzo;
    @SerializedName("junkrat")
    @Expose
    private Junkrat junkrat;
    @SerializedName("lucio")
    @Expose
    private Lúcio lucio;
    @SerializedName("mccree")
    @Expose
    private Mccree mccree;
    @SerializedName("mei")
    @Expose
    private Mei mei;
    @SerializedName("mercy")
    @Expose
    private Mercy mercy;
    @SerializedName("moira")
    @Expose
    private Moira moira;
    @SerializedName("orisa")
    @Expose
    private Orisa orisa;
    @SerializedName("pharah")
    @Expose
    private Pharah pharah;
    @SerializedName("reaper")
    @Expose
    private Reaper reaper;
    @SerializedName("reinhardt")
    @Expose
    private Reinhardt reinhardt;
    @SerializedName("roadhog")
    @Expose
    private Roadhog roadhog;
    @SerializedName("sigma")
    @Expose
    private Sigma sigma;
    @SerializedName("soldier76")
    @Expose
    private Soldier76 soldier76;
    @SerializedName("sombra")
    @Expose
    private Sombra sombra;
    @SerializedName("symmetra")
    @Expose
    private Symmetra symmetra;
    @SerializedName("torbjorn")
    @Expose
    private Torbjörn torbjorn;
    @SerializedName("tracer")
    @Expose
    private Tracer tracer;
    @SerializedName("widowmaker")
    @Expose
    private Widowmaker widowmaker;
    @SerializedName("winston")
    @Expose
    private Winston winston;
    @SerializedName("wreckingBall")
    @Expose
    private WreckingBall wreckingBall;
    @SerializedName("zarya")
    @Expose
    private Zarya zarya;
    @SerializedName("zenyatta")
    @Expose
    private Zenyatta zenyatta;

    private ArrayList<Hero> heroList = new ArrayList<>();

    public Ana getAna() {
        return ana;
    }

    public void setAna(Ana ana) {
        this.ana = ana;
    }

    public Ashe getAshe() {
        return ashe;
    }

    public void setAshe(Ashe ashe) {
        this.ashe = ashe;
    }

    public Baptiste getBaptiste() {
        return baptiste;
    }

    public void setBaptiste(Baptiste baptiste) {
        this.baptiste = baptiste;
    }

    public Bastion getBastion() {
        return bastion;
    }

    public void setBastion(Bastion bastion) {
        this.bastion = bastion;
    }

    public Brigitte getBrigitte() {
        return brigitte;
    }

    public void setBrigitte(Brigitte brigitte) {
        this.brigitte = brigitte;
    }

    public DVa getDVa() {
        return dVa;
    }

    public void setDVa(DVa dVa) {
        this.dVa = dVa;
    }

    public Doomfist getDoomfist() {
        return doomfist;
    }

    public void setDoomfist(Doomfist doomfist) {
        this.doomfist = doomfist;
    }

    public Genji getGenji() {
        return genji;
    }

    public void setGenji(Genji genji) {
        this.genji = genji;
    }

    public Hanzo getHanzo() {
        return hanzo;
    }

    public void setHanzo(Hanzo hanzo) {
        this.hanzo = hanzo;
    }

    public Junkrat getJunkrat() {
        return junkrat;
    }

    public void setJunkrat(Junkrat junkrat) {
        this.junkrat = junkrat;
    }

    public Lúcio getLucio() {
        return lucio;
    }

    public void setLucio(Lúcio lucio) {
        this.lucio = lucio;
    }

    public Mccree getMccree() {
        return mccree;
    }

    public void setMccree(Mccree mccree) {
        this.mccree = mccree;
    }

    public Mei getMei() {
        return mei;
    }

    public void setMei(Mei mei) {
        this.mei = mei;
    }

    public Mercy getMercy() {
        return mercy;
    }

    public void setMercy(Mercy mercy) {
        this.mercy = mercy;
    }

    public Moira getMoira() {
        return moira;
    }

    public void setMoira(Moira moira) {
        this.moira = moira;
    }

    public Orisa getOrisa() {
        return orisa;
    }

    public void setOrisa(Orisa orisa) {
        this.orisa = orisa;
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

    public Reinhardt getReinhardt() {
        return reinhardt;
    }

    public void setReinhardt(Reinhardt reinhardt) {
        this.reinhardt = reinhardt;
    }

    public Roadhog getRoadhog() {
        return roadhog;
    }

    public void setRoadhog(Roadhog roadhog) {
        this.roadhog = roadhog;
    }

    public Sigma getSigma() {
        return sigma;
    }

    public void setSigma(Sigma sigma) {
        this.sigma = sigma;
    }

    public Soldier76 getSoldier76() {
        return soldier76;
    }

    public void setSoldier76(Soldier76 soldier76) {
        this.soldier76 = soldier76;
    }

    public Sombra getSombra() {
        return sombra;
    }

    public void setSombra(Sombra sombra) {
        this.sombra = sombra;
    }

    public Symmetra getSymmetra() {
        return symmetra;
    }

    public void setSymmetra(Symmetra symmetra) {
        this.symmetra = symmetra;
    }

    public Torbjörn getTorbjorn() {
        return torbjorn;
    }

    public void setTorbjorn(Torbjörn torbjorn) {
        this.torbjorn = torbjorn;
    }

    public Tracer getTracer() {
        return tracer;
    }

    public void setTracer(Tracer tracer) {
        this.tracer = tracer;
    }

    public Widowmaker getWidowmaker() {
        return widowmaker;
    }

    public void setWidowmaker(Widowmaker widowmaker) {
        this.widowmaker = widowmaker;
    }

    public Winston getWinston() {
        return winston;
    }

    public void setWinston(Winston winston) {
        this.winston = winston;
    }

    public WreckingBall getWreckingBall() {
        return wreckingBall;
    }

    public void setWreckingBall(WreckingBall wreckingBall) {
        this.wreckingBall = wreckingBall;
    }

    public Zarya getZarya() {
        return zarya;
    }

    public void setZarya(Zarya zarya) {
        this.zarya = zarya;
    }

    public Zenyatta getZenyatta() {
        return zenyatta;
    }

    public void setZenyatta(Zenyatta zenyatta) {
        this.zenyatta = zenyatta;
    }

    public ArrayList<Hero> getHeroList() {
        heroList.add(allHeroes);
        heroList.add(ana);
        heroList.add(ashe);
        heroList.add(baptiste);
        heroList.add(bastion);
        heroList.add(brigitte);
        heroList.add(dVa);
        heroList.add(doomfist);
        heroList.add(genji);
        heroList.add(hanzo);
        heroList.add(junkrat);
        heroList.add(lucio);
        heroList.add(mccree);
        heroList.add(mei);
        heroList.add(mercy);
        heroList.add(moira);
        heroList.add(orisa);
        heroList.add(pharah);
        heroList.add(reaper);
        heroList.add(reinhardt);
        heroList.add(roadhog);
        heroList.add(sigma);
        heroList.add(soldier76);
        heroList.add(sombra);
        heroList.add(symmetra);
        heroList.add(torbjorn);
        heroList.add(tracer);
        heroList.add(widowmaker);
        heroList.add(winston);
        heroList.add(wreckingBall);
        heroList.add(zarya);
        heroList.add(zenyatta);

        return heroList;
    }
}
