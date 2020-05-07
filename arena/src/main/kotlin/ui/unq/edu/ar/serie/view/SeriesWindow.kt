package ui.unq.edu.ar.serie.view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.exceptions.NoSelectedException
import ui.unq.edu.ar.mainWindow.UNQFlixAppModel
import ui.unq.edu.ar.season.view.SeasonWindow
import ui.unq.edu.ar.serie.model.SerieAppModel

class SeriesWindow (owner : WindowOwner, unqFlixAppModel: UNQFlixAppModel) : SimpleWindow<UNQFlixAppModel>(owner, unqFlixAppModel) {
    override fun addActions(p0: Panel?) {}

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

                bindTo("busquedaInput")
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
                title = "#Seasons"
                weight = 75
                bindContentsTo("cantSeasons")
            }

            column {
                title = "State"
                weight = 85
                bindContentsTo("stateDescription")
            }
            bindSelectionTo("selectedSerie")
            bindItemsTo("seriesBuscadas")
        }

// --------------------------------------
// Series CRUD Buttons
// --------------------------------------

        Panel(mainPanel) with {
            asHorizontal()

            Button(it) with {
                text = "New Serie"
                onClick {
                    NewSerieDialog(
                        thisWindow,
                        SerieAppModel(thisWindow.modelObject)
                    ).open()
                }
            }

            Button(it) with {
                text = "View Seasons"
                onClick {
                    if (thisWindow.modelObject.selectedSerie != null) {
                        SeasonWindow(
                            thisWindow, thisWindow.modelObject.selectedSerie!!
                        ).open()
                    } else {
                        throw NoSelectedException("Please, select an item from the list.")
                    }
                }
            }

            Button(it) with {
                text = "Modify Serie"
                onClick {
                    if (thisWindow.modelObject.selectedSerie != null) {
                        ModifySerieDialog(
                            thisWindow,
                            SerieAppModel(thisWindow.modelObject, thisWindow.modelObject.selectedSerie!!.model)
                        ).open()
                    } else {
                        throw NoSelectedException("Please, select an item from the list.")
                    }
                }
            }

            Button(it) with {
                text = "Delete Serie"
                onClick {
                    if (thisWindow.modelObject.selectedSerie != null) {
                        ConfirmDeleteSerieDialog(thisWindow, thisWindow.modelObject).open()
                    } else {
                        throw NoSelectedException("Please, select an item from the list.")
                    }
                }
            }
        }
    }
}