package ui.unq.edu.ar.serie.view

import ui.unq.edu.ar.serie.model.SerieAppModel
import ui.unq.edu.ar.mainWindow.UNQFlixWindow

class ModifySerieDialog(owner: UNQFlixWindow, model: SerieAppModel) : NewSerieDialog(owner, model){
    val oldState = model.contentState // no se me ocurre otra forma de guardar este estado

    override fun cancelar() {
        modelObject.cancelar(oldState)
        close()
    }

    override fun aceptar() {
        modificarSerie()
        close()
    }

    private fun modificarSerie() {
        modelObject.modificarSerie(modelObject.title, modelObject.poster, modelObject.description, modelObject.contentState, modelObject.categories, modelObject.relatedContent)
    }
}