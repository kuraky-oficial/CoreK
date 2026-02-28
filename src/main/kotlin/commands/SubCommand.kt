package com.kuraky.CoreK.commands

import org.bukkit.command.CommandSender

interface SubCommand {
    val name: String
    val description: String
    val usage: String?

    fun execute(sender: CommandSender, args: Array<out String>)
}