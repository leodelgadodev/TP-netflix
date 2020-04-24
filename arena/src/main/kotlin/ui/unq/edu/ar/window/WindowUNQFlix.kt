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

class WindowUNQFlix (owner : WindowOwner, unqFlixAppModel: UNQFlixAppModel) : SimpleWindow<UNQFlixAppModel>(owner, unqFlixAppModel){
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
            bindItemsTo("series")
            bindSelectionTo("selectSerie")

            visibleRows = 10

            column {
                title = "#"
                weight = 50
                bindContentsTo("id")
            }

            column {
                title = "Title"
                weight = 200
                bindContentsTo("title")
            }

            column {
                title = "#Season"
                weight = 75
                bindContentsTo("cantSeason")
            }

            column {
                title = "State"
                weight = 50
                bindContentsTo("contentState")
            }
        }

        val model = modelObject

        Panel(mainPanel) with{
            asHorizontal()
            Button(it) with {
                text = "New Serie"
                onClick(Action{ AddNewSerieDialog(this@WindowUNQFlix, SerieAppModel(unqFlixAppModel = model)).open()})
            }
            Button(it) with {
                text = "Delete Serie"
                onClick(Action {
                    if(model.selectSerie != null)
                        ConfirmDeleteSerieDialog(this@WindowUNQFlix, model).open()
                })
            }
            Button(it) with {
                text = "Modify Serie"
                onClick(Action {
                    if(model.selectSerie != null)
                        ModificarSerieDialog(this@WindowUNQFlix, model.selectSerie!!).open()
                })
            }
            Button(it) with {
                text = "Show Serie"

            }
        }

    }
}