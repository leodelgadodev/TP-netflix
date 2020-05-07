package ui.unq.edu.ar.season.view

import org.uqbar.arena.kotlin.extensions.asHorizontal
import org.uqbar.arena.kotlin.extensions.caption
import org.uqbar.arena.kotlin.extensions.text
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import ui.unq.edu.ar.serie.model.SerieAppModel

class ConfirmDeleteSeasonDialog(owner: SeasonWindow, model: SerieAppModel) : Dialog<SerieAppModel>(owner, model)  {

    override fun createFormPanel(mainPanel : Panel) {

        Label(mainPanel) with {
            text = "Are you sure? This season will be lost forever!!"
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Delete"
                onClick {
                    deleteSeason()
                    close()
                }
            }
            Button(it) with {
                caption = "Cancel"
                onClick { close() }
            }
        }
    }

    private fun deleteSeason() {
        modelObject.deleteSeason(modelObject.selectedSeason!!)
    }
}