
package com.BlizzardArmory.warcraft.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Realm {

    @SerializedName("key")
    @Expose
    private Key_ key;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("slug")
    @Expose
    private String slug;

    public Key_ getKey() {
        return key;
    }

    public void setKey(Key_ key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}
