package com.kuraky.CoreK.effects

import org.bukkit.entity.LivingEntity

interface CoreEffect {
    fun apply(target: LivingEntity)
}

// Clase abstracta SÓLO para los efectos que se van a auto-registrar por ID
abstract class RegisteredEffect(val id: String) : CoreEffect