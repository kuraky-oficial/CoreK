package com.kuraky.CoreK.managers

import com.kuraky.CoreK.commands.BaseCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections
import java.lang.reflect.Field

class CommandManager (private val plugin: JavaPlugin) {

    private var commandMap: CommandMap? = null

    init {
        setupCommandMap()
    }

    private fun setupCommandMap() {

        try {

            val commandField: Field = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
            commandField.isAccessible = true
            commandMap = commandField.get(Bukkit.getServer()) as CommandMap
        } catch (e: NoSuchFieldException) {
            plugin.logger.severe("¡Error crítico! No se pudo acceder al CommandMap del servidor.")
        }

    }

    fun autoRegister(packageName: String) {

        val reflections = Reflections(packageName)

        val commandClasses = reflections.getSubTypesOf(BaseCommand::class.java)

        for (clazz in commandClasses) {
            try {

                val command = clazz.getDeclaredConstructor().newInstance()
                commandMap?.register(plugin.name, command)
                plugin.logger.info("Comando registrado automáticamente: /${command.name}")

            }catch (e: Exception){
                plugin.logger.warning("No se pudo registrar el comando ${clazz.simpleName}: ${e.message}")
            }
        }
    }

}