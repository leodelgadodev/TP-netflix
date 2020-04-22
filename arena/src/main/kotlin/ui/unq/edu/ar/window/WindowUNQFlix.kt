package ui.unq.edu.ar.window

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import ui.unq.edu.ar.appModel.SerieAppModel
import ui.unq.edu.ar.appModel.UNQFlixAppModel

class WindowUNQFlix (owner : WindowOwner, UNQFlixAppModel: UNQFlixAppModel) : SimpleWindow<UNQFlixAppModel>(owner, UNQFlixAppModel){
    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "UNQFlix"

        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Search: "
                height = 15
                width = 50
            }
            TextBox(it) with {
                height = 15
                width = 300
            }
        }

        table<SerieAppModel>(mainPanel) with {
            title = "Series"
            column {
                title = "#"
                width = 50
                bindContentsTo("id")
            }

            column {
                title = "Title"
                bindContentsTo("title")
            }

            column {
                title = "#Season"
                bindContentsTo("cantSeason")
            }

            column {
                title = "State"
                bindContentsTo("state")
            }
        }
        Button(mainPanel) with {
            text = "New Serie"
            onClick(Action{ AddNewSerieDialog(this@WindowUNQFlix, modelObject).open()})
        }

        Button(mainPanel) with {
            text = "Delete Serie"
        }

        Button(mainPanel) with {
            text = "Modified Serie"
        }

        Button(mainPanel) with {
            text = "Show Serie"
        }

    }
}