package ui.unq.edu.ar.appModel

import domain.Category
import domain.ContentState
import domain.Serie
import org.uqbar.commons.model.annotations.Observable


@Observable
class SerieAppModel (serie : Serie) {

    var id : String = ""
    var title : String = ""
    var cantSeason : Int = 0
    var state : ContentState
    var description : String = ""
    var poster : String = ""
    var categories = mutableListOf<Category>()

    init {
        this.id = serie.idValue()
        this.title = serie.title
        this.cantSeason = serie.seasons.size
        this.state = serie.state
        this.description = serie.description
        this.poster = serie.poster
        this.categories = serie.categories
    }

}