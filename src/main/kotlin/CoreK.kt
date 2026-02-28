package com.kuraky.CoreK

import com.kuraky.CoreK.configs.Settings
import com.kuraky.CoreK.managers.CommandManager
import com.kuraky.CoreK.managers.ConfigManager
import com.kuraky.CoreK.managers.DatabaseManager
import com.kuraky.CoreK.managers.ListenerManager
import com.kuraky.CoreK.managers.MongoManager
import com.kuraky.CoreK.managers.TaskManager
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

    lateinit var dbManager : DatabaseManager
        private set

    lateinit var mongoManager : MongoManager
        private set

    lateinit var listenerManager : ListenerManager

    lateinit var taskManager : TaskManager
        private set

    override fun onEnable() {
        INSTANCE = this
        log = this.logger
        config = ConfigManager(this, "config.yml")
        commandManager = CommandManager(this)
        dbManager = DatabaseManager(this)
        mongoManager = MongoManager()
        listenerManager = ListenerManager(this)
        taskManager = TaskManager(this)

        listenerManager.autoRegister("com.kuraky.CoreK.listeners")

        Settings.load(config.getConfig())
        config.reloadConfig()

        commandManager.autoRegister("com.kuraky.CoreK.commands")

        log.info("----------------------------------")
        log.info("   CoreK habilitado (v${description.version})")
        log.info("   Desarrollado por: KurakyStudio")
        log.info("----------------------------------")

    }

    fun runAsync(task: Runnable) {
        server.scheduler.runTaskAsynchronously(this, task)
    }

    override fun onDisable() {
        config.saveConfig()
        log.info("CoreK deshabilitado. Guardando datos...")
    }



}