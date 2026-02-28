package com.kuraky.CoreK.effects

import org.bukkit.entity.LivingEntity

class CombinedCoreEffect(
    private val effects: List<CoreEffect>
): CoreEffect {
    constructor(vararg effects: CoreEffect) : this(effects.toList())

    override fun apply(target: LivingEntity) {
        for (effect in effects) {
            effect.apply(target)
        }
    }

}