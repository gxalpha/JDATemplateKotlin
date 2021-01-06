package core

import commands.HelpCommand
import commands.TestCommand
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import util.Static

object CommandHandler {

    val commands = mapOf(
        "ping" to TestCommand,
        "help" to HelpCommand
    )

    @Suppress("MapGetWithNotNullAssertionOperator")
    fun handleCommand(container: CommandContainer) {
        if (commands.containsKey(container.invoke)) {
            val allowed = commands[container.invoke]!!.allowed(
                container.args,
                container.event
            ) //Should be null-safe, as it was checked above
            if (allowed) {
                commands[container.invoke]!!.action(container.args, container.event)
            } else {
                commands[container.invoke]!!.denied(container.args, container.event)
            }
            commands[container.invoke]!!.executed(allowed, container.event)
        } else {
            container.event.channel.sendMessage(
                BotHelper.getDefaultEmbedBuilder(
                    title = "Error",
                    description = "Command **${container.invoke}** can't be found."
                ).build()
            ).queue()
        }
    }

    fun parseMessage(
        raw: String,
        event: MessageReceivedEvent
    ): CommandContainer {
        var beheaded = raw.removePrefix(Static.COMMAND_PREFIX)
        // Remove spaces from behind the prefix
        while (beheaded.startsWith(" ")) {
            beheaded = beheaded.removePrefix(" ")
        }
        // Remove spaces from rest
        val beheadedSplit = (beheaded.split(" ") as MutableList).filterNot { it == "" }.toTypedArray()

        val invoke = beheadedSplit[0].toLowerCase()
        val args = ArrayList<String>()
        args.addAll(beheadedSplit)
        args.removeFirst()
        return CommandContainer(
            raw,
            beheaded,
            beheadedSplit,
            invoke,
            args.toTypedArray(),
            event
        )
    }

    class CommandContainer(
        val raw: String,
        val beheaded: String,
        val beheadedSplit: Array<String>,
        val invoke: String,
        val args: Array<String>,
        val event: MessageReceivedEvent
    )
}
