package ui.unq.edu.ar

import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import ui.unq.edu.ar.appModel.SeriesAppModel

//import domain.UNQFlix

class ApplicationUNQFlix : Application(){

    override fun createMainWindow(): Window<*> {
        return WindowUNQFlix(this, SeriesAppModel())
    }
}

fun main(){
    ApplicationUNQFlix().start()
}