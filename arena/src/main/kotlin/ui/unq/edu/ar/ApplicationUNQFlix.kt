package ui.unq.edu.ar

import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import ui.unq.edu.ar.appModel.UNQFlixAppModel

//import domain.UNQFlix

class ApplicationUNQFlix : Application(){

    override fun createMainWindow(): Window<*> {
        return WindowUNQFlix(this, UNQFlixAppModel())
    }
}

fun main(){
    ApplicationUNQFlix().start()
}