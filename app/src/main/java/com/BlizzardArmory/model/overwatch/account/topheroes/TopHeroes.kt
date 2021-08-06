package com.BlizzardArmory.model.overwatch.account.topheroes

import com.google.gson.annotations.SerializedName


class TopHeroes {
    @SerializedName("ana")
    var ana: Ana? = null

    @SerializedName("ashe")
    var ashe: Ashe? = null

    @SerializedName("baptiste")
    var baptiste: Baptiste? = null

    @SerializedName("bastion")
    var bastion: Bastion? = null

    @SerializedName("brigitte")
    var brigitte: Brigitte? = null

    @SerializedName("dVa")
    var dVa: DVa? = null

    @SerializedName("echo")
    var echo: Echo? = null

    @SerializedName("doomfist")
    var doomfist: Doomfist? = null

    @SerializedName("genji")
    var genji: Genji? = null

    @SerializedName("hanzo")
    var hanzo: Hanzo? = null

    @SerializedName("junkrat")
    var junkrat: Junkrat? = null

    @SerializedName("lucio")
    var lucio: Lúcio? = null

    @SerializedName("mccree")
    var mccree: Mccree? = null

    @SerializedName("mei")
    var mei: Mei? = null

    @SerializedName("mercy")
    var mercy: Mercy? = null

    @SerializedName("moira")
    var moira: Moira? = null

    @SerializedName("orisa")
    var orisa: Orisa? = null

    @SerializedName("pharah")
    var pharah: Pharah? = null

    @SerializedName("reaper")
    var reaper: Reaper? = null

    @SerializedName("reinhardt")
    var reinhardt: Reinhardt? = null

    @SerializedName("roadhog")
    var roadhog: Roadhog? = null

    @SerializedName("sigma")
    var sigma: Sigma? = null

    @SerializedName("soldier76")
    var soldier76: Soldier76? = null

    @SerializedName("sombra")
    var sombra: Sombra? = null

    @SerializedName("symmetra")
    var symmetra: Symmetra? = null

    @SerializedName("torbjorn")
    var torbjorn: Torbjörn? = null

    @SerializedName("tracer")
    var tracer: Tracer? = null

    @SerializedName("widowmaker")
    var widowmaker: Widowmaker? = null

    @SerializedName("winston")
    var winston: Winston? = null

    @SerializedName("wreckingBall")
    var wreckingBall: WreckingBall? = null

    @SerializedName("zarya")
    var zarya: Zarya? = null

    @SerializedName("zenyatta")
    var zenyatta: Zenyatta? = null

    val heroList = ArrayList<TopHero>()

    fun getFullHeroList(): ArrayList<TopHero> {
        ana?.let { heroList.add(it) }
        ashe?.let { heroList.add(it) }
        baptiste?.let { heroList.add(it) }
        bastion?.let { heroList.add(it) }
        brigitte?.let { heroList.add(it) }
        dVa?.let { heroList.add(it) }
        echo?.let { heroList.add(it) }
        doomfist?.let { heroList.add(it) }
        genji?.let { heroList.add(it) }
        hanzo?.let { heroList.add(it) }
        junkrat?.let { heroList.add(it) }
        lucio?.let { heroList.add(it) }
        mccree?.let { heroList.add(it) }
        mei?.let { heroList.add(it) }
        mercy?.let { heroList.add(it) }
        moira?.let { heroList.add(it) }
        orisa?.let { heroList.add(it) }
        pharah?.let { heroList.add(it) }
        reaper?.let { heroList.add(it) }
        reinhardt?.let { heroList.add(it) }
        roadhog?.let { heroList.add(it) }
        sigma?.let { heroList.add(it) }
        soldier76?.let { heroList.add(it) }
        sombra?.let { heroList.add(it) }
        symmetra?.let { heroList.add(it) }
        torbjorn?.let { heroList.add(it) }
        tracer?.let { heroList.add(it) }
        widowmaker?.let { heroList.add(it) }
        winston?.let { heroList.add(it) }
        wreckingBall?.let { heroList.add(it) }
        zarya?.let { heroList.add(it) }
        zenyatta?.let { heroList.add(it) }
        return heroList
    }
}