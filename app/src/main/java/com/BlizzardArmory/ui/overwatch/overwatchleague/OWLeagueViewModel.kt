package com.BlizzardArmory.ui.overwatch.overwatchleague

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.overwatch.statslab.*
import com.BlizzardArmory.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OWLeagueViewModel(application: Application) : BaseViewModel(application) {

    private var matchesList: MutableLiveData<List<Matches>> = MutableLiveData()

    private var _matchesList = mutableListOf<Matches>()

    private val START_TIME = 0
    private val MATCH_ID = 1
    private val STAGE = 2
    private val MAP_TYPE = 3
    private val MAP_NAME = 4
    private val PLAYER = 5
    private val TEAM = 6
    private val STAT_NAME = 7
    private val HERO = 8
    private val STAT_AMOUNT = 9

    private val tempMatchesList = mutableListOf<Match>()
    private val tempListPlayers = mutableListOf<Player>()
    private val tempListHero = mutableListOf<Hero>()
    private val tempListStats = mutableListOf<Stat>()

    private var previousStartTime = ""
    private var previousMatch = ""
    private var previousStage = ""
    private var previousMapType = ""
    private var previousMapName = ""
    private var previousPlayer = ""
    private var previousTeam = ""
    private var previousStatName = ""
    private var previousHero = ""
    private var previousStatAmount = ""

    private var teamCount = 0

    lateinit var tempTeam1: Team
    private lateinit var tempTeam2: Team

    fun getMatches(): LiveData<List<Matches>> {
        return matchesList
    }

    fun parseCSV() {
        val job = coroutineScope.launch {
            /*csvReader().openAsync(getApplication<Application>().resources.openRawResource(R.raw.phs_2018_playoffs)) {
                readAllAsSequence().forEach {
                    if (it[START_TIME] == "start_time") {
                        setPreviousData(it)
                    } else {
                        parseMatch(it)
                    }
                    setPreviousData(it)
                }
            }*/
            tempTeam2 = Team(previousTeam, tempListPlayers.toList())
            tempMatchesList.add(Match(previousStartTime, previousStage, previousMapType, previousMapName, tempTeam1, tempTeam2))
            _matchesList.add(Matches(previousMatch.toLong(), tempMatchesList.toList()))
            withContext(Dispatchers.Main) {
                matchesList.value = _matchesList
            }
        }
        jobs["parseCSV"] = job
    }

    private fun parseMatch(it: List<String>) {
        if (it[MATCH_ID] == previousMatch || previousMatch == "match_id") {
            parseTeam(it)
        } else {
            if (tempMatchesList.last().startTime != previousStartTime) {
                tempTeam2 = Team(previousTeam, tempListPlayers.toList())
                tempMatchesList.add(Match(previousStartTime, previousStage, previousMapType, previousMapName, tempTeam1, tempTeam2))
            }
            _matchesList.add(Matches(previousMatch.toLong(), tempMatchesList.toList()))
            tempMatchesList.clear()
            tempListPlayers.clear()
            tempListHero.clear()
            tempListStats.clear()
        }
    }

    private fun parseTeam(it: List<String>) {
        if (it[TEAM] == previousTeam || previousTeam == "team") {
            parsePlayer(it)
        } else {
            teamCount++
            if (teamCount == 2) {
                tempListStats.add(Stat(it[STAT_NAME], it[STAT_AMOUNT].toDouble()))
                tempListHero.add(Hero(previousHero, tempListStats.toList()))
                tempListPlayers.add(Player(previousPlayer, tempListHero.toList()))
                tempTeam2 = Team(previousTeam, tempListPlayers.toList())
                tempMatchesList.add(Match(previousStartTime, previousStage, previousMapType, previousMapName, tempTeam1, tempTeam2))
                tempListPlayers.clear()
                tempListHero.clear()
                tempListStats.clear()
                teamCount = 0
            } else {
                tempListStats.add(Stat(it[STAT_NAME], it[STAT_AMOUNT].toDouble()))
                tempListHero.add(Hero(previousHero, tempListStats.toList()))
                tempListPlayers.add(Player(previousPlayer, tempListHero.toList()))
                tempTeam1 = Team(previousTeam, tempListPlayers.toList())
                tempListHero.clear()
                tempListStats.clear()
                tempListPlayers.clear()
            }
            parsePlayer(it)
        }
    }

    private fun parsePlayer(it: List<String>) {
        if (it[PLAYER] == previousPlayer || previousPlayer == "player") {
            parseHero(it)
        } else {
            if (tempListHero.isNotEmpty()) {
                tempListHero.add(Hero(previousHero, tempListStats.toList()))
                tempListPlayers.add(Player(previousPlayer, tempListHero.toList()))
                tempListHero.clear()
                tempListStats.clear()
            }
            parseHero(it)
        }
    }

    private fun parseHero(it: List<String>) {
        if (it[HERO] == previousHero || previousHero == "hero") {
            parseStat(it)
        } else {
            if (tempListStats.isNotEmpty()) {
                tempListHero.add(Hero(previousHero, tempListStats.toList()))
                tempListStats.clear()
            }
            parseStat(it)
        }
    }

    private fun parseStat(it: List<String>) {
        tempListStats.add(Stat(it[STAT_NAME], it[STAT_AMOUNT].toDouble()))
    }

    private fun setPreviousData(it: List<String>) {
        previousStartTime = it[START_TIME]
        previousMatch = it[MATCH_ID]
        previousStage = it[STAGE]
        previousMapType = it[MAP_TYPE]
        previousMapName = it[MAP_NAME]
        previousPlayer = it[PLAYER]
        previousTeam = it[TEAM]
        previousStatName = it[STAT_NAME]
        previousHero = it[HERO]
        previousStatAmount = it[STAT_AMOUNT]
    }
}