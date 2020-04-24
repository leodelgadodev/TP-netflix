package ui.unq.edu.ar.appModel

import domain.*
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException


@Observable
class UNQFlixAppModel {
    var system : UNQFlix = UNQFlix()
    var series : MutableList<SerieAppModel> = initSeries()
    var selectSerie : SerieAppModel? = null
    val idGenerator = IdGenerator()
    var allAategories = initCategories()
    var allContents = initContents()
    var allMovies = initMovies()

    fun initSeries() : MutableList<SerieAppModel>{
        return system.series.map{SerieAppModel(serie = it)}.toMutableList()
    }
    fun initCategories() : MutableList<Category> {
        var categoriesName = arrayOf("Action", "Adventure", "Animation", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy",
                                     "History", "Horror", "Music", "Mistery", "Romance", "Sience Fiction", "TV Movie", "War", "Thriller", "Western", "Unknown")
        for(name : String in categoriesName){
            system.addCategory(Category(idGenerator.nextCategoryId(), name))
        }
        return system.categories.toMutableList()
    }
    fun initContents() : MutableList<Content>{
        return system.banners.toMutableList()
    }
    fun initMovies() : MutableList<Movie>{
        return system.movies.toMutableList()
    }

    fun newSerie(serieAppModel: SerieAppModel) {
        try{
            system.addSerie(serieAppModel.model!!)
        } catch (e : ExistsException){
            throw UserException(e.message)
        }
        this.series.add(serieAppModel)

    }
    fun eliminarSerie(serie: SerieAppModel?){
        if (serie != null) {
            system.deleteSerie(serie.id)
            series.remove(serie)
        }
    }
}