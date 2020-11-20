package core

import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

object BotHelper {

    fun getDefaultEmbedBuilder(
        title: String,
        description: String? = null,
        footer: String? = null,
        url: String? = null
    ): EmbedBuilder =
        EmbedBuilder()
            .setColor(Color(0, 60, 255))
            .setTitle(title, url)
            .setDescription(description)
            .setFooter(
                "${
                    if (footer != null) {
                        "$footer\n"
                    } else {
                        ""
                    }
                }[FOOTER]"
            )
}
