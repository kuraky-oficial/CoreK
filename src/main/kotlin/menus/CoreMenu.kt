package com.kuraky.CoreK.menus

import com.kuraky.CoreK.utils.ColorUtils
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

abstract class CoreMenu(
    val player: Player,
    val title: String,
    val rows: Int
): InventoryHolder {

    private val inventory: Inventory = Bukkit.createInventory(player, rows * 9, ColorUtils.format(title))
    private val actions = mutableMapOf<Int, (InventoryClickEvent) -> Unit >()

    abstract fun setContents()

    fun setButton(slot: Int, item: ItemStack, action: ((InventoryClickEvent) -> Unit)? = null) {
        inventory.setItem(slot, item)
        if (action != null) {
            actions[slot] = action
        }
    }

    fun handleInventoryClick(event: InventoryClickEvent) {
        actions[event.slot]?.invoke(event)
    }

    fun open() {
        setContents()
        player.openInventory(inventory)
    }

    override fun getInventory(): Inventory = inventory

}