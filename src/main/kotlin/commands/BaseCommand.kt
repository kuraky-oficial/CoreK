package com.kuraky.CoreK.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender

abstract class BaseCommand (
    name: String,
    description: String = "",
    usage: String = "/$name",
    aliases: List<String> = emptyList()
): Command(name, description, usage, aliases) {

    private val subCommands = mutableMapOf<String, SubCommand>()

    fun registerSubCommand(sub: SubCommand) {
        subCommands[sub.name.lowercase()] = sub
    }

    override fun execute(sender: CommandSender, label: String, args: Array<out String>): Boolean {

        if (args.isEmpty()) {
            val subName = args[0].lowercase()
            val sub = subCommands[subName]

            if (sub != null) {
                if (sub.permission != null && !sender.hasPermission(sub.permission!!)) {
                    sender.sendMessage("§cNo tienes permiso para usar este subcomando.")
                    return true
                }

                sub.execute(sender, args.copyOfRange(1, args.size))
                return true
            }
            runDefault(sender, args)
            return true

        }

    }

    abstract fun runDefault(sender: CommandSender, args: Array<out String>)

}