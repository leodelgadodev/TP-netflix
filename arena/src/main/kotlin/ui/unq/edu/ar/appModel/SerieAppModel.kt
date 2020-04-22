package ui.unq.edu.ar.appModel

import domain.ContentState
import domain.Serie
import org.uqbar.commons.model.annotations.Observable


@Observable
class SerieAppModel (serie : Serie) {

    var id : String = ""
    var title : String = ""
    var cantSeason : Int = 0
    var state : ContentState

    init {
        this.id = serie.idKey()
        this.title = serie.title
        this.cantSeason = serie.seasons.size
        this.state = serie.state
    }
}