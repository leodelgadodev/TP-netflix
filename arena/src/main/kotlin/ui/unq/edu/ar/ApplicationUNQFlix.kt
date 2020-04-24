package ui.unq.edu.ar

import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import ui.unq.edu.ar.appModel.UNQFlixAppModel
import ui.unq.edu.ar.window.WindowUNQFlix


class ApplicationUNQFlix : Application(){
    // Test commit
    override fun createMainWindow(): Window<*> {
        return WindowUNQFlix(this, UNQFlixAppModel())
    }
}

fun main(){
    ApplicationUNQFlix().start()
}