package com.kuraky.CoreK

import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

class CoreK : JavaPlugin() {

    companion object {
        lateinit var INSTANCE: CoreK
        private set

        lateinit var log : Logger
    }

    override fun onEnable() {
        INSTANCE = this
        log = this.logger

        log.info("----------------------------------")
        log.info("   CoreK habilitado (v${description.version})")
        log.info("   Desarrollado por: KurakyStudio")
        log.info("----------------------------------")

    }

    override fun onDisable() {
        log.info("CoreK deshabilitado. Guardando datos...")
    }

}