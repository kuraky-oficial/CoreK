package com.kuraky.CoreK.effects

import org.bukkit.entity.LivingEntity

abstract class CoreEffect(val id: String) {

    abstract fun apply(target: LivingEntity)
}