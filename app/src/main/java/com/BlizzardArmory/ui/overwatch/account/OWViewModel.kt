package com.BlizzardArmory.ui.overwatch.account

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.overwatch.account.Profile
import com.BlizzardArmory.model.overwatch.account.heroes.Hero
import com.BlizzardArmory.model.overwatch.account.topheroes.TopHero
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import java.io.InputStream

class OWViewModel(application: Application) : BaseViewModel(application) {

    private val TIME_PLAYED = "Time Played"
    private val GAMES_WON = "Games Won"
    private val WEAPON_ACCURACY = "Weapon Accuracy"
    private val ELIMINATIONS_PER_LIFE = "Eliminations per Life"
    private val MULTIKILL_BEST = "Multikill - Best"
    private val OBJECTIVE_KILLS = "Objective Kills"
    private val sortHeroList = arrayOf(TIME_PLAYED, GAMES_WON, WEAPON_ACCURACY, ELIMINATIONS_PER_LIFE, MULTIKILL_BEST, OBJECTIVE_KILLS)

    private val sortCareerHeroes = arrayListOf<String>()
    private var comp: MutableLiveData<Boolean> = MutableLiveData()

    private var topHeroesQuickPlay: MutableLiveData<ArrayList<TopHero>> = MutableLiveData()
    private var topHeroesCompetitive: MutableLiveData<ArrayList<TopHero>> = MutableLiveData()
    private var careerQuickPlay: MutableLiveData<ArrayList<Hero>> = MutableLiveData()
    private var careerCompetitive: MutableLiveData<ArrayList<Hero>> = MutableLiveData()
    private var endorsementByteStream: MutableLiveData<InputStream> = MutableLiveData()
    private var profile: MutableLiveData<Profile> = MutableLiveData()

    init {
        topHeroesCompetitive.value = arrayListOf()
        topHeroesQuickPlay.value = arrayListOf()
        careerCompetitive.value = arrayListOf()
        careerQuickPlay.value = arrayListOf()
    }

    fun getProfile(): LiveData<Profile> {
        return profile
    }

    fun getEndorsementByteStream(): LiveData<InputStream> {
        return endorsementByteStream
    }

    fun getCompToggle(): MutableLiveData<Boolean> {
        return comp
    }

    fun getTopHeroesQuickPlay(): LiveData<ArrayList<TopHero>> {
        return topHeroesQuickPlay
    }

    fun getTopHeroesCompetitive(): LiveData<ArrayList<TopHero>> {
        return topHeroesCompetitive
    }

    fun getCareerQuickPlay(): LiveData<ArrayList<Hero>> {
        return careerQuickPlay
    }

    fun getCareerCompetitive(): LiveData<ArrayList<Hero>> {
        return careerCompetitive
    }

    fun getSortList(): Array<String> {
        return sortHeroList
    }

    fun getCareerSortList(): ArrayList<String> {
        return sortCareerHeroes
    }

    fun downloadAccountInformation(username: String, platform: String) {
        NetworkUtils.loading = true
        executeAPICall({ RetroClient.getOWClient(getApplication(), true).getOWProfile(NetworkUtils.getOWProfile(username, platform)) },
            {
                profile.value = it.body()
                NetworkUtils.loading = false
                setTopHeroesLists()
                setCareerLists()
                setSpinnerCareerList(careerQuickPlay.value!!)
                comp.value = false
            })
    }

    fun setCareerLists() {
        careerQuickPlay.value = profile.value?.quickPlayStats?.careerStats?.getFullHeroList()!!
        careerCompetitive.value = profile.value?.competitiveStats?.careerStats?.getFullHeroList()!!
    }

    fun setSpinnerCareerList(career: ArrayList<Hero>) {
        sortCareerHeroes.clear()
        sortCareerHeroes.addAll(career.map { it.getName() })
    }

    private fun setTopHeroesLists() {
        topHeroesQuickPlay.value = profile.value?.quickPlayStats?.topHeroes?.getFullHeroList()!!
        topHeroesCompetitive.value = profile.value?.competitiveStats?.topHeroes?.getFullHeroList()!!

        sortList(topHeroesCompetitive.value, sortHeroList[0])
        sortList(topHeroesQuickPlay.value, sortHeroList[0])
    }

    fun downloadEndorsementIcon(url: String) {
        executeAPICall({ RetroClient.getOWClient(getApplication()).getEndorsementSVG(url)}, {
            endorsementByteStream.value = it.body()?.byteStream()
        })
    }


    fun sortList(topHeroes: ArrayList<TopHero>?, howToSort: String) {
        when (howToSort) {
            TIME_PLAYED -> topHeroes?.sortByDescending { getSeconds(it) }
            GAMES_WON -> topHeroes?.sortByDescending { it.gamesWon }
            WEAPON_ACCURACY -> topHeroes?.sortByDescending { it.weaponAccuracy }
            ELIMINATIONS_PER_LIFE -> topHeroes?.sortByDescending { it.eliminationsPerLife }
            MULTIKILL_BEST -> topHeroes?.sortByDescending { it.multiKillBest }
            OBJECTIVE_KILLS -> topHeroes?.sortByDescending { it.objectiveKills }
        }
    }

    private fun getSeconds(hero: TopHero): Int {
        var secondsHero1 = 0
        when {
            StringUtils.countMatches(hero.timePlayed, ":") == 2 -> {
                secondsHero1 += hero.timePlayed!!.substring(0, hero.timePlayed!!.indexOf(":"))
                    .toInt() * 3600
                secondsHero1 += hero.timePlayed!!.substring(hero.timePlayed!!.indexOf(":") + 1, hero.timePlayed!!.lastIndexOf(":"))
                    .toInt() * 60
                secondsHero1 += hero.timePlayed!!.substring(hero.timePlayed!!.lastIndexOf(":") + 1)
                    .toInt()
            }
            StringUtils.countMatches(hero.timePlayed, ":") == 1 -> {
                secondsHero1 += hero.timePlayed!!.substring(0, hero.timePlayed!!.indexOf(":"))
                    .toInt() * 60
                secondsHero1 += hero.timePlayed!!.substring(hero.timePlayed!!.lastIndexOf(":") + 1)
                    .toInt()
            }
            else -> {
                secondsHero1 = hero.timePlayed!!.toInt()
            }
        }
        return secondsHero1
    }

}