package ui.unq.edu.ar.appModel

import data.idGenerator
import domain.*
import org.uqbar.commons.model.annotations.Observable



@Observable
class UNQFlixAppModel {
    var system : UNQFlix = UNQFlix()
    var series : MutableList<SerieAppModel> = initSeries()
    var selectSerie : SerieAppModel? = null
    var allAategories = initCategories()
    var allContents = initContents()

    fun initSeries() : MutableList<SerieAppModel>{

        return system.series.map{SerieAppModel(it)}.toMutableList()
    }
    fun initCategories() : MutableList<Category> {
        return system.categories.toMutableList()
    }
    private fun initContents() : MutableList<Content>{
        return system.banners.toMutableList()
    }

    fun newSerie(
        title: String,
        poster: String,
        description: String,
        state: ContentState,
        categories: MutableList<Category>,
        relateContent: MutableList<Content>,
        seasons : MutableList<Season> = mutableListOf()
    ) {
        val serie = Serie(idGenerator.nextSerieId(), title, description, poster, state, categories, seasons, relateContent)
        system.addSerie(serie)
        this.series.add(SerieAppModel(serie))

    }
    fun eliminarSerie(serie: SerieAppModel?){
        system.deleteSerie(serie?.id!!)
    }

}