package ui.unq.edu.ar.mainWindow

import data.getUNQFlix
import domain.*
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import ui.unq.edu.ar.serie.model.CategoryAppModel
import ui.unq.edu.ar.serie.model.ContentAppModel
import ui.unq.edu.ar.serie.model.SerieAppModel


@Observable
class UNQFlixAppModel {
    var system : UNQFlix  = getUNQFlix()
    val idGenerator = IdGenerator()
    var busquedaInput : String = ""
        set(value) {
            field = value.toLowerCase()
            buscarSeries()
        }

    var categories : MutableList<CategoryAppModel> = initCategories()
    var content : MutableList<ContentAppModel> = initContent()
    var series : MutableList<SerieAppModel> = initSeries()
    var seriesBuscadas : MutableList<SerieAppModel> = series
    var selectedSerie : SerieAppModel? = null

    fun initSeries() : MutableList<SerieAppModel>{
        for (s in system.series){
            idGenerator.nextSerieId()
        }
        return this.system.series.map{ SerieAppModel(this, it) }.toMutableList()

    }

    fun initCategories() : MutableList<CategoryAppModel> {
        return system.categories.map { CategoryAppModel(it) }.toMutableList()
    }

    fun newSerie(serieAppModel: SerieAppModel) {
        try{
            system.addSerie(serieAppModel.model!!)
        } catch (e : ExistsException) {
            throw UserException(e.message)
        }

        this.content.add(ContentAppModel(serieAppModel.model!!))
        this.series.add(serieAppModel)
        this.busquedaInput = ""
    }

    fun modificarSerie(serieAppModel: SerieAppModel) {
        eliminarSerie(serieAppModel.id)
        newSerie(serieAppModel)
    }

    fun eliminarSerie(id:String) {
        system.deleteSerie(id)
        removeContent(id)
        series.removeIf { it.id === id }
        this.busquedaInput = ""
    }

    fun removeContent(id : String){
        content.removeIf { it.id === id }
    }

    fun getCategoriesAppModel(ids : MutableList<String>) : MutableList<CategoryAppModel> {
        return categories.filter { ids.contains(it.id) }.toMutableList()
    }

    fun allContents() : MutableList<ContentAppModel>{
        return content
    }

    fun getContentsAppModel(ids: MutableList<String>) : MutableList<ContentAppModel>{
        return content.filter { ids.contains(it.id) }.toMutableList()
    }

    fun initContent() : MutableList<ContentAppModel>{
        content = system.series.map { ContentAppModel(it) }.toMutableList()
        content.addAll(system.movies.map { ContentAppModel(it) })
        return content
    }

    fun allCategories(): MutableList<CategoryAppModel> {
        return categories
    }

    private fun buscarSeries() {
        seriesBuscadas = series.filter { it.title.toLowerCase().contains(busquedaInput) }.toMutableList()
    }
}