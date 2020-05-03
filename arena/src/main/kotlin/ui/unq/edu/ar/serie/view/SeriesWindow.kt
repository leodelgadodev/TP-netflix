package ui.unq.edu.ar.serie.view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.mainWindow.UNQFlixAppModel
import ui.unq.edu.ar.season.SeasonWindow
import ui.unq.edu.ar.serie.model.SerieAppModel

class SeriesWindow (owner : WindowOwner, unqFlixAppModel: UNQFlixAppModel) : SimpleWindow<UNQFlixAppModel>(owner, unqFlixAppModel){
    override fun addActions(p0: Panel?) { }

    override fun createFormPanel(mainPanel: Panel) {
        title = "UNQFlix"
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Search: "
                fontSize = 12
                height = 16
                width = 50
            }
            TextBox(it) with {
                height = 16
                width = 300
            }
        }

        table<SerieAppModel>(mainPanel) with {
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
                weight = 85
                bindContentsTo("stateDescription")
            }
            bindSelectionTo("selectSerie")
            bindItemsTo("series")
        }

// --------------------------------------
// Series CRUD Buttons
// --------------------------------------

        Panel(mainPanel) with{
            asHorizontal()

            Button(it) with {
                text = "New Serie"
                onClick { NewSerieDialog(
                    thisWindow,
                    SerieAppModel(thisWindow.modelObject)
                ).open()}
            }

            Button(it) with {
                text = "Show Serie"
                onClick {
                    if(thisWindow.modelObject.selectSerie != null) {
                        SeasonWindow(thisWindow, thisWindow.modelObject.selectSerie!!)
                    }
                }
            }

            Button(it) with {
                text = "Modify Serie"
                onClick {
                    if(thisWindow.modelObject.selectSerie != null)
                        ModifySerieDialog(
                            thisWindow,
                            SerieAppModel(thisWindow.modelObject, thisWindow.modelObject.selectSerie!!.model)
                        ).open()
                }
            }

            Button(it) with {
                text = "Delete Serie"
                onClick {
                    if(thisWindow.modelObject.selectSerie != null)
                        ConfirmDeleteSerieDialog(thisWindow, thisWindow.modelObject).open()
                }
            }
        }
    }
}