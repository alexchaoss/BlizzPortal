package com.example.blizzardprofiles.warcraft;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum StatsEnum {
    None(), Mana(), Health(), Agility(), Strength(), Intellect(), Spirit(), Stamina(), DefenseSkill(), Dodge(), Parry(), Block(), MeleeHit(), RangedHit(), SpellHit(), MeleeCrit(),
    RangedCrit(), SpellCrit(), MeleeHitTaken(), RangedHitTaken(), SpellHitTaken(), MeleeCritTaken(), RangedCritTaken(), SpellCritTaken(),
    MeleeHaste(), RangedHaste(), SpellHaste(), Hit(), Crit(), HitTaken(), CritTaken(), Resilience(), Haste(), Expertise(), AttackPower(), RangedAttackPower(), Versatility(),
    ManaRegeneration(), ArmorPenetration(), SpellPower(), HealthRegen(), SpellPenetration(), BlockValue(), Mastery(), BonusArmor(),
    FireResistance(), FrostResistance(), HolyResistance(), ShadowResistance(), NatureResistance(), ArcaneResistance(), PVPPower(), Amplify(), Multistrike(), Readiness(), Speed(),
    Leech(), Avoidence(), Indestructible(), WOD_5(), Cleave(), StrengthAgilityIntellect(), StrengthAgility(), AgilityIntellect(), StrengthIntellect();

    private static final Map<Integer, StatsEnum> lookup = new HashMap<Integer, StatsEnum>();

    static {
        int ordinal = -1;
        for (StatsEnum statsEnum : EnumSet.allOf(StatsEnum.class)) {
            lookup.put(ordinal, statsEnum);
            ordinal += 1;
        }
    }

    public static StatsEnum fromOrdinal(int ordinal) {
        return lookup.get(ordinal);
    }
}

