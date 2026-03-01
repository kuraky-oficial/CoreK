package com.kuraky.CoreK.managers

import com.kuraky.CoreK.holograms.CoreHologram
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin

class HologramManager(private val plugin: JavaPlugin) {

    private val holograms = mutableListOf<CoreHologram>()

    fun create(location: Location, vararg lines: String): CoreHologram {
        val holo = CoreHologram(location, *lines)
        holograms.add(holo)
        return holo
    }

    fun remove(hologram: CoreHologram) {
        hologram.delete()
        holograms.remove(hologram)
    }

    fun clearAll(){
        for (holo in holograms){
            holo.delete()
        }

        holograms.clear()
        plugin.logger.info("Todos los hologramas han sido eliminados correctamente.")
    }

}