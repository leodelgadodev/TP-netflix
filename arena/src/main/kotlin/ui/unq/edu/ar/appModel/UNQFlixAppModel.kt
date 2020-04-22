package ui.unq.edu.ar.appModel

import domain.Category
import domain.Serie
import domain.UNQFlix
import org.uqbar.commons.model.annotations.Observable


@Observable
class UNQFlixAppModel {

    var system : UNQFlix = UNQFlix()
    var series = initSeries()
    var selectSerie : SerieAppModel? = null
    var categories = initCategories()

    private fun initCategories() : MutableList<Category> {
        return system.categories.toMutableList()
    }

    private fun initSeries() : MutableList<SerieAppModel>{
        return system.series.map { SerieAppModel(it) }.toMutableList()
    }
}