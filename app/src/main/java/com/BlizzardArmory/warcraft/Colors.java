package com.BlizzardArmory.warcraft;

public enum Colors {
    POOR("#9d9d9d"), COMMON("#ffffff"), UNCOMMON("#1eff00"), RARE("#0070dd"), EPIC("#a335ee"), LEGENDARY("#ff8000"), ARTIFACT("#e6cc80"), HEIRLOOM("#00ccff");

    String color;

    Colors(String color){
        this.color = color;
    }
}
