package ui.unq.edu.ar.appModel

import domain.Serie
import domain.UNQFlix
import org.uqbar.commons.model.annotations.Observable


@Observable
class UNQFlixAppModel {

    var system : UNQFlix = UNQFlix()
    var series = initSeries()
    var selectItem : SerieAppModel? = null


    fun initSeries() : MutableList<SerieAppModel>{
        return system.series.map { SerieAppModel(it) }.toMutableList()
    }
}