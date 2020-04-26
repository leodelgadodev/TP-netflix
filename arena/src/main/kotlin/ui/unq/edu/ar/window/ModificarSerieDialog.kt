package ui.unq.edu.ar.window

import domain.Category
import domain.Content
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.lacar.ui.model.Action
import ui.unq.edu.ar.appModel.SerieAppModel

class ModificarSerieDialog(owner: WindowUNQFlix, model: SerieAppModel) : AddNewSerieDialog(owner, model){

    val oldState = model.contentState // no se me ocurre otra forma de guardar este estado

    fun modificarSerie(){
        modelObject.modificarSerie(modelObject.title, modelObject.poster, modelObject.description, modelObject.contentState, modelObject.categories, modelObject.relatedContent)
    }
    override fun cancelar(){
        modelObject.cancelar(oldState)
        close()
    }

    override fun aceptar() {
        modificarSerie()
        close()
    }
}