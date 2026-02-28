package utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

object ColorUtils {
    private val mm= MiniMessage.miniMessage()

    fun format(text:String): Component {
        return Component.text(text)
    }

}