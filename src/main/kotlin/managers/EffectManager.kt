package com.kuraky.CoreK.managers

import com.kuraky.CoreK.effects.CoreEffect
import org.bukkit.entity.LivingEntity
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections

class EffectManager(private val plugin: JavaPlugin) {

    private val effectsRegistry = mutableMapOf<String, CoreEffect>()

    fun autoRegister(packageName: String) {
        val reflections = Reflections(packageName)
        val effectClasses = reflections.getSubTypesOf(CoreEffect::class.java)

        for (clazz in effectClasses) {
            try {
                // Instanciamos la clase del efecto (requiere que el constructor esté vacío)
                val effect = clazz.getDeclaredConstructor().newInstance()

                // Lo registramos usando el ID definido en la clase
                effectsRegistry[effect.id.lowercase()] = effect
                plugin.logger.info("Efecto registrado automáticamente: ${effect.id}")

            } catch (e: Exception) {
                plugin.logger.warning("No se pudo registrar el efecto ${clazz.simpleName}: ${e.message}")
            }
        }
    }

    fun registerEffect(id: String, effect: CoreEffect) {
        effectsRegistry[id.lowercase()] = effect
        plugin.logger.info("Efecto registrado exitosamente: $id")
    }

    fun getEffect(id: String): CoreEffect? {
        return effectsRegistry[id.lowercase()]
    }

    fun applyEffect(id: String, target: LivingEntity) : Boolean {

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