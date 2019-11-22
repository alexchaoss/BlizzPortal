package com.BlizzardArmory.warcraft;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ClassEnum {
    WARRIOR("Warrior"), PALADIN("Paladin"), HUNTER("Hunter"), ROGUE("Rogue"),
    PRIEST("Priest"), DEATHKNIGHT("Death Knight"), SHAMAN("Shaman"), MAGE("Mage"),
    WARLOCK("Warlock"), MONK("Monk"), DRUID("Druid"), DEMONHUNTER("Demon Hunter");

    private static final Map<Integer, ClassEnum> lookup = new HashMap<Integer, ClassEnum>();

    static {
        int ordinal = 0;
        for (ClassEnum woWClassEnum : EnumSet.allOf(ClassEnum.class)) {
            lookup.put(ordinal, woWClassEnum);
            ordinal += 1;
        }
    }

    private String className;

    ClassEnum(String className) {
        this.className = className;
    }

    public static ClassEnum fromOrdinal(int ordinal) {
        return lookup.get(ordinal);
    }

    @Override
    public String toString() {
        return className;
    }
}
