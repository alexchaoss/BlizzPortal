package com.BlizzardArmory.util

object SlugName {
    fun toSlug(name: String): String {
        return name.trim().lowercase().replace("'", "").replace(",", "").replace(":", "").replace(" ", "-")
    }

    fun toRaidSlug(name: String): String {
        return toSlug(name.trim().lowercase().replace("-", ""))
    }
}