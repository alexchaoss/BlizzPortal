package com.BlizzardArmory.model.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Hero specific.
 */
public class HeroSpecific {

    @SerializedName("enemiesTrapped")
    @Expose
    private double enemiesTrapped;
    @SerializedName("enemiesTrappedAvgPer10Min")
    @Expose
    private float enemiesTrappedAvgPer10Min;
    @SerializedName("enemiesTrappedMostInGame")
    @Expose
    private double enemiesTrappedMostInGame;
    @SerializedName("ripTireKills")
    @Expose
    private double ripTireKills;
    @SerializedName("ripTireKillsAvgPer10Min")
    @Expose
    private float ripTireKillsAvgPer10Min;
    @SerializedName("ripTireKillsMostInGame")
    @Expose
    private double ripTireKillsMostInGame;
    @SerializedName("dragonstrikeKills")
    @Expose
    private double dragonstrikeKills;
    @SerializedName("dragonstrikeKillsAvgPer10Min")
    @Expose
    private float dragonstrikeKillsAvgPer10Min;
    @SerializedName("dragonstrikeKillsMostInGame")
    @Expose
    private double dragonstrikeKillsMostInGame;
    @SerializedName("scatterArrowKills")
    @Expose
    private double scatterArrowKills;
    @SerializedName("scatterArrowKillsAvgPer10Min")
    @Expose
    private float scatterArrowKillsAvgPer10Min;
    @SerializedName("scatterArrowKillsMostInGame")
    @Expose
    private double scatterArrowKillsMostInGame;
    @SerializedName("stormArrowKills")
    @Expose
    private double stormArrowKills;
    @SerializedName("stormArrowKillsAvgPer10Min")
    @Expose
    private float stormArrowKillsAvgPer10Min;
    @SerializedName("stormArrowKillsMostInGame")
    @Expose
    private double stormArrowKillsMostInGame;
    @SerializedName("bioticGrenadeKills")
    @Expose
    private double bioticGrenadeKills;
    @SerializedName("enemiesSlept")
    @Expose
    private double enemiesSlept;
    @SerializedName("enemiesSleptAvgPer10Min")
    @Expose
    private float enemiesSleptAvgPer10Min;
    @SerializedName("enemiesSleptMostInGame")
    @Expose
    private double enemiesSleptMostInGame;
    @SerializedName("nanoBoostAssists")
    @Expose
    private double nanoBoostAssists;
    @SerializedName("nanoBoostAssistsAvgPer10Min")
    @Expose
    private float nanoBoostAssistsAvgPer10Min;
    @SerializedName("nanoBoostAssistsMostInGame")
    @Expose
    private double nanoBoostAssistsMostInGame;
    @SerializedName("nanoBoostsApplied")
    @Expose
    private double nanoBoostsApplied;
    @SerializedName("nanoBoostsAppliedAvgPer10Min")
    @Expose
    private float nanoBoostsAppliedAvgPer10Min;
    @SerializedName("nanoBoostsAppliedMostInGame")
    @Expose
    private double nanoBoostsAppliedMostInGame;
    @SerializedName("scopedAccuracy")
    @Expose
    private String scopedAccuracy;
    @SerializedName("scopedAccuracyBestInGame")
    @Expose
    private String scopedAccuracyBestInGame;
    @SerializedName("selfHealing")
    @Expose
    private double selfHealing;
    @SerializedName("selfHealingAvgPer10Min")
    @Expose
    private double selfHealingAvgPer10Min;
    @SerializedName("selfHealingMostInGame")
    @Expose
    private double selfHealingMostInGame;
    @SerializedName("unscopedAccuracy")
    @Expose
    private String unscopedAccuracy;
    @SerializedName("unscopedAccuracyBestInGame")
    @Expose
    private String unscopedAccuracyBestInGame;
    @SerializedName("bobKills")
    @Expose
    private double bobKills;
    @SerializedName("bobKillsAvgPer10Min")
    @Expose
    private float bobKillsAvgPer10Min;
    @SerializedName("bobKillsMostInGame")
    @Expose
    private double bobKillsMostInGame;
    @SerializedName("coachGunKills")
    @Expose
    private double coachGunKills;
    @SerializedName("coachGunKillsAvgPer10Min")
    @Expose
    private float coachGunKillsAvgPer10Min;
    @SerializedName("coachGunKillsMostInGame")
    @Expose
    private double coachGunKillsMostInGame;
    @SerializedName("dynamiteKills")
    @Expose
    private double dynamiteKills;
    @SerializedName("dynamiteKillsAvgPer10Min")
    @Expose
    private float dynamiteKillsAvgPer10Min;
    @SerializedName("dynamiteKillsMostInGame")
    @Expose
    private double dynamiteKillsMostInGame;
    @SerializedName("scopedCriticalHits")
    @Expose
    private double scopedCriticalHits;
    @SerializedName("scopedCriticalHitsAccuracy")
    @Expose
    private String scopedCriticalHitsAccuracy;
    @SerializedName("scopedCriticalHitsAvgPer10Min")
    @Expose
    private float scopedCriticalHitsAvgPer10Min;
    @SerializedName("scopedCriticalHitsMostInGame")
    @Expose
    private double scopedCriticalHitsMostInGame;
    @SerializedName("amplificationMatrixCasts")
    @Expose
    private double amplificationMatrixCasts;
    @SerializedName("amplificationMatrixCastsAvgPer10Min")
    @Expose
    private float amplificationMatrixCastsAvgPer10Min;
    @SerializedName("amplificationMatrixCastsMostInGame")
    @Expose
    private double amplificationMatrixCastsMostInGame;
    @SerializedName("damageAmplified")
    @Expose
    private double damageAmplified;
    @SerializedName("damageAmplifiedAvgPer10Min")
    @Expose
    private double damageAmplifiedAvgPer10Min;
    @SerializedName("damageAmplifiedMostInGame")
    @Expose
    private double damageAmplifiedMostInGame;
    @SerializedName("healingAccuracy")
    @Expose
    private String healingAccuracy;
    @SerializedName("healingAccuracyBestInGame")
    @Expose
    private String healingAccuracyBestInGame;
    @SerializedName("immortalityFieldDeathsPrevented")
    @Expose
    private double immortalityFieldDeathsPrevented;
    @SerializedName("immortalityFieldDeathsPreventedAvgPer10Min")
    @Expose
    private float immortalityFieldDeathsPreventedAvgPer10Min;
    @SerializedName("immortalityFieldDeathsPreventedMostInGame")
    @Expose
    private double immortalityFieldDeathsPreventedMostInGame;
    @SerializedName("reconKills")
    @Expose
    private double reconKills;
    @SerializedName("reconKillsAvgPer10Min")
    @Expose
    private float reconKillsAvgPer10Min;
    @SerializedName("reconKillsMostInGame")
    @Expose
    private double reconKillsMostInGame;
    @SerializedName("sentryKills")
    @Expose
    private double sentryKills;
    @SerializedName("sentryKillsAvgPer10Min")
    @Expose
    private float sentryKillsAvgPer10Min;
    @SerializedName("sentryKillsMostInGame")
    @Expose
    private double sentryKillsMostInGame;
    @SerializedName("tankKills")
    @Expose
    private double tankKills;
    @SerializedName("tankKillsAvgPer10Min")
    @Expose
    private float tankKillsAvgPer10Min;
    @SerializedName("tankKillsMostInGame")
    @Expose
    private double tankKillsMostInGame;
    @SerializedName("armorProvided")
    @Expose
    private double armorProvided;
    @SerializedName("armorProvidedAvgPer10Min")
    @Expose
    private double armorProvidedAvgPer10Min;
    @SerializedName("armorProvidedMostInGame")
    @Expose
    private double armorProvidedMostInGame;
    @SerializedName("damageBlocked")
    @Expose
    private double damageBlocked;
    @SerializedName("damageBlockedAvgPer10Min")
    @Expose
    private double damageBlockedAvgPer10Min;
    @SerializedName("damageBlockedMostInGame")
    @Expose
    private double damageBlockedMostInGame;
    @SerializedName("inspireUptimePercentage")
    @Expose
    private String inspireUptimePercentage;
    @SerializedName("mechDeaths")
    @Expose
    private double mechDeaths;
    @SerializedName("mechsCalled")
    @Expose
    private double mechsCalled;
    @SerializedName("mechsCalledAvgPer10Min")
    @Expose
    private float mechsCalledAvgPer10Min;
    @SerializedName("mechsCalledMostInGame")
    @Expose
    private double mechsCalledMostInGame;
    @SerializedName("selfDestructKills")
    @Expose
    private double selfDestructKills;
    @SerializedName("selfDestructKillsAvgPer10Min")
    @Expose
    private float selfDestructKillsAvgPer10Min;
    @SerializedName("selfDestructKillsMostInGame")
    @Expose
    private double selfDestructKillsMostInGame;
    @SerializedName("abilityDamageDone")
    @Expose
    private double abilityDamageDone;
    @SerializedName("abilityDamageDoneAvgPer10Min")
    @Expose
    private double abilityDamageDoneAvgPer10Min;
    @SerializedName("abilityDamageDoneMostInGame")
    @Expose
    private double abilityDamageDoneMostInGame;
    @SerializedName("meteorStrikeKills")
    @Expose
    private double meteorStrikeKills;
    @SerializedName("meteorStrikeKillsAvgPer10Min")
    @Expose
    private float meteorStrikeKillsAvgPer10Min;
    @SerializedName("meteorStrikeKillsMostInGame")
    @Expose
    private double meteorStrikeKillsMostInGame;
    @SerializedName("shieldsCreated")
    @Expose
    private double shieldsCreated;
    @SerializedName("shieldsCreatedAvgPer10Min")
    @Expose
    private double shieldsCreatedAvgPer10Min;
    @SerializedName("shieldsCreatedMostInGame")
    @Expose
    private double shieldsCreatedMostInGame;
    @SerializedName("damageReflected")
    @Expose
    private double damageReflected;
    @SerializedName("damageReflectedAvgPer10Min")
    @Expose
    private double damageReflectedAvgPer10Min;
    @SerializedName("damageReflectedMostInGame")
    @Expose
    private double damageReflectedMostInGame;
    @SerializedName("deflectionKills")
    @Expose
    private double deflectionKills;
    @SerializedName("dragonbladesKills")
    @Expose
    private double dragonbladesKills;
    @SerializedName("dragonbladesKillsAvgPer10Min")
    @Expose
    private float dragonbladesKillsAvgPer10Min;
    @SerializedName("dragonbladesKillsMostInGame")
    @Expose
    private double dragonbladesKillsMostInGame;
    @SerializedName("soundBarriersProvided")
    @Expose
    private double soundBarriersProvided;
    @SerializedName("soundBarriersProvidedAvgPer10Min")
    @Expose
    private float soundBarriersProvidedAvgPer10Min;
    @SerializedName("soundBarriersProvidedMostInGame")
    @Expose
    private double soundBarriersProvidedMostInGame;
    @SerializedName("deadeyeKills")
    @Expose
    private double deadeyeKills;
    @SerializedName("deadeyeKillsAvgPer10Min")
    @Expose
    private float deadeyeKillsAvgPer10Min;
    @SerializedName("deadeyeKillsMostInGame")
    @Expose
    private double deadeyeKillsMostInGame;
    @SerializedName("fanTheHammerKills")
    @Expose
    private double fanTheHammerKills;
    @SerializedName("fanTheHammerKillsAvgPer10Min")
    @Expose
    private float fanTheHammerKillsAvgPer10Min;
    @SerializedName("fanTheHammerKillsMostInGame")
    @Expose
    private double fanTheHammerKillsMostInGame;
    @SerializedName("blizzardKills")
    @Expose
    private double blizzardKills;
    @SerializedName("blizzardKillsAvgPer10Min")
    @Expose
    private float blizzardKillsAvgPer10Min;
    @SerializedName("blizzardKillsMostInGame")
    @Expose
    private double blizzardKillsMostInGame;
    @SerializedName("enemiesFrozen")
    @Expose
    private double enemiesFrozen;
    @SerializedName("enemiesFrozenAvgPer10Min")
    @Expose
    private float enemiesFrozenAvgPer10Min;
    @SerializedName("enemiesFrozenMostInGame")
    @Expose
    private double enemiesFrozenMostInGame;
    @SerializedName("blasterKills")
    @Expose
    private double blasterKills;
    @SerializedName("blasterKillsAvgPer10Min")
    @Expose
    private float blasterKillsAvgPer10Min;
    @SerializedName("blasterKillsMostInGame")
    @Expose
    private double blasterKillsMostInGame;
    @SerializedName("playersResurrected")
    @Expose
    private double playersResurrected;
    @SerializedName("playersResurrectedAvgPer10Min")
    @Expose
    private float playersResurrectedAvgPer10Min;
    @SerializedName("playersResurrectedMostInGame")
    @Expose
    private double playersResurrectedMostInGame;
    @SerializedName("coalescenceHealing")
    @Expose
    private double coalescenceHealing;
    @SerializedName("coalescenceHealingAvgPer10Min")
    @Expose
    private double coalescenceHealingAvgPer10Min;
    @SerializedName("coalescenceHealingMostInGame")
    @Expose
    private double coalescenceHealingMostInGame;
    @SerializedName("coalescenceKills")
    @Expose
    private double coalescenceKills;
    @SerializedName("coalescenceKillsAvgPer10Min")
    @Expose
    private float coalescenceKillsAvgPer10Min;
    @SerializedName("coalescenceKillsMostInGame")
    @Expose
    private double coalescenceKillsMostInGame;
    @SerializedName("secondaryFireAccuracy")
    @Expose
    private String secondaryFireAccuracy;
    @SerializedName("superchargerAssists")
    @Expose
    private double superchargerAssists;
    @SerializedName("superchargerAssistsAvgPer10Min")
    @Expose
    private float superchargerAssistsAvgPer10Min;
    @SerializedName("superchargerAssistsMostInGame")
    @Expose
    private double superchargerAssistsMostInGame;
    @SerializedName("barrageKills")
    @Expose
    private double barrageKills;
    @SerializedName("barrageKillsAvgPer10Min")
    @Expose
    private float barrageKillsAvgPer10Min;
    @SerializedName("barrageKillsMostInGame")
    @Expose
    private double barrageKillsMostInGame;
    @SerializedName("directHitsAccuracy")
    @Expose
    private String directHitsAccuracy;
    @SerializedName("rocketDirectHits")
    @Expose
    private double rocketDirectHits;
    @SerializedName("rocketDirectHitsAvgPer10Min")
    @Expose
    private float rocketDirectHitsAvgPer10Min;
    @SerializedName("rocketDirectHitsMostInGame")
    @Expose
    private double rocketDirectHitsMostInGame;
    @SerializedName("deathsBlossomKills")
    @Expose
    private double deathsBlossomKills;
    @SerializedName("deathsBlossomKillsAvgPer10Min")
    @Expose
    private float deathsBlossomKillsAvgPer10Min;
    @SerializedName("deathsBlossomKillsMostInGame")
    @Expose
    private double deathsBlossomKillsMostInGame;
    @SerializedName("chargeKills")
    @Expose
    private double chargeKills;
    @SerializedName("chargeKillsAvgPer10Min")
    @Expose
    private float chargeKillsAvgPer10Min;
    @SerializedName("chargeKillsMostInGame")
    @Expose
    private double chargeKillsMostInGame;
    @SerializedName("earthshatterKills")
    @Expose
    private double earthshatterKills;
    @SerializedName("earthshatterKillsAvgPer10Min")
    @Expose
    private float earthshatterKillsAvgPer10Min;
    @SerializedName("earthshatterKillsMostInGame")
    @Expose
    private double earthshatterKillsMostInGame;
    @SerializedName("fireStrikeKills")
    @Expose
    private double fireStrikeKills;
    @SerializedName("fireStrikeKillsAvgPer10Min")
    @Expose
    private float fireStrikeKillsAvgPer10Min;
    @SerializedName("fireStrikeKillsMostInGame")
    @Expose
    private double fireStrikeKillsMostInGame;
    @SerializedName("rocketHammerMeleeAccuracy")
    @Expose
    private String rocketHammerMeleeAccuracy;
    @SerializedName("enemiesHooked")
    @Expose
    private double enemiesHooked;
    @SerializedName("enemiesHookedAvgPer10Min")
    @Expose
    private float enemiesHookedAvgPer10Min;
    @SerializedName("enemiesHookedMostInGame")
    @Expose
    private double enemiesHookedMostInGame;
    @SerializedName("hookAccuracy")
    @Expose
    private String hookAccuracy;
    @SerializedName("hookAccuracyBestInGame")
    @Expose
    private String hookAccuracyBestInGame;
    @SerializedName("hooksAttempted")
    @Expose
    private double hooksAttempted;
    @SerializedName("wholeHogKills")
    @Expose
    private double wholeHogKills;
    @SerializedName("wholeHogKillsAvgPer10Min")
    @Expose
    private float wholeHogKillsAvgPer10Min;
    @SerializedName("wholeHogKillsMostInGame")
    @Expose
    private double wholeHogKillsMostInGame;
    @SerializedName("bioticFieldHealingDone")
    @Expose
    private double bioticFieldHealingDone;
    @SerializedName("bioticFieldsDeployed")
    @Expose
    private double bioticFieldsDeployed;
    @SerializedName("helixRocketsKills")
    @Expose
    private double helixRocketsKills;
    @SerializedName("helixRocketsKillsAvgPer10Min")
    @Expose
    private float helixRocketsKillsAvgPer10Min;
    @SerializedName("helixRocketsKillsMostInGame")
    @Expose
    private double helixRocketsKillsMostInGame;
    @SerializedName("tacticalVisorKills")
    @Expose
    private double tacticalVisorKills;
    @SerializedName("tacticalVisorKillsAvgPer10Min")
    @Expose
    private float tacticalVisorKillsAvgPer10Min;
    @SerializedName("tacticalVisorKillsMostInGame")
    @Expose
    private double tacticalVisorKillsMostInGame;
    @SerializedName("enemiesEmpd")
    @Expose
    private double enemiesEmpd;
    @SerializedName("enemiesEmpdAvgPer10Min")
    @Expose
    private float enemiesEmpdAvgPer10Min;
    @SerializedName("enemiesEmpdMostInGame")
    @Expose
    private double enemiesEmpdMostInGame;
    @SerializedName("enemiesHacked")
    @Expose
    private double enemiesHacked;
    @SerializedName("enemiesHackedAvgPer10Min")
    @Expose
    private float enemiesHackedAvgPer10Min;
    @SerializedName("enemiesHackedMostInGame")
    @Expose
    private double enemiesHackedMostInGame;
    @SerializedName("playersTeleported")
    @Expose
    private double playersTeleported;
    @SerializedName("playersTeleportedAvgPer10Min")
    @Expose
    private float playersTeleportedAvgPer10Min;
    @SerializedName("playersTeleportedMostInGame")
    @Expose
    private double playersTeleportedMostInGame;
    @SerializedName("primaryFireAccuracy")
    @Expose
    private String primaryFireAccuracy;
    @SerializedName("secondaryDirectHitsAvgPer10Min")
    @Expose
    private float secondaryDirectHitsAvgPer10Min;
    @SerializedName("sentryTurretsKills")
    @Expose
    private double sentryTurretsKills;
    @SerializedName("sentryTurretsKillsAvgPer10Min")
    @Expose
    private float sentryTurretsKillsAvgPer10Min;
    @SerializedName("sentryTurretsKillsMostInGame")
    @Expose
    private double sentryTurretsKillsMostInGame;
    @SerializedName("armorPacksCreated")
    @Expose
    private double armorPacksCreated;
    @SerializedName("armorPacksCreatedAvgPer10Min")
    @Expose
    private float armorPacksCreatedAvgPer10Min;
    @SerializedName("armorPacksCreatedMostInGame")
    @Expose
    private double armorPacksCreatedMostInGame;
    @SerializedName("moltenCoreKills")
    @Expose
    private double moltenCoreKills;
    @SerializedName("moltenCoreKillsAvgPer10Min")
    @Expose
    private float moltenCoreKillsAvgPer10Min;
    @SerializedName("moltenCoreKillsMostInGame")
    @Expose
    private double moltenCoreKillsMostInGame;
    @SerializedName("overloadKills")
    @Expose
    private double overloadKills;
    @SerializedName("overloadKillsMostInGame")
    @Expose
    private double overloadKillsMostInGame;
    @SerializedName("torbjornKills")
    @Expose
    private double torbjornKills;
    @SerializedName("torbjornKillsAvgPer10Min")
    @Expose
    private float torbjornKillsAvgPer10Min;
    @SerializedName("torbjornKillsMostInGame")
    @Expose
    private double torbjornKillsMostInGame;
    @SerializedName("turretsDamageAvgPer10Min")
    @Expose
    private double turretsDamageAvgPer10Min;
    @SerializedName("turretsKills")
    @Expose
    private double turretsKills;
    @SerializedName("turretsKillsAvgPer10Min")
    @Expose
    private float turretsKillsAvgPer10Min;
    @SerializedName("turretsKillsMostInGame")
    @Expose
    private double turretsKillsMostInGame;
    @SerializedName("healthRecovered")
    @Expose
    private double healthRecovered;
    @SerializedName("healthRecoveredAvgPer10Min")
    @Expose
    private double healthRecoveredAvgPer10Min;
    @SerializedName("healthRecoveredMostInGame")
    @Expose
    private double healthRecoveredMostInGame;
    @SerializedName("pulseBombsAttached")
    @Expose
    private double pulseBombsAttached;
    @SerializedName("pulseBombsAttachedAvgPer10Min")
    @Expose
    private float pulseBombsAttachedAvgPer10Min;
    @SerializedName("pulseBombsAttachedMostInGame")
    @Expose
    private double pulseBombsAttachedMostInGame;
    @SerializedName("pulseBombsKills")
    @Expose
    private double pulseBombsKills;
    @SerializedName("pulseBombsKillsAvgPer10Min")
    @Expose
    private float pulseBombsKillsAvgPer10Min;
    @SerializedName("pulseBombsKillsMostInGame")
    @Expose
    private double pulseBombsKillsMostInGame;
    @SerializedName("venomMineKills")
    @Expose
    private double venomMineKills;
    @SerializedName("venomMineKillsAvgPer10Min")
    @Expose
    private float venomMineKillsAvgPer10Min;
    @SerializedName("venomMineKillsMostInGame")
    @Expose
    private double venomMineKillsMostInGame;
    @SerializedName("jumpKills")
    @Expose
    private double jumpKills;
    @SerializedName("jumpPackKills")
    @Expose
    private double jumpPackKills;
    @SerializedName("jumpPackKillsAvgPer10Min")
    @Expose
    private float jumpPackKillsAvgPer10Min;
    @SerializedName("jumpPackKillsMostInGame")
    @Expose
    private double jumpPackKillsMostInGame;
    @SerializedName("meleeKills")
    @Expose
    private double meleeKills;
    @SerializedName("meleeKillsAvgPer10Min")
    @Expose
    private float meleeKillsAvgPer10Min;
    @SerializedName("meleeKillsMostInGame")
    @Expose
    private double meleeKillsMostInGame;
    @SerializedName("playersKnockedBack")
    @Expose
    private double playersKnockedBack;
    @SerializedName("playersKnockedBackAvgPer10Min")
    @Expose
    private float playersKnockedBackAvgPer10Min;
    @SerializedName("playersKnockedBackMostInGame")
    @Expose
    private double playersKnockedBackMostInGame;
    @SerializedName("primalRageKills")
    @Expose
    private double primalRageKills;
    @SerializedName("primalRageKillsAvgPer10Min")
    @Expose
    private float primalRageKillsAvgPer10Min;
    @SerializedName("primalRageKillsMostInGame")
    @Expose
    private double primalRageKillsMostInGame;
    @SerializedName("primalRageMeleeAccuracy")
    @Expose
    private String primalRageMeleeAccuracy;
    @SerializedName("teslaCannonAccuracy")
    @Expose
    private String teslaCannonAccuracy;
    @SerializedName("weaponKills")
    @Expose
    private double weaponKills;
    @SerializedName("grapplingClawKills")
    @Expose
    private double grapplingClawKills;
    @SerializedName("grapplingClawKillsAvgPer10Min")
    @Expose
    private float grapplingClawKillsAvgPer10Min;
    @SerializedName("grapplingClawKillsMostInGame")
    @Expose
    private double grapplingClawKillsMostInGame;
    @SerializedName("minefieldKills")
    @Expose
    private double minefieldKills;
    @SerializedName("minefieldKillsAvgPer10Min")
    @Expose
    private float minefieldKillsAvgPer10Min;
    @SerializedName("minefieldKillsMostInGame")
    @Expose
    private double minefieldKillsMostInGame;
    @SerializedName("averageEnergy")
    @Expose
    private String averageEnergy;
    @SerializedName("averageEnergyBestInGame")
    @Expose
    private String averageEnergyBestInGame;
    @SerializedName("gravitonSurgeKills")
    @Expose
    private double gravitonSurgeKills;
    @SerializedName("gravitonSurgeKillsAvgPer10Min")
    @Expose
    private float gravitonSurgeKillsAvgPer10Min;
    @SerializedName("gravitonSurgeKillsMostInGame")
    @Expose
    private double gravitonSurgeKillsMostInGame;
    @SerializedName("highEnergyKills")
    @Expose
    private double highEnergyKills;
    @SerializedName("highEnergyKillsAvgPer10Min")
    @Expose
    private float highEnergyKillsAvgPer10Min;
    @SerializedName("highEnergyKillsMostInGame")
    @Expose
    private double highEnergyKillsMostInGame;
    @SerializedName("projectedBarriersApplied")
    @Expose
    private double projectedBarriersApplied;
    @SerializedName("projectedBarriersAppliedAvgPer10Min")
    @Expose
    private float projectedBarriersAppliedAvgPer10Min;
    @SerializedName("projectedBarriersAppliedMostInGame")
    @Expose
    private double projectedBarriersAppliedMostInGame;
    @SerializedName("transcendenceHealing")
    @Expose
    private double transcendenceHealing;
    @SerializedName("transcendenceHealingBest")
    @Expose
    private double transcendenceHealingBest;

    /**
     * Gets enemies trapped.
     *
     * @return the enemies trapped
     */
    public double getEnemiesTrapped() {
        return enemiesTrapped;
    }

    /**
     * Sets enemies trapped.
     *
     * @param enemiesTrapped the enemies trapped
     */
    public void setEnemiesTrapped(double enemiesTrapped) {
        this.enemiesTrapped = enemiesTrapped;
    }

    /**
     * Gets enemies trapped avg per 10 min.
     *
     * @return the enemies trapped avg per 10 min
     */
    public float getEnemiesTrappedAvgPer10Min() {
        return enemiesTrappedAvgPer10Min;
    }

    /**
     * Sets enemies trapped avg per 10 min.
     *
     * @param enemiesTrappedAvgPer10Min the enemies trapped avg per 10 min
     */
    public void setEnemiesTrappedAvgPer10Min(float enemiesTrappedAvgPer10Min) {
        this.enemiesTrappedAvgPer10Min = enemiesTrappedAvgPer10Min;
    }

    /**
     * Gets enemies trapped most in game.
     *
     * @return the enemies trapped most in game
     */
    public double getEnemiesTrappedMostInGame() {
        return enemiesTrappedMostInGame;
    }

    /**
     * Sets enemies trapped most in game.
     *
     * @param enemiesTrappedMostInGame the enemies trapped most in game
     */
    public void setEnemiesTrappedMostInGame(double enemiesTrappedMostInGame) {
        this.enemiesTrappedMostInGame = enemiesTrappedMostInGame;
    }

    /**
     * Gets rip tire kills.
     *
     * @return the rip tire kills
     */
    public double getRipTireKills() {
        return ripTireKills;
    }

    /**
     * Sets rip tire kills.
     *
     * @param ripTireKills the rip tire kills
     */
    public void setRipTireKills(double ripTireKills) {
        this.ripTireKills = ripTireKills;
    }

    /**
     * Gets rip tire kills avg per 10 min.
     *
     * @return the rip tire kills avg per 10 min
     */
    public float getRipTireKillsAvgPer10Min() {
        return ripTireKillsAvgPer10Min;
    }

    /**
     * Sets rip tire kills avg per 10 min.
     *
     * @param ripTireKillsAvgPer10Min the rip tire kills avg per 10 min
     */
    public void setRipTireKillsAvgPer10Min(float ripTireKillsAvgPer10Min) {
        this.ripTireKillsAvgPer10Min = ripTireKillsAvgPer10Min;
    }

    /**
     * Gets rip tire kills most in game.
     *
     * @return the rip tire kills most in game
     */
    public double getRipTireKillsMostInGame() {
        return ripTireKillsMostInGame;
    }

    /**
     * Sets rip tire kills most in game.
     *
     * @param ripTireKillsMostInGame the rip tire kills most in game
     */
    public void setRipTireKillsMostInGame(double ripTireKillsMostInGame) {
        this.ripTireKillsMostInGame = ripTireKillsMostInGame;
    }

    /**
     * Gets dragonstrike kills.
     *
     * @return the dragonstrike kills
     */
    public double getDragonstrikeKills() {
        return dragonstrikeKills;
    }

    /**
     * Sets dragonstrike kills.
     *
     * @param dragonstrikeKills the dragonstrike kills
     */
    public void setDragonstrikeKills(double dragonstrikeKills) {
        this.dragonstrikeKills = dragonstrikeKills;
    }

    /**
     * Gets dragonstrike kills avg per 10 min.
     *
     * @return the dragonstrike kills avg per 10 min
     */
    public float getDragonstrikeKillsAvgPer10Min() {
        return dragonstrikeKillsAvgPer10Min;
    }

    /**
     * Sets dragonstrike kills avg per 10 min.
     *
     * @param dragonstrikeKillsAvgPer10Min the dragonstrike kills avg per 10 min
     */
    public void setDragonstrikeKillsAvgPer10Min(float dragonstrikeKillsAvgPer10Min) {
        this.dragonstrikeKillsAvgPer10Min = dragonstrikeKillsAvgPer10Min;
    }

    /**
     * Gets dragonstrike kills most in game.
     *
     * @return the dragonstrike kills most in game
     */
    public double getDragonstrikeKillsMostInGame() {
        return dragonstrikeKillsMostInGame;
    }

    /**
     * Sets dragonstrike kills most in game.
     *
     * @param dragonstrikeKillsMostInGame the dragonstrike kills most in game
     */
    public void setDragonstrikeKillsMostInGame(double dragonstrikeKillsMostInGame) {
        this.dragonstrikeKillsMostInGame = dragonstrikeKillsMostInGame;
    }

    /**
     * Gets scatter arrow kills.
     *
     * @return the scatter arrow kills
     */
    public double getScatterArrowKills() {
        return scatterArrowKills;
    }

    /**
     * Sets scatter arrow kills.
     *
     * @param scatterArrowKills the scatter arrow kills
     */
    public void setScatterArrowKills(double scatterArrowKills) {
        this.scatterArrowKills = scatterArrowKills;
    }

    /**
     * Gets scatter arrow kills avg per 10 min.
     *
     * @return the scatter arrow kills avg per 10 min
     */
    public float getScatterArrowKillsAvgPer10Min() {
        return scatterArrowKillsAvgPer10Min;
    }

    /**
     * Sets scatter arrow kills avg per 10 min.
     *
     * @param scatterArrowKillsAvgPer10Min the scatter arrow kills avg per 10 min
     */
    public void setScatterArrowKillsAvgPer10Min(float scatterArrowKillsAvgPer10Min) {
        this.scatterArrowKillsAvgPer10Min = scatterArrowKillsAvgPer10Min;
    }

    /**
     * Gets scatter arrow kills most in game.
     *
     * @return the scatter arrow kills most in game
     */
    public double getScatterArrowKillsMostInGame() {
        return scatterArrowKillsMostInGame;
    }

    /**
     * Sets scatter arrow kills most in game.
     *
     * @param scatterArrowKillsMostInGame the scatter arrow kills most in game
     */
    public void setScatterArrowKillsMostInGame(double scatterArrowKillsMostInGame) {
        this.scatterArrowKillsMostInGame = scatterArrowKillsMostInGame;
    }

    /**
     * Gets storm arrow kills.
     *
     * @return the storm arrow kills
     */
    public double getStormArrowKills() {
        return stormArrowKills;
    }

    /**
     * Sets storm arrow kills.
     *
     * @param stormArrowKills the storm arrow kills
     */
    public void setStormArrowKills(double stormArrowKills) {
        this.stormArrowKills = stormArrowKills;
    }

    /**
     * Gets storm arrow kills avg per 10 min.
     *
     * @return the storm arrow kills avg per 10 min
     */
    public float getStormArrowKillsAvgPer10Min() {
        return stormArrowKillsAvgPer10Min;
    }

    /**
     * Sets storm arrow kills avg per 10 min.
     *
     * @param stormArrowKillsAvgPer10Min the storm arrow kills avg per 10 min
     */
    public void setStormArrowKillsAvgPer10Min(float stormArrowKillsAvgPer10Min) {
        this.stormArrowKillsAvgPer10Min = stormArrowKillsAvgPer10Min;
    }

    /**
     * Gets storm arrow kills most in game.
     *
     * @return the storm arrow kills most in game
     */
    public double getStormArrowKillsMostInGame() {
        return stormArrowKillsMostInGame;
    }

    /**
     * Sets storm arrow kills most in game.
     *
     * @param stormArrowKillsMostInGame the storm arrow kills most in game
     */
    public void setStormArrowKillsMostInGame(double stormArrowKillsMostInGame) {
        this.stormArrowKillsMostInGame = stormArrowKillsMostInGame;
    }

    /**
     * Gets biotic grenade kills.
     *
     * @return the biotic grenade kills
     */
    public double getBioticGrenadeKills() {
        return bioticGrenadeKills;
    }

    /**
     * Sets biotic grenade kills.
     *
     * @param bioticGrenadeKills the biotic grenade kills
     */
    public void setBioticGrenadeKills(double bioticGrenadeKills) {
        this.bioticGrenadeKills = bioticGrenadeKills;
    }

    /**
     * Gets enemies slept.
     *
     * @return the enemies slept
     */
    public double getEnemiesSlept() {
        return enemiesSlept;
    }

    /**
     * Sets enemies slept.
     *
     * @param enemiesSlept the enemies slept
     */
    public void setEnemiesSlept(double enemiesSlept) {
        this.enemiesSlept = enemiesSlept;
    }

    /**
     * Gets enemies slept avg per 10 min.
     *
     * @return the enemies slept avg per 10 min
     */
    public float getEnemiesSleptAvgPer10Min() {
        return enemiesSleptAvgPer10Min;
    }

    /**
     * Sets enemies slept avg per 10 min.
     *
     * @param enemiesSleptAvgPer10Min the enemies slept avg per 10 min
     */
    public void setEnemiesSleptAvgPer10Min(float enemiesSleptAvgPer10Min) {
        this.enemiesSleptAvgPer10Min = enemiesSleptAvgPer10Min;
    }

    /**
     * Gets enemies slept most in game.
     *
     * @return the enemies slept most in game
     */
    public double getEnemiesSleptMostInGame() {
        return enemiesSleptMostInGame;
    }

    /**
     * Sets enemies slept most in game.
     *
     * @param enemiesSleptMostInGame the enemies slept most in game
     */
    public void setEnemiesSleptMostInGame(double enemiesSleptMostInGame) {
        this.enemiesSleptMostInGame = enemiesSleptMostInGame;
    }

    /**
     * Gets nano boost assists.
     *
     * @return the nano boost assists
     */
    public double getNanoBoostAssists() {
        return nanoBoostAssists;
    }

    /**
     * Sets nano boost assists.
     *
     * @param nanoBoostAssists the nano boost assists
     */
    public void setNanoBoostAssists(double nanoBoostAssists) {
        this.nanoBoostAssists = nanoBoostAssists;
    }

    /**
     * Gets nano boost assists avg per 10 min.
     *
     * @return the nano boost assists avg per 10 min
     */
    public float getNanoBoostAssistsAvgPer10Min() {
        return nanoBoostAssistsAvgPer10Min;
    }

    /**
     * Sets nano boost assists avg per 10 min.
     *
     * @param nanoBoostAssistsAvgPer10Min the nano boost assists avg per 10 min
     */
    public void setNanoBoostAssistsAvgPer10Min(float nanoBoostAssistsAvgPer10Min) {
        this.nanoBoostAssistsAvgPer10Min = nanoBoostAssistsAvgPer10Min;
    }

    /**
     * Gets nano boost assists most in game.
     *
     * @return the nano boost assists most in game
     */
    public double getNanoBoostAssistsMostInGame() {
        return nanoBoostAssistsMostInGame;
    }

    /**
     * Sets nano boost assists most in game.
     *
     * @param nanoBoostAssistsMostInGame the nano boost assists most in game
     */
    public void setNanoBoostAssistsMostInGame(double nanoBoostAssistsMostInGame) {
        this.nanoBoostAssistsMostInGame = nanoBoostAssistsMostInGame;
    }

    /**
     * Gets nano boosts applied.
     *
     * @return the nano boosts applied
     */
    public double getNanoBoostsApplied() {
        return nanoBoostsApplied;
    }

    /**
     * Sets nano boosts applied.
     *
     * @param nanoBoostsApplied the nano boosts applied
     */
    public void setNanoBoostsApplied(double nanoBoostsApplied) {
        this.nanoBoostsApplied = nanoBoostsApplied;
    }

    /**
     * Gets nano boosts applied avg per 10 min.
     *
     * @return the nano boosts applied avg per 10 min
     */
    public float getNanoBoostsAppliedAvgPer10Min() {
        return nanoBoostsAppliedAvgPer10Min;
    }

    /**
     * Sets nano boosts applied avg per 10 min.
     *
     * @param nanoBoostsAppliedAvgPer10Min the nano boosts applied avg per 10 min
     */
    public void setNanoBoostsAppliedAvgPer10Min(float nanoBoostsAppliedAvgPer10Min) {
        this.nanoBoostsAppliedAvgPer10Min = nanoBoostsAppliedAvgPer10Min;
    }

    /**
     * Gets nano boosts applied most in game.
     *
     * @return the nano boosts applied most in game
     */
    public double getNanoBoostsAppliedMostInGame() {
        return nanoBoostsAppliedMostInGame;
    }

    /**
     * Sets nano boosts applied most in game.
     *
     * @param nanoBoostsAppliedMostInGame the nano boosts applied most in game
     */
    public void setNanoBoostsAppliedMostInGame(double nanoBoostsAppliedMostInGame) {
        this.nanoBoostsAppliedMostInGame = nanoBoostsAppliedMostInGame;
    }

    /**
     * Gets scoped accuracy.
     *
     * @return the scoped accuracy
     */
    public String getScopedAccuracy() {
        return scopedAccuracy;
    }

    /**
     * Sets scoped accuracy.
     *
     * @param scopedAccuracy the scoped accuracy
     */
    public void setScopedAccuracy(String scopedAccuracy) {
        this.scopedAccuracy = scopedAccuracy;
    }

    /**
     * Gets scoped accuracy best in game.
     *
     * @return the scoped accuracy best in game
     */
    public String getScopedAccuracyBestInGame() {
        return scopedAccuracyBestInGame;
    }

    /**
     * Sets scoped accuracy best in game.
     *
     * @param scopedAccuracyBestInGame the scoped accuracy best in game
     */
    public void setScopedAccuracyBestInGame(String scopedAccuracyBestInGame) {
        this.scopedAccuracyBestInGame = scopedAccuracyBestInGame;
    }

    /**
     * Gets self healing.
     *
     * @return the self healing
     */
    public double getSelfHealing() {
        return selfHealing;
    }

    /**
     * Sets self healing.
     *
     * @param selfHealing the self healing
     */
    public void setSelfHealing(double selfHealing) {
        this.selfHealing = selfHealing;
    }

    /**
     * Gets self healing avg per 10 min.
     *
     * @return the self healing avg per 10 min
     */
    public double getSelfHealingAvgPer10Min() {
        return selfHealingAvgPer10Min;
    }

    /**
     * Sets self healing avg per 10 min.
     *
     * @param selfHealingAvgPer10Min the self healing avg per 10 min
     */
    public void setSelfHealingAvgPer10Min(double selfHealingAvgPer10Min) {
        this.selfHealingAvgPer10Min = selfHealingAvgPer10Min;
    }

    /**
     * Gets self healing most in game.
     *
     * @return the self healing most in game
     */
    public double getSelfHealingMostInGame() {
        return selfHealingMostInGame;
    }

    /**
     * Sets self healing most in game.
     *
     * @param selfHealingMostInGame the self healing most in game
     */
    public void setSelfHealingMostInGame(double selfHealingMostInGame) {
        this.selfHealingMostInGame = selfHealingMostInGame;
    }

    /**
     * Gets unscoped accuracy.
     *
     * @return the unscoped accuracy
     */
    public String getUnscopedAccuracy() {
        return unscopedAccuracy;
    }

    /**
     * Sets unscoped accuracy.
     *
     * @param unscopedAccuracy the unscoped accuracy
     */
    public void setUnscopedAccuracy(String unscopedAccuracy) {
        this.unscopedAccuracy = unscopedAccuracy;
    }

    /**
     * Gets unscoped accuracy best in game.
     *
     * @return the unscoped accuracy best in game
     */
    public String getUnscopedAccuracyBestInGame() {
        return unscopedAccuracyBestInGame;
    }

    /**
     * Sets unscoped accuracy best in game.
     *
     * @param unscopedAccuracyBestInGame the unscoped accuracy best in game
     */
    public void setUnscopedAccuracyBestInGame(String unscopedAccuracyBestInGame) {
        this.unscopedAccuracyBestInGame = unscopedAccuracyBestInGame;
    }

    /**
     * Gets bob kills.
     *
     * @return the bob kills
     */
    public double getBobKills() {
        return bobKills;
    }

    /**
     * Sets bob kills.
     *
     * @param bobKills the bob kills
     */
    public void setBobKills(double bobKills) {
        this.bobKills = bobKills;
    }

    /**
     * Gets bob kills avg per 10 min.
     *
     * @return the bob kills avg per 10 min
     */
    public float getBobKillsAvgPer10Min() {
        return bobKillsAvgPer10Min;
    }

    /**
     * Sets bob kills avg per 10 min.
     *
     * @param bobKillsAvgPer10Min the bob kills avg per 10 min
     */
    public void setBobKillsAvgPer10Min(float bobKillsAvgPer10Min) {
        this.bobKillsAvgPer10Min = bobKillsAvgPer10Min;
    }

    /**
     * Gets bob kills most in game.
     *
     * @return the bob kills most in game
     */
    public double getBobKillsMostInGame() {
        return bobKillsMostInGame;
    }

    /**
     * Sets bob kills most in game.
     *
     * @param bobKillsMostInGame the bob kills most in game
     */
    public void setBobKillsMostInGame(double bobKillsMostInGame) {
        this.bobKillsMostInGame = bobKillsMostInGame;
    }

    /**
     * Gets coach gun kills.
     *
     * @return the coach gun kills
     */
    public double getCoachGunKills() {
        return coachGunKills;
    }

    /**
     * Sets coach gun kills.
     *
     * @param coachGunKills the coach gun kills
     */
    public void setCoachGunKills(double coachGunKills) {
        this.coachGunKills = coachGunKills;
    }

    /**
     * Gets coach gun kills avg per 10 min.
     *
     * @return the coach gun kills avg per 10 min
     */
    public float getCoachGunKillsAvgPer10Min() {
        return coachGunKillsAvgPer10Min;
    }

    /**
     * Sets coach gun kills avg per 10 min.
     *
     * @param coachGunKillsAvgPer10Min the coach gun kills avg per 10 min
     */
    public void setCoachGunKillsAvgPer10Min(float coachGunKillsAvgPer10Min) {
        this.coachGunKillsAvgPer10Min = coachGunKillsAvgPer10Min;
    }

    /**
     * Gets coach gun kills most in game.
     *
     * @return the coach gun kills most in game
     */
    public double getCoachGunKillsMostInGame() {
        return coachGunKillsMostInGame;
    }

    /**
     * Sets coach gun kills most in game.
     *
     * @param coachGunKillsMostInGame the coach gun kills most in game
     */
    public void setCoachGunKillsMostInGame(double coachGunKillsMostInGame) {
        this.coachGunKillsMostInGame = coachGunKillsMostInGame;
    }

    /**
     * Gets dynamite kills.
     *
     * @return the dynamite kills
     */
    public double getDynamiteKills() {
        return dynamiteKills;
    }

    /**
     * Sets dynamite kills.
     *
     * @param dynamiteKills the dynamite kills
     */
    public void setDynamiteKills(double dynamiteKills) {
        this.dynamiteKills = dynamiteKills;
    }

    /**
     * Gets dynamite kills avg per 10 min.
     *
     * @return the dynamite kills avg per 10 min
     */
    public float getDynamiteKillsAvgPer10Min() {
        return dynamiteKillsAvgPer10Min;
    }

    /**
     * Sets dynamite kills avg per 10 min.
     *
     * @param dynamiteKillsAvgPer10Min the dynamite kills avg per 10 min
     */
    public void setDynamiteKillsAvgPer10Min(float dynamiteKillsAvgPer10Min) {
        this.dynamiteKillsAvgPer10Min = dynamiteKillsAvgPer10Min;
    }

    /**
     * Gets dynamite kills most in game.
     *
     * @return the dynamite kills most in game
     */
    public double getDynamiteKillsMostInGame() {
        return dynamiteKillsMostInGame;
    }

    /**
     * Sets dynamite kills most in game.
     *
     * @param dynamiteKillsMostInGame the dynamite kills most in game
     */
    public void setDynamiteKillsMostInGame(double dynamiteKillsMostInGame) {
        this.dynamiteKillsMostInGame = dynamiteKillsMostInGame;
    }

    /**
     * Gets scoped critical hits.
     *
     * @return the scoped critical hits
     */
    public double getScopedCriticalHits() {
        return scopedCriticalHits;
    }

    /**
     * Sets scoped critical hits.
     *
     * @param scopedCriticalHits the scoped critical hits
     */
    public void setScopedCriticalHits(double scopedCriticalHits) {
        this.scopedCriticalHits = scopedCriticalHits;
    }

    /**
     * Gets scoped critical hits accuracy.
     *
     * @return the scoped critical hits accuracy
     */
    public String getScopedCriticalHitsAccuracy() {
        return scopedCriticalHitsAccuracy;
    }

    /**
     * Sets scoped critical hits accuracy.
     *
     * @param scopedCriticalHitsAccuracy the scoped critical hits accuracy
     */
    public void setScopedCriticalHitsAccuracy(String scopedCriticalHitsAccuracy) {
        this.scopedCriticalHitsAccuracy = scopedCriticalHitsAccuracy;
    }

    /**
     * Gets scoped critical hits avg per 10 min.
     *
     * @return the scoped critical hits avg per 10 min
     */
    public float getScopedCriticalHitsAvgPer10Min() {
        return scopedCriticalHitsAvgPer10Min;
    }

    /**
     * Sets scoped critical hits avg per 10 min.
     *
     * @param scopedCriticalHitsAvgPer10Min the scoped critical hits avg per 10 min
     */
    public void setScopedCriticalHitsAvgPer10Min(float scopedCriticalHitsAvgPer10Min) {
        this.scopedCriticalHitsAvgPer10Min = scopedCriticalHitsAvgPer10Min;
    }

    /**
     * Gets scoped critical hits most in game.
     *
     * @return the scoped critical hits most in game
     */
    public double getScopedCriticalHitsMostInGame() {
        return scopedCriticalHitsMostInGame;
    }

    /**
     * Sets scoped critical hits most in game.
     *
     * @param scopedCriticalHitsMostInGame the scoped critical hits most in game
     */
    public void setScopedCriticalHitsMostInGame(double scopedCriticalHitsMostInGame) {
        this.scopedCriticalHitsMostInGame = scopedCriticalHitsMostInGame;
    }

    /**
     * Gets amplification matrix casts.
     *
     * @return the amplification matrix casts
     */
    public double getAmplificationMatrixCasts() {
        return amplificationMatrixCasts;
    }

    /**
     * Sets amplification matrix casts.
     *
     * @param amplificationMatrixCasts the amplification matrix casts
     */
    public void setAmplificationMatrixCasts(double amplificationMatrixCasts) {
        this.amplificationMatrixCasts = amplificationMatrixCasts;
    }

    /**
     * Gets amplification matrix casts avg per 10 min.
     *
     * @return the amplification matrix casts avg per 10 min
     */
    public float getAmplificationMatrixCastsAvgPer10Min() {
        return amplificationMatrixCastsAvgPer10Min;
    }

    /**
     * Sets amplification matrix casts avg per 10 min.
     *
     * @param amplificationMatrixCastsAvgPer10Min the amplification matrix casts avg per 10 min
     */
    public void setAmplificationMatrixCastsAvgPer10Min(float amplificationMatrixCastsAvgPer10Min) {
        this.amplificationMatrixCastsAvgPer10Min = amplificationMatrixCastsAvgPer10Min;
    }

    /**
     * Gets amplification matrix casts most in game.
     *
     * @return the amplification matrix casts most in game
     */
    public double getAmplificationMatrixCastsMostInGame() {
        return amplificationMatrixCastsMostInGame;
    }

    /**
     * Sets amplification matrix casts most in game.
     *
     * @param amplificationMatrixCastsMostInGame the amplification matrix casts most in game
     */
    public void setAmplificationMatrixCastsMostInGame(double amplificationMatrixCastsMostInGame) {
        this.amplificationMatrixCastsMostInGame = amplificationMatrixCastsMostInGame;
    }

    /**
     * Gets damage amplified.
     *
     * @return the damage amplified
     */
    public double getDamageAmplified() {
        return damageAmplified;
    }

    /**
     * Sets damage amplified.
     *
     * @param damageAmplified the damage amplified
     */
    public void setDamageAmplified(double damageAmplified) {
        this.damageAmplified = damageAmplified;
    }

    /**
     * Gets damage amplified avg per 10 min.
     *
     * @return the damage amplified avg per 10 min
     */
    public double getDamageAmplifiedAvgPer10Min() {
        return damageAmplifiedAvgPer10Min;
    }

    /**
     * Sets damage amplified avg per 10 min.
     *
     * @param damageAmplifiedAvgPer10Min the damage amplified avg per 10 min
     */
    public void setDamageAmplifiedAvgPer10Min(double damageAmplifiedAvgPer10Min) {
        this.damageAmplifiedAvgPer10Min = damageAmplifiedAvgPer10Min;
    }

    /**
     * Gets damage amplified most in game.
     *
     * @return the damage amplified most in game
     */
    public double getDamageAmplifiedMostInGame() {
        return damageAmplifiedMostInGame;
    }

    /**
     * Sets damage amplified most in game.
     *
     * @param damageAmplifiedMostInGame the damage amplified most in game
     */
    public void setDamageAmplifiedMostInGame(double damageAmplifiedMostInGame) {
        this.damageAmplifiedMostInGame = damageAmplifiedMostInGame;
    }

    /**
     * Gets healing accuracy.
     *
     * @return the healing accuracy
     */
    public String getHealingAccuracy() {
        return healingAccuracy;
    }

    /**
     * Sets healing accuracy.
     *
     * @param healingAccuracy the healing accuracy
     */
    public void setHealingAccuracy(String healingAccuracy) {
        this.healingAccuracy = healingAccuracy;
    }

    /**
     * Gets healing accuracy best in game.
     *
     * @return the healing accuracy best in game
     */
    public String getHealingAccuracyBestInGame() {
        return healingAccuracyBestInGame;
    }

    /**
     * Sets healing accuracy best in game.
     *
     * @param healingAccuracyBestInGame the healing accuracy best in game
     */
    public void setHealingAccuracyBestInGame(String healingAccuracyBestInGame) {
        this.healingAccuracyBestInGame = healingAccuracyBestInGame;
    }

    /**
     * Gets immortality field deaths prevented.
     *
     * @return the immortality field deaths prevented
     */
    public double getImmortalityFieldDeathsPrevented() {
        return immortalityFieldDeathsPrevented;
    }

    /**
     * Sets immortality field deaths prevented.
     *
     * @param immortalityFieldDeathsPrevented the immortality field deaths prevented
     */
    public void setImmortalityFieldDeathsPrevented(double immortalityFieldDeathsPrevented) {
        this.immortalityFieldDeathsPrevented = immortalityFieldDeathsPrevented;
    }

    /**
     * Gets immortality field deaths prevented avg per 10 min.
     *
     * @return the immortality field deaths prevented avg per 10 min
     */
    public float getImmortalityFieldDeathsPreventedAvgPer10Min() {
        return immortalityFieldDeathsPreventedAvgPer10Min;
    }

    /**
     * Sets immortality field deaths prevented avg per 10 min.
     *
     * @param immortalityFieldDeathsPreventedAvgPer10Min the immortality field deaths prevented avg per 10 min
     */
    public void setImmortalityFieldDeathsPreventedAvgPer10Min(float immortalityFieldDeathsPreventedAvgPer10Min) {
        this.immortalityFieldDeathsPreventedAvgPer10Min = immortalityFieldDeathsPreventedAvgPer10Min;
    }

    /**
     * Gets immortality field deaths prevented most in game.
     *
     * @return the immortality field deaths prevented most in game
     */
    public double getImmortalityFieldDeathsPreventedMostInGame() {
        return immortalityFieldDeathsPreventedMostInGame;
    }

    /**
     * Sets immortality field deaths prevented most in game.
     *
     * @param immortalityFieldDeathsPreventedMostInGame the immortality field deaths prevented most in game
     */
    public void setImmortalityFieldDeathsPreventedMostInGame(double immortalityFieldDeathsPreventedMostInGame) {
        this.immortalityFieldDeathsPreventedMostInGame = immortalityFieldDeathsPreventedMostInGame;
    }

    /**
     * Gets recon kills.
     *
     * @return the recon kills
     */
    public double getReconKills() {
        return reconKills;
    }

    /**
     * Sets recon kills.
     *
     * @param reconKills the recon kills
     */
    public void setReconKills(double reconKills) {
        this.reconKills = reconKills;
    }

    /**
     * Gets recon kills avg per 10 min.
     *
     * @return the recon kills avg per 10 min
     */
    public float getReconKillsAvgPer10Min() {
        return reconKillsAvgPer10Min;
    }

    /**
     * Sets recon kills avg per 10 min.
     *
     * @param reconKillsAvgPer10Min the recon kills avg per 10 min
     */
    public void setReconKillsAvgPer10Min(float reconKillsAvgPer10Min) {
        this.reconKillsAvgPer10Min = reconKillsAvgPer10Min;
    }

    /**
     * Gets recon kills most in game.
     *
     * @return the recon kills most in game
     */
    public double getReconKillsMostInGame() {
        return reconKillsMostInGame;
    }

    /**
     * Sets recon kills most in game.
     *
     * @param reconKillsMostInGame the recon kills most in game
     */
    public void setReconKillsMostInGame(double reconKillsMostInGame) {
        this.reconKillsMostInGame = reconKillsMostInGame;
    }

    /**
     * Gets sentry kills.
     *
     * @return the sentry kills
     */
    public double getSentryKills() {
        return sentryKills;
    }

    /**
     * Sets sentry kills.
     *
     * @param sentryKills the sentry kills
     */
    public void setSentryKills(double sentryKills) {
        this.sentryKills = sentryKills;
    }

    /**
     * Gets sentry kills avg per 10 min.
     *
     * @return the sentry kills avg per 10 min
     */
    public float getSentryKillsAvgPer10Min() {
        return sentryKillsAvgPer10Min;
    }

    /**
     * Sets sentry kills avg per 10 min.
     *
     * @param sentryKillsAvgPer10Min the sentry kills avg per 10 min
     */
    public void setSentryKillsAvgPer10Min(float sentryKillsAvgPer10Min) {
        this.sentryKillsAvgPer10Min = sentryKillsAvgPer10Min;
    }

    /**
     * Gets sentry kills most in game.
     *
     * @return the sentry kills most in game
     */
    public double getSentryKillsMostInGame() {
        return sentryKillsMostInGame;
    }

    /**
     * Sets sentry kills most in game.
     *
     * @param sentryKillsMostInGame the sentry kills most in game
     */
    public void setSentryKillsMostInGame(double sentryKillsMostInGame) {
        this.sentryKillsMostInGame = sentryKillsMostInGame;
    }

    /**
     * Gets tank kills.
     *
     * @return the tank kills
     */
    public double getTankKills() {
        return tankKills;
    }

    /**
     * Sets tank kills.
     *
     * @param tankKills the tank kills
     */
    public void setTankKills(double tankKills) {
        this.tankKills = tankKills;
    }

    /**
     * Gets tank kills avg per 10 min.
     *
     * @return the tank kills avg per 10 min
     */
    public float getTankKillsAvgPer10Min() {
        return tankKillsAvgPer10Min;
    }

    /**
     * Sets tank kills avg per 10 min.
     *
     * @param tankKillsAvgPer10Min the tank kills avg per 10 min
     */
    public void setTankKillsAvgPer10Min(float tankKillsAvgPer10Min) {
        this.tankKillsAvgPer10Min = tankKillsAvgPer10Min;
    }

    /**
     * Gets tank kills most in game.
     *
     * @return the tank kills most in game
     */
    public double getTankKillsMostInGame() {
        return tankKillsMostInGame;
    }

    /**
     * Sets tank kills most in game.
     *
     * @param tankKillsMostInGame the tank kills most in game
     */
    public void setTankKillsMostInGame(double tankKillsMostInGame) {
        this.tankKillsMostInGame = tankKillsMostInGame;
    }

    /**
     * Gets armor provided.
     *
     * @return the armor provided
     */
    public double getArmorProvided() {
        return armorProvided;
    }

    /**
     * Sets armor provided.
     *
     * @param armorProvided the armor provided
     */
    public void setArmorProvided(double armorProvided) {
        this.armorProvided = armorProvided;
    }

    /**
     * Gets armor provided avg per 10 min.
     *
     * @return the armor provided avg per 10 min
     */
    public double getArmorProvidedAvgPer10Min() {
        return armorProvidedAvgPer10Min;
    }

    /**
     * Sets armor provided avg per 10 min.
     *
     * @param armorProvidedAvgPer10Min the armor provided avg per 10 min
     */
    public void setArmorProvidedAvgPer10Min(double armorProvidedAvgPer10Min) {
        this.armorProvidedAvgPer10Min = armorProvidedAvgPer10Min;
    }

    /**
     * Gets armor provided most in game.
     *
     * @return the armor provided most in game
     */
    public double getArmorProvidedMostInGame() {
        return armorProvidedMostInGame;
    }

    /**
     * Sets armor provided most in game.
     *
     * @param armorProvidedMostInGame the armor provided most in game
     */
    public void setArmorProvidedMostInGame(double armorProvidedMostInGame) {
        this.armorProvidedMostInGame = armorProvidedMostInGame;
    }

    /**
     * Gets damage blocked.
     *
     * @return the damage blocked
     */
    public double getDamageBlocked() {
        return damageBlocked;
    }

    /**
     * Sets damage blocked.
     *
     * @param damageBlocked the damage blocked
     */
    public void setDamageBlocked(double damageBlocked) {
        this.damageBlocked = damageBlocked;
    }

    /**
     * Gets damage blocked avg per 10 min.
     *
     * @return the damage blocked avg per 10 min
     */
    public double getDamageBlockedAvgPer10Min() {
        return damageBlockedAvgPer10Min;
    }

    /**
     * Sets damage blocked avg per 10 min.
     *
     * @param damageBlockedAvgPer10Min the damage blocked avg per 10 min
     */
    public void setDamageBlockedAvgPer10Min(double damageBlockedAvgPer10Min) {
        this.damageBlockedAvgPer10Min = damageBlockedAvgPer10Min;
    }

    /**
     * Gets damage blocked most in game.
     *
     * @return the damage blocked most in game
     */
    public double getDamageBlockedMostInGame() {
        return damageBlockedMostInGame;
    }

    /**
     * Sets damage blocked most in game.
     *
     * @param damageBlockedMostInGame the damage blocked most in game
     */
    public void setDamageBlockedMostInGame(double damageBlockedMostInGame) {
        this.damageBlockedMostInGame = damageBlockedMostInGame;
    }

    /**
     * Gets inspire uptime percentage.
     *
     * @return the inspire uptime percentage
     */
    public String getInspireUptimePercentage() {
        return inspireUptimePercentage;
    }

    /**
     * Sets inspire uptime percentage.
     *
     * @param inspireUptimePercentage the inspire uptime percentage
     */
    public void setInspireUptimePercentage(String inspireUptimePercentage) {
        this.inspireUptimePercentage = inspireUptimePercentage;
    }

    /**
     * Gets mech deaths.
     *
     * @return the mech deaths
     */
    public double getMechDeaths() {
        return mechDeaths;
    }

    /**
     * Sets mech deaths.
     *
     * @param mechDeaths the mech deaths
     */
    public void setMechDeaths(double mechDeaths) {
        this.mechDeaths = mechDeaths;
    }

    /**
     * Gets mechs called.
     *
     * @return the mechs called
     */
    public double getMechsCalled() {
        return mechsCalled;
    }

    /**
     * Sets mechs called.
     *
     * @param mechsCalled the mechs called
     */
    public void setMechsCalled(double mechsCalled) {
        this.mechsCalled = mechsCalled;
    }

    /**
     * Gets mechs called avg per 10 min.
     *
     * @return the mechs called avg per 10 min
     */
    public float getMechsCalledAvgPer10Min() {
        return mechsCalledAvgPer10Min;
    }

    /**
     * Sets mechs called avg per 10 min.
     *
     * @param mechsCalledAvgPer10Min the mechs called avg per 10 min
     */
    public void setMechsCalledAvgPer10Min(float mechsCalledAvgPer10Min) {
        this.mechsCalledAvgPer10Min = mechsCalledAvgPer10Min;
    }

    /**
     * Gets mechs called most in game.
     *
     * @return the mechs called most in game
     */
    public double getMechsCalledMostInGame() {
        return mechsCalledMostInGame;
    }

    /**
     * Sets mechs called most in game.
     *
     * @param mechsCalledMostInGame the mechs called most in game
     */
    public void setMechsCalledMostInGame(double mechsCalledMostInGame) {
        this.mechsCalledMostInGame = mechsCalledMostInGame;
    }

    /**
     * Gets self destruct kills.
     *
     * @return the self destruct kills
     */
    public double getSelfDestructKills() {
        return selfDestructKills;
    }

    /**
     * Sets self destruct kills.
     *
     * @param selfDestructKills the self destruct kills
     */
    public void setSelfDestructKills(double selfDestructKills) {
        this.selfDestructKills = selfDestructKills;
    }

    /**
     * Gets self destruct kills avg per 10 min.
     *
     * @return the self destruct kills avg per 10 min
     */
    public float getSelfDestructKillsAvgPer10Min() {
        return selfDestructKillsAvgPer10Min;
    }

    /**
     * Sets self destruct kills avg per 10 min.
     *
     * @param selfDestructKillsAvgPer10Min the self destruct kills avg per 10 min
     */
    public void setSelfDestructKillsAvgPer10Min(float selfDestructKillsAvgPer10Min) {
        this.selfDestructKillsAvgPer10Min = selfDestructKillsAvgPer10Min;
    }

    /**
     * Gets self destruct kills most in game.
     *
     * @return the self destruct kills most in game
     */
    public double getSelfDestructKillsMostInGame() {
        return selfDestructKillsMostInGame;
    }

    /**
     * Sets self destruct kills most in game.
     *
     * @param selfDestructKillsMostInGame the self destruct kills most in game
     */
    public void setSelfDestructKillsMostInGame(double selfDestructKillsMostInGame) {
        this.selfDestructKillsMostInGame = selfDestructKillsMostInGame;
    }

    /**
     * Gets ability damage done.
     *
     * @return the ability damage done
     */
    public double getAbilityDamageDone() {
        return abilityDamageDone;
    }

    /**
     * Sets ability damage done.
     *
     * @param abilityDamageDone the ability damage done
     */
    public void setAbilityDamageDone(double abilityDamageDone) {
        this.abilityDamageDone = abilityDamageDone;
    }

    /**
     * Gets ability damage done avg per 10 min.
     *
     * @return the ability damage done avg per 10 min
     */
    public double getAbilityDamageDoneAvgPer10Min() {
        return abilityDamageDoneAvgPer10Min;
    }

    /**
     * Sets ability damage done avg per 10 min.
     *
     * @param abilityDamageDoneAvgPer10Min the ability damage done avg per 10 min
     */
    public void setAbilityDamageDoneAvgPer10Min(double abilityDamageDoneAvgPer10Min) {
        this.abilityDamageDoneAvgPer10Min = abilityDamageDoneAvgPer10Min;
    }

    /**
     * Gets ability damage done most in game.
     *
     * @return the ability damage done most in game
     */
    public double getAbilityDamageDoneMostInGame() {
        return abilityDamageDoneMostInGame;
    }

    /**
     * Sets ability damage done most in game.
     *
     * @param abilityDamageDoneMostInGame the ability damage done most in game
     */
    public void setAbilityDamageDoneMostInGame(double abilityDamageDoneMostInGame) {
        this.abilityDamageDoneMostInGame = abilityDamageDoneMostInGame;
    }

    /**
     * Gets meteor strike kills.
     *
     * @return the meteor strike kills
     */
    public double getMeteorStrikeKills() {
        return meteorStrikeKills;
    }

    /**
     * Sets meteor strike kills.
     *
     * @param meteorStrikeKills the meteor strike kills
     */
    public void setMeteorStrikeKills(double meteorStrikeKills) {
        this.meteorStrikeKills = meteorStrikeKills;
    }

    /**
     * Gets meteor strike kills avg per 10 min.
     *
     * @return the meteor strike kills avg per 10 min
     */
    public float getMeteorStrikeKillsAvgPer10Min() {
        return meteorStrikeKillsAvgPer10Min;
    }

    /**
     * Sets meteor strike kills avg per 10 min.
     *
     * @param meteorStrikeKillsAvgPer10Min the meteor strike kills avg per 10 min
     */
    public void setMeteorStrikeKillsAvgPer10Min(float meteorStrikeKillsAvgPer10Min) {
        this.meteorStrikeKillsAvgPer10Min = meteorStrikeKillsAvgPer10Min;
    }

    /**
     * Gets meteor strike kills most in game.
     *
     * @return the meteor strike kills most in game
     */
    public double getMeteorStrikeKillsMostInGame() {
        return meteorStrikeKillsMostInGame;
    }

    /**
     * Sets meteor strike kills most in game.
     *
     * @param meteorStrikeKillsMostInGame the meteor strike kills most in game
     */
    public void setMeteorStrikeKillsMostInGame(double meteorStrikeKillsMostInGame) {
        this.meteorStrikeKillsMostInGame = meteorStrikeKillsMostInGame;
    }

    /**
     * Gets shields created.
     *
     * @return the shields created
     */
    public double getShieldsCreated() {
        return shieldsCreated;
    }

    /**
     * Sets shields created.
     *
     * @param shieldsCreated the shields created
     */
    public void setShieldsCreated(double shieldsCreated) {
        this.shieldsCreated = shieldsCreated;
    }

    /**
     * Gets shields created avg per 10 min.
     *
     * @return the shields created avg per 10 min
     */
    public double getShieldsCreatedAvgPer10Min() {
        return shieldsCreatedAvgPer10Min;
    }

    /**
     * Sets shields created avg per 10 min.
     *
     * @param shieldsCreatedAvgPer10Min the shields created avg per 10 min
     */
    public void setShieldsCreatedAvgPer10Min(double shieldsCreatedAvgPer10Min) {
        this.shieldsCreatedAvgPer10Min = shieldsCreatedAvgPer10Min;
    }

    /**
     * Gets shields created most in game.
     *
     * @return the shields created most in game
     */
    public double getShieldsCreatedMostInGame() {
        return shieldsCreatedMostInGame;
    }

    /**
     * Sets shields created most in game.
     *
     * @param shieldsCreatedMostInGame the shields created most in game
     */
    public void setShieldsCreatedMostInGame(double shieldsCreatedMostInGame) {
        this.shieldsCreatedMostInGame = shieldsCreatedMostInGame;
    }

    /**
     * Gets damage reflected.
     *
     * @return the damage reflected
     */
    public double getDamageReflected() {
        return damageReflected;
    }

    /**
     * Sets damage reflected.
     *
     * @param damageReflected the damage reflected
     */
    public void setDamageReflected(double damageReflected) {
        this.damageReflected = damageReflected;
    }

    /**
     * Gets damage reflected avg per 10 min.
     *
     * @return the damage reflected avg per 10 min
     */
    public double getDamageReflectedAvgPer10Min() {
        return damageReflectedAvgPer10Min;
    }

    /**
     * Sets damage reflected avg per 10 min.
     *
     * @param damageReflectedAvgPer10Min the damage reflected avg per 10 min
     */
    public void setDamageReflectedAvgPer10Min(double damageReflectedAvgPer10Min) {
        this.damageReflectedAvgPer10Min = damageReflectedAvgPer10Min;
    }

    /**
     * Gets damage reflected most in game.
     *
     * @return the damage reflected most in game
     */
    public double getDamageReflectedMostInGame() {
        return damageReflectedMostInGame;
    }

    /**
     * Sets damage reflected most in game.
     *
     * @param damageReflectedMostInGame the damage reflected most in game
     */
    public void setDamageReflectedMostInGame(double damageReflectedMostInGame) {
        this.damageReflectedMostInGame = damageReflectedMostInGame;
    }

    /**
     * Gets deflection kills.
     *
     * @return the deflection kills
     */
    public double getDeflectionKills() {
        return deflectionKills;
    }

    /**
     * Sets deflection kills.
     *
     * @param deflectionKills the deflection kills
     */
    public void setDeflectionKills(double deflectionKills) {
        this.deflectionKills = deflectionKills;
    }

    /**
     * Gets dragonblades kills.
     *
     * @return the dragonblades kills
     */
    public double getDragonbladesKills() {
        return dragonbladesKills;
    }

    /**
     * Sets dragonblades kills.
     *
     * @param dragonbladesKills the dragonblades kills
     */
    public void setDragonbladesKills(double dragonbladesKills) {
        this.dragonbladesKills = dragonbladesKills;
    }

    /**
     * Gets dragonblades kills avg per 10 min.
     *
     * @return the dragonblades kills avg per 10 min
     */
    public float getDragonbladesKillsAvgPer10Min() {
        return dragonbladesKillsAvgPer10Min;

    }

    /**
     * Sets dragonblades kills avg per 10 min.
     *
     * @param dragonbladesKillsAvgPer10Min the dragonblades kills avg per 10 min
     */
    public void setDragonbladesKillsAvgPer10Min(float dragonbladesKillsAvgPer10Min) {
        this.dragonbladesKillsAvgPer10Min = dragonbladesKillsAvgPer10Min;
    }

    /**
     * Gets dragonblades kills most in game.
     *
     * @return the dragonblades kills most in game
     */
    public double getDragonbladesKillsMostInGame() {
        return dragonbladesKillsMostInGame;
    }

    /**
     * Sets dragonblades kills most in game.
     *
     * @param dragonbladesKillsMostInGame the dragonblades kills most in game
     */
    public void setDragonbladesKillsMostInGame(double dragonbladesKillsMostInGame) {
        this.dragonbladesKillsMostInGame = dragonbladesKillsMostInGame;
    }

    /**
     * Gets sound barriers provided.
     *
     * @return the sound barriers provided
     */
    public double getSoundBarriersProvided() {
        return soundBarriersProvided;
    }

    /**
     * Sets sound barriers provided.
     *
     * @param soundBarriersProvided the sound barriers provided
     */
    public void setSoundBarriersProvided(double soundBarriersProvided) {
        this.soundBarriersProvided = soundBarriersProvided;
    }

    /**
     * Gets sound barriers provided avg per 10 min.
     *
     * @return the sound barriers provided avg per 10 min
     */
    public float getSoundBarriersProvidedAvgPer10Min() {
        return soundBarriersProvidedAvgPer10Min;
    }

    /**
     * Sets sound barriers provided avg per 10 min.
     *
     * @param soundBarriersProvidedAvgPer10Min the sound barriers provided avg per 10 min
     */
    public void setSoundBarriersProvidedAvgPer10Min(float soundBarriersProvidedAvgPer10Min) {
        this.soundBarriersProvidedAvgPer10Min = soundBarriersProvidedAvgPer10Min;
    }

    /**
     * Gets sound barriers provided most in game.
     *
     * @return the sound barriers provided most in game
     */
    public double getSoundBarriersProvidedMostInGame() {
        return soundBarriersProvidedMostInGame;
    }

    /**
     * Sets sound barriers provided most in game.
     *
     * @param soundBarriersProvidedMostInGame the sound barriers provided most in game
     */
    public void setSoundBarriersProvidedMostInGame(double soundBarriersProvidedMostInGame) {
        this.soundBarriersProvidedMostInGame = soundBarriersProvidedMostInGame;
    }

    /**
     * Gets deadeye kills.
     *
     * @return the deadeye kills
     */
    public double getDeadeyeKills() {
        return deadeyeKills;
    }

    /**
     * Sets deadeye kills.
     *
     * @param deadeyeKills the deadeye kills
     */
    public void setDeadeyeKills(double deadeyeKills) {
        this.deadeyeKills = deadeyeKills;
    }

    /**
     * Gets deadeye kills avg per 10 min.
     *
     * @return the deadeye kills avg per 10 min
     */
    public float getDeadeyeKillsAvgPer10Min() {
        return deadeyeKillsAvgPer10Min;
    }

    /**
     * Sets deadeye kills avg per 10 min.
     *
     * @param deadeyeKillsAvgPer10Min the deadeye kills avg per 10 min
     */
    public void setDeadeyeKillsAvgPer10Min(float deadeyeKillsAvgPer10Min) {
        this.deadeyeKillsAvgPer10Min = deadeyeKillsAvgPer10Min;
    }

    /**
     * Gets deadeye kills most in game.
     *
     * @return the deadeye kills most in game
     */
    public double getDeadeyeKillsMostInGame() {
        return deadeyeKillsMostInGame;
    }

    /**
     * Sets deadeye kills most in game.
     *
     * @param deadeyeKillsMostInGame the deadeye kills most in game
     */
    public void setDeadeyeKillsMostInGame(double deadeyeKillsMostInGame) {
        this.deadeyeKillsMostInGame = deadeyeKillsMostInGame;
    }

    /**
     * Gets fan the hammer kills.
     *
     * @return the fan the hammer kills
     */
    public double getFanTheHammerKills() {
        return fanTheHammerKills;
    }

    /**
     * Sets fan the hammer kills.
     *
     * @param fanTheHammerKills the fan the hammer kills
     */
    public void setFanTheHammerKills(double fanTheHammerKills) {
        this.fanTheHammerKills = fanTheHammerKills;
    }

    /**
     * Gets fan the hammer kills avg per 10 min.
     *
     * @return the fan the hammer kills avg per 10 min
     */
    public float getFanTheHammerKillsAvgPer10Min() {
        return fanTheHammerKillsAvgPer10Min;
    }

    /**
     * Sets fan the hammer kills avg per 10 min.
     *
     * @param fanTheHammerKillsAvgPer10Min the fan the hammer kills avg per 10 min
     */
    public void setFanTheHammerKillsAvgPer10Min(float fanTheHammerKillsAvgPer10Min) {
        this.fanTheHammerKillsAvgPer10Min = fanTheHammerKillsAvgPer10Min;
    }

    /**
     * Gets fan the hammer kills most in game.
     *
     * @return the fan the hammer kills most in game
     */
    public double getFanTheHammerKillsMostInGame() {
        return fanTheHammerKillsMostInGame;
    }

    /**
     * Sets fan the hammer kills most in game.
     *
     * @param fanTheHammerKillsMostInGame the fan the hammer kills most in game
     */
    public void setFanTheHammerKillsMostInGame(double fanTheHammerKillsMostInGame) {
        this.fanTheHammerKillsMostInGame = fanTheHammerKillsMostInGame;
    }

    /**
     * Gets blizzard kills.
     *
     * @return the blizzard kills
     */
    public double getBlizzardKills() {
        return blizzardKills;
    }

    /**
     * Sets blizzard kills.
     *
     * @param blizzardKills the blizzard kills
     */
    public void setBlizzardKills(double blizzardKills) {
        this.blizzardKills = blizzardKills;
    }

    /**
     * Gets blizzard kills avg per 10 min.
     *
     * @return the blizzard kills avg per 10 min
     */
    public float getBlizzardKillsAvgPer10Min() {
        return blizzardKillsAvgPer10Min;
    }

    /**
     * Sets blizzard kills avg per 10 min.
     *
     * @param blizzardKillsAvgPer10Min the blizzard kills avg per 10 min
     */
    public void setBlizzardKillsAvgPer10Min(float blizzardKillsAvgPer10Min) {
        this.blizzardKillsAvgPer10Min = blizzardKillsAvgPer10Min;
    }

    /**
     * Gets blizzard kills most in game.
     *
     * @return the blizzard kills most in game
     */
    public double getBlizzardKillsMostInGame() {
        return blizzardKillsMostInGame;
    }

    /**
     * Sets blizzard kills most in game.
     *
     * @param blizzardKillsMostInGame the blizzard kills most in game
     */
    public void setBlizzardKillsMostInGame(double blizzardKillsMostInGame) {
        this.blizzardKillsMostInGame = blizzardKillsMostInGame;
    }

    /**
     * Gets enemies frozen.
     *
     * @return the enemies frozen
     */
    public double getEnemiesFrozen() {
        return enemiesFrozen;
    }

    /**
     * Sets enemies frozen.
     *
     * @param enemiesFrozen the enemies frozen
     */
    public void setEnemiesFrozen(double enemiesFrozen) {
        this.enemiesFrozen = enemiesFrozen;
    }

    /**
     * Gets enemies frozen avg per 10 min.
     *
     * @return the enemies frozen avg per 10 min
     */
    public float getEnemiesFrozenAvgPer10Min() {
        return enemiesFrozenAvgPer10Min;
    }

    /**
     * Sets enemies frozen avg per 10 min.
     *
     * @param enemiesFrozenAvgPer10Min the enemies frozen avg per 10 min
     */
    public void setEnemiesFrozenAvgPer10Min(float enemiesFrozenAvgPer10Min) {
        this.enemiesFrozenAvgPer10Min = enemiesFrozenAvgPer10Min;
    }

    /**
     * Gets enemies frozen most in game.
     *
     * @return the enemies frozen most in game
     */
    public double getEnemiesFrozenMostInGame() {
        return enemiesFrozenMostInGame;
    }

    /**
     * Sets enemies frozen most in game.
     *
     * @param enemiesFrozenMostInGame the enemies frozen most in game
     */
    public void setEnemiesFrozenMostInGame(double enemiesFrozenMostInGame) {
        this.enemiesFrozenMostInGame = enemiesFrozenMostInGame;
    }

    /**
     * Gets blaster kills.
     *
     * @return the blaster kills
     */
    public double getBlasterKills() {
        return blasterKills;
    }

    /**
     * Sets blaster kills.
     *
     * @param blasterKills the blaster kills
     */
    public void setBlasterKills(double blasterKills) {
        this.blasterKills = blasterKills;
    }

    /**
     * Gets blaster kills avg per 10 min.
     *
     * @return the blaster kills avg per 10 min
     */
    public float getBlasterKillsAvgPer10Min() {
        return blasterKillsAvgPer10Min;
    }

    /**
     * Sets blaster kills avg per 10 min.
     *
     * @param blasterKillsAvgPer10Min the blaster kills avg per 10 min
     */
    public void setBlasterKillsAvgPer10Min(float blasterKillsAvgPer10Min) {
        this.blasterKillsAvgPer10Min = blasterKillsAvgPer10Min;
    }

    /**
     * Gets blaster kills most in game.
     *
     * @return the blaster kills most in game
     */
    public double getBlasterKillsMostInGame() {
        return blasterKillsMostInGame;
    }

    /**
     * Sets blaster kills most in game.
     *
     * @param blasterKillsMostInGame the blaster kills most in game
     */
    public void setBlasterKillsMostInGame(double blasterKillsMostInGame) {
        this.blasterKillsMostInGame = blasterKillsMostInGame;
    }

    /**
     * Gets players resurrected.
     *
     * @return the players resurrected
     */
    public double getPlayersResurrected() {
        return playersResurrected;
    }

    /**
     * Sets players resurrected.
     *
     * @param playersResurrected the players resurrected
     */
    public void setPlayersResurrected(double playersResurrected) {
        this.playersResurrected = playersResurrected;
    }

    /**
     * Gets players resurrected avg per 10 min.
     *
     * @return the players resurrected avg per 10 min
     */
    public float getPlayersResurrectedAvgPer10Min() {
        return playersResurrectedAvgPer10Min;
    }

    /**
     * Sets players resurrected avg per 10 min.
     *
     * @param playersResurrectedAvgPer10Min the players resurrected avg per 10 min
     */
    public void setPlayersResurrectedAvgPer10Min(float playersResurrectedAvgPer10Min) {
        this.playersResurrectedAvgPer10Min = playersResurrectedAvgPer10Min;
    }

    /**
     * Gets players resurrected most in game.
     *
     * @return the players resurrected most in game
     */
    public double getPlayersResurrectedMostInGame() {
        return playersResurrectedMostInGame;
    }

    /**
     * Sets players resurrected most in game.
     *
     * @param playersResurrectedMostInGame the players resurrected most in game
     */
    public void setPlayersResurrectedMostInGame(double playersResurrectedMostInGame) {
        this.playersResurrectedMostInGame = playersResurrectedMostInGame;
    }

    /**
     * Gets coalescence healing.
     *
     * @return the coalescence healing
     */
    public double getCoalescenceHealing() {
        return coalescenceHealing;
    }

    /**
     * Sets coalescence healing.
     *
     * @param coalescenceHealing the coalescence healing
     */
    public void setCoalescenceHealing(double coalescenceHealing) {
        this.coalescenceHealing = coalescenceHealing;
    }

    /**
     * Gets coalescence healing avg per 10 min.
     *
     * @return the coalescence healing avg per 10 min
     */
    public double getCoalescenceHealingAvgPer10Min() {
        return coalescenceHealingAvgPer10Min;
    }

    /**
     * Sets coalescence healing avg per 10 min.
     *
     * @param coalescenceHealingAvgPer10Min the coalescence healing avg per 10 min
     */
    public void setCoalescenceHealingAvgPer10Min(double coalescenceHealingAvgPer10Min) {
        this.coalescenceHealingAvgPer10Min = coalescenceHealingAvgPer10Min;
    }

    /**
     * Gets coalescence healing most in game.
     *
     * @return the coalescence healing most in game
     */
    public double getCoalescenceHealingMostInGame() {
        return coalescenceHealingMostInGame;
    }

    /**
     * Sets coalescence healing most in game.
     *
     * @param coalescenceHealingMostInGame the coalescence healing most in game
     */
    public void setCoalescenceHealingMostInGame(double coalescenceHealingMostInGame) {
        this.coalescenceHealingMostInGame = coalescenceHealingMostInGame;
    }

    /**
     * Gets coalescence kills.
     *
     * @return the coalescence kills
     */
    public double getCoalescenceKills() {
        return coalescenceKills;
    }

    /**
     * Sets coalescence kills.
     *
     * @param coalescenceKills the coalescence kills
     */
    public void setCoalescenceKills(double coalescenceKills) {
        this.coalescenceKills = coalescenceKills;
    }

    /**
     * Gets coalescence kills avg per 10 min.
     *
     * @return the coalescence kills avg per 10 min
     */
    public float getCoalescenceKillsAvgPer10Min() {
        return coalescenceKillsAvgPer10Min;
    }

    /**
     * Sets coalescence kills avg per 10 min.
     *
     * @param coalescenceKillsAvgPer10Min the coalescence kills avg per 10 min
     */
    public void setCoalescenceKillsAvgPer10Min(float coalescenceKillsAvgPer10Min) {
        this.coalescenceKillsAvgPer10Min = coalescenceKillsAvgPer10Min;
    }

    /**
     * Gets coalescence kills most in game.
     *
     * @return the coalescence kills most in game
     */
    public double getCoalescenceKillsMostInGame() {
        return coalescenceKillsMostInGame;
    }

    /**
     * Sets coalescence kills most in game.
     *
     * @param coalescenceKillsMostInGame the coalescence kills most in game
     */
    public void setCoalescenceKillsMostInGame(double coalescenceKillsMostInGame) {
        this.coalescenceKillsMostInGame = coalescenceKillsMostInGame;
    }

    /**
     * Gets secondary fire accuracy.
     *
     * @return the secondary fire accuracy
     */
    public String getSecondaryFireAccuracy() {
        return secondaryFireAccuracy;
    }

    /**
     * Sets secondary fire accuracy.
     *
     * @param secondaryFireAccuracy the secondary fire accuracy
     */
    public void setSecondaryFireAccuracy(String secondaryFireAccuracy) {
        this.secondaryFireAccuracy = secondaryFireAccuracy;
    }

    /**
     * Gets supercharger assists.
     *
     * @return the supercharger assists
     */
    public double getSuperchargerAssists() {
        return superchargerAssists;
    }

    /**
     * Sets supercharger assists.
     *
     * @param superchargerAssists the supercharger assists
     */
    public void setSuperchargerAssists(double superchargerAssists) {
        this.superchargerAssists = superchargerAssists;
    }

    /**
     * Gets supercharger assists avg per 10 min.
     *
     * @return the supercharger assists avg per 10 min
     */
    public float getSuperchargerAssistsAvgPer10Min() {
        return superchargerAssistsAvgPer10Min;
    }

    /**
     * Sets supercharger assists avg per 10 min.
     *
     * @param superchargerAssistsAvgPer10Min the supercharger assists avg per 10 min
     */
    public void setSuperchargerAssistsAvgPer10Min(float superchargerAssistsAvgPer10Min) {
        this.superchargerAssistsAvgPer10Min = superchargerAssistsAvgPer10Min;
    }

    /**
     * Gets supercharger assists most in game.
     *
     * @return the supercharger assists most in game
     */
    public double getSuperchargerAssistsMostInGame() {
        return superchargerAssistsMostInGame;
    }

    /**
     * Sets supercharger assists most in game.
     *
     * @param superchargerAssistsMostInGame the supercharger assists most in game
     */
    public void setSuperchargerAssistsMostInGame(double superchargerAssistsMostInGame) {
        this.superchargerAssistsMostInGame = superchargerAssistsMostInGame;
    }

    /**
     * Gets barrage kills.
     *
     * @return the barrage kills
     */
    public double getBarrageKills() {
        return barrageKills;
    }

    /**
     * Sets barrage kills.
     *
     * @param barrageKills the barrage kills
     */
    public void setBarrageKills(double barrageKills) {
        this.barrageKills = barrageKills;
    }

    /**
     * Gets barrage kills avg per 10 min.
     *
     * @return the barrage kills avg per 10 min
     */
    public float getBarrageKillsAvgPer10Min() {
        return barrageKillsAvgPer10Min;
    }

    /**
     * Sets barrage kills avg per 10 min.
     *
     * @param barrageKillsAvgPer10Min the barrage kills avg per 10 min
     */
    public void setBarrageKillsAvgPer10Min(float barrageKillsAvgPer10Min) {
        this.barrageKillsAvgPer10Min = barrageKillsAvgPer10Min;
    }

    /**
     * Gets barrage kills most in game.
     *
     * @return the barrage kills most in game
     */
    public double getBarrageKillsMostInGame() {
        return barrageKillsMostInGame;
    }

    /**
     * Sets barrage kills most in game.
     *
     * @param barrageKillsMostInGame the barrage kills most in game
     */
    public void setBarrageKillsMostInGame(double barrageKillsMostInGame) {
        this.barrageKillsMostInGame = barrageKillsMostInGame;
    }

    /**
     * Gets direct hits accuracy.
     *
     * @return the direct hits accuracy
     */
    public String getDirectHitsAccuracy() {
        return directHitsAccuracy;
    }

    /**
     * Sets direct hits accuracy.
     *
     * @param directHitsAccuracy the direct hits accuracy
     */
    public void setDirectHitsAccuracy(String directHitsAccuracy) {
        this.directHitsAccuracy = directHitsAccuracy;
    }

    /**
     * Gets rocket direct hits.
     *
     * @return the rocket direct hits
     */
    public double getRocketDirectHits() {
        return rocketDirectHits;
    }

    /**
     * Sets rocket direct hits.
     *
     * @param rocketDirectHits the rocket direct hits
     */
    public void setRocketDirectHits(double rocketDirectHits) {
        this.rocketDirectHits = rocketDirectHits;
    }

    /**
     * Gets rocket direct hits avg per 10 min.
     *
     * @return the rocket direct hits avg per 10 min
     */
    public float getRocketDirectHitsAvgPer10Min() {
        return rocketDirectHitsAvgPer10Min;
    }

    /**
     * Sets rocket direct hits avg per 10 min.
     *
     * @param rocketDirectHitsAvgPer10Min the rocket direct hits avg per 10 min
     */
    public void setRocketDirectHitsAvgPer10Min(float rocketDirectHitsAvgPer10Min) {
        this.rocketDirectHitsAvgPer10Min = rocketDirectHitsAvgPer10Min;
    }

    /**
     * Gets rocket direct hits most in game.
     *
     * @return the rocket direct hits most in game
     */
    public double getRocketDirectHitsMostInGame() {
        return rocketDirectHitsMostInGame;
    }

    /**
     * Sets rocket direct hits most in game.
     *
     * @param rocketDirectHitsMostInGame the rocket direct hits most in game
     */
    public void setRocketDirectHitsMostInGame(double rocketDirectHitsMostInGame) {
        this.rocketDirectHitsMostInGame = rocketDirectHitsMostInGame;
    }

    /**
     * Gets deaths blossom kills.
     *
     * @return the deaths blossom kills
     */
    public double getDeathsBlossomKills() {
        return deathsBlossomKills;
    }

    /**
     * Sets deaths blossom kills.
     *
     * @param deathsBlossomKills the deaths blossom kills
     */
    public void setDeathsBlossomKills(double deathsBlossomKills) {
        this.deathsBlossomKills = deathsBlossomKills;
    }

    /**
     * Gets deaths blossom kills avg per 10 min.
     *
     * @return the deaths blossom kills avg per 10 min
     */
    public float getDeathsBlossomKillsAvgPer10Min() {
        return deathsBlossomKillsAvgPer10Min;
    }

    /**
     * Sets deaths blossom kills avg per 10 min.
     *
     * @param deathsBlossomKillsAvgPer10Min the deaths blossom kills avg per 10 min
     */
    public void setDeathsBlossomKillsAvgPer10Min(float deathsBlossomKillsAvgPer10Min) {
        this.deathsBlossomKillsAvgPer10Min = deathsBlossomKillsAvgPer10Min;
    }

    /**
     * Gets deaths blossom kills most in game.
     *
     * @return the deaths blossom kills most in game
     */
    public double getDeathsBlossomKillsMostInGame() {
        return deathsBlossomKillsMostInGame;
    }

    /**
     * Sets deaths blossom kills most in game.
     *
     * @param deathsBlossomKillsMostInGame the deaths blossom kills most in game
     */
    public void setDeathsBlossomKillsMostInGame(double deathsBlossomKillsMostInGame) {
        this.deathsBlossomKillsMostInGame = deathsBlossomKillsMostInGame;
    }

    /**
     * Gets charge kills.
     *
     * @return the charge kills
     */
    public double getChargeKills() {
        return chargeKills;
    }

    /**
     * Sets charge kills.
     *
     * @param chargeKills the charge kills
     */
    public void setChargeKills(double chargeKills) {
        this.chargeKills = chargeKills;
    }

    /**
     * Gets charge kills avg per 10 min.
     *
     * @return the charge kills avg per 10 min
     */
    public float getChargeKillsAvgPer10Min() {
        return chargeKillsAvgPer10Min;
    }

    /**
     * Sets charge kills avg per 10 min.
     *
     * @param chargeKillsAvgPer10Min the charge kills avg per 10 min
     */
    public void setChargeKillsAvgPer10Min(float chargeKillsAvgPer10Min) {
        this.chargeKillsAvgPer10Min = chargeKillsAvgPer10Min;
    }

    /**
     * Gets charge kills most in game.
     *
     * @return the charge kills most in game
     */
    public double getChargeKillsMostInGame() {
        return chargeKillsMostInGame;
    }

    /**
     * Sets charge kills most in game.
     *
     * @param chargeKillsMostInGame the charge kills most in game
     */
    public void setChargeKillsMostInGame(double chargeKillsMostInGame) {
        this.chargeKillsMostInGame = chargeKillsMostInGame;
    }

    /**
     * Gets earthshatter kills.
     *
     * @return the earthshatter kills
     */
    public double getEarthshatterKills() {
        return earthshatterKills;
    }

    /**
     * Sets earthshatter kills.
     *
     * @param earthshatterKills the earthshatter kills
     */
    public void setEarthshatterKills(double earthshatterKills) {
        this.earthshatterKills = earthshatterKills;
    }

    /**
     * Gets earthshatter kills avg per 10 min.
     *
     * @return the earthshatter kills avg per 10 min
     */
    public float getEarthshatterKillsAvgPer10Min() {
        return earthshatterKillsAvgPer10Min;
    }

    /**
     * Sets earthshatter kills avg per 10 min.
     *
     * @param earthshatterKillsAvgPer10Min the earthshatter kills avg per 10 min
     */
    public void setEarthshatterKillsAvgPer10Min(float earthshatterKillsAvgPer10Min) {
        this.earthshatterKillsAvgPer10Min = earthshatterKillsAvgPer10Min;
    }

    /**
     * Gets earthshatter kills most in game.
     *
     * @return the earthshatter kills most in game
     */
    public double getEarthshatterKillsMostInGame() {
        return earthshatterKillsMostInGame;
    }

    /**
     * Sets earthshatter kills most in game.
     *
     * @param earthshatterKillsMostInGame the earthshatter kills most in game
     */
    public void setEarthshatterKillsMostInGame(double earthshatterKillsMostInGame) {
        this.earthshatterKillsMostInGame = earthshatterKillsMostInGame;
    }

    /**
     * Gets fire strike kills.
     *
     * @return the fire strike kills
     */
    public double getFireStrikeKills() {
        return fireStrikeKills;
    }

    /**
     * Sets fire strike kills.
     *
     * @param fireStrikeKills the fire strike kills
     */
    public void setFireStrikeKills(double fireStrikeKills) {
        this.fireStrikeKills = fireStrikeKills;
    }

    /**
     * Gets fire strike kills avg per 10 min.
     *
     * @return the fire strike kills avg per 10 min
     */
    public float getFireStrikeKillsAvgPer10Min() {
        return fireStrikeKillsAvgPer10Min;
    }

    /**
     * Sets fire strike kills avg per 10 min.
     *
     * @param fireStrikeKillsAvgPer10Min the fire strike kills avg per 10 min
     */
    public void setFireStrikeKillsAvgPer10Min(float fireStrikeKillsAvgPer10Min) {
        this.fireStrikeKillsAvgPer10Min = fireStrikeKillsAvgPer10Min;
    }

    /**
     * Gets fire strike kills most in game.
     *
     * @return the fire strike kills most in game
     */
    public double getFireStrikeKillsMostInGame() {
        return fireStrikeKillsMostInGame;
    }

    /**
     * Sets fire strike kills most in game.
     *
     * @param fireStrikeKillsMostInGame the fire strike kills most in game
     */
    public void setFireStrikeKillsMostInGame(double fireStrikeKillsMostInGame) {
        this.fireStrikeKillsMostInGame = fireStrikeKillsMostInGame;
    }

    /**
     * Gets rocket hammer melee accuracy.
     *
     * @return the rocket hammer melee accuracy
     */
    public String getRocketHammerMeleeAccuracy() {
        return rocketHammerMeleeAccuracy;
    }

    /**
     * Sets rocket hammer melee accuracy.
     *
     * @param rocketHammerMeleeAccuracy the rocket hammer melee accuracy
     */
    public void setRocketHammerMeleeAccuracy(String rocketHammerMeleeAccuracy) {
        this.rocketHammerMeleeAccuracy = rocketHammerMeleeAccuracy;
    }

    /**
     * Gets enemies hooked.
     *
     * @return the enemies hooked
     */
    public double getEnemiesHooked() {
        return enemiesHooked;
    }

    /**
     * Sets enemies hooked.
     *
     * @param enemiesHooked the enemies hooked
     */
    public void setEnemiesHooked(double enemiesHooked) {
        this.enemiesHooked = enemiesHooked;
    }

    /**
     * Gets enemies hooked avg per 10 min.
     *
     * @return the enemies hooked avg per 10 min
     */
    public float getEnemiesHookedAvgPer10Min() {
        return enemiesHookedAvgPer10Min;
    }

    /**
     * Sets enemies hooked avg per 10 min.
     *
     * @param enemiesHookedAvgPer10Min the enemies hooked avg per 10 min
     */
    public void setEnemiesHookedAvgPer10Min(float enemiesHookedAvgPer10Min) {
        this.enemiesHookedAvgPer10Min = enemiesHookedAvgPer10Min;
    }

    /**
     * Gets enemies hooked most in game.
     *
     * @return the enemies hooked most in game
     */
    public double getEnemiesHookedMostInGame() {
        return enemiesHookedMostInGame;
    }

    /**
     * Sets enemies hooked most in game.
     *
     * @param enemiesHookedMostInGame the enemies hooked most in game
     */
    public void setEnemiesHookedMostInGame(double enemiesHookedMostInGame) {
        this.enemiesHookedMostInGame = enemiesHookedMostInGame;
    }

    /**
     * Gets hook accuracy.
     *
     * @return the hook accuracy
     */
    public String getHookAccuracy() {
        return hookAccuracy;
    }

    /**
     * Sets hook accuracy.
     *
     * @param hookAccuracy the hook accuracy
     */
    public void setHookAccuracy(String hookAccuracy) {
        this.hookAccuracy = hookAccuracy;
    }

    /**
     * Gets hook accuracy best in game.
     *
     * @return the hook accuracy best in game
     */
    public String getHookAccuracyBestInGame() {
        return hookAccuracyBestInGame;
    }

    /**
     * Sets hook accuracy best in game.
     *
     * @param hookAccuracyBestInGame the hook accuracy best in game
     */
    public void setHookAccuracyBestInGame(String hookAccuracyBestInGame) {
        this.hookAccuracyBestInGame = hookAccuracyBestInGame;
    }

    /**
     * Gets hooks attempted.
     *
     * @return the hooks attempted
     */
    public double getHooksAttempted() {
        return hooksAttempted;
    }

    /**
     * Sets hooks attempted.
     *
     * @param hooksAttempted the hooks attempted
     */
    public void setHooksAttempted(double hooksAttempted) {
        this.hooksAttempted = hooksAttempted;
    }

    /**
     * Gets whole hog kills.
     *
     * @return the whole hog kills
     */
    public double getWholeHogKills() {
        return wholeHogKills;
    }

    /**
     * Sets whole hog kills.
     *
     * @param wholeHogKills the whole hog kills
     */
    public void setWholeHogKills(double wholeHogKills) {
        this.wholeHogKills = wholeHogKills;
    }

    /**
     * Gets whole hog kills avg per 10 min.
     *
     * @return the whole hog kills avg per 10 min
     */
    public float getWholeHogKillsAvgPer10Min() {
        return wholeHogKillsAvgPer10Min;
    }

    /**
     * Sets whole hog kills avg per 10 min.
     *
     * @param wholeHogKillsAvgPer10Min the whole hog kills avg per 10 min
     */
    public void setWholeHogKillsAvgPer10Min(float wholeHogKillsAvgPer10Min) {
        this.wholeHogKillsAvgPer10Min = wholeHogKillsAvgPer10Min;
    }

    /**
     * Gets whole hog kills most in game.
     *
     * @return the whole hog kills most in game
     */
    public double getWholeHogKillsMostInGame() {
        return wholeHogKillsMostInGame;
    }

    /**
     * Sets whole hog kills most in game.
     *
     * @param wholeHogKillsMostInGame the whole hog kills most in game
     */
    public void setWholeHogKillsMostInGame(double wholeHogKillsMostInGame) {
        this.wholeHogKillsMostInGame = wholeHogKillsMostInGame;
    }

    /**
     * Gets biotic field healing done.
     *
     * @return the biotic field healing done
     */
    public double getBioticFieldHealingDone() {
        return bioticFieldHealingDone;
    }

    /**
     * Sets biotic field healing done.
     *
     * @param bioticFieldHealingDone the biotic field healing done
     */
    public void setBioticFieldHealingDone(double bioticFieldHealingDone) {
        this.bioticFieldHealingDone = bioticFieldHealingDone;
    }

    /**
     * Gets biotic fields deployed.
     *
     * @return the biotic fields deployed
     */
    public double getBioticFieldsDeployed() {
        return bioticFieldsDeployed;
    }

    /**
     * Sets biotic fields deployed.
     *
     * @param bioticFieldsDeployed the biotic fields deployed
     */
    public void setBioticFieldsDeployed(double bioticFieldsDeployed) {
        this.bioticFieldsDeployed = bioticFieldsDeployed;
    }

    /**
     * Gets helix rockets kills.
     *
     * @return the helix rockets kills
     */
    public double getHelixRocketsKills() {
        return helixRocketsKills;
    }

    /**
     * Sets helix rockets kills.
     *
     * @param helixRocketsKills the helix rockets kills
     */
    public void setHelixRocketsKills(double helixRocketsKills) {
        this.helixRocketsKills = helixRocketsKills;
    }

    /**
     * Gets helix rockets kills avg per 10 min.
     *
     * @return the helix rockets kills avg per 10 min
     */
    public float getHelixRocketsKillsAvgPer10Min() {
        return helixRocketsKillsAvgPer10Min;
    }

    /**
     * Sets helix rockets kills avg per 10 min.
     *
     * @param helixRocketsKillsAvgPer10Min the helix rockets kills avg per 10 min
     */
    public void setHelixRocketsKillsAvgPer10Min(float helixRocketsKillsAvgPer10Min) {
        this.helixRocketsKillsAvgPer10Min = helixRocketsKillsAvgPer10Min;
    }

    /**
     * Gets helix rockets kills most in game.
     *
     * @return the helix rockets kills most in game
     */
    public double getHelixRocketsKillsMostInGame() {
        return helixRocketsKillsMostInGame;
    }

    /**
     * Sets helix rockets kills most in game.
     *
     * @param helixRocketsKillsMostInGame the helix rockets kills most in game
     */
    public void setHelixRocketsKillsMostInGame(double helixRocketsKillsMostInGame) {
        this.helixRocketsKillsMostInGame = helixRocketsKillsMostInGame;
    }

    /**
     * Gets tactical visor kills.
     *
     * @return the tactical visor kills
     */
    public double getTacticalVisorKills() {
        return tacticalVisorKills;
    }

    /**
     * Sets tactical visor kills.
     *
     * @param tacticalVisorKills the tactical visor kills
     */
    public void setTacticalVisorKills(double tacticalVisorKills) {
        this.tacticalVisorKills = tacticalVisorKills;
    }

    /**
     * Gets tactical visor kills avg per 10 min.
     *
     * @return the tactical visor kills avg per 10 min
     */
    public float getTacticalVisorKillsAvgPer10Min() {
        return tacticalVisorKillsAvgPer10Min;
    }

    /**
     * Sets tactical visor kills avg per 10 min.
     *
     * @param tacticalVisorKillsAvgPer10Min the tactical visor kills avg per 10 min
     */
    public void setTacticalVisorKillsAvgPer10Min(float tacticalVisorKillsAvgPer10Min) {
        this.tacticalVisorKillsAvgPer10Min = tacticalVisorKillsAvgPer10Min;
    }

    /**
     * Gets tactical visor kills most in game.
     *
     * @return the tactical visor kills most in game
     */
    public double getTacticalVisorKillsMostInGame() {
        return tacticalVisorKillsMostInGame;
    }

    /**
     * Sets tactical visor kills most in game.
     *
     * @param tacticalVisorKillsMostInGame the tactical visor kills most in game
     */
    public void setTacticalVisorKillsMostInGame(double tacticalVisorKillsMostInGame) {
        this.tacticalVisorKillsMostInGame = tacticalVisorKillsMostInGame;
    }

    /**
     * Gets enemies empd.
     *
     * @return the enemies empd
     */
    public double getEnemiesEmpd() {
        return enemiesEmpd;
    }

    /**
     * Sets enemies empd.
     *
     * @param enemiesEmpd the enemies empd
     */
    public void setEnemiesEmpd(double enemiesEmpd) {
        this.enemiesEmpd = enemiesEmpd;
    }

    /**
     * Gets enemies empd avg per 10 min.
     *
     * @return the enemies empd avg per 10 min
     */
    public float getEnemiesEmpdAvgPer10Min() {
        return enemiesEmpdAvgPer10Min;
    }

    /**
     * Sets enemies empd avg per 10 min.
     *
     * @param enemiesEmpdAvgPer10Min the enemies empd avg per 10 min
     */
    public void setEnemiesEmpdAvgPer10Min(float enemiesEmpdAvgPer10Min) {
        this.enemiesEmpdAvgPer10Min = enemiesEmpdAvgPer10Min;
    }

    /**
     * Gets enemies empd most in game.
     *
     * @return the enemies empd most in game
     */
    public double getEnemiesEmpdMostInGame() {
        return enemiesEmpdMostInGame;
    }

    /**
     * Sets enemies empd most in game.
     *
     * @param enemiesEmpdMostInGame the enemies empd most in game
     */
    public void setEnemiesEmpdMostInGame(double enemiesEmpdMostInGame) {
        this.enemiesEmpdMostInGame = enemiesEmpdMostInGame;
    }

    /**
     * Gets enemies hacked.
     *
     * @return the enemies hacked
     */
    public double getEnemiesHacked() {
        return enemiesHacked;
    }

    /**
     * Sets enemies hacked.
     *
     * @param enemiesHacked the enemies hacked
     */
    public void setEnemiesHacked(double enemiesHacked) {
        this.enemiesHacked = enemiesHacked;
    }

    /**
     * Gets enemies hacked avg per 10 min.
     *
     * @return the enemies hacked avg per 10 min
     */
    public float getEnemiesHackedAvgPer10Min() {
        return enemiesHackedAvgPer10Min;
    }

    /**
     * Sets enemies hacked avg per 10 min.
     *
     * @param enemiesHackedAvgPer10Min the enemies hacked avg per 10 min
     */
    public void setEnemiesHackedAvgPer10Min(float enemiesHackedAvgPer10Min) {
        this.enemiesHackedAvgPer10Min = enemiesHackedAvgPer10Min;
    }

    /**
     * Gets enemies hacked most in game.
     *
     * @return the enemies hacked most in game
     */
    public double getEnemiesHackedMostInGame() {
        return enemiesHackedMostInGame;
    }

    /**
     * Sets enemies hacked most in game.
     *
     * @param enemiesHackedMostInGame the enemies hacked most in game
     */
    public void setEnemiesHackedMostInGame(double enemiesHackedMostInGame) {
        this.enemiesHackedMostInGame = enemiesHackedMostInGame;
    }

    /**
     * Gets players teleported.
     *
     * @return the players teleported
     */
    public double getPlayersTeleported() {
        return playersTeleported;
    }

    /**
     * Sets players teleported.
     *
     * @param playersTeleported the players teleported
     */
    public void setPlayersTeleported(double playersTeleported) {
        this.playersTeleported = playersTeleported;
    }

    /**
     * Gets players teleported avg per 10 min.
     *
     * @return the players teleported avg per 10 min
     */
    public float getPlayersTeleportedAvgPer10Min() {
        return playersTeleportedAvgPer10Min;
    }

    /**
     * Sets players teleported avg per 10 min.
     *
     * @param playersTeleportedAvgPer10Min the players teleported avg per 10 min
     */
    public void setPlayersTeleportedAvgPer10Min(float playersTeleportedAvgPer10Min) {
        this.playersTeleportedAvgPer10Min = playersTeleportedAvgPer10Min;
    }

    /**
     * Gets players teleported most in game.
     *
     * @return the players teleported most in game
     */
    public double getPlayersTeleportedMostInGame() {
        return playersTeleportedMostInGame;
    }

    /**
     * Sets players teleported most in game.
     *
     * @param playersTeleportedMostInGame the players teleported most in game
     */
    public void setPlayersTeleportedMostInGame(double playersTeleportedMostInGame) {
        this.playersTeleportedMostInGame = playersTeleportedMostInGame;
    }

    /**
     * Gets primary fire accuracy.
     *
     * @return the primary fire accuracy
     */
    public String getPrimaryFireAccuracy() {
        return primaryFireAccuracy;
    }

    /**
     * Sets primary fire accuracy.
     *
     * @param primaryFireAccuracy the primary fire accuracy
     */
    public void setPrimaryFireAccuracy(String primaryFireAccuracy) {
        this.primaryFireAccuracy = primaryFireAccuracy;
    }

    /**
     * Gets secondary direct hits avg per 10 min.
     *
     * @return the secondary direct hits avg per 10 min
     */
    public float getSecondaryDirectHitsAvgPer10Min() {
        return secondaryDirectHitsAvgPer10Min;
    }

    /**
     * Sets secondary direct hits avg per 10 min.
     *
     * @param secondaryDirectHitsAvgPer10Min the secondary direct hits avg per 10 min
     */
    public void setSecondaryDirectHitsAvgPer10Min(float secondaryDirectHitsAvgPer10Min) {
        this.secondaryDirectHitsAvgPer10Min = secondaryDirectHitsAvgPer10Min;
    }

    /**
     * Gets sentry turrets kills.
     *
     * @return the sentry turrets kills
     */
    public double getSentryTurretsKills() {
        return sentryTurretsKills;
    }

    /**
     * Sets sentry turrets kills.
     *
     * @param sentryTurretsKills the sentry turrets kills
     */
    public void setSentryTurretsKills(double sentryTurretsKills) {
        this.sentryTurretsKills = sentryTurretsKills;
    }

    /**
     * Gets sentry turrets kills avg per 10 min.
     *
     * @return the sentry turrets kills avg per 10 min
     */
    public float getSentryTurretsKillsAvgPer10Min() {
        return sentryTurretsKillsAvgPer10Min;
    }

    /**
     * Sets sentry turrets kills avg per 10 min.
     *
     * @param sentryTurretsKillsAvgPer10Min the sentry turrets kills avg per 10 min
     */
    public void setSentryTurretsKillsAvgPer10Min(float sentryTurretsKillsAvgPer10Min) {
        this.sentryTurretsKillsAvgPer10Min = sentryTurretsKillsAvgPer10Min;
    }

    /**
     * Gets sentry turrets kills most in game.
     *
     * @return the sentry turrets kills most in game
     */
    public double getSentryTurretsKillsMostInGame() {
        return sentryTurretsKillsMostInGame;
    }

    /**
     * Sets sentry turrets kills most in game.
     *
     * @param sentryTurretsKillsMostInGame the sentry turrets kills most in game
     */
    public void setSentryTurretsKillsMostInGame(double sentryTurretsKillsMostInGame) {
        this.sentryTurretsKillsMostInGame = sentryTurretsKillsMostInGame;
    }

    /**
     * Gets armor packs created.
     *
     * @return the armor packs created
     */
    public double getArmorPacksCreated() {
        return armorPacksCreated;
    }

    /**
     * Sets armor packs created.
     *
     * @param armorPacksCreated the armor packs created
     */
    public void setArmorPacksCreated(double armorPacksCreated) {
        this.armorPacksCreated = armorPacksCreated;
    }

    /**
     * Gets armor packs created avg per 10 min.
     *
     * @return the armor packs created avg per 10 min
     */
    public float getArmorPacksCreatedAvgPer10Min() {
        return armorPacksCreatedAvgPer10Min;
    }

    /**
     * Sets armor packs created avg per 10 min.
     *
     * @param armorPacksCreatedAvgPer10Min the armor packs created avg per 10 min
     */
    public void setArmorPacksCreatedAvgPer10Min(float armorPacksCreatedAvgPer10Min) {
        this.armorPacksCreatedAvgPer10Min = armorPacksCreatedAvgPer10Min;
    }

    /**
     * Gets armor packs created most in game.
     *
     * @return the armor packs created most in game
     */
    public double getArmorPacksCreatedMostInGame() {
        return armorPacksCreatedMostInGame;
    }

    /**
     * Sets armor packs created most in game.
     *
     * @param armorPacksCreatedMostInGame the armor packs created most in game
     */
    public void setArmorPacksCreatedMostInGame(double armorPacksCreatedMostInGame) {
        this.armorPacksCreatedMostInGame = armorPacksCreatedMostInGame;
    }

    /**
     * Gets molten core kills.
     *
     * @return the molten core kills
     */
    public double getMoltenCoreKills() {
        return moltenCoreKills;
    }

    /**
     * Sets molten core kills.
     *
     * @param moltenCoreKills the molten core kills
     */
    public void setMoltenCoreKills(double moltenCoreKills) {
        this.moltenCoreKills = moltenCoreKills;
    }

    /**
     * Gets molten core kills avg per 10 min.
     *
     * @return the molten core kills avg per 10 min
     */
    public float getMoltenCoreKillsAvgPer10Min() {
        return moltenCoreKillsAvgPer10Min;
    }

    /**
     * Sets molten core kills avg per 10 min.
     *
     * @param moltenCoreKillsAvgPer10Min the molten core kills avg per 10 min
     */
    public void setMoltenCoreKillsAvgPer10Min(float moltenCoreKillsAvgPer10Min) {
        this.moltenCoreKillsAvgPer10Min = moltenCoreKillsAvgPer10Min;
    }

    /**
     * Gets molten core kills most in game.
     *
     * @return the molten core kills most in game
     */
    public double getMoltenCoreKillsMostInGame() {
        return moltenCoreKillsMostInGame;
    }

    /**
     * Sets molten core kills most in game.
     *
     * @param moltenCoreKillsMostInGame the molten core kills most in game
     */
    public void setMoltenCoreKillsMostInGame(double moltenCoreKillsMostInGame) {
        this.moltenCoreKillsMostInGame = moltenCoreKillsMostInGame;
    }

    /**
     * Gets overload kills.
     *
     * @return the overload kills
     */
    public double getOverloadKills() {
        return overloadKills;
    }

    /**
     * Sets overload kills.
     *
     * @param overloadKills the overload kills
     */
    public void setOverloadKills(double overloadKills) {
        this.overloadKills = overloadKills;
    }

    /**
     * Gets overload kills most in game.
     *
     * @return the overload kills most in game
     */
    public double getOverloadKillsMostInGame() {
        return overloadKillsMostInGame;
    }

    /**
     * Sets overload kills most in game.
     *
     * @param overloadKillsMostInGame the overload kills most in game
     */
    public void setOverloadKillsMostInGame(double overloadKillsMostInGame) {
        this.overloadKillsMostInGame = overloadKillsMostInGame;
    }

    /**
     * Gets torbjorn kills.
     *
     * @return the torbjorn kills
     */
    public double getTorbjornKills() {
        return torbjornKills;
    }

    /**
     * Sets torbjorn kills.
     *
     * @param torbjornKills the torbjorn kills
     */
    public void setTorbjornKills(double torbjornKills) {
        this.torbjornKills = torbjornKills;
    }

    /**
     * Gets torbjorn kills avg per 10 min.
     *
     * @return the torbjorn kills avg per 10 min
     */
    public float getTorbjornKillsAvgPer10Min() {
        return torbjornKillsAvgPer10Min;
    }

    /**
     * Sets torbjorn kills avg per 10 min.
     *
     * @param torbjornKillsAvgPer10Min the torbjorn kills avg per 10 min
     */
    public void setTorbjornKillsAvgPer10Min(float torbjornKillsAvgPer10Min) {
        this.torbjornKillsAvgPer10Min = torbjornKillsAvgPer10Min;
    }

    /**
     * Gets torbjorn kills most in game.
     *
     * @return the torbjorn kills most in game
     */
    public double getTorbjornKillsMostInGame() {
        return torbjornKillsMostInGame;
    }

    /**
     * Sets torbjorn kills most in game.
     *
     * @param torbjornKillsMostInGame the torbjorn kills most in game
     */
    public void setTorbjornKillsMostInGame(double torbjornKillsMostInGame) {
        this.torbjornKillsMostInGame = torbjornKillsMostInGame;
    }

    /**
     * Gets turrets damage avg per 10 min.
     *
     * @return the turrets damage avg per 10 min
     */
    public double getTurretsDamageAvgPer10Min() {
        return turretsDamageAvgPer10Min;
    }

    /**
     * Sets turrets damage avg per 10 min.
     *
     * @param turretsDamageAvgPer10Min the turrets damage avg per 10 min
     */
    public void setTurretsDamageAvgPer10Min(double turretsDamageAvgPer10Min) {
        this.turretsDamageAvgPer10Min = turretsDamageAvgPer10Min;
    }

    /**
     * Gets turrets kills.
     *
     * @return the turrets kills
     */
    public double getTurretsKills() {
        return turretsKills;
    }

    /**
     * Sets turrets kills.
     *
     * @param turretsKills the turrets kills
     */
    public void setTurretsKills(double turretsKills) {
        this.turretsKills = turretsKills;
    }

    /**
     * Gets turrets kills avg per 10 min.
     *
     * @return the turrets kills avg per 10 min
     */
    public float getTurretsKillsAvgPer10Min() {
        return turretsKillsAvgPer10Min;
    }

    /**
     * Sets turrets kills avg per 10 min.
     *
     * @param turretsKillsAvgPer10Min the turrets kills avg per 10 min
     */
    public void setTurretsKillsAvgPer10Min(float turretsKillsAvgPer10Min) {
        this.turretsKillsAvgPer10Min = turretsKillsAvgPer10Min;
    }

    /**
     * Gets turrets kills most in game.
     *
     * @return the turrets kills most in game
     */
    public double getTurretsKillsMostInGame() {
        return turretsKillsMostInGame;
    }

    /**
     * Sets turrets kills most in game.
     *
     * @param turretsKillsMostInGame the turrets kills most in game
     */
    public void setTurretsKillsMostInGame(double turretsKillsMostInGame) {
        this.turretsKillsMostInGame = turretsKillsMostInGame;
    }

    /**
     * Gets health recovered.
     *
     * @return the health recovered
     */
    public double getHealthRecovered() {
        return healthRecovered;
    }

    /**
     * Sets health recovered.
     *
     * @param healthRecovered the health recovered
     */
    public void setHealthRecovered(double healthRecovered) {
        this.healthRecovered = healthRecovered;
    }

    /**
     * Gets health recovered avg per 10 min.
     *
     * @return the health recovered avg per 10 min
     */
    public double getHealthRecoveredAvgPer10Min() {
        return healthRecoveredAvgPer10Min;
    }

    /**
     * Sets health recovered avg per 10 min.
     *
     * @param healthRecoveredAvgPer10Min the health recovered avg per 10 min
     */
    public void setHealthRecoveredAvgPer10Min(double healthRecoveredAvgPer10Min) {
        this.healthRecoveredAvgPer10Min = healthRecoveredAvgPer10Min;
    }

    /**
     * Gets health recovered most in game.
     *
     * @return the health recovered most in game
     */
    public double getHealthRecoveredMostInGame() {
        return healthRecoveredMostInGame;
    }

    /**
     * Sets health recovered most in game.
     *
     * @param healthRecoveredMostInGame the health recovered most in game
     */
    public void setHealthRecoveredMostInGame(double healthRecoveredMostInGame) {
        this.healthRecoveredMostInGame = healthRecoveredMostInGame;
    }

    /**
     * Gets pulse bombs attached.
     *
     * @return the pulse bombs attached
     */
    public double getPulseBombsAttached() {
        return pulseBombsAttached;
    }

    /**
     * Sets pulse bombs attached.
     *
     * @param pulseBombsAttached the pulse bombs attached
     */
    public void setPulseBombsAttached(double pulseBombsAttached) {
        this.pulseBombsAttached = pulseBombsAttached;
    }

    /**
     * Gets pulse bombs attached avg per 10 min.
     *
     * @return the pulse bombs attached avg per 10 min
     */
    public float getPulseBombsAttachedAvgPer10Min() {
        return pulseBombsAttachedAvgPer10Min;
    }

    /**
     * Sets pulse bombs attached avg per 10 min.
     *
     * @param pulseBombsAttachedAvgPer10Min the pulse bombs attached avg per 10 min
     */
    public void setPulseBombsAttachedAvgPer10Min(float pulseBombsAttachedAvgPer10Min) {
        this.pulseBombsAttachedAvgPer10Min = pulseBombsAttachedAvgPer10Min;
    }

    /**
     * Gets pulse bombs attached most in game.
     *
     * @return the pulse bombs attached most in game
     */
    public double getPulseBombsAttachedMostInGame() {
        return pulseBombsAttachedMostInGame;
    }

    /**
     * Sets pulse bombs attached most in game.
     *
     * @param pulseBombsAttachedMostInGame the pulse bombs attached most in game
     */
    public void setPulseBombsAttachedMostInGame(double pulseBombsAttachedMostInGame) {
        this.pulseBombsAttachedMostInGame = pulseBombsAttachedMostInGame;
    }

    /**
     * Gets pulse bombs kills.
     *
     * @return the pulse bombs kills
     */
    public double getPulseBombsKills() {
        return pulseBombsKills;
    }

    /**
     * Sets pulse bombs kills.
     *
     * @param pulseBombsKills the pulse bombs kills
     */
    public void setPulseBombsKills(double pulseBombsKills) {
        this.pulseBombsKills = pulseBombsKills;
    }

    /**
     * Gets pulse bombs kills avg per 10 min.
     *
     * @return the pulse bombs kills avg per 10 min
     */
    public float getPulseBombsKillsAvgPer10Min() {
        return pulseBombsKillsAvgPer10Min;
    }

    /**
     * Sets pulse bombs kills avg per 10 min.
     *
     * @param pulseBombsKillsAvgPer10Min the pulse bombs kills avg per 10 min
     */
    public void setPulseBombsKillsAvgPer10Min(float pulseBombsKillsAvgPer10Min) {
        this.pulseBombsKillsAvgPer10Min = pulseBombsKillsAvgPer10Min;
    }

    /**
     * Gets pulse bombs kills most in game.
     *
     * @return the pulse bombs kills most in game
     */
    public double getPulseBombsKillsMostInGame() {
        return pulseBombsKillsMostInGame;
    }

    /**
     * Sets pulse bombs kills most in game.
     *
     * @param pulseBombsKillsMostInGame the pulse bombs kills most in game
     */
    public void setPulseBombsKillsMostInGame(double pulseBombsKillsMostInGame) {
        this.pulseBombsKillsMostInGame = pulseBombsKillsMostInGame;
    }

    /**
     * Gets venom mine kills.
     *
     * @return the venom mine kills
     */
    public double getVenomMineKills() {
        return venomMineKills;
    }

    /**
     * Sets venom mine kills.
     *
     * @param venomMineKills the venom mine kills
     */
    public void setVenomMineKills(double venomMineKills) {
        this.venomMineKills = venomMineKills;
    }

    /**
     * Gets venom mine kills avg per 10 min.
     *
     * @return the venom mine kills avg per 10 min
     */
    public float getVenomMineKillsAvgPer10Min() {
        return venomMineKillsAvgPer10Min;
    }

    /**
     * Sets venom mine kills avg per 10 min.
     *
     * @param venomMineKillsAvgPer10Min the venom mine kills avg per 10 min
     */
    public void setVenomMineKillsAvgPer10Min(float venomMineKillsAvgPer10Min) {
        this.venomMineKillsAvgPer10Min = venomMineKillsAvgPer10Min;
    }

    /**
     * Gets venom mine kills most in game.
     *
     * @return the venom mine kills most in game
     */
    public double getVenomMineKillsMostInGame() {
        return venomMineKillsMostInGame;
    }

    /**
     * Sets venom mine kills most in game.
     *
     * @param venomMineKillsMostInGame the venom mine kills most in game
     */
    public void setVenomMineKillsMostInGame(double venomMineKillsMostInGame) {
        this.venomMineKillsMostInGame = venomMineKillsMostInGame;
    }

    /**
     * Gets jump kills.
     *
     * @return the jump kills
     */
    public double getJumpKills() {
        return jumpKills;
    }

    /**
     * Sets jump kills.
     *
     * @param jumpKills the jump kills
     */
    public void setJumpKills(double jumpKills) {
        this.jumpKills = jumpKills;
    }

    /**
     * Gets jump pack kills.
     *
     * @return the jump pack kills
     */
    public double getJumpPackKills() {
        return jumpPackKills;
    }

    /**
     * Sets jump pack kills.
     *
     * @param jumpPackKills the jump pack kills
     */
    public void setJumpPackKills(double jumpPackKills) {
        this.jumpPackKills = jumpPackKills;
    }

    /**
     * Gets jump pack kills avg per 10 min.
     *
     * @return the jump pack kills avg per 10 min
     */
    public float getJumpPackKillsAvgPer10Min() {
        return jumpPackKillsAvgPer10Min;
    }

    /**
     * Sets jump pack kills avg per 10 min.
     *
     * @param jumpPackKillsAvgPer10Min the jump pack kills avg per 10 min
     */
    public void setJumpPackKillsAvgPer10Min(float jumpPackKillsAvgPer10Min) {
        this.jumpPackKillsAvgPer10Min = jumpPackKillsAvgPer10Min;
    }

    /**
     * Gets jump pack kills most in game.
     *
     * @return the jump pack kills most in game
     */
    public double getJumpPackKillsMostInGame() {
        return jumpPackKillsMostInGame;
    }

    /**
     * Sets jump pack kills most in game.
     *
     * @param jumpPackKillsMostInGame the jump pack kills most in game
     */
    public void setJumpPackKillsMostInGame(double jumpPackKillsMostInGame) {
        this.jumpPackKillsMostInGame = jumpPackKillsMostInGame;
    }

    /**
     * Gets melee kills.
     *
     * @return the melee kills
     */
    public double getMeleeKills() {
        return meleeKills;
    }

    /**
     * Sets melee kills.
     *
     * @param meleeKills the melee kills
     */
    public void setMeleeKills(double meleeKills) {
        this.meleeKills = meleeKills;
    }

    /**
     * Gets melee kills avg per 10 min.
     *
     * @return the melee kills avg per 10 min
     */
    public float getMeleeKillsAvgPer10Min() {
        return meleeKillsAvgPer10Min;
    }

    /**
     * Sets melee kills avg per 10 min.
     *
     * @param meleeKillsAvgPer10Min the melee kills avg per 10 min
     */
    public void setMeleeKillsAvgPer10Min(float meleeKillsAvgPer10Min) {
        this.meleeKillsAvgPer10Min = meleeKillsAvgPer10Min;
    }

    /**
     * Gets melee kills most in game.
     *
     * @return the melee kills most in game
     */
    public double getMeleeKillsMostInGame() {
        return meleeKillsMostInGame;
    }

    /**
     * Sets melee kills most in game.
     *
     * @param meleeKillsMostInGame the melee kills most in game
     */
    public void setMeleeKillsMostInGame(double meleeKillsMostInGame) {
        this.meleeKillsMostInGame = meleeKillsMostInGame;
    }

    /**
     * Gets players knocked back.
     *
     * @return the players knocked back
     */
    public double getPlayersKnockedBack() {
        return playersKnockedBack;
    }

    /**
     * Sets players knocked back.
     *
     * @param playersKnockedBack the players knocked back
     */
    public void setPlayersKnockedBack(double playersKnockedBack) {
        this.playersKnockedBack = playersKnockedBack;
    }

    /**
     * Gets players knocked back avg per 10 min.
     *
     * @return the players knocked back avg per 10 min
     */
    public float getPlayersKnockedBackAvgPer10Min() {
        return playersKnockedBackAvgPer10Min;
    }

    /**
     * Sets players knocked back avg per 10 min.
     *
     * @param playersKnockedBackAvgPer10Min the players knocked back avg per 10 min
     */
    public void setPlayersKnockedBackAvgPer10Min(float playersKnockedBackAvgPer10Min) {
        this.playersKnockedBackAvgPer10Min = playersKnockedBackAvgPer10Min;
    }

    /**
     * Gets players knocked back most in game.
     *
     * @return the players knocked back most in game
     */
    public double getPlayersKnockedBackMostInGame() {
        return playersKnockedBackMostInGame;
    }

    /**
     * Sets players knocked back most in game.
     *
     * @param playersKnockedBackMostInGame the players knocked back most in game
     */
    public void setPlayersKnockedBackMostInGame(double playersKnockedBackMostInGame) {
        this.playersKnockedBackMostInGame = playersKnockedBackMostInGame;
    }

    /**
     * Gets primal rage kills.
     *
     * @return the primal rage kills
     */
    public double getPrimalRageKills() {
        return primalRageKills;
    }

    /**
     * Sets primal rage kills.
     *
     * @param primalRageKills the primal rage kills
     */
    public void setPrimalRageKills(double primalRageKills) {
        this.primalRageKills = primalRageKills;
    }

    /**
     * Gets primal rage kills avg per 10 min.
     *
     * @return the primal rage kills avg per 10 min
     */
    public float getPrimalRageKillsAvgPer10Min() {
        return primalRageKillsAvgPer10Min;
    }

    /**
     * Sets primal rage kills avg per 10 min.
     *
     * @param primalRageKillsAvgPer10Min the primal rage kills avg per 10 min
     */
    public void setPrimalRageKillsAvgPer10Min(float primalRageKillsAvgPer10Min) {
        this.primalRageKillsAvgPer10Min = primalRageKillsAvgPer10Min;
    }

    /**
     * Gets primal rage kills most in game.
     *
     * @return the primal rage kills most in game
     */
    public double getPrimalRageKillsMostInGame() {
        return primalRageKillsMostInGame;
    }

    /**
     * Sets primal rage kills most in game.
     *
     * @param primalRageKillsMostInGame the primal rage kills most in game
     */
    public void setPrimalRageKillsMostInGame(double primalRageKillsMostInGame) {
        this.primalRageKillsMostInGame = primalRageKillsMostInGame;
    }

    /**
     * Gets primal rage melee accuracy.
     *
     * @return the primal rage melee accuracy
     */
    public String getPrimalRageMeleeAccuracy() {
        return primalRageMeleeAccuracy;
    }

    /**
     * Sets primal rage melee accuracy.
     *
     * @param primalRageMeleeAccuracy the primal rage melee accuracy
     */
    public void setPrimalRageMeleeAccuracy(String primalRageMeleeAccuracy) {
        this.primalRageMeleeAccuracy = primalRageMeleeAccuracy;
    }

    /**
     * Gets tesla cannon accuracy.
     *
     * @return the tesla cannon accuracy
     */
    public String getTeslaCannonAccuracy() {
        return teslaCannonAccuracy;
    }

    /**
     * Sets tesla cannon accuracy.
     *
     * @param teslaCannonAccuracy the tesla cannon accuracy
     */
    public void setTeslaCannonAccuracy(String teslaCannonAccuracy) {
        this.teslaCannonAccuracy = teslaCannonAccuracy;
    }

    /**
     * Gets weapon kills.
     *
     * @return the weapon kills
     */
    public double getWeaponKills() {
        return weaponKills;
    }

    /**
     * Sets weapon kills.
     *
     * @param weaponKills the weapon kills
     */
    public void setWeaponKills(double weaponKills) {
        this.weaponKills = weaponKills;
    }

    /**
     * Gets grappling claw kills.
     *
     * @return the grappling claw kills
     */
    public double getGrapplingClawKills() {
        return grapplingClawKills;
    }

    /**
     * Sets grappling claw kills.
     *
     * @param grapplingClawKills the grappling claw kills
     */
    public void setGrapplingClawKills(double grapplingClawKills) {
        this.grapplingClawKills = grapplingClawKills;
    }

    /**
     * Gets grappling claw kills avg per 10 min.
     *
     * @return the grappling claw kills avg per 10 min
     */
    public float getGrapplingClawKillsAvgPer10Min() {
        return grapplingClawKillsAvgPer10Min;
    }

    /**
     * Sets grappling claw kills avg per 10 min.
     *
     * @param grapplingClawKillsAvgPer10Min the grappling claw kills avg per 10 min
     */
    public void setGrapplingClawKillsAvgPer10Min(float grapplingClawKillsAvgPer10Min) {
        this.grapplingClawKillsAvgPer10Min = grapplingClawKillsAvgPer10Min;
    }

    /**
     * Gets grappling claw kills most in game.
     *
     * @return the grappling claw kills most in game
     */
    public double getGrapplingClawKillsMostInGame() {
        return grapplingClawKillsMostInGame;
    }

    /**
     * Sets grappling claw kills most in game.
     *
     * @param grapplingClawKillsMostInGame the grappling claw kills most in game
     */
    public void setGrapplingClawKillsMostInGame(double grapplingClawKillsMostInGame) {
        this.grapplingClawKillsMostInGame = grapplingClawKillsMostInGame;
    }

    /**
     * Gets minefield kills.
     *
     * @return the minefield kills
     */
    public double getMinefieldKills() {
        return minefieldKills;
    }

    /**
     * Sets minefield kills.
     *
     * @param minefieldKills the minefield kills
     */
    public void setMinefieldKills(double minefieldKills) {
        this.minefieldKills = minefieldKills;
    }

    /**
     * Gets minefield kills avg per 10 min.
     *
     * @return the minefield kills avg per 10 min
     */
    public float getMinefieldKillsAvgPer10Min() {
        return minefieldKillsAvgPer10Min;
    }

    /**
     * Sets minefield kills avg per 10 min.
     *
     * @param minefieldKillsAvgPer10Min the minefield kills avg per 10 min
     */
    public void setMinefieldKillsAvgPer10Min(float minefieldKillsAvgPer10Min) {
        this.minefieldKillsAvgPer10Min = minefieldKillsAvgPer10Min;
    }

    /**
     * Gets minefield kills most in game.
     *
     * @return the minefield kills most in game
     */
    public double getMinefieldKillsMostInGame() {
        return minefieldKillsMostInGame;
    }

    /**
     * Sets minefield kills most in game.
     *
     * @param minefieldKillsMostInGame the minefield kills most in game
     */
    public void setMinefieldKillsMostInGame(double minefieldKillsMostInGame) {
        this.minefieldKillsMostInGame = minefieldKillsMostInGame;
    }

    /**
     * Gets average energy.
     *
     * @return the average energy
     */
    public String getAverageEnergy() {
        return averageEnergy;
    }

    /**
     * Sets average energy.
     *
     * @param averageEnergy the average energy
     */
    public void setAverageEnergy(String averageEnergy) {
        this.averageEnergy = averageEnergy;
    }

    /**
     * Gets average energy best in game.
     *
     * @return the average energy best in game
     */
    public String getAverageEnergyBestInGame() {
        return averageEnergyBestInGame;
    }

    /**
     * Sets average energy best in game.
     *
     * @param averageEnergyBestInGame the average energy best in game
     */
    public void setAverageEnergyBestInGame(String averageEnergyBestInGame) {
        this.averageEnergyBestInGame = averageEnergyBestInGame;
    }

    /**
     * Gets graviton surge kills.
     *
     * @return the graviton surge kills
     */
    public double getGravitonSurgeKills() {
        return gravitonSurgeKills;
    }

    /**
     * Sets graviton surge kills.
     *
     * @param gravitonSurgeKills the graviton surge kills
     */
    public void setGravitonSurgeKills(double gravitonSurgeKills) {
        this.gravitonSurgeKills = gravitonSurgeKills;
    }

    /**
     * Gets graviton surge kills avg per 10 min.
     *
     * @return the graviton surge kills avg per 10 min
     */
    public float getGravitonSurgeKillsAvgPer10Min() {
        return gravitonSurgeKillsAvgPer10Min;
    }

    /**
     * Sets graviton surge kills avg per 10 min.
     *
     * @param gravitonSurgeKillsAvgPer10Min the graviton surge kills avg per 10 min
     */
    public void setGravitonSurgeKillsAvgPer10Min(float gravitonSurgeKillsAvgPer10Min) {
        this.gravitonSurgeKillsAvgPer10Min = gravitonSurgeKillsAvgPer10Min;
    }

    /**
     * Gets graviton surge kills most in game.
     *
     * @return the graviton surge kills most in game
     */
    public double getGravitonSurgeKillsMostInGame() {
        return gravitonSurgeKillsMostInGame;
    }

    /**
     * Sets graviton surge kills most in game.
     *
     * @param gravitonSurgeKillsMostInGame the graviton surge kills most in game
     */
    public void setGravitonSurgeKillsMostInGame(double gravitonSurgeKillsMostInGame) {
        this.gravitonSurgeKillsMostInGame = gravitonSurgeKillsMostInGame;
    }

    /**
     * Gets high energy kills.
     *
     * @return the high energy kills
     */
    public double getHighEnergyKills() {
        return highEnergyKills;
    }

    /**
     * Sets high energy kills.
     *
     * @param highEnergyKills the high energy kills
     */
    public void setHighEnergyKills(double highEnergyKills) {
        this.highEnergyKills = highEnergyKills;
    }

    /**
     * Gets high energy kills avg per 10 min.
     *
     * @return the high energy kills avg per 10 min
     */
    public float getHighEnergyKillsAvgPer10Min() {
        return highEnergyKillsAvgPer10Min;
    }

    /**
     * Sets high energy kills avg per 10 min.
     *
     * @param highEnergyKillsAvgPer10Min the high energy kills avg per 10 min
     */
    public void setHighEnergyKillsAvgPer10Min(float highEnergyKillsAvgPer10Min) {
        this.highEnergyKillsAvgPer10Min = highEnergyKillsAvgPer10Min;
    }

    /**
     * Gets high energy kills most in game.
     *
     * @return the high energy kills most in game
     */
    public double getHighEnergyKillsMostInGame() {
        return highEnergyKillsMostInGame;
    }

    /**
     * Sets high energy kills most in game.
     *
     * @param highEnergyKillsMostInGame the high energy kills most in game
     */
    public void setHighEnergyKillsMostInGame(double highEnergyKillsMostInGame) {
        this.highEnergyKillsMostInGame = highEnergyKillsMostInGame;
    }

    /**
     * Gets projected barriers applied.
     *
     * @return the projected barriers applied
     */
    public double getProjectedBarriersApplied() {
        return projectedBarriersApplied;
    }

    /**
     * Sets projected barriers applied.
     *
     * @param projectedBarriersApplied the projected barriers applied
     */
    public void setProjectedBarriersApplied(double projectedBarriersApplied) {
        this.projectedBarriersApplied = projectedBarriersApplied;
    }

    /**
     * Gets projected barriers applied avg per 10 min.
     *
     * @return the projected barriers applied avg per 10 min
     */
    public float getProjectedBarriersAppliedAvgPer10Min() {
        return projectedBarriersAppliedAvgPer10Min;
    }

    /**
     * Sets projected barriers applied avg per 10 min.
     *
     * @param projectedBarriersAppliedAvgPer10Min the projected barriers applied avg per 10 min
     */
    public void setProjectedBarriersAppliedAvgPer10Min(float projectedBarriersAppliedAvgPer10Min) {
        this.projectedBarriersAppliedAvgPer10Min = projectedBarriersAppliedAvgPer10Min;
    }

    /**
     * Gets projected barriers applied most in game.
     *
     * @return the projected barriers applied most in game
     */
    public double getProjectedBarriersAppliedMostInGame() {
        return projectedBarriersAppliedMostInGame;
    }

    /**
     * Sets projected barriers applied most in game.
     *
     * @param projectedBarriersAppliedMostInGame the projected barriers applied most in game
     */
    public void setProjectedBarriersAppliedMostInGame(double projectedBarriersAppliedMostInGame) {
        this.projectedBarriersAppliedMostInGame = projectedBarriersAppliedMostInGame;
    }

    /**
     * Gets transcendence healing.
     *
     * @return the transcendence healing
     */
    public double getTranscendenceHealing() {
        return transcendenceHealing;
    }

    /**
     * Sets transcendence healing.
     *
     * @param transcendenceHealing the transcendence healing
     */
    public void setTranscendenceHealing(double transcendenceHealing) {
        this.transcendenceHealing = transcendenceHealing;
    }

    /**
     * Gets transcendence healing best.
     *
     * @return the transcendence healing best
     */
    public double getTranscendenceHealingBest() {
        return transcendenceHealingBest;
    }

    /**
     * Sets transcendence healing best.
     *
     * @param transcendenceHealingBest the transcendence healing best
     */
    public void setTranscendenceHealingBest(double transcendenceHealingBest) {
        this.transcendenceHealingBest = transcendenceHealingBest;
    }
}
