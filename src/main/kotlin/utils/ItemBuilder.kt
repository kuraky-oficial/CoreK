package com.kuraky.CoreK.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class ItemBuilder(material: Material, amount: Int = 1) {
    private val item = ItemStack(material, amount)
    private val meta: ItemMeta? = item.itemMeta

    fun name(text: String): ItemBuilder {
        meta?.displayName(ColorUtils.format(text))
        return this
    }

    fun lore(lines: List<String>): ItemBuilder {
        val coloredLore = lines.map { ColorUtils.format(it) }
        meta?.lore(coloredLore)
        return this
    }

    fun build(): ItemStack {
        item.itemMeta = meta
        return item
    }
}