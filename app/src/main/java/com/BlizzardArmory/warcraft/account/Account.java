
package com.BlizzardArmory.warcraft.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Account.
 */
public class Account {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("wow_accounts")
    @Expose
    private List<WowAccount> wowAccounts = null;
    @SerializedName("collections")
    @Expose
    private Collections collections;

    /**
     * Gets links.
     *
     * @return the links
     */
    public Links getLinks() {
        return links;
    }

    /**
     * Sets links.
     *
     * @param links the links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

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
     * Gets wow accounts.
     *
     * @return the wow accounts
     */
    public List<WowAccount> getWowAccounts() {
        return wowAccounts;
    }

    /**
     * Sets wow accounts.
     *
     * @param wowAccounts the wow accounts
     */
    public void setWowAccounts(List<WowAccount> wowAccounts) {
        this.wowAccounts = wowAccounts;
    }

    /**
     * Gets collections.
     *
     * @return the collections
     */
    public Collections getCollections() {
        return collections;
    }

    /**
     * Sets collections.
     *
     * @param collections the collections
     */
    public void setCollections(Collections collections) {
        this.collections = collections;
    }

}
