package com.kuraky.CoreK.managers

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections

class ListenerManager (private val plugin: JavaPlugin) {

    fun autoRgister(packageName: String) {

        val reflections = Reflections(packageName)
        val listenerClasses = reflections.getSubTypesOf(Listener::class.java)

        for (clazz in listenerClasses) {
            try {
                val listener = clazz.getDeclaredConstructor().newInstance()
                Bukkit.getPluginManager().registerEvents(listener, plugin)

                plugin.logger.info("Evento registrado automáticamente: ${clazz.simpleName}")
            } catch (e: Exception) {
                plugin.logger.warning("No se pudo registrar el evento ${clazz.simpleName}: ${e.message}")
            }
        }

    }


}