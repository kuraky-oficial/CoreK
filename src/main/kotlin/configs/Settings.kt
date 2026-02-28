package com.kuraky.CoreK.configs

import org.bukkit.configuration.file.FileConfiguration

object Settings {
    // 1. Declaras las variables limpias que usarás en tu plugin
    var debugMode: Boolean = false
    var defaultSlots: Int = 27

    // 2. Esta función se llama UNA SOLA VEZ desde el Main
    fun load(config: FileConfiguration) {
        // Leemos el YML y lo guardamos en las variables.
        // El segundo parámetro (ej. false) es el valor por defecto si el YML está vacío o roto.
        debugMode = config.getBoolean("opciones.debug", false)
        defaultSlots = config.getInt("inventarios.tamaño-por-defecto", 27)
    }
}