package com.BlizzardArmory.warcraft;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum WowClassEnum {
    WARRIOR("Warrior"), PALADIN("Paladin"), HUNTER("Hunter"), ROGUE("Rogue"),
    PRIEST("Priest"), DEATHKNIGHT("Death Knight"), SHAMAN("Shaman"), MAGE("Mage"),
    WARLOCK("Warlock"), MONK("Monk"), DRUID("Druid"), DEMONHUNTER("Demon Hunter");

    private String className;

    private static final Map<Integer, WowClassEnum> lookup = new HashMap<Integer, WowClassEnum>();

    WowClassEnum(String className) {
        this.className = className;
    }

    static {
        int ordinal = 0;
        for (WowClassEnum woWClassEnum : EnumSet.allOf(WowClassEnum.class)) {
            lookup.put(ordinal, woWClassEnum);
            ordinal += 1;
        }
    }

    public static WowClassEnum fromOrdinal(int ordinal) {
        return lookup.get(ordinal);
    }

    @Override
    public String toString() {
        return className;
    }
}
