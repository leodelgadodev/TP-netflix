package ui.unq.edu.ar.window

import org.uqbar.arena.kotlin.extensions.asHorizontal
import org.uqbar.arena.kotlin.extensions.caption
import org.uqbar.arena.kotlin.extensions.text
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import ui.unq.edu.ar.appModel.UNQFlixAppModel

class ConfirmDeleteSerieDialog(owner : WindowOwner, model : UNQFlixAppModel) : Dialog<UNQFlixAppModel>(owner, model){

    override fun createFormPanel(mainPanel : Panel) {

        Label(mainPanel) with {
            text = "Estás seguro?"
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "aceptar"
                onClick(Action {
                    eliminarSerie()
                    close()
                })
            }
            Button(it) with {
                caption = "cancelar"
                onClick(Action { close() })
            }
        }
    }
    fun eliminarSerie(){
        modelObject.eliminarSerie(modelObject.selectSerie)
    }
}