package ui.unq.edu.ar.season.view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.chapter.view.ChaptersWindow
import ui.unq.edu.ar.exceptions.NoSelectedException
import ui.unq.edu.ar.season.model.SeasonAppModel
import ui.unq.edu.ar.season.view.ConfirmDeleteSeasonDialog
import ui.unq.edu.ar.season.view.ModifySeasonDialog
import ui.unq.edu.ar.season.view.NewSeasonDialog
import ui.unq.edu.ar.serie.model.SerieAppModel

class SeasonWindow(owner: WindowOwner, serieAppModel: SerieAppModel) : SimpleWindow<SerieAppModel>(owner, serieAppModel) {

    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(mainPanel: Panel) {
        title = "Seasons"

        Label(mainPanel) with {
            fontSize = 18
            bindTo("title")
            alignLeft()
        }
            table<SeasonAppModel>(mainPanel) with {
                visibleRows = 10
                column {
                    title = "Title"
                    fixedSize = 100
                    bindContentsTo("nombre")
                }

                column {
                    title = "Description"
                    fixedSize = 400
                    bindContentsTo("descripcion")
                }

                column {
                    title = "#Chapters"
                    fixedSize = 100
                    bindContentsTo("cantCapitulos")
                }
                bindSelectionTo("selectedSeason")
                bindItemsTo("seasons")
            }


// --------------------------------------------------
// Season CRUD Buttons
// --------------------------------------------------

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                text = "New Season"
                onClick {
                    NewSeasonDialog(
                        thisWindow, SeasonAppModel(thisWindow.modelObject)
                    ).open()
                }
            }

            Button(it) with {
                text = "View Chapters"
                onClick {
                    if(thisWindow.modelObject.selectedSeason != null) {
                        ChaptersWindow(
                            thisWindow, thisWindow.modelObject.selectedSeason!!
                        ).open()
                    } else {
                        throw NoSelectedException("Please, select an item from the list.")
                    }
                }
            }

            Button(it) with {
                text = "Modify Season"
                onClick {
                    if (thisWindow.modelObject.selectedSeason != null) {
                        ModifySeasonDialog(
                            thisWindow,
                            SeasonAppModel(thisWindow.modelObject, thisWindow.modelObject.selectedSeason!!.model)
                        ).open()
                    } else {
                        throw NoSelectedException("Please, select an item from the list.")
                    }
                }
            }

            Button(it) with {
                text = "Delete Season"
                onClick {
                    if (thisWindow.modelObject.selectedSeason != null) {
                        ConfirmDeleteSeasonDialog(
                            thisWindow, thisWindow.modelObject
                        ).open()
                    } else {
                        throw NoSelectedException("Please, select an item from the list.")
                    }
                }
            }
        }
    }
}