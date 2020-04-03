package com.BlizzardArmory.warcraft.talents.specializationdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Specialization data.
 */
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
     * Instantiates a new Specialization data.
     *
     * @param links             the links
     * @param id                the id
     * @param playableClass     the playable class
     * @param name              the name
     * @param genderDescription the gender description
     * @param media             the media
     * @param role              the role
     * @param talentTiers       the talent tiers
     * @param pvpTalents        the pvp talents
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
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets playable class.
     *
     * @return the playable class
     */
    public PlayableClass getPlayableClass() {
        return playableClass;
    }

    /**
     * Sets playable class.
     *
     * @param playableClass the playable class
     */
    public void setPlayableClass(PlayableClass playableClass) {
        this.playableClass = playableClass;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets gender description.
     *
     * @return the gender description
     */
    public GenderDescription getGenderDescription() {
        return genderDescription;
    }

    /**
     * Sets gender description.
     *
     * @param genderDescription the gender description
     */
    public void setGenderDescription(GenderDescription genderDescription) {
        this.genderDescription = genderDescription;
    }

    /**
     * Gets media.
     *
     * @return the media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * Sets media.
     *
     * @param media the media
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets talent tiers.
     *
     * @return the talent tiers
     */
    public List<TalentTier> getTalentTiers() {
        return talentTiers;
    }

    /**
     * Sets talent tiers.
     *
     * @param talentTiers the talent tiers
     */
    public void setTalentTiers(List<TalentTier> talentTiers) {
        this.talentTiers = talentTiers;
    }

    /**
     * Gets pvp talents.
     *
     * @return the pvp talents
     */
    public List<PvpTalent> getPvpTalents() {
        return pvpTalents;
    }

    /**
     * Sets pvp talents.
     *
     * @param pvpTalents the pvp talents
     */
    public void setPvpTalents(List<PvpTalent> pvpTalents) {
        this.pvpTalents = pvpTalents;
    }

}
