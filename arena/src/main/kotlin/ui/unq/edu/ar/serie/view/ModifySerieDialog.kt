package ui.unq.edu.ar.serie.view

import ui.unq.edu.ar.serie.model.SerieAppModel

class ModifySerieDialog(owner: SeriesWindow, model: SerieAppModel) : NewSerieDialog(owner, model){

    override fun accept() {
        modificarSerie()
        close()
    }

    private fun modificarSerie() {
        modelObject.modificarSerie(modelObject.title, modelObject.poster, modelObject.description, modelObject.state, modelObject.categories, modelObject.relatedContent)
    }
}