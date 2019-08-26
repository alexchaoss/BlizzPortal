package com.BlizzardArmory.warcraft;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum StatsEnum {
    None("", -1),
    Mana("Mana", 0),
    Health("Health", 1),
    PlaceHolder1("p1", 2),
    Agility("Agility", 3),
    Strength("Strength", 4),
    Intellect("Intellect", 5),
    Spirit("Spirit", 6),
    Stamina("Stamina", 7),
    PlaceHolder2("p2", 8),
    PlaceHolder3("p3", 9),
    PlaceHolder4("p4", 10),
    PlaceHolder5("p5", 11),
    DefenseSkill("Defense Skill", 12),
    Dodge("Dodge", 13),
    Parry("Parry", 14),
    Block("Block", 15),
    MeleeHit("Melee Hit", 16),
    RangedHit("Ranged Hit", 17),
    SpellHit("Spell Hit", 18),
    MeleeCrit("Melee Crit", 19),
    RangedCrit("Ranged Crit", 20),
    SpellCrit("Spell Crit", 21),
    MeleeHitTaken("Melee Hit Taken", 22),
    RangedHitTaken("Range dHit Taken", 23),
    SpellHitTaken("Spell Hit Taken", 24),
    MeleeCritTaken("Melee Crit Taken", 25),
    RangedCritTaken("Ranged Crit Taken", 26),
    SpellCritTaken("Spell Crit Taken", 27),
    MeleeHaste("Melee Haste", 28),
    RangedHaste("Ranged Haste", 29),
    SpellHaste("Spell Haste", 30),
    Hit("Hit", 31),
    Crit("Critical Strike", 32),
    HitTaken("Hit Taken", 33),
    CritTaken("Crit Taken", 34),
    Resilience("Resilience", 35),
    Haste("Haste", 36),
    Expertise("Expertise", 37),
    AttackPower("Attack Power", 38),
    RangedAttackPower("Ranged Attack Power", 39),
    Versatility("Versatility", 40),
    SpellHealingDone("Spell Healing Done", 41),
    SpellDamageDone("Spell Damage Done", 42),
    ManaRegeneration("Mana Regeneration", 43),
    ArmorPenetration("Armor Penetration", 44),
    SpellPower("Spell Power", 45),
    HealthRegen("Health Regeneration", 46),
    SpellPenetration("Spell Penetration", 47),
    BlockValue("Block Value", 48),
    Mastery("Mastery", 49),
    BonusArmor("Bonus Armor", 50),
    FireResistance("Fire Resistance", 51),
    FrostResistance("Frost Resistance", 52),
    HolyResistance("Holy Resistance", 53),
    ShadowResistance("Shadow Resistance", 54),
    NatureResistance("Nature Resistance", 55),
    ArcaneResistance("Arcane Resistance", 56),
    PVPPower("PVP Power", 57),
    Amplify("Amplify", 58),
    Multistrike("Multistrike", 59),
    Readiness("Readiness", 60),
    Speed("Speed", 61),
    Leech("Leech", 62),
    Avoidance("Avoidance", 63),
    Indestructible("Indestructible", 64),
    WOD_5("WOD_5", 65),
    Cleave("Cleave", 66),
    PlaceHolder6("p6", 67),
    PlaceHolder7("p7", 68),
    PlaceHolder8("p8", 69),
    PlaceHolder9("p9", 70),
    StrengthAgilityIntellect("Agility or Strength or Intellect", 71),
    StrengthAgility("Agility or Strength ", 72),
    AgilityIntellect("Agility or Intellect", 73),
    StrengthIntellect("Strength or Intellect", 74);

    private static final Map<Integer, StatsEnum> lookup = new HashMap<Integer, StatsEnum>();
    private String name;
    private int index;

    StatsEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

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

    @Override
    public String toString() {
        return name;
    }
}

