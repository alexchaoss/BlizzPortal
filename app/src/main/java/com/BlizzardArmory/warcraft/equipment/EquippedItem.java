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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getContext() {
        return context;
    }

    public void setContext(int context) {
        this.context = context;
    }

    public List<Integer> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<Integer> bonusList) {
        this.bonusList = bonusList;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModifiedAppearanceId() {
        return modifiedAppearanceId;
    }

    public void setModifiedAppearanceId(int modifiedAppearanceId) {
        this.modifiedAppearanceId = modifiedAppearanceId;
    }

    public AzeriteDetails getAzeriteDetails() {
        return azeriteDetails;
    }

    public void setAzeriteDetails(AzeriteDetails azeriteDetails) {
        this.azeriteDetails = azeriteDetails;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public ItemClass getItemClass() {
        return itemClass;
    }

    public void setItemClass(ItemClass itemClass) {
        this.itemClass = itemClass;
    }

    public ItemSubclass getItemSubclass() {
        return itemSubclass;
    }

    public void setItemSubclass(ItemSubclass itemSubclass) {
        this.itemSubclass = itemSubclass;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Binding getBinding() {
        return binding;
    }

    public void setBinding(Binding binding) {
        this.binding = binding;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public Requirements getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Transmog getTransmog() {
        return transmog;
    }

    public void setTransmog(Transmog transmog) {
        this.transmog = transmog;
    }

    public Durability getDurability() {
        return durability;
    }

    public void setDurability(Durability durability) {
        this.durability = durability;
    }

    public String getUniqueEquipped() {
        return uniqueEquipped;
    }

    public void setUniqueEquipped(String uniqueEquipped) {
        this.uniqueEquipped = uniqueEquipped;
    }

    public List<SpellDescription> getSpells() {
        return spells;
    }

    public void setSpells(List<SpellDescription> spells) {
        this.spells = spells;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NameDescription getNameDescriptionObject() {
        return nameDescriptionObject;
    }

    public void setNameDescription(NameDescription nameDescription) {
        this.nameDescriptionObject = nameDescription;
    }

    public boolean isIsSubclassHidden() {
        return isSubclassHidden;
    }

    public void setIsSubclassHidden(boolean isSubclassHidden) {
        this.isSubclassHidden = isSubclassHidden;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public String getNameDescription() {
        return nameDescription;
    }

    public void setNameDescription(String nameDescription) {
        this.nameDescription = nameDescription;
    }

    public String getSocketBonus() {
        return socketBonus;
    }

    public void setSocketBonus(String socketBonus) {
        this.socketBonus = socketBonus;
    }

    public SellPrice getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(SellPrice sellPrice) {
        this.sellPrice = sellPrice;
    }

    public List<Socket> getSockets() {
        return sockets;
    }

    public void setSockets(List<Socket> sockets) {
        this.sockets = sockets;
    }

    public List<Enchantment> getEnchantments() {
        return enchantments;
    }

    public void setEnchantments(List<Enchantment> enchantments) {
        this.enchantments = enchantments;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }


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
