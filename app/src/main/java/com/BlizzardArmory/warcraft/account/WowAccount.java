
package com.BlizzardArmory.warcraft.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Wow account.
 */
public class WowAccount {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("characters")
    @Expose
    private List<Character> characters = null;

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets characters.
     *
     * @return the characters
     */
    public List<Character> getCharacters() {
        return characters;
    }

    /**
     * Sets characters.
     *
     * @param characters the characters
     */
    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

}
