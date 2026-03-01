package com.kuraky.CoreK.managers

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID

class CooldownManager(private val plugin: JavaPlugin) {

    private val cooldowns = mutableMapOf<UUID, MutableMap<String, Long>>()

    fun addCooldown(player: Player, action: String, seconds: Int) {
        val uuid = player.uniqueId
        val expiration = System.currentTimeMillis() + (seconds * 1000L)

        cooldowns.computeIfAbsent(uuid) { mutableMapOf() }[action.lowercase()] = expiration

    }

    fun isOnCooldown(player: Player, action: String): Boolean {
        val uuid = player.uniqueId
        val actionKey = action.lowercase()

        val playerCooldowns = cooldowns[uuid] ?: return false
        val expiracion = playerCooldowns[actionKey] ?: return false

        // Si el tiempo actual es mayor o igual a la expiración, el cooldown ya pasó-
        if (System.currentTimeMillis() >= expiracion) {
            // Limpiamos la memoria borrando la acción
            playerCooldowns.remove(actionKey)
            // Si el jugador ya no tiene ningún cooldown, lo borramos completamente del mapa principal
            if (playerCooldowns.isEmpty()) {
                cooldowns.remove(uuid)
            }
            return false
        }

        return true // Aún está en cooldown
    }

    fun getRemainingTime(player: Player, action: String): Long {
        val expiration = cooldowns[player.uniqueId]?.get(action.lowercase()) ?: return 0L
        val restante = expiration - System.currentTimeMillis()

    return if(restante < 0) restante else 0L
    }

    fun getRemainingTimeFormatted(player: Player, action: String): String {
        val restanteMilis = getRemainingTime(player, action)
        if (restanteMilis <= 0) return "0s"

        val totalSeconds = restanteMilis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60

        return if (minutes > 0) {
            "${minutes}m ${seconds}s"
        } else {
            "${seconds}s"
        }
    }

    fun removeCooldown(player: Player, action: String) {
        val uuid = player.uniqueId
        cooldowns[uuid]?.remove(action.lowercase())
    }

}