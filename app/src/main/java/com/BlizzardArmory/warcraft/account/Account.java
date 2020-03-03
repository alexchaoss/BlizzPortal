
package com.BlizzardArmory.warcraft.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<WowAccount> getWowAccounts() {
        return wowAccounts;
    }

    public void setWowAccounts(List<WowAccount> wowAccounts) {
        this.wowAccounts = wowAccounts;
    }

    public Collections getCollections() {
        return collections;
    }

    public void setCollections(Collections collections) {
        this.collections = collections;
    }

}
