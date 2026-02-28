package com.kuraky.CoreK.listeners

import com.kuraky.CoreK.menus.CoreMenu
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class MenuListener: Listener {

    @EventHandler
    fun onMenuClick(event: InventoryClickEvent) {
        val holder = event.inventory.holder

        if (holder is CoreMenu) {
            event.isCancelled = true
            val player = event.whoClicked
            if (event.clickedInventory == event.inventory) {
                holder.handleInventoryClick(event)
            }
        }
    }

}