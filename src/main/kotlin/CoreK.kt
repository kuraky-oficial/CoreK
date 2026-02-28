package com.kuraky.CoreK

import com.kuraky.CoreK.configs.Settings
import com.kuraky.CoreK.managers.CommandManager
import com.kuraky.CoreK.managers.ConfigManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

class CoreK : JavaPlugin() {

    companion object {
        lateinit var INSTANCE: CoreK
        private set

        lateinit var log : Logger
    }

    lateinit var config : ConfigManager
        private set

    lateinit var commandManager : CommandManager
        private set

    override fun onEnable() {
        INSTANCE = this
        log = this.logger
        config = ConfigManager(this, "config.yml")
        commandManager = CommandManager(this)

        Settings.load(config.getConfig())
        config.reloadConfig()

        commandManager.autoRegister("com.kuraky.CoreK.commands")

        log.info("----------------------------------")
        log.info("   CoreK habilitado (v${description.version})")
        log.info("   Desarrollado por: KurakyStudio")
        log.info("----------------------------------")

    }

    override fun onDisable() {
        config.saveConfig()
        log.info("CoreK deshabilitado. Guardando datos...")
    }

}