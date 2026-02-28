package com.kuraky.CoreK.effects

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

// --- Efecto de Poción ---
class PotionCoreEffect(
    private val type: PotionEffectType,
    private val durationTicks: Int,
    private val amplifier: Int = 0
) : CoreEffect {
    override fun apply(target: LivingEntity) {
        target.addPotionEffect(PotionEffect(type, durationTicks, amplifier))
    }
}

// --- Efecto de Sonido ---
class SoundCoreEffect(
    private val sound: Sound,
    private val volume: Float = 1.0f,
    private val pitch: Float = 1.0f
) : CoreEffect {
    override fun apply(target: LivingEntity) {
        target.world.playSound(target.location, sound, volume, pitch)
    }
}

// --- Efecto de Partículas ---
class ParticleCoreEffect(
    private val particle: Particle,
    private val count: Int = 10,
    private val offsetY: Double = 1.0
) : CoreEffect {
    override fun apply(target: LivingEntity) {
        target.world.spawnParticle(particle, target.location.add(0.0, offsetY, 0.0), count)
    }
}