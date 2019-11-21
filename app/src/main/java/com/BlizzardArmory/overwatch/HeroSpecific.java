package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public double getEnemiesTrapped() {
        return enemiesTrapped;
    }

    public void setEnemiesTrapped(double enemiesTrapped) {
        this.enemiesTrapped = enemiesTrapped;
    }

    public float getEnemiesTrappedAvgPer10Min() {
        return enemiesTrappedAvgPer10Min;
    }

    public void setEnemiesTrappedAvgPer10Min(float enemiesTrappedAvgPer10Min) {
        this.enemiesTrappedAvgPer10Min = enemiesTrappedAvgPer10Min;
    }

    public double getEnemiesTrappedMostInGame() {
        return enemiesTrappedMostInGame;
    }

    public void setEnemiesTrappedMostInGame(double enemiesTrappedMostInGame) {
        this.enemiesTrappedMostInGame = enemiesTrappedMostInGame;
    }

    public double getRipTireKills() {
        return ripTireKills;
    }

    public void setRipTireKills(double ripTireKills) {
        this.ripTireKills = ripTireKills;
    }

    public float getRipTireKillsAvgPer10Min() {
        return ripTireKillsAvgPer10Min;
    }

    public void setRipTireKillsAvgPer10Min(float ripTireKillsAvgPer10Min) {
        this.ripTireKillsAvgPer10Min = ripTireKillsAvgPer10Min;
    }

    public double getRipTireKillsMostInGame() {
        return ripTireKillsMostInGame;
    }

    public void setRipTireKillsMostInGame(double ripTireKillsMostInGame) {
        this.ripTireKillsMostInGame = ripTireKillsMostInGame;
    }

    public double getDragonstrikeKills() {
        return dragonstrikeKills;
    }

    public void setDragonstrikeKills(double dragonstrikeKills) {
        this.dragonstrikeKills = dragonstrikeKills;
    }

    public float getDragonstrikeKillsAvgPer10Min() {
        return dragonstrikeKillsAvgPer10Min;
    }

    public void setDragonstrikeKillsAvgPer10Min(float dragonstrikeKillsAvgPer10Min) {
        this.dragonstrikeKillsAvgPer10Min = dragonstrikeKillsAvgPer10Min;
    }

    public double getDragonstrikeKillsMostInGame() {
        return dragonstrikeKillsMostInGame;
    }

    public void setDragonstrikeKillsMostInGame(double dragonstrikeKillsMostInGame) {
        this.dragonstrikeKillsMostInGame = dragonstrikeKillsMostInGame;
    }

    public double getScatterArrowKills() {
        return scatterArrowKills;
    }

    public void setScatterArrowKills(double scatterArrowKills) {
        this.scatterArrowKills = scatterArrowKills;
    }

    public float getScatterArrowKillsAvgPer10Min() {
        return scatterArrowKillsAvgPer10Min;
    }

    public void setScatterArrowKillsAvgPer10Min(float scatterArrowKillsAvgPer10Min) {
        this.scatterArrowKillsAvgPer10Min = scatterArrowKillsAvgPer10Min;
    }

    public double getScatterArrowKillsMostInGame() {
        return scatterArrowKillsMostInGame;
    }

    public void setScatterArrowKillsMostInGame(double scatterArrowKillsMostInGame) {
        this.scatterArrowKillsMostInGame = scatterArrowKillsMostInGame;
    }

    public double getStormArrowKills() {
        return stormArrowKills;
    }

    public void setStormArrowKills(double stormArrowKills) {
        this.stormArrowKills = stormArrowKills;
    }

    public float getStormArrowKillsAvgPer10Min() {
        return stormArrowKillsAvgPer10Min;
    }

    public void setStormArrowKillsAvgPer10Min(float stormArrowKillsAvgPer10Min) {
        this.stormArrowKillsAvgPer10Min = stormArrowKillsAvgPer10Min;
    }

    public double getStormArrowKillsMostInGame() {
        return stormArrowKillsMostInGame;
    }

    public void setStormArrowKillsMostInGame(double stormArrowKillsMostInGame) {
        this.stormArrowKillsMostInGame = stormArrowKillsMostInGame;
    }

    public double getBioticGrenadeKills() {
        return bioticGrenadeKills;
    }

    public void setBioticGrenadeKills(double bioticGrenadeKills) {
        this.bioticGrenadeKills = bioticGrenadeKills;
    }

    public double getEnemiesSlept() {
        return enemiesSlept;
    }

    public void setEnemiesSlept(double enemiesSlept) {
        this.enemiesSlept = enemiesSlept;
    }

    public float getEnemiesSleptAvgPer10Min() {
        return enemiesSleptAvgPer10Min;
    }

    public void setEnemiesSleptAvgPer10Min(float enemiesSleptAvgPer10Min) {
        this.enemiesSleptAvgPer10Min = enemiesSleptAvgPer10Min;
    }

    public double getEnemiesSleptMostInGame() {
        return enemiesSleptMostInGame;
    }

    public void setEnemiesSleptMostInGame(double enemiesSleptMostInGame) {
        this.enemiesSleptMostInGame = enemiesSleptMostInGame;
    }

    public double getNanoBoostAssists() {
        return nanoBoostAssists;
    }

    public void setNanoBoostAssists(double nanoBoostAssists) {
        this.nanoBoostAssists = nanoBoostAssists;
    }

    public float getNanoBoostAssistsAvgPer10Min() {
        return nanoBoostAssistsAvgPer10Min;
    }

    public void setNanoBoostAssistsAvgPer10Min(float nanoBoostAssistsAvgPer10Min) {
        this.nanoBoostAssistsAvgPer10Min = nanoBoostAssistsAvgPer10Min;
    }

    public double getNanoBoostAssistsMostInGame() {
        return nanoBoostAssistsMostInGame;
    }

    public void setNanoBoostAssistsMostInGame(double nanoBoostAssistsMostInGame) {
        this.nanoBoostAssistsMostInGame = nanoBoostAssistsMostInGame;
    }

    public double getNanoBoostsApplied() {
        return nanoBoostsApplied;
    }

    public void setNanoBoostsApplied(double nanoBoostsApplied) {
        this.nanoBoostsApplied = nanoBoostsApplied;
    }

    public float getNanoBoostsAppliedAvgPer10Min() {
        return nanoBoostsAppliedAvgPer10Min;
    }

    public void setNanoBoostsAppliedAvgPer10Min(float nanoBoostsAppliedAvgPer10Min) {
        this.nanoBoostsAppliedAvgPer10Min = nanoBoostsAppliedAvgPer10Min;
    }

    public double getNanoBoostsAppliedMostInGame() {
        return nanoBoostsAppliedMostInGame;
    }

    public void setNanoBoostsAppliedMostInGame(double nanoBoostsAppliedMostInGame) {
        this.nanoBoostsAppliedMostInGame = nanoBoostsAppliedMostInGame;
    }

    public String getScopedAccuracy() {
        return scopedAccuracy;
    }

    public void setScopedAccuracy(String scopedAccuracy) {
        this.scopedAccuracy = scopedAccuracy;
    }

    public String getScopedAccuracyBestInGame() {
        return scopedAccuracyBestInGame;
    }

    public void setScopedAccuracyBestInGame(String scopedAccuracyBestInGame) {
        this.scopedAccuracyBestInGame = scopedAccuracyBestInGame;
    }

    public double getSelfHealing() {
        return selfHealing;
    }

    public void setSelfHealing(double selfHealing) {
        this.selfHealing = selfHealing;
    }

    public double getSelfHealingAvgPer10Min() {
        return selfHealingAvgPer10Min;
    }

    public void setSelfHealingAvgPer10Min(double selfHealingAvgPer10Min) {
        this.selfHealingAvgPer10Min = selfHealingAvgPer10Min;
    }

    public double getSelfHealingMostInGame() {
        return selfHealingMostInGame;
    }

    public void setSelfHealingMostInGame(double selfHealingMostInGame) {
        this.selfHealingMostInGame = selfHealingMostInGame;
    }

    public String getUnscopedAccuracy() {
        return unscopedAccuracy;
    }

    public void setUnscopedAccuracy(String unscopedAccuracy) {
        this.unscopedAccuracy = unscopedAccuracy;
    }

    public String getUnscopedAccuracyBestInGame() {
        return unscopedAccuracyBestInGame;
    }

    public void setUnscopedAccuracyBestInGame(String unscopedAccuracyBestInGame) {
        this.unscopedAccuracyBestInGame = unscopedAccuracyBestInGame;
    }

    public double getBobKills() {
        return bobKills;
    }

    public void setBobKills(double bobKills) {
        this.bobKills = bobKills;
    }

    public float getBobKillsAvgPer10Min() {
        return bobKillsAvgPer10Min;
    }

    public void setBobKillsAvgPer10Min(float bobKillsAvgPer10Min) {
        this.bobKillsAvgPer10Min = bobKillsAvgPer10Min;
    }

    public double getBobKillsMostInGame() {
        return bobKillsMostInGame;
    }

    public void setBobKillsMostInGame(double bobKillsMostInGame) {
        this.bobKillsMostInGame = bobKillsMostInGame;
    }

    public double getCoachGunKills() {
        return coachGunKills;
    }

    public void setCoachGunKills(double coachGunKills) {
        this.coachGunKills = coachGunKills;
    }

    public float getCoachGunKillsAvgPer10Min() {
        return coachGunKillsAvgPer10Min;
    }

    public void setCoachGunKillsAvgPer10Min(float coachGunKillsAvgPer10Min) {
        this.coachGunKillsAvgPer10Min = coachGunKillsAvgPer10Min;
    }

    public double getCoachGunKillsMostInGame() {
        return coachGunKillsMostInGame;
    }

    public void setCoachGunKillsMostInGame(double coachGunKillsMostInGame) {
        this.coachGunKillsMostInGame = coachGunKillsMostInGame;
    }

    public double getDynamiteKills() {
        return dynamiteKills;
    }

    public void setDynamiteKills(double dynamiteKills) {
        this.dynamiteKills = dynamiteKills;
    }

    public float getDynamiteKillsAvgPer10Min() {
        return dynamiteKillsAvgPer10Min;
    }

    public void setDynamiteKillsAvgPer10Min(float dynamiteKillsAvgPer10Min) {
        this.dynamiteKillsAvgPer10Min = dynamiteKillsAvgPer10Min;
    }

    public double getDynamiteKillsMostInGame() {
        return dynamiteKillsMostInGame;
    }

    public void setDynamiteKillsMostInGame(double dynamiteKillsMostInGame) {
        this.dynamiteKillsMostInGame = dynamiteKillsMostInGame;
    }

    public double getScopedCriticalHits() {
        return scopedCriticalHits;
    }

    public void setScopedCriticalHits(double scopedCriticalHits) {
        this.scopedCriticalHits = scopedCriticalHits;
    }

    public String getScopedCriticalHitsAccuracy() {
        return scopedCriticalHitsAccuracy;
    }

    public void setScopedCriticalHitsAccuracy(String scopedCriticalHitsAccuracy) {
        this.scopedCriticalHitsAccuracy = scopedCriticalHitsAccuracy;
    }

    public float getScopedCriticalHitsAvgPer10Min() {
        return scopedCriticalHitsAvgPer10Min;
    }

    public void setScopedCriticalHitsAvgPer10Min(float scopedCriticalHitsAvgPer10Min) {
        this.scopedCriticalHitsAvgPer10Min = scopedCriticalHitsAvgPer10Min;
    }

    public double getScopedCriticalHitsMostInGame() {
        return scopedCriticalHitsMostInGame;
    }

    public void setScopedCriticalHitsMostInGame(double scopedCriticalHitsMostInGame) {
        this.scopedCriticalHitsMostInGame = scopedCriticalHitsMostInGame;
    }

    public double getAmplificationMatrixCasts() {
        return amplificationMatrixCasts;
    }

    public void setAmplificationMatrixCasts(double amplificationMatrixCasts) {
        this.amplificationMatrixCasts = amplificationMatrixCasts;
    }

    public float getAmplificationMatrixCastsAvgPer10Min() {
        return amplificationMatrixCastsAvgPer10Min;
    }

    public void setAmplificationMatrixCastsAvgPer10Min(float amplificationMatrixCastsAvgPer10Min) {
        this.amplificationMatrixCastsAvgPer10Min = amplificationMatrixCastsAvgPer10Min;
    }

    public double getAmplificationMatrixCastsMostInGame() {
        return amplificationMatrixCastsMostInGame;
    }

    public void setAmplificationMatrixCastsMostInGame(double amplificationMatrixCastsMostInGame) {
        this.amplificationMatrixCastsMostInGame = amplificationMatrixCastsMostInGame;
    }

    public double getDamageAmplified() {
        return damageAmplified;
    }

    public void setDamageAmplified(double damageAmplified) {
        this.damageAmplified = damageAmplified;
    }

    public double getDamageAmplifiedAvgPer10Min() {
        return damageAmplifiedAvgPer10Min;
    }

    public void setDamageAmplifiedAvgPer10Min(double damageAmplifiedAvgPer10Min) {
        this.damageAmplifiedAvgPer10Min = damageAmplifiedAvgPer10Min;
    }

    public double getDamageAmplifiedMostInGame() {
        return damageAmplifiedMostInGame;
    }

    public void setDamageAmplifiedMostInGame(double damageAmplifiedMostInGame) {
        this.damageAmplifiedMostInGame = damageAmplifiedMostInGame;
    }

    public String getHealingAccuracy() {
        return healingAccuracy;
    }

    public void setHealingAccuracy(String healingAccuracy) {
        this.healingAccuracy = healingAccuracy;
    }

    public String getHealingAccuracyBestInGame() {
        return healingAccuracyBestInGame;
    }

    public void setHealingAccuracyBestInGame(String healingAccuracyBestInGame) {
        this.healingAccuracyBestInGame = healingAccuracyBestInGame;
    }

    public double getImmortalityFieldDeathsPrevented() {
        return immortalityFieldDeathsPrevented;
    }

    public void setImmortalityFieldDeathsPrevented(double immortalityFieldDeathsPrevented) {
        this.immortalityFieldDeathsPrevented = immortalityFieldDeathsPrevented;
    }

    public float getImmortalityFieldDeathsPreventedAvgPer10Min() {
        return immortalityFieldDeathsPreventedAvgPer10Min;
    }

    public void setImmortalityFieldDeathsPreventedAvgPer10Min(float immortalityFieldDeathsPreventedAvgPer10Min) {
        this.immortalityFieldDeathsPreventedAvgPer10Min = immortalityFieldDeathsPreventedAvgPer10Min;
    }

    public double getImmortalityFieldDeathsPreventedMostInGame() {
        return immortalityFieldDeathsPreventedMostInGame;
    }

    public void setImmortalityFieldDeathsPreventedMostInGame(double immortalityFieldDeathsPreventedMostInGame) {
        this.immortalityFieldDeathsPreventedMostInGame = immortalityFieldDeathsPreventedMostInGame;
    }

    public double getReconKills() {
        return reconKills;
    }

    public void setReconKills(double reconKills) {
        this.reconKills = reconKills;
    }

    public float getReconKillsAvgPer10Min() {
        return reconKillsAvgPer10Min;
    }

    public void setReconKillsAvgPer10Min(float reconKillsAvgPer10Min) {
        this.reconKillsAvgPer10Min = reconKillsAvgPer10Min;
    }

    public double getReconKillsMostInGame() {
        return reconKillsMostInGame;
    }

    public void setReconKillsMostInGame(double reconKillsMostInGame) {
        this.reconKillsMostInGame = reconKillsMostInGame;
    }

    public double getSentryKills() {
        return sentryKills;
    }

    public void setSentryKills(double sentryKills) {
        this.sentryKills = sentryKills;
    }

    public float getSentryKillsAvgPer10Min() {
        return sentryKillsAvgPer10Min;
    }

    public void setSentryKillsAvgPer10Min(float sentryKillsAvgPer10Min) {
        this.sentryKillsAvgPer10Min = sentryKillsAvgPer10Min;
    }

    public double getSentryKillsMostInGame() {
        return sentryKillsMostInGame;
    }

    public void setSentryKillsMostInGame(double sentryKillsMostInGame) {
        this.sentryKillsMostInGame = sentryKillsMostInGame;
    }

    public double getTankKills() {
        return tankKills;
    }

    public void setTankKills(double tankKills) {
        this.tankKills = tankKills;
    }

    public float getTankKillsAvgPer10Min() {
        return tankKillsAvgPer10Min;
    }

    public void setTankKillsAvgPer10Min(float tankKillsAvgPer10Min) {
        this.tankKillsAvgPer10Min = tankKillsAvgPer10Min;
    }

    public double getTankKillsMostInGame() {
        return tankKillsMostInGame;
    }

    public void setTankKillsMostInGame(double tankKillsMostInGame) {
        this.tankKillsMostInGame = tankKillsMostInGame;
    }

    public double getArmorProvided() {
        return armorProvided;
    }

    public void setArmorProvided(double armorProvided) {
        this.armorProvided = armorProvided;
    }

    public double getArmorProvidedAvgPer10Min() {
        return armorProvidedAvgPer10Min;
    }

    public void setArmorProvidedAvgPer10Min(double armorProvidedAvgPer10Min) {
        this.armorProvidedAvgPer10Min = armorProvidedAvgPer10Min;
    }

    public double getArmorProvidedMostInGame() {
        return armorProvidedMostInGame;
    }

    public void setArmorProvidedMostInGame(double armorProvidedMostInGame) {
        this.armorProvidedMostInGame = armorProvidedMostInGame;
    }

    public double getDamageBlocked() {
        return damageBlocked;
    }

    public void setDamageBlocked(double damageBlocked) {
        this.damageBlocked = damageBlocked;
    }

    public double getDamageBlockedAvgPer10Min() {
        return damageBlockedAvgPer10Min;
    }

    public void setDamageBlockedAvgPer10Min(double damageBlockedAvgPer10Min) {
        this.damageBlockedAvgPer10Min = damageBlockedAvgPer10Min;
    }

    public double getDamageBlockedMostInGame() {
        return damageBlockedMostInGame;
    }

    public void setDamageBlockedMostInGame(double damageBlockedMostInGame) {
        this.damageBlockedMostInGame = damageBlockedMostInGame;
    }

    public String getInspireUptimePercentage() {
        return inspireUptimePercentage;
    }

    public void setInspireUptimePercentage(String inspireUptimePercentage) {
        this.inspireUptimePercentage = inspireUptimePercentage;
    }

    public double getMechDeaths() {
        return mechDeaths;
    }

    public void setMechDeaths(double mechDeaths) {
        this.mechDeaths = mechDeaths;
    }

    public double getMechsCalled() {
        return mechsCalled;
    }

    public void setMechsCalled(double mechsCalled) {
        this.mechsCalled = mechsCalled;
    }

    public float getMechsCalledAvgPer10Min() {
        return mechsCalledAvgPer10Min;
    }

    public void setMechsCalledAvgPer10Min(float mechsCalledAvgPer10Min) {
        this.mechsCalledAvgPer10Min = mechsCalledAvgPer10Min;
    }

    public double getMechsCalledMostInGame() {
        return mechsCalledMostInGame;
    }

    public void setMechsCalledMostInGame(double mechsCalledMostInGame) {
        this.mechsCalledMostInGame = mechsCalledMostInGame;
    }

    public double getSelfDestructKills() {
        return selfDestructKills;
    }

    public void setSelfDestructKills(double selfDestructKills) {
        this.selfDestructKills = selfDestructKills;
    }

    public float getSelfDestructKillsAvgPer10Min() {
        return selfDestructKillsAvgPer10Min;
    }

    public void setSelfDestructKillsAvgPer10Min(float selfDestructKillsAvgPer10Min) {
        this.selfDestructKillsAvgPer10Min = selfDestructKillsAvgPer10Min;
    }

    public double getSelfDestructKillsMostInGame() {
        return selfDestructKillsMostInGame;
    }

    public void setSelfDestructKillsMostInGame(double selfDestructKillsMostInGame) {
        this.selfDestructKillsMostInGame = selfDestructKillsMostInGame;
    }

    public double getAbilityDamageDone() {
        return abilityDamageDone;
    }

    public void setAbilityDamageDone(double abilityDamageDone) {
        this.abilityDamageDone = abilityDamageDone;
    }

    public double getAbilityDamageDoneAvgPer10Min() {
        return abilityDamageDoneAvgPer10Min;
    }

    public void setAbilityDamageDoneAvgPer10Min(double abilityDamageDoneAvgPer10Min) {
        this.abilityDamageDoneAvgPer10Min = abilityDamageDoneAvgPer10Min;
    }

    public double getAbilityDamageDoneMostInGame() {
        return abilityDamageDoneMostInGame;
    }

    public void setAbilityDamageDoneMostInGame(double abilityDamageDoneMostInGame) {
        this.abilityDamageDoneMostInGame = abilityDamageDoneMostInGame;
    }

    public double getMeteorStrikeKills() {
        return meteorStrikeKills;
    }

    public void setMeteorStrikeKills(double meteorStrikeKills) {
        this.meteorStrikeKills = meteorStrikeKills;
    }

    public float getMeteorStrikeKillsAvgPer10Min() {
        return meteorStrikeKillsAvgPer10Min;
    }

    public void setMeteorStrikeKillsAvgPer10Min(float meteorStrikeKillsAvgPer10Min) {
        this.meteorStrikeKillsAvgPer10Min = meteorStrikeKillsAvgPer10Min;
    }

    public double getMeteorStrikeKillsMostInGame() {
        return meteorStrikeKillsMostInGame;
    }

    public void setMeteorStrikeKillsMostInGame(double meteorStrikeKillsMostInGame) {
        this.meteorStrikeKillsMostInGame = meteorStrikeKillsMostInGame;
    }

    public double getShieldsCreated() {
        return shieldsCreated;
    }

    public void setShieldsCreated(double shieldsCreated) {
        this.shieldsCreated = shieldsCreated;
    }

    public double getShieldsCreatedAvgPer10Min() {
        return shieldsCreatedAvgPer10Min;
    }

    public void setShieldsCreatedAvgPer10Min(double shieldsCreatedAvgPer10Min) {
        this.shieldsCreatedAvgPer10Min = shieldsCreatedAvgPer10Min;
    }

    public double getShieldsCreatedMostInGame() {
        return shieldsCreatedMostInGame;
    }

    public void setShieldsCreatedMostInGame(double shieldsCreatedMostInGame) {
        this.shieldsCreatedMostInGame = shieldsCreatedMostInGame;
    }

    public double getDamageReflected() {
        return damageReflected;
    }

    public void setDamageReflected(double damageReflected) {
        this.damageReflected = damageReflected;
    }

    public double getDamageReflectedAvgPer10Min() {
        return damageReflectedAvgPer10Min;
    }

    public void setDamageReflectedAvgPer10Min(double damageReflectedAvgPer10Min) {
        this.damageReflectedAvgPer10Min = damageReflectedAvgPer10Min;
    }

    public double getDamageReflectedMostInGame() {
        return damageReflectedMostInGame;
    }

    public void setDamageReflectedMostInGame(double damageReflectedMostInGame) {
        this.damageReflectedMostInGame = damageReflectedMostInGame;
    }

    public double getDeflectionKills() {
        return deflectionKills;
    }

    public void setDeflectionKills(double deflectionKills) {
        this.deflectionKills = deflectionKills;
    }

    public double getDragonbladesKills() {
        return dragonbladesKills;
    }

    public void setDragonbladesKills(double dragonbladesKills) {
        this.dragonbladesKills = dragonbladesKills;
    }

    public float getDragonbladesKillsAvgPer10Min() {
        return dragonbladesKillsAvgPer10Min;

    }

    public void setDragonbladesKillsAvgPer10Min(float dragonbladesKillsAvgPer10Min) {
        this.dragonbladesKillsAvgPer10Min = dragonbladesKillsAvgPer10Min;
    }

    public double getDragonbladesKillsMostInGame() {
        return dragonbladesKillsMostInGame;
    }

    public void setDragonbladesKillsMostInGame(double dragonbladesKillsMostInGame) {
        this.dragonbladesKillsMostInGame = dragonbladesKillsMostInGame;
    }

    public double getSoundBarriersProvided() {
        return soundBarriersProvided;
    }

    public void setSoundBarriersProvided(double soundBarriersProvided) {
        this.soundBarriersProvided = soundBarriersProvided;
    }

    public float getSoundBarriersProvidedAvgPer10Min() {
        return soundBarriersProvidedAvgPer10Min;
    }

    public void setSoundBarriersProvidedAvgPer10Min(float soundBarriersProvidedAvgPer10Min) {
        this.soundBarriersProvidedAvgPer10Min = soundBarriersProvidedAvgPer10Min;
    }

    public double getSoundBarriersProvidedMostInGame() {
        return soundBarriersProvidedMostInGame;
    }

    public void setSoundBarriersProvidedMostInGame(double soundBarriersProvidedMostInGame) {
        this.soundBarriersProvidedMostInGame = soundBarriersProvidedMostInGame;
    }

    public double getDeadeyeKills() {
        return deadeyeKills;
    }

    public void setDeadeyeKills(double deadeyeKills) {
        this.deadeyeKills = deadeyeKills;
    }

    public float getDeadeyeKillsAvgPer10Min() {
        return deadeyeKillsAvgPer10Min;
    }

    public void setDeadeyeKillsAvgPer10Min(float deadeyeKillsAvgPer10Min) {
        this.deadeyeKillsAvgPer10Min = deadeyeKillsAvgPer10Min;
    }

    public double getDeadeyeKillsMostInGame() {
        return deadeyeKillsMostInGame;
    }

    public void setDeadeyeKillsMostInGame(double deadeyeKillsMostInGame) {
        this.deadeyeKillsMostInGame = deadeyeKillsMostInGame;
    }

    public double getFanTheHammerKills() {
        return fanTheHammerKills;
    }

    public void setFanTheHammerKills(double fanTheHammerKills) {
        this.fanTheHammerKills = fanTheHammerKills;
    }

    public float getFanTheHammerKillsAvgPer10Min() {
        return fanTheHammerKillsAvgPer10Min;
    }

    public void setFanTheHammerKillsAvgPer10Min(float fanTheHammerKillsAvgPer10Min) {
        this.fanTheHammerKillsAvgPer10Min = fanTheHammerKillsAvgPer10Min;
    }

    public double getFanTheHammerKillsMostInGame() {
        return fanTheHammerKillsMostInGame;
    }

    public void setFanTheHammerKillsMostInGame(double fanTheHammerKillsMostInGame) {
        this.fanTheHammerKillsMostInGame = fanTheHammerKillsMostInGame;
    }

    public double getBlizzardKills() {
        return blizzardKills;
    }

    public void setBlizzardKills(double blizzardKills) {
        this.blizzardKills = blizzardKills;
    }

    public float getBlizzardKillsAvgPer10Min() {
        return blizzardKillsAvgPer10Min;
    }

    public void setBlizzardKillsAvgPer10Min(float blizzardKillsAvgPer10Min) {
        this.blizzardKillsAvgPer10Min = blizzardKillsAvgPer10Min;
    }

    public double getBlizzardKillsMostInGame() {
        return blizzardKillsMostInGame;
    }

    public void setBlizzardKillsMostInGame(double blizzardKillsMostInGame) {
        this.blizzardKillsMostInGame = blizzardKillsMostInGame;
    }

    public double getEnemiesFrozen() {
        return enemiesFrozen;
    }

    public void setEnemiesFrozen(double enemiesFrozen) {
        this.enemiesFrozen = enemiesFrozen;
    }

    public float getEnemiesFrozenAvgPer10Min() {
        return enemiesFrozenAvgPer10Min;
    }

    public void setEnemiesFrozenAvgPer10Min(float enemiesFrozenAvgPer10Min) {
        this.enemiesFrozenAvgPer10Min = enemiesFrozenAvgPer10Min;
    }

    public double getEnemiesFrozenMostInGame() {
        return enemiesFrozenMostInGame;
    }

    public void setEnemiesFrozenMostInGame(double enemiesFrozenMostInGame) {
        this.enemiesFrozenMostInGame = enemiesFrozenMostInGame;
    }

    public double getBlasterKills() {
        return blasterKills;
    }

    public void setBlasterKills(double blasterKills) {
        this.blasterKills = blasterKills;
    }

    public float getBlasterKillsAvgPer10Min() {
        return blasterKillsAvgPer10Min;
    }

    public void setBlasterKillsAvgPer10Min(float blasterKillsAvgPer10Min) {
        this.blasterKillsAvgPer10Min = blasterKillsAvgPer10Min;
    }

    public double getBlasterKillsMostInGame() {
        return blasterKillsMostInGame;
    }

    public void setBlasterKillsMostInGame(double blasterKillsMostInGame) {
        this.blasterKillsMostInGame = blasterKillsMostInGame;
    }

    public double getPlayersResurrected() {
        return playersResurrected;
    }

    public void setPlayersResurrected(double playersResurrected) {
        this.playersResurrected = playersResurrected;
    }

    public float getPlayersResurrectedAvgPer10Min() {
        return playersResurrectedAvgPer10Min;
    }

    public void setPlayersResurrectedAvgPer10Min(float playersResurrectedAvgPer10Min) {
        this.playersResurrectedAvgPer10Min = playersResurrectedAvgPer10Min;
    }

    public double getPlayersResurrectedMostInGame() {
        return playersResurrectedMostInGame;
    }

    public void setPlayersResurrectedMostInGame(double playersResurrectedMostInGame) {
        this.playersResurrectedMostInGame = playersResurrectedMostInGame;
    }

    public double getCoalescenceHealing() {
        return coalescenceHealing;
    }

    public void setCoalescenceHealing(double coalescenceHealing) {
        this.coalescenceHealing = coalescenceHealing;
    }

    public double getCoalescenceHealingAvgPer10Min() {
        return coalescenceHealingAvgPer10Min;
    }

    public void setCoalescenceHealingAvgPer10Min(double coalescenceHealingAvgPer10Min) {
        this.coalescenceHealingAvgPer10Min = coalescenceHealingAvgPer10Min;
    }

    public double getCoalescenceHealingMostInGame() {
        return coalescenceHealingMostInGame;
    }

    public void setCoalescenceHealingMostInGame(double coalescenceHealingMostInGame) {
        this.coalescenceHealingMostInGame = coalescenceHealingMostInGame;
    }

    public double getCoalescenceKills() {
        return coalescenceKills;
    }

    public void setCoalescenceKills(double coalescenceKills) {
        this.coalescenceKills = coalescenceKills;
    }

    public float getCoalescenceKillsAvgPer10Min() {
        return coalescenceKillsAvgPer10Min;
    }

    public void setCoalescenceKillsAvgPer10Min(float coalescenceKillsAvgPer10Min) {
        this.coalescenceKillsAvgPer10Min = coalescenceKillsAvgPer10Min;
    }

    public double getCoalescenceKillsMostInGame() {
        return coalescenceKillsMostInGame;
    }

    public void setCoalescenceKillsMostInGame(double coalescenceKillsMostInGame) {
        this.coalescenceKillsMostInGame = coalescenceKillsMostInGame;
    }

    public String getSecondaryFireAccuracy() {
        return secondaryFireAccuracy;
    }

    public void setSecondaryFireAccuracy(String secondaryFireAccuracy) {
        this.secondaryFireAccuracy = secondaryFireAccuracy;
    }

    public double getSuperchargerAssists() {
        return superchargerAssists;
    }

    public void setSuperchargerAssists(double superchargerAssists) {
        this.superchargerAssists = superchargerAssists;
    }

    public float getSuperchargerAssistsAvgPer10Min() {
        return superchargerAssistsAvgPer10Min;
    }

    public void setSuperchargerAssistsAvgPer10Min(float superchargerAssistsAvgPer10Min) {
        this.superchargerAssistsAvgPer10Min = superchargerAssistsAvgPer10Min;
    }

    public double getSuperchargerAssistsMostInGame() {
        return superchargerAssistsMostInGame;
    }

    public void setSuperchargerAssistsMostInGame(double superchargerAssistsMostInGame) {
        this.superchargerAssistsMostInGame = superchargerAssistsMostInGame;
    }

    public double getBarrageKills() {
        return barrageKills;
    }

    public void setBarrageKills(double barrageKills) {
        this.barrageKills = barrageKills;
    }

    public float getBarrageKillsAvgPer10Min() {
        return barrageKillsAvgPer10Min;
    }

    public void setBarrageKillsAvgPer10Min(float barrageKillsAvgPer10Min) {
        this.barrageKillsAvgPer10Min = barrageKillsAvgPer10Min;
    }

    public double getBarrageKillsMostInGame() {
        return barrageKillsMostInGame;
    }

    public void setBarrageKillsMostInGame(double barrageKillsMostInGame) {
        this.barrageKillsMostInGame = barrageKillsMostInGame;
    }

    public String getDirectHitsAccuracy() {
        return directHitsAccuracy;
    }

    public void setDirectHitsAccuracy(String directHitsAccuracy) {
        this.directHitsAccuracy = directHitsAccuracy;
    }

    public double getRocketDirectHits() {
        return rocketDirectHits;
    }

    public void setRocketDirectHits(double rocketDirectHits) {
        this.rocketDirectHits = rocketDirectHits;
    }

    public float getRocketDirectHitsAvgPer10Min() {
        return rocketDirectHitsAvgPer10Min;
    }

    public void setRocketDirectHitsAvgPer10Min(float rocketDirectHitsAvgPer10Min) {
        this.rocketDirectHitsAvgPer10Min = rocketDirectHitsAvgPer10Min;
    }

    public double getRocketDirectHitsMostInGame() {
        return rocketDirectHitsMostInGame;
    }

    public void setRocketDirectHitsMostInGame(double rocketDirectHitsMostInGame) {
        this.rocketDirectHitsMostInGame = rocketDirectHitsMostInGame;
    }

    public double getDeathsBlossomKills() {
        return deathsBlossomKills;
    }

    public void setDeathsBlossomKills(double deathsBlossomKills) {
        this.deathsBlossomKills = deathsBlossomKills;
    }

    public float getDeathsBlossomKillsAvgPer10Min() {
        return deathsBlossomKillsAvgPer10Min;
    }

    public void setDeathsBlossomKillsAvgPer10Min(float deathsBlossomKillsAvgPer10Min) {
        this.deathsBlossomKillsAvgPer10Min = deathsBlossomKillsAvgPer10Min;
    }

    public double getDeathsBlossomKillsMostInGame() {
        return deathsBlossomKillsMostInGame;
    }

    public void setDeathsBlossomKillsMostInGame(double deathsBlossomKillsMostInGame) {
        this.deathsBlossomKillsMostInGame = deathsBlossomKillsMostInGame;
    }

    public double getChargeKills() {
        return chargeKills;
    }

    public void setChargeKills(double chargeKills) {
        this.chargeKills = chargeKills;
    }

    public float getChargeKillsAvgPer10Min() {
        return chargeKillsAvgPer10Min;
    }

    public void setChargeKillsAvgPer10Min(float chargeKillsAvgPer10Min) {
        this.chargeKillsAvgPer10Min = chargeKillsAvgPer10Min;
    }

    public double getChargeKillsMostInGame() {
        return chargeKillsMostInGame;
    }

    public void setChargeKillsMostInGame(double chargeKillsMostInGame) {
        this.chargeKillsMostInGame = chargeKillsMostInGame;
    }

    public double getEarthshatterKills() {
        return earthshatterKills;
    }

    public void setEarthshatterKills(double earthshatterKills) {
        this.earthshatterKills = earthshatterKills;
    }

    public float getEarthshatterKillsAvgPer10Min() {
        return earthshatterKillsAvgPer10Min;
    }

    public void setEarthshatterKillsAvgPer10Min(float earthshatterKillsAvgPer10Min) {
        this.earthshatterKillsAvgPer10Min = earthshatterKillsAvgPer10Min;
    }

    public double getEarthshatterKillsMostInGame() {
        return earthshatterKillsMostInGame;
    }

    public void setEarthshatterKillsMostInGame(double earthshatterKillsMostInGame) {
        this.earthshatterKillsMostInGame = earthshatterKillsMostInGame;
    }

    public double getFireStrikeKills() {
        return fireStrikeKills;
    }

    public void setFireStrikeKills(double fireStrikeKills) {
        this.fireStrikeKills = fireStrikeKills;
    }

    public float getFireStrikeKillsAvgPer10Min() {
        return fireStrikeKillsAvgPer10Min;
    }

    public void setFireStrikeKillsAvgPer10Min(float fireStrikeKillsAvgPer10Min) {
        this.fireStrikeKillsAvgPer10Min = fireStrikeKillsAvgPer10Min;
    }

    public double getFireStrikeKillsMostInGame() {
        return fireStrikeKillsMostInGame;
    }

    public void setFireStrikeKillsMostInGame(double fireStrikeKillsMostInGame) {
        this.fireStrikeKillsMostInGame = fireStrikeKillsMostInGame;
    }

    public String getRocketHammerMeleeAccuracy() {
        return rocketHammerMeleeAccuracy;
    }

    public void setRocketHammerMeleeAccuracy(String rocketHammerMeleeAccuracy) {
        this.rocketHammerMeleeAccuracy = rocketHammerMeleeAccuracy;
    }

    public double getEnemiesHooked() {
        return enemiesHooked;
    }

    public void setEnemiesHooked(double enemiesHooked) {
        this.enemiesHooked = enemiesHooked;
    }

    public float getEnemiesHookedAvgPer10Min() {
        return enemiesHookedAvgPer10Min;
    }

    public void setEnemiesHookedAvgPer10Min(float enemiesHookedAvgPer10Min) {
        this.enemiesHookedAvgPer10Min = enemiesHookedAvgPer10Min;
    }

    public double getEnemiesHookedMostInGame() {
        return enemiesHookedMostInGame;
    }

    public void setEnemiesHookedMostInGame(double enemiesHookedMostInGame) {
        this.enemiesHookedMostInGame = enemiesHookedMostInGame;
    }

    public String getHookAccuracy() {
        return hookAccuracy;
    }

    public void setHookAccuracy(String hookAccuracy) {
        this.hookAccuracy = hookAccuracy;
    }

    public String getHookAccuracyBestInGame() {
        return hookAccuracyBestInGame;
    }

    public void setHookAccuracyBestInGame(String hookAccuracyBestInGame) {
        this.hookAccuracyBestInGame = hookAccuracyBestInGame;
    }

    public double getHooksAttempted() {
        return hooksAttempted;
    }

    public void setHooksAttempted(double hooksAttempted) {
        this.hooksAttempted = hooksAttempted;
    }

    public double getWholeHogKills() {
        return wholeHogKills;
    }

    public void setWholeHogKills(double wholeHogKills) {
        this.wholeHogKills = wholeHogKills;
    }

    public float getWholeHogKillsAvgPer10Min() {
        return wholeHogKillsAvgPer10Min;
    }

    public void setWholeHogKillsAvgPer10Min(float wholeHogKillsAvgPer10Min) {
        this.wholeHogKillsAvgPer10Min = wholeHogKillsAvgPer10Min;
    }

    public double getWholeHogKillsMostInGame() {
        return wholeHogKillsMostInGame;
    }

    public void setWholeHogKillsMostInGame(double wholeHogKillsMostInGame) {
        this.wholeHogKillsMostInGame = wholeHogKillsMostInGame;
    }

    public double getBioticFieldHealingDone() {
        return bioticFieldHealingDone;
    }

    public void setBioticFieldHealingDone(double bioticFieldHealingDone) {
        this.bioticFieldHealingDone = bioticFieldHealingDone;
    }

    public double getBioticFieldsDeployed() {
        return bioticFieldsDeployed;
    }

    public void setBioticFieldsDeployed(double bioticFieldsDeployed) {
        this.bioticFieldsDeployed = bioticFieldsDeployed;
    }

    public double getHelixRocketsKills() {
        return helixRocketsKills;
    }

    public void setHelixRocketsKills(double helixRocketsKills) {
        this.helixRocketsKills = helixRocketsKills;
    }

    public float getHelixRocketsKillsAvgPer10Min() {
        return helixRocketsKillsAvgPer10Min;
    }

    public void setHelixRocketsKillsAvgPer10Min(float helixRocketsKillsAvgPer10Min) {
        this.helixRocketsKillsAvgPer10Min = helixRocketsKillsAvgPer10Min;
    }

    public double getHelixRocketsKillsMostInGame() {
        return helixRocketsKillsMostInGame;
    }

    public void setHelixRocketsKillsMostInGame(double helixRocketsKillsMostInGame) {
        this.helixRocketsKillsMostInGame = helixRocketsKillsMostInGame;
    }

    public double getTacticalVisorKills() {
        return tacticalVisorKills;
    }

    public void setTacticalVisorKills(double tacticalVisorKills) {
        this.tacticalVisorKills = tacticalVisorKills;
    }

    public float getTacticalVisorKillsAvgPer10Min() {
        return tacticalVisorKillsAvgPer10Min;
    }

    public void setTacticalVisorKillsAvgPer10Min(float tacticalVisorKillsAvgPer10Min) {
        this.tacticalVisorKillsAvgPer10Min = tacticalVisorKillsAvgPer10Min;
    }

    public double getTacticalVisorKillsMostInGame() {
        return tacticalVisorKillsMostInGame;
    }

    public void setTacticalVisorKillsMostInGame(double tacticalVisorKillsMostInGame) {
        this.tacticalVisorKillsMostInGame = tacticalVisorKillsMostInGame;
    }

    public double getEnemiesEmpd() {
        return enemiesEmpd;
    }

    public void setEnemiesEmpd(double enemiesEmpd) {
        this.enemiesEmpd = enemiesEmpd;
    }

    public float getEnemiesEmpdAvgPer10Min() {
        return enemiesEmpdAvgPer10Min;
    }

    public void setEnemiesEmpdAvgPer10Min(float enemiesEmpdAvgPer10Min) {
        this.enemiesEmpdAvgPer10Min = enemiesEmpdAvgPer10Min;
    }

    public double getEnemiesEmpdMostInGame() {
        return enemiesEmpdMostInGame;
    }

    public void setEnemiesEmpdMostInGame(double enemiesEmpdMostInGame) {
        this.enemiesEmpdMostInGame = enemiesEmpdMostInGame;
    }

    public double getEnemiesHacked() {
        return enemiesHacked;
    }

    public void setEnemiesHacked(double enemiesHacked) {
        this.enemiesHacked = enemiesHacked;
    }

    public float getEnemiesHackedAvgPer10Min() {
        return enemiesHackedAvgPer10Min;
    }

    public void setEnemiesHackedAvgPer10Min(float enemiesHackedAvgPer10Min) {
        this.enemiesHackedAvgPer10Min = enemiesHackedAvgPer10Min;
    }

    public double getEnemiesHackedMostInGame() {
        return enemiesHackedMostInGame;
    }

    public void setEnemiesHackedMostInGame(double enemiesHackedMostInGame) {
        this.enemiesHackedMostInGame = enemiesHackedMostInGame;
    }

    public double getPlayersTeleported() {
        return playersTeleported;
    }

    public void setPlayersTeleported(double playersTeleported) {
        this.playersTeleported = playersTeleported;
    }

    public float getPlayersTeleportedAvgPer10Min() {
        return playersTeleportedAvgPer10Min;
    }

    public void setPlayersTeleportedAvgPer10Min(float playersTeleportedAvgPer10Min) {
        this.playersTeleportedAvgPer10Min = playersTeleportedAvgPer10Min;
    }

    public double getPlayersTeleportedMostInGame() {
        return playersTeleportedMostInGame;
    }

    public void setPlayersTeleportedMostInGame(double playersTeleportedMostInGame) {
        this.playersTeleportedMostInGame = playersTeleportedMostInGame;
    }

    public String getPrimaryFireAccuracy() {
        return primaryFireAccuracy;
    }

    public void setPrimaryFireAccuracy(String primaryFireAccuracy) {
        this.primaryFireAccuracy = primaryFireAccuracy;
    }

    public float getSecondaryDirectHitsAvgPer10Min() {
        return secondaryDirectHitsAvgPer10Min;
    }

    public void setSecondaryDirectHitsAvgPer10Min(float secondaryDirectHitsAvgPer10Min) {
        this.secondaryDirectHitsAvgPer10Min = secondaryDirectHitsAvgPer10Min;
    }

    public double getSentryTurretsKills() {
        return sentryTurretsKills;
    }

    public void setSentryTurretsKills(double sentryTurretsKills) {
        this.sentryTurretsKills = sentryTurretsKills;
    }

    public float getSentryTurretsKillsAvgPer10Min() {
        return sentryTurretsKillsAvgPer10Min;
    }

    public void setSentryTurretsKillsAvgPer10Min(float sentryTurretsKillsAvgPer10Min) {
        this.sentryTurretsKillsAvgPer10Min = sentryTurretsKillsAvgPer10Min;
    }

    public double getSentryTurretsKillsMostInGame() {
        return sentryTurretsKillsMostInGame;
    }

    public void setSentryTurretsKillsMostInGame(double sentryTurretsKillsMostInGame) {
        this.sentryTurretsKillsMostInGame = sentryTurretsKillsMostInGame;
    }

    public double getArmorPacksCreated() {
        return armorPacksCreated;
    }

    public void setArmorPacksCreated(double armorPacksCreated) {
        this.armorPacksCreated = armorPacksCreated;
    }

    public float getArmorPacksCreatedAvgPer10Min() {
        return armorPacksCreatedAvgPer10Min;
    }

    public void setArmorPacksCreatedAvgPer10Min(float armorPacksCreatedAvgPer10Min) {
        this.armorPacksCreatedAvgPer10Min = armorPacksCreatedAvgPer10Min;
    }

    public double getArmorPacksCreatedMostInGame() {
        return armorPacksCreatedMostInGame;
    }

    public void setArmorPacksCreatedMostInGame(double armorPacksCreatedMostInGame) {
        this.armorPacksCreatedMostInGame = armorPacksCreatedMostInGame;
    }

    public double getMoltenCoreKills() {
        return moltenCoreKills;
    }

    public void setMoltenCoreKills(double moltenCoreKills) {
        this.moltenCoreKills = moltenCoreKills;
    }

    public float getMoltenCoreKillsAvgPer10Min() {
        return moltenCoreKillsAvgPer10Min;
    }

    public void setMoltenCoreKillsAvgPer10Min(float moltenCoreKillsAvgPer10Min) {
        this.moltenCoreKillsAvgPer10Min = moltenCoreKillsAvgPer10Min;
    }

    public double getMoltenCoreKillsMostInGame() {
        return moltenCoreKillsMostInGame;
    }

    public void setMoltenCoreKillsMostInGame(double moltenCoreKillsMostInGame) {
        this.moltenCoreKillsMostInGame = moltenCoreKillsMostInGame;
    }

    public double getOverloadKills() {
        return overloadKills;
    }

    public void setOverloadKills(double overloadKills) {
        this.overloadKills = overloadKills;
    }

    public double getOverloadKillsMostInGame() {
        return overloadKillsMostInGame;
    }

    public void setOverloadKillsMostInGame(double overloadKillsMostInGame) {
        this.overloadKillsMostInGame = overloadKillsMostInGame;
    }

    public double getTorbjornKills() {
        return torbjornKills;
    }

    public void setTorbjornKills(double torbjornKills) {
        this.torbjornKills = torbjornKills;
    }

    public float getTorbjornKillsAvgPer10Min() {
        return torbjornKillsAvgPer10Min;
    }

    public void setTorbjornKillsAvgPer10Min(float torbjornKillsAvgPer10Min) {
        this.torbjornKillsAvgPer10Min = torbjornKillsAvgPer10Min;
    }

    public double getTorbjornKillsMostInGame() {
        return torbjornKillsMostInGame;
    }

    public void setTorbjornKillsMostInGame(double torbjornKillsMostInGame) {
        this.torbjornKillsMostInGame = torbjornKillsMostInGame;
    }

    public double getTurretsDamageAvgPer10Min() {
        return turretsDamageAvgPer10Min;
    }

    public void setTurretsDamageAvgPer10Min(double turretsDamageAvgPer10Min) {
        this.turretsDamageAvgPer10Min = turretsDamageAvgPer10Min;
    }

    public double getTurretsKills() {
        return turretsKills;
    }

    public void setTurretsKills(double turretsKills) {
        this.turretsKills = turretsKills;
    }

    public float getTurretsKillsAvgPer10Min() {
        return turretsKillsAvgPer10Min;
    }

    public void setTurretsKillsAvgPer10Min(float turretsKillsAvgPer10Min) {
        this.turretsKillsAvgPer10Min = turretsKillsAvgPer10Min;
    }

    public double getTurretsKillsMostInGame() {
        return turretsKillsMostInGame;
    }

    public void setTurretsKillsMostInGame(double turretsKillsMostInGame) {
        this.turretsKillsMostInGame = turretsKillsMostInGame;
    }

    public double getHealthRecovered() {
        return healthRecovered;
    }

    public void setHealthRecovered(double healthRecovered) {
        this.healthRecovered = healthRecovered;
    }

    public double getHealthRecoveredAvgPer10Min() {
        return healthRecoveredAvgPer10Min;
    }

    public void setHealthRecoveredAvgPer10Min(double healthRecoveredAvgPer10Min) {
        this.healthRecoveredAvgPer10Min = healthRecoveredAvgPer10Min;
    }

    public double getHealthRecoveredMostInGame() {
        return healthRecoveredMostInGame;
    }

    public void setHealthRecoveredMostInGame(double healthRecoveredMostInGame) {
        this.healthRecoveredMostInGame = healthRecoveredMostInGame;
    }

    public double getPulseBombsAttached() {
        return pulseBombsAttached;
    }

    public void setPulseBombsAttached(double pulseBombsAttached) {
        this.pulseBombsAttached = pulseBombsAttached;
    }

    public float getPulseBombsAttachedAvgPer10Min() {
        return pulseBombsAttachedAvgPer10Min;
    }

    public void setPulseBombsAttachedAvgPer10Min(float pulseBombsAttachedAvgPer10Min) {
        this.pulseBombsAttachedAvgPer10Min = pulseBombsAttachedAvgPer10Min;
    }

    public double getPulseBombsAttachedMostInGame() {
        return pulseBombsAttachedMostInGame;
    }

    public void setPulseBombsAttachedMostInGame(double pulseBombsAttachedMostInGame) {
        this.pulseBombsAttachedMostInGame = pulseBombsAttachedMostInGame;
    }

    public double getPulseBombsKills() {
        return pulseBombsKills;
    }

    public void setPulseBombsKills(double pulseBombsKills) {
        this.pulseBombsKills = pulseBombsKills;
    }

    public float getPulseBombsKillsAvgPer10Min() {
        return pulseBombsKillsAvgPer10Min;
    }

    public void setPulseBombsKillsAvgPer10Min(float pulseBombsKillsAvgPer10Min) {
        this.pulseBombsKillsAvgPer10Min = pulseBombsKillsAvgPer10Min;
    }

    public double getPulseBombsKillsMostInGame() {
        return pulseBombsKillsMostInGame;
    }

    public void setPulseBombsKillsMostInGame(double pulseBombsKillsMostInGame) {
        this.pulseBombsKillsMostInGame = pulseBombsKillsMostInGame;
    }

    public double getVenomMineKills() {
        return venomMineKills;
    }

    public void setVenomMineKills(double venomMineKills) {
        this.venomMineKills = venomMineKills;
    }

    public float getVenomMineKillsAvgPer10Min() {
        return venomMineKillsAvgPer10Min;
    }

    public void setVenomMineKillsAvgPer10Min(float venomMineKillsAvgPer10Min) {
        this.venomMineKillsAvgPer10Min = venomMineKillsAvgPer10Min;
    }

    public double getVenomMineKillsMostInGame() {
        return venomMineKillsMostInGame;
    }

    public void setVenomMineKillsMostInGame(double venomMineKillsMostInGame) {
        this.venomMineKillsMostInGame = venomMineKillsMostInGame;
    }

    public double getJumpKills() {
        return jumpKills;
    }

    public void setJumpKills(double jumpKills) {
        this.jumpKills = jumpKills;
    }

    public double getJumpPackKills() {
        return jumpPackKills;
    }

    public void setJumpPackKills(double jumpPackKills) {
        this.jumpPackKills = jumpPackKills;
    }

    public float getJumpPackKillsAvgPer10Min() {
        return jumpPackKillsAvgPer10Min;
    }

    public void setJumpPackKillsAvgPer10Min(float jumpPackKillsAvgPer10Min) {
        this.jumpPackKillsAvgPer10Min = jumpPackKillsAvgPer10Min;
    }

    public double getJumpPackKillsMostInGame() {
        return jumpPackKillsMostInGame;
    }

    public void setJumpPackKillsMostInGame(double jumpPackKillsMostInGame) {
        this.jumpPackKillsMostInGame = jumpPackKillsMostInGame;
    }

    public double getMeleeKills() {
        return meleeKills;
    }

    public void setMeleeKills(double meleeKills) {
        this.meleeKills = meleeKills;
    }

    public float getMeleeKillsAvgPer10Min() {
        return meleeKillsAvgPer10Min;
    }

    public void setMeleeKillsAvgPer10Min(float meleeKillsAvgPer10Min) {
        this.meleeKillsAvgPer10Min = meleeKillsAvgPer10Min;
    }

    public double getMeleeKillsMostInGame() {
        return meleeKillsMostInGame;
    }

    public void setMeleeKillsMostInGame(double meleeKillsMostInGame) {
        this.meleeKillsMostInGame = meleeKillsMostInGame;
    }

    public double getPlayersKnockedBack() {
        return playersKnockedBack;
    }

    public void setPlayersKnockedBack(double playersKnockedBack) {
        this.playersKnockedBack = playersKnockedBack;
    }

    public float getPlayersKnockedBackAvgPer10Min() {
        return playersKnockedBackAvgPer10Min;
    }

    public void setPlayersKnockedBackAvgPer10Min(float playersKnockedBackAvgPer10Min) {
        this.playersKnockedBackAvgPer10Min = playersKnockedBackAvgPer10Min;
    }

    public double getPlayersKnockedBackMostInGame() {
        return playersKnockedBackMostInGame;
    }

    public void setPlayersKnockedBackMostInGame(double playersKnockedBackMostInGame) {
        this.playersKnockedBackMostInGame = playersKnockedBackMostInGame;
    }

    public double getPrimalRageKills() {
        return primalRageKills;
    }

    public void setPrimalRageKills(double primalRageKills) {
        this.primalRageKills = primalRageKills;
    }

    public float getPrimalRageKillsAvgPer10Min() {
        return primalRageKillsAvgPer10Min;
    }

    public void setPrimalRageKillsAvgPer10Min(float primalRageKillsAvgPer10Min) {
        this.primalRageKillsAvgPer10Min = primalRageKillsAvgPer10Min;
    }

    public double getPrimalRageKillsMostInGame() {
        return primalRageKillsMostInGame;
    }

    public void setPrimalRageKillsMostInGame(double primalRageKillsMostInGame) {
        this.primalRageKillsMostInGame = primalRageKillsMostInGame;
    }

    public String getPrimalRageMeleeAccuracy() {
        return primalRageMeleeAccuracy;
    }

    public void setPrimalRageMeleeAccuracy(String primalRageMeleeAccuracy) {
        this.primalRageMeleeAccuracy = primalRageMeleeAccuracy;
    }

    public String getTeslaCannonAccuracy() {
        return teslaCannonAccuracy;
    }

    public void setTeslaCannonAccuracy(String teslaCannonAccuracy) {
        this.teslaCannonAccuracy = teslaCannonAccuracy;
    }

    public double getWeaponKills() {
        return weaponKills;
    }

    public void setWeaponKills(double weaponKills) {
        this.weaponKills = weaponKills;
    }

    public double getGrapplingClawKills() {
        return grapplingClawKills;
    }

    public void setGrapplingClawKills(double grapplingClawKills) {
        this.grapplingClawKills = grapplingClawKills;
    }

    public float getGrapplingClawKillsAvgPer10Min() {
        return grapplingClawKillsAvgPer10Min;
    }

    public void setGrapplingClawKillsAvgPer10Min(float grapplingClawKillsAvgPer10Min) {
        this.grapplingClawKillsAvgPer10Min = grapplingClawKillsAvgPer10Min;
    }

    public double getGrapplingClawKillsMostInGame() {
        return grapplingClawKillsMostInGame;
    }

    public void setGrapplingClawKillsMostInGame(double grapplingClawKillsMostInGame) {
        this.grapplingClawKillsMostInGame = grapplingClawKillsMostInGame;
    }

    public double getMinefieldKills() {
        return minefieldKills;
    }

    public void setMinefieldKills(double minefieldKills) {
        this.minefieldKills = minefieldKills;
    }

    public float getMinefieldKillsAvgPer10Min() {
        return minefieldKillsAvgPer10Min;
    }

    public void setMinefieldKillsAvgPer10Min(float minefieldKillsAvgPer10Min) {
        this.minefieldKillsAvgPer10Min = minefieldKillsAvgPer10Min;
    }

    public double getMinefieldKillsMostInGame() {
        return minefieldKillsMostInGame;
    }

    public void setMinefieldKillsMostInGame(double minefieldKillsMostInGame) {
        this.minefieldKillsMostInGame = minefieldKillsMostInGame;
    }

    public String getAverageEnergy() {
        return averageEnergy;
    }

    public void setAverageEnergy(String averageEnergy) {
        this.averageEnergy = averageEnergy;
    }

    public String getAverageEnergyBestInGame() {
        return averageEnergyBestInGame;
    }

    public void setAverageEnergyBestInGame(String averageEnergyBestInGame) {
        this.averageEnergyBestInGame = averageEnergyBestInGame;
    }

    public double getGravitonSurgeKills() {
        return gravitonSurgeKills;
    }

    public void setGravitonSurgeKills(double gravitonSurgeKills) {
        this.gravitonSurgeKills = gravitonSurgeKills;
    }

    public float getGravitonSurgeKillsAvgPer10Min() {
        return gravitonSurgeKillsAvgPer10Min;
    }

    public void setGravitonSurgeKillsAvgPer10Min(float gravitonSurgeKillsAvgPer10Min) {
        this.gravitonSurgeKillsAvgPer10Min = gravitonSurgeKillsAvgPer10Min;
    }

    public double getGravitonSurgeKillsMostInGame() {
        return gravitonSurgeKillsMostInGame;
    }

    public void setGravitonSurgeKillsMostInGame(double gravitonSurgeKillsMostInGame) {
        this.gravitonSurgeKillsMostInGame = gravitonSurgeKillsMostInGame;
    }

    public double getHighEnergyKills() {
        return highEnergyKills;
    }

    public void setHighEnergyKills(double highEnergyKills) {
        this.highEnergyKills = highEnergyKills;
    }

    public float getHighEnergyKillsAvgPer10Min() {
        return highEnergyKillsAvgPer10Min;
    }

    public void setHighEnergyKillsAvgPer10Min(float highEnergyKillsAvgPer10Min) {
        this.highEnergyKillsAvgPer10Min = highEnergyKillsAvgPer10Min;
    }

    public double getHighEnergyKillsMostInGame() {
        return highEnergyKillsMostInGame;
    }

    public void setHighEnergyKillsMostInGame(double highEnergyKillsMostInGame) {
        this.highEnergyKillsMostInGame = highEnergyKillsMostInGame;
    }

    public double getProjectedBarriersApplied() {
        return projectedBarriersApplied;
    }

    public void setProjectedBarriersApplied(double projectedBarriersApplied) {
        this.projectedBarriersApplied = projectedBarriersApplied;
    }

    public float getProjectedBarriersAppliedAvgPer10Min() {
        return projectedBarriersAppliedAvgPer10Min;
    }

    public void setProjectedBarriersAppliedAvgPer10Min(float projectedBarriersAppliedAvgPer10Min) {
        this.projectedBarriersAppliedAvgPer10Min = projectedBarriersAppliedAvgPer10Min;
    }

    public double getProjectedBarriersAppliedMostInGame() {
        return projectedBarriersAppliedMostInGame;
    }

    public void setProjectedBarriersAppliedMostInGame(double projectedBarriersAppliedMostInGame) {
        this.projectedBarriersAppliedMostInGame = projectedBarriersAppliedMostInGame;
    }

    public double getTranscendenceHealing() {
        return transcendenceHealing;
    }

    public void setTranscendenceHealing(double transcendenceHealing) {
        this.transcendenceHealing = transcendenceHealing;
    }

    public double getTranscendenceHealingBest() {
        return transcendenceHealingBest;
    }

    public void setTranscendenceHealingBest(double transcendenceHealingBest) {
        this.transcendenceHealingBest = transcendenceHealingBest;
    }
}
