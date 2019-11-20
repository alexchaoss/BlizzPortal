package com.BlizzardArmory.overwatch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeroSpecific {

    @SerializedName("enemiesTrapped")
    @Expose
    private int enemiesTrapped;
    @SerializedName("enemiesTrappedAvgPer10Min")
    @Expose
    private float enemiesTrappedAvgPer10Min;
    @SerializedName("enemiesTrappedMostInGame")
    @Expose
    private int enemiesTrappedMostInGame;
    @SerializedName("ripTireKills")
    @Expose
    private int ripTireKills;
    @SerializedName("ripTireKillsAvgPer10Min")
    @Expose
    private float ripTireKillsAvgPer10Min;
    @SerializedName("ripTireKillsMostInGame")
    @Expose
    private int ripTireKillsMostInGame;
    @SerializedName("dragonstrikeKills")
    @Expose
    private int dragonstrikeKills;
    @SerializedName("dragonstrikeKillsAvgPer10Min")
    @Expose
    private float dragonstrikeKillsAvgPer10Min;
    @SerializedName("dragonstrikeKillsMostInGame")
    @Expose
    private int dragonstrikeKillsMostInGame;
    @SerializedName("scatterArrowKills")
    @Expose
    private int scatterArrowKills;
    @SerializedName("scatterArrowKillsAvgPer10Min")
    @Expose
    private float scatterArrowKillsAvgPer10Min;
    @SerializedName("scatterArrowKillsMostInGame")
    @Expose
    private int scatterArrowKillsMostInGame;
    @SerializedName("stormArrowKills")
    @Expose
    private int stormArrowKills;
    @SerializedName("stormArrowKillsAvgPer10Min")
    @Expose
    private float stormArrowKillsAvgPer10Min;
    @SerializedName("stormArrowKillsMostInGame")
    @Expose
    private int stormArrowKillsMostInGame;
    @SerializedName("bioticGrenadeKills")
    @Expose
    private int bioticGrenadeKills;
    @SerializedName("enemiesSlept")
    @Expose
    private int enemiesSlept;
    @SerializedName("enemiesSleptAvgPer10Min")
    @Expose
    private float enemiesSleptAvgPer10Min;
    @SerializedName("enemiesSleptMostInGame")
    @Expose
    private int enemiesSleptMostInGame;
    @SerializedName("nanoBoostAssists")
    @Expose
    private int nanoBoostAssists;
    @SerializedName("nanoBoostAssistsAvgPer10Min")
    @Expose
    private float nanoBoostAssistsAvgPer10Min;
    @SerializedName("nanoBoostAssistsMostInGame")
    @Expose
    private int nanoBoostAssistsMostInGame;
    @SerializedName("nanoBoostsApplied")
    @Expose
    private int nanoBoostsApplied;
    @SerializedName("nanoBoostsAppliedAvgPer10Min")
    @Expose
    private float nanoBoostsAppliedAvgPer10Min;
    @SerializedName("nanoBoostsAppliedMostInGame")
    @Expose
    private int nanoBoostsAppliedMostInGame;
    @SerializedName("scopedAccuracy")
    @Expose
    private String scopedAccuracy;
    @SerializedName("scopedAccuracyBestInGame")
    @Expose
    private String scopedAccuracyBestInGame;
    @SerializedName("selfHealing")
    @Expose
    private int selfHealing;
    @SerializedName("selfHealingAvgPer10Min")
    @Expose
    private int selfHealingAvgPer10Min;
    @SerializedName("selfHealingMostInGame")
    @Expose
    private int selfHealingMostInGame;
    @SerializedName("unscopedAccuracy")
    @Expose
    private String unscopedAccuracy;
    @SerializedName("unscopedAccuracyBestInGame")
    @Expose
    private String unscopedAccuracyBestInGame;
    @SerializedName("bobKills")
    @Expose
    private int bobKills;
    @SerializedName("bobKillsAvgPer10Min")
    @Expose
    private float bobKillsAvgPer10Min;
    @SerializedName("bobKillsMostInGame")
    @Expose
    private int bobKillsMostInGame;
    @SerializedName("coachGunKills")
    @Expose
    private int coachGunKills;
    @SerializedName("coachGunKillsAvgPer10Min")
    @Expose
    private float coachGunKillsAvgPer10Min;
    @SerializedName("coachGunKillsMostInGame")
    @Expose
    private int coachGunKillsMostInGame;
    @SerializedName("dynamiteKills")
    @Expose
    private int dynamiteKills;
    @SerializedName("dynamiteKillsAvgPer10Min")
    @Expose
    private float dynamiteKillsAvgPer10Min;
    @SerializedName("dynamiteKillsMostInGame")
    @Expose
    private int dynamiteKillsMostInGame;
    @SerializedName("scopedCriticalHits")
    @Expose
    private int scopedCriticalHits;
    @SerializedName("scopedCriticalHitsAccuracy")
    @Expose
    private String scopedCriticalHitsAccuracy;
    @SerializedName("scopedCriticalHitsAvgPer10Min")
    @Expose
    private float scopedCriticalHitsAvgPer10Min;
    @SerializedName("scopedCriticalHitsMostInGame")
    @Expose
    private int scopedCriticalHitsMostInGame;
    @SerializedName("amplificationMatrixCasts")
    @Expose
    private int amplificationMatrixCasts;
    @SerializedName("amplificationMatrixCastsAvgPer10Min")
    @Expose
    private float amplificationMatrixCastsAvgPer10Min;
    @SerializedName("amplificationMatrixCastsMostInGame")
    @Expose
    private int amplificationMatrixCastsMostInGame;
    @SerializedName("damageAmplified")
    @Expose
    private int damageAmplified;
    @SerializedName("damageAmplifiedAvgPer10Min")
    @Expose
    private int damageAmplifiedAvgPer10Min;
    @SerializedName("damageAmplifiedMostInGame")
    @Expose
    private int damageAmplifiedMostInGame;
    @SerializedName("healingAccuracy")
    @Expose
    private String healingAccuracy;
    @SerializedName("healingAccuracyBestInGame")
    @Expose
    private String healingAccuracyBestInGame;
    @SerializedName("immortalityFieldDeathsPrevented")
    @Expose
    private int immortalityFieldDeathsPrevented;
    @SerializedName("immortalityFieldDeathsPreventedAvgPer10Min")
    @Expose
    private float immortalityFieldDeathsPreventedAvgPer10Min;
    @SerializedName("immortalityFieldDeathsPreventedMostInGame")
    @Expose
    private int immortalityFieldDeathsPreventedMostInGame;
    @SerializedName("reconKills")
    @Expose
    private int reconKills;
    @SerializedName("reconKillsAvgPer10Min")
    @Expose
    private float reconKillsAvgPer10Min;
    @SerializedName("reconKillsMostInGame")
    @Expose
    private int reconKillsMostInGame;
    @SerializedName("sentryKills")
    @Expose
    private int sentryKills;
    @SerializedName("sentryKillsAvgPer10Min")
    @Expose
    private float sentryKillsAvgPer10Min;
    @SerializedName("sentryKillsMostInGame")
    @Expose
    private int sentryKillsMostInGame;
    @SerializedName("tankKills")
    @Expose
    private int tankKills;
    @SerializedName("tankKillsAvgPer10Min")
    @Expose
    private float tankKillsAvgPer10Min;
    @SerializedName("tankKillsMostInGame")
    @Expose
    private int tankKillsMostInGame;
    @SerializedName("armorProvided")
    @Expose
    private int armorProvided;
    @SerializedName("armorProvidedAvgPer10Min")
    @Expose
    private int armorProvidedAvgPer10Min;
    @SerializedName("armorProvidedMostInGame")
    @Expose
    private int armorProvidedMostInGame;
    @SerializedName("damageBlocked")
    @Expose
    private int damageBlocked;
    @SerializedName("damageBlockedAvgPer10Min")
    @Expose
    private int damageBlockedAvgPer10Min;
    @SerializedName("damageBlockedMostInGame")
    @Expose
    private int damageBlockedMostInGame;
    @SerializedName("inspireUptimePercentage")
    @Expose
    private String inspireUptimePercentage;
    @SerializedName("mechDeaths")
    @Expose
    private int mechDeaths;
    @SerializedName("mechsCalled")
    @Expose
    private int mechsCalled;
    @SerializedName("mechsCalledAvgPer10Min")
    @Expose
    private float mechsCalledAvgPer10Min;
    @SerializedName("mechsCalledMostInGame")
    @Expose
    private int mechsCalledMostInGame;
    @SerializedName("selfDestructKills")
    @Expose
    private int selfDestructKills;
    @SerializedName("selfDestructKillsAvgPer10Min")
    @Expose
    private float selfDestructKillsAvgPer10Min;
    @SerializedName("selfDestructKillsMostInGame")
    @Expose
    private int selfDestructKillsMostInGame;
    @SerializedName("abilityDamageDone")
    @Expose
    private int abilityDamageDone;
    @SerializedName("abilityDamageDoneAvgPer10Min")
    @Expose
    private int abilityDamageDoneAvgPer10Min;
    @SerializedName("abilityDamageDoneMostInGame")
    @Expose
    private int abilityDamageDoneMostInGame;
    @SerializedName("meteorStrikeKills")
    @Expose
    private int meteorStrikeKills;
    @SerializedName("meteorStrikeKillsAvgPer10Min")
    @Expose
    private float meteorStrikeKillsAvgPer10Min;
    @SerializedName("meteorStrikeKillsMostInGame")
    @Expose
    private int meteorStrikeKillsMostInGame;
    @SerializedName("shieldsCreated")
    @Expose
    private int shieldsCreated;
    @SerializedName("shieldsCreatedAvgPer10Min")
    @Expose
    private int shieldsCreatedAvgPer10Min;
    @SerializedName("shieldsCreatedMostInGame")
    @Expose
    private int shieldsCreatedMostInGame;
    @SerializedName("damageReflected")
    @Expose
    private int damageReflected;
    @SerializedName("damageReflectedAvgPer10Min")
    @Expose
    private int damageReflectedAvgPer10Min;
    @SerializedName("damageReflectedMostInGame")
    @Expose
    private int damageReflectedMostInGame;
    @SerializedName("deflectionKills")
    @Expose
    private int deflectionKills;
    @SerializedName("dragonbladesKills")
    @Expose
    private int dragonbladesKills;
    @SerializedName("dragonbladesKillsAvgPer10Min")
    @Expose
    private float dragonbladesKillsAvgPer10Min;
    @SerializedName("dragonbladesKillsMostInGame")
    @Expose
    private int dragonbladesKillsMostInGame;
    @SerializedName("soundBarriersProvided")
    @Expose
    private int soundBarriersProvided;
    @SerializedName("soundBarriersProvidedAvgPer10Min")
    @Expose
    private float soundBarriersProvidedAvgPer10Min;
    @SerializedName("soundBarriersProvidedMostInGame")
    @Expose
    private int soundBarriersProvidedMostInGame;
    @SerializedName("deadeyeKills")
    @Expose
    private int deadeyeKills;
    @SerializedName("deadeyeKillsAvgPer10Min")
    @Expose
    private float deadeyeKillsAvgPer10Min;
    @SerializedName("deadeyeKillsMostInGame")
    @Expose
    private int deadeyeKillsMostInGame;
    @SerializedName("fanTheHammerKills")
    @Expose
    private int fanTheHammerKills;
    @SerializedName("fanTheHammerKillsAvgPer10Min")
    @Expose
    private float fanTheHammerKillsAvgPer10Min;
    @SerializedName("fanTheHammerKillsMostInGame")
    @Expose
    private int fanTheHammerKillsMostInGame;
    @SerializedName("blizzardKills")
    @Expose
    private int blizzardKills;
    @SerializedName("blizzardKillsAvgPer10Min")
    @Expose
    private float blizzardKillsAvgPer10Min;
    @SerializedName("blizzardKillsMostInGame")
    @Expose
    private int blizzardKillsMostInGame;
    @SerializedName("enemiesFrozen")
    @Expose
    private int enemiesFrozen;
    @SerializedName("enemiesFrozenAvgPer10Min")
    @Expose
    private float enemiesFrozenAvgPer10Min;
    @SerializedName("enemiesFrozenMostInGame")
    @Expose
    private int enemiesFrozenMostInGame;
    @SerializedName("blasterKills")
    @Expose
    private int blasterKills;
    @SerializedName("blasterKillsAvgPer10Min")
    @Expose
    private float blasterKillsAvgPer10Min;
    @SerializedName("blasterKillsMostInGame")
    @Expose
    private int blasterKillsMostInGame;
    @SerializedName("playersResurrected")
    @Expose
    private int playersResurrected;
    @SerializedName("playersResurrectedAvgPer10Min")
    @Expose
    private float playersResurrectedAvgPer10Min;
    @SerializedName("playersResurrectedMostInGame")
    @Expose
    private int playersResurrectedMostInGame;
    @SerializedName("coalescenceHealing")
    @Expose
    private int coalescenceHealing;
    @SerializedName("coalescenceHealingAvgPer10Min")
    @Expose
    private int coalescenceHealingAvgPer10Min;
    @SerializedName("coalescenceHealingMostInGame")
    @Expose
    private int coalescenceHealingMostInGame;
    @SerializedName("coalescenceKills")
    @Expose
    private int coalescenceKills;
    @SerializedName("coalescenceKillsAvgPer10Min")
    @Expose
    private float coalescenceKillsAvgPer10Min;
    @SerializedName("coalescenceKillsMostInGame")
    @Expose
    private int coalescenceKillsMostInGame;
    @SerializedName("secondaryFireAccuracy")
    @Expose
    private String secondaryFireAccuracy;
    @SerializedName("superchargerAssists")
    @Expose
    private int superchargerAssists;
    @SerializedName("superchargerAssistsAvgPer10Min")
    @Expose
    private float superchargerAssistsAvgPer10Min;
    @SerializedName("superchargerAssistsMostInGame")
    @Expose
    private int superchargerAssistsMostInGame;
    @SerializedName("barrageKills")
    @Expose
    private int barrageKills;
    @SerializedName("barrageKillsAvgPer10Min")
    @Expose
    private float barrageKillsAvgPer10Min;
    @SerializedName("barrageKillsMostInGame")
    @Expose
    private int barrageKillsMostInGame;
    @SerializedName("directHitsAccuracy")
    @Expose
    private String directHitsAccuracy;
    @SerializedName("rocketDirectHits")
    @Expose
    private int rocketDirectHits;
    @SerializedName("rocketDirectHitsAvgPer10Min")
    @Expose
    private float rocketDirectHitsAvgPer10Min;
    @SerializedName("rocketDirectHitsMostInGame")
    @Expose
    private int rocketDirectHitsMostInGame;
    @SerializedName("deathsBlossomKills")
    @Expose
    private int deathsBlossomKills;
    @SerializedName("deathsBlossomKillsAvgPer10Min")
    @Expose
    private float deathsBlossomKillsAvgPer10Min;
    @SerializedName("deathsBlossomKillsMostInGame")
    @Expose
    private int deathsBlossomKillsMostInGame;
    @SerializedName("chargeKills")
    @Expose
    private int chargeKills;
    @SerializedName("chargeKillsAvgPer10Min")
    @Expose
    private float chargeKillsAvgPer10Min;
    @SerializedName("chargeKillsMostInGame")
    @Expose
    private int chargeKillsMostInGame;
    @SerializedName("earthshatterKills")
    @Expose
    private int earthshatterKills;
    @SerializedName("earthshatterKillsAvgPer10Min")
    @Expose
    private float earthshatterKillsAvgPer10Min;
    @SerializedName("earthshatterKillsMostInGame")
    @Expose
    private int earthshatterKillsMostInGame;
    @SerializedName("fireStrikeKills")
    @Expose
    private int fireStrikeKills;
    @SerializedName("fireStrikeKillsAvgPer10Min")
    @Expose
    private float fireStrikeKillsAvgPer10Min;
    @SerializedName("fireStrikeKillsMostInGame")
    @Expose
    private int fireStrikeKillsMostInGame;
    @SerializedName("rocketHammerMeleeAccuracy")
    @Expose
    private String rocketHammerMeleeAccuracy;
    @SerializedName("enemiesHooked")
    @Expose
    private int enemiesHooked;
    @SerializedName("enemiesHookedAvgPer10Min")
    @Expose
    private float enemiesHookedAvgPer10Min;
    @SerializedName("enemiesHookedMostInGame")
    @Expose
    private int enemiesHookedMostInGame;
    @SerializedName("hookAccuracy")
    @Expose
    private String hookAccuracy;
    @SerializedName("hookAccuracyBestInGame")
    @Expose
    private String hookAccuracyBestInGame;
    @SerializedName("hooksAttempted")
    @Expose
    private int hooksAttempted;
    @SerializedName("wholeHogKills")
    @Expose
    private int wholeHogKills;
    @SerializedName("wholeHogKillsAvgPer10Min")
    @Expose
    private float wholeHogKillsAvgPer10Min;
    @SerializedName("wholeHogKillsMostInGame")
    @Expose
    private int wholeHogKillsMostInGame;
    @SerializedName("bioticFieldHealingDone")
    @Expose
    private int bioticFieldHealingDone;
    @SerializedName("bioticFieldsDeployed")
    @Expose
    private int bioticFieldsDeployed;
    @SerializedName("helixRocketsKills")
    @Expose
    private int helixRocketsKills;
    @SerializedName("helixRocketsKillsAvgPer10Min")
    @Expose
    private float helixRocketsKillsAvgPer10Min;
    @SerializedName("helixRocketsKillsMostInGame")
    @Expose
    private int helixRocketsKillsMostInGame;
    @SerializedName("tacticalVisorKills")
    @Expose
    private int tacticalVisorKills;
    @SerializedName("tacticalVisorKillsAvgPer10Min")
    @Expose
    private float tacticalVisorKillsAvgPer10Min;
    @SerializedName("tacticalVisorKillsMostInGame")
    @Expose
    private int tacticalVisorKillsMostInGame;
    @SerializedName("enemiesEmpd")
    @Expose
    private int enemiesEmpd;
    @SerializedName("enemiesEmpdAvgPer10Min")
    @Expose
    private float enemiesEmpdAvgPer10Min;
    @SerializedName("enemiesEmpdMostInGame")
    @Expose
    private int enemiesEmpdMostInGame;
    @SerializedName("enemiesHacked")
    @Expose
    private int enemiesHacked;
    @SerializedName("enemiesHackedAvgPer10Min")
    @Expose
    private float enemiesHackedAvgPer10Min;
    @SerializedName("enemiesHackedMostInGame")
    @Expose
    private int enemiesHackedMostInGame;
    @SerializedName("playersTeleported")
    @Expose
    private int playersTeleported;
    @SerializedName("playersTeleportedAvgPer10Min")
    @Expose
    private float playersTeleportedAvgPer10Min;
    @SerializedName("playersTeleportedMostInGame")
    @Expose
    private int playersTeleportedMostInGame;
    @SerializedName("primaryFireAccuracy")
    @Expose
    private String primaryFireAccuracy;
    @SerializedName("secondaryDirectHitsAvgPer10Min")
    @Expose
    private float secondaryDirectHitsAvgPer10Min;
    @SerializedName("sentryTurretsKills")
    @Expose
    private int sentryTurretsKills;
    @SerializedName("sentryTurretsKillsAvgPer10Min")
    @Expose
    private float sentryTurretsKillsAvgPer10Min;
    @SerializedName("sentryTurretsKillsMostInGame")
    @Expose
    private int sentryTurretsKillsMostInGame;
    @SerializedName("armorPacksCreated")
    @Expose
    private int armorPacksCreated;
    @SerializedName("armorPacksCreatedAvgPer10Min")
    @Expose
    private float armorPacksCreatedAvgPer10Min;
    @SerializedName("armorPacksCreatedMostInGame")
    @Expose
    private int armorPacksCreatedMostInGame;
    @SerializedName("moltenCoreKills")
    @Expose
    private int moltenCoreKills;
    @SerializedName("moltenCoreKillsAvgPer10Min")
    @Expose
    private float moltenCoreKillsAvgPer10Min;
    @SerializedName("moltenCoreKillsMostInGame")
    @Expose
    private int moltenCoreKillsMostInGame;
    @SerializedName("overloadKills")
    @Expose
    private int overloadKills;
    @SerializedName("overloadKillsMostInGame")
    @Expose
    private int overloadKillsMostInGame;
    @SerializedName("torbjornKills")
    @Expose
    private int torbjornKills;
    @SerializedName("torbjornKillsAvgPer10Min")
    @Expose
    private float torbjornKillsAvgPer10Min;
    @SerializedName("torbjornKillsMostInGame")
    @Expose
    private int torbjornKillsMostInGame;
    @SerializedName("turretsDamageAvgPer10Min")
    @Expose
    private int turretsDamageAvgPer10Min;
    @SerializedName("turretsKills")
    @Expose
    private int turretsKills;
    @SerializedName("turretsKillsAvgPer10Min")
    @Expose
    private float turretsKillsAvgPer10Min;
    @SerializedName("turretsKillsMostInGame")
    @Expose
    private int turretsKillsMostInGame;
    @SerializedName("healthRecovered")
    @Expose
    private int healthRecovered;
    @SerializedName("healthRecoveredAvgPer10Min")
    @Expose
    private int healthRecoveredAvgPer10Min;
    @SerializedName("healthRecoveredMostInGame")
    @Expose
    private int healthRecoveredMostInGame;
    @SerializedName("pulseBombsAttached")
    @Expose
    private int pulseBombsAttached;
    @SerializedName("pulseBombsAttachedAvgPer10Min")
    @Expose
    private float pulseBombsAttachedAvgPer10Min;
    @SerializedName("pulseBombsAttachedMostInGame")
    @Expose
    private int pulseBombsAttachedMostInGame;
    @SerializedName("pulseBombsKills")
    @Expose
    private int pulseBombsKills;
    @SerializedName("pulseBombsKillsAvgPer10Min")
    @Expose
    private float pulseBombsKillsAvgPer10Min;
    @SerializedName("pulseBombsKillsMostInGame")
    @Expose
    private int pulseBombsKillsMostInGame;
    @SerializedName("venomMineKills")
    @Expose
    private int venomMineKills;
    @SerializedName("venomMineKillsAvgPer10Min")
    @Expose
    private float venomMineKillsAvgPer10Min;
    @SerializedName("venomMineKillsMostInGame")
    @Expose
    private int venomMineKillsMostInGame;
    @SerializedName("jumpKills")
    @Expose
    private int jumpKills;
    @SerializedName("jumpPackKills")
    @Expose
    private int jumpPackKills;
    @SerializedName("jumpPackKillsAvgPer10Min")
    @Expose
    private float jumpPackKillsAvgPer10Min;
    @SerializedName("jumpPackKillsMostInGame")
    @Expose
    private int jumpPackKillsMostInGame;
    @SerializedName("meleeKills")
    @Expose
    private int meleeKills;
    @SerializedName("meleeKillsAvgPer10Min")
    @Expose
    private float meleeKillsAvgPer10Min;
    @SerializedName("meleeKillsMostInGame")
    @Expose
    private int meleeKillsMostInGame;
    @SerializedName("playersKnockedBack")
    @Expose
    private int playersKnockedBack;
    @SerializedName("playersKnockedBackAvgPer10Min")
    @Expose
    private float playersKnockedBackAvgPer10Min;
    @SerializedName("playersKnockedBackMostInGame")
    @Expose
    private int playersKnockedBackMostInGame;
    @SerializedName("primalRageKills")
    @Expose
    private int primalRageKills;
    @SerializedName("primalRageKillsAvgPer10Min")
    @Expose
    private float primalRageKillsAvgPer10Min;
    @SerializedName("primalRageKillsMostInGame")
    @Expose
    private int primalRageKillsMostInGame;
    @SerializedName("primalRageMeleeAccuracy")
    @Expose
    private String primalRageMeleeAccuracy;
    @SerializedName("teslaCannonAccuracy")
    @Expose
    private String teslaCannonAccuracy;
    @SerializedName("weaponKills")
    @Expose
    private int weaponKills;
    @SerializedName("grapplingClawKills")
    @Expose
    private int grapplingClawKills;
    @SerializedName("grapplingClawKillsAvgPer10Min")
    @Expose
    private float grapplingClawKillsAvgPer10Min;
    @SerializedName("grapplingClawKillsMostInGame")
    @Expose
    private int grapplingClawKillsMostInGame;
    @SerializedName("minefieldKills")
    @Expose
    private int minefieldKills;
    @SerializedName("minefieldKillsAvgPer10Min")
    @Expose
    private float minefieldKillsAvgPer10Min;
    @SerializedName("minefieldKillsMostInGame")
    @Expose
    private int minefieldKillsMostInGame;
    @SerializedName("averageEnergy")
    @Expose
    private String averageEnergy;
    @SerializedName("averageEnergyBestInGame")
    @Expose
    private String averageEnergyBestInGame;
    @SerializedName("gravitonSurgeKills")
    @Expose
    private int gravitonSurgeKills;
    @SerializedName("gravitonSurgeKillsAvgPer10Min")
    @Expose
    private float gravitonSurgeKillsAvgPer10Min;
    @SerializedName("gravitonSurgeKillsMostInGame")
    @Expose
    private int gravitonSurgeKillsMostInGame;
    @SerializedName("highEnergyKills")
    @Expose
    private int highEnergyKills;
    @SerializedName("highEnergyKillsAvgPer10Min")
    @Expose
    private float highEnergyKillsAvgPer10Min;
    @SerializedName("highEnergyKillsMostInGame")
    @Expose
    private int highEnergyKillsMostInGame;
    @SerializedName("projectedBarriersApplied")
    @Expose
    private int projectedBarriersApplied;
    @SerializedName("projectedBarriersAppliedAvgPer10Min")
    @Expose
    private float projectedBarriersAppliedAvgPer10Min;
    @SerializedName("projectedBarriersAppliedMostInGame")
    @Expose
    private int projectedBarriersAppliedMostInGame;
    @SerializedName("transcendenceHealing")
    @Expose
    private int transcendenceHealing;
    @SerializedName("transcendenceHealingBest")
    @Expose
    private int transcendenceHealingBest;

    public int getEnemiesTrapped() {
        return enemiesTrapped;
    }

    public void setEnemiesTrapped(int enemiesTrapped) {
        this.enemiesTrapped = enemiesTrapped;
    }

    public float getEnemiesTrappedAvgPer10Min() {
        return enemiesTrappedAvgPer10Min;
    }

    public void setEnemiesTrappedAvgPer10Min(float enemiesTrappedAvgPer10Min) {
        this.enemiesTrappedAvgPer10Min = enemiesTrappedAvgPer10Min;
    }

    public int getEnemiesTrappedMostInGame() {
        return enemiesTrappedMostInGame;
    }

    public void setEnemiesTrappedMostInGame(int enemiesTrappedMostInGame) {
        this.enemiesTrappedMostInGame = enemiesTrappedMostInGame;
    }

    public int getRipTireKills() {
        return ripTireKills;
    }

    public void setRipTireKills(int ripTireKills) {
        this.ripTireKills = ripTireKills;
    }

    public float getRipTireKillsAvgPer10Min() {
        return ripTireKillsAvgPer10Min;
    }

    public void setRipTireKillsAvgPer10Min(float ripTireKillsAvgPer10Min) {
        this.ripTireKillsAvgPer10Min = ripTireKillsAvgPer10Min;
    }

    public int getRipTireKillsMostInGame() {
        return ripTireKillsMostInGame;
    }

    public void setRipTireKillsMostInGame(int ripTireKillsMostInGame) {
        this.ripTireKillsMostInGame = ripTireKillsMostInGame;
    }

    public int getDragonstrikeKills() {
        return dragonstrikeKills;
    }

    public void setDragonstrikeKills(int dragonstrikeKills) {
        this.dragonstrikeKills = dragonstrikeKills;
    }

    public float getDragonstrikeKillsAvgPer10Min() {
        return dragonstrikeKillsAvgPer10Min;
    }

    public void setDragonstrikeKillsAvgPer10Min(float dragonstrikeKillsAvgPer10Min) {
        this.dragonstrikeKillsAvgPer10Min = dragonstrikeKillsAvgPer10Min;
    }

    public int getDragonstrikeKillsMostInGame() {
        return dragonstrikeKillsMostInGame;
    }

    public void setDragonstrikeKillsMostInGame(int dragonstrikeKillsMostInGame) {
        this.dragonstrikeKillsMostInGame = dragonstrikeKillsMostInGame;
    }

    public int getScatterArrowKills() {
        return scatterArrowKills;
    }

    public void setScatterArrowKills(int scatterArrowKills) {
        this.scatterArrowKills = scatterArrowKills;
    }

    public float getScatterArrowKillsAvgPer10Min() {
        return scatterArrowKillsAvgPer10Min;
    }

    public void setScatterArrowKillsAvgPer10Min(float scatterArrowKillsAvgPer10Min) {
        this.scatterArrowKillsAvgPer10Min = scatterArrowKillsAvgPer10Min;
    }

    public int getScatterArrowKillsMostInGame() {
        return scatterArrowKillsMostInGame;
    }

    public void setScatterArrowKillsMostInGame(int scatterArrowKillsMostInGame) {
        this.scatterArrowKillsMostInGame = scatterArrowKillsMostInGame;
    }

    public int getStormArrowKills() {
        return stormArrowKills;
    }

    public void setStormArrowKills(int stormArrowKills) {
        this.stormArrowKills = stormArrowKills;
    }

    public float getStormArrowKillsAvgPer10Min() {
        return stormArrowKillsAvgPer10Min;
    }

    public void setStormArrowKillsAvgPer10Min(float stormArrowKillsAvgPer10Min) {
        this.stormArrowKillsAvgPer10Min = stormArrowKillsAvgPer10Min;
    }

    public int getStormArrowKillsMostInGame() {
        return stormArrowKillsMostInGame;
    }

    public void setStormArrowKillsMostInGame(int stormArrowKillsMostInGame) {
        this.stormArrowKillsMostInGame = stormArrowKillsMostInGame;
    }

    public int getBioticGrenadeKills() {
        return bioticGrenadeKills;
    }

    public void setBioticGrenadeKills(int bioticGrenadeKills) {
        this.bioticGrenadeKills = bioticGrenadeKills;
    }

    public int getEnemiesSlept() {
        return enemiesSlept;
    }

    public void setEnemiesSlept(int enemiesSlept) {
        this.enemiesSlept = enemiesSlept;
    }

    public float getEnemiesSleptAvgPer10Min() {
        return enemiesSleptAvgPer10Min;
    }

    public void setEnemiesSleptAvgPer10Min(float enemiesSleptAvgPer10Min) {
        this.enemiesSleptAvgPer10Min = enemiesSleptAvgPer10Min;
    }

    public int getEnemiesSleptMostInGame() {
        return enemiesSleptMostInGame;
    }

    public void setEnemiesSleptMostInGame(int enemiesSleptMostInGame) {
        this.enemiesSleptMostInGame = enemiesSleptMostInGame;
    }

    public int getNanoBoostAssists() {
        return nanoBoostAssists;
    }

    public void setNanoBoostAssists(int nanoBoostAssists) {
        this.nanoBoostAssists = nanoBoostAssists;
    }

    public float getNanoBoostAssistsAvgPer10Min() {
        return nanoBoostAssistsAvgPer10Min;
    }

    public void setNanoBoostAssistsAvgPer10Min(float nanoBoostAssistsAvgPer10Min) {
        this.nanoBoostAssistsAvgPer10Min = nanoBoostAssistsAvgPer10Min;
    }

    public int getNanoBoostAssistsMostInGame() {
        return nanoBoostAssistsMostInGame;
    }

    public void setNanoBoostAssistsMostInGame(int nanoBoostAssistsMostInGame) {
        this.nanoBoostAssistsMostInGame = nanoBoostAssistsMostInGame;
    }

    public int getNanoBoostsApplied() {
        return nanoBoostsApplied;
    }

    public void setNanoBoostsApplied(int nanoBoostsApplied) {
        this.nanoBoostsApplied = nanoBoostsApplied;
    }

    public float getNanoBoostsAppliedAvgPer10Min() {
        return nanoBoostsAppliedAvgPer10Min;
    }

    public void setNanoBoostsAppliedAvgPer10Min(float nanoBoostsAppliedAvgPer10Min) {
        this.nanoBoostsAppliedAvgPer10Min = nanoBoostsAppliedAvgPer10Min;
    }

    public int getNanoBoostsAppliedMostInGame() {
        return nanoBoostsAppliedMostInGame;
    }

    public void setNanoBoostsAppliedMostInGame(int nanoBoostsAppliedMostInGame) {
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

    public int getSelfHealing() {
        return selfHealing;
    }

    public void setSelfHealing(int selfHealing) {
        this.selfHealing = selfHealing;
    }

    public int getSelfHealingAvgPer10Min() {
        return selfHealingAvgPer10Min;
    }

    public void setSelfHealingAvgPer10Min(int selfHealingAvgPer10Min) {
        this.selfHealingAvgPer10Min = selfHealingAvgPer10Min;
    }

    public int getSelfHealingMostInGame() {
        return selfHealingMostInGame;
    }

    public void setSelfHealingMostInGame(int selfHealingMostInGame) {
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

    public int getBobKills() {
        return bobKills;
    }

    public void setBobKills(int bobKills) {
        this.bobKills = bobKills;
    }

    public float getBobKillsAvgPer10Min() {
        return bobKillsAvgPer10Min;
    }

    public void setBobKillsAvgPer10Min(float bobKillsAvgPer10Min) {
        this.bobKillsAvgPer10Min = bobKillsAvgPer10Min;
    }

    public int getBobKillsMostInGame() {
        return bobKillsMostInGame;
    }

    public void setBobKillsMostInGame(int bobKillsMostInGame) {
        this.bobKillsMostInGame = bobKillsMostInGame;
    }

    public int getCoachGunKills() {
        return coachGunKills;
    }

    public void setCoachGunKills(int coachGunKills) {
        this.coachGunKills = coachGunKills;
    }

    public float getCoachGunKillsAvgPer10Min() {
        return coachGunKillsAvgPer10Min;
    }

    public void setCoachGunKillsAvgPer10Min(float coachGunKillsAvgPer10Min) {
        this.coachGunKillsAvgPer10Min = coachGunKillsAvgPer10Min;
    }

    public int getCoachGunKillsMostInGame() {
        return coachGunKillsMostInGame;
    }

    public void setCoachGunKillsMostInGame(int coachGunKillsMostInGame) {
        this.coachGunKillsMostInGame = coachGunKillsMostInGame;
    }

    public int getDynamiteKills() {
        return dynamiteKills;
    }

    public void setDynamiteKills(int dynamiteKills) {
        this.dynamiteKills = dynamiteKills;
    }

    public float getDynamiteKillsAvgPer10Min() {
        return dynamiteKillsAvgPer10Min;
    }

    public void setDynamiteKillsAvgPer10Min(float dynamiteKillsAvgPer10Min) {
        this.dynamiteKillsAvgPer10Min = dynamiteKillsAvgPer10Min;
    }

    public int getDynamiteKillsMostInGame() {
        return dynamiteKillsMostInGame;
    }

    public void setDynamiteKillsMostInGame(int dynamiteKillsMostInGame) {
        this.dynamiteKillsMostInGame = dynamiteKillsMostInGame;
    }

    public int getScopedCriticalHits() {
        return scopedCriticalHits;
    }

    public void setScopedCriticalHits(int scopedCriticalHits) {
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

    public int getScopedCriticalHitsMostInGame() {
        return scopedCriticalHitsMostInGame;
    }

    public void setScopedCriticalHitsMostInGame(int scopedCriticalHitsMostInGame) {
        this.scopedCriticalHitsMostInGame = scopedCriticalHitsMostInGame;
    }

    public int getAmplificationMatrixCasts() {
        return amplificationMatrixCasts;
    }

    public void setAmplificationMatrixCasts(int amplificationMatrixCasts) {
        this.amplificationMatrixCasts = amplificationMatrixCasts;
    }

    public float getAmplificationMatrixCastsAvgPer10Min() {
        return amplificationMatrixCastsAvgPer10Min;
    }

    public void setAmplificationMatrixCastsAvgPer10Min(float amplificationMatrixCastsAvgPer10Min) {
        this.amplificationMatrixCastsAvgPer10Min = amplificationMatrixCastsAvgPer10Min;
    }

    public int getAmplificationMatrixCastsMostInGame() {
        return amplificationMatrixCastsMostInGame;
    }

    public void setAmplificationMatrixCastsMostInGame(int amplificationMatrixCastsMostInGame) {
        this.amplificationMatrixCastsMostInGame = amplificationMatrixCastsMostInGame;
    }

    public int getDamageAmplified() {
        return damageAmplified;
    }

    public void setDamageAmplified(int damageAmplified) {
        this.damageAmplified = damageAmplified;
    }

    public int getDamageAmplifiedAvgPer10Min() {
        return damageAmplifiedAvgPer10Min;
    }

    public void setDamageAmplifiedAvgPer10Min(int damageAmplifiedAvgPer10Min) {
        this.damageAmplifiedAvgPer10Min = damageAmplifiedAvgPer10Min;
    }

    public int getDamageAmplifiedMostInGame() {
        return damageAmplifiedMostInGame;
    }

    public void setDamageAmplifiedMostInGame(int damageAmplifiedMostInGame) {
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

    public int getImmortalityFieldDeathsPrevented() {
        return immortalityFieldDeathsPrevented;
    }

    public void setImmortalityFieldDeathsPrevented(int immortalityFieldDeathsPrevented) {
        this.immortalityFieldDeathsPrevented = immortalityFieldDeathsPrevented;
    }

    public float getImmortalityFieldDeathsPreventedAvgPer10Min() {
        return immortalityFieldDeathsPreventedAvgPer10Min;
    }

    public void setImmortalityFieldDeathsPreventedAvgPer10Min(float immortalityFieldDeathsPreventedAvgPer10Min) {
        this.immortalityFieldDeathsPreventedAvgPer10Min = immortalityFieldDeathsPreventedAvgPer10Min;
    }

    public int getImmortalityFieldDeathsPreventedMostInGame() {
        return immortalityFieldDeathsPreventedMostInGame;
    }

    public void setImmortalityFieldDeathsPreventedMostInGame(int immortalityFieldDeathsPreventedMostInGame) {
        this.immortalityFieldDeathsPreventedMostInGame = immortalityFieldDeathsPreventedMostInGame;
    }

    public int getReconKills() {
        return reconKills;
    }

    public void setReconKills(int reconKills) {
        this.reconKills = reconKills;
    }

    public float getReconKillsAvgPer10Min() {
        return reconKillsAvgPer10Min;
    }

    public void setReconKillsAvgPer10Min(float reconKillsAvgPer10Min) {
        this.reconKillsAvgPer10Min = reconKillsAvgPer10Min;
    }

    public int getReconKillsMostInGame() {
        return reconKillsMostInGame;
    }

    public void setReconKillsMostInGame(int reconKillsMostInGame) {
        this.reconKillsMostInGame = reconKillsMostInGame;
    }

    public int getSentryKills() {
        return sentryKills;
    }

    public void setSentryKills(int sentryKills) {
        this.sentryKills = sentryKills;
    }

    public float getSentryKillsAvgPer10Min() {
        return sentryKillsAvgPer10Min;
    }

    public void setSentryKillsAvgPer10Min(float sentryKillsAvgPer10Min) {
        this.sentryKillsAvgPer10Min = sentryKillsAvgPer10Min;
    }

    public int getSentryKillsMostInGame() {
        return sentryKillsMostInGame;
    }

    public void setSentryKillsMostInGame(int sentryKillsMostInGame) {
        this.sentryKillsMostInGame = sentryKillsMostInGame;
    }

    public int getTankKills() {
        return tankKills;
    }

    public void setTankKills(int tankKills) {
        this.tankKills = tankKills;
    }

    public float getTankKillsAvgPer10Min() {
        return tankKillsAvgPer10Min;
    }

    public void setTankKillsAvgPer10Min(float tankKillsAvgPer10Min) {
        this.tankKillsAvgPer10Min = tankKillsAvgPer10Min;
    }

    public int getTankKillsMostInGame() {
        return tankKillsMostInGame;
    }

    public void setTankKillsMostInGame(int tankKillsMostInGame) {
        this.tankKillsMostInGame = tankKillsMostInGame;
    }

    public int getArmorProvided() {
        return armorProvided;
    }

    public void setArmorProvided(int armorProvided) {
        this.armorProvided = armorProvided;
    }

    public int getArmorProvidedAvgPer10Min() {
        return armorProvidedAvgPer10Min;
    }

    public void setArmorProvidedAvgPer10Min(int armorProvidedAvgPer10Min) {
        this.armorProvidedAvgPer10Min = armorProvidedAvgPer10Min;
    }

    public int getArmorProvidedMostInGame() {
        return armorProvidedMostInGame;
    }

    public void setArmorProvidedMostInGame(int armorProvidedMostInGame) {
        this.armorProvidedMostInGame = armorProvidedMostInGame;
    }

    public int getDamageBlocked() {
        return damageBlocked;
    }

    public void setDamageBlocked(int damageBlocked) {
        this.damageBlocked = damageBlocked;
    }

    public int getDamageBlockedAvgPer10Min() {
        return damageBlockedAvgPer10Min;
    }

    public void setDamageBlockedAvgPer10Min(int damageBlockedAvgPer10Min) {
        this.damageBlockedAvgPer10Min = damageBlockedAvgPer10Min;
    }

    public int getDamageBlockedMostInGame() {
        return damageBlockedMostInGame;
    }

    public void setDamageBlockedMostInGame(int damageBlockedMostInGame) {
        this.damageBlockedMostInGame = damageBlockedMostInGame;
    }

    public String getInspireUptimePercentage() {
        return inspireUptimePercentage;
    }

    public void setInspireUptimePercentage(String inspireUptimePercentage) {
        this.inspireUptimePercentage = inspireUptimePercentage;
    }

    public int getMechDeaths() {
        return mechDeaths;
    }

    public void setMechDeaths(int mechDeaths) {
        this.mechDeaths = mechDeaths;
    }

    public int getMechsCalled() {
        return mechsCalled;
    }

    public void setMechsCalled(int mechsCalled) {
        this.mechsCalled = mechsCalled;
    }

    public float getMechsCalledAvgPer10Min() {
        return mechsCalledAvgPer10Min;
    }

    public void setMechsCalledAvgPer10Min(float mechsCalledAvgPer10Min) {
        this.mechsCalledAvgPer10Min = mechsCalledAvgPer10Min;
    }

    public int getMechsCalledMostInGame() {
        return mechsCalledMostInGame;
    }

    public void setMechsCalledMostInGame(int mechsCalledMostInGame) {
        this.mechsCalledMostInGame = mechsCalledMostInGame;
    }

    public int getSelfDestructKills() {
        return selfDestructKills;
    }

    public void setSelfDestructKills(int selfDestructKills) {
        this.selfDestructKills = selfDestructKills;
    }

    public float getSelfDestructKillsAvgPer10Min() {
        return selfDestructKillsAvgPer10Min;
    }

    public void setSelfDestructKillsAvgPer10Min(float selfDestructKillsAvgPer10Min) {
        this.selfDestructKillsAvgPer10Min = selfDestructKillsAvgPer10Min;
    }

    public int getSelfDestructKillsMostInGame() {
        return selfDestructKillsMostInGame;
    }

    public void setSelfDestructKillsMostInGame(int selfDestructKillsMostInGame) {
        this.selfDestructKillsMostInGame = selfDestructKillsMostInGame;
    }

    public int getAbilityDamageDone() {
        return abilityDamageDone;
    }

    public void setAbilityDamageDone(int abilityDamageDone) {
        this.abilityDamageDone = abilityDamageDone;
    }

    public int getAbilityDamageDoneAvgPer10Min() {
        return abilityDamageDoneAvgPer10Min;
    }

    public void setAbilityDamageDoneAvgPer10Min(int abilityDamageDoneAvgPer10Min) {
        this.abilityDamageDoneAvgPer10Min = abilityDamageDoneAvgPer10Min;
    }

    public int getAbilityDamageDoneMostInGame() {
        return abilityDamageDoneMostInGame;
    }

    public void setAbilityDamageDoneMostInGame(int abilityDamageDoneMostInGame) {
        this.abilityDamageDoneMostInGame = abilityDamageDoneMostInGame;
    }

    public int getMeteorStrikeKills() {
        return meteorStrikeKills;
    }

    public void setMeteorStrikeKills(int meteorStrikeKills) {
        this.meteorStrikeKills = meteorStrikeKills;
    }

    public float getMeteorStrikeKillsAvgPer10Min() {
        return meteorStrikeKillsAvgPer10Min;
    }

    public void setMeteorStrikeKillsAvgPer10Min(float meteorStrikeKillsAvgPer10Min) {
        this.meteorStrikeKillsAvgPer10Min = meteorStrikeKillsAvgPer10Min;
    }

    public int getMeteorStrikeKillsMostInGame() {
        return meteorStrikeKillsMostInGame;
    }

    public void setMeteorStrikeKillsMostInGame(int meteorStrikeKillsMostInGame) {
        this.meteorStrikeKillsMostInGame = meteorStrikeKillsMostInGame;
    }

    public int getShieldsCreated() {
        return shieldsCreated;
    }

    public void setShieldsCreated(int shieldsCreated) {
        this.shieldsCreated = shieldsCreated;
    }

    public int getShieldsCreatedAvgPer10Min() {
        return shieldsCreatedAvgPer10Min;
    }

    public void setShieldsCreatedAvgPer10Min(int shieldsCreatedAvgPer10Min) {
        this.shieldsCreatedAvgPer10Min = shieldsCreatedAvgPer10Min;
    }

    public int getShieldsCreatedMostInGame() {
        return shieldsCreatedMostInGame;
    }

    public void setShieldsCreatedMostInGame(int shieldsCreatedMostInGame) {
        this.shieldsCreatedMostInGame = shieldsCreatedMostInGame;
    }

    public int getDamageReflected() {
        return damageReflected;
    }

    public void setDamageReflected(int damageReflected) {
        this.damageReflected = damageReflected;
    }

    public int getDamageReflectedAvgPer10Min() {
        return damageReflectedAvgPer10Min;
    }

    public void setDamageReflectedAvgPer10Min(int damageReflectedAvgPer10Min) {
        this.damageReflectedAvgPer10Min = damageReflectedAvgPer10Min;
    }

    public int getDamageReflectedMostInGame() {
        return damageReflectedMostInGame;
    }

    public void setDamageReflectedMostInGame(int damageReflectedMostInGame) {
        this.damageReflectedMostInGame = damageReflectedMostInGame;
    }

    public int getDeflectionKills() {
        return deflectionKills;
    }

    public void setDeflectionKills(int deflectionKills) {
        this.deflectionKills = deflectionKills;
    }

    public int getDragonbladesKills() {
        return dragonbladesKills;
    }

    public void setDragonbladesKills(int dragonbladesKills) {
        this.dragonbladesKills = dragonbladesKills;
    }

    public float getDragonbladesKillsAvgPer10Min() {
        return dragonbladesKillsAvgPer10Min;

    }

    public void setDragonbladesKillsAvgPer10Min(float dragonbladesKillsAvgPer10Min) {
        this.dragonbladesKillsAvgPer10Min = dragonbladesKillsAvgPer10Min;
    }

    public int getDragonbladesKillsMostInGame() {
        return dragonbladesKillsMostInGame;
    }

    public void setDragonbladesKillsMostInGame(int dragonbladesKillsMostInGame) {
        this.dragonbladesKillsMostInGame = dragonbladesKillsMostInGame;
    }

    public int getSoundBarriersProvided() {
        return soundBarriersProvided;
    }

    public void setSoundBarriersProvided(int soundBarriersProvided) {
        this.soundBarriersProvided = soundBarriersProvided;
    }

    public float getSoundBarriersProvidedAvgPer10Min() {
        return soundBarriersProvidedAvgPer10Min;
    }

    public void setSoundBarriersProvidedAvgPer10Min(float soundBarriersProvidedAvgPer10Min) {
        this.soundBarriersProvidedAvgPer10Min = soundBarriersProvidedAvgPer10Min;
    }

    public int getSoundBarriersProvidedMostInGame() {
        return soundBarriersProvidedMostInGame;
    }

    public void setSoundBarriersProvidedMostInGame(int soundBarriersProvidedMostInGame) {
        this.soundBarriersProvidedMostInGame = soundBarriersProvidedMostInGame;
    }

    public int getDeadeyeKills() {
        return deadeyeKills;
    }

    public void setDeadeyeKills(int deadeyeKills) {
        this.deadeyeKills = deadeyeKills;
    }

    public float getDeadeyeKillsAvgPer10Min() {
        return deadeyeKillsAvgPer10Min;
    }

    public void setDeadeyeKillsAvgPer10Min(float deadeyeKillsAvgPer10Min) {
        this.deadeyeKillsAvgPer10Min = deadeyeKillsAvgPer10Min;
    }

    public int getDeadeyeKillsMostInGame() {
        return deadeyeKillsMostInGame;
    }

    public void setDeadeyeKillsMostInGame(int deadeyeKillsMostInGame) {
        this.deadeyeKillsMostInGame = deadeyeKillsMostInGame;
    }

    public int getFanTheHammerKills() {
        return fanTheHammerKills;
    }

    public void setFanTheHammerKills(int fanTheHammerKills) {
        this.fanTheHammerKills = fanTheHammerKills;
    }

    public float getFanTheHammerKillsAvgPer10Min() {
        return fanTheHammerKillsAvgPer10Min;
    }

    public void setFanTheHammerKillsAvgPer10Min(float fanTheHammerKillsAvgPer10Min) {
        this.fanTheHammerKillsAvgPer10Min = fanTheHammerKillsAvgPer10Min;
    }

    public int getFanTheHammerKillsMostInGame() {
        return fanTheHammerKillsMostInGame;
    }

    public void setFanTheHammerKillsMostInGame(int fanTheHammerKillsMostInGame) {
        this.fanTheHammerKillsMostInGame = fanTheHammerKillsMostInGame;
    }

    public int getBlizzardKills() {
        return blizzardKills;
    }

    public void setBlizzardKills(int blizzardKills) {
        this.blizzardKills = blizzardKills;
    }

    public float getBlizzardKillsAvgPer10Min() {
        return blizzardKillsAvgPer10Min;
    }

    public void setBlizzardKillsAvgPer10Min(float blizzardKillsAvgPer10Min) {
        this.blizzardKillsAvgPer10Min = blizzardKillsAvgPer10Min;
    }

    public int getBlizzardKillsMostInGame() {
        return blizzardKillsMostInGame;
    }

    public void setBlizzardKillsMostInGame(int blizzardKillsMostInGame) {
        this.blizzardKillsMostInGame = blizzardKillsMostInGame;
    }

    public int getEnemiesFrozen() {
        return enemiesFrozen;
    }

    public void setEnemiesFrozen(int enemiesFrozen) {
        this.enemiesFrozen = enemiesFrozen;
    }

    public float getEnemiesFrozenAvgPer10Min() {
        return enemiesFrozenAvgPer10Min;
    }

    public void setEnemiesFrozenAvgPer10Min(float enemiesFrozenAvgPer10Min) {
        this.enemiesFrozenAvgPer10Min = enemiesFrozenAvgPer10Min;
    }

    public int getEnemiesFrozenMostInGame() {
        return enemiesFrozenMostInGame;
    }

    public void setEnemiesFrozenMostInGame(int enemiesFrozenMostInGame) {
        this.enemiesFrozenMostInGame = enemiesFrozenMostInGame;
    }

    public int getBlasterKills() {
        return blasterKills;
    }

    public void setBlasterKills(int blasterKills) {
        this.blasterKills = blasterKills;
    }

    public float getBlasterKillsAvgPer10Min() {
        return blasterKillsAvgPer10Min;
    }

    public void setBlasterKillsAvgPer10Min(float blasterKillsAvgPer10Min) {
        this.blasterKillsAvgPer10Min = blasterKillsAvgPer10Min;
    }

    public int getBlasterKillsMostInGame() {
        return blasterKillsMostInGame;
    }

    public void setBlasterKillsMostInGame(int blasterKillsMostInGame) {
        this.blasterKillsMostInGame = blasterKillsMostInGame;
    }

    public int getPlayersResurrected() {
        return playersResurrected;
    }

    public void setPlayersResurrected(int playersResurrected) {
        this.playersResurrected = playersResurrected;
    }

    public float getPlayersResurrectedAvgPer10Min() {
        return playersResurrectedAvgPer10Min;
    }

    public void setPlayersResurrectedAvgPer10Min(float playersResurrectedAvgPer10Min) {
        this.playersResurrectedAvgPer10Min = playersResurrectedAvgPer10Min;
    }

    public int getPlayersResurrectedMostInGame() {
        return playersResurrectedMostInGame;
    }

    public void setPlayersResurrectedMostInGame(int playersResurrectedMostInGame) {
        this.playersResurrectedMostInGame = playersResurrectedMostInGame;
    }

    public int getCoalescenceHealing() {
        return coalescenceHealing;
    }

    public void setCoalescenceHealing(int coalescenceHealing) {
        this.coalescenceHealing = coalescenceHealing;
    }

    public int getCoalescenceHealingAvgPer10Min() {
        return coalescenceHealingAvgPer10Min;
    }

    public void setCoalescenceHealingAvgPer10Min(int coalescenceHealingAvgPer10Min) {
        this.coalescenceHealingAvgPer10Min = coalescenceHealingAvgPer10Min;
    }

    public int getCoalescenceHealingMostInGame() {
        return coalescenceHealingMostInGame;
    }

    public void setCoalescenceHealingMostInGame(int coalescenceHealingMostInGame) {
        this.coalescenceHealingMostInGame = coalescenceHealingMostInGame;
    }

    public int getCoalescenceKills() {
        return coalescenceKills;
    }

    public void setCoalescenceKills(int coalescenceKills) {
        this.coalescenceKills = coalescenceKills;
    }

    public float getCoalescenceKillsAvgPer10Min() {
        return coalescenceKillsAvgPer10Min;
    }

    public void setCoalescenceKillsAvgPer10Min(float coalescenceKillsAvgPer10Min) {
        this.coalescenceKillsAvgPer10Min = coalescenceKillsAvgPer10Min;
    }

    public int getCoalescenceKillsMostInGame() {
        return coalescenceKillsMostInGame;
    }

    public void setCoalescenceKillsMostInGame(int coalescenceKillsMostInGame) {
        this.coalescenceKillsMostInGame = coalescenceKillsMostInGame;
    }

    public String getSecondaryFireAccuracy() {
        return secondaryFireAccuracy;
    }

    public void setSecondaryFireAccuracy(String secondaryFireAccuracy) {
        this.secondaryFireAccuracy = secondaryFireAccuracy;
    }

    public int getSuperchargerAssists() {
        return superchargerAssists;
    }

    public void setSuperchargerAssists(int superchargerAssists) {
        this.superchargerAssists = superchargerAssists;
    }

    public float getSuperchargerAssistsAvgPer10Min() {
        return superchargerAssistsAvgPer10Min;
    }

    public void setSuperchargerAssistsAvgPer10Min(float superchargerAssistsAvgPer10Min) {
        this.superchargerAssistsAvgPer10Min = superchargerAssistsAvgPer10Min;
    }

    public int getSuperchargerAssistsMostInGame() {
        return superchargerAssistsMostInGame;
    }

    public void setSuperchargerAssistsMostInGame(int superchargerAssistsMostInGame) {
        this.superchargerAssistsMostInGame = superchargerAssistsMostInGame;
    }

    public int getBarrageKills() {
        return barrageKills;
    }

    public void setBarrageKills(int barrageKills) {
        this.barrageKills = barrageKills;
    }

    public float getBarrageKillsAvgPer10Min() {
        return barrageKillsAvgPer10Min;
    }

    public void setBarrageKillsAvgPer10Min(float barrageKillsAvgPer10Min) {
        this.barrageKillsAvgPer10Min = barrageKillsAvgPer10Min;
    }

    public int getBarrageKillsMostInGame() {
        return barrageKillsMostInGame;
    }

    public void setBarrageKillsMostInGame(int barrageKillsMostInGame) {
        this.barrageKillsMostInGame = barrageKillsMostInGame;
    }

    public String getDirectHitsAccuracy() {
        return directHitsAccuracy;
    }

    public void setDirectHitsAccuracy(String directHitsAccuracy) {
        this.directHitsAccuracy = directHitsAccuracy;
    }

    public int getRocketDirectHits() {
        return rocketDirectHits;
    }

    public void setRocketDirectHits(int rocketDirectHits) {
        this.rocketDirectHits = rocketDirectHits;
    }

    public float getRocketDirectHitsAvgPer10Min() {
        return rocketDirectHitsAvgPer10Min;
    }

    public void setRocketDirectHitsAvgPer10Min(float rocketDirectHitsAvgPer10Min) {
        this.rocketDirectHitsAvgPer10Min = rocketDirectHitsAvgPer10Min;
    }

    public int getRocketDirectHitsMostInGame() {
        return rocketDirectHitsMostInGame;
    }

    public void setRocketDirectHitsMostInGame(int rocketDirectHitsMostInGame) {
        this.rocketDirectHitsMostInGame = rocketDirectHitsMostInGame;
    }

    public int getDeathsBlossomKills() {
        return deathsBlossomKills;
    }

    public void setDeathsBlossomKills(int deathsBlossomKills) {
        this.deathsBlossomKills = deathsBlossomKills;
    }

    public float getDeathsBlossomKillsAvgPer10Min() {
        return deathsBlossomKillsAvgPer10Min;
    }

    public void setDeathsBlossomKillsAvgPer10Min(float deathsBlossomKillsAvgPer10Min) {
        this.deathsBlossomKillsAvgPer10Min = deathsBlossomKillsAvgPer10Min;
    }

    public int getDeathsBlossomKillsMostInGame() {
        return deathsBlossomKillsMostInGame;
    }

    public void setDeathsBlossomKillsMostInGame(int deathsBlossomKillsMostInGame) {
        this.deathsBlossomKillsMostInGame = deathsBlossomKillsMostInGame;
    }

    public int getChargeKills() {
        return chargeKills;
    }

    public void setChargeKills(int chargeKills) {
        this.chargeKills = chargeKills;
    }

    public float getChargeKillsAvgPer10Min() {
        return chargeKillsAvgPer10Min;
    }

    public void setChargeKillsAvgPer10Min(float chargeKillsAvgPer10Min) {
        this.chargeKillsAvgPer10Min = chargeKillsAvgPer10Min;
    }

    public int getChargeKillsMostInGame() {
        return chargeKillsMostInGame;
    }

    public void setChargeKillsMostInGame(int chargeKillsMostInGame) {
        this.chargeKillsMostInGame = chargeKillsMostInGame;
    }

    public int getEarthshatterKills() {
        return earthshatterKills;
    }

    public void setEarthshatterKills(int earthshatterKills) {
        this.earthshatterKills = earthshatterKills;
    }

    public float getEarthshatterKillsAvgPer10Min() {
        return earthshatterKillsAvgPer10Min;
    }

    public void setEarthshatterKillsAvgPer10Min(float earthshatterKillsAvgPer10Min) {
        this.earthshatterKillsAvgPer10Min = earthshatterKillsAvgPer10Min;
    }

    public int getEarthshatterKillsMostInGame() {
        return earthshatterKillsMostInGame;
    }

    public void setEarthshatterKillsMostInGame(int earthshatterKillsMostInGame) {
        this.earthshatterKillsMostInGame = earthshatterKillsMostInGame;
    }

    public int getFireStrikeKills() {
        return fireStrikeKills;
    }

    public void setFireStrikeKills(int fireStrikeKills) {
        this.fireStrikeKills = fireStrikeKills;
    }

    public float getFireStrikeKillsAvgPer10Min() {
        return fireStrikeKillsAvgPer10Min;
    }

    public void setFireStrikeKillsAvgPer10Min(float fireStrikeKillsAvgPer10Min) {
        this.fireStrikeKillsAvgPer10Min = fireStrikeKillsAvgPer10Min;
    }

    public int getFireStrikeKillsMostInGame() {
        return fireStrikeKillsMostInGame;
    }

    public void setFireStrikeKillsMostInGame(int fireStrikeKillsMostInGame) {
        this.fireStrikeKillsMostInGame = fireStrikeKillsMostInGame;
    }

    public String getRocketHammerMeleeAccuracy() {
        return rocketHammerMeleeAccuracy;
    }

    public void setRocketHammerMeleeAccuracy(String rocketHammerMeleeAccuracy) {
        this.rocketHammerMeleeAccuracy = rocketHammerMeleeAccuracy;
    }

    public int getEnemiesHooked() {
        return enemiesHooked;
    }

    public void setEnemiesHooked(int enemiesHooked) {
        this.enemiesHooked = enemiesHooked;
    }

    public float getEnemiesHookedAvgPer10Min() {
        return enemiesHookedAvgPer10Min;
    }

    public void setEnemiesHookedAvgPer10Min(float enemiesHookedAvgPer10Min) {
        this.enemiesHookedAvgPer10Min = enemiesHookedAvgPer10Min;
    }

    public int getEnemiesHookedMostInGame() {
        return enemiesHookedMostInGame;
    }

    public void setEnemiesHookedMostInGame(int enemiesHookedMostInGame) {
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

    public int getHooksAttempted() {
        return hooksAttempted;
    }

    public void setHooksAttempted(int hooksAttempted) {
        this.hooksAttempted = hooksAttempted;
    }

    public int getWholeHogKills() {
        return wholeHogKills;
    }

    public void setWholeHogKills(int wholeHogKills) {
        this.wholeHogKills = wholeHogKills;
    }

    public float getWholeHogKillsAvgPer10Min() {
        return wholeHogKillsAvgPer10Min;
    }

    public void setWholeHogKillsAvgPer10Min(float wholeHogKillsAvgPer10Min) {
        this.wholeHogKillsAvgPer10Min = wholeHogKillsAvgPer10Min;
    }

    public int getWholeHogKillsMostInGame() {
        return wholeHogKillsMostInGame;
    }

    public void setWholeHogKillsMostInGame(int wholeHogKillsMostInGame) {
        this.wholeHogKillsMostInGame = wholeHogKillsMostInGame;
    }

    public int getBioticFieldHealingDone() {
        return bioticFieldHealingDone;
    }

    public void setBioticFieldHealingDone(int bioticFieldHealingDone) {
        this.bioticFieldHealingDone = bioticFieldHealingDone;
    }

    public int getBioticFieldsDeployed() {
        return bioticFieldsDeployed;
    }

    public void setBioticFieldsDeployed(int bioticFieldsDeployed) {
        this.bioticFieldsDeployed = bioticFieldsDeployed;
    }

    public int getHelixRocketsKills() {
        return helixRocketsKills;
    }

    public void setHelixRocketsKills(int helixRocketsKills) {
        this.helixRocketsKills = helixRocketsKills;
    }

    public float getHelixRocketsKillsAvgPer10Min() {
        return helixRocketsKillsAvgPer10Min;
    }

    public void setHelixRocketsKillsAvgPer10Min(float helixRocketsKillsAvgPer10Min) {
        this.helixRocketsKillsAvgPer10Min = helixRocketsKillsAvgPer10Min;
    }

    public int getHelixRocketsKillsMostInGame() {
        return helixRocketsKillsMostInGame;
    }

    public void setHelixRocketsKillsMostInGame(int helixRocketsKillsMostInGame) {
        this.helixRocketsKillsMostInGame = helixRocketsKillsMostInGame;
    }

    public int getTacticalVisorKills() {
        return tacticalVisorKills;
    }

    public void setTacticalVisorKills(int tacticalVisorKills) {
        this.tacticalVisorKills = tacticalVisorKills;
    }

    public float getTacticalVisorKillsAvgPer10Min() {
        return tacticalVisorKillsAvgPer10Min;
    }

    public void setTacticalVisorKillsAvgPer10Min(float tacticalVisorKillsAvgPer10Min) {
        this.tacticalVisorKillsAvgPer10Min = tacticalVisorKillsAvgPer10Min;
    }

    public int getTacticalVisorKillsMostInGame() {
        return tacticalVisorKillsMostInGame;
    }

    public void setTacticalVisorKillsMostInGame(int tacticalVisorKillsMostInGame) {
        this.tacticalVisorKillsMostInGame = tacticalVisorKillsMostInGame;
    }

    public int getEnemiesEmpd() {
        return enemiesEmpd;
    }

    public void setEnemiesEmpd(int enemiesEmpd) {
        this.enemiesEmpd = enemiesEmpd;
    }

    public float getEnemiesEmpdAvgPer10Min() {
        return enemiesEmpdAvgPer10Min;
    }

    public void setEnemiesEmpdAvgPer10Min(float enemiesEmpdAvgPer10Min) {
        this.enemiesEmpdAvgPer10Min = enemiesEmpdAvgPer10Min;
    }

    public int getEnemiesEmpdMostInGame() {
        return enemiesEmpdMostInGame;
    }

    public void setEnemiesEmpdMostInGame(int enemiesEmpdMostInGame) {
        this.enemiesEmpdMostInGame = enemiesEmpdMostInGame;
    }

    public int getEnemiesHacked() {
        return enemiesHacked;
    }

    public void setEnemiesHacked(int enemiesHacked) {
        this.enemiesHacked = enemiesHacked;
    }

    public float getEnemiesHackedAvgPer10Min() {
        return enemiesHackedAvgPer10Min;
    }

    public void setEnemiesHackedAvgPer10Min(float enemiesHackedAvgPer10Min) {
        this.enemiesHackedAvgPer10Min = enemiesHackedAvgPer10Min;
    }

    public int getEnemiesHackedMostInGame() {
        return enemiesHackedMostInGame;
    }

    public void setEnemiesHackedMostInGame(int enemiesHackedMostInGame) {
        this.enemiesHackedMostInGame = enemiesHackedMostInGame;
    }

    public int getPlayersTeleported() {
        return playersTeleported;
    }

    public void setPlayersTeleported(int playersTeleported) {
        this.playersTeleported = playersTeleported;
    }

    public float getPlayersTeleportedAvgPer10Min() {
        return playersTeleportedAvgPer10Min;
    }

    public void setPlayersTeleportedAvgPer10Min(float playersTeleportedAvgPer10Min) {
        this.playersTeleportedAvgPer10Min = playersTeleportedAvgPer10Min;
    }

    public int getPlayersTeleportedMostInGame() {
        return playersTeleportedMostInGame;
    }

    public void setPlayersTeleportedMostInGame(int playersTeleportedMostInGame) {
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

    public int getSentryTurretsKills() {
        return sentryTurretsKills;
    }

    public void setSentryTurretsKills(int sentryTurretsKills) {
        this.sentryTurretsKills = sentryTurretsKills;
    }

    public float getSentryTurretsKillsAvgPer10Min() {
        return sentryTurretsKillsAvgPer10Min;
    }

    public void setSentryTurretsKillsAvgPer10Min(float sentryTurretsKillsAvgPer10Min) {
        this.sentryTurretsKillsAvgPer10Min = sentryTurretsKillsAvgPer10Min;
    }

    public int getSentryTurretsKillsMostInGame() {
        return sentryTurretsKillsMostInGame;
    }

    public void setSentryTurretsKillsMostInGame(int sentryTurretsKillsMostInGame) {
        this.sentryTurretsKillsMostInGame = sentryTurretsKillsMostInGame;
    }

    public int getArmorPacksCreated() {
        return armorPacksCreated;
    }

    public void setArmorPacksCreated(int armorPacksCreated) {
        this.armorPacksCreated = armorPacksCreated;
    }

    public float getArmorPacksCreatedAvgPer10Min() {
        return armorPacksCreatedAvgPer10Min;
    }

    public void setArmorPacksCreatedAvgPer10Min(float armorPacksCreatedAvgPer10Min) {
        this.armorPacksCreatedAvgPer10Min = armorPacksCreatedAvgPer10Min;
    }

    public int getArmorPacksCreatedMostInGame() {
        return armorPacksCreatedMostInGame;
    }

    public void setArmorPacksCreatedMostInGame(int armorPacksCreatedMostInGame) {
        this.armorPacksCreatedMostInGame = armorPacksCreatedMostInGame;
    }

    public int getMoltenCoreKills() {
        return moltenCoreKills;
    }

    public void setMoltenCoreKills(int moltenCoreKills) {
        this.moltenCoreKills = moltenCoreKills;
    }

    public float getMoltenCoreKillsAvgPer10Min() {
        return moltenCoreKillsAvgPer10Min;
    }

    public void setMoltenCoreKillsAvgPer10Min(float moltenCoreKillsAvgPer10Min) {
        this.moltenCoreKillsAvgPer10Min = moltenCoreKillsAvgPer10Min;
    }

    public int getMoltenCoreKillsMostInGame() {
        return moltenCoreKillsMostInGame;
    }

    public void setMoltenCoreKillsMostInGame(int moltenCoreKillsMostInGame) {
        this.moltenCoreKillsMostInGame = moltenCoreKillsMostInGame;
    }

    public int getOverloadKills() {
        return overloadKills;
    }

    public void setOverloadKills(int overloadKills) {
        this.overloadKills = overloadKills;
    }

    public int getOverloadKillsMostInGame() {
        return overloadKillsMostInGame;
    }

    public void setOverloadKillsMostInGame(int overloadKillsMostInGame) {
        this.overloadKillsMostInGame = overloadKillsMostInGame;
    }

    public int getTorbjornKills() {
        return torbjornKills;
    }

    public void setTorbjornKills(int torbjornKills) {
        this.torbjornKills = torbjornKills;
    }

    public float getTorbjornKillsAvgPer10Min() {
        return torbjornKillsAvgPer10Min;
    }

    public void setTorbjornKillsAvgPer10Min(float torbjornKillsAvgPer10Min) {
        this.torbjornKillsAvgPer10Min = torbjornKillsAvgPer10Min;
    }

    public int getTorbjornKillsMostInGame() {
        return torbjornKillsMostInGame;
    }

    public void setTorbjornKillsMostInGame(int torbjornKillsMostInGame) {
        this.torbjornKillsMostInGame = torbjornKillsMostInGame;
    }

    public int getTurretsDamageAvgPer10Min() {
        return turretsDamageAvgPer10Min;
    }

    public void setTurretsDamageAvgPer10Min(int turretsDamageAvgPer10Min) {
        this.turretsDamageAvgPer10Min = turretsDamageAvgPer10Min;
    }

    public int getTurretsKills() {
        return turretsKills;
    }

    public void setTurretsKills(int turretsKills) {
        this.turretsKills = turretsKills;
    }

    public float getTurretsKillsAvgPer10Min() {
        return turretsKillsAvgPer10Min;
    }

    public void setTurretsKillsAvgPer10Min(float turretsKillsAvgPer10Min) {
        this.turretsKillsAvgPer10Min = turretsKillsAvgPer10Min;
    }

    public int getTurretsKillsMostInGame() {
        return turretsKillsMostInGame;
    }

    public void setTurretsKillsMostInGame(int turretsKillsMostInGame) {
        this.turretsKillsMostInGame = turretsKillsMostInGame;
    }

    public int getHealthRecovered() {
        return healthRecovered;
    }

    public void setHealthRecovered(int healthRecovered) {
        this.healthRecovered = healthRecovered;
    }

    public int getHealthRecoveredAvgPer10Min() {
        return healthRecoveredAvgPer10Min;
    }

    public void setHealthRecoveredAvgPer10Min(int healthRecoveredAvgPer10Min) {
        this.healthRecoveredAvgPer10Min = healthRecoveredAvgPer10Min;
    }

    public int getHealthRecoveredMostInGame() {
        return healthRecoveredMostInGame;
    }

    public void setHealthRecoveredMostInGame(int healthRecoveredMostInGame) {
        this.healthRecoveredMostInGame = healthRecoveredMostInGame;
    }

    public int getPulseBombsAttached() {
        return pulseBombsAttached;
    }

    public void setPulseBombsAttached(int pulseBombsAttached) {
        this.pulseBombsAttached = pulseBombsAttached;
    }

    public float getPulseBombsAttachedAvgPer10Min() {
        return pulseBombsAttachedAvgPer10Min;
    }

    public void setPulseBombsAttachedAvgPer10Min(float pulseBombsAttachedAvgPer10Min) {
        this.pulseBombsAttachedAvgPer10Min = pulseBombsAttachedAvgPer10Min;
    }

    public int getPulseBombsAttachedMostInGame() {
        return pulseBombsAttachedMostInGame;
    }

    public void setPulseBombsAttachedMostInGame(int pulseBombsAttachedMostInGame) {
        this.pulseBombsAttachedMostInGame = pulseBombsAttachedMostInGame;
    }

    public int getPulseBombsKills() {
        return pulseBombsKills;
    }

    public void setPulseBombsKills(int pulseBombsKills) {
        this.pulseBombsKills = pulseBombsKills;
    }

    public float getPulseBombsKillsAvgPer10Min() {
        return pulseBombsKillsAvgPer10Min;
    }

    public void setPulseBombsKillsAvgPer10Min(float pulseBombsKillsAvgPer10Min) {
        this.pulseBombsKillsAvgPer10Min = pulseBombsKillsAvgPer10Min;
    }

    public int getPulseBombsKillsMostInGame() {
        return pulseBombsKillsMostInGame;
    }

    public void setPulseBombsKillsMostInGame(int pulseBombsKillsMostInGame) {
        this.pulseBombsKillsMostInGame = pulseBombsKillsMostInGame;
    }

    public int getVenomMineKills() {
        return venomMineKills;
    }

    public void setVenomMineKills(int venomMineKills) {
        this.venomMineKills = venomMineKills;
    }

    public float getVenomMineKillsAvgPer10Min() {
        return venomMineKillsAvgPer10Min;
    }

    public void setVenomMineKillsAvgPer10Min(float venomMineKillsAvgPer10Min) {
        this.venomMineKillsAvgPer10Min = venomMineKillsAvgPer10Min;
    }

    public int getVenomMineKillsMostInGame() {
        return venomMineKillsMostInGame;
    }

    public void setVenomMineKillsMostInGame(int venomMineKillsMostInGame) {
        this.venomMineKillsMostInGame = venomMineKillsMostInGame;
    }

    public int getJumpKills() {
        return jumpKills;
    }

    public void setJumpKills(int jumpKills) {
        this.jumpKills = jumpKills;
    }

    public int getJumpPackKills() {
        return jumpPackKills;
    }

    public void setJumpPackKills(int jumpPackKills) {
        this.jumpPackKills = jumpPackKills;
    }

    public float getJumpPackKillsAvgPer10Min() {
        return jumpPackKillsAvgPer10Min;
    }

    public void setJumpPackKillsAvgPer10Min(float jumpPackKillsAvgPer10Min) {
        this.jumpPackKillsAvgPer10Min = jumpPackKillsAvgPer10Min;
    }

    public int getJumpPackKillsMostInGame() {
        return jumpPackKillsMostInGame;
    }

    public void setJumpPackKillsMostInGame(int jumpPackKillsMostInGame) {
        this.jumpPackKillsMostInGame = jumpPackKillsMostInGame;
    }

    public int getMeleeKills() {
        return meleeKills;
    }

    public void setMeleeKills(int meleeKills) {
        this.meleeKills = meleeKills;
    }

    public float getMeleeKillsAvgPer10Min() {
        return meleeKillsAvgPer10Min;
    }

    public void setMeleeKillsAvgPer10Min(float meleeKillsAvgPer10Min) {
        this.meleeKillsAvgPer10Min = meleeKillsAvgPer10Min;
    }

    public int getMeleeKillsMostInGame() {
        return meleeKillsMostInGame;
    }

    public void setMeleeKillsMostInGame(int meleeKillsMostInGame) {
        this.meleeKillsMostInGame = meleeKillsMostInGame;
    }

    public int getPlayersKnockedBack() {
        return playersKnockedBack;
    }

    public void setPlayersKnockedBack(int playersKnockedBack) {
        this.playersKnockedBack = playersKnockedBack;
    }

    public float getPlayersKnockedBackAvgPer10Min() {
        return playersKnockedBackAvgPer10Min;
    }

    public void setPlayersKnockedBackAvgPer10Min(float playersKnockedBackAvgPer10Min) {
        this.playersKnockedBackAvgPer10Min = playersKnockedBackAvgPer10Min;
    }

    public int getPlayersKnockedBackMostInGame() {
        return playersKnockedBackMostInGame;
    }

    public void setPlayersKnockedBackMostInGame(int playersKnockedBackMostInGame) {
        this.playersKnockedBackMostInGame = playersKnockedBackMostInGame;
    }

    public int getPrimalRageKills() {
        return primalRageKills;
    }

    public void setPrimalRageKills(int primalRageKills) {
        this.primalRageKills = primalRageKills;
    }

    public float getPrimalRageKillsAvgPer10Min() {
        return primalRageKillsAvgPer10Min;
    }

    public void setPrimalRageKillsAvgPer10Min(float primalRageKillsAvgPer10Min) {
        this.primalRageKillsAvgPer10Min = primalRageKillsAvgPer10Min;
    }

    public int getPrimalRageKillsMostInGame() {
        return primalRageKillsMostInGame;
    }

    public void setPrimalRageKillsMostInGame(int primalRageKillsMostInGame) {
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

    public int getWeaponKills() {
        return weaponKills;
    }

    public void setWeaponKills(int weaponKills) {
        this.weaponKills = weaponKills;
    }

    public int getGrapplingClawKills() {
        return grapplingClawKills;
    }

    public void setGrapplingClawKills(int grapplingClawKills) {
        this.grapplingClawKills = grapplingClawKills;
    }

    public float getGrapplingClawKillsAvgPer10Min() {
        return grapplingClawKillsAvgPer10Min;
    }

    public void setGrapplingClawKillsAvgPer10Min(float grapplingClawKillsAvgPer10Min) {
        this.grapplingClawKillsAvgPer10Min = grapplingClawKillsAvgPer10Min;
    }

    public int getGrapplingClawKillsMostInGame() {
        return grapplingClawKillsMostInGame;
    }

    public void setGrapplingClawKillsMostInGame(int grapplingClawKillsMostInGame) {
        this.grapplingClawKillsMostInGame = grapplingClawKillsMostInGame;
    }

    public int getMinefieldKills() {
        return minefieldKills;
    }

    public void setMinefieldKills(int minefieldKills) {
        this.minefieldKills = minefieldKills;
    }

    public float getMinefieldKillsAvgPer10Min() {
        return minefieldKillsAvgPer10Min;
    }

    public void setMinefieldKillsAvgPer10Min(float minefieldKillsAvgPer10Min) {
        this.minefieldKillsAvgPer10Min = minefieldKillsAvgPer10Min;
    }

    public int getMinefieldKillsMostInGame() {
        return minefieldKillsMostInGame;
    }

    public void setMinefieldKillsMostInGame(int minefieldKillsMostInGame) {
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

    public int getGravitonSurgeKills() {
        return gravitonSurgeKills;
    }

    public void setGravitonSurgeKills(int gravitonSurgeKills) {
        this.gravitonSurgeKills = gravitonSurgeKills;
    }

    public float getGravitonSurgeKillsAvgPer10Min() {
        return gravitonSurgeKillsAvgPer10Min;
    }

    public void setGravitonSurgeKillsAvgPer10Min(float gravitonSurgeKillsAvgPer10Min) {
        this.gravitonSurgeKillsAvgPer10Min = gravitonSurgeKillsAvgPer10Min;
    }

    public int getGravitonSurgeKillsMostInGame() {
        return gravitonSurgeKillsMostInGame;
    }

    public void setGravitonSurgeKillsMostInGame(int gravitonSurgeKillsMostInGame) {
        this.gravitonSurgeKillsMostInGame = gravitonSurgeKillsMostInGame;
    }

    public int getHighEnergyKills() {
        return highEnergyKills;
    }

    public void setHighEnergyKills(int highEnergyKills) {
        this.highEnergyKills = highEnergyKills;
    }

    public float getHighEnergyKillsAvgPer10Min() {
        return highEnergyKillsAvgPer10Min;
    }

    public void setHighEnergyKillsAvgPer10Min(float highEnergyKillsAvgPer10Min) {
        this.highEnergyKillsAvgPer10Min = highEnergyKillsAvgPer10Min;
    }

    public int getHighEnergyKillsMostInGame() {
        return highEnergyKillsMostInGame;
    }

    public void setHighEnergyKillsMostInGame(int highEnergyKillsMostInGame) {
        this.highEnergyKillsMostInGame = highEnergyKillsMostInGame;
    }

    public int getProjectedBarriersApplied() {
        return projectedBarriersApplied;
    }

    public void setProjectedBarriersApplied(int projectedBarriersApplied) {
        this.projectedBarriersApplied = projectedBarriersApplied;
    }

    public float getProjectedBarriersAppliedAvgPer10Min() {
        return projectedBarriersAppliedAvgPer10Min;
    }

    public void setProjectedBarriersAppliedAvgPer10Min(float projectedBarriersAppliedAvgPer10Min) {
        this.projectedBarriersAppliedAvgPer10Min = projectedBarriersAppliedAvgPer10Min;
    }

    public int getProjectedBarriersAppliedMostInGame() {
        return projectedBarriersAppliedMostInGame;
    }

    public void setProjectedBarriersAppliedMostInGame(int projectedBarriersAppliedMostInGame) {
        this.projectedBarriersAppliedMostInGame = projectedBarriersAppliedMostInGame;
    }

    public int getTranscendenceHealing() {
        return transcendenceHealing;
    }

    public void setTranscendenceHealing(int transcendenceHealing) {
        this.transcendenceHealing = transcendenceHealing;
    }

    public int getTranscendenceHealingBest() {
        return transcendenceHealingBest;
    }

    public void setTranscendenceHealingBest(int transcendenceHealingBest) {
        this.transcendenceHealingBest = transcendenceHealingBest;
    }
}
