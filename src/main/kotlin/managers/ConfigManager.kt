package com.kuraky.CoreK.managers

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException

class ConfigManager (private val plugin: JavaPlugin, private val fileName: String) {

    private val file: File = File(plugin.dataFolder, fileName)
    private var config: FileConfiguration? = YamlConfiguration()

    init {
        saveDefaultConfig()
        reloadConfig()
    }

    fun saveDefaultConfig() {
        if (!file.exists()) {
            if (plugin.getResource(fileName) != null){
                plugin.saveResource(fileName, false)
            } else {
                file.parentFile.mkdirs()
                file.createNewFile()
            }
        }
    }

    fun reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file)
    }

    fun getConfig(): FileConfiguration {
        return config!!
    }

    fun saveConfig() {
        try {
            config?.save(file)
        } catch (e: IOException) {
            plugin.logger.severe("¡Error crítico! No se pudo guardar el archivo: $fileName")
        }
    }

}