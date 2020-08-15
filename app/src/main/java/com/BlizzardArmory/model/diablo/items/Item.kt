package com.BlizzardArmory.model.diablo.items

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.apache.commons.lang3.builder.ToStringBuilder

/**
 * The type Item.
 */
open class Item {
    /**
     * Gets id.
     *
     * @return the id
     */
    /**
     * Sets id.
     *
     * @param id the id
     */
    @SerializedName("id")
    @Expose
    var id: String? = null

    /**
     * Gets name.
     *
     * @return the name
     */
    /**
     * Sets name.
     *
     * @param name the name
     */
    @SerializedName("name")
    @Expose
    var name: String? = null

    /**
     * Gets icon.
     *
     * @return the icon
     */
    /**
     * Sets icon.
     *
     * @param icon the icon
     */
    @SerializedName("icon")
    @Expose
    var icon: String? = null

    /**
     * Gets display color.
     *
     * @return the display color
     */
    /**
     * Sets display color.
     *
     * @param displayColor the display color
     */
    @SerializedName("displayColor")
    @Expose
    var displayColor: String? = null

    /**
     * Gets tooltip params.
     *
     * @return the tooltip params
     */
    /**
     * Sets tooltip params.
     *
     * @param tooltipParams the tooltip params
     */
    @SerializedName("tooltipParams")
    @Expose
    var tooltipParams: String? = null

    /**
     * Gets required level.
     *
     * @return the required level
     */
    /**
     * Sets required level.
     *
     * @param requiredLevel the required level
     */
    @SerializedName("requiredLevel")
    @Expose
    var requiredLevel: Double? = null

    /**
     * Gets item level.
     *
     * @return the item level
     */
    /**
     * Sets item level.
     *
     * @param itemLevel the item level
     */
    @SerializedName("itemLevel")
    @Expose
    var itemLevel: Double? = null

    /**
     * Gets stack size max.
     *
     * @return the stack size max
     */
    /**
     * Sets stack size max.
     *
     * @param stackSizeMax the stack size max
     */
    @SerializedName("stackSizeMax")
    @Expose
    var stackSizeMax: Double? = null

    /**
     * Gets account bound.
     *
     * @return the account bound
     */
    /**
     * Sets account bound.
     *
     * @param accountBound the account bound
     */
    @SerializedName("accountBound")
    @Expose
    var accountBound: Boolean? = null

    /**
     * Gets flavor text.
     *
     * @return the flavor text
     */
    /**
     * Sets flavor text.
     *
     * @param flavorText the flavor text
     */
    @SerializedName("flavorText")
    @Expose
    var flavorText: String? = null

    /**
     * Gets type name.
     *
     * @return the type name
     */
    /**
     * Sets type name.
     *
     * @param typeName the type name
     */
    @SerializedName("typeName")
    @Expose
    var typeName: String? = null

    /**
     * Gets type.
     *
     * @return the type
     */
    /**
     * Sets type.
     *
     * @param type the type
     */
    @SerializedName("type")
    @Expose
    var type: Type? = null

    /**
     * Gets armor.
     *
     * @return the armor
     */
    /**
     * Sets armor.
     *
     * @param armor the armor
     */
    @SerializedName("armor")
    @Expose
    var armor: Double? = null

    /**
     * Gets attacks per second.
     *
     * @return the attacks per second
     */
    /**
     * Sets attacks per second.
     *
     * @param attacksPerSecond the attacks per second
     */
    @SerializedName("attacksPerSecond")
    @Expose
    var attacksPerSecond: Double? = null

    /**
     * Gets min damage.
     *
     * @return the min damage
     */
    /**
     * Sets min damage.
     *
     * @param minDamage the min damage
     */
    @SerializedName("minDamage")
    @Expose
    var minDamage: Double? = null

    /**
     * Gets max damage.
     *
     * @return the max damage
     */
    /**
     * Sets max damage.
     *
     * @param maxDamage the max damage
     */
    @SerializedName("maxDamage")
    @Expose
    var maxDamage: Double? = null

    /**
     * Gets elemental type.
     *
     * @return the elemental type
     */
    /**
     * Sets elemental type.
     *
     * @param elementalType the elemental type
     */
    @SerializedName("elementalType")
    @Expose
    var elementalType: String? = null

    /**
     * Gets slots.
     *
     * @return the slots
     */
    /**
     * Sets slots.
     *
     * @param slots the slots
     */
    @SerializedName("slots")
    @Expose
    var slots: String? = null

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    /**
     * Sets attributes.
     *
     * @param attributes the attributes
     */
    @SerializedName("attributes")
    @Expose
    var attributes: Attributes? = null

    /**
     * Gets attributes html.
     *
     * @return the attributes html
     */
    /**
     * Sets attributes html.
     *
     * @param attributesHtml the attributes html
     */
    @SerializedName("attributesHtml")
    @Expose
    var attributesHtml: AttributesHtml? = null

    /**
     * Gets open sockets.
     *
     * @return the open sockets
     */
    /**
     * Sets open sockets.
     *
     * @param openSockets the open sockets
     */
    @SerializedName("openSockets")
    @Expose
    var openSockets: Double? = null

    /**
     * Gets gems.
     *
     * @return the gems
     */
    /**
     * Sets gems.
     *
     * @param gems the gems
     */
    @SerializedName("gems")
    @Expose
    var gems: List<Gem>? = null

    /**
     * Gets set.
     *
     * @return the set
     */
    /**
     * Sets set.
     *
     * @param set the set
     */
    @SerializedName("set")
    @Expose
    var set: Set? = null

    /**
     * Gets season required to drop.
     *
     * @return the season required to drop
     */
    /**
     * Sets season required to drop.
     *
     * @param seasonRequiredToDrop the season required to drop
     */
    @SerializedName("seasonRequiredToDrop")
    @Expose
    var seasonRequiredToDrop: Double? = null

    /**
     * Gets dye.
     *
     * @return the dye
     */
    /**
     * Sets dye.
     *
     * @param dye the dye
     */
    @SerializedName("dye")
    @Expose
    var dye: Dye? = null

    /**
     * Gets transmog.
     *
     * @return the transmog
     */
    /**
     * Sets transmog.
     *
     * @param transmog the transmog
     */
    @SerializedName("transmog")
    @Expose
    var transmog: Transmog? = null

    @SerializedName("isSeasonRequiredToDrop")
    @Expose
    private var isSeasonRequiredToDrop: Boolean? = null

    /**
     * Gets is season required to drop.
     *
     * @return the is season required to drop
     */
    fun getIsSeasonRequiredToDrop(): Boolean? {
        return isSeasonRequiredToDrop
    }

    /**
     * Sets is season required to drop.
     *
     * @param isSeasonRequiredToDrop the is season required to drop
     */
    fun setIsSeasonRequiredToDrop(isSeasonRequiredToDrop: Boolean?) {
        this.isSeasonRequiredToDrop = isSeasonRequiredToDrop
    }

    override fun toString(): String {
        return ToStringBuilder(this).append("id", id).append("name", name).append("icon", icon).append("displayColor", displayColor).append("tooltipParams", tooltipParams).append("requiredLevel", requiredLevel).append("itemLevel", itemLevel).append("stackSizeMax", stackSizeMax).append("accountBound", accountBound).append("flavorText", flavorText).append("typeName", typeName).append("type", type).append("armor", armor).append("attacksPerSecond", attacksPerSecond).append("minDamage", minDamage).append("maxDamage", maxDamage).append("slots", slots).append("attributes", attributes).append("attributesHtml", attributesHtml).append("openSockets", openSockets).append("gems", gems).append("set", set).append("seasonRequiredToDrop", seasonRequiredToDrop).append("dye", dye).append("transmog", transmog).append("isSeasonRequiredToDrop", isSeasonRequiredToDrop).toString()
    }
}