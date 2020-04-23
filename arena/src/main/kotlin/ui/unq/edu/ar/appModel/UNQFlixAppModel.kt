package ui.unq.edu.ar.appModel

import domain.Category
import domain.Content
import domain.Serie
import domain.UNQFlix
import org.uqbar.commons.model.annotations.Observable


@Observable
class UNQFlixAppModel {

    var system : UNQFlix = UNQFlix()
    var series = initSeries()
    var selectSerie : SerieAppModel? = null
    var categories = initCategories()
    var contents = initContents()

    private fun initCategories() : MutableList<Category> {
        return system.categories.toMutableList()
    }

    private fun initSeries() : MutableList<SerieAppModel>{
        return system.series.map { SerieAppModel() }.toMutableList()
    }

    private fun initContents() : MutableList<Content>{
        return system.banners.toMutableList()
    }

    fun nuevaSerie(serie : Serie) {
        system.addSerie(serie)
    }
    fun eliminarSerie(serie: SerieAppModel?){
        system.deleteSerie(serie?.id!!)
    }

}