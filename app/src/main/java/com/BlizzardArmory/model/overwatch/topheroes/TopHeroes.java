
package com.BlizzardArmory.model.overwatch.topheroes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * The type Top heroes.
 */
public class TopHeroes {

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
    @SerializedName("echo")
    @Expose
    private Echo echo;
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


    private ArrayList<TopHero> heroList = new ArrayList<>();

    /**
     * Gets ana.
     *
     * @return the ana
     */
    public Ana getAna() {
        return ana;
    }

    /**
     * Sets ana.
     *
     * @param ana the ana
     */
    public void setAna(Ana ana) {
        this.ana = ana;
    }

    /**
     * Gets ashe.
     *
     * @return the ashe
     */
    public Ashe getAshe() {
        return ashe;
    }

    /**
     * Sets ashe.
     *
     * @param ashe the ashe
     */
    public void setAshe(Ashe ashe) {
        this.ashe = ashe;
    }

    /**
     * Gets baptiste.
     *
     * @return the baptiste
     */
    public Baptiste getBaptiste() {
        return baptiste;
    }

    /**
     * Sets baptiste.
     *
     * @param baptiste the baptiste
     */
    public void setBaptiste(Baptiste baptiste) {
        this.baptiste = baptiste;
    }

    /**
     * Gets bastion.
     *
     * @return the bastion
     */
    public Bastion getBastion() {
        return bastion;
    }

    /**
     * Sets bastion.
     *
     * @param bastion the bastion
     */
    public void setBastion(Bastion bastion) {
        this.bastion = bastion;
    }

    /**
     * Gets brigitte.
     *
     * @return the brigitte
     */
    public Brigitte getBrigitte() {
        return brigitte;
    }

    /**
     * Sets brigitte.
     *
     * @param brigitte the brigitte
     */
    public void setBrigitte(Brigitte brigitte) {
        this.brigitte = brigitte;
    }

    /**
     * Gets d va.
     *
     * @return the d va
     */
    public DVa getDVa() {
        return dVa;
    }

    /**
     * Sets d va.
     *
     * @param dVa the d va
     */
    public void setDVa(DVa dVa) {
        this.dVa = dVa;
    }

    /**
     * Gets echo.
     *
     * @return the echo
     */
    public Echo getEcho() {
        return echo;
    }

    /**
     * Sets echo.
     *
     * @param echo the echo
     */
    public void setEcho(Echo echo) {
        this.echo = echo;
    }

    /**
     * Gets doomfist.
     *
     * @return the doomfist
     */
    public Doomfist getDoomfist() {
        return doomfist;
    }

    /**
     * Sets doomfist.
     *
     * @param doomfist the doomfist
     */
    public void setDoomfist(Doomfist doomfist) {
        this.doomfist = doomfist;
    }

    /**
     * Gets genji.
     *
     * @return the genji
     */
    public Genji getGenji() {
        return genji;
    }

    /**
     * Sets genji.
     *
     * @param genji the genji
     */
    public void setGenji(Genji genji) {
        this.genji = genji;
    }

    /**
     * Gets hanzo.
     *
     * @return the hanzo
     */
    public Hanzo getHanzo() {
        return hanzo;
    }

    /**
     * Sets hanzo.
     *
     * @param hanzo the hanzo
     */
    public void setHanzo(Hanzo hanzo) {
        this.hanzo = hanzo;
    }

    /**
     * Gets junkrat.
     *
     * @return the junkrat
     */
    public Junkrat getJunkrat() {
        return junkrat;
    }

    /**
     * Sets junkrat.
     *
     * @param junkrat the junkrat
     */
    public void setJunkrat(Junkrat junkrat) {
        this.junkrat = junkrat;
    }

    /**
     * Gets lucio.
     *
     * @return the lucio
     */
    public Lúcio getLucio() {
        return lucio;
    }

    /**
     * Sets lucio.
     *
     * @param lucio the lucio
     */
    public void setLucio(Lúcio lucio) {
        this.lucio = lucio;
    }

    /**
     * Gets mccree.
     *
     * @return the mccree
     */
    public Mccree getMccree() {
        return mccree;
    }

    /**
     * Sets mccree.
     *
     * @param mccree the mccree
     */
    public void setMccree(Mccree mccree) {
        this.mccree = mccree;
    }

    /**
     * Gets mei.
     *
     * @return the mei
     */
    public Mei getMei() {
        return mei;
    }

    /**
     * Sets mei.
     *
     * @param mei the mei
     */
    public void setMei(Mei mei) {
        this.mei = mei;
    }

    /**
     * Gets mercy.
     *
     * @return the mercy
     */
    public Mercy getMercy() {
        return mercy;
    }

    /**
     * Sets mercy.
     *
     * @param mercy the mercy
     */
    public void setMercy(Mercy mercy) {
        this.mercy = mercy;
    }

    /**
     * Gets moira.
     *
     * @return the moira
     */
    public Moira getMoira() {
        return moira;
    }

    /**
     * Sets moira.
     *
     * @param moira the moira
     */
    public void setMoira(Moira moira) {
        this.moira = moira;
    }

    /**
     * Gets orisa.
     *
     * @return the orisa
     */
    public Orisa getOrisa() {
        return orisa;
    }

    /**
     * Sets orisa.
     *
     * @param orisa the orisa
     */
    public void setOrisa(Orisa orisa) {
        this.orisa = orisa;
    }

    /**
     * Gets pharah.
     *
     * @return the pharah
     */
    public Pharah getPharah() {
        return pharah;
    }

    /**
     * Sets pharah.
     *
     * @param pharah the pharah
     */
    public void setPharah(Pharah pharah) {
        this.pharah = pharah;
    }

    /**
     * Gets reaper.
     *
     * @return the reaper
     */
    public Reaper getReaper() {
        return reaper;
    }

    /**
     * Sets reaper.
     *
     * @param reaper the reaper
     */
    public void setReaper(Reaper reaper) {
        this.reaper = reaper;
    }

    /**
     * Gets reinhardt.
     *
     * @return the reinhardt
     */
    public Reinhardt getReinhardt() {
        return reinhardt;
    }

    /**
     * Sets reinhardt.
     *
     * @param reinhardt the reinhardt
     */
    public void setReinhardt(Reinhardt reinhardt) {
        this.reinhardt = reinhardt;
    }

    /**
     * Gets roadhog.
     *
     * @return the roadhog
     */
    public Roadhog getRoadhog() {
        return roadhog;
    }

    /**
     * Sets roadhog.
     *
     * @param roadhog the roadhog
     */
    public void setRoadhog(Roadhog roadhog) {
        this.roadhog = roadhog;
    }

    /**
     * Gets sigma.
     *
     * @return the sigma
     */
    public Sigma getSigma() {
        return sigma;
    }

    /**
     * Sets sigma.
     *
     * @param sigma the sigma
     */
    public void setSigma(Sigma sigma) {
        this.sigma = sigma;
    }

    /**
     * Gets soldier 76.
     *
     * @return the soldier 76
     */
    public Soldier76 getSoldier76() {
        return soldier76;
    }

    /**
     * Sets soldier 76.
     *
     * @param soldier76 the soldier 76
     */
    public void setSoldier76(Soldier76 soldier76) {
        this.soldier76 = soldier76;
    }

    /**
     * Gets sombra.
     *
     * @return the sombra
     */
    public Sombra getSombra() {
        return sombra;
    }

    /**
     * Sets sombra.
     *
     * @param sombra the sombra
     */
    public void setSombra(Sombra sombra) {
        this.sombra = sombra;
    }

    /**
     * Gets symmetra.
     *
     * @return the symmetra
     */
    public Symmetra getSymmetra() {
        return symmetra;
    }

    /**
     * Sets symmetra.
     *
     * @param symmetra the symmetra
     */
    public void setSymmetra(Symmetra symmetra) {
        this.symmetra = symmetra;
    }

    /**
     * Gets torbjorn.
     *
     * @return the torbjorn
     */
    public Torbjörn getTorbjorn() {
        return torbjorn;
    }

    /**
     * Sets torbjorn.
     *
     * @param torbjorn the torbjorn
     */
    public void setTorbjorn(Torbjörn torbjorn) {
        this.torbjorn = torbjorn;
    }

    /**
     * Gets tracer.
     *
     * @return the tracer
     */
    public Tracer getTracer() {
        return tracer;
    }

    /**
     * Sets tracer.
     *
     * @param tracer the tracer
     */
    public void setTracer(Tracer tracer) {
        this.tracer = tracer;
    }

    /**
     * Gets widowmaker.
     *
     * @return the widowmaker
     */
    public Widowmaker getWidowmaker() {
        return widowmaker;
    }

    /**
     * Sets widowmaker.
     *
     * @param widowmaker the widowmaker
     */
    public void setWidowmaker(Widowmaker widowmaker) {
        this.widowmaker = widowmaker;
    }

    /**
     * Gets winston.
     *
     * @return the winston
     */
    public Winston getWinston() {
        return winston;
    }

    /**
     * Sets winston.
     *
     * @param winston the winston
     */
    public void setWinston(Winston winston) {
        this.winston = winston;
    }

    /**
     * Gets wrecking ball.
     *
     * @return the wrecking ball
     */
    public WreckingBall getWreckingBall() {
        return wreckingBall;
    }

    /**
     * Sets wrecking ball.
     *
     * @param wreckingBall the wrecking ball
     */
    public void setWreckingBall(WreckingBall wreckingBall) {
        this.wreckingBall = wreckingBall;
    }

    /**
     * Gets zarya.
     *
     * @return the zarya
     */
    public Zarya getZarya() {
        return zarya;
    }

    /**
     * Sets zarya.
     *
     * @param zarya the zarya
     */
    public void setZarya(Zarya zarya) {
        this.zarya = zarya;
    }

    /**
     * Gets zenyatta.
     *
     * @return the zenyatta
     */
    public Zenyatta getZenyatta() {
        return zenyatta;
    }

    /**
     * Sets zenyatta.
     *
     * @param zenyatta the zenyatta
     */
    public void setZenyatta(Zenyatta zenyatta) {
        this.zenyatta = zenyatta;
    }

    /**
     * Gets hero list.
     *
     * @return the hero list
     */
    public ArrayList<TopHero> getHeroList() {
        heroList.add(ana);
        heroList.add(ashe);
        heroList.add(baptiste);
        heroList.add(bastion);
        heroList.add(brigitte);
        heroList.add(dVa);
        heroList.add(echo);
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
