package listeners

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.EventListener

object ReadyListener : EventListener {

    override fun onEvent(event: GenericEvent) {
        if (event is ReadyEvent) {
            println("Connection successful! Online in following servers:")
            for (guild in event.getJDA().guilds) {
                println(guild.id + ": \"" + guild.name + "\"")
            }
        }
    }
}
