package com.kuraky.CoreK.scoreboards

import com.kuraky.CoreK.managers.CommandManager
import com.kuraky.CoreK.utils.ColorUtils
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard

class CoreScoreboard(val player: Player) {

    private val scoreboard: Scoreboard = Bukkit.getScoreboardManager().newScoreboard
    private val objective: Objective
    private val maxLines = 15

    init {
        objective = scoreboard.registerNewObjective("core_sb", Criteria.DUMMY, ColorUtils.format("Scoreboard"))
        objective.displaySlot = DisplaySlot.SIDEBAR
        player.scoreboard = scoreboard
    }

    fun updateTitle(title: String) {
        objective.displayName(ColorUtils.format(title))
    }

    fun updateLines(lines: List<String>) {
        val size = lines.size.coerceAtMost(maxLines)

        for (i in 0 until size) {
            val teamName = "Line_$i"
            var team = scoreboard.getTeam(teamName)

            val entry = "§" + Integer.toHexString(i) + "§r"

            if (team == null) {
                team = scoreboard.registerNewTeam(teamName)
                team.addEntry(entry)
                objective.getScore(entry).score = 15 - i
            }

            team.prefix(ColorUtils.format(lines[i]))

        }

        for (i in size until maxLines) {
            val teamName = "Line_$i"
            var team = scoreboard.getTeam(teamName)
            if (team != null) {
                val entry = "§" + Integer.toHexString(i) + "§r"
                scoreboard.resetScores(entry)
                team.unregister()
            }
        }

    }

    fun delete() {
        objective.unregister()
        player.scoreboard  = Bukkit.getScoreboardManager().mainScoreboard
    }

}