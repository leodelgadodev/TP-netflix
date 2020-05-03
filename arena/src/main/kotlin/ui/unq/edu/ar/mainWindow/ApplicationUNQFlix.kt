package ui.unq.edu.ar.mainWindow

import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import ui.unq.edu.ar.serie.view.SeriesWindow


class ApplicationUNQFlix : Application() {
    override fun createMainWindow(): Window<*> {
        return SeriesWindow(this, UNQFlixAppModel())
    }
}

fun main() {
    ApplicationUNQFlix().start()
}