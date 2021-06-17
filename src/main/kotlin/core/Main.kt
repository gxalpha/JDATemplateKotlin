package core

import listeners.CommandListener
import listeners.ReadyListener
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import util.Static
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.*
import kotlin.system.exitProcess

fun main() {

    try {
        Static.DISCORD_TOKEN = readFromFile("discord_token.txt")
    } catch (e: FileNotFoundException) {
        System.err.println("File not found: ${e.message}.\nPlease add the file with the corresponding token and try again.\nExiting process...\n")
        exitProcess(1)
    }

    val jda = JDABuilder.createDefault(Static.DISCORD_TOKEN)
        .setActivity(Activity.playing(Static.BOT_GAME_PLAYING))
        .setAutoReconnect(true)
        .addEventListeners(ReadyListener, CommandListener)
        .build()
    try {
        jda.awaitReady()
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }

}

private fun readFromFile(file: String): String = Scanner(FileInputStream(file)).nextLine()
