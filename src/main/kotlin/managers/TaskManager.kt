package com.kuraky.CoreK.managers

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class TaskManager(private val plugin: JavaPlugin) {

    fun runSync(task: Runnable): BukkitTask {
        return Bukkit.getScheduler().runTask(plugin, task)
    }

    fun runAsync(task: Runnable): BukkitTask {
        return Bukkit.getScheduler().runTaskAsynchronously(plugin, task)
    }

    fun runLater(delay: Long,task: Runnable): BukkitTask {
        return Bukkit.getScheduler().runTaskLater(plugin, task, delay)
    }

    fun runLaterAsync(delay: Long,task: Runnable): BukkitTask {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delay)
    }

    fun runTimer(delay: Long, period: Long, task: Runnable): BukkitTask {
        return Bukkit.getScheduler().runTaskTimer(plugin, task, delay, period)
    }

}