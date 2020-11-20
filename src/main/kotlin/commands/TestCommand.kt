package commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

object TestCommand : Command {

    override fun allowed(args: Array<String>, event: MessageReceivedEvent): Boolean {
        return true
    }

    override fun action(args: Array<String>, event: MessageReceivedEvent) {
        event.channel.sendMessage("Pong.").queue()
    }

    override fun denied(args: Array<String>, event: MessageReceivedEvent) {
        event.channel.sendMessage("Uhm, looks like you're not allowed to ping.").queue()
    }

    override fun executed(success: Boolean, event: MessageReceivedEvent) {}

    override fun help(): String = "This is the help for the `ping`-command."

    override fun usage(): String = "Pong."
}
