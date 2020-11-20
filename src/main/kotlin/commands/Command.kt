package commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

interface Command {

    fun allowed(args: Array<String>, event: MessageReceivedEvent): Boolean
    fun action(args: Array<String>, event: MessageReceivedEvent)
    fun denied(args: Array<String>, event: MessageReceivedEvent)
    fun executed(success: Boolean, event: MessageReceivedEvent)
    fun help(): String
    fun usage(): String

}
