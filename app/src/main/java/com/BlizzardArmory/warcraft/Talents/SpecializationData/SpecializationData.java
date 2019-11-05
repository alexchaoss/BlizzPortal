package com.BlizzardArmory.warcraft.Talents.SpecializationData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpecializationData {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("playable_class")
    @Expose
    private PlayableClass playableClass;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender_description")
    @Expose
    private GenderDescription genderDescription;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("role")
    @Expose
    private Role role;
    @SerializedName("talent_tiers")
    @Expose
    private List<TalentTier> talentTiers = null;
    @SerializedName("pvp_talents")
    @Expose
    private List<PvpTalent> pvpTalents = null;

    /**
     * No args constructor for use in serialization
     */
    public SpecializationData() {
    }

    /**
     * @param genderDescription
     * @param role
     * @param name
     * @param links
     * @param id
     * @param playableClass
     * @param media
     * @param pvpTalents
     * @param talentTiers
     */
    public SpecializationData(Links links, int id, PlayableClass playableClass, String name, GenderDescription genderDescription, Media media, Role role, List<TalentTier> talentTiers, List<PvpTalent> pvpTalents) {
        super();
        this.links = links;
        this.id = id;
        this.playableClass = playableClass;
        this.name = name;
        this.genderDescription = genderDescription;
        this.media = media;
        this.role = role;
        this.talentTiers = talentTiers;
        this.pvpTalents = pvpTalents;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayableClass getPlayableClass() {
        return playableClass;
    }

    public void setPlayableClass(PlayableClass playableClass) {
        this.playableClass = playableClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderDescription getGenderDescription() {
        return genderDescription;
    }

    public void setGenderDescription(GenderDescription genderDescription) {
        this.genderDescription = genderDescription;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<TalentTier> getTalentTiers() {
        return talentTiers;
    }

    public void setTalentTiers(List<TalentTier> talentTiers) {
        this.talentTiers = talentTiers;
    }

    public List<PvpTalent> getPvpTalents() {
        return pvpTalents;
    }

    public void setPvpTalents(List<PvpTalent> pvpTalents) {
        this.pvpTalents = pvpTalents;
    }

}
