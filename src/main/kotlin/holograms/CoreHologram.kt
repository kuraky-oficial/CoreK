package com.kuraky.CoreK.holograms

import com.kuraky.CoreK.utils.ColorUtils
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.entity.Display
import org.bukkit.entity.EntityType
import org.bukkit.entity.TextDisplay

class CoreHologram(var location: Location, vararg Lines: String) {

    private val display: TextDisplay = location.world.spawnEntity(location, EntityType.TEXT_DISPLAY) as TextDisplay

    init {

        display.billboard = Display.Billboard.CENTER
        display.isPersistent = false
        display.backgroundColor = Color.fromARGB(0, 0, 0, 0)
        display.teleportDuration = 10

        updateLines(Lines.toList())

    }

    fun updateLines(lines: List<String>) {
        val text = lines.joinToString("<br>")
        display.text(ColorUtils.format(text))
    }

    fun move (newLocation: Location) {
        this.location = newLocation
        display.teleport(newLocation)
    }

    fun delete() {
        if (display.isValid) {
            display.remove()
        }
    }


}