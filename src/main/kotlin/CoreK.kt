package com.kuraky.CoreK

import com.kuraky.CoreK.configs.Settings
import com.kuraky.CoreK.holograms.CoreHologram
import com.kuraky.CoreK.managers.CommandManager
import com.kuraky.CoreK.managers.ConfigManager
import com.kuraky.CoreK.managers.CooldownManager
import com.kuraky.CoreK.managers.DatabaseManager
import com.kuraky.CoreK.managers.EffectManager
import com.kuraky.CoreK.managers.HologramManager
import com.kuraky.CoreK.managers.ListenerManager
import com.kuraky.CoreK.managers.MongoManager
import com.kuraky.CoreK.managers.ScoreboardManager
import com.kuraky.CoreK.managers.TaskManager
import com.mongodb.client.MongoDatabase
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

    lateinit var effectManager : EffectManager
        private set

    lateinit var cooldownManager : CooldownManager
        private set

    lateinit var scoreboards : ScoreboardManager
        private set

    lateinit var hologram: HologramManager
        private set

    override fun onEnable() {
        INSTANCE = this
        log = this.logger

        // Inicializadores

        commandManager = CommandManager(this)
        dbManager = DatabaseManager(this)
        mongoManager = MongoManager()
        listenerManager = ListenerManager(this)
        taskManager = TaskManager(this)
        effectManager = EffectManager(this)
        cooldownManager = CooldownManager(this)
        scoreboards = ScoreboardManager(this)
        hologram = HologramManager(this)


        config = ConfigManager(this, "config.yml")
        Settings.load(config.getConfig())
        config.reloadConfig()


        log.info("----------------------------------")
        log.info("   CoreK habilitado (v${description.version})")
        log.info("   Desarrollado por: KurakyStudio")
        log.info("----------------------------------")

    }

    fun runAsync(task: Runnable) {
        server.scheduler.runTaskAsynchronously(this, task)
    }

    override fun onDisable() {
        hologram.clearAll()
        scoreboards.clearAll()
        config.saveConfig()
        log.info("CoreK deshabilitado. Guardando datos...")
    }



}