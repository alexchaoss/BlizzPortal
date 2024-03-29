package com.BlizzardArmory.ui.warcraft.character.armory

import android.app.Application
import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.BlizzardArmory.model.warcraft.charactersummary.CharacterSummary
import com.BlizzardArmory.model.warcraft.equipment.Equipment
import com.BlizzardArmory.model.warcraft.equipment.EquippedItem
import com.BlizzardArmory.model.warcraft.equipment.Set
import com.BlizzardArmory.model.warcraft.equipment.Socket
import com.BlizzardArmory.model.warcraft.media.Media
import com.BlizzardArmory.model.warcraft.statistic.Statistic
import com.BlizzardArmory.network.NetworkUtils
import com.BlizzardArmory.network.RetroClient
import com.BlizzardArmory.ui.BaseViewModel
import com.BlizzardArmory.util.events.LocaleSelectedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.Locale

class WoWCharacterViewModel(application: Application) : BaseViewModel(application) {

    lateinit var character: String
    lateinit var realm: String
    lateinit var region: String

    private var characterSummary: MutableLiveData<CharacterSummary> = MutableLiveData()
    private var statistic: MutableLiveData<Statistic> = MutableLiveData()
    private var equipment: MutableLiveData<Equipment> = MutableLiveData()
    private var media: MutableLiveData<Media> = MutableLiveData()
    private val imageURLs: MutableLiveData<HashMap<String, String>> = MutableLiveData()

    val stats = HashMap<String, String>()
    val nameList = HashMap<String, String>()
    private val imageURLsTemp = hashMapOf<String, String>()

    init {
        imageURLs.value = hashMapOf()
    }

    fun getCharacterSummary(): LiveData<CharacterSummary> {
        return characterSummary
    }

    fun getCharacterStats(): LiveData<Statistic> {
        return statistic
    }

    fun getEquipment(): LiveData<Equipment> {
        return equipment
    }

    fun getMedia(): MutableLiveData<Media> {
        return media
    }

    fun getIconURLs(): LiveData<HashMap<String, String>> {
        return imageURLs
    }

    fun downloadCharacterSummary() {
        executeAPICall({
            RetroClient.getWoWClient(getApplication(), true).getCharacter(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                region.lowercase(Locale.getDefault()),
            )
        }, { characterSummary.value = it.body() }, {
            showErrorDialog.value = true
        }, onComplete = {
            if (!EventBus.getDefault().isRegistered(this@WoWCharacterViewModel)) {
                EventBus.getDefault().register(this@WoWCharacterViewModel)
            }
        })

    }

    fun downloadStats() {
        executeAPICall({
            RetroClient.getWoWClient(getApplication(), true).getStats(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                region.lowercase(Locale.getDefault()),
            )
        }, { statistic.value = it.body() }, {
            showErrorDialog.value = true
        })
    }

    fun downloadEquipment() {
        executeAPICall({
            RetroClient.getWoWClient(getApplication(), true).getEquippedItems(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                region.lowercase(Locale.getDefault()),
            )
        }, { equipment.value = it.body() }, {
            showErrorDialog.value = true
        })
    }

    fun downloadBackground() {
        executeAPICall({
            RetroClient.getWoWClient(getApplication(), true).getMedia(
                character.lowercase(Locale.getDefault()),
                realm.lowercase(Locale.getDefault()),
                region.lowercase(Locale.getDefault()),
            )
        }, { media.value = it.body() })
    }

    fun downloadIcons(equippedItem: EquippedItem) {
        executeAPICall(
            { RetroClient.getWoWClient(getApplication(), cacheTime = 365L).getEquipmentMedia(itemId = equippedItem.media.id, namespace = "static-" + NetworkUtils.region) },
            {
                val mediaItem = it.body()!!
                imageURLsTemp[equippedItem.slot.type] = mediaItem.assets[0].value

            },
            {
                imageURLsTemp[equippedItem.slot.type] = "empty"
            }, onComplete = {
                if (imageURLsTemp.size == equipment.value!!.equippedItems.size) {
                    imageURLs.value = imageURLsTemp
                }
            })
        NetworkUtils.loading = false
    }

    fun getItemColor(item: EquippedItem): Int {
        try {
            when (item.quality.type) {
                "POOR" -> return Color.parseColor("#9d9d9d")
                "COMMON" -> return Color.parseColor("#ffffff")
                "UNCOMMON" -> return Color.parseColor("#1eff00")
                "RARE" -> return Color.parseColor("#0070dd")
                "EPIC" -> return Color.parseColor("#a335ee")
                "LEGENDARY" -> return Color.parseColor("#ff8000")
                "ARTIFACT" -> return Color.parseColor("#e6ca80")
                "HEIRLOOM" -> return Color.parseColor("#01c5f7")
            }
        } catch (e: Exception) {
            return Color.GRAY
        }
        return 0
    }

    fun setCharacterItemsInformation(equippedItem: EquippedItem) {
        nameList[equippedItem.slot.type] = equippedItem.name
        var slot = StringBuilder(equippedItem.inventoryType.name)
        var itemLvl = ""
        if (equippedItem.level != null) {
            itemLvl = "<font color=#edc201>" + equippedItem.level?.displayString + "</font><br>"
        }
        var nameDescription = ""
        var damageInfo = StringBuilder()
        var durability = ""
        var transmog = ""
        var armor = ""
        var requiredLevel = ""
        var description = ""
        var trigger = ""
        var hoaLevel = ""
        var bind = ""
        val itemSubclass: String
        val enchant = StringBuilder()
        val setInfo = StringBuilder()
        var socketBonus = ""
        val sockets = StringBuilder()
        val statsString = StringBuilder()
        var azeriteSpells = StringBuilder()
        val sellPrice = StringBuilder()
        try {
            setInfo.append("<font color=#edc201>").append(equippedItem.set.displayString)
                .append("</font><br>")
            setInfo.append(formatSetItemText(equippedItem.set))
        } catch (e: Exception) {
            setInfo.replace(0, setInfo.length, "")
        }
        try {
            sellPrice.append(equippedItem.sellPrice.displayStrings.header).append(" ")
            if (equippedItem.sellPrice.displayStrings.gold != "0") {
                sellPrice.append(equippedItem.sellPrice.displayStrings.gold)
                    .append(" <img src=\"gold\">")
            }
            if (equippedItem.sellPrice.displayStrings.silver != "0") {
                sellPrice.append(equippedItem.sellPrice.displayStrings.silver)
                    .append(" <img src=\"silver\">")
            }
            if (equippedItem.sellPrice.displayStrings.copper != "0") {
                sellPrice.append(equippedItem.sellPrice.displayStrings.copper)
                    .append(" <img src=\"copper\">")
            }
        } catch (e: Exception) {
        }
        try {
            durability = equippedItem.durability.displayString
        } catch (e: Exception) {
        }
        try {
            for (enchantment in equippedItem.enchantments) {
                enchant.append("<font color=#00ff00>").append(enchantment.displayString)
                    .append("</font><br>")
            }
        } catch (e: Exception) {
        }
        try {
            itemSubclass = equippedItem.itemSubclass.name
            var length = slot.length + itemSubclass.length
            while (length < 43) {
                slot.append("&nbsp;")
                length++
            }
            var lengthSubClass = itemSubclass.length
            while (lengthSubClass < 14) {
                slot.append("&nbsp;")
                lengthSubClass++
            }
            if (lengthSubClass + length == 60 && itemSubclass == "Miscellaneous") {
                slot = StringBuilder(slot.substring(0, slot.length - 12))
            }
            slot.append(itemSubclass).append("<br>")
        } catch (e: Exception) {
        }
        try {
            transmog = "<font color=#f57bf5>" + equippedItem.transmog.displayString.replace("\n", "<br>") + "</font><br>"
        } catch (e: Exception) {
        }
        try {
            armor = if (equippedItem.armor.displayString == null) {
                "<font color=#" + rbgToHexHTML(equippedItem.armor.display.color) + ">" + equippedItem.armor.display.displayString + "</font><br>"
            } else {
                equippedItem.armor.displayString + "<br>"
            }
        } catch (e: Exception) {
        }
        try {
            requiredLevel = equippedItem.requirements.level.displayString
        } catch (e: Exception) {
        }

        if (equippedItem.description != null) {
            description = "<font color=#edc201>" + equippedItem.description + "</font>"
        }

        try {
            nameDescription = "<font color=#" + getNameDescriptionColor(equippedItem) + ">" + equippedItem.nameDescriptionObject.displayString + "</font><br>"
        } catch (e: Exception) {
        }
        try {
            trigger = if (equippedItem.spells[0].color != null) {
                "<font color=#" + rbgToHexHTML(equippedItem.spells[0].color!!) + ">" + equippedItem.spells[0].description + "</font>"
            } else {
                "<font color=#00ff00>" + equippedItem.spells[0].description + "</font>"
            }
        } catch (e: Exception) {
        }
        try {
            if (equippedItem.item.id == 158075L) {
                azeriteSpells.append("<br>")
                for (selectedEssence in equippedItem.azeriteDetails?.selectedEssences!!) {
                    val essenceImage = "<img src=\"" + getEssenceImage(selectedEssence.slot) + "\"> "
                    val color = "<font color=#" + getEssenceRankColor(selectedEssence.rank) + ">"
                    azeriteSpells.append(essenceImage).append(color)
                        .append(selectedEssence.essence.name).append("</font><br>")
                }
            } else {
                if (equippedItem.azeriteDetails != null) {
                    azeriteSpells = StringBuilder("<br><font color=#edc201>" + equippedItem.azeriteDetails?.selectedPowersString + "</font><br>")
                    for (selectedPower in equippedItem.azeriteDetails?.selectedPowers!!) {
                        if (!selectedPower.isIsDisplayHidden) {
                            azeriteSpells.append("- ").append(selectedPower.spellTooltip.spell.name)
                                .append("<br>")
                            azeriteSpells.append("<font color=#00ff00>")
                                .append(selectedPower.spellTooltip.description)
                                .append("</font><br>")
                        }
                    }
                }
            }
        } catch (e: Exception) {
        }
        try {
            for (currentSocket in equippedItem.sockets) {
                if (currentSocket.displayString == null) {
                    sockets.append("<img src=\"").append(getSocketColor(currentSocket))
                        .append("\"> ")
                    sockets.append(currentSocket.socketType?.name).append("<br>")
                } else {
                    sockets.append("<img src=\"").append(getSocketColor(currentSocket))
                        .append("\"> ")
                    sockets.append(currentSocket.displayString).append("<br>")
                }
            }
        } catch (e: Exception) {
        }
        try {
            if (equippedItem.socketBonus != null) {
                socketBonus = "<font color=#00ff00>" + equippedItem.socketBonus + "</font><br>"
            }
        } catch (e: Exception) {
        }
        try {
            bind = equippedItem.binding.name + "<br>"
        } catch (e: Exception) {
        }
        if (equippedItem.item.id == 158075L) {
            hoaLevel = "<font color=#edc201>" + equippedItem.azeriteDetails?.level?.displayString + "</font><br>"
        }
        if (equippedItem.itemClass.id == 2L) {
            damageInfo = StringBuilder(equippedItem.weapon.damage.displayString)
            var length = damageInfo.length + equippedItem.weapon.attackSpeed.displayString.length
            while (length < 43) {
                damageInfo.append("&nbsp;")
                length++
            }
            damageInfo.append(equippedItem.weapon.attackSpeed.displayString).append("<br>")
                .append(equippedItem.weapon.dps.displayString).append("<br>")
        }
        try {
            for (i in equippedItem.stats?.indices!!) {
                statsString.append("<font color=#" + rbgToHexHTML(equippedItem.stats?.get(i)?.display?.color!!) + ">")
                    .append(equippedItem.stats!![i].display.displayString).append("</font>")
                    .append("<br>")
            }
        } catch (e: Exception) {
            var i = 0
            while (equippedItem.stats != null && i < equippedItem.stats!!.size) {
                if (equippedItem.stats!![i].isIsEquipBonus) {
                    statsString.append("<font color=#00ff00>")
                        .append(equippedItem.stats!![i].display_string).append("</font>")
                        .append("<br>")
                } else {
                    statsString.append(equippedItem.stats!![i].display_string).append("<br>")
                }
                i++
            }
        }
        stats[equippedItem.slot.type] = String.format("%s%s%s%s%s%s%s%s%s", nameDescription, itemLvl, transmog, hoaLevel, bind, slot.toString(), damageInfo.toString(), armor, statsString.toString())
        if (enchant.toString() != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s", enchant)
        }
        if (azeriteSpells.isNotEmpty()) {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type] + azeriteSpells
        }
        if (trigger != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", trigger)
        }
        if (sockets.length > 4) {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s", sockets)
        }
        if (socketBonus != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("%s", socketBonus)
        }
        if (setInfo.toString() != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s", setInfo)
        }
        if (durability != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", durability)
        }
        if (requiredLevel != "") {
            if (durability == "") {
                stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", requiredLevel)
            } else {
                stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("%s<br>", requiredLevel)
            }
        }
        if (sellPrice.toString() != "") {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("%s<br>", sellPrice)
        }
        if (equippedItem.description != null) {
            stats[equippedItem.slot.type] = stats[equippedItem.slot.type].toString() + String.format("<br>%s<br>", description)
        }
    }

    private fun formatSetItemText(set: Set): StringBuilder {
        var equippedSetItem = 0
        val itemRequiredForEffect = IntArray(set.effects.size)
        val setInfo = StringBuilder()
        for (i in set.effects.indices) {
            itemRequiredForEffect[i] = set.effects[i].requiredCount
        }
        for (itemEquipped in set.items) {
            if (itemEquipped.isIsEquipped) {
                setInfo.append("&nbsp;&nbsp;&nbsp;&nbsp;<font color=#ffff98>")
                    .append(itemEquipped.item.name).append("</font><br>")
                equippedSetItem++
            } else {
                setInfo.append("&nbsp;&nbsp;&nbsp;&nbsp;<font color=#6e6e6e>")
                    .append(itemEquipped.item.name).append("</font><br>")
            }
        }
        setInfo.append("<br>")
        for (i in itemRequiredForEffect.indices) {
            if (itemRequiredForEffect[i] <= equippedSetItem) {
                setInfo.append("<font color=#ffff98>").append(set.effects[i].displayString)
                    .append("</font><br>")
            } else {
                setInfo.append("<font color=#6e6e6e>").append(set.effects[i].displayString)
                    .append("</font><br>")
            }
        }
        return setInfo
    }

    private fun rbgToHexHTML(color: com.BlizzardArmory.model.warcraft.equipment.Color): String {
        return String.format("#%X", Color.rgb(color.r, color.g, color.b)).substring(3)
    }

    private fun getNameDescriptionColor(equippedItem: EquippedItem): String {
        var red = Integer.toHexString(equippedItem.nameDescriptionObject.color.r)
        var green = Integer.toHexString(equippedItem.nameDescriptionObject.color.g)
        var blue = Integer.toHexString(equippedItem.nameDescriptionObject.color.b)
        if (red == "0") {
            red += "0"
        }
        if (green == "0") {
            green += "0"
        }
        if (blue == "0") {
            blue += "0"
        }
        return red + green + blue
    }

    private fun getSocketColor(currentSocket: Socket): String {
        when (currentSocket.socketType?.type) {
            "PRISMATIC" -> return "socket_prismatic"
            "META" -> return "socket_meta"
            "PUNCHCARDRED" -> return "socket_red"
            "PUNCHCARDYELLOW" -> return "socket_yellow"
            "PUNCHCARDBLUE" -> return "socket_blue"
        }
        return "socket_prismatic"
    }

    private fun getEssenceImage(essenceSlot: Int): String {
        when (essenceSlot) {
            0 -> return "essence_border_gold"
            1, 3 -> return "essence_border_silver"
        }
        return "essence_border_gold"
    }

    private fun getEssenceRankColor(rank: Int): String {
        when (rank) {
            1 -> return "00ff00"
            2 -> return "0070ff"
            3 -> return "663288"
            4 -> return "ff8000"
        }
        return "00ff00"
    }

    @Subscribe
    override fun localeSelectedReceived(localeSelectedEvent: LocaleSelectedEvent) {
        super.localeSelectedReceived(localeSelectedEvent)
        downloadCharacterSummary()
        downloadStats()
        downloadEquipment()
    }
}