package ui.unq.edu.ar.appModel

import domain.*
import org.uqbar.commons.model.annotations.Observable


@Observable
class UNQFlixAppModel {

    var system : UNQFlix = UNQFlix()
    var series : MutableList<SerieAppModel> = mutableListOf()
    var selectSerie : SerieAppModel? = null
    var allAategories = initCategories()
    var allContents = initContents()
    var curId = 0

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
        relateContent: MutableList<Content>
    ) {
        var serie = Serie("serie#"+ curId, title, description, poster, state, categories, mutableListOf(), relateContent)
        system.addSerie(serie)
        this.series.add(SerieAppModel(serie))
        curId++
    }
    fun eliminarSerie(serie: SerieAppModel?){
        system.deleteSerie(serie?.id!!)
    }

}