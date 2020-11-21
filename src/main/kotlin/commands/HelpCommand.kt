package commands

// TODO Generate and import Build Config, should look a little like this:
import JDATemplateKotlin.BuildConfig
import core.BotHelper
import core.CommandHandler
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import util.Static

object HelpCommand : Command {

    override fun allowed(args: Array<String>, event: MessageReceivedEvent): Boolean {
        return true
    }

    override fun action(args: Array<String>, event: MessageReceivedEvent) {
        if (args.isEmpty() || args[0] == "allow") {
            val stringBuilder = StringBuilder()
            CommandHandler.commands.forEach { (key, command) ->
                if (command.allowed(args, event)) {
                    stringBuilder.append("\n**${Static.COMMAND_PREFIX}$key**: ${command.help()}")
                }
            }
            event.channel.sendMessage(
                BotHelper.getDefaultEmbedBuilder(
                    title = "Bot Help",
                    description = "Here's a list of commands you can use. If you want detailed information on a command, use `${Static.COMMAND_PREFIX}help [command]`.\n$stringBuilder",
                    footer = "Bot Version ${BuildConfig.VERSION}"
                ).build()
            ).queue()
        } else {
            var commandFound = false
            CommandHandler.commands.forEach { (key, command) ->
                if (args[0] == key) {
                    event.channel.sendMessage(
                        BotHelper.getDefaultEmbedBuilder(
                            title = "Usage for `${Static.COMMAND_PREFIX}$key`",
                            footer = if (!command.allowed(args, event)) {
                                "You don't have the rights to use this command on the current server."
                            } else {
                                null
                            }
                        )
                            .addField(command.help(), command.usage(), false)
                            .build()
                    ).queue()
                    commandFound = true
                }
            }
            if (!commandFound) {
                event.channel.sendMessage(
                    BotHelper.getDefaultEmbedBuilder(
                        title = "Error",
                        description = "Command ${Static.COMMAND_PREFIX}${args[0]} can't be found."
                    ).build()
                ).queue()
            }
        }
    }

    override fun denied(args: Array<String>, event: MessageReceivedEvent) {
        event.channel.sendMessage("You are not allowed to do look at the commands monkaS.").queue()
    }

    override fun executed(success: Boolean, event: MessageReceivedEvent) {}

    override fun help(): String = "Shows this message"

    override fun usage(): String = "Displays information about the available commands.\n" +
        "Developed by gxalpha."

}
