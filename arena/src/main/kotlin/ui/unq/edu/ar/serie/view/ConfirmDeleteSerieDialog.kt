package ui.unq.edu.ar.serie.view

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import ui.unq.edu.ar.mainWindow.UNQFlixAppModel

class ConfirmDeleteSerieDialog(owner : WindowOwner, model : UNQFlixAppModel) : Dialog<UNQFlixAppModel>(owner, model){

    override fun createFormPanel(mainPanel : Panel) {

        Label(mainPanel) with {
            text = "Are you sure? This serie will be lost forever!!"
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Delete"
                onClick {
                    eliminarSerie()
                    close()
                }
            }
            Button(it) with {
                caption = "Cancel"
                onClick { close() }
            }
        }
    }

    private fun eliminarSerie() {
        modelObject.eliminarSerie(modelObject.selectSerie!!)
    }
}