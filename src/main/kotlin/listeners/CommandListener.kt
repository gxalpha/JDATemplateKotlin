package listeners

import core.CommandHandler
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import util.Static

class CommandListener : EventListener {
    override fun onEvent(event: GenericEvent) {

        if (event is MessageReceivedEvent && !event.author.isBot) { //alternatively second part as "event.author.id != event.jda.selfUser.id" if bots should be able to use commands

            if (event.message.contentRaw.toLowerCase().startsWith(Static.COMMAND_PREFIX)) {
                CommandHandler.handleCommand(
                    container = CommandHandler.parseMessage(event.message.contentRaw, event),
                    event = event
                )
            }
        }
    }
}
