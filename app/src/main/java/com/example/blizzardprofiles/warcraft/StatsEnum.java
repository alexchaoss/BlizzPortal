package com.example.blizzardprofiles.warcraft;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum StatsEnum {
    None(""), Mana("Mana"), Health("Health"), Agility("Agility"), Strength("Strength"), Intellect("Intellect"),
    Spirit("Spirit"), Stamina("Stamina"), DefenseSkill("Defense Skill"), Dodge("Dodge"), Parry("Parry"), Block("Block"),
    MeleeHit("Melee Hit"), RangedHit("Ranged Hit"), SpellHit("Spell Hit"), MeleeCrit("Melee Crit"),
    RangedCrit("Ranged Crit"), SpellCrit("Spell Crit"), MeleeHitTaken("Melee Hit Taken"), RangedHitTaken("Range dHit Taken"),
    SpellHitTaken("Spell Hit Taken"), MeleeCritTaken("Melee Crit Taken"), RangedCritTaken("Ranged Crit Taken"), SpellCritTaken("Spell Crit Taken"),
    MeleeHaste("Melee Haste"), RangedHaste("Ranged Haste"), SpellHaste("Spell Haste"), Hit("Hit"), Crit("Crit"), HitTaken("Hit Taken"),
    CritTaken("Crit Taken"), Resilience("Resilience"), Haste("Haste"), Expertise("Expertise"), AttackPower("Attack Power"),
    RangedAttackPower("Ranged Attack Power"), Versatility("Versatility"), ManaRegeneration("Mana Regeneration"), ArmorPenetration("Armor Penetration"),
    SpellPower("Spell Power"), HealthRegen("Health Regen"), SpellPenetration("Spell Penetration"), BlockValue("Block Value"), Mastery("Mastery"),
    BonusArmor("Bonus Armor"), FireResistance("Fire Resistance"), FrostResistance("Frost Resistance"), HolyResistance("HolyR esistance"),
    ShadowResistance("Shadow Resistance"), NatureResistance("Nature Resistance"), ArcaneResistance("Arcane Resistance"), PVPPower("PVP Power"),
    Amplify("Amplify"), Multistrike("Multistrike"), Readiness("Readiness"), Speed("Speed"), Leech("Leech"), Avoidence("Avoidence"),
    Indestructible("Indestructible"), WOD_5("WOD_5"), Cleave("Cleave"), StrengthAgilityIntellect("Strength or Agility or Intellect"),
    StrengthAgility("Strength or Agility"), AgilityIntellect("Agility or Intellect"), StrengthIntellect("Strength or Intellect");

    private static final Map<Integer, StatsEnum> lookup = new HashMap<Integer, StatsEnum>();
    private String name;

    StatsEnum(String name){
        this.name = name;
    }

    static {
        int ordinal = 0;
        for (StatsEnum statsEnum : EnumSet.allOf(StatsEnum.class)) {
            lookup.put(ordinal, statsEnum);
            ordinal += 1;
        }
    }

    public static StatsEnum fromOrdinal(int ordinal) {
        return lookup.get(ordinal);
    }

    @Override
    public String toString() {
        return name;
    }
}

