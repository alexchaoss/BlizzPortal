package com.BlizzardArmory.model.overwatch

import com.BlizzardArmory.model.overwatch.heroes.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Career stats.
 */
data class CareerStats(
        @SerializedName("allHeroes")
        @Expose
        private val allHeroes: AllHeroes,

        @SerializedName("ana")
        @Expose
        var ana: Ana,

        @SerializedName("ashe")
        @Expose
        var ashe: Ashe,

        @SerializedName("baptiste")
        @Expose
        var baptiste: Baptiste,

        @SerializedName("bastion")
        @Expose
        var bastion: Bastion,

        @SerializedName("brigitte")
        @Expose
        var brigitte: Brigitte,

        @SerializedName("dVa")
        @Expose
        var dVa: DVa,

        @SerializedName("echo")
        @Expose
        var echo: Echo,

        @SerializedName("doomfist")
        @Expose
        var doomfist: Doomfist,

        @SerializedName("genji")
        @Expose
        var genji: Genji,

        @SerializedName("hanzo")
        @Expose
        var hanzo: Hanzo,

        @SerializedName("junkrat")
        @Expose
        var junkrat: Junkrat,

        @SerializedName("lucio")
        @Expose
        var lucio: Lúcio,

        @SerializedName("mccree")
        @Expose
        var mccree: Mccree,

        @SerializedName("mei")
        @Expose
        var mei: Mei,

        @SerializedName("mercy")
        @Expose
        var mercy: Mercy,

        @SerializedName("moira")
        @Expose
        var moira: Moira,

        @SerializedName("orisa")
        @Expose
        var orisa: Orisa,

        @SerializedName("pharah")
        @Expose
        var pharah: Pharah,

        @SerializedName("reaper")
        @Expose
        var reaper: Reaper,

        @SerializedName("reinhardt")
        @Expose
        var reinhardt: Reinhardt,

        @SerializedName("roadhog")
        @Expose
        var roadhog: Roadhog,

        @SerializedName("sigma")
        @Expose
        var sigma: Sigma,

        @SerializedName("soldier76")
        @Expose
        var soldier76: Soldier76,

        @SerializedName("sombra")
        @Expose
        var sombra: Sombra,

        @SerializedName("symmetra")
        @Expose
        var symmetra: Symmetra,

        @SerializedName("torbjorn")
        @Expose
        var torbjorn: Torbjörn,

        @SerializedName("tracer")
        @Expose
        var tracer: Tracer,

        @SerializedName("widowmaker")
        @Expose
        var widowmaker: Widowmaker,

        @SerializedName("winston")
        @Expose
        var winston: Winston,

        @SerializedName("wreckingBall")
        @Expose
        var wreckingBall: WreckingBall,

        @SerializedName("zarya")
        @Expose
        var zarya: Zarya,

        @SerializedName("zenyatta")
        @Expose
        var zenyatta: Zenyatta


) {
    val heroList: ArrayList<Hero>
        get() {
            val tempHeroList = arrayListOf<Hero>()
            tempHeroList.add(allHeroes)
            tempHeroList.add(ana)
            tempHeroList.add(ashe)
            tempHeroList.add(baptiste)
            tempHeroList.add(bastion)
            tempHeroList.add(brigitte)
            tempHeroList.add(dVa)
            tempHeroList.add(echo)
            tempHeroList.add(doomfist)
            tempHeroList.add(genji)
            tempHeroList.add(hanzo)
            tempHeroList.add(junkrat)
            tempHeroList.add(lucio)
            tempHeroList.add(mccree)
            tempHeroList.add(mei)
            tempHeroList.add(mercy)
            tempHeroList.add(moira)
            tempHeroList.add(orisa)
            tempHeroList.add(pharah)
            tempHeroList.add(reaper)
            tempHeroList.add(reinhardt)
            tempHeroList.add(roadhog)
            tempHeroList.add(sigma)
            tempHeroList.add(soldier76)
            tempHeroList.add(sombra)
            tempHeroList.add(symmetra)
            tempHeroList.add(torbjorn)
            tempHeroList.add(tracer)
            tempHeroList.add(widowmaker)
            tempHeroList.add(winston)
            tempHeroList.add(wreckingBall)
            tempHeroList.add(zarya)
            tempHeroList.add(zenyatta)
            return tempHeroList
        }
}