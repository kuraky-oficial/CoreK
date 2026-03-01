package com.kuraky.CoreK.managers

import com.kuraky.CoreK.scoreboards.CoreScoreboard
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Scoreboard
import java.util.UUID

class ScoreboardManager(private val plugin: JavaPlugin) {

    private val scoreboards = mutableMapOf<UUID, CoreScoreboard>()

    fun create(player: Player): CoreScoreboard {
        val sb = CoreScoreboard(player)
        scoreboards[player.uniqueId] = sb
        return sb
    }

    fun get(player: Player): CoreScoreboard? {
        return scoreboards[player.uniqueId]
    }

    fun remove(player: Player) {
        scoreboards.remove(player.uniqueId)?.delete()
    }

    fun clearAll() {
        scoreboards.values.forEach {it.delete()}
        scoreboards.clear()
    }

}