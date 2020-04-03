package com.BlizzardArmory.warcraft.equipment;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Equipped item.
 */
public class EquippedItem {

    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("slot")
    @Expose
    private Slot slot;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("context")
    @Expose
    private int context;
    @SerializedName("bonus_list")
    @Expose
    private List<Integer> bonusList = new ArrayList<>();
    @SerializedName("quality")
    @Expose
    private Quality quality;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("modified_appearance_id")
    @Expose
    private int modifiedAppearanceId;
    @SerializedName("azerite_details")
    @Expose
    private AzeriteDetails azeriteDetails;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("item_class")
    @Expose
    private ItemClass itemClass;
    @SerializedName("item_subclass")
    @Expose
    private ItemSubclass itemSubclass;
    @SerializedName("inventory_type")
    @Expose
    private InventoryType inventoryType;
    @SerializedName("binding")
    @Expose
    private Binding binding;
    @SerializedName("armor")
    @Expose
    private Armor armor;
    @SerializedName("stats")
    @Expose
    private List<Stat> stats = new ArrayList<>();
    @SerializedName("requirements")
    @Expose
    private Requirements requirements;
    @SerializedName("level")
    @Expose
    private Level level;
    @SerializedName("transmog")
    @Expose
    private Transmog transmog;
    @SerializedName("durability")
    @Expose
    private Durability durability;
    @SerializedName("unique_equipped")
    @Expose
    private String uniqueEquipped;
    @SerializedName("spells")
    @Expose
    private List<SpellDescription> spells = new ArrayList<>();
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_subclass_hidden")
    @Expose
    private boolean isSubclassHidden;
    @SerializedName("set")
    @Expose
    private Set set;
    private String nameDescription = "";
    private NameDescription nameDescriptionObject;
    @SerializedName("socket_bonus")
    @Expose
    private String socketBonus;
    @SerializedName("sell_price")
    @Expose
    private SellPrice sellPrice;
    @SerializedName("sockets")
    @Expose
    private List<Socket> sockets = new ArrayList<>();
    @SerializedName("enchantments")
    @Expose
    private List<Enchantment> enchantments = new ArrayList<>();
    @SerializedName("weapon")
    @Expose
    private Weapon weapon;

    /**
     * Gets item.
     *
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets item.
     *
     * @param item the item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Gets slot.
     *
     * @return the slot
     */
    public Slot getSlot() {
        return slot;
    }

    /**
     * Sets slot.
     *
     * @param slot the slot
     */
    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public int getContext() {
        return context;
    }

    /**
     * Sets context.
     *
     * @param context the context
     */
    public void setContext(int context) {
        this.context = context;
    }

    /**
     * Gets bonus list.
     *
     * @return the bonus list
     */
    public List<Integer> getBonusList() {
        return bonusList;
    }

    /**
     * Sets bonus list.
     *
     * @param bonusList the bonus list
     */
    public void setBonusList(List<Integer> bonusList) {
        this.bonusList = bonusList;
    }

    /**
     * Gets quality.
     *
     * @return the quality
     */
    public Quality getQuality() {
        return quality;
    }

    /**
     * Sets quality.
     *
     * @param quality the quality
     */
    public void setQuality(Quality quality) {
        this.quality = quality;
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
     * Gets modified appearance id.
     *
     * @return the modified appearance id
     */
    public int getModifiedAppearanceId() {
        return modifiedAppearanceId;
    }

    /**
     * Sets modified appearance id.
     *
     * @param modifiedAppearanceId the modified appearance id
     */
    public void setModifiedAppearanceId(int modifiedAppearanceId) {
        this.modifiedAppearanceId = modifiedAppearanceId;
    }

    /**
     * Gets azerite details.
     *
     * @return the azerite details
     */
    public AzeriteDetails getAzeriteDetails() {
        return azeriteDetails;
    }

    /**
     * Sets azerite details.
     *
     * @param azeriteDetails the azerite details
     */
    public void setAzeriteDetails(AzeriteDetails azeriteDetails) {
        this.azeriteDetails = azeriteDetails;
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
     * Gets item class.
     *
     * @return the item class
     */
    public ItemClass getItemClass() {
        return itemClass;
    }

    /**
     * Sets item class.
     *
     * @param itemClass the item class
     */
    public void setItemClass(ItemClass itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * Gets item subclass.
     *
     * @return the item subclass
     */
    public ItemSubclass getItemSubclass() {
        return itemSubclass;
    }

    /**
     * Sets item subclass.
     *
     * @param itemSubclass the item subclass
     */
    public void setItemSubclass(ItemSubclass itemSubclass) {
        this.itemSubclass = itemSubclass;
    }

    /**
     * Gets inventory type.
     *
     * @return the inventory type
     */
    public InventoryType getInventoryType() {
        return inventoryType;
    }

    /**
     * Sets inventory type.
     *
     * @param inventoryType the inventory type
     */
    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }

    /**
     * Gets binding.
     *
     * @return the binding
     */
    public Binding getBinding() {
        return binding;
    }

    /**
     * Sets binding.
     *
     * @param binding the binding
     */
    public void setBinding(Binding binding) {
        this.binding = binding;
    }

    /**
     * Gets armor.
     *
     * @return the armor
     */
    public Armor getArmor() {
        return armor;
    }

    /**
     * Sets armor.
     *
     * @param armor the armor
     */
    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    /**
     * Gets stats.
     *
     * @return the stats
     */
    public List<Stat> getStats() {
        return stats;
    }

    /**
     * Sets stats.
     *
     * @param stats the stats
     */
    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    /**
     * Gets requirements.
     *
     * @return the requirements
     */
    public Requirements getRequirements() {
        return requirements;
    }

    /**
     * Sets requirements.
     *
     * @param requirements the requirements
     */
    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Gets transmog.
     *
     * @return the transmog
     */
    public Transmog getTransmog() {
        return transmog;
    }

    /**
     * Sets transmog.
     *
     * @param transmog the transmog
     */
    public void setTransmog(Transmog transmog) {
        this.transmog = transmog;
    }

    /**
     * Gets durability.
     *
     * @return the durability
     */
    public Durability getDurability() {
        return durability;
    }

    /**
     * Sets durability.
     *
     * @param durability the durability
     */
    public void setDurability(Durability durability) {
        this.durability = durability;
    }

    /**
     * Gets unique equipped.
     *
     * @return the unique equipped
     */
    public String getUniqueEquipped() {
        return uniqueEquipped;
    }

    /**
     * Sets unique equipped.
     *
     * @param uniqueEquipped the unique equipped
     */
    public void setUniqueEquipped(String uniqueEquipped) {
        this.uniqueEquipped = uniqueEquipped;
    }

    /**
     * Gets spells.
     *
     * @return the spells
     */
    public List<SpellDescription> getSpells() {
        return spells;
    }

    /**
     * Sets spells.
     *
     * @param spells the spells
     */
    public void setSpells(List<SpellDescription> spells) {
        this.spells = spells;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets name description object.
     *
     * @return the name description object
     */
    public NameDescription getNameDescriptionObject() {
        return nameDescriptionObject;
    }

    /**
     * Sets name description.
     *
     * @param nameDescription the name description
     */
    public void setNameDescription(NameDescription nameDescription) {
        this.nameDescriptionObject = nameDescription;
    }

    /**
     * Is is subclass hidden boolean.
     *
     * @return the boolean
     */
    public boolean isIsSubclassHidden() {
        return isSubclassHidden;
    }

    /**
     * Sets is subclass hidden.
     *
     * @param isSubclassHidden the is subclass hidden
     */
    public void setIsSubclassHidden(boolean isSubclassHidden) {
        this.isSubclassHidden = isSubclassHidden;
    }

    /**
     * Gets set.
     *
     * @return the set
     */
    public Set getSet() {
        return set;
    }

    /**
     * Sets set.
     *
     * @param set the set
     */
    public void setSet(Set set) {
        this.set = set;
    }

    /**
     * Gets name description.
     *
     * @return the name description
     */
    public String getNameDescription() {
        return nameDescription;
    }

    /**
     * Sets name description.
     *
     * @param nameDescription the name description
     */
    public void setNameDescription(String nameDescription) {
        this.nameDescription = nameDescription;
    }

    /**
     * Gets socket bonus.
     *
     * @return the socket bonus
     */
    public String getSocketBonus() {
        return socketBonus;
    }

    /**
     * Sets socket bonus.
     *
     * @param socketBonus the socket bonus
     */
    public void setSocketBonus(String socketBonus) {
        this.socketBonus = socketBonus;
    }

    /**
     * Gets sell price.
     *
     * @return the sell price
     */
    public SellPrice getSellPrice() {
        return sellPrice;
    }

    /**
     * Sets sell price.
     *
     * @param sellPrice the sell price
     */
    public void setSellPrice(SellPrice sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * Gets sockets.
     *
     * @return the sockets
     */
    public List<Socket> getSockets() {
        return sockets;
    }

    /**
     * Sets sockets.
     *
     * @param sockets the sockets
     */
    public void setSockets(List<Socket> sockets) {
        this.sockets = sockets;
    }

    /**
     * Gets enchantments.
     *
     * @return the enchantments
     */
    public List<Enchantment> getEnchantments() {
        return enchantments;
    }

    /**
     * Sets enchantments.
     *
     * @param enchantments the enchantments
     */
    public void setEnchantments(List<Enchantment> enchantments) {
        this.enchantments = enchantments;
    }

    /**
     * Gets weapon.
     *
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Sets weapon.
     *
     * @param weapon the weapon
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }


    /**
     * The type Name description deserializer.
     */
    public static class NameDescriptionDeserializer implements JsonDeserializer<EquippedItem> {

        @Override
        public EquippedItem deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            EquippedItem equippedItem = new Gson().fromJson(json, EquippedItem.class);
            JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject.has("name_description")) {
                JsonElement elem = jsonObject.get("name_description");
                if (elem != null && !elem.isJsonNull()) {
                    try {
                        equippedItem.setNameDescription(elem.getAsString());
                    } catch (Exception e) {
                        equippedItem.setNameDescription(new NameDescription(elem.getAsJsonObject().get("display_string").getAsString(),
                                new Color(elem.getAsJsonObject().get("color").getAsJsonObject().get("r").getAsInt(),
                                        elem.getAsJsonObject().get("color").getAsJsonObject().get("g").getAsInt(),
                                        elem.getAsJsonObject().get("color").getAsJsonObject().get("b").getAsInt(),
                                        elem.getAsJsonObject().get("color").getAsJsonObject().get("a").getAsFloat())));
                    }
                }
            }
            return equippedItem;
        }
    }
}
