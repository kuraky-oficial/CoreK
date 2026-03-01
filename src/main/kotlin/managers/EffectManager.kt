package com.kuraky.CoreK.managers

import com.kuraky.CoreK.effects.CoreEffect
import com.kuraky.CoreK.effects.RegisteredEffect
import org.bukkit.entity.LivingEntity
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections

class EffectManager(private val plugin: JavaPlugin) {

    private val effectsRegistry = mutableMapOf<String, CoreEffect>()

    fun autoRegister(packageName: String) {
        val reflections = Reflections(packageName)
        // AQUI ESTÁ LA MAGIA: Solo buscamos los 'RegisteredEffect'
        val effectClasses = reflections.getSubTypesOf(RegisteredEffect::class.java)

        for (clazz in effectClasses) {
            try {
                val effect = clazz.getDeclaredConstructor().newInstance()
                effectsRegistry[effect.id.lowercase()] = effect
                plugin.logger.info("Efecto registrado automáticamente: ${effect.id}")
            } catch (e: Exception) {
                plugin.logger.warning("No se pudo registrar el efecto ${clazz.simpleName}: ${e.message}")
            }
        }
    }

    // Permitimos registrar efectos manualmente también (útil para otros plugins)
    fun registerEffect(id: String, effect: CoreEffect) {
        effectsRegistry[id.lowercase()] = effect
        plugin.logger.info("Efecto registrado exitosamente: $id")
    }

    fun getEffect(id: String): CoreEffect? {
        return effectsRegistry[id.lowercase()]
    }

    fun applyEffect(id: String, target: LivingEntity): Boolean {
        val effect = getEffect(id)
        if (effect != null) {
            effect.apply(target)
            return true
        }
        plugin.logger.warning("Intento de aplicar un efecto que no existe: $id")
        return false
    }

    fun clearRegistry() {
        effectsRegistry.clear()
    }
}