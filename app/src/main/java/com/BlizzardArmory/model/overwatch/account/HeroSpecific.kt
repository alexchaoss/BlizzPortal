package com.BlizzardArmory.model.overwatch.account

import com.google.gson.annotations.SerializedName


/**
 * The type Hero specific.
 */
class HeroSpecific {

    @SerializedName("enemiesTrapped")
    var enemiesTrapped = 0.0

    @SerializedName("enemiesTrappedAvgPer10Min")
    var enemiesTrappedAvgPer10Min = 0f

    @SerializedName("enemiesTrappedMostInGame")
    var enemiesTrappedMostInGame = 0.0

    @SerializedName("ripTireKills")
    var ripTireKills = 0.0

    @SerializedName("ripTireKillsAvgPer10Min")
    var ripTireKillsAvgPer10Min = 0f

    @SerializedName("ripTireKillsMostInGame")
    var ripTireKillsMostInGame = 0.0

    @SerializedName("dragonstrikeKills")
    var dragonstrikeKills = 0.0

    @SerializedName("dragonstrikeKillsAvgPer10Min")
    var dragonstrikeKillsAvgPer10Min = 0f

    @SerializedName("dragonstrikeKillsMostInGame")
    var dragonstrikeKillsMostInGame = 0.0

    @SerializedName("scatterArrowKills")
    var scatterArrowKills = 0.0

    @SerializedName("scatterArrowKillsAvgPer10Min")
    var scatterArrowKillsAvgPer10Min = 0f

    @SerializedName("scatterArrowKillsMostInGame")
    var scatterArrowKillsMostInGame = 0.0

    @SerializedName("stormArrowKills")
    var stormArrowKills = 0.0

    @SerializedName("stormArrowKillsAvgPer10Min")
    var stormArrowKillsAvgPer10Min = 0f

    @SerializedName("stormArrowKillsMostInGame")
    var stormArrowKillsMostInGame = 0.0

    @SerializedName("bioticGrenadeKills")
    var bioticGrenadeKills = 0.0

    @SerializedName("enemiesSlept")
    var enemiesSlept = 0.0

    @SerializedName("enemiesSleptAvgPer10Min")
    var enemiesSleptAvgPer10Min = 0f

    @SerializedName("enemiesSleptMostInGame")
    var enemiesSleptMostInGame = 0.0

    @SerializedName("nanoBoostAssists")
    var nanoBoostAssists = 0.0

    @SerializedName("nanoBoostAssistsAvgPer10Min")
    var nanoBoostAssistsAvgPer10Min = 0f

    @SerializedName("nanoBoostAssistsMostInGame")
    var nanoBoostAssistsMostInGame = 0.0

    @SerializedName("nanoBoostsApplied")
    var nanoBoostsApplied = 0.0

    @SerializedName("nanoBoostsAppliedAvgPer10Min")
    var nanoBoostsAppliedAvgPer10Min = 0f

    @SerializedName("nanoBoostsAppliedMostInGame")
    var nanoBoostsAppliedMostInGame = 0.0

    @SerializedName("scopedAccuracy")
    var scopedAccuracy: String? = null

    @SerializedName("scopedAccuracyBestInGame")
    var scopedAccuracyBestInGame: String? = null

    @SerializedName("selfHealing")
    var selfHealing = 0.0

    @SerializedName("selfHealingAvgPer10Min")
    var selfHealingAvgPer10Min = 0.0

    @SerializedName("selfHealingMostInGame")
    var selfHealingMostInGame = 0.0

    @SerializedName("unscopedAccuracy")
    var unscopedAccuracy: String? = null

    @SerializedName("unscopedAccuracyBestInGame")
    var unscopedAccuracyBestInGame: String? = null

    @SerializedName("bobKills")
    var bobKills = 0.0

    @SerializedName("bobKillsAvgPer10Min")
    var bobKillsAvgPer10Min = 0f

    @SerializedName("bobKillsMostInGame")
    var bobKillsMostInGame = 0.0

    @SerializedName("coachGunKills")
    var coachGunKills = 0.0

    @SerializedName("coachGunKillsAvgPer10Min")
    var coachGunKillsAvgPer10Min = 0f

    @SerializedName("coachGunKillsMostInGame")
    var coachGunKillsMostInGame = 0.0

    @SerializedName("dynamiteKills")
    var dynamiteKills = 0.0

    @SerializedName("dynamiteKillsAvgPer10Min")
    var dynamiteKillsAvgPer10Min = 0f

    @SerializedName("dynamiteKillsMostInGame")
    var dynamiteKillsMostInGame = 0.0

    @SerializedName("scopedCriticalHits")
    var scopedCriticalHits = 0.0

    @SerializedName("scopedCriticalHitsAccuracy")
    var scopedCriticalHitsAccuracy: String? = null

    @SerializedName("scopedCriticalHitsAvgPer10Min")
    var scopedCriticalHitsAvgPer10Min = 0f

    @SerializedName("scopedCriticalHitsMostInGame")
    var scopedCriticalHitsMostInGame = 0.0

    @SerializedName("amplificationMatrixCasts")
    var amplificationMatrixCasts = 0.0

    @SerializedName("amplificationMatrixCastsAvgPer10Min")
    var amplificationMatrixCastsAvgPer10Min = 0f

    @SerializedName("amplificationMatrixCastsMostInGame")
    var amplificationMatrixCastsMostInGame = 0.0

    @SerializedName("damageAmplified")
    var damageAmplified = 0.0

    @SerializedName("damageAmplifiedAvgPer10Min")
    var damageAmplifiedAvgPer10Min = 0.0

    @SerializedName("damageAmplifiedMostInGame")
    var damageAmplifiedMostInGame = 0.0

    @SerializedName("healingAccuracy")
    var healingAccuracy: String? = null

    @SerializedName("healingAccuracyBestInGame")
    var healingAccuracyBestInGame: String? = null

    @SerializedName("immortalityFieldDeathsPrevented")
    var immortalityFieldDeathsPrevented = 0.0

    @SerializedName("immortalityFieldDeathsPreventedAvgPer10Min")
    var immortalityFieldDeathsPreventedAvgPer10Min = 0f

    @SerializedName("immortalityFieldDeathsPreventedMostInGame")
    var immortalityFieldDeathsPreventedMostInGame = 0.0

    @SerializedName("reconKills")
    var reconKills = 0.0

    @SerializedName("reconKillsAvgPer10Min")
    var reconKillsAvgPer10Min = 0f

    @SerializedName("reconKillsMostInGame")
    var reconKillsMostInGame = 0.0

    @SerializedName("sentryKills")
    var sentryKills = 0.0

    @SerializedName("sentryKillsAvgPer10Min")
    var sentryKillsAvgPer10Min = 0f

    @SerializedName("sentryKillsMostInGame")
    var sentryKillsMostInGame = 0.0

    @SerializedName("tankKills")
    var tankKills = 0.0

    @SerializedName("tankKillsAvgPer10Min")
    var tankKillsAvgPer10Min = 0f

    @SerializedName("tankKillsMostInGame")
    var tankKillsMostInGame = 0.0

    @SerializedName("armorProvided")
    var armorProvided = 0.0

    @SerializedName("armorProvidedAvgPer10Min")
    var armorProvidedAvgPer10Min = 0.0

    @SerializedName("armorProvidedMostInGame")
    var armorProvidedMostInGame = 0.0

    @SerializedName("damageBlocked")
    var damageBlocked = 0.0

    @SerializedName("damageBlockedAvgPer10Min")
    var damageBlockedAvgPer10Min = 0.0

    @SerializedName("damageBlockedMostInGame")
    var damageBlockedMostInGame = 0.0

    @SerializedName("inspireUptimePercentage")
    var inspireUptimePercentage: String? = null

    @SerializedName("mechDeaths")
    var mechDeaths = 0.0

    @SerializedName("mechsCalled")
    var mechsCalled = 0.0

    @SerializedName("mechsCalledAvgPer10Min")
    var mechsCalledAvgPer10Min = 0f

    @SerializedName("mechsCalledMostInGame")
    var mechsCalledMostInGame = 0.0

    @SerializedName("selfDestructKills")
    var selfDestructKills = 0.0

    @SerializedName("selfDestructKillsAvgPer10Min")
    var selfDestructKillsAvgPer10Min = 0f

    @SerializedName("selfDestructKillsMostInGame")
    var selfDestructKillsMostInGame = 0.0

    @SerializedName("abilityDamageDone")
    var abilityDamageDone = 0.0

    @SerializedName("abilityDamageDoneAvgPer10Min")
    var abilityDamageDoneAvgPer10Min = 0.0

    @SerializedName("abilityDamageDoneMostInGame")
    var abilityDamageDoneMostInGame = 0.0

    @SerializedName("meteorStrikeKills")
    var meteorStrikeKills = 0.0

    @SerializedName("meteorStrikeKillsAvgPer10Min")
    var meteorStrikeKillsAvgPer10Min = 0f

    @SerializedName("meteorStrikeKillsMostInGame")
    var meteorStrikeKillsMostInGame = 0.0

    @SerializedName("shieldsCreated")
    var shieldsCreated = 0.0

    @SerializedName("shieldsCreatedAvgPer10Min")
    var shieldsCreatedAvgPer10Min = 0.0

    @SerializedName("shieldsCreatedMostInGame")
    var shieldsCreatedMostInGame = 0.0

    @SerializedName("damageReflected")
    var damageReflected = 0.0

    @SerializedName("damageReflectedAvgPer10Min")
    var damageReflectedAvgPer10Min = 0.0

    @SerializedName("damageReflectedMostInGame")
    var damageReflectedMostInGame = 0.0

    @SerializedName("deflectionKills")
    var deflectionKills = 0.0

    @SerializedName("dragonbladesKills")
    var dragonbladesKills = 0.0

    @SerializedName("dragonbladesKillsAvgPer10Min")
    var dragonbladesKillsAvgPer10Min = 0f

    @SerializedName("dragonbladesKillsMostInGame")
    var dragonbladesKillsMostInGame = 0.0

    @SerializedName("soundBarriersProvided")
    var soundBarriersProvided = 0.0

    @SerializedName("soundBarriersProvidedAvgPer10Min")
    var soundBarriersProvidedAvgPer10Min = 0f

    @SerializedName("soundBarriersProvidedMostInGame")
    var soundBarriersProvidedMostInGame = 0.0

    @SerializedName("deadeyeKills")
    var deadeyeKills = 0.0

    @SerializedName("deadeyeKillsAvgPer10Min")
    var deadeyeKillsAvgPer10Min = 0f

    @SerializedName("deadeyeKillsMostInGame")
    var deadeyeKillsMostInGame = 0.0

    @SerializedName("fanTheHammerKills")
    var fanTheHammerKills = 0.0

    @SerializedName("fanTheHammerKillsAvgPer10Min")
    var fanTheHammerKillsAvgPer10Min = 0f

    @SerializedName("fanTheHammerKillsMostInGame")
    var fanTheHammerKillsMostInGame = 0.0

    @SerializedName("blizzardKills")
    var blizzardKills = 0.0

    @SerializedName("blizzardKillsAvgPer10Min")
    var blizzardKillsAvgPer10Min = 0f

    @SerializedName("blizzardKillsMostInGame")
    var blizzardKillsMostInGame = 0.0

    @SerializedName("enemiesFrozen")
    var enemiesFrozen = 0.0

    @SerializedName("enemiesFrozenAvgPer10Min")
    var enemiesFrozenAvgPer10Min = 0f

    @SerializedName("enemiesFrozenMostInGame")
    var enemiesFrozenMostInGame = 0.0

    @SerializedName("blasterKills")
    var blasterKills = 0.0

    @SerializedName("blasterKillsAvgPer10Min")
    var blasterKillsAvgPer10Min = 0f

    @SerializedName("blasterKillsMostInGame")
    var blasterKillsMostInGame = 0.0

    @SerializedName("playersResurrected")
    var playersResurrected = 0.0

    @SerializedName("playersResurrectedAvgPer10Min")
    var playersResurrectedAvgPer10Min = 0f

    @SerializedName("playersResurrectedMostInGame")
    var playersResurrectedMostInGame = 0.0

    @SerializedName("coalescenceHealing")
    var coalescenceHealing = 0.0

    @SerializedName("coalescenceHealingAvgPer10Min")
    var coalescenceHealingAvgPer10Min = 0.0

    @SerializedName("coalescenceHealingMostInGame")
    var coalescenceHealingMostInGame = 0.0

    @SerializedName("coalescenceKills")
    var coalescenceKills = 0.0

    @SerializedName("coalescenceKillsAvgPer10Min")
    var coalescenceKillsAvgPer10Min = 0f

    @SerializedName("coalescenceKillsMostInGame")
    var coalescenceKillsMostInGame = 0.0

    @SerializedName("secondaryFireAccuracy")
    var secondaryFireAccuracy: String? = null

    @SerializedName("superchargerAssists")
    var superchargerAssists = 0.0

    @SerializedName("superchargerAssistsAvgPer10Min")
    var superchargerAssistsAvgPer10Min = 0f

    @SerializedName("superchargerAssistsMostInGame")
    var superchargerAssistsMostInGame = 0.0

    @SerializedName("barrageKills")
    var barrageKills = 0.0

    @SerializedName("barrageKillsAvgPer10Min")
    var barrageKillsAvgPer10Min = 0f

    @SerializedName("barrageKillsMostInGame")
    var barrageKillsMostInGame = 0.0

    @SerializedName("directHitsAccuracy")
    var directHitsAccuracy: String? = null

    @SerializedName("rocketDirectHits")
    var rocketDirectHits = 0.0

    @SerializedName("rocketDirectHitsAvgPer10Min")
    var rocketDirectHitsAvgPer10Min = 0f

    @SerializedName("rocketDirectHitsMostInGame")
    var rocketDirectHitsMostInGame = 0.0

    @SerializedName("deathsBlossomKills")
    var deathsBlossomKills = 0.0

    @SerializedName("deathsBlossomKillsAvgPer10Min")
    var deathsBlossomKillsAvgPer10Min = 0f

    @SerializedName("deathsBlossomKillsMostInGame")
    var deathsBlossomKillsMostInGame = 0.0

    @SerializedName("chargeKills")
    var chargeKills = 0.0

    @SerializedName("chargeKillsAvgPer10Min")
    var chargeKillsAvgPer10Min = 0f

    @SerializedName("chargeKillsMostInGame")
    var chargeKillsMostInGame = 0.0

    @SerializedName("earthshatterKills")
    var earthshatterKills = 0.0

    @SerializedName("earthshatterKillsAvgPer10Min")
    var earthshatterKillsAvgPer10Min = 0f

    @SerializedName("earthshatterKillsMostInGame")
    var earthshatterKillsMostInGame = 0.0

    @SerializedName("fireStrikeKills")
    var fireStrikeKills = 0.0

    @SerializedName("fireStrikeKillsAvgPer10Min")
    var fireStrikeKillsAvgPer10Min = 0f

    @SerializedName("fireStrikeKillsMostInGame")
    var fireStrikeKillsMostInGame = 0.0

    @SerializedName("rocketHammerMeleeAccuracy")
    var rocketHammerMeleeAccuracy: String? = null

    @SerializedName("enemiesHooked")
    var enemiesHooked = 0.0

    @SerializedName("enemiesHookedAvgPer10Min")
    var enemiesHookedAvgPer10Min = 0f

    @SerializedName("enemiesHookedMostInGame")
    var enemiesHookedMostInGame = 0.0

    @SerializedName("hookAccuracy")
    var hookAccuracy: String? = null

    @SerializedName("hookAccuracyBestInGame")
    var hookAccuracyBestInGame: String? = null

    @SerializedName("hooksAttempted")
    var hooksAttempted = 0.0

    @SerializedName("wholeHogKills")
    var wholeHogKills = 0.0

    @SerializedName("wholeHogKillsAvgPer10Min")
    var wholeHogKillsAvgPer10Min = 0f

    @SerializedName("wholeHogKillsMostInGame")
    var wholeHogKillsMostInGame = 0.0

    @SerializedName("bioticFieldHealingDone")
    var bioticFieldHealingDone = 0.0

    @SerializedName("bioticFieldsDeployed")
    var bioticFieldsDeployed = 0.0

    @SerializedName("helixRocketsKills")
    var helixRocketsKills = 0.0

    @SerializedName("helixRocketsKillsAvgPer10Min")
    var helixRocketsKillsAvgPer10Min = 0f

    @SerializedName("helixRocketsKillsMostInGame")
    var helixRocketsKillsMostInGame = 0.0

    @SerializedName("tacticalVisorKills")
    var tacticalVisorKills = 0.0

    @SerializedName("tacticalVisorKillsAvgPer10Min")
    var tacticalVisorKillsAvgPer10Min = 0f

    @SerializedName("tacticalVisorKillsMostInGame")
    var tacticalVisorKillsMostInGame = 0.0

    @SerializedName("enemiesEmpd")
    var enemiesEmpd = 0.0

    @SerializedName("enemiesEmpdAvgPer10Min")
    var enemiesEmpdAvgPer10Min = 0f

    @SerializedName("enemiesEmpdMostInGame")
    var enemiesEmpdMostInGame = 0.0

    @SerializedName("enemiesHacked")
    var enemiesHacked = 0.0

    @SerializedName("enemiesHackedAvgPer10Min")
    var enemiesHackedAvgPer10Min = 0f

    @SerializedName("enemiesHackedMostInGame")
    var enemiesHackedMostInGame = 0.0

    @SerializedName("playersTeleported")
    var playersTeleported = 0.0

    @SerializedName("playersTeleportedAvgPer10Min")
    var playersTeleportedAvgPer10Min = 0f

    @SerializedName("playersTeleportedMostInGame")
    var playersTeleportedMostInGame = 0.0

    @SerializedName("primaryFireAccuracy")
    var primaryFireAccuracy: String? = null

    @SerializedName("secondaryDirectHitsAvgPer10Min")
    var secondaryDirectHitsAvgPer10Min = 0f

    @SerializedName("sentryTurretsKills")
    var sentryTurretsKills = 0.0

    @SerializedName("sentryTurretsKillsAvgPer10Min")
    var sentryTurretsKillsAvgPer10Min = 0f

    @SerializedName("sentryTurretsKillsMostInGame")
    var sentryTurretsKillsMostInGame = 0.0

    @SerializedName("armorPacksCreated")
    var armorPacksCreated = 0.0

    @SerializedName("armorPacksCreatedAvgPer10Min")
    var armorPacksCreatedAvgPer10Min = 0f

    @SerializedName("armorPacksCreatedMostInGame")
    var armorPacksCreatedMostInGame = 0.0

    @SerializedName("moltenCoreKills")
    var moltenCoreKills = 0.0

    @SerializedName("moltenCoreKillsAvgPer10Min")
    var moltenCoreKillsAvgPer10Min = 0f

    @SerializedName("moltenCoreKillsMostInGame")
    var moltenCoreKillsMostInGame = 0.0

    @SerializedName("overloadKills")
    var overloadKills = 0.0

    @SerializedName("overloadKillsMostInGame")
    var overloadKillsMostInGame = 0.0

    @SerializedName("torbjornKills")
    var torbjornKills = 0.0

    @SerializedName("torbjornKillsAvgPer10Min")
    var torbjornKillsAvgPer10Min = 0f

    @SerializedName("torbjornKillsMostInGame")
    var torbjornKillsMostInGame = 0.0

    @SerializedName("turretsDamageAvgPer10Min")
    var turretsDamageAvgPer10Min = 0.0

    @SerializedName("turretsKills")
    var turretsKills = 0.0

    @SerializedName("turretsKillsAvgPer10Min")
    var turretsKillsAvgPer10Min = 0f

    @SerializedName("turretsKillsMostInGame")
    var turretsKillsMostInGame = 0.0

    @SerializedName("healthRecovered")
    var healthRecovered = 0.0

    @SerializedName("healthRecoveredAvgPer10Min")
    var healthRecoveredAvgPer10Min = 0.0

    @SerializedName("healthRecoveredMostInGame")
    var healthRecoveredMostInGame = 0.0

    @SerializedName("pulseBombsAttached")
    var pulseBombsAttached = 0.0

    @SerializedName("pulseBombsAttachedAvgPer10Min")
    var pulseBombsAttachedAvgPer10Min = 0f

    @SerializedName("pulseBombsAttachedMostInGame")
    var pulseBombsAttachedMostInGame = 0.0

    @SerializedName("pulseBombsKills")
    var pulseBombsKills = 0.0

    @SerializedName("pulseBombsKillsAvgPer10Min")
    var pulseBombsKillsAvgPer10Min = 0f

    @SerializedName("pulseBombsKillsMostInGame")
    var pulseBombsKillsMostInGame = 0.0

    @SerializedName("venomMineKills")
    var venomMineKills = 0.0

    @SerializedName("venomMineKillsAvgPer10Min")
    var venomMineKillsAvgPer10Min = 0f

    @SerializedName("venomMineKillsMostInGame")
    var venomMineKillsMostInGame = 0.0

    @SerializedName("jumpKills")
    var jumpKills = 0.0

    @SerializedName("jumpPackKills")
    var jumpPackKills = 0.0

    @SerializedName("jumpPackKillsAvgPer10Min")
    var jumpPackKillsAvgPer10Min = 0f

    @SerializedName("jumpPackKillsMostInGame")
    var jumpPackKillsMostInGame = 0.0

    @SerializedName("meleeKills")
    var meleeKills = 0.0

    @SerializedName("meleeKillsAvgPer10Min")
    var meleeKillsAvgPer10Min = 0f

    @SerializedName("meleeKillsMostInGame")
    var meleeKillsMostInGame = 0.0

    @SerializedName("playersKnockedBack")
    var playersKnockedBack = 0.0

    @SerializedName("playersKnockedBackAvgPer10Min")
    var playersKnockedBackAvgPer10Min = 0f

    @SerializedName("playersKnockedBackMostInGame")
    var playersKnockedBackMostInGame = 0.0

    @SerializedName("primalRageKills")
    var primalRageKills = 0.0

    @SerializedName("primalRageKillsAvgPer10Min")
    var primalRageKillsAvgPer10Min = 0f

    @SerializedName("primalRageKillsMostInGame")
    var primalRageKillsMostInGame = 0.0

    @SerializedName("primalRageMeleeAccuracy")
    var primalRageMeleeAccuracy: String? = null

    @SerializedName("teslaCannonAccuracy")
    var teslaCannonAccuracy: String? = null

    @SerializedName("weaponKills")
    var weaponKills = 0.0

    @SerializedName("grapplingClawKills")
    var grapplingClawKills = 0.0

    @SerializedName("grapplingClawKillsAvgPer10Min")
    var grapplingClawKillsAvgPer10Min = 0f

    @SerializedName("grapplingClawKillsMostInGame")
    var grapplingClawKillsMostInGame = 0.0

    @SerializedName("minefieldKills")
    var minefieldKills = 0.0

    @SerializedName("minefieldKillsAvgPer10Min")
    var minefieldKillsAvgPer10Min = 0f

    @SerializedName("minefieldKillsMostInGame")
    var minefieldKillsMostInGame = 0.0

    @SerializedName("averageEnergy")
    var averageEnergy: String? = null

    @SerializedName("averageEnergyBestInGame")
    var averageEnergyBestInGame: String? = null

    @SerializedName("gravitonSurgeKills")
    var gravitonSurgeKills = 0.0

    @SerializedName("gravitonSurgeKillsAvgPer10Min")
    var gravitonSurgeKillsAvgPer10Min = 0f

    @SerializedName("gravitonSurgeKillsMostInGame")
    var gravitonSurgeKillsMostInGame = 0.0

    @SerializedName("highEnergyKills")
    var highEnergyKills = 0.0

    @SerializedName("highEnergyKillsAvgPer10Min")
    var highEnergyKillsAvgPer10Min = 0f

    @SerializedName("highEnergyKillsMostInGame")
    var highEnergyKillsMostInGame = 0.0

    @SerializedName("projectedBarriersApplied")
    var projectedBarriersApplied = 0.0

    @SerializedName("projectedBarriersAppliedAvgPer10Min")
    var projectedBarriersAppliedAvgPer10Min = 0f

    @SerializedName("projectedBarriersAppliedMostInGame")
    var projectedBarriersAppliedMostInGame = 0.0

    @SerializedName("transcendenceHealing")
    var transcendenceHealing = 0.0

    @SerializedName("transcendenceHealingBest")
    var transcendenceHealingBest = 0.0

}