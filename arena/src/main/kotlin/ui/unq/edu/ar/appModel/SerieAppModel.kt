package ui.unq.edu.ar.appModel

import domain.*
import org.uqbar.commons.model.annotations.Observable


@Observable
class SerieAppModel (var serie : Serie) {

    var id : String = ""
    var title : String = ""
    var poster : String = ""
    var description : String = ""
    var contentState : ContentState = Unavailable()
    var state : Boolean = contentState != Unavailable()
    var categories : MutableList<Category> = mutableListOf()
    var seasons : MutableList<Season> = mutableListOf()
    var relatedContent : MutableList<Content> = mutableListOf()
    var model : Serie = serie
    var cantSeason = seasons.size
    init {
        this.id = serie.id
        this.title = serie.title
        this.poster = serie.poster
        this.description = serie.description
        this.contentState = serie.state
        this.categories = serie.categories
        this.seasons = serie.seasons
        this.relatedContent = serie.relatedContent

    }
}