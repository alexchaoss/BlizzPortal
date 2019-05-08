package com.example.blizzardprofiles;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum WoWClassEnum {
    WARRIOR( "Warrior"), PALADIN( "Paladin"), HUNTER( "Hunter"), ROGUE( "Rogue"),
    PRIEST( "Priest"), DEATHKNIGHT( "Death Knight"), SHAMAN( "Shaman"),MAGE("Mage"),
    WARLOCK( "Warlock"), MONK( "Monk"), DRUID( "Druid"), DEMONHUNTER( "Demon Hunter");

    private String className;

    private static final Map<Integer, WoWClassEnum> lookup = new HashMap<Integer, WoWClassEnum>();

    WoWClassEnum(String className){
        this.className = className;
    }

    static {
        int ordinal = 0;
        for (WoWClassEnum woWClassEnum : EnumSet.allOf(WoWClassEnum.class)) {
            lookup.put(ordinal, woWClassEnum);
            ordinal += 1;
        }
    }

    public static WoWClassEnum fromOrdinal(int ordinal) {
        return lookup.get(ordinal);
    }

    @Override
    public String toString() {
        return className;
    }
}
