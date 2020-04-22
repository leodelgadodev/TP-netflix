package ui.unq.edu.ar

import org.uqbar.arena.kotlin.extensions.text
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.appModel.SeriesAppModel

class WindowUNQFlix (owner : WindowOwner, seriesAppModel: SeriesAppModel) : SimpleWindow<SeriesAppModel>(owner, seriesAppModel){
    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "UNQFlix"
        Label(mainPanel) with{
            text = "Search"
        }
    }
}