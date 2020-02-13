
package com.BlizzardArmory.warcraft.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class Characters {

    @SerializedName("characters")
    @Expose
    private List<Character> characters = null;

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public void sortInfo() {

        Collections.sort(characters, (a, b) -> {
            if (a.getLevel() > b.getLevel()) {
                return -1;
            }
            return 0;
        });

        Collections.sort(characters, (rm1, rm2) -> {
            if (rm1.getRealm().compareTo(rm2.getRealm()) < 0) {
                return -1;
            }
            return 0;
        });
    }

}
