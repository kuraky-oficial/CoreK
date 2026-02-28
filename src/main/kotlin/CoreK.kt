package com.kuraky.CoreK

import com.kuraky.CoreK.configs.Settings
import com.kuraky.CoreK.managers.ConfigManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

class CoreK : JavaPlugin() {

    companion object {
        lateinit var INSTANCE: CoreK
        private set

        lateinit var log : Logger
    }

    lateinit var settingsFile : ConfigManager
    private set

    override fun onEnable() {
        INSTANCE = this
        log = this.logger

        settingsFile = ConfigManager(this, "settings.yml")
        Settings.load(settingsFile.getConfig())

        log.info("----------------------------------")
        log.info("   CoreK habilitado (v${description.version})")
        log.info("   Desarrollado por: KurakyStudio")
        log.info("----------------------------------")


        val title = Settings.defaultSlots
    }

    override fun onDisable() {
        log.info("CoreK deshabilitado. Guardando datos...")
        settingsFile.saveConfig()
    }

}