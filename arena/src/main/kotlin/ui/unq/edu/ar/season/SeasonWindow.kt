package ui.unq.edu.ar.season

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.season.model.SeasonAppModel
import ui.unq.edu.ar.season.view.ConfirmDeleteSeasonDialog
import ui.unq.edu.ar.season.view.ModifySeasonDialog
import ui.unq.edu.ar.season.view.NewSeasonDialog
import ui.unq.edu.ar.serie.model.SerieAppModel

class SeasonWindow(owner: WindowOwner, serieAppModel: SerieAppModel) : SimpleWindow<SerieAppModel>(owner, serieAppModel) {

    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(mainPanel: Panel) {
        title = "Seasons"
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) with {
                text = "Test"
                fontSize = 12
                height = 50
                width = 300
            }
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
                    //ChapterWindow(thisWindow, thisWindow.modelObject.selectedSeason!!
                    //).open()
                }
            }

            Button(it) with {
                text = "Modify Season"
                onClick {
                    if (thisWindow.modelObject.selectedSeason != null)
                        ModifySeasonDialog(
                            thisWindow,
                            SeasonAppModel(thisWindow.modelObject, thisWindow.modelObject.selectedSeason!!.model)
                        ).open()
                }
            }

            Button(it) with {
                text = "Delete Season"
                onClick {
                    if (thisWindow.modelObject.selectedSeason != null)
                        ConfirmDeleteSeasonDialog(
                            thisWindow, thisWindow.modelObject
                        ).open()
                }
            }
        }
    }
}