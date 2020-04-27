package ui.unq.edu.ar.mainWindow

import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window


class ApplicationUNQFlix : Application() {
    override fun createMainWindow(): Window<*> {
        return UNQFlixWindow(this, UNQFlixAppModel())
    }
}

fun main() {
    ApplicationUNQFlix().start()
}