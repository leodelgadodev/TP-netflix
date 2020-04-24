package ui.unq.edu.ar.appModel

import domain.*
import org.uqbar.commons.model.annotations.Observable



@Observable
class UNQFlixAppModel {
    var system : UNQFlix = UNQFlix()
    var series : MutableList<SerieAppModel> = initSeries()
    var selectSerie : SerieAppModel? = null
    var allAategories = initCategories()
    var allContents = initContents()
    var allMovies = initMovies()
    val idGenerator = IdGenerator()

    fun initSeries() : MutableList<SerieAppModel>{

        return system.series.map{SerieAppModel(serie = it)}.toMutableList()
    }
    fun initCategories() : MutableList<Category> {
        return system.categories.toMutableList()
    }
    fun initContents() : MutableList<Content>{
        return system.banners.toMutableList()
    }
    fun initMovies() : MutableList<Movie>{
        return system.movies.toMutableList()
    }

    fun newSerie(serieAppModel: SerieAppModel) {
        system.addSerie(serieAppModel.model!!)
        this.series.add(serieAppModel)

    }
    fun eliminarSerie(serie: SerieAppModel?){
        if (serie != null) {
            system.deleteSerie(serie.id)
            series.remove(serie)
        }
    }
}