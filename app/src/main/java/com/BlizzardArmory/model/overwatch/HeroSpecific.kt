package com.BlizzardArmory.model.overwatch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * The type Hero specific.
 */
class HeroSpecific {

    @SerializedName("enemiesTrapped")
    @Expose
    var enemiesTrapped = 0.0

    @SerializedName("enemiesTrappedAvgPer10Min")
    @Expose
    var enemiesTrappedAvgPer10Min = 0f

    @SerializedName("enemiesTrappedMostInGame")
    @Expose
    var enemiesTrappedMostInGame = 0.0

    @SerializedName("ripTireKills")
    @Expose
    var ripTireKills = 0.0

    @SerializedName("ripTireKillsAvgPer10Min")
    @Expose
    var ripTireKillsAvgPer10Min = 0f

    @SerializedName("ripTireKillsMostInGame")
    @Expose
    var ripTireKillsMostInGame = 0.0

    @SerializedName("dragonstrikeKills")
    @Expose
    var dragonstrikeKills = 0.0

    @SerializedName("dragonstrikeKillsAvgPer10Min")
    @Expose
    var dragonstrikeKillsAvgPer10Min = 0f

    @SerializedName("dragonstrikeKillsMostInGame")
    @Expose
    var dragonstrikeKillsMostInGame = 0.0

    @SerializedName("scatterArrowKills")
    @Expose
    var scatterArrowKills = 0.0

    @SerializedName("scatterArrowKillsAvgPer10Min")
    @Expose
    var scatterArrowKillsAvgPer10Min = 0f

    @SerializedName("scatterArrowKillsMostInGame")
    @Expose
    var scatterArrowKillsMostInGame = 0.0

    @SerializedName("stormArrowKills")
    @Expose
    var stormArrowKills = 0.0

    @SerializedName("stormArrowKillsAvgPer10Min")
    @Expose
    var stormArrowKillsAvgPer10Min = 0f

    @SerializedName("stormArrowKillsMostInGame")
    @Expose
    var stormArrowKillsMostInGame = 0.0

    @SerializedName("bioticGrenadeKills")
    @Expose
    var bioticGrenadeKills = 0.0

    @SerializedName("enemiesSlept")
    @Expose
    var enemiesSlept = 0.0

    @SerializedName("enemiesSleptAvgPer10Min")
    @Expose
    var enemiesSleptAvgPer10Min = 0f

    @SerializedName("enemiesSleptMostInGame")
    @Expose
    var enemiesSleptMostInGame = 0.0

    @SerializedName("nanoBoostAssists")
    @Expose
    var nanoBoostAssists = 0.0

    @SerializedName("nanoBoostAssistsAvgPer10Min")
    @Expose
    var nanoBoostAssistsAvgPer10Min = 0f

    @SerializedName("nanoBoostAssistsMostInGame")
    @Expose
    var nanoBoostAssistsMostInGame = 0.0

    @SerializedName("nanoBoostsApplied")
    @Expose
    var nanoBoostsApplied = 0.0

    @SerializedName("nanoBoostsAppliedAvgPer10Min")
    @Expose
    var nanoBoostsAppliedAvgPer10Min = 0f

    @SerializedName("nanoBoostsAppliedMostInGame")
    @Expose
    var nanoBoostsAppliedMostInGame = 0.0

    @SerializedName("scopedAccuracy")
    @Expose
    var scopedAccuracy: String? = null

    @SerializedName("scopedAccuracyBestInGame")
    @Expose
    var scopedAccuracyBestInGame: String? = null

    @SerializedName("selfHealing")
    @Expose
    var selfHealing = 0.0

    @SerializedName("selfHealingAvgPer10Min")
    @Expose
    var selfHealingAvgPer10Min = 0.0

    @SerializedName("selfHealingMostInGame")
    @Expose
    var selfHealingMostInGame = 0.0

    @SerializedName("unscopedAccuracy")
    @Expose
    var unscopedAccuracy: String? = null

    @SerializedName("unscopedAccuracyBestInGame")
    @Expose
    var unscopedAccuracyBestInGame: String? = null

    @SerializedName("bobKills")
    @Expose
    var bobKills = 0.0

    @SerializedName("bobKillsAvgPer10Min")
    @Expose
    var bobKillsAvgPer10Min = 0f

    @SerializedName("bobKillsMostInGame")
    @Expose
    var bobKillsMostInGame = 0.0

    @SerializedName("coachGunKills")
    @Expose
    var coachGunKills = 0.0

    @SerializedName("coachGunKillsAvgPer10Min")
    @Expose
    var coachGunKillsAvgPer10Min = 0f

    @SerializedName("coachGunKillsMostInGame")
    @Expose
    var coachGunKillsMostInGame = 0.0

    @SerializedName("dynamiteKills")
    @Expose
    var dynamiteKills = 0.0

    @SerializedName("dynamiteKillsAvgPer10Min")
    @Expose
    var dynamiteKillsAvgPer10Min = 0f

    @SerializedName("dynamiteKillsMostInGame")
    @Expose
    var dynamiteKillsMostInGame = 0.0

    @SerializedName("scopedCriticalHits")
    @Expose
    var scopedCriticalHits = 0.0

    @SerializedName("scopedCriticalHitsAccuracy")
    @Expose
    var scopedCriticalHitsAccuracy: String? = null

    @SerializedName("scopedCriticalHitsAvgPer10Min")
    @Expose
    var scopedCriticalHitsAvgPer10Min = 0f

    @SerializedName("scopedCriticalHitsMostInGame")
    @Expose
    var scopedCriticalHitsMostInGame = 0.0

    @SerializedName("amplificationMatrixCasts")
    @Expose
    var amplificationMatrixCasts = 0.0

    @SerializedName("amplificationMatrixCastsAvgPer10Min")
    @Expose
    var amplificationMatrixCastsAvgPer10Min = 0f

    @SerializedName("amplificationMatrixCastsMostInGame")
    @Expose
    var amplificationMatrixCastsMostInGame = 0.0

    @SerializedName("damageAmplified")
    @Expose
    var damageAmplified = 0.0

    @SerializedName("damageAmplifiedAvgPer10Min")
    @Expose
    var damageAmplifiedAvgPer10Min = 0.0

    @SerializedName("damageAmplifiedMostInGame")
    @Expose
    var damageAmplifiedMostInGame = 0.0

    @SerializedName("healingAccuracy")
    @Expose
    var healingAccuracy: String? = null

    @SerializedName("healingAccuracyBestInGame")
    @Expose
    var healingAccuracyBestInGame: String? = null

    @SerializedName("immortalityFieldDeathsPrevented")
    @Expose
    var immortalityFieldDeathsPrevented = 0.0

    @SerializedName("immortalityFieldDeathsPreventedAvgPer10Min")
    @Expose
    var immortalityFieldDeathsPreventedAvgPer10Min = 0f

    @SerializedName("immortalityFieldDeathsPreventedMostInGame")
    @Expose
    var immortalityFieldDeathsPreventedMostInGame = 0.0

    @SerializedName("reconKills")
    @Expose
    var reconKills = 0.0

    @SerializedName("reconKillsAvgPer10Min")
    @Expose
    var reconKillsAvgPer10Min = 0f

    @SerializedName("reconKillsMostInGame")
    @Expose
    var reconKillsMostInGame = 0.0

    @SerializedName("sentryKills")
    @Expose
    var sentryKills = 0.0

    @SerializedName("sentryKillsAvgPer10Min")
    @Expose
    var sentryKillsAvgPer10Min = 0f

    @SerializedName("sentryKillsMostInGame")
    @Expose
    var sentryKillsMostInGame = 0.0

    @SerializedName("tankKills")
    @Expose
    var tankKills = 0.0

    @SerializedName("tankKillsAvgPer10Min")
    @Expose
    var tankKillsAvgPer10Min = 0f

    @SerializedName("tankKillsMostInGame")
    @Expose
    var tankKillsMostInGame = 0.0

    @SerializedName("armorProvided")
    @Expose
    var armorProvided = 0.0

    @SerializedName("armorProvidedAvgPer10Min")
    @Expose
    var armorProvidedAvgPer10Min = 0.0

    @SerializedName("armorProvidedMostInGame")
    @Expose
    var armorProvidedMostInGame = 0.0

    @SerializedName("damageBlocked")
    @Expose
    var damageBlocked = 0.0

    @SerializedName("damageBlockedAvgPer10Min")
    @Expose
    var damageBlockedAvgPer10Min = 0.0

    @SerializedName("damageBlockedMostInGame")
    @Expose
    var damageBlockedMostInGame = 0.0

    @SerializedName("inspireUptimePercentage")
    @Expose
    var inspireUptimePercentage: String? = null

    @SerializedName("mechDeaths")
    @Expose
    var mechDeaths = 0.0

    @SerializedName("mechsCalled")
    @Expose
    var mechsCalled = 0.0

    @SerializedName("mechsCalledAvgPer10Min")
    @Expose
    var mechsCalledAvgPer10Min = 0f

    @SerializedName("mechsCalledMostInGame")
    @Expose
    var mechsCalledMostInGame = 0.0

    @SerializedName("selfDestructKills")
    @Expose
    var selfDestructKills = 0.0

    @SerializedName("selfDestructKillsAvgPer10Min")
    @Expose
    var selfDestructKillsAvgPer10Min = 0f

    @SerializedName("selfDestructKillsMostInGame")
    @Expose
    var selfDestructKillsMostInGame = 0.0

    @SerializedName("abilityDamageDone")
    @Expose
    var abilityDamageDone = 0.0

    @SerializedName("abilityDamageDoneAvgPer10Min")
    @Expose
    var abilityDamageDoneAvgPer10Min = 0.0

    @SerializedName("abilityDamageDoneMostInGame")
    @Expose
    var abilityDamageDoneMostInGame = 0.0

    @SerializedName("meteorStrikeKills")
    @Expose
    var meteorStrikeKills = 0.0

    @SerializedName("meteorStrikeKillsAvgPer10Min")
    @Expose
    var meteorStrikeKillsAvgPer10Min = 0f

    @SerializedName("meteorStrikeKillsMostInGame")
    @Expose
    var meteorStrikeKillsMostInGame = 0.0

    @SerializedName("shieldsCreated")
    @Expose
    var shieldsCreated = 0.0

    @SerializedName("shieldsCreatedAvgPer10Min")
    @Expose
    var shieldsCreatedAvgPer10Min = 0.0

    @SerializedName("shieldsCreatedMostInGame")
    @Expose
    var shieldsCreatedMostInGame = 0.0

    @SerializedName("damageReflected")
    @Expose
    var damageReflected = 0.0

    @SerializedName("damageReflectedAvgPer10Min")
    @Expose
    var damageReflectedAvgPer10Min = 0.0

    @SerializedName("damageReflectedMostInGame")
    @Expose
    var damageReflectedMostInGame = 0.0

    @SerializedName("deflectionKills")
    @Expose
    var deflectionKills = 0.0

    @SerializedName("dragonbladesKills")
    @Expose
    var dragonbladesKills = 0.0

    @SerializedName("dragonbladesKillsAvgPer10Min")
    @Expose
    var dragonbladesKillsAvgPer10Min = 0f

    @SerializedName("dragonbladesKillsMostInGame")
    @Expose
    var dragonbladesKillsMostInGame = 0.0

    @SerializedName("soundBarriersProvided")
    @Expose
    var soundBarriersProvided = 0.0

    @SerializedName("soundBarriersProvidedAvgPer10Min")
    @Expose
    var soundBarriersProvidedAvgPer10Min = 0f

    @SerializedName("soundBarriersProvidedMostInGame")
    @Expose
    var soundBarriersProvidedMostInGame = 0.0

    @SerializedName("deadeyeKills")
    @Expose
    var deadeyeKills = 0.0

    @SerializedName("deadeyeKillsAvgPer10Min")
    @Expose
    var deadeyeKillsAvgPer10Min = 0f

    @SerializedName("deadeyeKillsMostInGame")
    @Expose
    var deadeyeKillsMostInGame = 0.0

    @SerializedName("fanTheHammerKills")
    @Expose
    var fanTheHammerKills = 0.0

    @SerializedName("fanTheHammerKillsAvgPer10Min")
    @Expose
    var fanTheHammerKillsAvgPer10Min = 0f

    @SerializedName("fanTheHammerKillsMostInGame")
    @Expose
    var fanTheHammerKillsMostInGame = 0.0

    @SerializedName("blizzardKills")
    @Expose
    var blizzardKills = 0.0

    @SerializedName("blizzardKillsAvgPer10Min")
    @Expose
    var blizzardKillsAvgPer10Min = 0f

    @SerializedName("blizzardKillsMostInGame")
    @Expose
    var blizzardKillsMostInGame = 0.0

    @SerializedName("enemiesFrozen")
    @Expose
    var enemiesFrozen = 0.0

    @SerializedName("enemiesFrozenAvgPer10Min")
    @Expose
    var enemiesFrozenAvgPer10Min = 0f

    @SerializedName("enemiesFrozenMostInGame")
    @Expose
    var enemiesFrozenMostInGame = 0.0

    @SerializedName("blasterKills")
    @Expose
    var blasterKills = 0.0

    @SerializedName("blasterKillsAvgPer10Min")
    @Expose
    var blasterKillsAvgPer10Min = 0f

    @SerializedName("blasterKillsMostInGame")
    @Expose
    var blasterKillsMostInGame = 0.0

    @SerializedName("playersResurrected")
    @Expose
    var playersResurrected = 0.0

    @SerializedName("playersResurrectedAvgPer10Min")
    @Expose
    var playersResurrectedAvgPer10Min = 0f

    @SerializedName("playersResurrectedMostInGame")
    @Expose
    var playersResurrectedMostInGame = 0.0

    @SerializedName("coalescenceHealing")
    @Expose
    var coalescenceHealing = 0.0

    @SerializedName("coalescenceHealingAvgPer10Min")
    @Expose
    var coalescenceHealingAvgPer10Min = 0.0

    @SerializedName("coalescenceHealingMostInGame")
    @Expose
    var coalescenceHealingMostInGame = 0.0

    @SerializedName("coalescenceKills")
    @Expose
    var coalescenceKills = 0.0

    @SerializedName("coalescenceKillsAvgPer10Min")
    @Expose
    var coalescenceKillsAvgPer10Min = 0f

    @SerializedName("coalescenceKillsMostInGame")
    @Expose
    var coalescenceKillsMostInGame = 0.0

    @SerializedName("secondaryFireAccuracy")
    @Expose
    var secondaryFireAccuracy: String? = null

    @SerializedName("superchargerAssists")
    @Expose
    var superchargerAssists = 0.0

    @SerializedName("superchargerAssistsAvgPer10Min")
    @Expose
    var superchargerAssistsAvgPer10Min = 0f

    @SerializedName("superchargerAssistsMostInGame")
    @Expose
    var superchargerAssistsMostInGame = 0.0

    @SerializedName("barrageKills")
    @Expose
    var barrageKills = 0.0

    @SerializedName("barrageKillsAvgPer10Min")
    @Expose
    var barrageKillsAvgPer10Min = 0f

    @SerializedName("barrageKillsMostInGame")
    @Expose
    var barrageKillsMostInGame = 0.0

    @SerializedName("directHitsAccuracy")
    @Expose
    var directHitsAccuracy: String? = null

    @SerializedName("rocketDirectHits")
    @Expose
    var rocketDirectHits = 0.0

    @SerializedName("rocketDirectHitsAvgPer10Min")
    @Expose
    var rocketDirectHitsAvgPer10Min = 0f

    @SerializedName("rocketDirectHitsMostInGame")
    @Expose
    var rocketDirectHitsMostInGame = 0.0

    @SerializedName("deathsBlossomKills")
    @Expose
    var deathsBlossomKills = 0.0

    @SerializedName("deathsBlossomKillsAvgPer10Min")
    @Expose
    var deathsBlossomKillsAvgPer10Min = 0f

    @SerializedName("deathsBlossomKillsMostInGame")
    @Expose
    var deathsBlossomKillsMostInGame = 0.0

    @SerializedName("chargeKills")
    @Expose
    var chargeKills = 0.0

    @SerializedName("chargeKillsAvgPer10Min")
    @Expose
    var chargeKillsAvgPer10Min = 0f

    @SerializedName("chargeKillsMostInGame")
    @Expose
    var chargeKillsMostInGame = 0.0

    @SerializedName("earthshatterKills")
    @Expose
    var earthshatterKills = 0.0

    @SerializedName("earthshatterKillsAvgPer10Min")
    @Expose
    var earthshatterKillsAvgPer10Min = 0f

    @SerializedName("earthshatterKillsMostInGame")
    @Expose
    var earthshatterKillsMostInGame = 0.0

    @SerializedName("fireStrikeKills")
    @Expose
    var fireStrikeKills = 0.0

    @SerializedName("fireStrikeKillsAvgPer10Min")
    @Expose
    var fireStrikeKillsAvgPer10Min = 0f

    @SerializedName("fireStrikeKillsMostInGame")
    @Expose
    var fireStrikeKillsMostInGame = 0.0

    @SerializedName("rocketHammerMeleeAccuracy")
    @Expose
    var rocketHammerMeleeAccuracy: String? = null

    @SerializedName("enemiesHooked")
    @Expose
    var enemiesHooked = 0.0

    @SerializedName("enemiesHookedAvgPer10Min")
    @Expose
    var enemiesHookedAvgPer10Min = 0f

    @SerializedName("enemiesHookedMostInGame")
    @Expose
    var enemiesHookedMostInGame = 0.0

    @SerializedName("hookAccuracy")
    @Expose
    var hookAccuracy: String? = null

    @SerializedName("hookAccuracyBestInGame")
    @Expose
    var hookAccuracyBestInGame: String? = null

    @SerializedName("hooksAttempted")
    @Expose
    var hooksAttempted = 0.0

    @SerializedName("wholeHogKills")
    @Expose
    var wholeHogKills = 0.0

    @SerializedName("wholeHogKillsAvgPer10Min")
    @Expose
    var wholeHogKillsAvgPer10Min = 0f

    @SerializedName("wholeHogKillsMostInGame")
    @Expose
    var wholeHogKillsMostInGame = 0.0

    @SerializedName("bioticFieldHealingDone")
    @Expose
    var bioticFieldHealingDone = 0.0

    @SerializedName("bioticFieldsDeployed")
    @Expose
    var bioticFieldsDeployed = 0.0

    @SerializedName("helixRocketsKills")
    @Expose
    var helixRocketsKills = 0.0

    @SerializedName("helixRocketsKillsAvgPer10Min")
    @Expose
    var helixRocketsKillsAvgPer10Min = 0f

    @SerializedName("helixRocketsKillsMostInGame")
    @Expose
    var helixRocketsKillsMostInGame = 0.0

    @SerializedName("tacticalVisorKills")
    @Expose
    var tacticalVisorKills = 0.0

    @SerializedName("tacticalVisorKillsAvgPer10Min")
    @Expose
    var tacticalVisorKillsAvgPer10Min = 0f

    @SerializedName("tacticalVisorKillsMostInGame")
    @Expose
    var tacticalVisorKillsMostInGame = 0.0

    @SerializedName("enemiesEmpd")
    @Expose
    var enemiesEmpd = 0.0

    @SerializedName("enemiesEmpdAvgPer10Min")
    @Expose
    var enemiesEmpdAvgPer10Min = 0f

    @SerializedName("enemiesEmpdMostInGame")
    @Expose
    var enemiesEmpdMostInGame = 0.0

    @SerializedName("enemiesHacked")
    @Expose
    var enemiesHacked = 0.0

    @SerializedName("enemiesHackedAvgPer10Min")
    @Expose
    var enemiesHackedAvgPer10Min = 0f

    @SerializedName("enemiesHackedMostInGame")
    @Expose
    var enemiesHackedMostInGame = 0.0

    @SerializedName("playersTeleported")
    @Expose
    var playersTeleported = 0.0

    @SerializedName("playersTeleportedAvgPer10Min")
    @Expose
    var playersTeleportedAvgPer10Min = 0f

    @SerializedName("playersTeleportedMostInGame")
    @Expose
    var playersTeleportedMostInGame = 0.0

    @SerializedName("primaryFireAccuracy")
    @Expose
    var primaryFireAccuracy: String? = null

    @SerializedName("secondaryDirectHitsAvgPer10Min")
    @Expose
    var secondaryDirectHitsAvgPer10Min = 0f

    @SerializedName("sentryTurretsKills")
    @Expose
    var sentryTurretsKills = 0.0

    @SerializedName("sentryTurretsKillsAvgPer10Min")
    @Expose
    var sentryTurretsKillsAvgPer10Min = 0f

    @SerializedName("sentryTurretsKillsMostInGame")
    @Expose
    var sentryTurretsKillsMostInGame = 0.0

    @SerializedName("armorPacksCreated")
    @Expose
    var armorPacksCreated = 0.0

    @SerializedName("armorPacksCreatedAvgPer10Min")
    @Expose
    var armorPacksCreatedAvgPer10Min = 0f

    @SerializedName("armorPacksCreatedMostInGame")
    @Expose
    var armorPacksCreatedMostInGame = 0.0

    @SerializedName("moltenCoreKills")
    @Expose
    var moltenCoreKills = 0.0

    @SerializedName("moltenCoreKillsAvgPer10Min")
    @Expose
    var moltenCoreKillsAvgPer10Min = 0f

    @SerializedName("moltenCoreKillsMostInGame")
    @Expose
    var moltenCoreKillsMostInGame = 0.0

    @SerializedName("overloadKills")
    @Expose
    var overloadKills = 0.0

    @SerializedName("overloadKillsMostInGame")
    @Expose
    var overloadKillsMostInGame = 0.0

    @SerializedName("torbjornKills")
    @Expose
    var torbjornKills = 0.0

    @SerializedName("torbjornKillsAvgPer10Min")
    @Expose
    var torbjornKillsAvgPer10Min = 0f

    @SerializedName("torbjornKillsMostInGame")
    @Expose
    var torbjornKillsMostInGame = 0.0

    @SerializedName("turretsDamageAvgPer10Min")
    @Expose
    var turretsDamageAvgPer10Min = 0.0

    @SerializedName("turretsKills")
    @Expose
    var turretsKills = 0.0

    @SerializedName("turretsKillsAvgPer10Min")
    @Expose
    var turretsKillsAvgPer10Min = 0f

    @SerializedName("turretsKillsMostInGame")
    @Expose
    var turretsKillsMostInGame = 0.0

    @SerializedName("healthRecovered")
    @Expose
    var healthRecovered = 0.0

    @SerializedName("healthRecoveredAvgPer10Min")
    @Expose
    var healthRecoveredAvgPer10Min = 0.0

    @SerializedName("healthRecoveredMostInGame")
    @Expose
    var healthRecoveredMostInGame = 0.0

    @SerializedName("pulseBombsAttached")
    @Expose
    var pulseBombsAttached = 0.0

    @SerializedName("pulseBombsAttachedAvgPer10Min")
    @Expose
    var pulseBombsAttachedAvgPer10Min = 0f

    @SerializedName("pulseBombsAttachedMostInGame")
    @Expose
    var pulseBombsAttachedMostInGame = 0.0

    @SerializedName("pulseBombsKills")
    @Expose
    var pulseBombsKills = 0.0

    @SerializedName("pulseBombsKillsAvgPer10Min")
    @Expose
    var pulseBombsKillsAvgPer10Min = 0f

    @SerializedName("pulseBombsKillsMostInGame")
    @Expose
    var pulseBombsKillsMostInGame = 0.0

    @SerializedName("venomMineKills")
    @Expose
    var venomMineKills = 0.0

    @SerializedName("venomMineKillsAvgPer10Min")
    @Expose
    var venomMineKillsAvgPer10Min = 0f

    @SerializedName("venomMineKillsMostInGame")
    @Expose
    var venomMineKillsMostInGame = 0.0

    @SerializedName("jumpKills")
    @Expose
    var jumpKills = 0.0

    @SerializedName("jumpPackKills")
    @Expose
    var jumpPackKills = 0.0

    @SerializedName("jumpPackKillsAvgPer10Min")
    @Expose
    var jumpPackKillsAvgPer10Min = 0f

    @SerializedName("jumpPackKillsMostInGame")
    @Expose
    var jumpPackKillsMostInGame = 0.0

    @SerializedName("meleeKills")
    @Expose
    var meleeKills = 0.0

    @SerializedName("meleeKillsAvgPer10Min")
    @Expose
    var meleeKillsAvgPer10Min = 0f

    @SerializedName("meleeKillsMostInGame")
    @Expose
    var meleeKillsMostInGame = 0.0

    @SerializedName("playersKnockedBack")
    @Expose
    var playersKnockedBack = 0.0

    @SerializedName("playersKnockedBackAvgPer10Min")
    @Expose
    var playersKnockedBackAvgPer10Min = 0f

    @SerializedName("playersKnockedBackMostInGame")
    @Expose
    var playersKnockedBackMostInGame = 0.0

    @SerializedName("primalRageKills")
    @Expose
    var primalRageKills = 0.0

    @SerializedName("primalRageKillsAvgPer10Min")
    @Expose
    var primalRageKillsAvgPer10Min = 0f

    @SerializedName("primalRageKillsMostInGame")
    @Expose
    var primalRageKillsMostInGame = 0.0

    @SerializedName("primalRageMeleeAccuracy")
    @Expose
    var primalRageMeleeAccuracy: String? = null

    @SerializedName("teslaCannonAccuracy")
    @Expose
    var teslaCannonAccuracy: String? = null

    @SerializedName("weaponKills")
    @Expose
    var weaponKills = 0.0

    @SerializedName("grapplingClawKills")
    @Expose
    var grapplingClawKills = 0.0

    @SerializedName("grapplingClawKillsAvgPer10Min")
    @Expose
    var grapplingClawKillsAvgPer10Min = 0f

    @SerializedName("grapplingClawKillsMostInGame")
    @Expose
    var grapplingClawKillsMostInGame = 0.0

    @SerializedName("minefieldKills")
    @Expose
    var minefieldKills = 0.0

    @SerializedName("minefieldKillsAvgPer10Min")
    @Expose
    var minefieldKillsAvgPer10Min = 0f

    @SerializedName("minefieldKillsMostInGame")
    @Expose
    var minefieldKillsMostInGame = 0.0

    @SerializedName("averageEnergy")
    @Expose
    var averageEnergy: String? = null

    @SerializedName("averageEnergyBestInGame")
    @Expose
    var averageEnergyBestInGame: String? = null

    @SerializedName("gravitonSurgeKills")
    @Expose
    var gravitonSurgeKills = 0.0

    @SerializedName("gravitonSurgeKillsAvgPer10Min")
    @Expose
    var gravitonSurgeKillsAvgPer10Min = 0f

    @SerializedName("gravitonSurgeKillsMostInGame")
    @Expose
    var gravitonSurgeKillsMostInGame = 0.0

    @SerializedName("highEnergyKills")
    @Expose
    var highEnergyKills = 0.0

    @SerializedName("highEnergyKillsAvgPer10Min")
    @Expose
    var highEnergyKillsAvgPer10Min = 0f

    @SerializedName("highEnergyKillsMostInGame")
    @Expose
    var highEnergyKillsMostInGame = 0.0

    @SerializedName("projectedBarriersApplied")
    @Expose
    var projectedBarriersApplied = 0.0

    @SerializedName("projectedBarriersAppliedAvgPer10Min")
    @Expose
    var projectedBarriersAppliedAvgPer10Min = 0f

    @SerializedName("projectedBarriersAppliedMostInGame")
    @Expose
    var projectedBarriersAppliedMostInGame = 0.0

    @SerializedName("transcendenceHealing")
    @Expose
    var transcendenceHealing = 0.0

    @SerializedName("transcendenceHealingBest")
    @Expose
    var transcendenceHealingBest = 0.0

}