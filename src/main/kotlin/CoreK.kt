import org.bukkit.plugin.Plugin
import java.util.logging.Logger

class CoreK : Plugin {

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