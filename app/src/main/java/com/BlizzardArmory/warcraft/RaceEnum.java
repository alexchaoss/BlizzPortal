package com.BlizzardArmory.warcraft;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum RaceEnum {
    HUMAN("Human", 0), ORC("Orc", 1), DWARF("Dwarf", 0), NIGHT_ELF("Night Elf", 0), UNDEAD("Undead", 1), TAUREN("Tauren", 1),
    GNOME("Gnome", 0), TROLL("Troll", 1), GOBLIN("Goblin", 1), BLOOD_ELF("Blood Elf", 1), DRAENEI("Draenei", 0),
    PLACEHOLDER12("",-1), PLACEHOLDER13("", -1), PLACEHOLDER14("", -1), PLACEHOLDER15("", -1), PLACEHOLDER16("", -1),
    PLACEHOLDER17("", -1), PLACEHOLDER18("", -1), PLACEHOLDER19("", -1), PLACEHOLDER20("", -1), PLACEHOLDER21("", -1),
    WORGEN("Worgen", 0), PLACEHOLDER23("", -1), PANDAREN1("Pandaren", -1), PANDAREN2("Pandaren", 0), PANDAREN3("Pandaren", 1),
    NIGHTBORNE("Nightborne", 1), HIGHMOUNTAIN_TAUREN("Highmountain Tauren", 1), VOID_ELF("Void Elf", 0), LIGHTFORGED_DRAENEI("Lightforged Draenei", 0),
    ZANDALARI_TROLL("Zandalari Troll", 1), KUL_TIRAN("Kul Tiran", 0), PLACEHOLDER33("", -1), DARK_IRON_DWARF("Dark Iron Dwarf", 0),
    PLACEHOLDER35("", -1), MAG_HAR_ORC("Mag'har Orc", 1);

    private String race;
    private int faction;

    private static final Map<Integer, RaceEnum> lookup = new HashMap<Integer, RaceEnum>();

    RaceEnum(String race, int faction) {
        this.race = race;
        this.faction = faction;
    }

    static {
        int ordinal = 0;
        for (RaceEnum raceEnum : EnumSet.allOf(RaceEnum.class)) {
            lookup.put(ordinal, raceEnum);
            ordinal += 1;
        }
    }

    public static String fromOrdinal(int ordinal) {
        return lookup.get(ordinal).race;
    }

    public static int fromOrdinalFaction(int ordinal){
        return lookup.get(ordinal).faction;
    }

    @Override
    public String toString() {
        return race;
    }
}
